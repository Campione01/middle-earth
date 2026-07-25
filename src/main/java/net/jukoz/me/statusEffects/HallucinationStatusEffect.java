package net.jukoz.me.statusEffects;

import net.jukoz.me.utils.HallucinationData;
import net.jukoz.me.utils.IEntityDataSaver;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import java.util.Map;

public class HallucinationStatusEffect extends MobEffect {
    public HallucinationStatusEffect(MobEffectCategory statusEffectCategory, int i) {
        super(statusEffectCategory, i);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        if(entity instanceof Player){
            Map<Holder<MobEffect>, MobEffectInstance> map = entity.getActiveEffectsMap();
            int ticksLeft = map.get(ModStatusEffects.HALLUCINATION).getDuration();
            if(ticksLeft != -1 && ticksLeft < HallucinationData.STOPPING_TICK)
                HallucinationData.addHallucination((IEntityDataSaver) entity, -2);
            else{
                HallucinationData.addHallucination((IEntityDataSaver) entity, 2);
            }
        }

        return true;
    }

    public void stop(LivingEntity entity){
        HallucinationData.stopHallucination((IEntityDataSaver) entity);
    }


}
