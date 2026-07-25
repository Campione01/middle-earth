package net.jukoz.me.datageneration.content.tags;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class Planks {
    public static List<Block> planks = new ArrayList<>() {
        {

        }
    };

    public static List<Item> getItemPlanks() {
        ArrayList<Item> newList = new ArrayList<>();
        for (Block block : planks) {
            newList.add(block.asItem());
        }
        return newList;
    }
}
