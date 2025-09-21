package net.kapitencraft.enchantments_plus.enchantments.components;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.kapitencraft.kap_lib.util.attribute.TimedModifierUtils;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

public record ApplyTimedModifier(ResourceLocation location, LevelBasedValue duration, Holder<Attribute> attribute, LevelBasedValue amount, AttributeModifier.Operation operation) implements EnchantmentEntityEffect {
    public static final MapCodec<ApplyTimedModifier> CODEC = RecordCodecBuilder.mapCodec(i -> i.group(
            ResourceLocation.CODEC.fieldOf("location").forGetter(ApplyTimedModifier::location),
            LevelBasedValue.CODEC.fieldOf("duration").forGetter(ApplyTimedModifier::duration),
            Attribute.CODEC.fieldOf("attribute").forGetter(ApplyTimedModifier::attribute),
            LevelBasedValue.CODEC.fieldOf("amount").forGetter(ApplyTimedModifier::amount),
            AttributeModifier.Operation.CODEC.fieldOf("operation").forGetter(ApplyTimedModifier::operation)
    ).apply(i, ApplyTimedModifier::new));

    @Override
    public void apply(ServerLevel level, int enchantmentLevel, EnchantedItemInUse item, Entity entity, Vec3 origin) {
        if (entity instanceof LivingEntity living) {
            TimedModifierUtils.add(living, location, ((int) duration.calculate(enchantmentLevel)), attribute, amount.calculate(enchantmentLevel), operation);
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
