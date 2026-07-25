package net.jukoz.me.entity.goals;

import net.jukoz.me.entity.beasts.AbstractBeastEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import java.util.EnumSet;

public class BeastSitGoal extends Goal {
    private final AbstractBeastEntity mob;

    public BeastSitGoal(AbstractBeastEntity mob) {
        this.mob = mob;
        this.setFlags(EnumSet.of(Flag.JUMP, Flag.MOVE));
    }

    @Override
    public boolean canContinueToUse() {
        return this.mob.isSitting();
    }

    @Override
    public boolean canUse() {
        if (!this.mob.isTamed()) {
            return false;
        }
        if (this.mob.isInWaterOrBubble()) {
            return false;
        }
        if (!this.mob.onGround()) {
            return false;
        }
        LivingEntity livingEntity = this.mob.getOwner();
        if (livingEntity == null) {
            return true;
        }
        if (this.mob.distanceToSqr(livingEntity) < 144.0 && livingEntity.getLastHurtByMob() != null) {
            return false;
        }
        return this.mob.isSitting();
    }

    @Override
    public void start() {
        this.mob.getNavigation().stop();
    }

    @Override
    public void stop() {
    }
}
