package net.jukoz.me.entity.goals;

import net.jukoz.me.MiddleEarth;
import net.jukoz.me.entity.pheasant.PheasantEntity;
import net.jukoz.me.item.ModDataComponentTypes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public class PheasantStartledGoal extends Goal {
    PheasantEntity pheasant;
    Player player;
    Vec3 vec3d = Vec3.ZERO;
    Vec3 fleeDir;
    @Nullable
    Path fleePath;
    final PathNavigation fleeingEntityNavigation;
    boolean flying;

    public PheasantStartledGoal(PheasantEntity pheasant) {
        this.setFlags(EnumSet.of(Flag.MOVE));
        this.pheasant = pheasant;
        this.fleeingEntityNavigation = pheasant.getNavigation();
    }

    @Override
    public boolean canUse() {
        player = pheasant.level().getNearestPlayer(pheasant, 7.0d);
        if(player == null) {
            return false;
        }

        vec3d = DefaultRandomPos.getPosAway(this.pheasant, 4, 3, this.player.position());
        if (vec3d == null) {
            return false;
        }
        if (this.player.distanceToSqr(vec3d.x, vec3d.y, vec3d.z) < this.player.distanceToSqr(this.pheasant)) {
            return false;
        }

        this.fleePath = this.fleeingEntityNavigation.createPath(vec3d.x, vec3d.y, vec3d.z, 0);
        if(this.fleePath == null) {
            return false;
        }

        boolean wearingCloak =  player.getItemBySlot(EquipmentSlot.CHEST).get(ModDataComponentTypes.CAPE_DATA) != null
                && player.getItemBySlot(EquipmentSlot.HEAD).get(ModDataComponentTypes.HOOD_DATA) != null;

        return !(player == null || wearingCloak || player.isShiftKeyDown() || !pheasant.onGround());
    }

    @Override
    public void start() {
        fleeDir = vec3d.subtract(pheasant.position()).multiply(1,0,1).normalize();
        this.flying = false;
        this.fleeingEntityNavigation.moveTo(this.fleePath, 1.6d);
    }

    @Override
    public void tick() {
        if(this.fleeingEntityNavigation.isDone() && !this.flying) {
            this.pheasant.setDeltaMovement(fleeDir.multiply(1.7, 0, 1.7).add(0, 0.5, 0));
            this.pheasant.setYRot((float) Math.toDegrees(Math.atan2(-fleeDir.x, fleeDir.z)));
            this.flying = true;
        }
    }

    @Override
    public boolean canContinueToUse() {
        return !this.flying;
    }

    @Override
    public void stop() {
        this.player = null;
        this.flying = false;
    }
}