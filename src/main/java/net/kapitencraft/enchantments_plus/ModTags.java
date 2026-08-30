package net.kapitencraft.enchantments_plus;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import org.jetbrains.annotations.NotNull;

public class ModTags {

    public interface Enchantments {
        TagKey<Enchantment> MINING_DROPS_EXCLUSIVE = TagKey.create(Registries.ENCHANTMENT, EnchantmentsPlusMod.res("exclusive_set/mining_drops"));
        TagKey<Enchantment> STRIKE_EXCLUSIVE = TagKey.create(Registries.ENCHANTMENT, EnchantmentsPlusMod.res("exclusive_set/strike"));
        TagKey<Enchantment> EXECUTION_EXCLUSIVE = TagKey.create(Registries.ENCHANTMENT, EnchantmentsPlusMod.res("exclusive_set/execution"));
    }

    public interface Items {
        TagKey<Item> MOVEMENT_ARMOR_ENCHANTABLE = create("enchantable/movement_armor");

        TagKey<Item> SHIELD_ENCHANTABLE = create("enchantable/shield");
        TagKey<Item> FARMING_ENCHANTABLE = create("enchantable/farming");
        TagKey<Item> ALL_WEAPONS_ENCHANTABLE = create("enchantable/all_weapons");
        TagKey<Item> WEAPON_AND_MINING_ENCHANTABLE = create("enchantable/weapon_and_mining");
        TagKey<Item> PET_ARMOR_ENCHANTABLE = create("enchantable/pet_armor");
        TagKey<Item> HORSE_ARMOR_ENCHANTABLE = create("enchantable/horse_armor");
        TagKey<Item> WOLF_ARMOR_ENCHANTABLE = create("enchantable/wolf_armor");

        private static @NotNull TagKey<Item> create(String path) {
            return TagKey.create(Registries.ITEM, EnchantmentsPlusMod.res(path));
        }
    }

    public interface EntityTypes {
        TagKey<EntityType<?>> SENSITIVE_TO_ENDER_SLAYER = TagKey.create(Registries.ENTITY_TYPE, EnchantmentsPlusMod.res("sensitive_to_ender_slayer"));
        TagKey<EntityType<?>> SENSITIVE_TO_CUBISM = TagKey.create(Registries.ENTITY_TYPE, EnchantmentsPlusMod.res("sensitive_to_cubism"));
        TagKey<EntityType<?>> SENSITIVE_TO_JUSTICE = TagKey.create(Registries.ENTITY_TYPE, EnchantmentsPlusMod.res("sensitive_to_justice"));
    }
}
