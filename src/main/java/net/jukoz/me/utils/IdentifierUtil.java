package net.jukoz.me.utils;

import net.jukoz.me.MiddleEarth;
import net.minecraft.resources.ResourceLocation;

public class IdentifierUtil {
    public static ResourceLocation getIdentifierFromString(String id){
        if(id.contains(":") && id.split(":").length == 2){
            return ResourceLocation.fromNamespaceAndPath(id.split(":")[0], id.split(":")[1]);
        } else {
            return ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, id);
        }
    }
}
