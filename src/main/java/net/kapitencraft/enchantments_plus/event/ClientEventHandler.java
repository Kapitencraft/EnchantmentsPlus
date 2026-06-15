package net.kapitencraft.enchantments_plus.event;

import net.kapitencraft.enchantments_plus.client.FortressShieldingRenderLayer;
import net.kapitencraft.enchantments_plus.data_gen.ModEnchantments;
import net.kapitencraft.kap_lib.core.helpers.EnchantmentHelperExtras;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.resources.PlayerSkin;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.ViewportEvent;

@EventBusSubscriber(Dist.CLIENT)
public class ClientEventHandler {

    @SuppressWarnings("DataFlowIssue")
    @SubscribeEvent(receiveCanceled = true)
    public static void fogModifiers(ViewportEvent.RenderFog event) {
        LocalPlayer player = Minecraft.getInstance().player;
        EnchantmentHelperExtras.getEnchantmentLevelAndDo(player, ModEnchantments.ENLIGHTENMENT, enlightenmentLevel -> {
            event.setCanceled(true);
            event.setNearPlaneDistance(event.getNearPlaneDistance() * (1 + enlightenmentLevel * .4f));
            event.setFarPlaneDistance(event.getFarPlaneDistance() * (1 + enlightenmentLevel * .4f));
        });
    }

    @SubscribeEvent
    public static void onEntityRenderersAddLayers(EntityRenderersEvent.AddLayers event) {
        EntityRendererProvider.Context context = event.getContext();
        addShieldFortressLayer(event.getSkin(PlayerSkin.Model.WIDE), context);
        addShieldFortressLayer(event.getSkin(PlayerSkin.Model.SLIM), context);
    }

    private static void addShieldFortressLayer(LivingEntityRenderer<Player, PlayerModel<Player>> renderer, EntityRendererProvider.Context context) {
        renderer.addLayer(new FortressShieldingRenderLayer<>(renderer, context));
    }
}
