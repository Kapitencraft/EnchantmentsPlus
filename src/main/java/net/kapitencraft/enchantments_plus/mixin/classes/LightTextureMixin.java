package net.kapitencraft.enchantments_plus.mixin.classes;

import net.kapitencraft.enchantments_plus.registry.ModEnchantments;
import net.kapitencraft.kap_lib.helpers.MiscHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.core.Holder;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LightTexture.class)
public class LightTextureMixin {

    @Shadow @Final private Minecraft minecraft;

    @Redirect(method = "calculateDarknessScale", at = @At(value = "INVOKE", target = "Ljava/lang/Math;max(FF)F"))
    private float reduceDarkness(float a, float b, LivingEntity pLiving) {
        int enlightenmentLevel = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.ENLIGHTENMENT.get(), pLiving);
        return Math.max(a, b - (enlightenmentLevel * .2f));
    }

    @SuppressWarnings("DataFlowIssue")
    @ModifyVariable(method = "updateLightTexture", at = @At("LOAD"), ordinal = 7)
    private float increaseNightVision(float value) {
        int enlightenmentLevel = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.ENLIGHTENMENT.get(), this.minecraft.player);
        return value + enlightenmentLevel * .2f;
    }
}
