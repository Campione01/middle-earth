package net.jukoz.me.resources.datas.factions.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.jukoz.me.resources.datas.factions.Faction;
import net.jukoz.me.utils.IdentifierUtil;
import net.jukoz.me.world.dimension.ModDimensions;
import net.jukoz.me.world.map.MiddleEarthMapConfigs;
import net.jukoz.me.world.map.MiddleEarthMapUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector2d;
import org.joml.Vector3d;
import org.joml.Vector3i;

public class SpawnData {
    public static final Codec<SpawnData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("id").forGetter(SpawnData::getIdentifierValue),
            Vec3.CODEC.fieldOf("coordinates").forGetter(SpawnData::getCoordinates),
            Codec.BOOL.fieldOf("dynamic").forGetter(SpawnData::isDynamic))
            .apply(instance, SpawnData::new));

    private ResourceLocation identifier;
    private Vec3 coordinates;
    private boolean isDynamic = false;

    /**
     * Constructor from codec
     * @param identifier
     * @param coordinates
     * @param isDynamic
     */
    public SpawnData(String identifier, Vec3 coordinates, Boolean isDynamic) {
        this.identifier = IdentifierUtil.getIdentifierFromString(identifier);
        this.isDynamic = isDynamic;

        if(isDynamic)
            this.coordinates = new Vec3(coordinates.x, 0, coordinates.z);
        else
            this.coordinates = coordinates;
    }

    public SpawnData(ResourceLocation identifier, Vector2d coordinate){
        this(identifier, new Vec3(coordinate.x, 0, coordinate.y));
        isDynamic = true;
    }

    public SpawnData(ResourceLocation identifier, Vec3 coordinate){
        this.identifier = identifier;
        this.coordinates = coordinate;
    }

    public static SpawnData deserialize(CompoundTag compound) {
        CompoundTag coordinateCompound = compound.getCompound("coordinates");
        double x = coordinateCompound.getDouble("x");
        double y = coordinateCompound.getDouble("y");
        double z = coordinateCompound.getDouble("z");
        Vec3 coordinate = new Vec3(x, y, z);
        boolean isDynamic = compound.getBoolean("dynamic");

        return new SpawnData(compound.getString("id"), coordinate, isDynamic);
    }

    public static CompoundTag serialize(SpawnData spawnData) {
        CompoundTag serializedCompound = new CompoundTag();

        CompoundTag coordinateCompound = new CompoundTag();
        coordinateCompound.putDouble("x", spawnData.coordinates.x);
        coordinateCompound.putDouble("y", spawnData.coordinates.y);
        coordinateCompound.putDouble("z", spawnData.coordinates.z);

        serializedCompound.put("coordinates", coordinateCompound);
        serializedCompound.putString("id", spawnData.getIdentifierValue());
        serializedCompound.putBoolean("dynamic", spawnData.isDynamic());
        return serializedCompound;
    }

    public ResourceLocation getIdentifier(){
        return identifier;
    }

    private String getIdentifierValue() {
        return this.identifier.toString();
    }

    public Vec3 getCoordinates() {
        return coordinates;
    }

    public Vector3i getWorldCoordinates() {
        int ratio = (MiddleEarthMapConfigs.FULL_MAP_SIZE / MiddleEarthMapConfigs.REGION_SIZE);
        Vector3i worldCoordinates = new Vector3i((int) coordinates.x, (int) coordinates.y, (int) coordinates.z);
        if(isDynamic) {
            worldCoordinates.x = worldCoordinates.x * ratio;
            worldCoordinates.z = worldCoordinates.z * ratio;
        }
        return worldCoordinates;
    }

    public boolean isDynamic() {
        return isDynamic;
    }

    public BlockPos getBlockPos() {
        BlockPos blockPos = new BlockPos((int) coordinates.x, (int) coordinates.y, (int) coordinates.z);
        if(isDynamic){
            Vector2d coords = MiddleEarthMapUtils.getInstance().getWorldCoordinateFromInitialMap(coordinates.x, coordinates.z);
            Vector3i spawnCoordinates =  ModDimensions.getDimensionHeight((int) coords.x, (int) coords.y);
            blockPos = new BlockPos(spawnCoordinates.x, spawnCoordinates.y, spawnCoordinates.z);
        }
        return blockPos;
    }
}
