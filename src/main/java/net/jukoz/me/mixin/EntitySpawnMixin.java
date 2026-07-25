package net.jukoz.me.mixin;

import com.google.common.collect.ImmutableList;
import net.jukoz.me.world.spawners.SpawnerNPCs;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.CustomSpawner;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.ArrayList;
import java.util.List;

@Mixin(ServerLevel.class)
public class EntitySpawnMixin {

    // We replace the first argument of type "List<Spawner>" (which is the 10th argument of the constructor)
    // By our own list that contains the vanilla list + the SpawnerNPCs
    @ModifyVariable(method = "<init>", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private static List<CustomSpawner> ServerWorld(List<CustomSpawner> spawners) {
        List<CustomSpawner> newSpawners = new ArrayList<>(spawners);
        newSpawners.add(new SpawnerNPCs());
        return ImmutableList.copyOf(newSpawners);
    }
}
