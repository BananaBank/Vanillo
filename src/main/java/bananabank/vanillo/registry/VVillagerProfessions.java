package rusty.vanillo.registry;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import rusty.vanillo.Vanillo;

import java.util.List;

public class VVillagerProfessions {
    public static final DeferredRegister<VillagerProfession> PROFESSIONS = DeferredRegister.create(ForgeRegistries.PROFESSIONS, Vanillo.ID);

    public static final RegistryObject<VillagerProfession> CONDUCTOR = PROFESSIONS.register("conductor", () -> new VillagerProfession("conductor", VPoiTypes.CONDUCTOR.get(), ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_WEAPONSMITH));

    public static void addTrades(VillagerTradesEvent event) {
        if (event.getType() == CONDUCTOR.get()) { // Item, Emerald Cost, Item Amount, Stock, XP, Price Multiplier
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            trades.put(1, Lists.newArrayList(new VillagerTrades.EmeraldForItems(Items.COAL, 15, 16, 2), new VillagerTrades.ItemsForEmeralds(new ItemStack(Items.REDSTONE), 1, 2, 12, 1, 0.05f)));
            trades.put(2, Lists.newArrayList(new VillagerTrades.ItemsForEmeralds(new ItemStack(Items.RAIL), 1, 8, 12, 5, 0.05f), new VillagerTrades.EmeraldForItems(Items.GOLD_INGOT, 3, 12, 5), new VillagerTrades.ItemsForEmeralds(new ItemStack(Items.DETECTOR_RAIL), 2, 5, 12, 5, 0.05f), new VillagerTrades.ItemsForEmeralds(new ItemStack(Items.ACTIVATOR_RAIL), 2, 5, 12, 5, 0.05f)));
            trades.put(3, Lists.newArrayList(new VillagerTrades.EmeraldForItems(Items.FLINT, 24, 12, 20), new VillagerTrades.ItemsForEmeralds(new ItemStack(Items.POWERED_RAIL), 2, 1, 24, 5, 0.05f), new VillagerTrades.ItemsForEmeralds(new ItemStack(VItems.GLOWSTONE_RAIL.get()), 2, 8, 12, 10, 0.05f)));
            trades.put(4, Lists.newArrayList(new VillagerTrades.ItemsForEmeralds(new ItemStack(VItems.DIAMOND_POWERED_RAIL.get()), 11, 3, 4, 10, 0.05f), new VillagerTrades.EmeraldForItems(Items.LEVER, 16, 10, 5)));
            trades.put(5, Lists.newArrayList(new VillagerTrades.ItemsForEmeralds(new ItemStack(Items.MINECART), 6, 1, 8, 5, 0.05f), new VillagerTrades.ItemsForEmeralds(new ItemStack(Items.CHEST_MINECART), 8, 1, 8, 5, 0.05f), new VillagerTrades.ItemsForEmeralds(new ItemStack(Items.TNT_MINECART), 10, 1, 8, 5, 0.05f), new VillagerTrades.ItemsForEmeralds(new ItemStack(Items.HOPPER_MINECART), 12, 1, 8, 5, 0.05f)));
        }
    }
}
