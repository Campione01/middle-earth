package net.jukoz.me.client.renderer;

import net.jukoz.me.compat.neoforge.api.client.rendering.v1.ArmorRenderer;
import net.jukoz.me.MiddleEarth;
import net.jukoz.me.MiddleEarthClient;
import net.jukoz.me.client.model.equipment.chest.ChestplateAddonModel;
import net.jukoz.me.client.model.equipment.chest.capes.CloakCapeModel;
import net.jukoz.me.item.ModDataComponentTypes;
import net.jukoz.me.item.dataComponents.CapeDataComponent;
import net.jukoz.me.item.dataComponents.CustomDyeableDataComponent;
import net.jukoz.me.item.items.armor.CapeChestplateItem;
import net.jukoz.me.item.utils.armor.ModArmorModels;
import net.jukoz.me.item.utils.armor.ModDyeablePieces;
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
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import java.util.Objects;

public class CapeRenderer implements ArmorRenderer {

    private ChestplateAddonModel<LivingEntity> capeModel;

    public CapeRenderer() {
    }

    @Override
    public void render(PoseStack matrices, MultiBufferSource vertexConsumers, ItemStack stack, LivingEntity entity, EquipmentSlot slot, int light, HumanoidModel<LivingEntity> contextModel) {
        this.capeModel = new CloakCapeModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(MiddleEarthClient.CAPE_MODEL_LAYER));

        if (slot == EquipmentSlot.CHEST) {
            CapeDataComponent capeDataComponent = stack.get(ModDataComponentTypes.CAPE_DATA);

            if (capeDataComponent != null) {
                this.capeModel = ModArmorModels.ModCapePairedModels.valueOf(capeDataComponent.cape().getName().toUpperCase()).getModel().getUnarmoredModel();
                contextModel.copyPropertiesTo(capeModel);
                capeModel.setAllVisible(false);
                capeModel.body.visible = true;
                capeModel.rightArm.visible = true;
                capeModel.leftArm.visible = true;
                capeModel.rightLeg.visible = true;
                capeModel.leftLeg.visible = true;
                this.capeModel.setupAnim(entity, entity.walkAnimation.position(), entity.walkAnimation.speed(), (float) entity.tickCount + Minecraft.getInstance().getTimer().getGameTimeDeltaPartialTick(true), contextModel.head.yRot, contextModel.head.zRot);

                if (ModDyeablePieces.dyeableCapes.containsKey(capeDataComponent.getCape())) {
                    renderDyeableCape(matrices, vertexConsumers, light, stack, capeModel, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "textures/models/cape/" + capeDataComponent.cape().getName() + ".png"), false);
                    if (ModDyeablePieces.dyeableCapes.get(capeDataComponent.cape())){
                        ArmorRenderer.renderPart(matrices, vertexConsumers, light, stack, capeModel, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "textures/models/cape/" + capeDataComponent.cape().getName() + "_overlay.png"));
                    }
                } else {
                    ArmorRenderer.renderPart(matrices, vertexConsumers, light, stack, capeModel, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "textures/models/cape/" + capeDataComponent.cape().getName() + ".png"));
                }
            }
        }
    }

    static void renderDyeableCape(PoseStack matrices, MultiBufferSource vertexConsumers, int light, ItemStack stack, Model model, ResourceLocation texture, boolean chestplate) {
        VertexConsumer vertexConsumer = ItemRenderer.getArmorFoilBuffer(vertexConsumers, RenderType.armorCutoutNoCull(texture), stack.hasFoil());
        int color;
        if (chestplate){
            color =  FastColor.ARGB32.opaque(stack.get(ModDataComponentTypes.CAPE_DATA).capeColor());
        } else {
            color = CustomDyeableDataComponent.getColor(stack, CustomDyeableDataComponent.DEFAULT_COLOR);
        }
        model.renderToBuffer(matrices, vertexConsumer, light, OverlayTexture.NO_OVERLAY, color);
    }
}
