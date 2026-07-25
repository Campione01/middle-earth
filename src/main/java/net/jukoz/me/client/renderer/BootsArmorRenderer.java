package net.jukoz.me.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.jukoz.me.compat.neoforge.api.client.rendering.v1.ArmorRenderer;
import net.jukoz.me.MiddleEarth;
import net.jukoz.me.MiddleEarthClient;
import net.jukoz.me.client.model.equipment.CustomBootsModel;
import net.jukoz.me.recipe.ModTags;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class BootsArmorRenderer implements ArmorRenderer {

    private CustomBootsModel<LivingEntity> customBootsModel;

    public BootsArmorRenderer() {
    }

    @Override
    public void render(PoseStack matrices, MultiBufferSource vertexConsumers, ItemStack stack, LivingEntity entity, EquipmentSlot slot, int light, HumanoidModel<LivingEntity> contextModel) {
        this.customBootsModel = new CustomBootsModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(MiddleEarthClient.CUSTOM_ARMOR_BOOTS));

        boolean dyeable = false;

        if (slot == EquipmentSlot.FEET) {
            contextModel.copyPropertiesTo(customBootsModel);
            customBootsModel.setAllVisible(false);
            customBootsModel.rightLeg.visible = true;
            customBootsModel.leftLeg.visible = true;

            if (stack.is(ModTags.DYEABLE)) {
                dyeable = true;
            }

            String texture = "textures/models/armor/" + BuiltInRegistries.ITEM.getKey(stack.getItem()).getPath() + ".png";
            ModArmorRenderer.renderArmor(matrices, vertexConsumers, light, stack, customBootsModel, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, texture), dyeable);
        }
    }
}
