package net.kapitencraft.enchantments_plus.event;

import net.kapitencraft.enchantments_plus.enchantments.HealthMendingEnchantment;
import net.kapitencraft.enchantments_plus.registry.ModEnchantments;
import net.kapitencraft.enchantments_plus.util.VeinMinerHolder;
import net.kapitencraft.kap_lib.event.custom.ModifyFishingHookStatsEvent;
import net.kapitencraft.kap_lib.helpers.MathHelper;
import net.kapitencraft.kap_lib.helpers.MiscHelper;
import net.kapitencraft.kap_lib.util.Reference;
import net.kapitencraft.kap_lib.util.attribute.TimedModifier;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.ThornsEnchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.NetherWartBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class EventHandler {

    @SubscribeEvent
    public static void endermanEvent(EnderManAngerEvent event) {
        Player player = event.getPlayer();
        if (player.getItemBySlot(EquipmentSlot.HEAD).getEnchantmentLevel(ModEnchantments.ENDER_FRIEND.get()) > 0) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void blockBreakRegister(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        ItemStack mainHandItem = player.getMainHandItem();
        BlockState state = event.getState();
        Block block = state.getBlock(); Level level = player.level(); final BlockPos pos = event.getPos();

        ServerLevel serverLevel = level instanceof ServerLevel serverLevel1 ? serverLevel1 : null;
        if (serverLevel == null) return;
        ServerPlayer serverPlayer = (ServerPlayer) player;
        if (block instanceof CropBlock || block instanceof NetherWartBlock) {
            int max = block instanceof CropBlock cropBlock ? cropBlock.getMaxAge() : NetherWartBlock.MAX_AGE;
            IntegerProperty ageProperty = block instanceof CropBlock cropBlock ?  cropBlock.getAgeProperty() : BlockStateProperties.AGE_3;
            if (state.getValue(ageProperty) < max) {
                if (mainHandItem.getEnchantmentLevel(ModEnchantments.DELICATE.get()) > 0) {
                    event.setCanceled(true);
                    return;
                }
            }

            if (mainHandItem.getEnchantmentLevel(ModEnchantments.REPLENISH.get()) > 0) {
                event.setCanceled(true);
                Block.dropResources(state, level, pos);
                mainHandItem.mineBlock(level, state, pos, player);
                state = state.setValue(ageProperty, 0);
                level.setBlockAndUpdate(pos, state);
            }
        }
        if (mainHandItem.getEnchantmentLevel(ModEnchantments.LUMBERJACK.get()) > 0 && state.is(BlockTags.LOGS)) {
            VeinMinerHolder.create(pos, serverPlayer, block, pos1 -> {}, state1 -> true, pos1 -> false);
        }
        if (state.is(BlockTags.MINEABLE_WITH_PICKAXE) || state.is(BlockTags.MINEABLE_WITH_SHOVEL)) {
            MiscHelper.getEnchantmentLevelAndDo(mainHandItem, ModEnchantments.VEIN_MINER.get(), integer -> {
                Reference<Integer> brokenBlocks = Reference.of(-1);
                VeinMinerHolder.create(pos, serverPlayer, block,
                        pos1 -> MathHelper.up1(brokenBlocks),
                        state1 -> true, pos1 -> brokenBlocks.getIntValue() > integer);
            });
        }
        if (event.getExpToDrop() > 0) {
            MiscHelper.getEnchantmentLevelAndDo(mainHandItem, ModEnchantments.EXPERIENCED.get(), enchLevel -> {
                MathHelper.add(event::getExpToDrop, event::setExpToDrop, enchLevel);
            });
        }
        if (mainHandItem.getEnchantmentLevel(ModEnchantments.TELEKINESIS.get()) > 0) {
            addXp(player, event.getExpToDrop());
            event.setExpToDrop(0);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void telekinesisXpRegister(LivingExperienceDropEvent event) {
        Player attacker = event.getAttackingPlayer();
        if (attacker != null) {
            ItemStack mainHand = attacker.getMainHandItem();
            if (mainHand.getEnchantmentLevel(ModEnchantments.TELEKINESIS.get()) > 0) {
                addXp(attacker, event.getDroppedExperience());
                event.setCanceled(true);
            }
        }
    }

    private static void addXp(Player player, int amount) {
        player.giveExperiencePoints(MiscHelper.repairPlayerItems(player, amount, Enchantments.MENDING));
    }


    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void tickVeinMiner(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            VeinMinerHolder.tickAll();
        }
    }

    @SubscribeEvent
    public static void onModifyFishingHookStats(ModifyFishingHookStatsEvent event) {
        event.hookSpeed.addAddition(event.fishingRod.getEnchantmentLevel(ModEnchantments.FLASH.get()));
    }

    @SubscribeEvent
    public static void registerJoinEventData(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof AbstractArrow arrow) {
            if (arrow.getOwner() instanceof LivingEntity entity) {
                MiscHelper.getEnchantmentLevelAndDo(entity.getUseItem(), ModEnchantments.OVERLOAD.get(),
                        level -> arrow.getPersistentData().putInt("OverloadLvl", level)
                );
                MiscHelper.getEnchantmentLevelAndDo(entity.getUseItem(), ModEnchantments.WIND_BLESSING.get(),
                        level -> arrow.setNoGravity(true)
                );
            }
        }
    }


    @SubscribeEvent
    public static void healthRegenRegister(LivingHealEvent event) {
        LivingEntity living = event.getEntity();
        if (living instanceof Player player) event.setAmount(HealthMendingEnchantment.repairPlayerItems(player, event.getAmount()));
    }



    @SubscribeEvent
    public static void itemUseEvents(LivingEntityUseItemEvent event) {
        if (event.getItem().isEdible()) {
            int lvl = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.GLUTTONOUS.get(), event.getEntity());
            if (lvl > 0) {
                event.setDuration((int) (event.getDuration() * (1 - lvl * .1)));
            }
        }
    }

    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        DamageSource source = event.getSource();
        if (!source.isIndirect() && source.getEntity() instanceof LivingEntity living) {
            int lvl = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.BLOOD_THIRST.get(), living);
            if (lvl > 0) {
                AttributeInstance attack = living.getAttribute(Attributes.ATTACK_DAMAGE);
                if (attack != null) {
                    attack.addPermanentModifier(new TimedModifier("Blood Thirst", lvl / 100., AttributeModifier.Operation.MULTIPLY_BASE, lvl * 40));
                }
            }
        }
    }

    @SubscribeEvent
    public static void onMobEffectAdded(MobEffectEvent.Added event) {
        int resilienceLevel = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.RESILIENCE.get(), event.getEntity());
        if (resilienceLevel > 0) {
            MobEffectInstance instance = event.getEffectInstance();
            instance.duration = (int) (instance.duration * (1 - resilienceLevel * .2));
        }
    }

    @SubscribeEvent
    public static void onShieldBlock(ShieldBlockEvent event) {
        Entity attacker = event.getDamageSource().getEntity();
        if (attacker != null) {
            LivingEntity target = event.getEntity();
            int thornyLvl = target.getUseItem().getEnchantmentLevel(ModEnchantments.THORNY.get());
            if (thornyLvl > 0) {
                attacker.hurt(attacker.damageSources().thorns(target), ThornsEnchantment.getDamage(thornyLvl, target.getRandom()));
                target.getUseItem().hurtAndBreak(2, target, living -> living.broadcastBreakEvent(target.getUsedItemHand()));
            }
        }
    }
}
