package net.kapitencraft.enchantments_plus.enchantments.weapon.melee;

import net.kapitencraft.kap_lib.enchantments.abstracts.IWeaponEnchantment;
import net.kapitencraft.kap_lib.enchantments.abstracts.ModEnchantment;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.DamageEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class JusticeEnchantment extends Enchantment implements IWeaponEnchantment, ModEnchantment {
    public JusticeEnchantment() {
        super(Rarity.COMMON, EnchantmentCategory.WEAPON, new EquipmentSlot[] {EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND});
    }

    @Override
    public float getDamageBonus(int level, MobType mobType, ItemStack enchantedItem) {
        if (mobType == MobType.ILLAGER) {
            return (float) (level * 2.5);
        }
        return 0;
    }

    @Override
    public boolean checkCompatibility(@NotNull Enchantment pOther) {
        return !(pOther instanceof DamageEnchantment);
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public Object[] getDescriptionMods(int level) {
        return new Object[] {level * 2.5};
    }
}
