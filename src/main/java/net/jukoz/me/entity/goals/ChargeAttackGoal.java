package net.jukoz.me.entity.goals;

import net.jukoz.me.entity.beasts.AbstractBeastEntity;
import net.jukoz.me.resources.StateSaverAndLoader;
import net.jukoz.me.resources.datas.Disposition;
import net.jukoz.me.resources.persistent_datas.PlayerData;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.pathfinder.Node;
import net.minecraft.world.level.pathfinder.Path;

public class ChargeAttackGoal extends Goal {
    private AbstractBeastEntity mob;
    private final int MAX_COOLDOWN;
    private int checkCanNavigateCooldown;
    private Disposition beastDisposition;

    public ChargeAttackGoal(AbstractBeastEntity mob, Disposition beastDisposition, int maxCooldown) {
        this.mob = mob;
        this.beastDisposition = beastDisposition;
        this.MAX_COOLDOWN = maxCooldown;
    }

    @Override
    public boolean canUse() {
        if(this.mob.getTarget() != null && this.mob.getTarget() instanceof Player player) {
            PlayerData data = StateSaverAndLoader.getPlayerStateReadOnly(player);
            Disposition playerDisposition = data == null ? null : data.getCurrentDisposition();
            if(playerDisposition == null)
                return true;
            if(playerDisposition == beastDisposition){
                return false;
            }
        }

        return this.mob.getChargeTimeout() == 0 &&
                (mob.getTarget() != null) &&
                this.mob.getRandom().nextInt(ChargeAttackGoal.reducedTickDelay(40)) == 0 &&
                canNavigateToEntity(this.mob.getTarget()) &&
                this.mob.canCharge();
    }

    @Override
    public void start() {
        this.mob.setCharging(true);
        this.mob.setChargeTimeout(this.MAX_COOLDOWN);
        this.checkCanNavigateCooldown = 0;
    }

    @Override
    public void tick() {
        super.tick();
        if(this.checkCanNavigateCooldown > 0) {
            --this.checkCanNavigateCooldown;
        }

    }

    private boolean canNavigateToEntity(LivingEntity entity) {
        int j;
        this.checkCanNavigateCooldown = Goal.reducedTickDelay(10 + this.mob.getRandom().nextInt(5));
        Path path = this.mob.getNavigation().createPath(entity, 0);
        if (path == null) {
            return false;
        }
        Node pathNode = path.getEndNode();
        if (pathNode == null) {
            return false;
        }
        int i = pathNode.x - entity.getBlockX();
        return (double)(i * i + (j = pathNode.z - entity.getBlockZ()) * j) <= 2.25;
    }
}
