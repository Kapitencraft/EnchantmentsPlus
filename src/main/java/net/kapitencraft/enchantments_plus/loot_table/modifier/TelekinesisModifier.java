package net.kapitencraft.enchantments_plus.loot_table.modifier;

import com.mojang.serialization.Codec;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.kapitencraft.kap_lib.helpers.LootTableHelper;
import net.kapitencraft.enchantments_plus.registry.ModEnchantments;
import net.kapitencraft.kap_lib.item.loot_table.IConditional;
import net.kapitencraft.kap_lib.item.loot_table.modifiers.ModLootModifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import org.jetbrains.annotations.NotNull;

public class TelekinesisModifier extends ModLootModifier implements IConditional {
    public static final Codec<TelekinesisModifier> CODEC = LootTableHelper.simpleCodec(TelekinesisModifier::new);

    public TelekinesisModifier(LootItemCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        Player source = LootTableHelper.getPlayerSource(context);
        if (source != null && source.getMainHandItem().getEnchantmentLevel(ModEnchantments.TELEKINESIS.get()) > 0) {
            context.getLevel().getProfiler().push("telekinesis modifier");
            Inventory inventory = source.getInventory();
            generatedLoot.removeIf(inventory::add);
            context.getLevel().getProfiler().pop();
        }
        return generatedLoot;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}
