package net.kapitencraft.enchantments_plus.mixin.classes;

import net.kapitencraft.enchantments_plus.registry.ModEnchantments;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(CrossbowItem.class)
public abstract class CrossbowItemMixin {

    @Shadow
    public static void performShooting(Level pLevel, LivingEntity pShooter, InteractionHand pUsedHand, ItemStack pCrossbowStack, float pVelocity, float pInaccuracy) {
    }

    @Redirect(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/CrossbowItem;performShooting(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/InteractionHand;Lnet/minecraft/world/item/ItemStack;FF)V"))
    private void addPrecision(Level pLevel, LivingEntity pPlayer, InteractionHand pUseHand, ItemStack pStack, float pVelocity, float pInaccuracy) {
        int precision = pStack.getEnchantmentLevel(ModEnchantments.PRECISION.get());
        performShooting(pLevel, pPlayer, pUseHand, pStack, pVelocity, pInaccuracy - (precision * .15f));
    }
}
