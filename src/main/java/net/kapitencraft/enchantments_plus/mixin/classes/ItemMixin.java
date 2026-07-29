package net.kapitencraft.enchantments_plus.mixin.classes;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.kapitencraft.enchantments_plus.data_gen.ModEnchantments;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Item.class)
public class ItemMixin {

    @WrapOperation(method = "getUseDuration", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/food/FoodProperties;eatDurationTicks()I"))
    private int implementGluttonous(FoodProperties instance, Operation<Integer> original, @Local LivingEntity source) {
        int level = EnchantmentHelper.getEnchantmentLevel(source.level().holderOrThrow(ModEnchantments.GLUTTONOUS), source);
        return (int) (original.call(instance) * (1 - level * .1));
    }
}
