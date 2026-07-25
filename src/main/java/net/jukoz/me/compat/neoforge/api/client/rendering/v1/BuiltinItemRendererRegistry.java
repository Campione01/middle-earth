package net.jukoz.me.compat.neoforge.api.client.rendering.v1;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

import java.util.LinkedHashMap;
import java.util.Map;

public final class BuiltinItemRendererRegistry {
    public static final BuiltinItemRendererRegistry INSTANCE = new BuiltinItemRendererRegistry();
    private static final Map<Item, DynamicItemRenderer> RENDERERS = new LinkedHashMap<>();

    private BuiltinItemRendererRegistry() {
    }

    public void register(Item item, DynamicItemRenderer renderer) {
        RENDERERS.put(item, renderer);
    }

    public static void registerClientExtensions(RegisterClientExtensionsEvent event) {
        RENDERERS.keySet().forEach(item -> event.registerItem(new Extension(), item));
    }

    private static final class Extension implements IClientItemExtensions {
        private BlockEntityWithoutLevelRenderer renderer;

        @Override
        public BlockEntityWithoutLevelRenderer getCustomRenderer() {
            if (renderer == null) {
                renderer = new RendererAdapter();
            }
            return renderer;
        }
    }

    private static final class RendererAdapter extends BlockEntityWithoutLevelRenderer {
        private RendererAdapter() {
            super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
        }

        @Override
        public void renderByItem(ItemStack stack, ItemDisplayContext displayContext, PoseStack poseStack,
                                 MultiBufferSource buffer, int packedLight, int packedOverlay) {
            DynamicItemRenderer renderer = RENDERERS.get(stack.getItem());
            if (renderer != null) {
                renderer.render(stack, displayContext, poseStack, buffer, packedLight, packedOverlay);
                return;
            }
            super.renderByItem(stack, displayContext, poseStack, buffer, packedLight, packedOverlay);
        }
    }

    @FunctionalInterface
    public interface DynamicItemRenderer {
        void render(ItemStack stack, ItemDisplayContext mode, PoseStack matrices,
                    MultiBufferSource vertexConsumers, int light, int overlay);
    }
}
