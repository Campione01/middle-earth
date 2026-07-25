package net.jukoz.me.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.jukoz.me.datageneration.VariantsModelProvider;
import net.jukoz.me.datageneration.content.models.HotMetalsModel;
import net.jukoz.me.datageneration.content.models.SimpleBigItemModel;
import net.jukoz.me.datageneration.content.models.SimpleSpearModel;
import net.jukoz.me.item.items.weapons.artefacts.ArtefactCustomGlowingDaggerWeaponItem;
import net.jukoz.me.item.items.weapons.artefacts.ArtefactCustomGlowingLongswordWeaponItem;
import net.jukoz.me.item.items.weapons.artefacts.ArtefactCustomLongswordWeaponItem;
import net.jukoz.me.item.items.weapons.ranged.CustomLongbowWeaponItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.jukoz.me.item.ModDataComponentTypes;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.HashSet;
import java.util.Set;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {
    @Unique
    private static Set<Item> middleearth$inventoryVariantItems;

    @ModifyVariable(method = "render(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemDisplayContext;ZLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;IILnet/minecraft/client/resources/model/BakedModel;)V",
            at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private BakedModel renderItem(BakedModel model, ItemStack stack, ItemDisplayContext renderMode, boolean leftHanded, PoseStack matrices, MultiBufferSource vertexConsumers, int light, int overlay) {
        if(renderMode == ItemDisplayContext.GUI || renderMode == ItemDisplayContext.GROUND || renderMode == ItemDisplayContext.FIXED) {
            if(usesInventoryVariant(stack.getItem())) {
                ResourceLocation identifier = VariantsModelProvider.getInventoryModelIdentifierVariant(stack.getItem());
                if (SimpleBigItemModel.artefactsBroken.contains(stack.getItem()) && stack.getDamageValue() == stack.getMaxDamage() - 1){
                    identifier = VariantsModelProvider.getInventoryModelBrokenItem(stack.getItem());
                    return getModelOrOriginal(identifier, model);
                }

                if (SimpleBigItemModel.artefactsGlowing.contains(stack.getItem())) {
                    if (stack.getItem() instanceof  ArtefactCustomGlowingLongswordWeaponItem item && item.glowing){
                        identifier = VariantsModelProvider.getInventoryModelGlowingItem(item);
                        return getModelOrOriginal(identifier, model);
                    } else if (stack.getItem() instanceof  ArtefactCustomGlowingDaggerWeaponItem item && item.glowing){
                        identifier = VariantsModelProvider.getInventoryModelGlowingItem(item);
                        return getModelOrOriginal(identifier, model);
                    }
                } else if(SimpleBigItemModel.bigBows.contains(stack.getItem())) {
                    if(stack.getItem() instanceof BowItem bowWeaponItem) {
                        Player playerEntity = Minecraft.getInstance().player;
                        if(playerEntity.getUseItem() == stack) {
                            float pull = BowItem.getPowerForTime(playerEntity.getTicksUsingItem());
                            if(stack.getItem() instanceof CustomLongbowWeaponItem) {
                                pull = CustomLongbowWeaponItem.getPullProgressLongbow((int) (playerEntity.getTicksUsingItem() * 0.92f));
                            }

                            if(pull > 0) {
                                identifier = VariantsModelProvider.getPullLongbowModel(bowWeaponItem, pull);
                                BakedModel bakedModel = getModelOrOriginal(identifier, model);
                                return bakedModel;
                            }
                        }
                    }
                }
                return getModelOrOriginal(identifier, model);
            }
        }

        if(isItemHot(stack)) {
            ResourceLocation identifier = VariantsModelProvider.getHotModelIdentifierVariant(stack.getItem());
            return getModelOrOriginal(identifier, model);
        }
        return model;
    }

    @Unique
    private static BakedModel getModelOrOriginal(ResourceLocation identifier, BakedModel original) {
        var modelManager = Minecraft.getInstance().getModelManager();
        BakedModel bakedModel = modelManager.getModel(ModelResourceLocation.standalone(identifier));
        return bakedModel == modelManager.getMissingModel() ? original : bakedModel;
    }

    @Unique
    private static boolean usesInventoryVariant(Item item) {
        if (middleearth$inventoryVariantItems == null) {
            Set<Item> items = new HashSet<>();
            items.addAll(SimpleBigItemModel.artefacts);
            items.addAll(SimpleBigItemModel.items);
            items.addAll(SimpleBigItemModel.bigBows);
            items.addAll(SimpleSpearModel.items);
            items.addAll(SimpleBigItemModel.genericItems);
            middleearth$inventoryVariantItems = items;
        }
        return middleearth$inventoryVariantItems.contains(item);
    }

    @Unique
    private static boolean isItemHot(ItemStack stack) {
        return stack.getComponents().has(ModDataComponentTypes.TEMPERATURE_DATA) && (
                HotMetalsModel.nuggets.contains(stack.getItem()) ||
                HotMetalsModel.ingots.contains(stack.getItem()) ||
                HotMetalsModel.items.contains(stack.getItem())
                );
    }

}
