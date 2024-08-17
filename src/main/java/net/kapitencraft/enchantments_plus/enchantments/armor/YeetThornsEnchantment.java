package net.kapitencraft.enchantments_plus.enchantments.armor;

import net.kapitencraft.kap_lib.enchantments.abstracts.IArmorEnchantment;
import net.kapitencraft.kap_lib.enchantments.abstracts.ModEnchantment;
import net.kapitencraft.kap_lib.helpers.MiscHelper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.ThornsEnchantment;
import org.jetbrains.annotations.NotNull;

public class YeetThornsEnchantment extends Enchantment implements IArmorEnchantment, ModEnchantment {

    public YeetThornsEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentCategory.ARMOR, MiscHelper.ARMOR_EQUIPMENT);
    }

    @Override
    public void doPostHurt(LivingEntity pAttacker, Entity pTarget, int pLevel) {
        pTarget.setDeltaMovement((pTarget.getX() - pAttacker.getX()) / pLevel * 10, 0.4 * pLevel, (pTarget.getZ() - pAttacker.getZ()) / pLevel * 10);
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    protected boolean checkCompatibility(@NotNull Enchantment enchantment) {
        return !(enchantment instanceof ThornsEnchantment);
    }

    @Override
    public String[] getDescriptionMods(int level) {
        return new String[] {""};
    }
}
