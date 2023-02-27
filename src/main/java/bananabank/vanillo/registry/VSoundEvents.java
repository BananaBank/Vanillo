package rusty.vanillo.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import rusty.vanillo.Vanillo;

public class VSoundEvents {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Vanillo.ID);

    public static final RegistryObject<SoundEvent> RECYCLER = SOUND_EVENTS.register("recycler", () -> new SoundEvent(new ResourceLocation(Vanillo.ID, "recycler")));
}
