package net.kapitencraft.enchantments_plus.mixin.classes;

import net.kapitencraft.enchantments_plus.registry.ModEnchantmentEffectComponents;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.IShearable;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Mixin(Sheep.class)
public abstract class SheepMixin extends Entity implements IShearable {

    public SheepMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Shadow public abstract void setSheared(boolean sheared);

    @Shadow @Final private static Map<DyeColor, ItemLike> ITEM_BY_DYE;

    @Shadow public abstract DyeColor getColor();

    @Override
    public List<ItemStack> onSheared(@Nullable Player player, ItemStack item, Level level, BlockPos pos) {
        this.level().playSound(null, this, SoundEvents.SHEEP_SHEAR, player == null ? SoundSource.BLOCKS : SoundSource.PLAYERS, 1.0F, 1.0F);
        this.setSheared(true);
        int i = 1 + this.random.nextInt(3);
        Collection<ItemEntity> previous = this.captureDrops(new ArrayList<>());

        for (int j = 0; j < i; j++) {
            ItemEntity itementity = this.spawnAtLocation(ITEM_BY_DYE.get(
                    EnchantmentHelper.has(item, ModEnchantmentEffectComponents.CHROMATIC.get()) ?
                            DyeColor.byId(level.getRandom().nextIntBetweenInclusive(0, 15)) :
                            this.getColor()), 1);
            if (itementity != null) {
                itementity.setDeltaMovement(
                        itementity.getDeltaMovement()
                                .add(
                                        (this.random.nextFloat() - this.random.nextFloat()) * 0.1F,
                                        this.random.nextFloat() * 0.05F,
                                        (this.random.nextFloat() - this.random.nextFloat()) * 0.1F
                                )
                );
            }
        }
        return this.captureDrops(previous).stream().map(ItemEntity::getItem).toList();
    }
}
