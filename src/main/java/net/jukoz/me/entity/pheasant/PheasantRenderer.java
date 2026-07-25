package net.jukoz.me.entity.pheasant;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import net.jukoz.me.MiddleEarth;
import net.jukoz.me.entity.model.ModEntityModelLayers;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import java.util.Map;

public class PheasantRenderer extends MobRenderer<PheasantEntity, PheasantModel> {
    private static final String PATH = "textures/entities/pheasants/";
    private static final float SIZE = 1f;

    public PheasantRenderer(EntityRendererProvider.Context context) {
        super(context, new PheasantModel(context.bakeLayer(ModEntityModelLayers.PHEASANT)), 0.35F);

    }

    public static final Map<PheasantVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(PheasantVariant.class), (map) -> {
                map.put(PheasantVariant.MALE,
                        ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, PATH + "pheasant1m.png"));
                map.put(PheasantVariant.FEMALE,
                        ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, PATH + "pheasant1f.png"));
            });

    @Override
    public ResourceLocation getTextureLocation(PheasantEntity entity) {
        return LOCATION_BY_VARIANT.get(entity.getVariant());
    }

    public void render(PheasantEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {

        if(entity.isBaby()) {
            float initialBabySize = (SIZE / 2);
            float size = initialBabySize + ((SIZE - initialBabySize) / 24000) * entity.tickCount;
            poseStack.scale(size, size, size);
        } else {
            poseStack.scale(SIZE, SIZE, SIZE);
        }
        poseStack.scale(1, 1, 1);
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
