package net.kapitencraft.enchantments_plus.mixin.classes;

import net.kapitencraft.enchantments_plus.registry.ModEnchantmentEffectComponents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.IShearable;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

@Mixin(ShearsItem.class)
public class ShearsItemMixin {

    @Redirect(method = "interactLivingEntity", at = @At(value = "INVOKE", target = "Lnet/neoforged/neoforge/common/IShearable;onSheared(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;)Ljava/util/List;"))
    private List<ItemStack> addTelekinesisToShears(IShearable instance, @Nullable Player playerIn, ItemStack item, Level level, BlockPos pos) {
        List<ItemStack> list = instance.onSheared(playerIn, item, level, pos);
        if (EnchantmentHelper.has(item, ModEnchantmentEffectComponents.TELEKINESIS.get()) && playerIn != null) {
            list.removeIf(playerIn.getInventory()::add);
        }
        return list;
    }
}
