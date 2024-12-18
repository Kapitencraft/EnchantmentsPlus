package net.kapitencraft.enchantments_plus.loot_table;

import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParam;

public class LootTableHelper {

    public static void copy(LootParams.Builder origin, LootContext context, LootContextParam<?>... params) {
        for (LootContextParam<?> param : params) {
            copyParam(param, origin, context);
        }
    }

    private static <T> void copyParam(LootContextParam<T> param, LootParams.Builder origin, LootContext context) {
        origin.withParameter(param, context.getParam(param));
    }
}
