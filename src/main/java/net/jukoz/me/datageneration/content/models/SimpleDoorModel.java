package net.jukoz.me.datageneration.content.models;

import net.jukoz.me.block.ModBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import java.util.ArrayList;
import java.util.List;

public class SimpleDoorModel {
    public record Door(Block block, Block door) {}

    public static List<Door> doors = new ArrayList<>() {
        {
            add(new Door(Blocks.IRON_BLOCK, ModBlocks.TREATED_STEEL_DOOR));
        }
    };
}
