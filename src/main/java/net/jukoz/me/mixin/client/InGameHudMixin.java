package net.jukoz.me.mixin.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.jukoz.me.MiddleEarth;
import net.jukoz.me.statusEffects.ModStatusEffects;
import net.jukoz.me.utils.HallucinationData;
import net.jukoz.me.utils.IEntityDataSaver;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public abstract class InGameHudMixin {
    @Unique
    private static final ResourceLocation HALLUCINATION_OUTLINE = ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "textures/misc/hallucination_outline.png");

    @Inject(method = "renderCameraOverlays", at = @At("TAIL"))
    private void renderHallucinationOverlay(GuiGraphics context, DeltaTracker tickCounter, CallbackInfo ci) {
        Player player = Minecraft.getInstance().player;
        if(player != null && player.hasEffect(ModStatusEffects.HALLUCINATION)) {
            float intensity = (float) HallucinationData.readHallucination((IEntityDataSaver) player) / 100f;
            renderOverlayTexture(context, HALLUCINATION_OUTLINE, intensity);
        }
    }

    @Unique
    private static void renderOverlayTexture(GuiGraphics context, ResourceLocation texture, float opacity) {
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.enableBlend();
        context.setColor(1.0F, 1.0F, 1.0F, opacity);
        context.blit(texture, 0, 0, -90, 0.0F, 0.0F, context.guiWidth(), context.guiHeight(), context.guiWidth(), context.guiHeight());
        RenderSystem.disableBlend();
        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();
        context.setColor(1.0F, 1.0F, 1.0F, 1.0F);
    }
}

