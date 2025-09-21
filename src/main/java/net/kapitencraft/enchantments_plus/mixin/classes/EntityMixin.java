package net.kapitencraft.enchantments_plus.mixin.classes;

import net.kapitencraft.enchantments_plus.data_gen.ModEnchantments;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(Entity.class)
public class EntityMixin {

    @ModifyVariable(method = "setRemainingFireTicks", at = @At("HEAD"), argsOnly = true)
    private int getReducedFireTicks(int in) {
        if (((Entity) (Object) this) instanceof LivingEntity living) {
            return (int) (in * (1 - EnchantmentHelper.getEnchantmentLevel(living.registryAccess().holderOrThrow(ModEnchantments.EXTINGUISH), living) * .2));
        }
        return in;
    }
}
