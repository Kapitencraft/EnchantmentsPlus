package net.kapitencraft.enchantments_plus;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ServerConfig {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    private static final ModConfigSpec.IntValue MAX_ENCHANTING_POWER = BUILDER
            .comment("determines the maximum enchanting power an enchanting table can have, vanilla default = 15")
            .defineInRange("max_enchanting_power", 30, 5, 60);

    public static final ModConfigSpec SPEC = BUILDER.build();

    public static int getMaxEnchantingPower() {
        return MAX_ENCHANTING_POWER.get();
    }
}