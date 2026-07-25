package net.jukoz.me.client.model.equipment.chest.capes;

import net.jukoz.me.client.model.equipment.chest.ChestplateAddonModel;
import net.jukoz.me.utils.ToRad;
import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class CloakCapeModel<T extends LivingEntity>  extends ChestplateAddonModel<T> {
    private static final float MAX_ANGLE_CLOAK = 80f;
    private static final float SPEED_MULTIPLIER_CLOAK = 1.8f;
    private final ModelPart cape;
    private final ModelPart capeLow;
    private final ModelPart capeShoulder;
    private final ModelPart rightArmShoulderCape;
    private final ModelPart leftArmShoulderCape;

    public CloakCapeModel(ModelPart root) {
        super(root);
        this.cape = root.getChild("body").getChild("cape");
        this.capeLow = root.getChild("body").getChild("cape").getChild("cape_low");
        this.capeShoulder = root.getChild("body").getChild("cape_shoulder");
        this.rightArmShoulderCape = root.getChild("right_arm").getChild("right_arm_shoulder_cape");
        this.leftArmShoulderCape = root.getChild("left_arm").getChild("left_arm_shoulder_cape");
    }

    @Override
    public void setupAnim(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.cape.getAllParts().forEach(ModelPart::resetPose);
        Vec3 velocity = entity.getDeltaMovement();
        double sqrVel = velocity.lengthSqr();
        double speed = (sqrVel * 0.35f) + Math.sqrt(Math.abs(limbDistance)) * 0.4f;
        double degree;

        if (entity.isCrouching()) {
            this.cape.z = 0.0f;
            this.cape.y = 0.0f;
            degree = 5f + (speed * (MAX_ANGLE_CLOAK / 2));
        } else {
            this.cape.z = 0;
            this.cape.y = 0.0f;
            degree = (MAX_ANGLE_CLOAK * speed);
        }
        degree = Math.max(0.0F, degree);
        degree = Math.min(MAX_ANGLE_CLOAK, degree);

        double result = entity.getLookAngle().dot(velocity);

        if(result > 0) {
            this.cape.xRot = ToRad.ex(degree);
        }
    }

}
