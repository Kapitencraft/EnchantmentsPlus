package net.kapitencraft.enchantments_plus.data_gen;

import io.netty.util.Recycler;
import net.kapitencraft.enchantments_plus.EnchantmentsPlusMod;
import net.kapitencraft.enchantments_plus.ModTags;
import net.kapitencraft.enchantments_plus.enchantments.components.Endurance;
import net.kapitencraft.enchantments_plus.enchantments.components.ProtectiveCover;
import net.kapitencraft.enchantments_plus.enchantments.components.Transylvanian;
import net.kapitencraft.enchantments_plus.registry.ModBlocks;
import net.kapitencraft.enchantments_plus.registry.ModEnchantmentEffectComponents;
import net.kapitencraft.enchantments_plus.registry.ModEnchantmentEntityEffects;
import net.kapitencraft.kap_lib.helpers.EnchantmentHelper;
import net.kapitencraft.kap_lib.registry.ExtraAttributes;
import net.kapitencraft.kap_lib.tags.ExtraTags;
import net.minecraft.advancements.critereon.DamageSourcePredicate;
import net.minecraft.advancements.critereon.EntityFlagsPredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.TagPredicate;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Unit;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.*;
import net.minecraft.world.item.enchantment.effects.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.DamageSourceCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.EnchantmentLevelProvider;
import net.neoforged.neoforge.common.Tags;
import org.checkerframework.checker.units.qual.A;

import java.util.Optional;

public interface ModEnchantments {
    ResourceKey<Enchantment> BASALT_WALKER = key("basalt_walker");
    ResourceKey<Enchantment> BLOCK_BREATHER = key("block_breather");
    ResourceKey<Enchantment> KANGAROO = key("kangaroo");
    ResourceKey<Enchantment> ENDER_FRIEND = key("ender_friend");
    ResourceKey<Enchantment> ENDURANCE = key("endurance");
    ResourceKey<Enchantment> ENLIGHTENMENT = key("enlightenment");
    ResourceKey<Enchantment> EXTINGUISH = key("extinguish");
    ResourceKey<Enchantment> FIRM_STAND = key("firm_stand");
    ResourceKey<Enchantment> GLUTTONOUS = key("gluttonous");
    ResourceKey<Enchantment> GROWTH = key("growth");
    ResourceKey<Enchantment> HEAVY_PLATING = key("heavy_plating");
    ResourceKey<Enchantment> INSOMNIA = key("insomnia");
    ResourceKey<Enchantment> LONG_LEGS = key("long_legs");

    private static ResourceKey<Enchantment> key(String name) {
        return ResourceKey.create(Registries.ENCHANTMENT, EnchantmentsPlusMod.res(name));
    }

