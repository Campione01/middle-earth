package net.jukoz.me.resources.persistent_datas;

import net.jukoz.me.exceptions.FactionIdentifierException;
import net.jukoz.me.resources.MiddleEarthRaces;
import net.jukoz.me.resources.datas.Disposition;
import net.jukoz.me.resources.datas.FactionType;
import net.jukoz.me.resources.datas.RaceType;
import net.jukoz.me.resources.datas.factions.Faction;
import net.jukoz.me.resources.datas.factions.FactionLookup;
import net.jukoz.me.resources.datas.races.Race;
import net.jukoz.me.utils.LoggerUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class PlayerData {
    private AffiliationData affiliationData;
    private ResourceLocation race;
    private BlockPos overworldSpawnCoordinates;
    private transient Runnable dirtyMarker = () -> {};

    public PlayerData(){
        this.affiliationData = null;
        this.race = null;
        this.overworldSpawnCoordinates = null;
    }

    public void setDirtyMarker(Runnable dirtyMarker) {
        this.dirtyMarker = dirtyMarker == null ? () -> {} : dirtyMarker;
    }

    private void markDirty() {
        dirtyMarker.run();
    }

    public void setRace(ResourceLocation raceId){
        this.race = raceId;
        markDirty();
    }
    public ResourceLocation getRace(){
        return this.race;
    }

    public void setAffiliationData(AffiliationData affiliationData){
        this.affiliationData = affiliationData;
        markDirty();
    }

    public boolean hasAffilition(){
        return affiliationData != null;
    }

    public Race getRace(Level world){
        if(this.race == null)
            return null;
        return world.registryAccess().registryOrThrow(MiddleEarthRaces.RACE_KEY).get(this.race);
    }
    public Faction getFaction(Level world) throws FactionIdentifierException{
        if(!hasAffilition())
            return null;
        Faction faction = FactionLookup.getFactionById(world, affiliationData.faction);
        if(faction.getFactionType() == FactionType.SUBFACTION){
            ResourceLocation parentFactionIdentifier = faction.getParentFactionId();
            if(parentFactionIdentifier == null){
                LoggerUtil.logError(faction.getName() + " is said to be a subfaction, but does not have a parent faction, returning the obtained faction by default.");
                return faction;
            }
            faction = FactionLookup.getFactionById(world, parentFactionIdentifier);
        }
        return faction;
    }
    public Faction getSubfaction(Level world) throws FactionIdentifierException{
        if(!hasAffilition())
            return null;
        Faction faction = FactionLookup.getFactionById(world, affiliationData.faction);
        if(faction.getFactionType() == FactionType.FACTION)
            return null;
        return faction;
    }

    public Faction getCurrentFaction(Level world) throws FactionIdentifierException {
        if(!hasAffilition())
            return null;
        return FactionLookup.getFactionById(world, affiliationData.faction);
    }
    public Disposition getCurrentDisposition() {
        if(!hasAffilition())
            return null;
        return affiliationData.disposition;
    }
    public ResourceLocation getCurrentFactionId() {
        if(!hasAffilition())
            return null;
        return affiliationData.faction;
    }

    public ResourceLocation getCurrentSpawnId(){
        if(!hasAffilition())
            return null;
        return affiliationData.spawnId;
    }

    public RaceType getRaceType(Level world){
        if(race == null)
            return RaceType.NONE;
        Race foundRace = getRace(world);
        if(foundRace == null)
            return RaceType.NONE;
        return foundRace.getRaceType();
    }

    public Vec3 getSpawnMiddleEarthCoordinate(Level world){
        if(!hasAffilition())
            return null;
        return affiliationData.getSpawnMiddleEarthCoordinate(world);
    }

    @Override
    public String toString() {
        String text = "";
        if(hasAffilition())
            text += affiliationData+"\n";
        if(race != null)
            text += "Race="+race+"\n";
        if(overworldSpawnCoordinates != null)
            text += "Overworld="+overworldSpawnCoordinates+"\n";

        if(!text.equals(""))
            return text;
        else
            return "No Data";
    }

    public void setOverworldSpawn(BlockPos overworldSpawnCoordinate) {
        this.overworldSpawnCoordinates = overworldSpawnCoordinate;
        markDirty();
    }
    public BlockPos getOverworldSpawnCoordinates() {
        return overworldSpawnCoordinates;
    }

    public void clearData() {
        this.affiliationData = null;
        this.overworldSpawnCoordinates = null;
        this.race = null;
        markDirty();
    }

    public boolean setSpawnMiddleEarthId(Level world, ResourceLocation foundId) throws FactionIdentifierException {
        if(hasAffilition()){
            if(FactionLookup.getFactionById(world,affiliationData.faction).getSpawnData().getAllSpawnIdentifiers().contains(foundId)){
                affiliationData.spawnId = foundId;
                markDirty();
                return true;
            }
        }
        return false;
    }
}
