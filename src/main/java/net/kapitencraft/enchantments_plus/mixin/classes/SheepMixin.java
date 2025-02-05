package net.kapitencraft.enchantments_plus.mixin.classes;

import net.kapitencraft.enchantments_plus.registry.ModEnchantments;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import javax.annotation.Nullable;

@Mixin(Sheep.class)
public class SheepMixin {

    @Redirect(method = {"onSheared"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/animal/Sheep;getColor()Lnet/minecraft/world/item/DyeColor;"))
    private DyeColor chromaticColor(Sheep instance, @Nullable Player player, @org.jetbrains.annotations.NotNull ItemStack item, Level world, BlockPos pos, int fortune) {
        if (item.getEnchantmentLevel(ModEnchantments.CHROMATIC.get()) > 0) return DyeColor.byId(world.getRandom().nextIntBetweenInclusive(0, 15));
        return instance.getColor();
    }
}
