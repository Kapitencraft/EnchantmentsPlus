package net.kapitencraft.enchantments_plus.mixin.classes;

import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.core.dispenser.ShearsDispenseItemBehavior;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.IForgeShearable;
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

    /**
     * @author Kapitencraft
     * @reason Chromatic enchantment fix
     */
    @Overwrite
    protected ItemStack execute(BlockSource pSource, ItemStack pStack) {
        ServerLevel serverlevel = pSource.getLevel();
        if (!serverlevel.isClientSide()) {
            BlockPos blockpos = pSource.getPos().relative(pSource.getBlockState().getValue(DispenserBlock.FACING));
            this.setSuccess(tryShearBeehive(serverlevel, blockpos) || tryShearLivingEntity(serverlevel, blockpos, pStack));
            if (this.isSuccess() && pStack.hurt(1, serverlevel.getRandom(), null)) {
                pStack.setCount(0);
            }
        }

        return pStack;
    }

    @Unique
    private static boolean tryShearLivingEntity(ServerLevel pLevel, BlockPos pPos, ItemStack shear) {
        for(LivingEntity livingentity : pLevel.getEntitiesOfClass(LivingEntity.class, new AABB(pPos), EntitySelector.NO_SPECTATORS)) {
            if (livingentity instanceof IForgeShearable shearable) {
                if (shearable.isShearable(shear, pLevel, pPos)) {
                    List<ItemStack> drops = shearable.onSheared(null, shear, pLevel, pPos, shear.getEnchantmentLevel(Enchantments.BLOCK_FORTUNE));
                    java.util.Random rand = new java.util.Random();
                    drops.forEach(d -> {
                        net.minecraft.world.entity.item.ItemEntity ent = livingentity.spawnAtLocation(d, 1.0F);
                        if (ent != null) ent.setDeltaMovement(ent.getDeltaMovement().add((rand.nextFloat() - rand.nextFloat()) * 0.1F, rand.nextFloat() * 0.05F, (rand.nextFloat() - rand.nextFloat()) * 0.1F));
                    });
                    return true;
                }
            }
        }

        return false;
    }
}
