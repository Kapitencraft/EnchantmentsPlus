package net.kapitencraft.enchantments_plus.data_gen;

import net.kapitencraft.enchantments_plus.EnchantmentsPlusMod;
import net.kapitencraft.enchantments_plus.loot_table.modifier.*;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.AnyOfCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;

public class ModGlobalLootModifierProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifierProvider(PackOutput output) {
        super(output, EnchantmentsPlusMod.MOD_ID);
    }

    @Override
    protected void start() {
        this.add("chromatic", new ChromaticModifier(
                new LootItemCondition[]{
                        AnyOfCondition.anyOf(
                                LootTableIdCondition.builder(new ResourceLocation("entities/sheep/white")),
                                LootTableIdCondition.builder(new ResourceLocation("entities/sheep/orange")),
                                LootTableIdCondition.builder(new ResourceLocation("entities/sheep/magenta")),
                                LootTableIdCondition.builder(new ResourceLocation("entities/sheep/light_blue")),
                                LootTableIdCondition.builder(new ResourceLocation("entities/sheep/yellow")),
                                LootTableIdCondition.builder(new ResourceLocation("entities/sheep/lime")),
                                LootTableIdCondition.builder(new ResourceLocation("entities/sheep/pink")),
                                LootTableIdCondition.builder(new ResourceLocation("entities/sheep/gray")),
                                LootTableIdCondition.builder(new ResourceLocation("entities/sheep/light_gray")),
                                LootTableIdCondition.builder(new ResourceLocation("entities/sheep/cyan")),
                                LootTableIdCondition.builder(new ResourceLocation("entities/sheep/purple")),
                                LootTableIdCondition.builder(new ResourceLocation("entities/sheep/blue")),
                                LootTableIdCondition.builder(new ResourceLocation("entities/sheep/brown")),
                                LootTableIdCondition.builder(new ResourceLocation("entities/sheep/green")),
                                LootTableIdCondition.builder(new ResourceLocation("entities/sheep/red")),
                                LootTableIdCondition.builder(new ResourceLocation("entities/sheep/black"))
                        ).build()
                }
        ));
        this.add("compact", new CompactModifier(new LootItemCondition[0]));
        this.add("replenish", new ReplenishModifier(new LootItemCondition[0]));
        this.add("scavenger_drops", new ScavengerModifier(new LootItemCondition[0]));
        this.add("smelt", new SmeltModifier(new LootItemCondition[0]));
        this.add("telekinesis", new TelekinesisModifier(new LootItemCondition[0]));
    }
}
