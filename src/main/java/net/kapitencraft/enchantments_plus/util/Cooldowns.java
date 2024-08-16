package net.kapitencraft.enchantments_plus.util;

import net.kapitencraft.kap_lib.cooldown.MappedCooldown;
import net.minecraft.world.entity.EquipmentSlot;

public interface Cooldowns {

    MappedCooldown<EquipmentSlot> BONK_ENCHANTMENT = new MappedCooldown<>("Bonk", EquipmentSlot::getName, null);
}
