package team.bananabank.vanillo.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import team.bananabank.vanillo.Vanillo;

public class VSoundEvents {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Vanillo.ID);

    public static final RegistryObject<SoundEvent> RECYCLER = SOUND_EVENTS.register("recycler", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Vanillo.ID, "recycler")));
}
