package net.kapitencraft.enchantments_plus.enchantments.weapon.melee;

import net.kapitencraft.kap_lib.enchantments.abstracts.CountEnchantment;
import net.kapitencraft.kap_lib.helpers.MiscHelper;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class LightningLordEnchantment extends Enchantment implements CountEnchantment {
    public LightningLordEnchantment() {
        super(Rarity.RARE, EnchantmentCategory.WEAPON, MiscHelper.WEAPON_SLOT);
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public String mapName() {
        return "lightningLordMap";
    }

    @Override
    public CountType countType() {
        return CountType.NORMAL;
    }

    @Override
    public int getCountAmount(int level) {
        return (int) (3 + level * 0.4);
    }

    @Override
    public double mainExecute(int level, ItemStack enchanted, LivingEntity attacker, LivingEntity attacked, double damageAmount, int curTick, DamageSource source) {
        if (attacker.level() instanceof ServerLevel serverLevel) {
            LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(serverLevel);
            assert lightning != null;
            lightning.moveTo(Vec3.atBottomCenterOf(attacked.getOnPos()));
            lightning.setVisualOnly(true);
            serverLevel.addFreshEntity(lightning);
        }
        damageAmount *= (1 + level * 0.1);
        return damageAmount;
    }


    @Override
    public String[] getDescriptionMods(int level) {
        return new String[] {(int) (3 + level * 0.4) + "th", level*10 + "%"};
    }

    @Override
    public @NotNull CalculationType type() {
        return CalculationType.ALL;
    }

    @Override
    public @NotNull ProcessPriority priority() {
        return ProcessPriority.HIGH;
    }
}
