package net.jukoz.me.entity.beasts.broadhoof.features;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.jukoz.me.entity.beasts.broadhoof.BroadhoofGoatEntity;
import net.jukoz.me.entity.beasts.broadhoof.BroadhoofGoatModel;
import net.jukoz.me.entity.model.ModEntityModelLayers;
import net.jukoz.me.item.dataComponents.CustomDyeableDataComponent;
import net.jukoz.me.item.items.armor.CustomAnimalArmorItem;
import net.jukoz.me.recipe.ModTags;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class BroadhoofGoatArmorFeatureRenderer extends RenderLayer<BroadhoofGoatEntity, BroadhoofGoatModel> {

    private final BroadhoofGoatArmorModel model;

    public BroadhoofGoatArmorFeatureRenderer(RenderLayerParent<BroadhoofGoatEntity, BroadhoofGoatModel> context, EntityModelSet loader) {
        super(context);
        this.model = new BroadhoofGoatArmorModel(loader.bakeLayer(ModEntityModelLayers.BROADHOOF_GOAT_ARMOR));
    }

    @Override
    public void render(PoseStack matrices, MultiBufferSource vertexConsumers, int light, BroadhoofGoatEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        ItemStack itemStack = entity.getBodyArmorItem();
        Item item = itemStack.getItem();
        if(item instanceof CustomAnimalArmorItem animalArmorItem) {
            if (animalArmorItem.getArmorType() == CustomAnimalArmorItem.Type.BROADHOOF_GOAT) {
                ((BroadhoofGoatModel)this.getParentModel()).copyPropertiesTo(this.model);

                this.model.setupAnim(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);

                VertexConsumer vertexConsumer;

                if (itemStack.is(ModTags.DYEABLE)) {
                    if(animalArmorItem.getOverlayTexture() != null) {
                        vertexConsumer = vertexConsumers.getBuffer(RenderType.entityCutoutNoCull(animalArmorItem.getOverlayTexture()));
                        this.model.renderToBuffer(matrices, vertexConsumer, light, OverlayTexture.NO_OVERLAY, -1);
                    }

                    vertexConsumer = vertexConsumers.getBuffer(RenderType.entityCutoutNoCull(animalArmorItem.getEntityTexture()));
                    int color = CustomDyeableDataComponent.getColor(itemStack, CustomDyeableDataComponent.DEFAULT_COLOR);
                    this.model.renderToBuffer(matrices, vertexConsumer, light, OverlayTexture.NO_OVERLAY, color);
                }
                else {
                    vertexConsumer = vertexConsumers.getBuffer(RenderType.entityCutoutNoCull(animalArmorItem.getEntityTexture()));
                    int color = CustomDyeableDataComponent.getColor(itemStack, CustomDyeableDataComponent.DEFAULT_COLOR);
                    this.model.renderToBuffer(matrices, vertexConsumer, light, OverlayTexture.NO_OVERLAY, -1);
                }
                return;
            }
        }
    }
}
