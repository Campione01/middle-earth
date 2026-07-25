package net.jukoz.me.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.jukoz.me.compat.neoforge.api.client.rendering.v1.ArmorRenderer;
import net.jukoz.me.MiddleEarth;
import net.jukoz.me.item.dataComponents.CustomDyeableDataComponent;
import net.jukoz.me.item.utils.armor.ModDyeablePieces;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class ModArmorRenderer implements ArmorRenderer {

    public ModArmorRenderer() {
    }

    @Override
    public void render(PoseStack matrices, MultiBufferSource vertexConsumers, ItemStack stack, LivingEntity entity, EquipmentSlot slot, int light, HumanoidModel<LivingEntity> contextModel) {

    }

    static void renderArmor(PoseStack matrices, MultiBufferSource vertexConsumers, int light, ItemStack stack, Model model, ResourceLocation texture, boolean dyeable){
        if(dyeable){
            renderDyeable(matrices, vertexConsumers, light, stack, model, texture);
            if(ModDyeablePieces.dyeablePieces.get(stack.getItem())) {
                ArmorRenderer.renderPart(matrices, vertexConsumers, light, stack, model, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, texture.getPath().replaceAll(".png", "_overlay.png")));
            }
        } else {
            ArmorRenderer.renderPart(matrices, vertexConsumers, light, stack, model, texture);
        }
    }

    static void renderDyeable(PoseStack matrices, MultiBufferSource vertexConsumers, int light, ItemStack stack, Model model, ResourceLocation texture) {
        VertexConsumer vertexConsumer = ItemRenderer.getArmorFoilBuffer(vertexConsumers, RenderType.armorCutoutNoCull(texture), stack.hasFoil());
        int color = CustomDyeableDataComponent.getColor(stack, CustomDyeableDataComponent.DEFAULT_COLOR);
        model.renderToBuffer(matrices, vertexConsumer, light, OverlayTexture.NO_OVERLAY, color);
    }

    static void renderTranslucentPiece(PoseStack matrices, MultiBufferSource vertexConsumers, int light, ItemStack stack, Model model, ResourceLocation texture) {
        VertexConsumer vertexConsumer = ItemRenderer.getArmorFoilBuffer(vertexConsumers, RenderType.entityTranslucent(texture), stack.hasFoil());
        model.renderToBuffer(matrices, vertexConsumer, light, OverlayTexture.NO_OVERLAY, 0xFFFFFFFF);
    }
}
