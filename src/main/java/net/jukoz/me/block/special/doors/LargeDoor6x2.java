package net.jukoz.me.block.special.doors;

import net.jukoz.me.block.special.LargeDoorBlock;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class LargeDoor6x2 extends LargeDoorBlock {
    public static final IntegerProperty PART = IntegerProperty.create("part", 0, 11);

    public LargeDoor6x2(Properties settings) {
        super(settings);
        this.doorHeight = 6;
        this.doorWidth  = 2;
    }

    @Override
    public IntegerProperty getPart() {
        return PART;
    }
}