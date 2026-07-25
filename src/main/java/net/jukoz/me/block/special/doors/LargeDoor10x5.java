package net.jukoz.me.block.special.doors;

import net.jukoz.me.block.special.LargeDoorBlock;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class LargeDoor10x5 extends LargeDoorBlock {
    public static final IntegerProperty PART = IntegerProperty.create("part", 0, 49);

    public LargeDoor10x5(Properties settings) {
        super(settings);
        this.doorHeight = 10;
        this.doorWidth  = 5;
    }

    @Override
    public IntegerProperty getPart() {
        return PART;
    }
}