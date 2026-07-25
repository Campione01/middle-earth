package net.jukoz.me.entity.hobbits.shire;

import net.jukoz.me.compat.neoforge.dist.EnvType;
import net.jukoz.me.compat.neoforge.dist.Environment;
import net.jukoz.me.entity.projectile.pebble.PebbleEntity;
import net.jukoz.me.item.ModResourceItems;
import net.jukoz.me.item.ModToolItems;
import net.jukoz.me.item.ModWeaponItems;
import net.jukoz.me.item.items.weapons.ranged.CustomBowWeaponItem;
import net.jukoz.me.item.items.weapons.ranged.CustomLongbowWeaponItem;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

@Environment(value= EnvType.CLIENT)
public class ShireHobbitModel<T extends Mob>
        extends HumanoidModel<T> {

    public ShireHobbitModel(ModelPart root) {
        super(root);
    }

    @Override
    public void prepareMobModel(T mobEntity, float f, float g, float h) {
        this.rightArmPose = HumanoidModel.ArmPose.EMPTY;
        this.leftArmPose = HumanoidModel.ArmPose.EMPTY;
        ItemStack itemStack = (mobEntity).getItemInHand(InteractionHand.MAIN_HAND);
        if ((itemStack.is(Items.BOW) || itemStack.is(ModResourceItems.PEBBLE) || itemStack.getItem() instanceof CustomLongbowWeaponItem || itemStack.getItem() instanceof CustomBowWeaponItem) && (mobEntity).isAggressive()) {
            if (mobEntity.getMainArm() == HumanoidArm.RIGHT) {
                this.rightArmPose = HumanoidModel.ArmPose.BOW_AND_ARROW;
            } else {
                this.leftArmPose = HumanoidModel.ArmPose.BOW_AND_ARROW;
            }
        }
        super.prepareMobModel(mobEntity, f, g, h);
    }

    @Override
    public void setupAnim(T livingEntity, float f, float g, float h, float i, float j) {
        super.setupAnim(livingEntity, f, g, h, i, j);
        if(riding){
            head.y = 10f;
            hat.y = 10f;
            body.y = 10f;
            leftArm.y = 12f;
            rightArm.y = 12f;
            leftLeg.y = 22f;
            rightLeg.y = 22f;
        }
    }

    /*@Override
    public void setupAnim(T mobEntity, float f, float g, float h, float i, float j) {
        super.setupAnim(mobEntity, f, g, h, i, j);
        ItemStack itemStack = ((LivingEntity)mobEntity).getMainHandStack();
        if (((MobEntity)mobEntity).isAttacking() && (itemStack.isEmpty() || !itemStack.isOf(Items.BOW))) {
            float k = MathHelper.sin(this.handSwingProgress * (float)Math.PI);
            float l = MathHelper.sin((1.0f - (1.0f - this.handSwingProgress) * (1.0f - this.handSwingProgress)) * (float)Math.PI);
            this.rightArm.roll = 0.0f;
            this.leftArm.roll = 0.0f;
            this.rightArm.yaw = -(0.1f - k * 0.6f);
            this.leftArm.yaw = 0.1f - k * 0.6f;
            this.rightArm.pitch = -1.5707964f;
            this.leftArm.pitch = -1.5707964f;
            this.rightArm.pitch -= k * 1.2f - l * 0.4f;
            this.leftArm.pitch -= k * 1.2f - l * 0.4f;
            CrossbowPosing.swingArms(this.rightArm, this.leftArm, h);
        }
    }

    @Override
    public void setArmAngle(Arm arm, MatrixStack matrices) {
        float f = arm == Arm.RIGHT ? 1.0f : -1.0f;
        ModelPart modelPart = this.getArm(arm);
        modelPart.pivotX += f;
        modelPart.rotate(matrices);
        modelPart.pivotX -= f;
    }*/
}
