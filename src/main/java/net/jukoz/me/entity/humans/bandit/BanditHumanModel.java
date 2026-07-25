package net.jukoz.me.entity.humans.bandit;

import net.jukoz.me.compat.neoforge.dist.EnvType;
import net.jukoz.me.compat.neoforge.dist.Environment;
import net.jukoz.me.item.items.weapons.ranged.CustomBowWeaponItem;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

@Environment(value= EnvType.CLIENT)
public class BanditHumanModel<T extends Mob>
        extends HumanoidModel<T> {

    public BanditHumanModel(ModelPart root) {
        super(root);
    }

    @Override
    public void prepareMobModel(T mobEntity, float f, float g, float h) {
        this.rightArmPose = HumanoidModel.ArmPose.EMPTY;
        this.leftArmPose = HumanoidModel.ArmPose.EMPTY;
        ItemStack itemStack = ((LivingEntity)mobEntity).getItemInHand(InteractionHand.MAIN_HAND);
        if ((itemStack.is(Items.BOW) || itemStack.getItem() instanceof CustomBowWeaponItem) && ((Mob)mobEntity).isAggressive()) {
            if (((Mob)mobEntity).getMainArm() == HumanoidArm.RIGHT) {
                this.rightArmPose = HumanoidModel.ArmPose.BOW_AND_ARROW;
            } else {
                this.leftArmPose = HumanoidModel.ArmPose.BOW_AND_ARROW;
            }
        }
        super.prepareMobModel(mobEntity, f, g, h);
    }

    /*@Override
    public void setupAnim(T mobEntity, float f, float g, float h, float i, float j) {
        super.setupAnim(mobEntity, f, g, h, i, j);
        ItemStack itemStack = ((LivingEntity)mobEntity).getMainHandStack();
        GondorHumanEntity.State state = ((GondorHumanEntity)mobEntity).getState();

        if(state == GondorHumanEntity.State.ATTACKING) {
            // Walk added
            this.leftArm.pitch = MathHelper.cos(f * 0.6662f) * 2.0f * g * 0.5f;
            this.leftArm.yaw = 0.0f;
            this.leftArm.roll = 0.0f;

            this.rightArm.pitch = -0.9f + MathHelper.cos(f * 0.6662f + (float)Math.PI) * 2.0f * g * 0.5f;
            this.rightArm.yaw = 0.0f;
            this.rightArm.roll = 0.0f;
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
