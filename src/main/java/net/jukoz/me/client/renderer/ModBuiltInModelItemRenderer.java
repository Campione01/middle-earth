package net.jukoz.me.client.renderer;

import net.jukoz.me.compat.neoforge.dist.EnvType;
import net.jukoz.me.compat.neoforge.dist.Environment;
import net.jukoz.me.compat.neoforge.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.jukoz.me.MiddleEarthClient;
import net.jukoz.me.client.MEModelLoader;
import net.jukoz.me.client.ModTexturedRenderLayers;
import net.jukoz.me.client.model.hand.HeldBannerEntityModel;
import net.jukoz.me.client.model.hand.shields.HeaterShieldEntityModel;
import net.jukoz.me.client.model.hand.shields.KiteShieldEntityModel;
import net.jukoz.me.client.model.hand.shields.RoundShieldEntityModel;
import net.jukoz.me.item.ModWeaponItems;
import net.jukoz.me.item.items.HeldBannerItem;
import net.jukoz.me.item.items.shields.CustomBannerShieldItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BannerPatternLayers;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import java.util.Objects;

@Environment(EnvType.CLIENT)
public class ModBuiltInModelItemRenderer implements BuiltinItemRendererRegistry.DynamicItemRenderer {

    private HeaterShieldEntityModel heaterShieldEntityModel;
    private KiteShieldEntityModel kiteShieldEntityModel;
    private RoundShieldEntityModel roundShieldEntityModel;

    private HeldBannerEntityModel heldBannerEntityModel;

    public ModBuiltInModelItemRenderer() {
    }

    public void render(ItemStack stack, ItemDisplayContext mode, PoseStack matrices, MultiBufferSource vertexConsumers, int light, int overlay) {
        this.heaterShieldEntityModel = new HeaterShieldEntityModel(Minecraft.getInstance().getEntityModels().bakeLayer(MiddleEarthClient.HEATER_SHIELD_LAYER));
        this.kiteShieldEntityModel = new KiteShieldEntityModel(Minecraft.getInstance().getEntityModels().bakeLayer(MiddleEarthClient.KITE_SHIELD_LAYER));
        this.roundShieldEntityModel = new RoundShieldEntityModel(Minecraft.getInstance().getEntityModels().bakeLayer(MiddleEarthClient.ROUND_SHIELD_LAYER));
        this.heldBannerEntityModel = new HeldBannerEntityModel(Minecraft.getInstance().getEntityModels().bakeLayer(MiddleEarthClient.HELD_BANNER_LAYER));

        if (stack.getItem() instanceof CustomBannerShieldItem) {
            BannerPatternLayers bannerPatternsComponent = (BannerPatternLayers)stack.getOrDefault(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY);
            DyeColor dyeColor2 = (DyeColor)stack.get(DataComponents.BASE_COLOR);
            boolean bl = !bannerPatternsComponent.layers().isEmpty() || dyeColor2 != null;
            matrices.pushPose();
            matrices.scale(1.0F, -1.0F, -1.0F);

            if (stack.is(ModWeaponItems.HEATER_SHIELD)){
                Material spriteIdentifier = MEModelLoader.heaterShieldBase(bl);
                VertexConsumer vertexConsumer = spriteIdentifier.sprite().wrap(ItemRenderer.getFoilBufferDirect(vertexConsumers, this.heaterShieldEntityModel.renderType(spriteIdentifier.atlasLocation()), true, stack.hasFoil()));
                this.heaterShieldEntityModel.getHandle().render(matrices, vertexConsumer, light, overlay);
                if (bl) {
                    renderCanvas(matrices, vertexConsumers, light, overlay, this.heaterShieldEntityModel.getPlate(), spriteIdentifier, false, (DyeColor) Objects.requireNonNullElse(dyeColor2, DyeColor.WHITE), bannerPatternsComponent, stack.hasFoil(), stack);
                } else {
                    this.heaterShieldEntityModel.getPlate().render(matrices, vertexConsumer, light, overlay);
                }

                matrices.popPose();
            } else if (stack.is(ModWeaponItems.KITE_SHIELD)){
                Material spriteIdentifier = MEModelLoader.kiteShieldBase(bl);
                VertexConsumer vertexConsumer = spriteIdentifier.sprite().wrap(ItemRenderer.getFoilBufferDirect(vertexConsumers, this.kiteShieldEntityModel.renderType(spriteIdentifier.atlasLocation()), true, stack.hasFoil()));
                this.kiteShieldEntityModel.getHandle().render(matrices, vertexConsumer, light, overlay);
                if (bl) {
                    renderCanvas(matrices, vertexConsumers, light, overlay, this.kiteShieldEntityModel.getPlate(), spriteIdentifier, false, (DyeColor) Objects.requireNonNullElse(dyeColor2, DyeColor.WHITE), bannerPatternsComponent, stack.hasFoil(), stack);
                } else {
                    this.kiteShieldEntityModel.getPlate().render(matrices, vertexConsumer, light, overlay);
                }

                matrices.popPose();
            } else if (stack.is(ModWeaponItems.ROUND_SHIELD)){
                Material spriteIdentifier = MEModelLoader.roundShieldBase(bl);
                VertexConsumer vertexConsumer = spriteIdentifier.sprite().wrap(ItemRenderer.getFoilBufferDirect(vertexConsumers, this.roundShieldEntityModel.renderType(spriteIdentifier.atlasLocation()), true, stack.hasFoil()));
                this.roundShieldEntityModel.getHandle().render(matrices, vertexConsumer, light, overlay);
                if (bl) {
                    renderCanvas(matrices, vertexConsumers, light, overlay, this.roundShieldEntityModel.getPlate(), spriteIdentifier, false, (DyeColor) Objects.requireNonNullElse(dyeColor2, DyeColor.WHITE), bannerPatternsComponent, stack.hasFoil(), stack);
                } else {
                    this.roundShieldEntityModel.getPlate().render(matrices, vertexConsumer, light, overlay);
                }

                matrices.popPose();
            }
        }

        if (stack.getItem() instanceof HeldBannerItem){
            BannerPatternLayers bannerPatternsComponent = (BannerPatternLayers)stack.getOrDefault(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY);
            DyeColor dyeColor2 = (DyeColor)stack.get(DataComponents.BASE_COLOR);
            boolean bl = !bannerPatternsComponent.layers().isEmpty() || dyeColor2 != null;
            matrices.pushPose();
            matrices.scale(1.0F, -1.0F, -1.0F);

            if (stack.is(ModWeaponItems.HELD_BANNER)){
                Material spriteIdentifier = ModelBakery.BANNER_BASE;
                VertexConsumer vertexConsumer = spriteIdentifier.sprite().wrap(ItemRenderer.getFoilBufferDirect(vertexConsumers, this.heldBannerEntityModel.renderType(spriteIdentifier.atlasLocation()), true, stack.hasFoil()));
                this.heldBannerEntityModel.getPole().render(matrices, vertexConsumer, light, overlay);
                if (bl) {
                    renderCanvas(matrices, vertexConsumers, light, overlay, this.heldBannerEntityModel.getBanner(), spriteIdentifier, false, (DyeColor) Objects.requireNonNullElse(dyeColor2, DyeColor.WHITE), bannerPatternsComponent, stack.hasFoil(), stack);
                } else {
                    this.heldBannerEntityModel.getBanner().render(matrices, vertexConsumer, light, overlay);
                }

                matrices.popPose();
            }
        }
    }

