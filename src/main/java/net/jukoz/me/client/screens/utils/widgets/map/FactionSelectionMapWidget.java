package net.jukoz.me.client.screens.utils.widgets.map;

import net.jukoz.me.client.screens.faction_selection.FactionSelectionController;
import net.jukoz.me.client.screens.utils.widgets.UiDirections;
import net.jukoz.me.client.screens.utils.widgets.map.types.MapMarkerType;
import net.jukoz.me.resources.datas.factions.data.SpawnData;
import net.jukoz.me.resources.datas.factions.data.SpawnDataHandler;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import org.joml.Vector2d;
import org.joml.Vector2i;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FactionSelectionMapWidget extends MapWidget {
    MapMarkerWidget[] spawnMapMarkers;
    FactionSelectionController controller;
    public FactionSelectionMapWidget(FactionSelectionController controller, int mapWidth, int mapHeight) {
        super(mapWidth, mapHeight);
        this.controller = controller;
        final int[] maxSpawnCount = {0};
        this.controller.getFactions().values().forEach(factionList -> factionList.forEach(faction -> {
            SpawnDataHandler spawnDataHandler = faction.getSpawnData();
            if(spawnDataHandler != null && spawnDataHandler.getSpawnList() != null){
                int count = spawnDataHandler.getSpawnList().size();
                if(count > maxSpawnCount[0]){
                    maxSpawnCount[0] = count;
                }
            }
        }));
        spawnMapMarkers = new MapMarkerWidget[maxSpawnCount[0]];
        for(int i = 0; i< maxSpawnCount[0]; i++){
            int finalIndex = i;
            spawnMapMarkers[i] = new MapMarkerWidget("SpawnButton_" + i, x -> selectSpawn(finalIndex),
                    new Rectangle2D.Double(0, 0, uiWidth, uiHeight - 11));
            spawnMapMarkers[i].setType(MapMarkerType.DYNAMIC_SPAWN);
        }
        updateSelectedSpawn(controller.getCurrentSpawnIndex());
        MapMarkerWidget.setTitle(Component.translatable("widget.me.spawn_tooltip_title").withStyle(ChatFormatting.UNDERLINE));
    }
    public Button[] getButtons() {
        Button[] spawnButtonArray = new Button[spawnMapMarkers.length];
        for(int i = 0; i < spawnMapMarkers.length; i++){
            spawnButtonArray[i] = spawnMapMarkers[i].getButton();
        }
        return spawnButtonArray;
    }

    public void selectSpawn(int index){
        addCooldown();
        controller.setSpawnIndex(index);
    }

    public void updateSelectedSpawn(int index){
        for(int i = 0; i < spawnMapMarkers.length; i++){
            this.spawnMapMarkers[i].setSelected(i == index);
        }
    }

    protected double getMarkerGroupUpRadius(){
        return 15;
    }

    @Override
    protected void draw(GuiGraphics context, int startX, int startY) {
        super.draw(context, startX, startY);
        SpawnDataHandler handler = controller.getCurrentSpawnDataHandler();
        if(handler == null || handler.getSpawnList() == null) return;
        List<SpawnData> spawns = handler.getSpawnList();
        HashMap<Integer, List<Vector2i>> uniqueIndexes = new HashMap<>();
        for(int i = 0; i < spawns.size(); i++){
            SpawnData spawnData = spawns.get(i);
            Vector2d coordinates = new Vector2d(spawnData.getCoordinates().x(), spawnData.getCoordinates().z());
            MapMarkerWidget mapMarker = this.spawnMapMarkers[i];
            if(spawnData.isDynamic()){
                mapMarker.setType(MapMarkerType.DYNAMIC_SPAWN);
                mapMarker.computeFromMapPosition(this, coordinates);
                mapMarker.setContent(
                        List.of(
                                Component.translatable("spawn." + spawnData.getIdentifier().toLanguageKey()).withStyle(ChatFormatting.GOLD),
                                Component.translatable("widget.me.marker.margin_front").append(Component.translatable("spawn.me.coordinates_base.dynamic").withStyle(ChatFormatting.GRAY)
                                        .append(Component.translatable("spawn.me.coordinates_base_values.dynamic", spawnData.getWorldCoordinates().x, spawnData.getWorldCoordinates().z).withStyle(ChatFormatting.WHITE)))
                        ));
            } else {
                mapMarker.setType(MapMarkerType.CUSTOM_SPAWN);
                mapMarker.computeFromWorldPosition(this, coordinates);
                mapMarker.setContent(
                        List.of(
                                Component.translatable("spawn." + spawnData.getIdentifier().toLanguageKey()).withStyle(ChatFormatting.GOLD),
                                Component.translatable("widget.me.marker.margin_front").append(Component.translatable("spawn.me.coordinates_base.custom").withStyle(ChatFormatting.GRAY)
                                        .append(Component.translatable("spawn.me.coordinates_base_values.custom", spawnData.getWorldCoordinates().x, spawnData.getWorldCoordinates().y, spawnData.getWorldCoordinates().z).withStyle(ChatFormatting.WHITE)))
                        ));
            }

            mapMarker.clearChild();
            Vector2i currentCenterCoordinate = mapMarker.getCenterCoordinates();
            boolean isSeperate = true;
            int currentUniqueIndex = 0;
            for(int j = 0; j < uniqueIndexes.size() && isSeperate; j++){
                currentUniqueIndex = uniqueIndexes.keySet().stream().toList().get(j);
                List<Vector2i> currentList = uniqueIndexes.get(currentUniqueIndex);
                for (Vector2i vector2i : currentList) {
                    double distance = currentCenterCoordinate.distance(vector2i);
                    if (Math.round(distance) <= getMarkerGroupUpRadius()) {
                        isSeperate = false;
                    }
                }
            }
            if(isSeperate){
                uniqueIndexes.put(i, List.of(currentCenterCoordinate));
            } else {
                List<Vector2i> currentList = new ArrayList<>(uniqueIndexes.get(currentUniqueIndex).stream().toList());
                currentList.add(currentCenterCoordinate);
                uniqueIndexes.put(currentUniqueIndex, currentList);
                spawnMapMarkers[currentUniqueIndex].updateMarkerType(MapMarkerType.STACKED_SPAWNS);
                spawnMapMarkers[currentUniqueIndex].addChild(mapMarker);
                mapMarker.activateButton(false);
            }
        }
        for (Integer index : uniqueIndexes.keySet()) {
            spawnMapMarkers[index].assignNewCenter(averagePosition(uniqueIndexes.get(index)));
            spawnMapMarkers[index].draw(context);
        }
    }

    private Vector2i averagePosition(List<Vector2i> listOfPositions){
        Vector2i average = new Vector2i();
        for(Vector2i position : listOfPositions){
            average.x += position.x;
            average.y += position.y;
        }
        average.x /= listOfPositions.size();
        average.y /= listOfPositions.size();
        return average;
    }
}
