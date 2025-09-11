package net.kapitencraft.enchantments_plus.data_gen;

import net.kapitencraft.enchantments_plus.EnchantmentsPlusMod;
import net.kapitencraft.enchantments_plus.registry.ModEnchantmentEffectComponents;
import net.kapitencraft.kap_lib.tags.ExtraTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EnchantmentTagsProvider;
import net.minecraft.tags.EnchantmentTags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModEnchantmentTagsProvider extends EnchantmentTagsProvider {
    public ModEnchantmentTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, EnchantmentsPlusMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(EnchantmentTags.TREASURE).add(
                ModEnchantments.BASALT_WALKER,
                ModEnchantments.BLOCK_BREATHER,
                ModEnchantments.ENDURANCE,
                ModEnchantments.ENLIGHTENMENT,
                ModEnchantments,
                ModEnchantments,
                ModEnchantments
        );
        tag(ExtraTags.Enchantments.ULTIMATE).add(
                ModEnchantments.GLUTTONOUS
        );
        tag(EnchantmentTags.CURSE).add(
                ModEnchantments.INSOMNIA,
                ModEnchantments
        );
    }
}
