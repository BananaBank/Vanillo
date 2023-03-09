package team.bananabank.vanillo.item;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import team.bananabank.vanillo.registry.VEnchantments;

public class VoidSwordItem extends SwordItem {
    public VoidSwordItem(Tier tier, int attackDamageModifier, float attackSpeedModifier, Properties p_i48460_4_) {
        super(tier, attackDamageModifier, attackSpeedModifier, p_i48460_4_);
    }

    public static void punish(LivingDamageEvent event) {
        LivingEntity target = event.getEntity();
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
