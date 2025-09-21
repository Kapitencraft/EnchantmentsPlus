package net.kapitencraft.enchantments_plus.data_gen;

import net.kapitencraft.enchantments_plus.EnchantmentsPlusMod;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = EnchantmentsPlusMod.MOD_ID)
public class DataGenerators {

    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper helper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> registries = event.getLookupProvider();

        registries = generator.addProvider(event.includeServer(), new ModRegistryDataGen(output, registries)).getRegistryProvider();
        generator.addProvider(event.includeClient(), new ModBlockStateProvider(output, helper));
        generator.addProvider(event.includeServer(), new ModGlobalLootModifierProvider(output, registries));
        generator.addProvider(event.includeServer(), new ModEnchantmentTagsProvider(output, registries, helper));
        generator.addProvider(event.includeServer(), new ModItemTagsProvider(output, registries, helper));
        generator.addProvider(event.includeServer(), new ModEntityTypeTagsProvider(output, registries, helper));
    }
}
