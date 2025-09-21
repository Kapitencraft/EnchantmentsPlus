package net.kapitencraft.enchantments_plus.mixin.classes;

import net.minecraft.core.BlockPos;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.core.dispenser.ShearsDispenseItemBehavior;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.phys.AABB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.util.List;

@Mixin(ShearsDispenseItemBehavior.class)
public abstract class ShearsDispenseItemBehaviourMixin extends OptionalDispenseItemBehavior {


    @Shadow
    private static boolean tryShearBeehive(ServerLevel pLevel, BlockPos pPos) {
        return false;
    }

    @Shadow
    private static boolean tryShearLivingEntity(ServerLevel level, BlockPos pos, ItemStack stack) {
        return false;
    }

    /**
     * @author Kapitencraft
     * @reason Chromatic enchantment fix
     */
    @Overwrite
    protected ItemStack execute(BlockSource pSource, ItemStack pStack) {
        ServerLevel serverlevel = pSource.level();
        if (!serverlevel.isClientSide()) {
            BlockPos blockpos = pSource.pos().relative(pSource.state().getValue(DispenserBlock.FACING));
            this.setSuccess(net.neoforged.neoforge.common.CommonHooks.tryDispenseShearsHarvestBlock(pSource, pStack, serverlevel, blockpos) || tryShearBeehive(serverlevel, blockpos) || tryShearLivingEntity(serverlevel, blockpos, pStack));
            if (this.isSuccess()) {
                pStack.hurtAndBreak(1, serverlevel, null, p_348118_ -> {});
                pStack.setCount(0);
            }
        }

        return pStack;
    }
}
