package net.kapitencraft.enchantments_plus.mixin.classes;

import net.minecraft.world.entity.projectile.AbstractArrow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AbstractArrow.class)
public class AbstractArrowMixin {

    @Redirect(method = "playerTouch", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/projectile/AbstractArrow;isNoPhysics()Z"))
    private boolean checkSlow(AbstractArrow instance) {
        return instance.isNoPhysics() || instance.isNoGravity() && instance.getDeltaMovement().length() < 0.05;
    }
}
