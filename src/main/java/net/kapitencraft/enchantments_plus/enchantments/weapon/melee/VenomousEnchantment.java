package net.kapitencraft.enchantments_plus.enchantments.weapon.melee;

import net.kapitencraft.kap_lib.enchantments.abstracts.ExtendedCalculationEnchantment;
import net.kapitencraft.kap_lib.enchantments.abstracts.IWeaponEnchantment;
import net.kapitencraft.kap_lib.helpers.MiscHelper;
import net.kapitencraft.kap_lib.util.attribute.TimedModifier;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class VenomousEnchantment extends Enchantment implements ExtendedCalculationEnchantment, IWeaponEnchantment {
    public static final UUID ID = UUID.fromString("c3f0728b-ecd3-487b-9e5b-a55ae35241c0");
    public static final String TIMER_ID = "VenomousTimer";
    public VenomousEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentCategory.WEAPON, MiscHelper.WEAPON_SLOT);
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }


    @Override
    public @NotNull CalculationType type() {
        return CalculationType.ALL;
    }

    @Override
    public @NotNull ProcessPriority priority() {
        return ProcessPriority.LOWEST;
    }

    @Override
    public double execute(int level, ItemStack enchanted, LivingEntity attacker, LivingEntity attacked, double damage, DamageSource source) {
        AttributeInstance speed = attacked.getAttribute(Attributes.MOVEMENT_SPEED);
        assert speed != null;
        try {
            speed.addTransientModifier(new TimedModifier(TIMER_ID, -0.05 * level, AttributeModifier.Operation.MULTIPLY_TOTAL, level * 20));
        } catch (Throwable ignored) {}
        return damage;
    }

    @Override
    public Object[] getDescriptionMods(int level) {
        return new Object[] {level * 2 + "%", level};
    }
}
