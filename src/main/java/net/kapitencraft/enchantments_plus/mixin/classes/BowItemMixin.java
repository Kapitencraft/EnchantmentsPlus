package net.kapitencraft.enchantments_plus.mixin.classes;

import net.kapitencraft.enchantments_plus.registry.ModEnchantments;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BowItem.class)
public class BowItemMixin {

    @Redirect(method = "releaseUsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/projectile/AbstractArrow;shootFromRotation(Lnet/minecraft/world/entity/Entity;FFFFF)V"))
    private void addPrecision(AbstractArrow instance, Entity entity, float xRot, float yRot, float zRot, float velocity, float inaccuracy, ItemStack pStack) {
        int precision = pStack.getEnchantmentLevel(ModEnchantments.PRECISION.get());
        instance.shootFromRotation(entity, xRot, yRot, zRot, velocity, inaccuracy - (precision * .15f));
    }
}
