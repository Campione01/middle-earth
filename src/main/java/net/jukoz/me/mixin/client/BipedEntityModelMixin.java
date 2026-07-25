package net.jukoz.me.mixin.client;

import net.jukoz.me.item.ModDecorativeItems;
import net.jukoz.me.item.ModWeaponItems;
import net.jukoz.me.item.items.weapons.ReachWeaponItem;
import net.jukoz.me.item.utils.ModWeaponTypes;
import net.jukoz.me.utils.IEntityDataSaver;
import net.jukoz.me.utils.PlayerMovementData;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(HumanoidModel.class)
public class BipedEntityModelMixin {
    @Shadow public HumanoidModel.ArmPose leftArmPose;

    @Shadow public HumanoidModel.ArmPose rightArmPose;

    @Shadow @Final public ModelPart rightArm;

    @Shadow @Final public ModelPart leftArm;
    private final static float VERTICAL_ANGLE = -1.4f;

    @Inject(at = @At("TAIL"), method = "poseRightArm")
    private void positionRightArm(LivingEntity entity, CallbackInfo ci) {
        List<ItemStack> handItems = new ArrayList<>();
        entity.getHandSlots().forEach(handItems::add);
        if(handItems.get(0) != null) {
            tryItemAnimation(handItems.get(0), entity, true);
        }
    }

    @Inject(at = @At("TAIL"), method = "poseLeftArm")
    private void positionLeftArm(LivingEntity entity, CallbackInfo ci) {
        List<ItemStack> handItems = new ArrayList<>();
        entity.getHandSlots().forEach(handItems::add);
        if(handItems.get(1) != null) {
            tryItemAnimation(handItems.get(1), entity, false);
        }
    }

    private void tryItemAnimation(ItemStack itemStack, LivingEntity entity, boolean rightHand) {
        if(itemStack.getItem().equals(ModDecorativeItems.FIRE_OF_ORTHANC)) {
            float pitch = this.rightArm.xRot * 0.25F - 0.5F;
            this.rightArm.xRot = pitch;
            this.rightArm.yRot = 0.0F;
            this.leftArm.xRot = pitch;
            this.leftArm.yRot = 0.0F;
        } else if(itemStack.getItem() instanceof ReachWeaponItem && (((ReachWeaponItem) itemStack.getItem()).type.twoHanded)) {
            float pitch = -1.15f;
            this.rightArm.xRot = pitch;
            this.leftArm.xRot = pitch - 0.2f;
            this.rightArm.yRot = -0.35f;
            this.leftArm.yRot = 0.8f;
        } else if((itemStack.getItem() instanceof ReachWeaponItem && (((ReachWeaponItem) itemStack.getItem()).type == ModWeaponTypes.SPEAR))) {
            if(!entity.isUsingItem() && entity instanceof Player playerEntity) {
                int afkTime = PlayerMovementData.readAFK((IEntityDataSaver) playerEntity);
                if(afkTime > 60) {
                    if (rightHand) this.rightArm.xRot = VERTICAL_ANGLE;
                    else this.leftArm.xRot = VERTICAL_ANGLE;
                }
            } else if(entity instanceof Mob mob) {
                if (mob.isNoAi()) {
                    if (rightHand) this.rightArm.xRot = VERTICAL_ANGLE;
                    else this.leftArm.xRot = VERTICAL_ANGLE;
                }
            }
        } else if (itemStack.getItem() == ModWeaponItems.HELD_BANNER) {
            if (rightHand) this.rightArm.xRot = VERTICAL_ANGLE;
            else this.leftArm.xRot = VERTICAL_ANGLE;
        }else if (itemStack.getItem() == ModDecorativeItems.TORCH_OF_ORTHANC) {
            if(rightHand) {
                this.rightArm.xRot = VERTICAL_ANGLE;
            } else {
                this.leftArm.xRot = VERTICAL_ANGLE;
            }
        }
    }
}
