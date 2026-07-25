package net.jukoz.me.entity.beasts.warg.features;

import net.jukoz.me.MiddleEarth;
import net.jukoz.me.entity.beasts.warg.WargEntity;
import net.jukoz.me.entity.beasts.warg.WargModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;

public class WargEyesFeatureRenderer extends EyesLayer<WargEntity, WargModel> {
    private static final RenderType SKIN = RenderType.eyes(ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "textures/entities/warg/feature/warg_eyes.png"));
    public WargEyesFeatureRenderer(RenderLayerParent<WargEntity, WargModel> featureRendererContext) {
        super(featureRendererContext);
    }

    @Override
    public RenderType renderType() {
        return SKIN;
    }
}
