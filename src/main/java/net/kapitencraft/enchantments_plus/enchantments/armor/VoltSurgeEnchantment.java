package net.kapitencraft.enchantments_plus.enchantments.armor;

import net.kapitencraft.enchantments_plus.registry.ModEnchantments;
import net.kapitencraft.kap_lib.helpers.MiscHelper;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.*;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class VoltSurgeEnchantment extends Enchantment {
    public VoltSurgeEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentCategory.ARMOR, MiscHelper.ARMOR_EQUIPMENT);
    }

    @Override
    public void doPostHurt(LivingEntity pUser, @NotNull Entity pAttacker, int pLevel) {
         RandomSource randomsource = pUser.getRandom();
        Map.Entry<EquipmentSlot, ItemStack> entry = EnchantmentHelper.getRandomItemWith(ModEnchantments.VOLT_SURGE.get(), pUser);
        if (ThornsEnchantment.shouldHit(pLevel, randomsource)) {
            LightningBolt bolt = new LightningBolt(EntityType.LIGHTNING_BOLT, pAttacker.level());
            bolt.setPos(pAttacker.getPosition(0));
            bolt.setVisualOnly(true);
            pAttacker.level().addFreshEntity(bolt);
            pAttacker.hurt(pUser.damageSources().thorns(pUser), (float) ThornsEnchantment.getDamage(pLevel, randomsource) * 1.5f);

            if (entry != null) {
                entry.getValue().hurtAndBreak(2, pUser, (p_45208_) -> p_45208_.broadcastBreakEvent(entry.getKey()));
            }
        }

    }

    @Override
    public boolean isTreasureOnly() {
        return true;
    }
}
