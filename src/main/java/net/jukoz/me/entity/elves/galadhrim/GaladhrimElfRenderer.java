package net.jukoz.me.entity.elves.galadhrim;

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
public class GaladhrimElfRenderer extends HumanoidMobRenderer<GaladhrimElfEntity, GaladhrimElfModel<GaladhrimElfEntity>> {
    private static final String PATH = "textures/entities/elves/galadhrim/";

    public GaladhrimElfRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new GaladhrimElfModel<>(ctx.bakeLayer(ModEntityModelLayers.ELF)), 0.5f);
        this.addLayer(new HumanoidArmorLayer<>(this, new GaladhrimElfModel(ctx.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)), new GaladhrimElfModel(ctx.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR)), ctx.getModelManager()));
    }

    public static final Map<GaladhrimElfVariant, String> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(GaladhrimElfVariant.class), (resourceLocation) -> {
                resourceLocation.put(GaladhrimElfVariant.SILVER_TEAL_BLUE,
                          PATH + "elf1.png");
                resourceLocation.put(GaladhrimElfVariant.SILVER_CYAN_BLUE,
                        PATH + "elf2.png");
                resourceLocation.put(GaladhrimElfVariant.SILVER_LIME_BLUE,
                        PATH + "elf3.png");
                resourceLocation.put(GaladhrimElfVariant.BLOND_GREEN_BLUE,
                        PATH + "elf4.png");
            });

    @Override
    public ResourceLocation getTextureLocation(GaladhrimElfEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, LOCATION_BY_VARIANT.get(entity.getVariant()));
    }

    @Override
    public void render(GaladhrimElfEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {

        poseStack.scale(0.95f, 1.0f, 0.95f);
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
