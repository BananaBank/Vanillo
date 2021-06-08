package rusty.vanillo.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.RailBlock;
import net.minecraft.entity.item.minecart.AbstractMinecartEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class VRailBlock extends RailBlock {
    private final float speed;

    public VRailBlock(float speed, Properties properties) {
        super(properties);

        this.speed = speed;
    }

    @Override
    public float getRailMaxSpeed(BlockState state, World world, BlockPos pos, AbstractMinecartEntity cart) {
        return speed;
    }
}