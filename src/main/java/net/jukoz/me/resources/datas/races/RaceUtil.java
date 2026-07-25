package net.jukoz.me.resources.datas.races;

import net.jukoz.me.config.ModServerConfigs;
import net.jukoz.me.resources.StateSaverAndLoader;
import net.jukoz.me.resources.datas.RaceType;
import net.jukoz.me.resources.datas.races.data.AttributeData;
import net.jukoz.me.resources.persistent_datas.PlayerData;
import net.jukoz.me.utils.LoggerUtil;
import net.jukoz.me.world.dimension.ModDimensions;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import java.util.HashMap;
import java.util.Optional;

public class RaceUtil {

    public static void updateRace(Player player, Race race, boolean shouldHeal){
        PlayerData data = StateSaverAndLoader.getPlayerState(player);

        boolean havePreviousRace = data.getRace() != null;
        boolean raceExists = race != null;

        // [RESET]
        if(havePreviousRace){
            RaceLookup.getRace(player.level(), data.getRace()).reverseAttributes(player);
            data.setRace(null);
        }

        reset(player);

        // [SET]
        if(raceExists){
            race.applyAttributes(player);
            data.setRace(race.getId());
        }

        if(shouldHeal)
            player.heal(player.getMaxHealth());
    }

    public static Race getRace(Player player){
        PlayerData data = StateSaverAndLoader.getPlayerStateReadOnly(player);
        if(data == null) return null;
        return data.getRace(player.level());
    }

    public static RaceType getRaceType(Player player){
        Race race = getRace(player);
        if(race != null)
            return race.getRaceType();
        else
            return null;
    }

    public static void initializeRace(ServerPlayer player) {
        Race race = getRace(player);
        updateRace(player, race, false);
    }

    public static void reset(Player player) {
        AttributeData.reset(player);
    }
}
