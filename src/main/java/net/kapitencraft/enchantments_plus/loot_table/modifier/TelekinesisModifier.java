package net.kapitencraft.enchantments_plus.loot_table.modifier;

import com.mojang.serialization.MapCodec;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.kapitencraft.enchantments_plus.registry.ModEnchantmentEffectComponents;
import net.kapitencraft.kap_lib.loot.IConditional;
import net.kapitencraft.kap_lib.loot.modifiers.ModLootModifier;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import org.jetbrains.annotations.NotNull;

public class TelekinesisModifier extends ModLootModifier implements IConditional {
    public static final MapCodec<TelekinesisModifier> CODEC = IConditional.simpleCodec(TelekinesisModifier::new);

    public TelekinesisModifier(LootItemCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        Entity entity = context.getParamOrNull(LootContextParams.ATTACKING_ENTITY);
        if (entity == null) entity = context.getParamOrNull(LootContextParams.THIS_ENTITY);
        if (entity instanceof Player source && EnchantmentHelper.has(source.getMainHandItem(), ModEnchantmentEffectComponents.TELEKINESIS.get())) {
            context.getLevel().getProfiler().push("telekinesis modifier");
            Inventory inventory = source.getInventory();
            generatedLoot.removeIf(inventory::add);
            context.getLevel().getProfiler().pop();
        }
        return generatedLoot;
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}
