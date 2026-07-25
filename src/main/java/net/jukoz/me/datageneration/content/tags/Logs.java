package net.jukoz.me.datageneration.content.tags;

import net.jukoz.me.block.ModNatureBlocks;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import java.util.ArrayList;
import java.util.List;

public class Logs {
    public static List<Block> logs = new ArrayList<>() {
        {
            add(ModNatureBlocks.PINE_BRANCHES);
        }
    };

    public static List<Item> getItemPlanks() {
        ArrayList<Item> newList = new ArrayList<>();
        for (Block block : logs) {
            newList.add(block.asItem());
        }
        return newList;
    }
}
