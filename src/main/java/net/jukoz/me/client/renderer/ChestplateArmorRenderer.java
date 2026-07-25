package net.jukoz.me.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.jukoz.me.compat.neoforge.api.client.rendering.v1.ArmorRenderer;
import net.jukoz.me.MiddleEarth;
import net.jukoz.me.MiddleEarthClient;
import net.jukoz.me.client.model.equipment.CustomChestplateModel;
import net.jukoz.me.client.model.equipment.chest.ChestplateAddonModel;
import net.jukoz.me.client.model.equipment.chest.capes.CloakCapeModel;
import net.jukoz.me.item.ModDataComponentTypes;
import net.jukoz.me.item.dataComponents.CapeDataComponent;
import net.jukoz.me.item.items.armor.CustomChestplateItem;
import net.jukoz.me.item.utils.armor.ModArmorModels;
import net.jukoz.me.item.utils.armor.ModDyeablePieces;
import net.jukoz.me.recipe.ModTags;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class ChestplateArmorRenderer implements ArmorRenderer {

    private CustomChestplateModel<LivingEntity> customChestplateModel;
    private ChestplateAddonModel<LivingEntity> capeModel;
    private ChestplateAddonModel<LivingEntity> chestplateModel;

    public ChestplateArmorRenderer() {
    }

    public ChestplateArmorRenderer(ChestplateAddonModel<LivingEntity> chestplateModel) {
        this.chestplateModel = chestplateModel;
    }

    @Override
    public void render(PoseStack matrices, MultiBufferSource vertexConsumers, ItemStack stack, LivingEntity entity, EquipmentSlot slot, int light, HumanoidModel<LivingEntity> contextModel) {
        this.customChestplateModel = new CustomChestplateModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(MiddleEarthClient.CUSTOM_ARMOR_CHESTPLATE));
        this.capeModel = new CloakCapeModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(MiddleEarthClient.CAPE_MODEL_LAYER));

        CustomChestplateItem item = (CustomChestplateItem)stack.getItem();
        boolean dyeable = false;

        if (slot == EquipmentSlot.CHEST) {
            contextModel.copyPropertiesTo(customChestplateModel);
            customChestplateModel.setAllVisible(false);
            customChestplateModel.body.visible = true;
            customChestplateModel.rightArm.visible = true;
            customChestplateModel.leftArm.visible = true;
            customChestplateModel.rightLeg.visible = true;
            customChestplateModel.leftLeg.visible = true;

            if (stack.is(ModTags.DYEABLE)) {
                dyeable = true;
            }

            String texture = "textures/models/armor/" + BuiltInRegistries.ITEM.getKey(stack.getItem()).getPath() + ".png";
            ModArmorRenderer.renderArmor(matrices, vertexConsumers, light, stack, customChestplateModel, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, texture), dyeable);

            if (this.chestplateModel != null) {
                contextModel.copyPropertiesTo(this.chestplateModel);
                this.chestplateModel.setAllVisible(false);
                this.chestplateModel.body.visible = true;
                this.chestplateModel.rightArm.visible = true;
                this.chestplateModel.leftArm.visible = true;
                ModArmorRenderer.renderArmor(matrices, vertexConsumers, light, stack, this.chestplateModel, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, texture.replaceAll("_chestplate.png", "_addition.png")), dyeable);
            }

            CapeDataComponent capeDataComponent = stack.get(ModDataComponentTypes.CAPE_DATA);
            if (capeDataComponent != null) {
                this.capeModel = ModArmorModels.ModCapePairedModels.valueOf(capeDataComponent.cape().getName().toUpperCase()).getModel().getArmoredModel();
                contextModel.copyPropertiesTo(capeModel);
                capeModel.setAllVisible(false);
                capeModel.body.visible = true;
                capeModel.rightArm.visible = true;
                capeModel.leftArm.visible = true;
                capeModel.rightLeg.visible = true;
                capeModel.leftLeg.visible = true;
                capeModel.setupAnim(entity, entity.walkAnimation.position(), entity.walkAnimation.speed(), (float) entity.tickCount + Minecraft.getInstance().getTimer().getGameTimeDeltaPartialTick(true), contextModel.head.yRot, contextModel.head.zRot);
                if (ModDyeablePieces.dyeableCapes.containsKey(capeDataComponent.getCape())) {
                    CapeRenderer.renderDyeableCape(matrices, vertexConsumers, light, stack, capeModel, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "textures/models/cape/" + capeDataComponent.cape().getName() + ".png"), true);
                    if (ModDyeablePieces.dyeableCapes.get(capeDataComponent.cape()).booleanValue()){
                        ArmorRenderer.renderPart(matrices, vertexConsumers, light, stack, capeModel, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "textures/models/cape/" + capeDataComponent.cape().getName() + "_overlay.png"));
                    }
                } else {
                    ArmorRenderer.renderPart(matrices, vertexConsumers, light, stack, capeModel, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "textures/models/cape/" + capeDataComponent.cape().getName() + ".png"));
                }}
        }
    }
}
