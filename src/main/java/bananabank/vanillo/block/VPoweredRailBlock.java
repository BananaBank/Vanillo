package rusty.vanillo.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.PoweredRailBlock;
import net.minecraft.world.level.block.state.BlockState;

public class VPoweredRailBlock extends PoweredRailBlock {
    private final float speed;

    public VPoweredRailBlock(float speed, Properties properties) {
        super(properties, true);
        this.speed = speed;
    }

    @Override
    public float getRailMaxSpeed(BlockState state, Level world, BlockPos pos, AbstractMinecart cart) {
        return speed;
    }
}