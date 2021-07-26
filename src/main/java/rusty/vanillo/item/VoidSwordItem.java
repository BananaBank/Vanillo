package rusty.vanillo.item;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import rusty.vanillo.registry.VEnchantments;

public class VoidSwordItem extends SwordItem {
    public VoidSwordItem(Tier tier, int p_i48460_2_, float p_i48460_3_, Properties p_i48460_4_) {
        super(tier, p_i48460_2_, p_i48460_3_, p_i48460_4_);
    }

    public static void punish(LivingDamageEvent event) {
        LivingEntity target = event.getEntityLiving();
        Entity entity = event.getSource().getEntity();
        if (!(entity instanceof LivingEntity)) return;
        ItemStack sword = ((LivingEntity) entity).getMainHandItem();
        if (!(sword.getItem() instanceof VoidSwordItem)) return;
        int punisherLevel = EnchantmentHelper.getItemEnchantmentLevel(VEnchantments.PUNISHER.get(), sword);
        float maxHealth = target.getMaxHealth();
        float currentHealth = target.getHealth();
        float dmgBoost = 1f + (punisherLevel * 0.25f);
        float dmg = event.getAmount();
        if (currentHealth >= maxHealth * 0.95f) {
            event.setAmount(dmg * dmgBoost);
        }
    }
}
