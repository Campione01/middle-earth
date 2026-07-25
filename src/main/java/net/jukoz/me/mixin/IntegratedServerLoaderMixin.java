package net.jukoz.me.mixin;

import net.minecraft.client.gui.screens.worldselection.WorldOpenFlows;
import net.minecraft.world.level.storage.LevelStorageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(WorldOpenFlows.class)
public abstract class IntegratedServerLoaderMixin {
    @ModifyVariable(
            method = "confirmWorldCreation(Lnet/minecraft/client/Minecraft;Lnet/minecraft/client/gui/screens/worldselection/CreateWorldScreen;Lcom/mojang/serialization/Lifecycle;Ljava/lang/Runnable;Z)V",
            at = @At("HEAD"),
            index = 4,
            argsOnly = true)
    private static boolean removeAdviceOnCreation(boolean original) {
        return true;
    }

    @Redirect(
            method = "openWorldCheckWorldStemCompatibility",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screens/worldselection/WorldOpenFlows;askForBackup(Lnet/minecraft/world/level/storage/LevelStorageSource$LevelStorageAccess;ZLjava/lang/Runnable;Ljava/lang/Runnable;)V"))
    private void skipExperimentalBackupPrompt(WorldOpenFlows instance, LevelStorageSource.LevelStorageAccess session,
                                              boolean customized, Runnable callback, Runnable onCancel) {
        callback.run();
    }
}
