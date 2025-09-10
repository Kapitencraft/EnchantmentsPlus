package net.kapitencraft.enchantments_plus;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;

public class ModTags {

    public static final class Enchantments {
    }

    public interface Items {
        TagKey<Item> MOVEMENT_ARMOR_ENCHANTABLE = TagKey.create(Registries.ITEM, EnchantmentsPlusMod.res("movement_armor_enchantable"));
        TagKey<Item> SHIELD_ENCHANTABLE = TagKey.create(Registries.ITEM, EnchantmentsPlusMod.res("shield_enchantable"));
        TagKey
    }
}
