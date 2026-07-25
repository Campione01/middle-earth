package net.jukoz.me.entity.beasts.warg.features;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.jukoz.me.MiddleEarth;
import net.jukoz.me.entity.beasts.warg.WargEntity;
import net.jukoz.me.entity.beasts.warg.WargModel;
import net.jukoz.me.entity.model.ModEntityModelLayers;
import net.jukoz.me.item.ModDataComponentTypes;
import net.jukoz.me.item.ModEquipmentItems;
import net.jukoz.me.item.items.armor.CustomAnimalArmorItem;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class WargArmorFrontSkullFeatureRenderer extends RenderLayer<WargEntity, WargModel> {
    private final WargArmorTopAddonsModel model;

    public WargArmorFrontSkullFeatureRenderer(RenderLayerParent<WargEntity, WargModel> context, EntityModelSet loader) {
        super(context);
        this.model = new WargArmorTopAddonsModel(loader.bakeLayer(ModEntityModelLayers.WARG_ARMOR_ADDONS_FRONT));
    }

    @Override
    public void render(PoseStack matrices, MultiBufferSource vertexConsumers, int light, WargEntity wargEntity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        ItemStack itemStack = wargEntity.getBodyArmorItem();
        Item item = itemStack.getItem();

        if(item instanceof CustomAnimalArmorItem animalArmorItem) {
            if (itemStack.is(ModEquipmentItems.WARG_MORDOR_PLATE_ARMOR)) {
                if(itemStack.get(ModDataComponentTypes.MOUNT_ARMOR_DATA) != null && itemStack.get(ModDataComponentTypes.MOUNT_ARMOR_DATA).topArmorAddon()) {
                    ((WargModel)this.getParentModel()).copyPropertiesTo(this.model);

                    this.model.setupAnim(wargEntity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);

                    ResourceLocation addonTexture = ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "textures/entities/warg/feature/warg_armor_addons.png");

                    VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderType.entityCutoutNoCull(addonTexture));
                    this.model.renderToBuffer(matrices, vertexConsumer, light, OverlayTexture.NO_OVERLAY, -1);
                }
            }
        }
    }
}
