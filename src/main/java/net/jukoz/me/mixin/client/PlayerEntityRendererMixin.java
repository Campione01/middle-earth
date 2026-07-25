package net.jukoz.me.mixin.client;

import net.jukoz.me.item.items.weapons.ranged.CustomCrossbowWeaponItem;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(PlayerRenderer.class)
public class PlayerEntityRendererMixin {

    @Inject(at = @At("TAIL"), method = "getArmPose", cancellable = true)
    private static void positionLeftArm(AbstractClientPlayer player, InteractionHand hand, CallbackInfoReturnable<HumanoidModel.ArmPose> cir) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (itemStack.getItem() instanceof CustomCrossbowWeaponItem && CrossbowItem.isCharged(itemStack)){
            cir.setReturnValue(HumanoidModel.ArmPose.CROSSBOW_HOLD);
        }
    }
}
