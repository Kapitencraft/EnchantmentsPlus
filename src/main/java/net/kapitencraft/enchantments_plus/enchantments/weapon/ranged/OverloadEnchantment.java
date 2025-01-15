package net.kapitencraft.enchantments_plus.enchantments.weapon.ranged;

import com.google.common.collect.Multimap;
import net.kapitencraft.kap_lib.enchantments.abstracts.ModBowEnchantment;
import net.kapitencraft.kap_lib.enchantments.abstracts.ModEnchantment;
import net.kapitencraft.kap_lib.enchantments.abstracts.StatBoostEnchantment;
import net.kapitencraft.kap_lib.helpers.AttributeHelper;
import net.kapitencraft.kap_lib.helpers.MiscHelper;
import net.kapitencraft.kap_lib.registry.ExtraAttributes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

public class OverloadEnchantment extends Enchantment implements StatBoostEnchantment, ModBowEnchantment, ModEnchantment {
    public OverloadEnchantment() {
        super(Rarity.UNCOMMON, EnchantmentCategory.BOW, MiscHelper.WEAPON_SLOT);
    }


    @Override
    public List<EquipmentSlot> slots() {
        return List.of();
    }

    @Override
    public Consumer<Multimap<Attribute, AttributeModifier>> getModifiers(int level, ItemStack enchanted, EquipmentSlot slot) {
        return multimap -> {
            multimap.put(ExtraAttributes.DRAW_SPEED.get(), AttributeHelper.createModifier("Overload Enchantment", AttributeModifier.Operation.ADDITION, -2 * level));
            multimap.put(ExtraAttributes.CRIT_DAMAGE.get(), AttributeHelper.createModifier("Overload Enchantment", AttributeModifier.Operation.ADDITION, level));
        };
    }

    @Override
    public boolean hasModifiersForThatSlot(EquipmentSlot slot, ItemStack stack) {
        return slot == EquipmentSlot.MAINHAND;
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public String[] getDescriptionMods(int level) {
        return new String[]{"" + level * 2, "+" + level, "10%", level * 10 + "%"};
    }

    @Override
    public CompoundTag write(CompoundTag tag, int level, ItemStack bow, LivingEntity owner, AbstractArrow arrow) {
        return tag;
    }

    @Override
    public float execute(int level, @Nullable LivingEntity target, CompoundTag tag, ExePhase type, float oldDamage, AbstractArrow arrow) {
        return oldDamage;
    }

    @Override
    public boolean shouldTick() {
        return false;
    }
}