    static void bootstrap(BootstrapContext<Enchantment> context) {
        HolderGetter<DamageType> damageTypes = context.lookup(Registries.DAMAGE_TYPE);
        HolderGetter<Enchantment> enchantments = context.lookup(Registries.ENCHANTMENT);
        HolderGetter<Item> items = context.lookup(Registries.ITEM);
        HolderGetter<Block> blocks = context.lookup(Registries.BLOCK);

        //region armor
        register(context, BASALT_WALKER, Enchantment.enchantment(
                Enchantment.definition(
                        items.getOrThrow(ItemTags.FOOT_ARMOR_ENCHANTABLE),
                        2,
                        3,
                        Enchantment.dynamicCost(10, 10),
                        Enchantment.dynamicCost(25, 10),
                        4,
                        EquipmentSlotGroup.FEET
                ))
                .withEffect(
                        EnchantmentEffectComponents.LOCATION_CHANGED,
                        new ReplaceDisk(
                                new LevelBasedValue.Clamped(LevelBasedValue.perLevel(2, 1), 0, 16),
                                LevelBasedValue.constant(1),
                                new Vec3i(0, -1, 0),
                                Optional.of(
                                        BlockPredicate.allOf(
                                                BlockPredicate.matchesTag(new Vec3i(0, 1, 0), BlockTags.AIR),
                                                BlockPredicate.matchesBlocks(Blocks.LAVA),
                                                BlockPredicate.matchesFluids(Fluids.LAVA),
                                                BlockPredicate.unobstructed()
                                        )
                                ),
                                BlockStateProvider.simple(ModBlocks.FRAGILE_BASALT.get()),
                                Optional.of(GameEvent.BLOCK_PLACE)
                        ),
                        LootItemEntityPropertyCondition.hasProperties(
                                LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnGround(true))
                        )
                )
        );
        register(context, BLOCK_BREATHER, Enchantment.enchantment(
                Enchantment.definition(
                        items.getOrThrow(ItemTags.HEAD_ARMOR_ENCHANTABLE),
                        2,
                        1,
                        Enchantment.constantCost(1),
                        Enchantment.constantCost(41),
                        4,
                        EquipmentSlotGroup.HEAD
                )
        ).withEffect(ModEnchantmentEffectComponents.BREATH_BLOCK.get()));
        register(context, KANGAROO, Enchantment.enchantment(
                Enchantment.definition(
                        items.getOrThrow(ItemTags.FOOT_ARMOR_ENCHANTABLE),
                        2,
                        3,
                        Enchantment.dynamicCost(10, 10),
                        Enchantment.dynamicCost(17, 8),
                        4,
                        EquipmentSlotGroup.FEET
                )
        ).withEffect(EnchantmentEffectComponents.ATTRIBUTES,
                new EnchantmentAttributeEffect(EnchantmentsPlusMod.res("kangaroo_enchantment"), ExtraAttributes.DOUBLE_JUMP, LevelBasedValue.perLevel(1), AttributeModifier.Operation.ADD_VALUE)
        ));
        register(context, ENDER_FRIEND, Enchantment.enchantment(
                Enchantment.definition(
                        items.getOrThrow(ItemTags.HEAD_ARMOR_ENCHANTABLE),
                        1,
                        1,
                        Enchantment.constantCost(3),
                        Enchantment.constantCost(46),
                        4,
                        EquipmentSlotGroup.HEAD
                )
        ).withEffect(ModEnchantmentEffectComponents.ENDER_FRIEND.get()));
        register(context, ENDURANCE, Enchantment.enchantment(
                Enchantment.definition(
                        items.getOrThrow(ItemTags.ARMOR_ENCHANTABLE),
                        1,
                        3,
                        Enchantment.dynamicCost(15, 8),
                        Enchantment.dynamicCost(20, 10),
                        6,
                        EquipmentSlotGroup.ARMOR
                )
        ).withEffect(EnchantmentEffectComponents.TICK, new Endurance()));
        register(context, ENLIGHTENMENT, Enchantment.enchantment(
                Enchantment.definition(
                        items.getOrThrow(ItemTags.HEAD_ARMOR_ENCHANTABLE),
                        1,
                        3,
                        Enchantment.dynamicCost(8, 10),
                        Enchantment.dynamicCost(10, 12),
                        3,
                        EquipmentSlotGroup.HEAD
                )
        ).withEffect(ModEnchantmentEffectComponents.ENLIGHTENMENT.get()));
        register(context, EXTINGUISH, Enchantment.enchantment(
                Enchantment.definition(
                        items.getOrThrow(ItemTags.CHEST_ARMOR_ENCHANTABLE),
                        4,
                        3,
                        Enchantment.dynamicCost(10, 10),
                        Enchantment.dynamicCost(15, 8),
                        2,
                        EquipmentSlotGroup.CHEST
                )
        ).withEffect(EnchantmentEffectComponents.ATTRIBUTES, new EnchantmentAttributeEffect(
                EnchantmentsPlusMod.res("extinguish_enchantment"), Attributes.BURNING_TIME, LevelBasedValue.perLevel(-.2f), AttributeModifier.Operation.ADD_VALUE
        )));
        register(context, FIRM_STAND, Enchantment.enchantment(
                Enchantment.definition(
                        items.getOrThrow(ModTags.Items.MOVEMENT_ARMOR_ENCHANTABLE),
                        10,
                        5,
                        Enchantment.dynamicCost(8, 10),
                        Enchantment.dynamicCost(10, 15),
                        5,
                        EquipmentSlotGroup.FEET, EquipmentSlotGroup.LEGS
                )
        ).withEffect(EnchantmentEffectComponents.ATTRIBUTES, new EnchantmentAttributeEffect(
                EnchantmentsPlusMod.res("firm_stand_enchantment"), Attributes.KNOCKBACK_RESISTANCE, LevelBasedValue.perLevel(.04f), AttributeModifier.Operation.ADD_VALUE
        )));
        register(context, GLUTTONOUS, EnchantmentHelper.ultimate(
                enchantments,
                Enchantment.definition(
                        items.getOrThrow(ItemTags.HEAD_ARMOR_ENCHANTABLE),
                        1,
                        5,
                        Enchantment.dynamicCost(10, 10),
                        Enchantment.dynamicCost(15, 12),
                        8,
                        EquipmentSlotGroup.HEAD
                )
        )); //TODO add effect?
        register(context, GROWTH, Enchantment.enchantment(
                Enchantment.definition(
                        items.getOrThrow(ItemTags.ARMOR_ENCHANTABLE),
                        1,
                        3,
                        Enchantment.dynamicCost(15, 12),
                        Enchantment.dynamicCost(20, 10),
                        7,
                        EquipmentSlotGroup.ARMOR
                )
        ).withEffect(EnchantmentEffectComponents.ATTRIBUTES, new EnchantmentAttributeEffect(
                EnchantmentsPlusMod.res("growth_enchantment"), Attributes.MAX_HEALTH, LevelBasedValue.perLevel(1), AttributeModifier.Operation.ADD_VALUE
        )));
        register(context, HEAVY_PLATING, EnchantmentHelper.ultimate(
                enchantments,
                Enchantment.definition(
                        items.getOrThrow(ItemTags.ARMOR_ENCHANTABLE),
                        1,
                        5,
                        Enchantment.dynamicCost(8, 10),
                        Enchantment.dynamicCost(10, 10),
                        8,
                        EquipmentSlotGroup.ARMOR
                )
        ).withEffect(EnchantmentEffectComponents.ATTRIBUTES, new EnchantmentAttributeEffect(
                EnchantmentsPlusMod.res("heavy_plating_enchantment"), Attributes.MOVEMENT_SPEED, LevelBasedValue.perLevel(-.01f), AttributeModifier.Operation.ADD_MULTIPLIED_BASE
        )).withEffect(EnchantmentEffectComponents.ATTRIBUTES, new EnchantmentAttributeEffect(
                EnchantmentsPlusMod.res("heavy_plating_enchantment"), Attributes.ARMOR, LevelBasedValue.perLevel(2), AttributeModifier.Operation.ADD_VALUE
        )));
        register(context, INSOMNIA, Enchantment.enchantment(
                Enchantment.definition(
                        items.getOrThrow(ItemTags.HEAD_ARMOR_ENCHANTABLE),
                        1,
                        1,
                        Enchantment.dynamicCost(10, 10),
                        Enchantment.dynamicCost(12, 12),
                        10,
                        EquipmentSlotGroup.HEAD
                )
        ).withEffect(ModEnchantmentEffectComponents.INSOMNIA.get()));
        register(context, LONG_LEGS, Enchantment.enchantment(
                Enchantment.definition(
                        items.getOrThrow(ItemTags.LEG_ARMOR_ENCHANTABLE),
                        1,
                        5,
                        Enchantment.dynamicCost(6, 10),
                        Enchantment.dynamicCost(8, 12),
                        10,
                        EquipmentSlotGroup.LEGS
                )
        ).withEffect(EnchantmentEffectComponents.ATTRIBUTES, new EnchantmentAttributeEffect(
                EnchantmentsPlusMod.res("long_legs_enchantment"), Attributes.STEP_HEIGHT, LevelBasedValue.perLevel(.2f), AttributeModifier.Operation.ADD_VALUE
        )));
        register(context, MAGIC_PROTECTION, Enchantment.enchantment(
                Enchantment.definition(
                        items.getOrThrow(ItemTags.ARMOR_ENCHANTABLE),
                        5,
                        4,
                        Enchantment.dynamicCost(1, 10),
                        Enchantment.dynamicCost(6, 10),
                        6,
                        EquipmentSlotGroup.ARMOR
                )
        ).withEffect(EnchantmentEffectComponents.DAMAGE_PROTECTION,
                new AddValue(LevelBasedValue.perLevel(2)),
                DamageSourceCondition.hasDamageSource(
                        DamageSourcePredicate.Builder.damageType()
                                .tag(TagPredicate.isNot(DamageTypeTags.BYPASSES_INVULNERABILITY))
                                .tag(TagPredicate.is(ExtraTags.DamageTypes.MAGIC))
                )
        ));
        register(context, PROTECTIVE_COVER, Enchantment.enchantment(
                Enchantment.definition(
                        items.getOrThrow(ItemTags.CHEST_ARMOR_ENCHANTABLE),
                        5,
                        3,
                        Enchantment.dynamicCost(1, 10),
                        Enchantment.dynamicCost(6, 10),
                        5,
                        EquipmentSlotGroup.CHEST
                )
        ).withEffect(EnchantmentEffectComponents.TICK, new ProtectiveCover()));
        register(context, REJUVENATE, Enchantment.enchantment(
                Enchantment.definition(
                        items.getOrThrow(ItemTags.ARMOR_ENCHANTABLE),
                        5,
                        10,
                        Enchantment.dynamicCost(5, 8),
                        Enchantment.dynamicCost(10, 10),
                        4,
                        EquipmentSlotGroup.ARMOR
                )
        ).withEffect(EnchantmentEffectComponents.ATTRIBUTES, new EnchantmentAttributeEffect(
                EnchantmentsPlusMod.res("rejuvenate_enchantment"), ExtraAttributes.VITALITY, LevelBasedValue.perLevel(2), AttributeModifier.Operation.ADD_VALUE
        )));
        register(context, RESILIENCE, Enchantment.enchantment(
                Enchantment.definition(
                        items.getOrThrow(ItemTags.CHEST_ARMOR_ENCHANTABLE),
                        2,
                        3,
                        Enchantment.dynamicCost(1, 5),
                        Enchantment.dynamicCost(6, 8),
                        8,
                        EquipmentSlotGroup.CHEST
                )
        )); //TODO add effect?
        register(context, SLEEPY, Enchantment.enchantment(
                Enchantment.definition(
                        items.getOrThrow(ItemTags.HEAD_ARMOR_ENCHANTABLE),
                        5,
                        1,
                        Enchantment.constantCost(12),
                        Enchantment.constantCost(18),
                        4,
                        EquipmentSlotGroup.HEAD
                )
        ).withEffect(ModEnchantmentEffectComponents.SLEEPY.get()));
        register(context, THORNY, Enchantment.enchantment(
                Enchantment.definition(
                        items.getOrThrow(ModTags.Items.SHIELD_ENCHANTABLE),
                        2,
                        5,
                        Enchantment.dynamicCost(1, 10),
                        Enchantment.dynamicCost(6, 10),
                        5,
                        EquipmentSlotGroup.HAND
                )
        ).withEffect(
                EnchantmentEffectComponents.POST_ATTACK,
                EnchantmentTarget.VICTIM,
                EnchantmentTarget.ATTACKER,
                AllOf.entityEffects(
                        new DamageEntity(LevelBasedValue.constant(1.0F), LevelBasedValue.constant(5.0F), damageTypes.getOrThrow(DamageTypes.THORNS)),
                        new DamageItem(LevelBasedValue.constant(2.0F))
                ),
                LootItemRandomChanceCondition.randomChance(EnchantmentLevelProvider.forEnchantmentLevel(LevelBasedValue.perLevel(0.15F)))
        ));
        register(context, TRANSYLVANIAN, Enchantment.enchantment(
                Enchantment.definition(
                        items.getOrThrow(ItemTags.ARMOR_ENCHANTABLE),
                        1,
                        2,
                        Enchantment.dynamicCost(1, 10),
                        Enchantment.dynamicCost(6, 10),
                        6,
                        EquipmentSlotGroup.ARMOR
                )
        ).withEffect(EnchantmentEffectComponents.TICK, new Transylvanian()));
        register(context, TRUE_PROTECTION, Enchantment.enchantment(
                Enchantment.definition(
                        items.getOrThrow(ItemTags.ARMOR_ENCHANTABLE),
                        2,
                        4,
                        Enchantment.dynamicCost(1, 10),
                        Enchantment.dynamicCost(6, 10),
                        5,
                        EquipmentSlotGroup.ARMOR
                )
        ).withEffect(EnchantmentEffectComponents.DAMAGE_PROTECTION,
                new AddValue(LevelBasedValue.perLevel(1)),
                DamageSourceCondition.hasDamageSource(
                        DamageSourcePredicate.Builder.damageType()
                                .tag(TagPredicate.isNot(DamageTypeTags.BYPASSES_INVULNERABILITY))
                                .tag(TagPredicate.is(DamageTypeTags.BYPASSES_ARMOR))
                )
        ));
        register(context, VOLT_SURGE, Enchantment.enchantment(
                Enchantment.definition(
                        items.getOrThrow(ItemTags.ARMOR_ENCHANTABLE),
                        1,
                        2,
                        Enchantment.dynamicCost(1, 10),
                        Enchantment.dynamicCost(6, 10),
                        5,
                        EquipmentSlotGroup.ARMOR
                )
        ).withEffect(EnchantmentEffectComponents.POST_ATTACK,
                        EnchantmentTarget.VICTIM, EnchantmentTarget.ATTACKER,
                        AllOf.entityEffects(
                                new SummonEntityEffect(HolderSet.direct(EntityType.LIGHTNING_BOLT.builtInRegistryHolder()), false),
                                new DamageEntity(LevelBasedValue.constant(1.5F), LevelBasedValue.constant(7.5F), damageTypes.getOrThrow(DamageTypes.THORNS)),
                                new DamageItem(LevelBasedValue.constant(2.0F))
                        ),
                        LootItemRandomChanceCondition.randomChance(EnchantmentLevelProvider.forEnchantmentLevel(LevelBasedValue.perLevel(0.15F)))
                )
        );
        //endregion
        //region tools
        register(context, CHROMATIC, Enchantment.enchantment(
                Enchantment.definition(
                        items.getOrThrow(Tags.Items.TOOLS_SHEAR),
                        1,
                        1,
                        Enchantment.constantCost(11),
                        Enchantment.constantCost(15),
                        2,
                        EquipmentSlotGroup.MAINHAND
                )
        ).withEffect(ModEnchantmentEffectComponents.CHROMATIC.get()));
        register(context, DELICATE, Enchantment.enchantment(
                Enchantment.definition(
                        items.getOrThrow(Tags.Items.),
                        2,
                        1,
                        Enchantment.constantCost(12),
                        Enchantment.constantCost(16),
                        6,
                        EquipmentSlotGroup.MAINHAND
                )
        ).withEffect(ModEnchantmentEffectComponents.DELICATE.get()));
        register(context, FLASH, Enchantment.enchantment(
                Enchantment.definition(
                        items.getOrThrow(ItemTags.FISHING_ENCHANTABLE),
                        2,
                        3,
                        Enchantment.dynamicCost(1, 10),
                        Enchantment.dynamicCost(5, 10),
                        5,
                        EquipmentSlotGroup.MAINHAND
                )
        )); //TODO add effect?
        register(context, LUMBERJACK, Enchantment.enchantment(
                Enchantment.definition(
                        items.getOrThrow(ItemTags.AXES),
                        1,
                        1,
                        Enchantment.dynamicCost(5, 10),
                        Enchantment.dynamicCost(6, 11),
                        4,
                        EquipmentSlotGroup.MAINHAND
                )
        )); //TODO add effect?
        register(context, );



        register(context, PRECISION, Enchantment.enchantment(
                Enchantment.definition(
                        items.getOrThrow(ItemTags.BOW_ENCHANTABLE),
                        5,
                        4,
                        Enchantment.dynamicCost(1, 10),
                        Enchantment.dynamicCost(6, 10),
                        5,
                        EquipmentSlotGroup.MAINHAND
                )
        ));
    }

    private static void register(BootstrapContext<Enchantment> context, ResourceKey<Enchantment> key, Enchantment.Builder builder) {
        context.register(key, builder.build(key.location()));
    }
}
