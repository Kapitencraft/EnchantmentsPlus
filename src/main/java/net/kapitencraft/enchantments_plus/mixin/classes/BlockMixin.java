package net.kapitencraft.enchantments_plus.mixin.classes;

import net.kapitencraft.enchantments_plus.registry.ModEnchantmentEffectComponents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Block.class)
public abstract class BlockMixin {

    @Shadow protected abstract void spawnDestroyParticles(Level pLevel, Player pPlayer, BlockPos pPos, BlockState pState);

    @Redirect(method = "playerWillDestroy", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/Block;spawnDestroyParticles(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)V"))
    private void suppressHarvestClientEffects(Block instance, Level pLevel, Player pPlayer, BlockPos pPos, BlockState pState) {
        if (EnchantmentHelper.has(pPlayer.getMainHandItem(), ModEnchantmentEffectComponents.SILENT_HARVEST.get())) return;
        spawnDestroyParticles(pLevel, pPlayer, pPos, pState);
    }
}
