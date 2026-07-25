package net.jukoz.me.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.jukoz.me.compat.neoforge.api.client.rendering.v1.ArmorRenderer;
import net.jukoz.me.MiddleEarth;
import net.jukoz.me.MiddleEarthClient;
import net.jukoz.me.client.model.equipment.CustomLeggingsModel;
import net.jukoz.me.recipe.ModTags;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class LeggingsArmorRenderer implements ArmorRenderer {

    private CustomLeggingsModel<LivingEntity> customLeggingsModel;

    public LeggingsArmorRenderer() {
    }

    @Override
    public void render(PoseStack matrices, MultiBufferSource vertexConsumers, ItemStack stack, LivingEntity entity, EquipmentSlot slot, int light, HumanoidModel<LivingEntity> contextModel) {
        this.customLeggingsModel = new CustomLeggingsModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(MiddleEarthClient.CUSTOM_ARMOR_LEGGINGS));
        boolean dyeable = false;

        if (slot == EquipmentSlot.LEGS) {
            contextModel.copyPropertiesTo(customLeggingsModel);
            customLeggingsModel.setAllVisible(false);
            customLeggingsModel.body.visible = true;
            customLeggingsModel.rightLeg.visible = true;
            customLeggingsModel.leftLeg.visible = true;

            if (stack.is(ModTags.DYEABLE)) {
                dyeable = true;
            }

            String texture = "textures/models/armor/" + BuiltInRegistries.ITEM.getKey(stack.getItem()).getPath() + ".png";
            ModArmorRenderer.renderArmor(matrices, vertexConsumers, light, stack, customLeggingsModel, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, texture), dyeable);
        }
    }
}
