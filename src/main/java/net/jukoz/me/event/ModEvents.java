package net.jukoz.me.event;

import net.jukoz.me.config.ModServerConfigs;
import net.jukoz.me.resources.StateSaverAndLoader;
import net.jukoz.me.resources.datas.races.RaceUtil;
import net.jukoz.me.resources.persistent_datas.PlayerData;
import net.jukoz.me.world.chunkgen.map.MiddleEarthHeightMap;
import net.jukoz.me.world.dimension.ModDimensions;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

public class ModEvents {
    public static void register(){
        NeoForge.EVENT_BUS.addListener(ModEvents::onPlayerLoggedIn);
    }

    private static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) {
            return;
        }

        MiddleEarthHeightMap.setSeed(player.serverLevel().getSeed());

        PlayerData data = StateSaverAndLoader.getPlayerStateReadOnly(player);
        if(data == null)
            return;
        if(data.getRace() != null){
            RaceUtil.reset(player);
            boolean isInMiddleEarth = ModDimensions.isInMiddleEarth(player.level());
            if(isInMiddleEarth){
                RaceUtil.initializeRace(player);
            } else if(ModServerConfigs.ENABLE_KEEP_RACE_ON_DIMENSION_SWAP){
                RaceUtil.initializeRace(player);
            }
        }
    }
}
