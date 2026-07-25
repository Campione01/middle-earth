package net.jukoz.me.entity.goals;

import net.jukoz.me.entity.NpcEntity;
import net.jukoz.me.resources.StateSaverAndLoader;
import net.jukoz.me.resources.datas.Disposition;
import net.jukoz.me.resources.persistent_datas.PlayerData;
import net.jukoz.me.utils.LoggerUtil;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;

public class NpcTargetPlayerGoal extends NearestAttackableTargetGoal<Player> {
    NpcEntity mob;

    public NpcTargetPlayerGoal(NpcEntity mob) {
        super(mob, Player.class, true);
        this.mob = mob;
    }

    @Override
    public boolean canUse() {
        return super.canUse() && canContinue();
    }

    @Override
    public boolean canContinueToUse() {
        return super.canContinueToUse() && canContinue();
    }

    private boolean canContinue(){
        if(this.mob.getTarget() != null && this.mob.getTarget().isAlive()){
            if(this.mob.getTarget() instanceof Player player){
                if(player.isCreative() || player.distanceTo(this.mob) > 50)
                    return false;
                return true;
            }
        }
        return false;
    }
}
