package net.kapitencraft.enchantments_plus;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.kapitencraft.kap_lib.core.helpers.CommandHelper;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.item.enchantment.ItemEnchantments;

import java.util.List;

public class MaxEnchantCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("max_enchant").requires(CommandHelper::isGameMaster)
                .then(Commands.argument("target", EntityArgument.entity())
                        .executes(MaxEnchantCommand::execute)
                )
        );
    }

    private static int execute(CommandContext<CommandSourceStack> context) {
        return CommandHelper.checkNonConsoleCommand(context, (player, stack) -> {
            ItemStack mainHandItem = player.getMainHandItem();
            if (mainHandItem.getEnchantmentValue() <= 0) {
                stack.sendFailure(Component.translatable("commands.max_enchant.limited_enchantability"));
            }
            Registry<Enchantment> enchantments = player.level().registryAccess().registryOrThrow(Registries.ENCHANTMENT);
            List<EnchantmentInstance> list = enchantments.holders().filter(mainHandItem::supportsEnchantment).map(r -> new EnchantmentInstance(r, r.value().getMaxLevel())).toList();
            ItemEnchantments.Mutable mutable = new ItemEnchantments.Mutable(ItemEnchantments.EMPTY);
            for (EnchantmentInstance instance : list) {
                mutable.set(instance.enchantment, instance.level);
            }
            mainHandItem.set(DataComponents.ENCHANTMENTS, mutable.toImmutable());
            return 1;
        });
    }
}
