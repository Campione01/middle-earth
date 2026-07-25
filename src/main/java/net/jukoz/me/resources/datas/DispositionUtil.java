package net.jukoz.me.resources.datas;

import net.jukoz.me.resources.StateSaverAndLoader;
import net.jukoz.me.resources.datas.races.Race;
import net.jukoz.me.resources.persistent_datas.PlayerData;
import net.minecraft.world.entity.player.Player;

public class DispositionUtil {
    public static Disposition getDisposition(Player player){
        PlayerData data = StateSaverAndLoader.getPlayerStateReadOnly(player);
        if(data == null) return null;
        return data.getCurrentDisposition();
    }
}
