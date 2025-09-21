package net.kapitencraft.enchantments_plus.event;

import net.kapitencraft.kap_lib.event.custom.RegisterUpdateCheckersEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber
public class ModBusEvents {

    @SubscribeEvent
    public static void onRegisterUpdateCheckers(RegisterUpdateCheckersEvent event) {
        event.register("enchantments_plus", "qXXsseLn", RegisterUpdateCheckersEvent.DEFAULT_PATTERN);
    }
}
