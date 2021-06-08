package rusty.vanillo.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.PoweredRailBlock;
import net.minecraft.entity.item.minecart.AbstractMinecartEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class VPoweredRailBlock extends PoweredRailBlock {
    private final float speed;

    public VPoweredRailBlock(float speed, Properties properties) {
        super(properties, true);
        this.speed = speed;
    }

    @Override
    public float getRailMaxSpeed(BlockState state, World world, BlockPos pos, AbstractMinecartEntity cart) {
        return speed;
    }
}