    public static void renderCanvas(PoseStack matrices, MultiBufferSource vertexConsumers, int light, int overlay, ModelPart canvas, Material baseSprite, boolean isBanner, DyeColor color, BannerPatternLayers patterns, boolean glint, ItemStack stack) {
        canvas.render(matrices, baseSprite.buffer(vertexConsumers, RenderType::entitySolid, glint), light, overlay);
        if (stack.is(ModWeaponItems.HEATER_SHIELD)){
            renderLayer(matrices, vertexConsumers, light, overlay, canvas, isBanner ? Sheets.BANNER_BASE : ModTexturedRenderLayers.heaterShieldBase(), color);
        } else if (stack.is(ModWeaponItems.KITE_SHIELD)){
            renderLayer(matrices, vertexConsumers, light, overlay, canvas, isBanner ? Sheets.BANNER_BASE : ModTexturedRenderLayers.kiteShieldBase(), color);
        }else if (stack.is(ModWeaponItems.ROUND_SHIELD)){
            renderLayer(matrices, vertexConsumers, light, overlay, canvas, isBanner ? Sheets.BANNER_BASE : ModTexturedRenderLayers.roundShieldBase(), color);
        }else if (stack.is(ModWeaponItems.HELD_BANNER)){
            renderLayer(matrices, vertexConsumers, light, overlay, canvas, Sheets.BANNER_BASE, color);
        }
        for(int i = 0; i < 16 && i < patterns.layers().size(); ++i) {
            BannerPatternLayers.Layer layer = (BannerPatternLayers.Layer)patterns.layers().get(i);
            Material spriteIdentifier = isBanner ? Sheets.getBannerMaterial(layer.pattern()) : ModTexturedRenderLayers.getRoundShieldPatternTextureId(layer.pattern());
            if (stack.is(ModWeaponItems.HEATER_SHIELD)){
                spriteIdentifier = ModTexturedRenderLayers.getHeaterShieldPatternTextureId(layer.pattern());
            } else if (stack.is(ModWeaponItems.KITE_SHIELD)){
                spriteIdentifier = ModTexturedRenderLayers.getKiteShieldPatternTextureId(layer.pattern());
            }else if (stack.is(ModWeaponItems.ROUND_SHIELD)){
                spriteIdentifier = ModTexturedRenderLayers.getRoundShieldPatternTextureId(layer.pattern());
            } else if (stack.is(ModWeaponItems.HELD_BANNER)){
                spriteIdentifier = Sheets.getBannerMaterial(layer.pattern());
            }
            renderLayer(matrices, vertexConsumers, light, overlay, canvas, spriteIdentifier, layer.color());
        }
    }



    private static void renderLayer(PoseStack matrices, MultiBufferSource vertexConsumers, int light, int overlay, ModelPart canvas, Material textureId, DyeColor color) {
        int i = color.getTextureDiffuseColor();
        canvas.render(matrices, textureId.buffer(vertexConsumers, RenderType::entityNoOutline), light, overlay, i);
    }
}
