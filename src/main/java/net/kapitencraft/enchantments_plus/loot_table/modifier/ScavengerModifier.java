package net.kapitencraft.enchantments_plus.loot_table.modifier;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.kapitencraft.enchantments_plus.EnchantmentsPlusMod;
import net.kapitencraft.enchantments_plus.data_gen.ModEnchantments;
import net.kapitencraft.enchantments_plus.loot_table.LootTableHelper;
import net.kapitencraft.kap_lib.loot.modifiers.ModLootModifier;
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
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import org.jetbrains.annotations.NotNull;

public class ScavengerModifier extends ModLootModifier {
    private static final LootContextParamSet PARAM_SET = LootContextParamSet.builder().required(LootContextParams.THIS_ENTITY).required(LootContextParams.ATTACKING_ENTITY).build();

    public static final MapCodec<ScavengerModifier> CODEC = RecordCodecBuilder.mapCodec(scavengerModifiersInstance -> codecStart(scavengerModifiersInstance).apply(scavengerModifiersInstance, ScavengerModifier::new));

    public ScavengerModifier(LootItemCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        if (!context.hasParam(LootContextParams.ATTACKING_ENTITY)) return generatedLoot;
        Entity entity = context.getParam(LootContextParams.ATTACKING_ENTITY);
        if (!(entity instanceof LivingEntity living)) return generatedLoot;
        ItemStack tool = living.getItemBySlot(EquipmentSlot.MAINHAND);
        ServerLevel level = context.getLevel();
        int lvl = tool.getEnchantmentLevel(level.registryAccess().holderOrThrow(ModEnchantments.SCAVENGER));
        if (lvl > 0) {
            context.getLevel().getProfiler().push("scavenger modifier");
            if (Mth.randomBetweenInclusive(level.random, 1, 5) <= lvl) {
                LootTable table = level.getServer().reloadableRegistries().getLootTable(EnchantmentsPlusMod.SCAVENGER_DROPS);
                LootParams.Builder builder = new LootParams.Builder(level);
                LootTableHelper.copy(builder, context, LootContextParams.THIS_ENTITY, LootContextParams.ATTACKING_ENTITY);
                LootParams params = builder.create(PARAM_SET);
                generatedLoot.addAll(table.getRandomItems(params));
            }
            context.getLevel().getProfiler().pop();
        }
        return generatedLoot;
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}
