package net.jukoz.me.datageneration;
import net.jukoz.me.utils.LoggerUtil;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;


public class VariantsModelProvider {
    public static ResourceLocation getInventoryModelIdentifierVariant(Item item) {
        return BuiltInRegistries.ITEM.getKey(item).withPrefix("item/").withSuffix("_inventory");
    }

    public static ResourceLocation getInventoryLongbowModelIdentifierVariant(Item item, int stage) {
        return BuiltInRegistries.ITEM.getKey(item).withPrefix("item/").withSuffix("_pulling_" + stage + "_inventory");
    }

    public static ResourceLocation getHotModelIdentifierVariant(Item item) {
        return BuiltInRegistries.ITEM.getKey(item).withPrefix("item/").withSuffix("_hot");
    }

    public static ResourceLocation getPullModelIdentifierVariant(Item item) {
        return BuiltInRegistries.ITEM.getKey(item).withPrefix("item/").withSuffix("_pull");
    }

    public static ResourceLocation getInventoryModelBrokenItem(Item item) {
        return BuiltInRegistries.ITEM.getKey(item).withPrefix("item/").withSuffix("_broken_inventory");
    }
    public static ResourceLocation getInventoryModelGlowingItem(Item item) {
        return BuiltInRegistries.ITEM.getKey(item).withPrefix("item/").withSuffix("_glowing_inventory");
    }
    public static ResourceLocation getPullLongbowModel(Item item, float progress) {
        String predicate;
        if(progress < 0.65f) {
            predicate = "0";
        } else if(progress < 0.9f) {
            predicate = "1";
        }else {
            predicate = "2";
        }
        return BuiltInRegistries.ITEM.getKey(item).withPrefix("item/").withSuffix("_pulling_" + predicate + "_inventory");
    }
}
