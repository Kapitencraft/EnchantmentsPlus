package net.kapitencraft.enchantments_plus.enchantments.armor;

import net.kapitencraft.enchantments_plus.util.Cooldowns;
import net.kapitencraft.kap_lib.cooldown.Cooldown;
import net.kapitencraft.kap_lib.enchantments.abstracts.ExtendedCalculationEnchantment;
import net.kapitencraft.kap_lib.enchantments.abstracts.IArmorEnchantment;
import net.kapitencraft.kap_lib.enchantments.abstracts.IUltimateEnchantment;
import net.kapitencraft.kap_lib.helpers.MiscHelper;
import net.kapitencraft.kap_lib.helpers.ParticleHelper;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class BonkEnchantment extends Enchantment implements ExtendedCalculationEnchantment, IUltimateEnchantment, IArmorEnchantment {

    public BonkEnchantment() {
        super(DEFAULT_RARITY, EnchantmentCategory.ARMOR, DEFAULT_SLOTS);
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
        EquipmentSlot slot = MiscHelper.getSlotForStack(enchanted);
        if (slot.isArmor()) {
            Cooldown cooldown = Cooldowns.BONK_ENCHANTMENT.getOrCreate(slot, 1200);
            if (!cooldown.isActive(attacked)) {
                ParticleHelper.sendParticles(attacked.level(), ParticleTypes.EXPLOSION, false, attacked.position().add(0, 1, 0), 2, 0, 0, 0, 0);
                cooldown.applyCooldown(attacked, true);
                return 0;
            }
        }
        return damage;
    }

    @Override
    protected boolean checkCompatibility(@NotNull Enchantment ench) {
        return !(ench instanceof IUltimateEnchantment);
    }

    @Override
    public String[] getDescriptionMods(int level) {
        return new String[] {};
    }
}
