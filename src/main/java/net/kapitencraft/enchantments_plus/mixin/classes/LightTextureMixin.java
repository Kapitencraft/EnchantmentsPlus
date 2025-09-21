package net.kapitencraft.enchantments_plus.mixin.classes;

import net.kapitencraft.enchantments_plus.data_gen.ModEnchantments;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LightTexture.class)
public class LightTextureMixin {

    @Shadow @Final private Minecraft minecraft;

    @Redirect(method = "calculateDarknessScale", at = @At(value = "INVOKE", target = "Ljava/lang/Math;max(FF)F"))
    private float reduceDarkness(float a, float b, LivingEntity pLiving) {
        int enlightenmentLevel = getEnlightenmentLevel(pLiving);
        return Math.max(a, b - (enlightenmentLevel * .2f));
    }

    @Unique
    private static int getEnlightenmentLevel(LivingEntity living) {
        return EnchantmentHelper.getEnchantmentLevel(living.registryAccess().holderOrThrow(ModEnchantments.ENLIGHTENMENT), living);
    }

    @ModifyVariable(method = "updateLightTexture", at = @At("LOAD"), ordinal = 7)
    private float increaseNightVision(float value) {
        int enlightenmentLevel = getEnlightenmentLevel(this.minecraft.player);
        return value + enlightenmentLevel * .2f;
    }
}
