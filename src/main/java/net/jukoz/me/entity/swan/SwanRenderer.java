package net.jukoz.me.entity.swan;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import net.jukoz.me.MiddleEarth;
import net.jukoz.me.entity.model.ModEntityModelLayers;
import net.jukoz.me.entity.pheasant.PheasantEntity;
import net.jukoz.me.entity.pheasant.PheasantModel;
import net.jukoz.me.entity.pheasant.PheasantVariant;
import net.minecraft.Util;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import java.util.Map;

public class SwanRenderer extends MobRenderer<SwanEntity, SwanModel> {
    private static final String PATH = "textures/entities/swan/";
    private static final float SIZE = 1f;

    public SwanRenderer(EntityRendererProvider.Context context) {
        this(context, 0.35F, ModEntityModelLayers.SWAN);
    }

    protected SwanRenderer(EntityRendererProvider.Context ctx, float shadowRadius, ModelLayerLocation layer) {
        super(ctx, new SwanModel(ctx.bakeLayer(layer)), shadowRadius);
    }

    protected float getLyingAngle(SwanEntity swanEntity) {
        return 180.0F;
    }

    public static final Map<SwanVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(SwanVariant.class), (map) -> {
                map.put(SwanVariant.WHITE,
                        ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, PATH + "white_swan.png"));
                map.put(SwanVariant.BLACK,
                        ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, PATH + "black_swan.png"));
                map.put(SwanVariant.TRUMPETER,
                        ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, PATH + "trumpeter_swan.png"));
                map.put(SwanVariant.WHOOPER,
                        ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, PATH + "whooper_swan.png"));
            });

    @Override
    public ResourceLocation getTextureLocation(SwanEntity entity) {
        return LOCATION_BY_VARIANT.get(entity.getVariant());
    }

    @Override
    public void render(SwanEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {
        if(entity.isBaby()) {
            float initialBabySize = (SIZE / 2);
            float size = initialBabySize + ((SIZE - initialBabySize) / 24000) * entity.tickCount;
            poseStack.scale(size, size, size);
        } else {
            poseStack.scale(SIZE, SIZE, SIZE);
        }
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
