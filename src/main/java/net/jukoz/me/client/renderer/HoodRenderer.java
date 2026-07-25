package net.jukoz.me.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.jukoz.me.compat.neoforge.api.client.rendering.v1.ArmorRenderer;
import net.jukoz.me.MiddleEarth;
import net.jukoz.me.MiddleEarthClient;
import net.jukoz.me.client.model.equipment.CustomHelmetModel;
import net.jukoz.me.client.model.equipment.head.helmets.HelmetAddonModel;
import net.jukoz.me.client.model.equipment.head.hoods.CloakHoodModel;
import net.jukoz.me.item.ModDataComponentTypes;
import net.jukoz.me.item.dataComponents.CustomDyeableDataComponent;
import net.jukoz.me.item.dataComponents.HoodDataComponent;
import net.jukoz.me.item.items.armor.HoodHelmetItem;
import net.jukoz.me.item.utils.armor.ModArmorModels;
import net.jukoz.me.item.utils.armor.ModDyeablePieces;
import net.jukoz.me.recipe.ModTags;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class HoodRenderer implements ArmorRenderer {

    private CustomHelmetModel<LivingEntity> customHelmetModel;
    private HelmetAddonModel<LivingEntity> hoodModel;

    public HoodRenderer() {
    }

    @Override
    public void render(PoseStack matrices, MultiBufferSource vertexConsumers, ItemStack stack, LivingEntity entity, EquipmentSlot slot, int light, HumanoidModel<LivingEntity> contextModel) {
        this.hoodModel = new CloakHoodModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(MiddleEarthClient.HOOD_MODEL_LAYER));

        if (slot == EquipmentSlot.HEAD) {
            HoodDataComponent hoodDataComponent = stack.get(ModDataComponentTypes.HOOD_DATA);

            if(hoodDataComponent != null) {
                ResourceLocation texture;
                if (hoodDataComponent.down()){
                     texture = ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "textures/models/hood/" + hoodDataComponent.hood().getName().toLowerCase() + "_down.png");
                    this.hoodModel = ModArmorModels.ModHoodPairedModels.valueOf(hoodDataComponent.hood().getName().toUpperCase()).getModel().getUnarmoredDownModel();
                } else {
                    texture = ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "textures/models/hood/" + hoodDataComponent.hood().getName().toLowerCase() + ".png");
                    this.hoodModel = ModArmorModels.ModHoodPairedModels.valueOf(hoodDataComponent.hood().getName().toUpperCase()).getModel().getUnarmoredModel();
                }
                contextModel.copyPropertiesTo(hoodModel);
                hoodModel.setAllVisible(false);
                hoodModel.hat.visible = true;
                if (ModDyeablePieces.dyeableHoods.containsKey(hoodDataComponent.getHood())) {
                    renderDyeableHood(matrices, vertexConsumers, light, stack, hoodModel, texture, false);
                    if (ModDyeablePieces.dyeableHoods.get(hoodDataComponent.hood())){
                        ModArmorRenderer.renderTranslucentPiece(matrices, vertexConsumers, light, stack, hoodModel, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, texture.getPath().replaceAll(".png", "_overlay.png")));
                    }
                } else {
                    ModArmorRenderer.renderTranslucentPiece(matrices, vertexConsumers, light, stack, hoodModel, texture);
                }
            }
        }
    }

    static void renderDyeableHood(PoseStack matrices, MultiBufferSource vertexConsumers, int light, ItemStack stack, Model model, ResourceLocation texture, boolean helmet) {
        VertexConsumer vertexConsumer = ItemRenderer.getArmorFoilBuffer(vertexConsumers, RenderType.armorCutoutNoCull(texture), stack.hasFoil());
        int color;
        if (helmet){
            color =  FastColor.ARGB32.opaque(stack.get(ModDataComponentTypes.HOOD_DATA).hoodColor());
        } else {
            color = CustomDyeableDataComponent.getColor(stack, CustomDyeableDataComponent.DEFAULT_COLOR);
        }
        model.renderToBuffer(matrices, vertexConsumer, light, OverlayTexture.NO_OVERLAY, color);
    }
}

