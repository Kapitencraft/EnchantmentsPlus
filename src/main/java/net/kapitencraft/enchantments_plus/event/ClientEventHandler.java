package net.kapitencraft.enchantments_plus.event;

import net.kapitencraft.enchantments_plus.registry.ModEnchantments;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.commons.compress.archivers.sevenz.CLI;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class ClientEventHandler {

    @SuppressWarnings("DataFlowIssue")
    @SubscribeEvent(receiveCanceled = true)
    public static void fogModifiers(ViewportEvent.RenderFog event) {
        int enlightenmentLevel = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.ENLIGHTENMENT.get(), Minecraft.getInstance().player);
        if (enlightenmentLevel > 0) {
            event.setCanceled(true);
            event.setNearPlaneDistance(event.getNearPlaneDistance() * (1 + enlightenmentLevel * .4f));
            event.setFarPlaneDistance(event.getFarPlaneDistance() * (1 + enlightenmentLevel * .4f));
        }
    }
}
