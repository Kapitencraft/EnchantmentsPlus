package net.kapitencraft.enchantments_plus.event;

import net.kapitencraft.enchantments_plus.data_gen.ModDamageTypes;
import net.kapitencraft.enchantments_plus.data_gen.ModEnchantments;
import net.kapitencraft.enchantments_plus.registry.ModEnchantmentEffectComponents;
import net.kapitencraft.enchantments_plus.util.VeinMinerHolder;
import net.kapitencraft.kap_lib.core.helpers.EnchantmentHelperExtras;
import net.kapitencraft.kap_lib.core.helpers.MathHelper;
import net.kapitencraft.kap_lib.enchantment.event.custom.RegisterEnchantmentApplicableCharsEvent;
import net.kapitencraft.kap_lib.item.event.custom.ModifyFishingHookStatsEvent;
import net.kapitencraft.kap_lib.particle.custom.LightningParticleOptions;
import net.minecraft.core.BlockPos;
import net.minecraft.core.RegistryAccess;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.NetherWartBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.event.entity.living.*;
import net.neoforged.neoforge.event.entity.player.CanPlayerSleepEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.entity.player.PlayerSpawnPhantomsEvent;
import net.neoforged.neoforge.event.level.BlockDropsEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@EventBusSubscriber
public class EventHandler {

