package net.kapitencraft.enchantments_plus.mixin.classes;

import net.kapitencraft.enchantments_plus.registry.ModEnchantments;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShearsItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;
import java.util.function.Consumer;

@Mixin(ShearsItem.class)
public class ShearsItemMixin {

    @Redirect(method = "interactLivingEntity", at = @At(value = "INVOKE", target = "Ljava/util/List;forEach(Ljava/util/function/Consumer;)V"))
    private void addTelekinesisToShears(List<ItemStack> instance, Consumer<ItemStack> consumer, ItemStack stack, net.minecraft.world.entity.player.Player playerIn, LivingEntity entity, net.minecraft.world.InteractionHand hand) {
        if (stack.getEnchantmentLevel(ModEnchantments.TELEKINESIS.get()) > 0 && playerIn != null) {
            instance.removeIf(playerIn.getInventory()::add);
        }
        instance.forEach(consumer);
    }
}
