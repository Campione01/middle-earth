package net.jukoz.me.mixin;

import net.jukoz.me.compat.neoforge.api.tag.convention.v1.ConventionalItemTags;
import net.jukoz.me.MiddleEarth;
import net.jukoz.me.utils.LoggerUtil;
import net.jukoz.me.world.dimension.ModDimensions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.animal.Pig;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Mixin(Pig.class)
public class PigEntityMixin {
    @Inject(at = @At(value = "HEAD"), method = "thunderHit", cancellable = true)
    private void thunderHit(ServerLevel world, LightningBolt lightning, CallbackInfo callBackInfo) {
        if(ModDimensions.isInMiddleEarth(world)) {
            callBackInfo.cancel();
        }
    }
}
