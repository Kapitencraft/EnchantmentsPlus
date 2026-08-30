package net.kapitencraft.enchantments_plus.data_gen;

import net.kapitencraft.enchantments_plus.EnchantmentsPlusMod;
import net.kapitencraft.enchantments_plus.ModTags;
import net.kapitencraft.enchantments_plus.enchantments.components.bow.Aim;
import net.kapitencraft.enchantments_plus.enchantments.components.bow.Snipe;
import net.kapitencraft.enchantments_plus.enchantments.components.count.FirstStrike;
import net.kapitencraft.enchantments_plus.enchantments.components.count.Inferno;
import net.kapitencraft.enchantments_plus.enchantments.components.count.LightningLord;
import net.kapitencraft.enchantments_plus.enchantments.components.count.TripleStrike;
import net.kapitencraft.enchantments_plus.enchantments.components.entity.*;
import net.kapitencraft.enchantments_plus.registry.ModBlocks;
import net.kapitencraft.enchantments_plus.registry.ModCooldowns;
import net.kapitencraft.enchantments_plus.registry.ModEnchantmentEffectComponents;
import net.kapitencraft.kap_lib.attribute.ExtraAttributes;
import net.kapitencraft.kap_lib.cooldown.loot.CooldownInactiveCondition;
import net.kapitencraft.kap_lib.core.helpers.EnchantmentHelperExtras;
import net.kapitencraft.kap_lib.core.tags.ExtraTags;
import net.kapitencraft.kap_lib.enchantment.ExtraEnchantmentEffectComponents;
import net.kapitencraft.kap_lib.loot.conditions.AttackerEmptyOffhandCondition;
import net.kapitencraft.kap_lib.loot.conditions.BehindEntityCondition;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.*;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentTarget;
import net.minecraft.world.item.enchantment.LevelBasedValue;
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

import java.util.Optional;

