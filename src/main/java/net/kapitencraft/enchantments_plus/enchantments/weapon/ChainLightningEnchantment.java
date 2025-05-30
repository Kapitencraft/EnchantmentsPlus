package net.kapitencraft.enchantments_plus.enchantments.weapon;

import net.kapitencraft.enchantments_plus.data_gen.ModDamageTypes;
import net.kapitencraft.kap_lib.client.particle.LightningParticleOptions;
import net.kapitencraft.kap_lib.enchantments.abstracts.ExtendedCalculationEnchantment;
import net.kapitencraft.kap_lib.enchantments.abstracts.IUltimateEnchantment;
import net.kapitencraft.kap_lib.enchantments.abstracts.IWeaponEnchantment;
import net.kapitencraft.kap_lib.helpers.MathHelper;
import net.kapitencraft.kap_lib.helpers.MiscHelper;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ChainLightningEnchantment extends Enchantment implements ExtendedCalculationEnchantment, IUltimateEnchantment, IWeaponEnchantment {
    public ChainLightningEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentCategory.WEAPON, MiscHelper.WEAPON_SLOT);
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
    public float execute(int level, ItemStack enchanted, LivingEntity attacker, LivingEntity attacked, float damage, DamageSource source, float attackDamageScale) {
        if (attackDamageScale == 1 && MathHelper.chance(level * .1, attacker) && !source.is(ModDamageTypes.CHAIN_LIGHTNING) && attacked.level() instanceof ServerLevel serverLevel) {
            LivingEntity target;

            List<LivingEntity> previous = new ArrayList<>();
            for (int i = 0; i < level; i++) {
                target = selectTarget(level, serverLevel, attacked, attacker, previous);
                if (target == null) break;
                previous.add(target);
                target.hurt(attacked.damageSources().source(ModDamageTypes.CHAIN_LIGHTNING, attacker), level * .05f * damage);
                serverLevel.sendParticles(new LightningParticleOptions(attacked.getEyePosition(), target.getEyePosition(), 5, 100, .4f, .2f), target.getX(), target.getY(), target.getZ(), 1, 0, 0, 0, 0);
                attacked = target;
            }
        }
        return damage;
    }

    private LivingEntity selectTarget(int enchLevel, Level level, LivingEntity origin, LivingEntity attacker, List<LivingEntity> previous) {
        List<LivingEntity> livings = level.getEntitiesOfClass(
                LivingEntity.class,
                origin.getBoundingBox().inflate(enchLevel * 2),
                living1 -> {
                    if (living1 == attacker || previous.contains(living1)) return false;
                    BlockHitResult result = living1.level().clip(new ClipContext(origin.getEyePosition(), living1.getEyePosition(), ClipContext.Block.COLLIDER, ClipContext.Fluid.WATER, null));
                    return result.getType() == HitResult.Type.MISS;
                }
        );
        return MathHelper.pickRandom(livings, origin.getRandom());
    }

    @Override
    public String[] getDescriptionMods(int level) {
        return new String[] {MathHelper.defRound(level * 10) + "%", (int) (Math.sqrt(level)) + "", level * 2 + "", 1 + level * 5 + "%"};
    }


    @Override
    protected boolean checkCompatibility(@NotNull Enchantment ench) {
        return !(ench instanceof IUltimateEnchantment);
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }
}