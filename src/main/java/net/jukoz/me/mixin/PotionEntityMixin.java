package net.jukoz.me.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.jukoz.me.block.special.fireBlocks.AbstractToggleableFireBlock;
import net.jukoz.me.block.special.torches.METorchBlock;
import net.jukoz.me.block.special.torches.MEWallTorchBlock;
import net.minecraft.world.level.block.*;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ThrownPotion.class)
public abstract class PotionEntityMixin extends ThrowableItemProjectile {

    public PotionEntityMixin(EntityType<? extends ThrowableItemProjectile> entityType, Level world) {
        super(entityType, world);
    }

    @Inject(at = @At("TAIL"), method = "dowseFire")
    private void onDestroyedByExplosion(BlockPos pos, CallbackInfo ci, @Local BlockState blockState) {

        if (AbstractToggleableFireBlock.isLitFireBlock(blockState) || METorchBlock.isLitTorch(blockState) || MEWallTorchBlock.isLitWallTorch(blockState)){
            this.level().levelEvent((Player)null, 1009, pos, 0);
            AbstractToggleableFireBlock.extinguish(this.getOwner(), this.level(), pos, blockState);
            this.level().setBlockAndUpdate(pos, (BlockState)blockState.setValue(CampfireBlock.LIT, false));
        }
    }
}
