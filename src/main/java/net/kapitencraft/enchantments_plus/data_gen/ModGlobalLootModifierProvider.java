package net.kapitencraft.enchantments_plus.data_gen;

import net.kapitencraft.enchantments_plus.EnchantmentsPlusMod;
import net.kapitencraft.enchantments_plus.loot_table.modifier.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.AnyOfCondition;
import net.minecraft.world.level.storage.loot.predicates.InvertedLootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.conditions.NotCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;

import java.util.concurrent.CompletableFuture;

public class ModGlobalLootModifierProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, EnchantmentsPlusMod.MOD_ID);
    }

    @Override
    protected void start() {
        this.add("chromatic", new ChromaticModifier(
                new LootItemCondition[]{
                        AnyOfCondition.anyOf(
                                LootTableIdCondition.builder(ResourceLocation.withDefaultNamespace("entities/sheep/white")),
                                LootTableIdCondition.builder(ResourceLocation.withDefaultNamespace("entities/sheep/orange")),
                                LootTableIdCondition.builder(ResourceLocation.withDefaultNamespace("entities/sheep/magenta")),
                                LootTableIdCondition.builder(ResourceLocation.withDefaultNamespace("entities/sheep/light_blue")),
                                LootTableIdCondition.builder(ResourceLocation.withDefaultNamespace("entities/sheep/yellow")),
                                LootTableIdCondition.builder(ResourceLocation.withDefaultNamespace("entities/sheep/lime")),
                                LootTableIdCondition.builder(ResourceLocation.withDefaultNamespace("entities/sheep/pink")),
                                LootTableIdCondition.builder(ResourceLocation.withDefaultNamespace("entities/sheep/gray")),
                                LootTableIdCondition.builder(ResourceLocation.withDefaultNamespace("entities/sheep/light_gray")),
                                LootTableIdCondition.builder(ResourceLocation.withDefaultNamespace("entities/sheep/cyan")),
                                LootTableIdCondition.builder(ResourceLocation.withDefaultNamespace("entities/sheep/purple")),
                                LootTableIdCondition.builder(ResourceLocation.withDefaultNamespace("entities/sheep/blue")),
                                LootTableIdCondition.builder(ResourceLocation.withDefaultNamespace("entities/sheep/brown")),
                                LootTableIdCondition.builder(ResourceLocation.withDefaultNamespace("entities/sheep/green")),
                                LootTableIdCondition.builder(ResourceLocation.withDefaultNamespace("entities/sheep/red")),
                                LootTableIdCondition.builder(ResourceLocation.withDefaultNamespace("entities/sheep/black"))
                        ).build()
                }
        ));
        this.add("compact", new CompactModifier(new LootItemCondition[0]));
        this.add("replenish", new ReplenishModifier(new LootItemCondition[0]));
        this.add("scavenger_drops", new ScavengerModifier(new LootItemCondition[]{
                new InvertedLootItemCondition(LootTableIdCondition.builder(EnchantmentsPlusMod.SCAVENGER_DROPS.location()).build())
        }));
        this.add("smelt", new SmeltModifier(new LootItemCondition[0]));
        this.add("telekinesis", new TelekinesisModifier(new LootItemCondition[0]));
    }
}
