package net.kapitencraft.enchantments_plus.enchantments.weapon.ranged;

import net.kapitencraft.kap_lib.enchantments.abstracts.ModBowEnchantment;
import net.kapitencraft.kap_lib.helpers.MiscHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.Nullable;

public class FrozenArrowsEnchantment extends Enchantment implements ModBowEnchantment {
    public FrozenArrowsEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentCategory.BOW, MiscHelper.WEAPON_SLOT);
    }

    @Override
    public CompoundTag write(CompoundTag tag, int level, ItemStack bow, LivingEntity owner, AbstractArrow arrow) {
        return tag;
    }

    @Override
    public float execute(int level, @Nullable LivingEntity target, CompoundTag tag, ExePhase type, float oldDamage, AbstractArrow arrow) {
        if (type == ExePhase.HIT && target != null) target.setTicksFrozen(target.getTicksFrozen() + level * 40);
        return oldDamage;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public boolean shouldTick() {
        return false;
    }


    @Override
    public String[] getDescriptionMods(int level) {
        return new String[] {"" + level*40};
    }
}
