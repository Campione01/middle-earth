package net.jukoz.me.compat.neoforge.api.client.rendering.v1;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.LinkedHashMap;
import java.util.Map;

public interface ArmorRenderer {
    Map<Item, ArmorRenderer> RENDERERS = new LinkedHashMap<>();

    void render(PoseStack matrices, MultiBufferSource vertexConsumers, ItemStack stack, LivingEntity entity,
                EquipmentSlot slot, int light, HumanoidModel<LivingEntity> contextModel);

    static void register(ArmorRenderer renderer, Item... items) {
        for (Item item : items) {
            RENDERERS.put(item, renderer);
        }
    }

    static ArmorRenderer get(Item item) {
        return RENDERERS.get(item);
    }

    static void renderPart(PoseStack matrices, MultiBufferSource vertexConsumers, int light, ItemStack stack,
                           Model model, ResourceLocation texture) {
        VertexConsumer vertexConsumer = ItemRenderer.getArmorFoilBuffer(
                vertexConsumers, RenderType.armorCutoutNoCull(texture), stack.hasFoil());
        model.renderToBuffer(matrices, vertexConsumer, light, OverlayTexture.NO_OVERLAY, 0xFFFFFFFF);
    }
}
