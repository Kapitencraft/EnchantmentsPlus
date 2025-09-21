package net.kapitencraft.enchantments_plus.event;

import net.kapitencraft.enchantments_plus.data_gen.ModEnchantments;
import net.kapitencraft.kap_lib.helpers.EnchantmentHelperExtras;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
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
}
