package net.jukoz.me.compat.neoforge.api.blockrenderlayer.v1;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;

public final class BlockRenderLayerMap {
    public static final BlockRenderLayerMap INSTANCE = new BlockRenderLayerMap();

    private BlockRenderLayerMap() {
    }

    public void putBlock(Block block, RenderType renderType) {
        ItemBlockRenderTypes.setRenderLayer(block, renderType);
    }
}
