package net.kapitencraft.enchantments_plus.data_gen;

import net.kapitencraft.enchantments_plus.EnchantmentsPlusMod;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.DamageTypeTagsProvider;
import net.minecraft.tags.DamageTypeTags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModDamageTypeTagsProvider extends DamageTypeTagsProvider {
    public ModDamageTypeTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, EnchantmentsPlusMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(DamageTypeTags.IS_FIRE).add(ModDamageTypes.INFERNO);
        tag(DamageTypeTags.BYPASSES_ARMOR).add(ModDamageTypes.INFERNO);
        tag(DamageTypeTags.NO_KNOCKBACK).add(ModDamageTypes.INFERNO, ModDamageTypes.CHAIN_LIGHTNING);
        tag(DamageTypeTags.BYPASSES_COOLDOWN).add(ModDamageTypes.INFERNO);
    }
}
