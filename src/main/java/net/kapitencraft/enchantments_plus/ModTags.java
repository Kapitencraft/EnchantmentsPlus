package net.kapitencraft.enchantments_plus;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;

public class ModTags {

    public interface Enchantments {
        TagKey<Enchantment> MINING_DROPS_EXCLUSIVE = TagKey.create(Registries.ENCHANTMENT, EnchantmentsPlusMod.res("exclusive_set/mining_drops"));
        TagKey<Enchantment> STRIKE_EXCLUSIVE = TagKey.create(Registries.ENCHANTMENT, EnchantmentsPlusMod.res("strike_exclusive"));
        TagKey<Enchantment> EXECUTION_EXCLUSIVE = TagKey.create(Registries.ENCHANTMENT, EnchantmentsPlusMod.res("execution_exclusive"));
    }

    public interface Items {
        TagKey<Item> MOVEMENT_ARMOR_ENCHANTABLE = TagKey.create(Registries.ITEM, EnchantmentsPlusMod.res("movement_armor_enchantable"));
        TagKey<Item> SHIELD_ENCHANTABLE = TagKey.create(Registries.ITEM, EnchantmentsPlusMod.res("shield_enchantable"));
        TagKey<Item> FARMING_ENCHANTABLE = TagKey.create(Registries.ITEM, EnchantmentsPlusMod.res("farming_enchantable"));
        TagKey<Item> ALL_WEAPONS_ENCHANTABLE = TagKey.create(Registries.ITEM, EnchantmentsPlusMod.res("all_weapons_enchantable"));
    }

    public interface EntityTypes {
        TagKey<EntityType<?>> SENSITIVE_TO_ENDER_SLAYER = TagKey.create(Registries.ENTITY_TYPE, EnchantmentsPlusMod.res("sensitive_to_ender_slayer"));
        TagKey<EntityType<?>> SENSITIVE_TO_JUSTICE = TagKey.create(Registries.ENTITY_TYPE, EnchantmentsPlusMod.res("sensitive_to_justice"));
    }
}
