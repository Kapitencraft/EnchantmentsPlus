package net.kapitencraft.enchantments_plus.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.kapitencraft.enchantments_plus.data_gen.ModEnchantments;
import net.kapitencraft.kap_lib.core.client.ExtraRenderTypes;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.ShieldModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class FortressShieldingRenderLayer<T extends LivingEntity, M extends HumanoidModel<T>> extends RenderLayer<T, M> {
    private final ShieldModel model;

    public FortressShieldingRenderLayer(RenderLayerParent<T, M> renderer, EntityRendererProvider.Context context) {
        super(renderer);
        this.model = new ShieldModel(context.bakeLayer(ModelLayers.SHIELD));
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, T livingEntity, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {
        ItemStack item = livingEntity.getUseItem();
        if (item != ItemStack.EMPTY && item.getEnchantmentLevel(livingEntity.registryAccess().holderOrThrow(ModEnchantments.FORTRESS)) > 0) {
            poseStack.pushPose();
            poseStack.translate(0, .5, 0);
            poseStack.mulPose(Axis.YP.rotationDegrees(livingEntity.tickCount + partialTick));
            Material material = ModelBakery.NO_PATTERN_SHIELD;
            VertexConsumer buffer = material.buffer(bufferSource, ModRenderTypes::ghostEntity);
            for (int i = 0; i < 4; i++) {
                poseStack.pushPose();
                poseStack.mulPose(Axis.YP.rotationDegrees(90 * i));
                poseStack.translate(0, 0, -.5f);
                model.handle().render(poseStack, buffer, LightTexture.FULL_BRIGHT, OverlayTexture.NO_OVERLAY);
                model.plate().render(poseStack, buffer, LightTexture.FULL_BRIGHT, OverlayTexture.NO_OVERLAY);
                poseStack.popPose();
            }
            poseStack.popPose();
        }
    }
}
