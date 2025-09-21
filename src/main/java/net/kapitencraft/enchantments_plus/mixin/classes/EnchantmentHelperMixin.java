package net.kapitencraft.enchantments_plus.mixin.classes;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.kapitencraft.enchantments_plus.ServerConfig;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin {

    @ModifyExpressionValue(method = "getEnchantmentCost", at = @At(value = "CONSTANT", args = "intValue=15"))
    private static int redirectMaxEnchantmentPower(int original) {
        return ServerConfig.getMaxEnchantingPower();
    }
}
