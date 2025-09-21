package net.kapitencraft.enchantments_plus.mixin.classes;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(CrossbowItem.class)
public abstract class CrossbowItemMixin {

    @Shadow public abstract void performShooting(Level level, LivingEntity shooter, InteractionHand hand, ItemStack weapon, float velocity, float inaccuracy, @Nullable LivingEntity target);

    @Redirect(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/CrossbowItem;performShooting(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/InteractionHand;Lnet/minecraft/world/item/ItemStack;FFLnet/minecraft/world/entity/LivingEntity;)V"))
    private void addPrecision(CrossbowItem instance, Level level, LivingEntity player, InteractionHand hand, ItemStack stack, float pVelocity, float pInaccuracy, LivingEntity target) {
        int precision = stack.getEnchantmentLevel(level.registryAccess().holderOrThrow(net.kapitencraft.enchantments_plus.data_gen.ModEnchantments.PRECISION));
        performShooting(level, player, hand, stack, pVelocity, pInaccuracy - (precision * .15f), target);
    }
}
