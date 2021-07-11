package rusty.vanillo.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SSpawnObjectPacket;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BanishmentLightningEntity extends LightningBoltEntity {
    public BanishmentLightningEntity(EntityType<? extends LightningBoltEntity> entityType, World level) {
        super(entityType, level);
        setVisualOnly(true);
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return new SSpawnObjectPacket(this);
    }

    @OnlyIn(Dist.CLIENT)
    public boolean shouldRenderAtSqrDistance(double p_70112_1_) {
        double d0 = 64.0D * getViewScale();
        return p_70112_1_ < d0 * d0;
    }
}
