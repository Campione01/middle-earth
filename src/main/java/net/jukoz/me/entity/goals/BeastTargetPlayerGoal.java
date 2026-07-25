package net.jukoz.me.entity.goals;

import net.jukoz.me.entity.NpcEntity;
import net.jukoz.me.entity.beasts.AbstractBeastEntity;
import net.jukoz.me.resources.StateSaverAndLoader;
import net.jukoz.me.resources.datas.Disposition;
import net.jukoz.me.resources.persistent_datas.PlayerData;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;

public class BeastTargetPlayerGoal extends NearestAttackableTargetGoal<Player> {
    AbstractBeastEntity mob;
    Disposition beastDisposition;

    public BeastTargetPlayerGoal(AbstractBeastEntity mob, Disposition beastDisposition) {
        super(mob, Player.class, true);
        this.mob = mob;
        this.beastDisposition = beastDisposition;
    }

    @Override
    public boolean canUse() {
        return super.canUse() && canTargetMob();
    }

    @Override
    public boolean canContinueToUse() {
        return super.canContinueToUse() && canTargetMob();
    }

    private boolean canTargetMob(){
        Player player = this.mob.level().getNearestPlayer(this.targetConditions, this.mob, this.mob.getX(), this.mob.getEyeY(), this.mob.getZ());;
        if(player == null || mob.level().getDifficulty() == Difficulty.PEACEFUL || mob.isTamed() || player == mob.getOwner()){
            return false;
        }
        if(beastDisposition != null){
            PlayerData data = StateSaverAndLoader.getPlayerStateReadOnly(player);

            if(data == null)
                return false;
            
            Disposition playerDisposition = data.getCurrentDisposition();
            return playerDisposition != beastDisposition;
        }
        return true;
    }
}
