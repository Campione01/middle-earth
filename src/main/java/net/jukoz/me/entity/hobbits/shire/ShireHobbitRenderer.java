package net.jukoz.me.entity.hobbits.shire;

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
import net.minecraft.resources.ResourceLocation;
import java.util.Map;

@Environment(value= EnvType.CLIENT)
public class ShireHobbitRenderer extends HumanoidMobRenderer<ShireHobbitEntity, ShireHobbitModel<ShireHobbitEntity>> {
    private static final String PATH = "textures/entities/hobbits/shire/";
    private static final float SIZE = 0.55f;

    public ShireHobbitRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new ShireHobbitModel<>(ctx.bakeLayer(ModEntityModelLayers.HOBBIT)), 0.5f);
        this.addLayer(new HumanoidArmorLayer<>(this, new ShireHobbitModel(ctx.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)), new ShireHobbitModel(ctx.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR)), ctx.getModelManager()));
    }

    public static final Map<ShireHobbitVariant, String> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(ShireHobbitVariant.class), (resourceLocation) -> {
                resourceLocation.put(ShireHobbitVariant.GINGER_WHITE_BLUE,
                        PATH + "hobbit1.png");
                resourceLocation.put(ShireHobbitVariant.DARK_BLONDE_CYAN_BROWN,
                        PATH + "hobbit2.png");
                resourceLocation.put(ShireHobbitVariant.GINGER_GREEN_BROWN,
                        PATH + "hobbit3.png");
                resourceLocation.put(ShireHobbitVariant.SAM,
                        PATH + "sam.png");
                resourceLocation.put(ShireHobbitVariant.FRODO,
                        PATH + "frodo.png");
            });

    @Override
    public ResourceLocation getTextureLocation(ShireHobbitEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, LOCATION_BY_VARIANT.get(entity.getVariant()));
    }

    public void render(ShireHobbitEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {

        poseStack.scale(SIZE, SIZE, SIZE);
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
