package rusty.vanillo.item;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.EntityLeaveWorldEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import rusty.vanillo.registry.VEnchantments;

import java.util.UUID;

public class VoidBowItem extends BowItem {
    private static final MobEffectInstance[] IMPULSE_EFFECT = new MobEffectInstance[] {
            new MobEffectInstance(MobEffects.POISON, 100, 1, false, false, false, null),
            new MobEffectInstance(MobEffects.WITHER, 100, 1, false, false, false, null),
            new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 1, false, false, false, null),
            new MobEffectInstance(MobEffects.WEAKNESS, 200, 1, false, false, false, null)
    };
    public static final Object2IntMap<UUID> ARROW_BANISH_LEVELS = new Object2IntOpenHashMap<>();
    public static final Object2IntMap<UUID> ARROW_ABYSS_LEVELS = new Object2IntOpenHashMap<>();

    public VoidBowItem(Properties properties) {
        super(properties);
    }

    @Override
    public AbstractArrow customArrow(AbstractArrow arrow) {
        arrow.setBaseDamage(3.0);
        return arrow;
    }

    @Override
    public void releaseUsing(ItemStack bow, Level level, LivingEntity player, int duration) {
        if (player instanceof Player) {
            Player playerentity = (Player)player;
            boolean flag = playerentity.getAbilities().instabuild || EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, bow) > 0;
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
                    boolean flag1 = playerentity.getAbilities().instabuild || (itemstack.getItem() instanceof ArrowItem && ((ArrowItem)itemstack.getItem()).isInfinite(itemstack, bow, playerentity));
                    if (!level.isClientSide) {
                        ArrowItem arrowitem = (ArrowItem)(itemstack.getItem() instanceof ArrowItem ? itemstack.getItem() : Items.ARROW);
                        AbstractArrow abstractarrowentity = arrowitem.createArrow(level, itemstack, playerentity);
                        abstractarrowentity = customArrow(abstractarrowentity);
                        abstractarrowentity.shootFromRotation(playerentity, playerentity.getXRot(), playerentity.getYRot(), 0.0F, f * 3.0F, 1.0F);
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
                        if (flag1 || playerentity.getAbilities().instabuild && (itemstack.getItem() == Items.SPECTRAL_ARROW || itemstack.getItem() == Items.TIPPED_ARROW)) {
                            abstractarrowentity.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                        }

                        level.addFreshEntity(abstractarrowentity);
                    }

                    level.playSound(null, playerentity.getX(), playerentity.getY(), playerentity.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F / (level.random.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
                    if (!flag1 && !playerentity.getAbilities().instabuild) {
                        itemstack.shrink(1);
                        if (itemstack.isEmpty()) {
                            playerentity.getInventory().removeItem(itemstack);
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
        AbstractArrow arrow;
        Entity entity = event.getSource().getDirectEntity();
        if (entity instanceof AbstractArrow) {
            arrow = (AbstractArrow) entity;
        } else {
            // If we're not an arrow skip the event
            return;
        }
        Level level = arrow.level;
        // Remove from the map after we're done
        int abyssLevel = ARROW_ABYSS_LEVELS.removeInt(arrow.getUUID());
        if (abyssLevel == 0) {
            return;
        }
        LivingEntity target = event.getEntityLiving();
        // If the chance succeeds
        if (level.random.nextInt(2) == 0) {
            MobEffectInstance choice = IMPULSE_EFFECT[level.random.nextInt(4)];
            target.addEffect(new MobEffectInstance(choice.getEffect(), choice.getDuration(), choice.getAmplifier(), false, true));
        }

    }

    public static void banish(LivingDamageEvent event) {
        AbstractArrow arrow;
        Entity entity = event.getSource().getDirectEntity();
        if (entity instanceof AbstractArrow) {
            arrow = (AbstractArrow) entity;
        } else {
            // If we're not an arrow skip the event
            return;
        }
        Level level = arrow.level;
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
        banishChance = Mth.clamp(banishChance, 1, Integer.MAX_VALUE);
        int bound = (int) Math.ceil(100.0f / ((float) banishChance));
        int randomNumber = level.random.nextInt(bound);
        // If chance is successful and target has below 25% of their max hp
        if (randomNumber == 0 && (currentHealth - event.getAmount()) <= maxHealth * (0.25f)) {
            target.setHealth(0);

            // Spawn lightning
            LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(level);
            double targetPosX = target.getX();
            double targetPosY = target.getY();
            double targetPosZ = target.getZ();
            lightning.moveTo(targetPosX, targetPosY, targetPosZ);
            lightning.setVisualOnly(true);
            Entity owner = event.getSource().getEntity();
            lightning.setCause(owner instanceof ServerPlayer ? (ServerPlayer)owner : null);
            level.addFreshEntity(lightning);
            level.addParticle(ParticleTypes.CRIT, targetPosX, targetPosY, targetPosZ, 100, 100, 100);
        }
    }

    public static void cleanupEntities(EntityLeaveWorldEvent event) {
        ARROW_ABYSS_LEVELS.removeInt(event.getEntity().getUUID());
        ARROW_BANISH_LEVELS.removeInt(event.getEntity().getUUID());
    }
}