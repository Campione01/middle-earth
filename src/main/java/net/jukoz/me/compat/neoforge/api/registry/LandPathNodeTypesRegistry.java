package net.jukoz.me.compat.neoforge.api.registry;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.pathfinder.PathType;

import java.util.LinkedHashMap;
import java.util.Map;

public final class LandPathNodeTypesRegistry {
    private static final Map<Block, PathType> BLOCK_PATH_TYPES = new LinkedHashMap<>();
    private static final Map<Block, PathType> NEIGHBOR_PATH_TYPES = new LinkedHashMap<>();

    private LandPathNodeTypesRegistry() {
    }

    public static void register(Block block, PathType landPathNodeType, PathType neighborPathNodeType) {
        if (landPathNodeType != null) {
            BLOCK_PATH_TYPES.put(block, landPathNodeType);
        }
        if (neighborPathNodeType != null) {
            NEIGHBOR_PATH_TYPES.put(block, neighborPathNodeType);
        }
    }

    public static PathType getBlockPathType(Block block) {
        return BLOCK_PATH_TYPES.get(block);
    }

    public static PathType getNeighborPathType(Block block) {
        return NEIGHBOR_PATH_TYPES.get(block);
    }
}
