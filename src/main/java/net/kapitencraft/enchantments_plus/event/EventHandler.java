package net.kapitencraft.enchantments_plus.event;

import net.kapitencraft.enchantments_plus.enchantments.HealthMendingEnchantment;
import net.kapitencraft.enchantments_plus.registry.ModEnchantments;
import net.kapitencraft.enchantments_plus.enchantments.armor.BasaltWalkerEnchantment;
import net.kapitencraft.enchantments_plus.util.VeinMinerHolder;
import net.kapitencraft.kap_lib.event.custom.ModifyFishingHookStatsEvent;
import net.kapitencraft.kap_lib.helpers.MathHelper;
import net.kapitencraft.kap_lib.helpers.MiscHelper;
import net.kapitencraft.kap_lib.util.Reference;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.NetherWartBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.EnderManAngerEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class EventHandler {

    @SubscribeEvent
    public void onLivingTick(LivingEvent.LivingTickEvent event) {
        LivingEntity living = event.getEntity();
        int i = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.BASALT_WALKER.get(), living);
        if (i > 0) {
            BasaltWalkerEnchantment.onEntityMoved(living, living.blockPosition(), i);
        }

    }

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
        LootParams.Builder context = new LootParams.Builder(serverLevel).withParameter(LootContextParams.ORIGIN, new Vec3(pos.getX(), pos.getY(), pos.getZ())).withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(pos)).withParameter(LootContextParams.TOOL, mainHandItem).withOptionalParameter(LootContextParams.THIS_ENTITY, serverPlayer);
        if (block instanceof CropBlock || block instanceof NetherWartBlock) {
            IntegerProperty ageProperty = block instanceof CropBlock cropBlock ?  cropBlock.getAgeProperty() : BlockStateProperties.AGE_3;
            if (state.getValue(ageProperty) < MathHelper.getLargest(ageProperty.getPossibleValues())) {
                if (mainHandItem.getEnchantmentLevel(ModEnchantments.DELICATE.get()) > 0) {
                    event.setCanceled(true);
                    return;
                }
            } else {
            }

            if (mainHandItem.getEnchantmentLevel(ModEnchantments.REPLENISH.get()) > 0) {
                event.setCanceled(true);
                Block.dropResources(state, level, pos);
                state.setValue(ageProperty, 0);
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
        MiscHelper.getEnchantmentLevelAndDo(mainHandItem, ModEnchantments.EXPERIENCED.get(), integer -> {
            MathHelper.add(event::getExpToDrop, event::setExpToDrop, integer);
        });
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
    public void onModifyFishingHookStats(ModifyFishingHookStatsEvent event) {
        event.hookSpeed.addAddition(event.fishingRod.getEnchantmentLevel(ModEnchantments.FLASH.get()));
    }


    @SubscribeEvent
    public static void healthRegenRegister(LivingHealEvent event) {
        LivingEntity living = event.getEntity();
        if (living instanceof Player player) event.setAmount(HealthMendingEnchantment.repairPlayerItems(player, event.getAmount()));
    }
}
