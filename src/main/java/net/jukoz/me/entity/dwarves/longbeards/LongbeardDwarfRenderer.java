package net.jukoz.me.entity.dwarves.longbeards;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import net.jukoz.me.compat.neoforge.dist.EnvType;
import net.jukoz.me.compat.neoforge.dist.Environment;
import net.jukoz.me.MiddleEarth;
import net.jukoz.me.entity.model.ModEntityModelLayers;
import net.minecraft.Util;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlot;
import java.util.Map;

@Environment(value= EnvType.CLIENT)
public class LongbeardDwarfRenderer extends HumanoidMobRenderer<LongbeardDwarfEntity, LongbeardDwarfModel<LongbeardDwarfEntity>> {
    private static final String PATH = "textures/entities/dwarves/durin/";
    private static final float SIZE = 0.78f;

    public LongbeardDwarfRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new LongbeardDwarfModel(ctx.bakeLayer(ModEntityModelLayers.DWARF)), 0.5f);
        this.addLayer(new HumanoidArmorLayer<>(this, new LongbeardDwarfModel(ctx.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)), new LongbeardDwarfModel(ctx.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR)), ctx.getModelManager()));
    }

    public static final Map<LongbeardDwarfVariant, String> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(LongbeardDwarfVariant.class), (resourceLocation) -> {
                resourceLocation.put(LongbeardDwarfVariant.GINGER,
                        PATH + "dwarf1.png");
                resourceLocation.put(LongbeardDwarfVariant.BLACK,
                        PATH + "dwarf2.png");
                resourceLocation.put(LongbeardDwarfVariant.OLD,
                        PATH + "dwarf3.png");
                resourceLocation.put(LongbeardDwarfVariant.DARK_BLONDE,
                        PATH + "dwarf4.png");
                resourceLocation.put(LongbeardDwarfVariant.BLACK_BALD,
                        PATH + "dwarf5.png");
                resourceLocation.put(LongbeardDwarfVariant.GINGER_NORI,
                        PATH + "dwarf6.png");
                resourceLocation.put(LongbeardDwarfVariant.BLACK_NORI,
                        PATH + "dwarf7.png");
                resourceLocation.put(LongbeardDwarfVariant.DARK_BLONDE_NORI,
                        PATH + "dwarf8.png");
            });

    @Override
    public ResourceLocation getTextureLocation(LongbeardDwarfEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, LOCATION_BY_VARIANT.get(entity.getVariant()));
    }

    @Override
    public void render(LongbeardDwarfEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {

        poseStack.scale(SIZE, SIZE, SIZE);
        if (entity.getItemBySlot(EquipmentSlot.HEAD).is(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "helmet_hides_dwarf_beard")))){
            this.model.head.getChild("beard").visible = false;
            this.model.head.getChild("beard2").visible = false;
            this.model.head.getChild("beard_tip").visible = false;

            this.model.head.getChild("nori_beard_left").visible = false;
            this.model.head.getChild("nori_beard_center").visible = false;
            this.model.head.getChild("nori_beard_right").visible = false;
        } else {
            this.model.head.getChild("beard").visible = true;
            this.model.head.getChild("beard2").visible = true;
            this.model.head.getChild("beard_tip").visible = true;

            this.model.head.getChild("nori_beard_left").visible = true;
            this.model.head.getChild("nori_beard_center").visible = true;
            this.model.head.getChild("nori_beard_right").visible = true;
        }
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
