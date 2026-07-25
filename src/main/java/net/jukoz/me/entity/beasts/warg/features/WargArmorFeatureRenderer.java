package net.jukoz.me.entity.beasts.warg.features;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.jukoz.me.entity.beasts.warg.WargEntity;
import net.jukoz.me.entity.beasts.warg.WargModel;
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

public class WargArmorFeatureRenderer extends RenderLayer<WargEntity, WargModel> {
    private final WargArmorModel model;

    public WargArmorFeatureRenderer(RenderLayerParent<WargEntity, WargModel> context, EntityModelSet loader) {
        super(context);
        this.model = new WargArmorModel(loader.bakeLayer(ModEntityModelLayers.WARG_ARMOR));
    }

    @Override
    public void render(PoseStack matrices, MultiBufferSource vertexConsumers, int light, WargEntity wargEntity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        ItemStack itemStack = wargEntity.getBodyArmorItem();
        Item item = itemStack.getItem();
        if(item instanceof CustomAnimalArmorItem animalArmorItem) {
            if (animalArmorItem.getArmorType() == CustomAnimalArmorItem.Type.WARG) {
                ((WargModel)this.getParentModel()).copyPropertiesTo(this.model);

                this.model.setupAnim(wargEntity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);

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
            }
        }
    }
}
