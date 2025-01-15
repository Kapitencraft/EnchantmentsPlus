package net.kapitencraft.enchantments_plus.enchantments.weapon.ranged;

import com.google.common.collect.Multimap;
import net.kapitencraft.kap_lib.client.particle.animation.activation_triggers.EntityAddedTrigger;
import net.kapitencraft.kap_lib.client.particle.animation.core.ParticleAnimation;
import net.kapitencraft.kap_lib.client.particle.animation.finalizers.SetLifeTimeFinalizer;
import net.kapitencraft.kap_lib.client.particle.animation.spawners.RingSpawner;
import net.kapitencraft.kap_lib.client.particle.animation.terminators.EntityRemovedTerminator;
import net.kapitencraft.kap_lib.client.particle.animation.util.pos_target.PositionTarget;
import net.kapitencraft.kap_lib.enchantments.abstracts.IUltimateEnchantment;
import net.kapitencraft.kap_lib.enchantments.abstracts.IWeaponEnchantment;
import net.kapitencraft.kap_lib.enchantments.abstracts.ModBowEnchantment;
import net.kapitencraft.kap_lib.enchantments.abstracts.StatBoostEnchantment;
import net.kapitencraft.kap_lib.helpers.AttributeHelper;
import net.kapitencraft.kap_lib.registry.ExtraAttributes;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

public class WindBlessingEnchantment extends Enchantment implements StatBoostEnchantment, IUltimateEnchantment, ModBowEnchantment, IWeaponEnchantment {

    public WindBlessingEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentCategory.BOW, DEFAULT_SLOT);
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public List<EquipmentSlot> slots() {
        return List.of(DEFAULT_SLOT);
    }

    @Override
    public Consumer<Multimap<Attribute, AttributeModifier>> getModifiers(int level, ItemStack enchanted, EquipmentSlot slot) {
        return multimap -> multimap.put(ExtraAttributes.PROJECTILE_SPEED.get(), AttributeHelper.createModifier("WindBlessing", AttributeModifier.Operation.ADDITION, 10*level));
    }

    @Override
    public CompoundTag write(CompoundTag tag, int level, ItemStack bow, LivingEntity owner, AbstractArrow arrow) {
        arrow.setNoGravity(true);
        if (owner.level() instanceof ServerLevel sL) {
            ParticleAnimation.builder()
                    .terminatedWhen(EntityRemovedTerminator.builder(arrow))
                    .spawn(RingSpawner.noHeight()
                            .rotPerTick(10)
                            .setParticle(new DustParticleOptions(Vec3.fromRGB24(0xFFFFFF).toVector3f(), .7f))
                            .setTarget(PositionTarget.entity(arrow))
                            .axis(Direction.Axis.Z)
                            .spawnCount(2)
                            .radius(.2f)
                    )
                    .finalizes(SetLifeTimeFinalizer.builder().lifeTime(40).resetAge())
                    .spawnTime(ParticleAnimation.SpawnTime.absolute(1))
                    .activatedOn(EntityAddedTrigger.forEntity(arrow))
                    .sendToAllPlayers(sL);
        }
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

    @Override
    public Object[] getDescriptionMods(int level) {
        return new Object[] {level * 10 + "%"};
    }
}
