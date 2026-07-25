package net.jukoz.me.world.map;

import net.jukoz.me.utils.resources.FileUtils;
import net.jukoz.me.world.biomes.surface.MapBasedCustomBiome;
import net.jukoz.me.world.biomes.surface.MapBasedBiomePool;
import org.joml.Vector2i;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class MiddleEarthMapRuntime {
    private static MiddleEarthMapRuntime single_instance = null;
    private final ConcurrentMap<Long, MiddleEarthMapRegion> regions;
    private BufferedImage edgeImage;
    private final MiddleEarthMapUtils middleEarthMapUtils;

    public static synchronized MiddleEarthMapRuntime getInstance()
    {
        if (single_instance == null)
            single_instance = new MiddleEarthMapRuntime();

        return single_instance;
    }

    public MiddleEarthMapRuntime() {
        regions = new ConcurrentHashMap<>();
        edgeImage = MiddleEarthMapGeneration.getEdgeHeightImage();
        if(edgeImage == null) {
            String path = MiddleEarthMapConfigs.BASE_HEIGHT_PATH + MiddleEarthMapConfigs.BASE_EDGE_IMAGE_NAME;
            BufferedImage image = FileUtils.getInstance().getRunImage(path);
            if(image != null){
                edgeImage = image;
            }
        }
        middleEarthMapUtils = MiddleEarthMapUtils.getInstance();
    }

    public MapBasedCustomBiome getBiome(int posX, int posZ) {
        if(!middleEarthMapUtils.isWorldCoordinateInBorder(posX, posZ)) return MapBasedBiomePool.defaultBiome;

        MiddleEarthMapRegion region = getRegionToUse(middleEarthMapUtils.getRegionByWorldCoordinate(posX, posZ));
        if(region == null) return MapBasedBiomePool.defaultBiome;

        return region.getBiome(getImageCoordinates(posX, posZ));
    }

    private final Color OUT_OF_BORDER_COLOR = new Color(35, 48, 55);
    public Color getHeight(int posX, int posZ) {
        if(!middleEarthMapUtils.isWorldCoordinateInBorder(posX, posZ)){
            return OUT_OF_BORDER_COLOR;
        }

        MiddleEarthMapRegion region = getRegionToUse(middleEarthMapUtils.getRegionByWorldCoordinate(posX, posZ));
        if(region == null) return OUT_OF_BORDER_COLOR;

        Vector2i coords = getImageCoordinates(posX, posZ);
        return region.getHeightColor(coords);
    }

    public float getEdge(int posX, int posZ) {
        if(!middleEarthMapUtils.isWorldCoordinateInBorder(posX, posZ)) return -0.67f;

        if(edgeImage == null) return 1.001f;

        int imageX = clamp((int)((float)posX / middleEarthMapUtils.ratioX), 0, edgeImage.getWidth() - 1);
        int imageZ = clamp((int)((float)posZ / middleEarthMapUtils.ratioZ), 0, edgeImage.getHeight() - 1);
        Color edgeColor = new Color(edgeImage.getRGB(imageX, imageZ));

        float average = (float)(edgeColor.getRed() + edgeColor.getGreen() +  edgeColor.getBlue()) / 3;

        if(average > 0.01f) {
            average *= 12;
        }

        return Math.max(0, 1 - (average / 255));
    }

    private Vector2i getImageCoordinates(int posX, int posZ){
        return new Vector2i(
            Math.floorMod(Math.floorDiv(posX, MiddleEarthMapConfigs.PIXEL_WEIGHT), MiddleEarthMapConfigs.REGION_SIZE),
            Math.floorMod(Math.floorDiv(posZ, MiddleEarthMapConfigs.PIXEL_WEIGHT), MiddleEarthMapConfigs.REGION_SIZE)
        );
    }

    private MiddleEarthMapRegion getRegionToUse(Vector2i regionCoordinate){
        long key = regionKey(regionCoordinate);
        return regions.computeIfAbsent(key, ignored -> new MiddleEarthMapRegion(new Vector2i(regionCoordinate)));
    }

    private static long regionKey(Vector2i regionCoordinate) {
        return ((long) regionCoordinate.x << 32) ^ (regionCoordinate.y & 0xffffffffL);
    }

    private static int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }
}
