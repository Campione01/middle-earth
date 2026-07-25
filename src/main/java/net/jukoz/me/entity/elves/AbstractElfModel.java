package net.jukoz.me.entity.elves;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.AgeableMob;


public abstract class AbstractElfModel<T extends AgeableMob> extends HumanoidModel<T> {
    protected AbstractElfModel(ModelPart pRoot) {
        super(pRoot);
    }
}
