package net.kapitencraft.enchantments_plus.event;

import net.kapitencraft.kap_lib.event.custom.RegisterUpdateCheckersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBusEvents {

    @SubscribeEvent
    public static void onRegisterUpdateCheckers(RegisterUpdateCheckersEvent event) {
        event.register("enchantments_plus", "qXXsseLn", RegisterUpdateCheckersEvent.DEFAULT_PATTERN);
    }
}