    @SubscribeEvent
    public static void endermanEvent(EnderManAngerEvent event) {
        Player player = event.getPlayer();
        if (EnchantmentHelper.has(player.getItemBySlot(EquipmentSlot.HEAD), ModEnchantmentEffectComponents.ENDER_FRIEND.get())) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void blockBreakRegister(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        ItemStack mainHandItem = player.getMainHandItem();
        BlockState state = event.getState();
        Block block = state.getBlock();
        Level level = player.level();
        final BlockPos pos = event.getPos();

        ServerLevel serverLevel = level instanceof ServerLevel serverLevel1 ? serverLevel1 : null;
        if (serverLevel == null) return;
        ServerPlayer serverPlayer = (ServerPlayer) player;

        if (EnchantmentHelper.has(mainHandItem, ModEnchantmentEffectComponents.MAGMATIC.get()) && state.is(Blocks.NETHERRACK)) {
            event.setCanceled(true);
            level.setBlockAndUpdate(pos, Blocks.LAVA.defaultBlockState());
            return;
        }

        if (block instanceof CropBlock || block instanceof NetherWartBlock) {
            IntegerProperty ageProperty = block instanceof CropBlock cropBlock ? cropBlock.getAgeProperty() : BlockStateProperties.AGE_3;
            if (EnchantmentHelper.has(mainHandItem, ModEnchantmentEffectComponents.REPLENISH.get())) {
                event.setCanceled(true);
                Block.dropResources(state, level, pos);
                mainHandItem.mineBlock(level, state, pos, player);
                state = state.setValue(ageProperty, 0);
                level.setBlockAndUpdate(pos, state);
            }
        }
        if (EnchantmentHelper.has(mainHandItem, ModEnchantmentEffectComponents.LUMBERJACK.get()) && state.is(BlockTags.LOGS)) {
            VeinMinerHolder.create(pos, serverPlayer, block, pos1 -> {
            }, state1 -> true, pos1 -> false);
        }
        if (state.is(BlockTags.MINEABLE_WITH_PICKAXE) || state.is(BlockTags.MINEABLE_WITH_SHOVEL)) {
            EnchantmentHelperExtras.getEnchantmentLevelAndDo(level.registryAccess(), mainHandItem, ModEnchantments.VEIN_MINER, integer -> {
                AtomicInteger brokenBlocks = new AtomicInteger(-1);
                VeinMinerHolder.create(pos, serverPlayer, block,
                        pos1 -> brokenBlocks.set(brokenBlocks.get() + 1),
                        state1 -> true, pos1 -> brokenBlocks.get() > integer);
            });
        }
    }

    @SubscribeEvent
    public static void onPlayerBreakSpeed(PlayerEvent.BreakSpeed event) {
        Player player = event.getEntity();
        ItemStack mainHandItem = player.getMainHandItem();
        BlockState state = event.getState();
        Block block = state.getBlock();
        if (block instanceof CropBlock || block instanceof NetherWartBlock) {
            int max = block instanceof CropBlock cropBlock ? cropBlock.getMaxAge() : NetherWartBlock.MAX_AGE;
            IntegerProperty ageProperty = block instanceof CropBlock cropBlock ? cropBlock.getAgeProperty() : BlockStateProperties.AGE_3;
            if (state.getValue(ageProperty) < max) {
                if (EnchantmentHelper.has(mainHandItem, ModEnchantmentEffectComponents.DELICATE.get())) {
                    event.setCanceled(true);
                }
            }
        }
    }


    @SubscribeEvent
    public static void onLivingDamagePre(LivingDamageEvent.Pre event) {
        DamageSource source = event.getSource();
        LivingEntity attacked = event.getEntity();
        if (source.getEntity() instanceof LivingEntity attacker) {
            EnchantmentHelperExtras.getEnchantmentLevelAndDo(attacker, ModEnchantments.EXECUTE, integer -> {
                float healthPercent = attacked.getHealth() / attacked.getMaxHealth();
                event.setNewDamage(event.getNewDamage() * (1 + (1 - healthPercent) * integer * .1f));
            });
            EnchantmentHelperExtras.getEnchantmentLevelAndDo(attacker, ModEnchantments.PROSECUTE, integer -> {
                float healthPercent = attacked.getHealth() / attacked.getMaxHealth();
                event.setNewDamage(event.getNewDamage() * (1 + healthPercent * integer * .1f));
            });
            EnchantmentHelperExtras.getEnchantmentLevelAndDo(attacker, ModEnchantments.GIANT_KILLER, i -> {
                float moreHpPercent = attacked.getHealth() / attacker.getHealth();
                event.setNewDamage((float) (event.getNewDamage() * (1 + Math.min(moreHpPercent * i * 0.01f, 0.5))));
            });
            EnchantmentHelperExtras.getEnchantmentLevelAndDo(attacker, ModEnchantments.COMBAT_KNOWLEDGE, level -> {
                if (source.getDirectEntity() == attacker && MathHelper.chance(.001, attacker)) {
                    event.setNewDamage(Float.MAX_VALUE);
                }
            });
        }
    }


    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onBlockDrops(BlockDropsEvent event) {
        if (event.getBreaker() instanceof Player player) {
            ItemStack mainHandItem = event.getTool();
            RegistryAccess access = player.registryAccess();
            if (event.getDroppedExperience() > 0) {
                EnchantmentHelperExtras.getEnchantmentLevelAndDo(access, mainHandItem, ModEnchantments.EXPERIENCED, enchLevel ->
                        MathHelper.add(event::getDroppedExperience, event::setDroppedExperience, enchLevel)
                );
            }
            if (EnchantmentHelper.has(mainHandItem, ModEnchantmentEffectComponents.TELEKINESIS.get())) {
                addXp(player, event.getDroppedExperience());
                event.setDroppedExperience(0);
            }
        }
    }

    @SubscribeEvent
    public static void onLivingExperienceDrop(LivingExperienceDropEvent event) {
        Player attackingPlayer = event.getAttackingPlayer();
        ItemStack handItem = attackingPlayer.getWeaponItem();
        RegistryAccess access = attackingPlayer.level().registryAccess();
        EnchantmentHelperExtras.getEnchantmentLevelAndDo(access, handItem, ModEnchantments.EXPERIENCED, enchLevel ->
                MathHelper.add(event::getDroppedExperience, event::setDroppedExperience, enchLevel)
        );
    }

    //region chain lightning
    @SubscribeEvent
    public static void onLivingDamagePost(LivingDamageEvent.Post event) {
        LivingEntity attacked = event.getEntity();
        DamageSource source = event.getSource();
        LivingEntity attacker = source.isDirect() ? source.getDirectEntity() instanceof LivingEntity living ? living : null : null;
        float damage = event.getOriginalDamage();
        if (attacker != null && !attacker.level().isClientSide()) {
            ServerLevel level = (ServerLevel) attacker.level();
            int enchantmentLevel = EnchantmentHelper.getEnchantmentLevel(level.registryAccess().holderOrThrow(ModEnchantments.CHAIN_LIGHTNING), attacker);
            LivingEntity target;
            List<LivingEntity> previous = new ArrayList<>();
            if (level.getRandom().nextFloat() < enchantmentLevel * .02f) {
                for (int i = 0; i < enchantmentLevel; i++) {
                    target = selectTarget(enchantmentLevel, level, attacked, attacker, previous);
                    if (target == null) break;
                    previous.add(target);
                    target.hurt(attacked.damageSources().source(ModDamageTypes.CHAIN_LIGHTNING, attacker), enchantmentLevel * .05f * damage);
                    level.sendParticles(new LightningParticleOptions(attacked.getEyePosition(), target.getEyePosition(), 5, 100, .4f, .2f), target.getX(), target.getY(), target.getZ(), 1, 0, 0, 0, 0);
                    attacked = target;
                }
            }
        }
    }

    private static LivingEntity selectTarget(int enchLevel, Level level, LivingEntity origin, LivingEntity attacker, List<LivingEntity> previous) {
        List<LivingEntity> livings = level.getEntitiesOfClass(
                LivingEntity.class,
                origin.getBoundingBox().inflate(enchLevel * 2),
                living1 -> {
                    if (living1 == attacker || previous.contains(living1)) return false;
                    BlockHitResult result = living1.level().clip(new ClipContext(origin.getEyePosition(), living1.getEyePosition(), ClipContext.Block.COLLIDER, ClipContext.Fluid.WATER, attacker));
                    return result.getType() == HitResult.Type.MISS;
                }
        );
        return MathHelper.pickRandom(livings, origin.getRandom());
    }
    //endregion

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void telekinesisXpRegister(LivingExperienceDropEvent event) {
        Player attacker = event.getAttackingPlayer();
        if (attacker != null) {
            ItemStack mainHand = attacker.getWeaponItem();
            if (EnchantmentHelper.has(mainHand, ModEnchantmentEffectComponents.TELEKINESIS.get())) {
                addXp(attacker, event.getDroppedExperience());
                event.setCanceled(true);
            }
        }
    }

    private static void addXp(Player player, int amount) {
        player.giveExperiencePoints(EnchantmentHelperExtras.repairPlayerItems(player, amount, EnchantmentEffectComponents.REPAIR_WITH_XP));
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void tickVeinMiner(ServerTickEvent.Post event) {
        VeinMinerHolder.tickAll();
    }

    @SubscribeEvent
    public static void onModifyFishingHookStats(ModifyFishingHookStatsEvent event) {
        event.hookSpeed.addAddition(event.fishingRod.getEnchantmentLevel(event.player.registryAccess().holderOrThrow(ModEnchantments.FLASH)));
    }

    @SubscribeEvent
    public static void healthRegenRegister(LivingHealEvent event) {
        LivingEntity living = event.getEntity();
        if (living instanceof Player player) {
            float amount = event.getAmount();
            amount = EnchantmentHelperExtras.repairPlayerItems(player, (int) amount, ModEnchantmentEffectComponents.REPAIR_WITH_HEALTH.get()) + amount % 1;
            event.setAmount(amount);
        }
    }

    @SubscribeEvent
    public static void onMobEffectAdded(MobEffectEvent.Added event) {
        if (!event.getEffectInstance().getEffect().value().isBeneficial()) {
            EnchantmentHelperExtras.getEnchantmentLevelAndDo(event.getEntity(), ModEnchantments.RESILIENCE, integer -> {
                MobEffectInstance instance = event.getEffectInstance();
                instance.duration = instance.mapDuration(i -> (int) (i * (1 - integer * .2)));
            });
        }
    }

    @SubscribeEvent
    public static void onPlayerSpawnPhantoms(PlayerSpawnPhantomsEvent event) {
        EnchantmentHelperExtras.getEnchantmentLevelAndDo(event.getEntity(), ModEnchantments.SLEEPY, i -> event.setResult(PlayerSpawnPhantomsEvent.Result.DENY));
    }

    @SubscribeEvent
    public static void onPlayerSleepInBed(CanPlayerSleepEvent event) {
        EnchantmentHelperExtras.getEnchantmentLevelAndDo(event.getEntity(), ModEnchantments.INSOMNIA, i -> event.setProblem(Player.BedSleepingProblem.NOT_SAFE));
    }

    @SubscribeEvent
    public static void onLivingShieldBlock(LivingShieldBlockEvent event) {
        LivingEntity entity = event.getEntity();
        ItemStack item = entity.getUseItem();
        if (item.canPerformAction(ItemAbilities.SHIELD_BLOCK)) {
            EnchantmentHelperExtras.getEnchantmentLevelAndDo(
                    entity.level().registryAccess(),
                    item,
                    ModEnchantments.FORTRESS,
                    i -> {
                        boolean fortress = isDamageSourceBlockedFortress(entity, event.getDamageSource());
                        if (fortress) {
                            event.setBlocked(true);

                        }
                    }
            );
        }
    }

    /**
     * Determines whether the entity can block the damage source based on the damage source's location, whether the damage source is blockable, and whether the entity is blocking.
     */
    public static boolean isDamageSourceBlockedFortress(LivingEntity target, DamageSource source) {
        Entity entity = source.getDirectEntity();
        boolean flag = entity instanceof AbstractArrow abstractarrow && abstractarrow.getPierceLevel() > 0;

        return !source.is(DamageTypeTags.BYPASSES_SHIELD) && target.isBlocking() && !flag;
    }

    @SubscribeEvent
    public static void onRegisterEnchantmentApplicableChars(RegisterEnchantmentApplicableCharsEvent event) {
        event.register(Items.DIAMOND_HORSE_ARMOR);
        event.register(Items.WOLF_ARMOR);
    }

}
