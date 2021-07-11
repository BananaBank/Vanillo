package rusty.vanillo.item;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import rusty.vanillo.registry.VEnchantments;

public class VoidSwordItem extends SwordItem {
    public VoidSwordItem(IItemTier tier, int p_i48460_2_, float p_i48460_3_, Properties p_i48460_4_) {
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
