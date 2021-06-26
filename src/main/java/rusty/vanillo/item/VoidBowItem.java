package rusty.vanillo.item;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.Stats;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityLeaveWorldEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import rusty.vanillo.registry.VEnchantments;

import java.util.UUID;

public class VoidBowItem extends BowItem {
    private static final EffectInstance[] IMPULSE_EFFECT = new EffectInstance[] {
            new EffectInstance(Effects.POISON, 100, 1, false, false, false, null),
            new EffectInstance(Effects.WITHER, 100, 1, false, false, false, null),
            new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 100, 1, false, false, false, null),
            new EffectInstance(Effects.WEAKNESS, 200, 1, false, false, false, null)
    };
    public static final Object2IntMap<UUID> ARROW_BANISH_LEVELS = new Object2IntOpenHashMap<>();
    public static final Object2IntMap<UUID> ARROW_ABYSS_LEVELS = new Object2IntOpenHashMap<>();

    public VoidBowItem(Properties properties) {
        super(properties);
    }

    @Override
    public AbstractArrowEntity customArrow(AbstractArrowEntity arrow) {
        arrow.setBaseDamage(3.0);
        return arrow;
    }

    @Override
    public void releaseUsing(ItemStack bow, World level, LivingEntity player, int duration) {
        if (player instanceof PlayerEntity) {
            PlayerEntity playerentity = (PlayerEntity)player;
            boolean flag = playerentity.abilities.instabuild || EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, bow) > 0;
            ItemStack itemstack = playerentity.getProjectile(bow);

            int i = this.getUseDuration(bow) - duration;
            i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(bow, level, playerentity, i, !itemstack.isEmpty() || flag);
            if (i < 0) return;

            if (!itemstack.isEmpty() || flag) {
                if (itemstack.isEmpty()) {
                    itemstack = new ItemStack(Items.ARROW);
                }

                float f = getPowerForTime(i);
                if (!((double)f < 0.1D)) {
                    boolean flag1 = playerentity.abilities.instabuild || (itemstack.getItem() instanceof ArrowItem && ((ArrowItem)itemstack.getItem()).isInfinite(itemstack, bow, playerentity));
                    if (!level.isClientSide) {
                        ArrowItem arrowitem = (ArrowItem)(itemstack.getItem() instanceof ArrowItem ? itemstack.getItem() : Items.ARROW);
                        AbstractArrowEntity abstractarrowentity = arrowitem.createArrow(level, itemstack, playerentity);
                        abstractarrowentity = customArrow(abstractarrowentity);
                        abstractarrowentity.shootFromRotation(playerentity, playerentity.xRot, playerentity.yRot, 0.0F, f * 3.0F, 1.0F);
                        // Get the level from the bow then apply it to the arrow
                        UUID uuid = abstractarrowentity.getUUID();

                        int banish = EnchantmentHelper.getItemEnchantmentLevel(VEnchantments.BANISHMENT.get(), bow);
                        int abyss = EnchantmentHelper.getItemEnchantmentLevel(VEnchantments.ABYSSAL_IMPULSE.get(), bow);
                        if (banish != 0) {
                            ARROW_BANISH_LEVELS.put(uuid, banish);
                        }
                        if (abyss != 0) {
                            ARROW_ABYSS_LEVELS.put(uuid, abyss);
                        }

                        if (f == 1.0F) {
                            abstractarrowentity.setCritArrow(true);
                        }

                        int j = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER_ARROWS, bow);
                        if (j > 0) {
                            abstractarrowentity.setBaseDamage(abstractarrowentity.getBaseDamage() + (double)j * 0.5D + 0.5D);
                        }

                        int k = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PUNCH_ARROWS, bow);
                        if (k > 0) {
                            abstractarrowentity.setKnockback(k);
                        }

                        if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FLAMING_ARROWS, bow) > 0) {
                            abstractarrowentity.setSecondsOnFire(100);
                        }

                        bow.hurtAndBreak(1, playerentity, playerIn -> playerIn.broadcastBreakEvent(playerentity.getUsedItemHand()));
                        if (flag1 || playerentity.abilities.instabuild && (itemstack.getItem() == Items.SPECTRAL_ARROW || itemstack.getItem() == Items.TIPPED_ARROW)) {
                            abstractarrowentity.pickup = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
                        }

                        level.addFreshEntity(abstractarrowentity);
                    }

                    level.playSound(null, playerentity.getX(), playerentity.getY(), playerentity.getZ(), SoundEvents.ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
                    if (!flag1 && !playerentity.abilities.instabuild) {
                        itemstack.shrink(1);
                        if (itemstack.isEmpty()) {
                            playerentity.inventory.removeItem(itemstack);
                        }
                    }

                    playerentity.awardStat(Stats.ITEM_USED.get(this));
                }
            }
        }
    }

    public static void damageEvent(LivingDamageEvent event) {
        abyssalImpulse(event);
        banish(event);
    }

    public static void abyssalImpulse(LivingDamageEvent event) {
        AbstractArrowEntity arrow;
        Entity entity = event.getSource().getDirectEntity();
        if (entity instanceof AbstractArrowEntity) {
            arrow = (AbstractArrowEntity) entity;
        } else {
            // If we're not an arrow skip the event
            return;
        }
        World level = arrow.level;
        // Remove from the map after we're done
        int abyssLevel = ARROW_ABYSS_LEVELS.removeInt(arrow.getUUID());
        if (abyssLevel == 0) {
            return;
        }
        LivingEntity target = event.getEntityLiving();
        // If the chance succeeds
        if (level.random.nextInt(2) == 0) {
            EffectInstance choice = IMPULSE_EFFECT[level.random.nextInt(4)];
            target.addEffect(new EffectInstance(choice.getEffect(), choice.getDuration(), choice.getAmplifier(), false, true));
        }

    }

    public static void banish(LivingDamageEvent event) {
        AbstractArrowEntity arrow;
        Entity entity = event.getSource().getDirectEntity();
        if (entity instanceof AbstractArrowEntity) {
            arrow = (AbstractArrowEntity) entity;
        } else {
            // If we're not an arrow skip the event
            return;
        }
        World level = arrow.level;
        // Remove from the map after we're done
        int banishLevel = ARROW_BANISH_LEVELS.removeInt(arrow.getUUID());
        // Calculates the base chance from the book level. Higher book levels
        // increase the base chance that the enchantment will activate.
        int banishChance = banishLevel * 5;
        LivingEntity target = event.getEntityLiving();
        float maxHealth = target.getMaxHealth();
        float currentHealth = target.getHealth();

        // Decrease chance on higher-health mobs
        if (maxHealth > 20) {
            banishChance -= 5;

            // Skip mobs if your enchant is not high enough
            if (banishLevel == 1) {
                return;
            }
        }
        // Forces this number to be over 0
        banishChance = MathHelper.clamp(banishChance, 1, Integer.MAX_VALUE);
        int bound = (int) Math.ceil(100.0f / ((float) banishChance));
        int randomNumber = level.random.nextInt(bound);
        // If chance is successful and target has below 25% of their max hp
        if (randomNumber == 0 && (currentHealth - event.getAmount()) <= maxHealth * (0.25f)) {
            target.setHealth(0);

            // Spawn lightning
            LightningBoltEntity lightning = EntityType.LIGHTNING_BOLT.create(level);
            double targetPosX = target.getX();
            double targetPosY = target.getY();
            double targetPosZ = target.getZ();
            lightning.moveTo(targetPosX, targetPosY, targetPosZ);
            lightning.setVisualOnly(true);
            Entity owner = event.getSource().getEntity();
            lightning.setCause(owner instanceof ServerPlayerEntity ? (ServerPlayerEntity)owner : null);
            level.addFreshEntity(lightning);
            level.addParticle(ParticleTypes.CRIT, targetPosX, targetPosY, targetPosZ, 100, 100, 100);
        }
    }

    public static void cleanupEntities(EntityLeaveWorldEvent event) {
        ARROW_ABYSS_LEVELS.removeInt(event.getEntity().getUUID());
        ARROW_BANISH_LEVELS.removeInt(event.getEntity().getUUID());
    }
}