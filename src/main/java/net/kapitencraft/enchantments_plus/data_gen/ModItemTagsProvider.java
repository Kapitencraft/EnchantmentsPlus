package net.kapitencraft.enchantments_plus.data_gen;

import net.kapitencraft.enchantments_plus.EnchantmentsPlusMod;
import net.kapitencraft.enchantments_plus.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends ItemTagsProvider {

    public ModItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, CompletableFuture.completedFuture(TagLookup.empty()), EnchantmentsPlusMod.MOD_ID, existingFileHelper);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        tag(ModTags.Items.MOVEMENT_ARMOR_ENCHANTABLE)
                .addTags(ItemTags.FOOT_ARMOR_ENCHANTABLE, ItemTags.LEG_ARMOR_ENCHANTABLE);
        tag(ModTags.Items.SHIELD_ENCHANTABLE)
                .addTags(Tags.Items.TOOLS_SHIELD);
        tag(ModTags.Items.FARMING_ENCHANTABLE)
                .addTags(ItemTags.AXES, ItemTags.HOES);
        tag(ModTags.Items.ALL_WEAPONS_ENCHANTABLE)
                .addTags(ItemTags.BOW_ENCHANTABLE, ItemTags.WEAPON_ENCHANTABLE, ItemTags.CROSSBOW_ENCHANTABLE, ItemTags.MACE_ENCHANTABLE);
    }
}
