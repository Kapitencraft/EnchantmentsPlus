package net.kapitencraft.enchantments_plus.mixin.classes;

import net.kapitencraft.enchantments_plus.enchantments.armor.BasaltWalkerEnchantment;
import net.kapitencraft.enchantments_plus.registry.ModEnchantments;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.common.extensions.IForgeLivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin implements IForgeLivingEntity {

    @Inject(method = "onChangedBlock", at = @At("TAIL"))
    private void addBasaltWalker(BlockPos pPos, CallbackInfo ci) {
        int i = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.BASALT_WALKER.get(), self());
        if (i > 0) {
            BasaltWalkerEnchantment.onEntityMoved(self(), self().blockPosition(), i);
        }
    }
}
