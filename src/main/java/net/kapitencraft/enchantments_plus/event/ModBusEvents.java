package net.kapitencraft.enchantments_plus.event;

import net.kapitencraft.kap_lib.attribute.ExtraAttributes;
import net.kapitencraft.kap_lib.core.event.custom.RegisterUpdateCheckersEvent;
import net.minecraft.world.entity.EntityType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;

@EventBusSubscriber
public class ModBusEvents {

    @SubscribeEvent
    public static void onRegisterUpdateCheckers(RegisterUpdateCheckersEvent event) {
        event.register("enchantments_plus", "qXXsseLn", RegisterUpdateCheckersEvent.DEFAULT_PATTERN);
    }

    @SubscribeEvent
    public static void onEntityAttributeModification(EntityAttributeModificationEvent event) {
        event.add(EntityType.WOLF, ExtraAttributes.LIFE_STEAL);
    }
}
