package net.jukoz.me.entity.spider;


import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import net.jukoz.me.MiddleEarth;
import net.jukoz.me.entity.model.ModEntityModelLayers;
import net.minecraft.Util;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import java.util.Map;

public class MirkwoodSpiderRenderer extends MobRenderer<MirkwoodSpiderEntity, MirkwoodSpiderModel> {
    private static final String PATH = "textures/entities/spiders/";

    public MirkwoodSpiderRenderer(EntityRendererProvider.Context context) {
        this(context, 0.45F, ModEntityModelLayers.SPIDER);
    }

    protected MirkwoodSpiderRenderer(EntityRendererProvider.Context ctx, float shadowRadius, ModelLayerLocation layer) {
        super(ctx, new MirkwoodSpiderModel(ctx.bakeLayer(layer)), shadowRadius);
    }

    @Override
    public ResourceLocation getTextureLocation(MirkwoodSpiderEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, LOCATION_BY_VARIANT.get(entity.getVariant()));
    }

    public static final Map<MirkwoodSpiderVariants, String> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(MirkwoodSpiderVariants.class), (resourceLocation) -> {
                resourceLocation.put(MirkwoodSpiderVariants.BLACK,
                        PATH + "spider1.png");
                resourceLocation.put(MirkwoodSpiderVariants.BROWN,
                        PATH + "spider2.png");
                resourceLocation.put(MirkwoodSpiderVariants.DARK_GREEN,
                        PATH + "spider3.png");
            });

    @Override
    public void render(MirkwoodSpiderEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {
        if(entity.tickCount > MirkwoodSpiderEntity.ADULT_AGE){
            poseStack.scale(1,1,1);
        } else {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
