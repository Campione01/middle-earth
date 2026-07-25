package net.jukoz.me.world.spawners;

import net.jukoz.me.entity.NpcEntity;
import net.jukoz.me.world.dimension.ModDimensions;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.CustomSpawner;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import java.util.ArrayList;
import java.util.List;

public class SpawnerNPCs implements CustomSpawner {
    private static final int SPAWN_COUNT_CAP = 32;
    private static final int SPAWN_DISTANCE = 32;
    private static final int SPAWN_RAND = 8;
    private static final int MAX_SPAWN_RAD = SPAWN_DISTANCE + SPAWN_RAND + 8;
    private static final int BASE_COOLDOWN = 30;
    private static final int COOLDOWN_RANGE = 8;
    private int cooldown = BASE_COOLDOWN + COOLDOWN_RANGE;

    @Override
    public int tick(ServerLevel world, boolean spawnMonsters, boolean spawnAnimals) {
        if (!spawnMonsters || !ModDimensions.isInMiddleEarth(world)) {
            return 0;
        }
        RandomSource random = world.random;
        --this.cooldown;
        if (this.cooldown > 0) {
            return 0;
        }

        this.cooldown += (BASE_COOLDOWN + random.nextInt(COOLDOWN_RANGE)) * 20;

        int i = 0;
        for (Player playerEntity : world.players()) {
            BlockState blockState;
            if (playerEntity.isSpectator()) continue;
            BlockPos blockPos = playerEntity.blockPosition();
            BlockPos targetBlockPos = new BlockPos(blockPos);
            DifficultyInstance localDifficulty = world.getCurrentDifficultyAt(blockPos);

            Vec3 offset = new Vec3(MAX_SPAWN_RAD, 0, MAX_SPAWN_RAD);
            Vec3 pos1 = blockPos.getCenter().add(offset).add(0, 321 - playerEntity.position().y, 0);
            Vec3 pos2 = blockPos.getCenter().subtract(offset).add(0, -63 - playerEntity.position().y, 0);
            int size = world.getEntitiesOfClass(NpcEntity.class, new AABB(pos1, pos2), (entity) -> true).size();
            if(size <= SPAWN_COUNT_CAP) {
                double randomAngle = Math.toRadians(random.nextInt(360));
                int distance = SPAWN_DISTANCE + random.nextInt(SPAWN_RAND);

                int x = targetBlockPos.getX() + (int)(distance * Math.cos(randomAngle));
                int z = targetBlockPos.getZ() + (int)(distance * Math.sin(randomAngle));
                targetBlockPos = new BlockPos(x, ModDimensions.getDimensionHeight(x, z).y, z);

                if(world.getBiome(targetBlockPos).unwrapKey().isEmpty()) continue;
                ResourceKey<Biome> biomeRegistryKey = world.getBiome(targetBlockPos).unwrapKey().get();

                SpawnGroupData entityData = null;
                List<EntitySpawningSettings> biomeSpawnSettings = ModEntitySpawning.getSpawnsAt(biomeRegistryKey);
                if(biomeSpawnSettings == null) continue;
                ArrayList<EntitySpawningSettings> spawningSettings = new ArrayList<>(biomeSpawnSettings);
                if(world.isDay()) {
                    spawningSettings.removeIf(EntitySpawningSettings::isNightOnly);
                }
                if(spawningSettings.size() == 0) continue;
                int totalWeight = 0;
                for(EntitySpawningSettings settings : spawningSettings) {
                    totalWeight += settings.getWeight();
                }
                int nextWeight = random.nextInt(totalWeight) + 1;
                totalWeight = 0;
                EntitySpawningSettings entitySpawningSettings = spawningSettings.get(0);
                for(EntitySpawningSettings settings : spawningSettings) {
                    if(totalWeight + settings.getWeight() >= nextWeight) {
                        entitySpawningSettings = settings;
                        break;
                    }
                    totalWeight += settings.getWeight();
                }
                int randomCount = random.nextInt(1 + entitySpawningSettings.getMaxCount() - entitySpawningSettings.getMinCount());
                int entityCount = entitySpawningSettings.getMinCount() + randomCount;

                blockState = world.getBlockState(new BlockPos(x, targetBlockPos.getY() - 1, z));
                if(!canSpawnAt(world.getChunkForCollisions(Math.floorDiv(targetBlockPos.getX(), 16), Math.floorDiv(targetBlockPos.getZ(), 16)),
                        targetBlockPos.subtract(new Vec3i(0, 1, 0)), entitySpawningSettings.getEntity(), blockState)) continue;

                for (int m = 0; m < entityCount; ++m) {
                    PathfinderMob entity = (PathfinderMob) entitySpawningSettings.getEntity().create(world);
                    if (entity == null) continue;
                    entity.moveTo(targetBlockPos, 0.0f, 0.0f);
                    entityData = entity.finalizeSpawn(world, localDifficulty, MobSpawnType.NATURAL, entityData);
                    world.addFreshEntityWithPassengers(entity);
                    ++i;
                }
            }
        }
        return i;
    }

    public static int getHighestYAtXZ(Level world, int x, int z) {
        return world.getChunk(new BlockPos(x, 0, z)).getHeight(Heightmap.Types.WORLD_SURFACE_WG, x, z);
    }

    private static boolean canSpawnAt(BlockGetter world, BlockPos pos, EntityType type, BlockState blockState) {
        if(!blockState.isValidSpawn(world, pos, type)) return false;
        return (!blockState.is(Blocks.WATER) && !blockState.is(Blocks.LAVA) && !blockState.is(BlockTags.LOGS));
    }
}
