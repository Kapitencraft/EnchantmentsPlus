package net.kapitencraft.enchantments_plus.loot_table.modifier;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.kapitencraft.enchantments_plus.EnchantmentsPlusMod;
import net.kapitencraft.enchantments_plus.loot_table.LootTableHelper;
import net.kapitencraft.enchantments_plus.registry.ModEnchantments;
import net.kapitencraft.kap_lib.item.loot_table.modifiers.ModLootModifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import org.jetbrains.annotations.NotNull;

public class ScavengerModifier extends ModLootModifier {
    private static final LootContextParamSet PARAM_SET = LootContextParamSet.builder().required(LootContextParams.THIS_ENTITY).required(LootContextParams.KILLER_ENTITY).build();

    public static final Codec<ScavengerModifier> CODEC = RecordCodecBuilder.create(scavengerModifiersInstance -> codecStart(scavengerModifiersInstance).apply(scavengerModifiersInstance, ScavengerModifier::new));

    public ScavengerModifier(LootItemCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        ServerLevel level = context.getLevel();
        LootTable table = level.getServer().getLootData().getLootTable(EnchantmentsPlusMod.res("scavenger_drops"));
        if (!context.hasParam(LootContextParams.KILLER_ENTITY)) return generatedLoot;
        Entity entity = context.getParam(LootContextParams.KILLER_ENTITY);
        if (!(entity instanceof LivingEntity living)) return generatedLoot;
        ItemStack tool = living.getItemBySlot(EquipmentSlot.MAINHAND);
        int lvl = tool.getEnchantmentLevel(ModEnchantments.SCAVENGER.get());
        if (lvl > 0) {
            context.getLevel().getProfiler().push("scavenger modifier");
            if (Mth.randomBetweenInclusive(level.random, 1, 5) <= lvl) {
                LootParams.Builder builder = new LootParams.Builder(level);
                LootTableHelper.copy(builder, context, LootContextParams.THIS_ENTITY, LootContextParams.KILLER_ENTITY);
                generatedLoot.addAll(table.getRandomItems(builder.create(PARAM_SET)));
            }
            context.getLevel().getProfiler().pop();
        }
        return generatedLoot;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}
