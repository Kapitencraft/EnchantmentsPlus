package net.kapitencraft.enchantments_plus.enchantments.weapon.melee;

import net.kapitencraft.enchantments_plus.registry.ModMobEffects;
import net.kapitencraft.kap_lib.enchantments.abstracts.EffectApplicationEnchantment;
import net.kapitencraft.kap_lib.helpers.MiscHelper;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class StunningEnchantment extends EffectApplicationEnchantment {
    public StunningEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentCategory.WEAPON, MiscHelper.ARMOR_EQUIPMENT, CalculationType.ONLY_MELEE);
    }

    @Override
    protected MobEffect getEffect() {
        return ModMobEffects.STUN.get();
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    protected int getChance(int level) {
        return level;
    }

    @Override
    protected int getScale() {
        return 20;
    }
}
