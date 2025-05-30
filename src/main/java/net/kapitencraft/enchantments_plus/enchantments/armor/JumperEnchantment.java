package net.kapitencraft.enchantments_plus.enchantments.armor;

import com.google.common.collect.Multimap;
import net.kapitencraft.kap_lib.enchantments.abstracts.StatBoostEnchantment;
import net.kapitencraft.kap_lib.helpers.AttributeHelper;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.common.ForgeMod;

import java.util.List;
import java.util.function.Consumer;

public class JumperEnchantment extends Enchantment implements StatBoostEnchantment {
    public JumperEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentCategory.ARMOR_LEGS, new EquipmentSlot[]{EquipmentSlot.LEGS});
    }

    @Override
    public List<EquipmentSlot> slots() {
        return List.of(EquipmentSlot.LEGS);
    }

    @Override
    public Object[] getDescriptionMods(int level) {
        return new Object[] {level * .1};
    }

    @Override
    public Consumer<Multimap<Attribute, AttributeModifier>> getModifiers(int level, ItemStack enchanted, EquipmentSlot slot) {
        return multimap -> multimap.put(ForgeMod.STEP_HEIGHT_ADDITION.get(), AttributeHelper.createModifier("JumperEnchantment", AttributeModifier.Operation.ADDITION, level * .1));
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }
}
