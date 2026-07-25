package net.jukoz.me.entity.deer;

import net.jukoz.me.MiddleEarth;
import net.jukoz.me.entity.model.ModEntityModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class DeerRenderer extends MobRenderer<DeerEntity, DeerModel> {
    private static final String PATH = "textures/entities/deer/";

    public DeerRenderer(EntityRendererProvider.Context context) {
        super(context, new DeerModel(context.bakeLayer(ModEntityModelLayers.DEER)), 0.7f);
    }

    @Override
    public ResourceLocation getTextureLocation(DeerEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, PATH + "deer1.png");
    }


}
