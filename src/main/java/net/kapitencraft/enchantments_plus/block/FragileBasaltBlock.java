package net.kapitencraft.enchantments_plus.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FrostedIceBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class FragileBasaltBlock extends FrostedIceBlock {
    public FragileBasaltBlock() {
        super(Properties.ofFullCopy(Blocks.BASALT).randomTicks());
    }

    @Override
    protected void melt(@NotNull BlockState state, Level level, @NotNull BlockPos pos) {
        level.setBlockAndUpdate(pos, Blocks.LAVA.defaultBlockState());
        level.neighborChanged(pos, Blocks.LAVA, pos);
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return false;
    }
}
