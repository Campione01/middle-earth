package net.jukoz.me.item.items;

import net.jukoz.me.resources.datas.RaceType;
import net.jukoz.me.resources.datas.races.RaceUtil;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class OrcishFoodItem extends Item {
    public OrcishFoodItem(Properties settings) {
        super(settings);
    }

    @Override
    public void releaseUsing(ItemStack stack, Level world, LivingEntity user, int remainingUseTicks) {
        super.releaseUsing(stack, world, user, remainingUseTicks);
        if(world.isClientSide) return;

        if(user instanceof Player playerEntity) {
            RaceType raceType = RaceUtil.getRaceType(playerEntity);
            if(raceType != RaceType.ORC && raceType != RaceType.URUK) {
                user.addEffect(new MobEffectInstance(MobEffects.HUNGER, 20 * 30, 1));
                user.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 20 * 10, 0));

            }
        }
    }
}