public interface ModEnchantments {
    //region armor
    ResourceKey<Enchantment> BASALT_WALKER = key("basalt_walker");
    ResourceKey<Enchantment> BLOCK_BREATHER = key("block_breather");
    ResourceKey<Enchantment> BONK = key("bonk");
    ResourceKey<Enchantment> ENDER_FRIEND = key("ender_friend");
    ResourceKey<Enchantment> ENDURANCE = key("endurance");
    ResourceKey<Enchantment> ENLIGHTENMENT = key("enlightenment");
    ResourceKey<Enchantment> EXTINGUISH = key("extinguish");
    ResourceKey<Enchantment> FIRM_STAND = key("firm_stand");
    ResourceKey<Enchantment> GLUTTONOUS = key("gluttonous");
    ResourceKey<Enchantment> GROWTH = key("growth");
    ResourceKey<Enchantment> HEAVY_PLATING = key("heavy_plating");
    ResourceKey<Enchantment> INSOMNIA = key("insomnia");
    ResourceKey<Enchantment> KANGAROO = key("kangaroo");
    ResourceKey<Enchantment> LONG_LEGS = key("long_legs");
    ResourceKey<Enchantment> MAGIC_PROTECTION = key("magic_protection");
    ResourceKey<Enchantment> MOMENTUM_SHIFT = key("momentum_shift");
    ResourceKey<Enchantment> PROTECTIVE_COVER = key("protective_cover");
    ResourceKey<Enchantment> REJUVENATE = key("rejuvenate");
    ResourceKey<Enchantment> RESILIENCE = key("resilience");
    ResourceKey<Enchantment> SLEEPY = key("sleepy");
    ResourceKey<Enchantment> TRANSYLVANIAN = key("transylvanian");
    ResourceKey<Enchantment> TRUE_PROTECTION = key("true_protection");
    //endregion
    //region shield
    ResourceKey<Enchantment> FORTRESS = key("fortress");
    ResourceKey<Enchantment> THORNY = key("thorny");
    //endregion
    //region tool
    ResourceKey<Enchantment> CHROMATIC = key("chromatic");
    ResourceKey<Enchantment> DELICATE = key("delicate");
    ResourceKey<Enchantment> FLASH = key("flash");
    ResourceKey<Enchantment> LUMBERJACK = key("lumberjack");
    ResourceKey<Enchantment> MAGMATIC = key("magmatic");
    ResourceKey<Enchantment> REPLENISH = key("replenish");
    ResourceKey<Enchantment> SILENT_HARVEST = key("silent_harvest");
    ResourceKey<Enchantment> SMELTING_TOUCH = key("smelting_touch");
    ResourceKey<Enchantment> VEIN_MINER = key("vein_miner");
    //endregion
    //region melee
    ResourceKey<Enchantment> ARMOR_SHREDDING = key("armor_shredding");
    ResourceKey<Enchantment> BACK_STAB = key("back_stab");
    ResourceKey<Enchantment> BLOOD_THIRST = key("blood_thirst");
    ResourceKey<Enchantment> CHAIN_LIGHTNING = key("chain_lightning");
    ResourceKey<Enchantment> CHILLING = key("chilling");
    ResourceKey<Enchantment> COMBAT_KNOWLEDGE = key("combat_knowledge");
    ResourceKey<Enchantment> CUBISM = key("cubism");
    ResourceKey<Enchantment> ENDER_SLAYER = key("ender_slayer");
    ResourceKey<Enchantment> EXECUTE = key("execute");
    ResourceKey<Enchantment> FIRST_STRIKE = key("first_strike");
    ResourceKey<Enchantment> GIANT_KILLER = key("giant_killer");
    ResourceKey<Enchantment> JUSTICE = key("justice");
    ResourceKey<Enchantment> LIGHTNING_LORD = key("lightning_lord");
    ResourceKey<Enchantment> NECROTIC_TOUCH = key("necrotic_touch");
    ResourceKey<Enchantment> POISONOUS_BLADE = key("poisonous_blade");
    ResourceKey<Enchantment> PROSECUTE = key("prosecute");
    ResourceKey<Enchantment> STARVATION = key("starvation");
    ResourceKey<Enchantment> TRIPLE_STRIKE = key("triple_strike");
    ResourceKey<Enchantment> VENOMOUS = key("venomous");
    //endregion
    //region ranged
    ResourceKey<Enchantment> AIM = key("aim");
    ResourceKey<Enchantment> ELVISH_MASTERY = key("elvish_mastery");
    ResourceKey<Enchantment> FAST_ARROWS = key("fast_arrows");
    ResourceKey<Enchantment> FROZEN_ARROWS = key("frozen_arrows");
    ResourceKey<Enchantment> OVERLOAD = key("overload");
    ResourceKey<Enchantment> PRECISION = key("precision");
    ResourceKey<Enchantment> SNIPE = key("snipe");
    ResourceKey<Enchantment> WIND_BLESSING = key("wind_blessing");
    //endregion
    //region weapon
    ResourceKey<Enchantment> CRITICAl = key("critical");
    ResourceKey<Enchantment> DIVINE_GIFT = key("divine_gift");
    ResourceKey<Enchantment> INFERNO = key("inferno");
    ResourceKey<Enchantment> SCAVENGER = key("scavenger");
    ResourceKey<Enchantment> TWO_HANDED = key("two_handed");
    ResourceKey<Enchantment> FATAL_TEMPO = key("fatal_tempo");
    //endregion
    //region util
    ResourceKey<Enchantment> COMPACTING = key("compacting");
    ResourceKey<Enchantment> EXPERIENCED = key("experienced");
    ResourceKey<Enchantment> HEALTH_MENDING = key("health_mending");
    ResourceKey<Enchantment> TELEKINESIS = key("telekinesis");
    //endregion
    //region pet
    ResourceKey<Enchantment> VAMPIRISM = key("vampirism");
    //endregion pet

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
        register(context, BONK, EnchantmentHelperExtras.ultimate(
                enchantments,
                Enchantment.definition(
                        items.getOrThrow(ItemTags.ARMOR_ENCHANTABLE),
                        1,
                        1,
                        Enchantment.constantCost(10),
                        Enchantment.constantCost(20),
                        4,
                        EquipmentSlotGroup.ARMOR
                )
        ).withEffect(EnchantmentEffectComponents.DAMAGE_IMMUNITY,
                DamageImmunity.INSTANCE,
                () -> new CooldownInactiveCondition(ModCooldowns.BONK.value())
        ).withEffect(EnchantmentEffectComponents.POST_ATTACK, EnchantmentTarget.VICTIM, EnchantmentTarget.VICTIM, new ActivateCooldown(ModCooldowns.BONK.value(), true)));
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
        ));
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
        register(context, GLUTTONOUS, EnchantmentHelperExtras.ultimate(
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
        ));
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
        register(context, HEAVY_PLATING, EnchantmentHelperExtras.ultimate(
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
        ));
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
        register(context, MOMENTUM_SHIFT, Enchantment.enchantment(
                Enchantment.definition(
                        items.getOrThrow(ItemTags.LEG_ARMOR_ENCHANTABLE),
                        1,
                        5,
                        Enchantment.dynamicCost(2, 5),
                        Enchantment.dynamicCost(4, 5),
                        2,
                        EquipmentSlotGroup.LEGS
                )
        ).withEffect(EnchantmentEffectComponents.POST_ATTACK, EnchantmentTarget.VICTIM, EnchantmentTarget.VICTIM, new ApplyTimedModifier(EnchantmentsPlusMod.res("movement_shift"), LevelBasedValue.constant(100), Attributes.MOVEMENT_SPEED, LevelBasedValue.perLevel(.05f), AttributeModifier.Operation.ADD_MULTIPLIED_BASE)));
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
        ));
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
        //endregion
        //region shield
        register(context, FORTRESS,
                EnchantmentHelperExtras.ultimate(
                        enchantments,
                        Enchantment.definition(
                                items.getOrThrow(ModTags.Items.SHIELD_ENCHANTABLE),
                                1,
                                1,
                                Enchantment.constantCost(20),
                                Enchantment.constantCost(20),
                                10,
                                EquipmentSlotGroup.HAND
                        )
                )
        );
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
                        items.getOrThrow(ModTags.Items.FARMING_ENCHANTABLE),
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
        ));
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
        ).withEffect(ModEnchantmentEffectComponents.LUMBERJACK.get()));
        register(context, MAGMATIC, Enchantment.enchantment(
                Enchantment.definition(
                        items.getOrThrow(ItemTags.MINING_ENCHANTABLE),
                        2,
                        1,
                        Enchantment.constantCost(16),
                        Enchantment.constantCost(20),
                        8,
                        EquipmentSlotGroup.MAINHAND
                )
        ).withEffect(ModEnchantmentEffectComponents.MAGMATIC.get()));
        register(context, REPLENISH, Enchantment.enchantment(
                Enchantment.definition(
                        items.getOrThrow(ModTags.Items.FARMING_ENCHANTABLE),
                        1,
                        1,
                        Enchantment.constantCost(22),
                        Enchantment.constantCost(30),
                        4,
                        EquipmentSlotGroup.MAINHAND
                )
        ).withEffect(ModEnchantmentEffectComponents.REPLENISH.get()));
        register(context, SILENT_HARVEST, Enchantment.enchantment(
                Enchantment.definition(
                        items.getOrThrow(ItemTags.MINING_ENCHANTABLE),
                        1,
                        1,
                        Enchantment.constantCost(17),
                        Enchantment.constantCost(20),
                        6,
                        EquipmentSlotGroup.MAINHAND
                )
        ).withEffect(ModEnchantmentEffectComponents.SILENT_HARVEST.get()));
        register(context, SMELTING_TOUCH, Enchantment.enchantment(
                Enchantment.definition(
                        items.getOrThrow(ItemTags.MINING_LOOT_ENCHANTABLE),
                        1,
                        1,
                        Enchantment.constantCost(20),
                        Enchantment.constantCost(25),
                        8,
                        EquipmentSlotGroup.MAINHAND
                )
        ).exclusiveWith(enchantments.getOrThrow(ModTags.Enchantments.MINING_DROPS_EXCLUSIVE)));
        register(context, VEIN_MINER, Enchantment.enchantment(
                Enchantment.definition(
                        items.getOrThrow(ItemTags.MINING_ENCHANTABLE),
                        1,
                        5,
                        Enchantment.dynamicCost(5, 10),
                        Enchantment.dynamicCost(12, 10),
                        4,
                        EquipmentSlotGroup.MAINHAND
                )
        ));
        //endregion
        //region weapon/melee
        HolderSet.Named<Item> weaponEnchantable = items.getOrThrow(ItemTags.WEAPON_ENCHANTABLE);
        HolderSet<Item> swordEnchantable = items.getOrThrow(ItemTags.SWORD_ENCHANTABLE);
        register(context, BACK_STAB, Enchantment.enchantment(
                Enchantment.definition(
                        items.getOrThrow(ItemTags.SHARP_WEAPON_ENCHANTABLE),
                        weaponEnchantable,
                        1,
                        5,
                        Enchantment.dynamicCost(1, 10),
                        Enchantment.dynamicCost(6, 10),
                        6,
                        EquipmentSlotGroup.MAINHAND
                )
        ).withEffect(EnchantmentEffectComponents.DAMAGE,
                new MultiplyValue(LevelBasedValue.perLevel(1.25f, .25f)),
                BehindEntityCondition::new
        ));
        register(context, BLOOD_THIRST, Enchantment.enchantment(
                Enchantment.definition(
                        weaponEnchantable,
                        1,
                        5,
                        Enchantment.dynamicCost(1, 10),
                        Enchantment.dynamicCost(5, 10),
                        4,
                        EquipmentSlotGroup.MAINHAND
                )
        ).withEffect(EnchantmentEffectComponents.POST_ATTACK, EnchantmentTarget.ATTACKER, EnchantmentTarget.ATTACKER,
                new ApplyTimedModifier(EnchantmentsPlusMod.res("blood_thirst"), LevelBasedValue.constant(100), Attributes.ATTACK_DAMAGE, LevelBasedValue.perLevel(.01f), AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
        ));
        register(context, CHILLING, Enchantment.enchantment(
                Enchantment.definition(
                        weaponEnchantable,
                        2,
                        3,
                        Enchantment.dynamicCost(1, 10),
                        Enchantment.dynamicCost(5, 10),
                        6,
                        EquipmentSlotGroup.MAINHAND
                )
        ).withEffect(EnchantmentEffectComponents.POST_ATTACK, EnchantmentTarget.ATTACKER, EnchantmentTarget.VICTIM, new IncreaseFreeze(LevelBasedValue.perLevel(25))));
        register(context, COMBAT_KNOWLEDGE, Enchantment.enchantment(
                Enchantment.definition(
                        items.getOrThrow(ItemTags.SHARP_WEAPON_ENCHANTABLE),
                        weaponEnchantable,
                        1,
                        5,
                        Enchantment.dynamicCost(1, 10),
                        Enchantment.dynamicCost(5, 10),
                        4,
                        EquipmentSlotGroup.MAINHAND
                )
        ));
        register(context, CUBISM, damageEnchantment(weaponEnchantable, swordEnchantable, ModTags.EntityTypes.SENSITIVE_TO_CUBISM));
        register(context, ENDER_SLAYER, damageEnchantment(weaponEnchantable, swordEnchantable, ModTags.EntityTypes.SENSITIVE_TO_ENDER_SLAYER));
        register(context, FIRST_STRIKE, Enchantment.enchantment(
                Enchantment.definition(
                        weaponEnchantable,
                        5,
                        4,
                        Enchantment.dynamicCost(5, 20),
                        Enchantment.dynamicCost(10, 40),
                        4,
                        EquipmentSlotGroup.MAINHAND
                )
        ).exclusiveWith(enchantments.getOrThrow(ModTags.Enchantments.STRIKE_EXCLUSIVE)).withEffect(ExtraEnchantmentEffectComponents.COUNT.get(), EnchantmentTarget.ATTACKER, EnchantmentTarget.VICTIM, new FirstStrike()));
        register(context, GIANT_KILLER, Enchantment.enchantment(
                Enchantment.definition(
                        weaponEnchantable,
                        2,
                        5,
                        Enchantment.dynamicCost(1, 10),
                        Enchantment.dynamicCost(1, 10),
                        5,
                        EquipmentSlotGroup.MAINHAND
                )
        ));
        register(context, JUSTICE, damageEnchantment(weaponEnchantable, swordEnchantable, ModTags.EntityTypes.SENSITIVE_TO_JUSTICE));
        register(context, LIGHTNING_LORD, Enchantment.enchantment(
                Enchantment.definition(
                        weaponEnchantable,
                        2,
                        5,
                        Enchantment.dynamicCost(1, 10),
                        Enchantment.dynamicCost(5, 10),
                        4,
                        EquipmentSlotGroup.MAINHAND
                )
        ).withEffect(ExtraEnchantmentEffectComponents.COUNT.get(), EnchantmentTarget.ATTACKER, EnchantmentTarget.VICTIM, new LightningLord()));
        register(context, NECROTIC_TOUCH, Enchantment.enchantment(
                Enchantment.definition(
                        weaponEnchantable,
                        10,
                        3,
                        Enchantment.dynamicCost(1, 10),
                        Enchantment.dynamicCost(5, 10),
                        4,
                        EquipmentSlotGroup.MAINHAND
                )
        ).withEffect(EnchantmentEffectComponents.ATTRIBUTES, new EnchantmentAttributeEffect(
                EnchantmentsPlusMod.res("necrotic_touch_enchantment"), ExtraAttributes.LIFE_STEAL, LevelBasedValue.perLevel(1), AttributeModifier.Operation.ADD_VALUE
        )));
        register(context, POISONOUS_BLADE, Enchantment.enchantment(
                Enchantment.definition(
                        weaponEnchantable,
                        2,
                        3,
                        Enchantment.dynamicCost(1, 10),
                        Enchantment.dynamicCost(5, 10),
                        4,
                        EquipmentSlotGroup.MAINHAND
                )
        ).withEffect(EnchantmentEffectComponents.POST_ATTACK, EnchantmentTarget.ATTACKER, EnchantmentTarget.VICTIM, new ApplyMobEffect(
                HolderSet.direct(MobEffects.POISON),
                LevelBasedValue.perLevel(3),
                LevelBasedValue.perLevel(3),
                LevelBasedValue.constant(1),
                LevelBasedValue.constant(1)
        )));
        register(context, STARVATION, Enchantment.enchantment(
                Enchantment.definition(
                        weaponEnchantable,
                        4,
                        5,
                        Enchantment.dynamicCost(2, 5),
                        Enchantment.dynamicCost(4, 7),
                        2,
                        EquipmentSlotGroup.MAINHAND
                )
        ).withEffect(EnchantmentEffectComponents.POST_ATTACK, EnchantmentTarget.ATTACKER, EnchantmentTarget.VICTIM, new Starvation()));
        register(context, TRIPLE_STRIKE, Enchantment.enchantment(
                Enchantment.definition(
                        weaponEnchantable,
                        5,
                        4,
                        Enchantment.dynamicCost(5, 20),
                        Enchantment.dynamicCost(10, 40),
                        5,
                        EquipmentSlotGroup.MAINHAND
                )
        ).exclusiveWith(enchantments.getOrThrow(ModTags.Enchantments.STRIKE_EXCLUSIVE)).withEffect(ExtraEnchantmentEffectComponents.COUNT.get(), EnchantmentTarget.ATTACKER, EnchantmentTarget.VICTIM, new TripleStrike()));
        register(context, VENOMOUS, Enchantment.enchantment(
                Enchantment.definition(
                        weaponEnchantable,
                        1,
                        5,
                        Enchantment.dynamicCost(1, 10),
                        Enchantment.dynamicCost(5, 10),
                        4,
                        EquipmentSlotGroup.MAINHAND
                )
        ).withEffect(EnchantmentEffectComponents.POST_ATTACK, EnchantmentTarget.ATTACKER, EnchantmentTarget.VICTIM,
                new ApplyTimedModifier(EnchantmentsPlusMod.res("venomous_enchantment"), LevelBasedValue.perLevel(20), Attributes.MOVEMENT_SPEED, LevelBasedValue.perLevel(-0.05f), AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)));
        register(context, ARMOR_SHREDDING, Enchantment.enchantment(
                Enchantment.definition(
                        weaponEnchantable,
                        1,
                        7,
                        Enchantment.dynamicCost(1, 10),
                        Enchantment.dynamicCost(5, 10),
                        4,
                        EquipmentSlotGroup.MAINHAND
                )
        ).withEffect(EnchantmentEffectComponents.ATTRIBUTES, new EnchantmentAttributeEffect(
                EnchantmentsPlusMod.res("armor_shredding_enchantment"), ExtraAttributes.ARMOR_SHREDDER, LevelBasedValue.perLevel(1), AttributeModifier.Operation.ADD_VALUE
        )));
        register(context, CHAIN_LIGHTNING, Enchantment.enchantment(
                Enchantment.definition(
                        weaponEnchantable,
                        1,
                        5,
                        Enchantment.dynamicCost(5, 10),
                        Enchantment.dynamicCost(10, 10),
                        6,
                        EquipmentSlotGroup.MAINHAND
                )
        ));
        register(context, EXECUTE, Enchantment.enchantment(
                Enchantment.definition(
                        weaponEnchantable,
                        2,
                        5,
                        Enchantment.dynamicCost(4, 10),
                        Enchantment.dynamicCost(5, 11),
                        4,
                        EquipmentSlotGroup.MAINHAND
                )
        ).exclusiveWith(enchantments.getOrThrow(ModTags.Enchantments.EXECUTION_EXCLUSIVE)));
        register(context, PROSECUTE, Enchantment.enchantment(
                Enchantment.definition(
                        weaponEnchantable,
                        2,
                        5,
                        Enchantment.dynamicCost(4, 10),
                        Enchantment.dynamicCost(5, 11),
                        4,
                        EquipmentSlotGroup.MAINHAND
                )
        ).exclusiveWith(enchantments.getOrThrow(ModTags.Enchantments.EXECUTION_EXCLUSIVE)));
        //endregion
        //region weapon/ranged
        register(context, AIM, Enchantment.enchantment(
                Enchantment.definition(
                        items.getOrThrow(ItemTags.BOW_ENCHANTABLE),
                        1,
                        5,
                        Enchantment.dynamicCost(1, 10),
                        Enchantment.dynamicCost(5, 10),
                        4,
                        EquipmentSlotGroup.MAINHAND
                )
        ).withEffect(ExtraEnchantmentEffectComponents.BOW.get(), new Aim()));
        register(context, ELVISH_MASTERY, Enchantment.enchantment(
                Enchantment.definition(
                        items.getOrThrow(ItemTags.BOW_ENCHANTABLE),
                        2,
                        5,
                        Enchantment.dynamicCost(1, 10),
                        Enchantment.dynamicCost(5, 10),
                        4,
                        EquipmentSlotGroup.MAINHAND
                )
        ).withEffect(EnchantmentEffectComponents.ATTRIBUTES, new EnchantmentAttributeEffect(
                EnchantmentsPlusMod.res("elvish_mastery_enchantment"), ExtraAttributes.DRAW_SPEED, LevelBasedValue.perLevel(7.5f), AttributeModifier.Operation.ADD_VALUE
        )));
        register(context, FAST_ARROWS, Enchantment.enchantment( //TODO arrow desync
                Enchantment.definition(
                        items.getOrThrow(ItemTags.BOW_ENCHANTABLE),
                        2,
                        5,
                        Enchantment.dynamicCost(1, 10),
                        Enchantment.dynamicCost(5, 10),
                        4,
                        EquipmentSlotGroup.MAINHAND
                )
        ).withEffect(EnchantmentEffectComponents.ATTRIBUTES, new EnchantmentAttributeEffect(
                EnchantmentsPlusMod.res("fast_arrows_enchantment"), ExtraAttributes.PROJECTILE_SPEED, LevelBasedValue.perLevel(10), AttributeModifier.Operation.ADD_VALUE
        )));
        register(context, FROZEN_ARROWS, Enchantment.enchantment(
                Enchantment.definition(
                        items.getOrThrow(ItemTags.BOW_ENCHANTABLE),
                        1,
                        3,
                        Enchantment.dynamicCost(1, 10),
                        Enchantment.dynamicCost(5, 10),
                        4,
                        EquipmentSlotGroup.MAINHAND
                )
        ).withEffect(EnchantmentEffectComponents.POST_ATTACK, EnchantmentTarget.ATTACKER, EnchantmentTarget.VICTIM, new IncreaseFreeze(LevelBasedValue.perLevel(40))));
        register(context, OVERLOAD, Enchantment.enchantment(
                Enchantment.definition(
                        items.getOrThrow(ItemTags.BOW_ENCHANTABLE),
                        5,
                        5,
                        Enchantment.dynamicCost(1, 10),
                        Enchantment.dynamicCost(5, 10),
                        4,
                        EquipmentSlotGroup.MAINHAND
                )
        ).withEffect(EnchantmentEffectComponents.ATTRIBUTES, new EnchantmentAttributeEffect(
                EnchantmentsPlusMod.res("overload_enchantment"), ExtraAttributes.DRAW_SPEED, LevelBasedValue.perLevel(-2), AttributeModifier.Operation.ADD_VALUE
        )).withEffect(EnchantmentEffectComponents.ATTRIBUTES, new EnchantmentAttributeEffect(
                EnchantmentsPlusMod.res("overload_enchantment"), ExtraAttributes.CRIT_DAMAGE, LevelBasedValue.perLevel(1), AttributeModifier.Operation.ADD_VALUE
        )).withEffect(EnchantmentEffectComponents.DAMAGE,
                new MultiplyValue(LevelBasedValue.perLevel(1.1f, .1f)),
                LootItemRandomChanceCondition.randomChance(.1f)
        ));
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
        register(context, SNIPE, Enchantment.enchantment(
                Enchantment.definition(
                        items.getOrThrow(ItemTags.BOW_ENCHANTABLE),
                        2,
                        5,
                        Enchantment.dynamicCost(1, 10),
                        Enchantment.dynamicCost(5, 10),
                        4,
                        EquipmentSlotGroup.MAINHAND
                )
        ).withEffect(ExtraEnchantmentEffectComponents.BOW.get(), new Snipe()));
        register(context, WIND_BLESSING, Enchantment.enchantment(
                                Enchantment.definition(
                                        items.getOrThrow(ItemTags.BOW_ENCHANTABLE),
                                        1,
                                        3,
                                        Enchantment.dynamicCost(1, 10),
                                        Enchantment.dynamicCost(5, 10),
                                        2,
                                        EquipmentSlotGroup.MAINHAND
                                )
                        ).withEffect(ExtraEnchantmentEffectComponents.BOW_SPAWN.get(), new WindBlessing())
                        .withEffect(EnchantmentEffectComponents.ATTRIBUTES, new EnchantmentAttributeEffect(
                                EnchantmentsPlusMod.res("wind_blessing_enchantment"), ExtraAttributes.PROJECTILE_SPEED, LevelBasedValue.perLevel(10), AttributeModifier.Operation.ADD_VALUE
                        ))
        );
        //endregion
        //region weapon
        HolderSet.Named<Item> allWeapons = items.getOrThrow(ModTags.Items.ALL_WEAPONS_ENCHANTABLE);
        register(context, CRITICAl, Enchantment.enchantment(
                Enchantment.definition(
                        allWeapons,
                        10,
                        5,
                        Enchantment.dynamicCost(1, 5),
                        Enchantment.dynamicCost(5, 8),
                        2,
                        EquipmentSlotGroup.MAINHAND
                )
        ).withEffect(EnchantmentEffectComponents.ATTRIBUTES, new EnchantmentAttributeEffect(
                EnchantmentsPlusMod.res("critical_enchantment"), ExtraAttributes.CRIT_DAMAGE, LevelBasedValue.perLevel(10), AttributeModifier.Operation.ADD_VALUE
        )));
        register(context, DIVINE_GIFT, Enchantment.enchantment(
                Enchantment.definition(
                        allWeapons,
                        1,
                        3,
                        Enchantment.dynamicCost(10, 15),
                        Enchantment.dynamicCost(10, 20),
                        8,
                        EquipmentSlotGroup.MAINHAND
                )
        ).withEffect(EnchantmentEffectComponents.ATTRIBUTES, new EnchantmentAttributeEffect(
                EnchantmentsPlusMod.res("divine_gift_enchantment"), Attributes.LUCK, LevelBasedValue.perLevel(2), AttributeModifier.Operation.ADD_VALUE
        )));
        register(context, INFERNO, EnchantmentHelperExtras.ultimate(
                enchantments,
                Enchantment.definition(
                        weaponEnchantable,
                        1,
                        5,
                        Enchantment.dynamicCost(10, 10),
                        Enchantment.dynamicCost(12, 15),
                        4,
                        EquipmentSlotGroup.MAINHAND
                )
        ).withEffect(ExtraEnchantmentEffectComponents.COUNT.get(), EnchantmentTarget.ATTACKER, EnchantmentTarget.VICTIM, new Inferno()));
        register(context, SCAVENGER, Enchantment.enchantment(
                Enchantment.definition(
                        allWeapons,
                        2,
                        5,
                        Enchantment.dynamicCost(5, 12),
                        Enchantment.dynamicCost(2, 8),
                        6,
                        EquipmentSlotGroup.MAINHAND
                )
        ));
        register(context, TWO_HANDED, Enchantment.enchantment(
                Enchantment.definition(
                        allWeapons,
                        5,
                        3,
                        Enchantment.dynamicCost(1, 7),
                        Enchantment.dynamicCost(4, 8),
                        2,
                        EquipmentSlotGroup.MAINHAND
                )
        ).withEffect(EnchantmentEffectComponents.DAMAGE,
                new MultiplyValue(LevelBasedValue.perLevel(1.2f, .2f)),
                AttackerEmptyOffhandCondition::new
        ));
        register(context, FATAL_TEMPO, EnchantmentHelperExtras.ultimate(enchantments,
                        Enchantment.definition(
                                allWeapons,
                                1,
                                5,
                                Enchantment.dynamicCost(10, 10),
                                Enchantment.dynamicCost(10, 15),
                                8,
                                EquipmentSlotGroup.MAINHAND
                        )
                ).withEffect(EnchantmentEffectComponents.POST_ATTACK, EnchantmentTarget.ATTACKER, EnchantmentTarget.ATTACKER,
                        new ApplyTimedModifier(EnchantmentsPlusMod.res("fatal_tempo_enchantment"), LevelBasedValue.constant(60), ExtraAttributes.FEROCITY, LevelBasedValue.perLevel(10), AttributeModifier.Operation.ADD_VALUE))
        );
        //endregion
        //region misc
        register(context, COMPACTING, Enchantment.enchantment(
                Enchantment.definition(
                        items.getOrThrow(ItemTags.MINING_ENCHANTABLE),
                        weaponEnchantable,
                        5,
                        1,
                        Enchantment.constantCost(12),
                        Enchantment.constantCost(20),
                        4,
                        EquipmentSlotGroup.MAINHAND
                )
        ));
        register(context, EXPERIENCED, Enchantment.enchantment(
                Enchantment.definition(
                        items.getOrThrow(ModTags.Items.WEAPON_AND_MINING_ENCHANTABLE),
                        items.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                        2,
                        5,
                        Enchantment.dynamicCost(1, 10),
                        Enchantment.dynamicCost(5, 10),
                        4,
                        EquipmentSlotGroup.MAINHAND
                )
        ).withEffect(EnchantmentEffectComponents.ATTRIBUTES, new EnchantmentAttributeEffect(
                EnchantmentsPlusMod.res("experienced_enchantment"), ExtraAttributes.WISDOM, LevelBasedValue.perLevel(2), AttributeModifier.Operation.ADD_VALUE
        )));
        register(context, HEALTH_MENDING, Enchantment.enchantment(
                Enchantment.definition(
                        items.getOrThrow(ItemTags.DURABILITY_ENCHANTABLE),
                        1,
                        1,
                        Enchantment.constantCost(30),
                        Enchantment.constantCost(50),
                        2,
                        EquipmentSlotGroup.MAINHAND
                )
        ).withEffect(ModEnchantmentEffectComponents.REPAIR_WITH_HEALTH.get()));
        register(context, TELEKINESIS, Enchantment.enchantment(
                Enchantment.definition(
                        items.getOrThrow(ModTags.Items.WEAPON_AND_MINING_ENCHANTABLE),
                        items.getOrThrow(ItemTags.MINING_ENCHANTABLE),
                        1,
                        1,
                        Enchantment.constantCost(20),
                        Enchantment.constantCost(30),
                        4,
                        EquipmentSlotGroup.MAINHAND
                )
        ).withEffect(ModEnchantmentEffectComponents.TELEKINESIS.get()));
        //endregion
        //region pet
        register(context, VAMPIRISM, Enchantment.enchantment(Enchantment.definition(
                items.getOrThrow(ModTags.Items.WOLF_ARMOR_ENCHANTABLE),
                items.getOrThrow(ModTags.Items.WOLF_ARMOR_ENCHANTABLE),
                2,
                3,
                Enchantment.dynamicCost(5, 2),
                Enchantment.dynamicCost(5, 3),
                2,
                EquipmentSlotGroup.BODY
        )).withEffect(EnchantmentEffectComponents.ATTRIBUTES, new EnchantmentAttributeEffect(
                EnchantmentsPlusMod.res("vampirism"),
                ExtraAttributes.LIFE_STEAL,
                LevelBasedValue.perLevel(1),
                AttributeModifier.Operation.ADD_VALUE
        )));
        //endregion pet
    }

    private static Enchantment.Builder damageEnchantment(HolderSet<Item> weaponEnchantable, HolderSet<Item> swordEnchantable, TagKey<EntityType<?>> sensitive) {
        return Enchantment.enchantment(
                Enchantment.definition(
                        weaponEnchantable,
                        swordEnchantable,
                        5,
                        5,
                        Enchantment.dynamicCost(5, 8),
                        Enchantment.dynamicCost(25, 8),
                        2,
                        EquipmentSlotGroup.MAINHAND
                )).withEffect(EnchantmentEffectComponents.DAMAGE,
                new AddValue(LevelBasedValue.perLevel(2.5f)),
                LootItemEntityPropertyCondition.hasProperties(
                        LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().entityType(EntityTypePredicate.of(sensitive))
                )
        );
    }

    private static void register(BootstrapContext<Enchantment> context, ResourceKey<Enchantment> key, Enchantment.Builder builder) {
        context.register(key, builder.build(key.location()));
    }
}
