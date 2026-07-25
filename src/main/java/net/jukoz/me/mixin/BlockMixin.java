package net.jukoz.me.mixin;

import net.jukoz.me.MiddleEarth;
import net.jukoz.me.compat.neoforge.api.registry.LandPathNodeTypesRegistry;
import net.jukoz.me.entity.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.level.pathfinder.WalkNodeEvaluator;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public abstract class BlockMixin {
    @Unique private static final float RANDOM_FLYING_BLOCK = 0.34f;
    @Unique private static final float DISCARD_DISTANCE = 3;
    @Unique private static final float FORCE = 80;
    @Unique private static final float VERTICAL_MULTIPLIER = 10;
    @Unique private static final ResourceLocation FIRE_OF_ORTHANC_BLOCK_ID =
            ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "fire_of_orthanc");

    @Shadow protected abstract Block asBlock();

    @Nullable
    public PathType getBlockPathType(BlockState state, BlockGetter level, BlockPos pos, @Nullable Mob mob) {
        PathType registered = LandPathNodeTypesRegistry.getBlockPathType(state.getBlock());
        if (registered != null) {
            return registered;
        }
        return state.getBlock() == Blocks.LAVA ? PathType.LAVA : state.isBurning(level, pos) ? PathType.DAMAGE_FIRE : null;
    }

    @Nullable
    public PathType getAdjacentBlockPathType(BlockState state, BlockGetter level, BlockPos pos, @Nullable Mob mob, PathType originalType) {
        PathType registered = LandPathNodeTypesRegistry.getNeighborPathType(state.getBlock());
        if (registered != null) {
            return registered;
        }
        if (state.is(Blocks.SWEET_BERRY_BUSH)) {
            return PathType.DANGER_OTHER;
        }
        return WalkNodeEvaluator.isBurningBlock(state) ? PathType.DANGER_FIRE : null;
    }

    @Inject(at = @At("HEAD"), method = "wasExploded")
    private void onDestroyedByExplosion(Level world, BlockPos pos, Explosion explosion, CallbackInfo ci) {
        if(!explosion.interactsWithBlocks()) return;
        Block block = this.asBlock();

        if(explosion.getDirectSourceEntity() == null || explosion.getDirectSourceEntity().getType() == ModEntities.FIRE_OF_ORTHANC) {
            if(block != Blocks.TNT && !FIRE_OF_ORTHANC_BLOCK_ID.equals(BuiltInRegistries.BLOCK.getKey(block))) {
                if(Math.random() < RANDOM_FLYING_BLOCK) {
                    float distance = (float) pos.distToCenterSqr(explosion.center());
                    if(distance < explosion.radius() / DISCARD_DISTANCE) return;

                    FallingBlockEntity fallingBlockEntity = FallingBlockEntity.fall(world, pos, block.defaultBlockState());
                    fallingBlockEntity.dropItem = false;
                    fallingBlockEntity.disableDrop();
                    Vec3 velocity = pos.getCenter().subtract(explosion.center()).normalize();
                    float factor = FORCE / distance;
                    velocity.scale(factor);
                    velocity.add(0, VERTICAL_MULTIPLIER * factor, 0);
                    fallingBlockEntity.setDeltaMovement(velocity);
                }
            }
        }
    }
}
