package net.kapitencraft.enchantments_plus.data_gen;

import net.kapitencraft.enchantments_plus.EnchantmentsPlusMod;
import net.kapitencraft.enchantments_plus.registry.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {
    private static final ResourceLocation BASALT_SIDE = new ResourceLocation("block/basalt_side");
    private static final ResourceLocation BASALT_TOP = new ResourceLocation("block/basalt_top");

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, EnchantmentsPlusMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        getVariantBuilder(ModBlocks.FRAGILE_BASALT.get())
                .forAllStates(state -> {
                    int level = state.getValue(BlockStateProperties.AGE_3);
                    return ConfiguredModel.builder().modelFile(createFragileBasaltModel(level)).build();
                });
    }

    private ModelFile createFragileBasaltModel(int age) {
        return models().cubeColumn(
                "fragile_basalt_" + age,
                age == 0 ? BASALT_SIDE : EnchantmentsPlusMod.res("block/fragile_basalt_side_" + age),
                age == 0 ? BASALT_TOP : EnchantmentsPlusMod.res("block/fragile_basalt_top_" + age)
        );
    }
}
