package net.jukoz.me.resources.datas.factions;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.jukoz.me.compat.neoforge.api.util.NbtType;
import net.jukoz.me.resources.MiddleEarthFactions;
import net.jukoz.me.resources.datas.Disposition;
import net.jukoz.me.resources.datas.FactionType;
import net.jukoz.me.resources.datas.factions.data.BannerData;
import net.jukoz.me.resources.datas.factions.data.SpawnDataHandler;
import net.jukoz.me.resources.datas.npcs.NpcData;
import net.jukoz.me.resources.datas.npcs.NpcDataLookup;
import net.jukoz.me.resources.datas.npcs.data.NpcGearData;
import net.jukoz.me.resources.datas.npcs.data.NpcRank;
import net.jukoz.me.resources.datas.races.Race;
import net.jukoz.me.resources.datas.races.RaceLookup;
import net.jukoz.me.utils.IdentifierUtil;
import net.jukoz.me.utils.LoggerUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.HolderGetter;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraft.world.level.block.entity.BannerPatternLayers;
import java.util.*;


public class Faction {
    private static HashMap<Disposition, List<Integer>> factionSelectionOrderIndexPerDisposition;

    public static final Codec<Faction> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("id").forGetter(Faction::getIdValue),
            Codec.INT.fieldOf("faction_selection_order_index").forGetter(Faction::getFactionSelectionOrderIndex),
            Codec.BOOL.fieldOf("joinable").forGetter(Faction::getJoinable),
            Codec.STRING.fieldOf("disposition").forGetter(Faction::getDispositionString),
            Codec.STRING.fieldOf("faction_type").forGetter(Faction::getFactionTypeString),
            ResourceLocation.CODEC.optionalFieldOf("parent_faction").forGetter(Faction::getParentFactionIdentifier),
            Codec.list(ResourceLocation.CODEC).optionalFieldOf("subfaction").forGetter(Faction::getSubfactionIds),
            CompoundTag.CODEC.optionalFieldOf("npcs").forGetter(Faction::getNpcValues),
            CompoundTag.CODEC.optionalFieldOf("banner").forGetter(Faction::getBannerNbt),
            CompoundTag.CODEC.optionalFieldOf("spawns").forGetter(Faction::getSpawnDataNbt),
            Codec.list(Codec.STRING, 0, 5).optionalFieldOf("command_join").forGetter(Faction::getJoinCommands),
            Codec.list(Codec.STRING, 0, 5).optionalFieldOf("command_leave").forGetter(Faction::getLeaveCommands)
        ).apply(instance, Faction::new));

    private final ResourceLocation id;
    private final Integer factionSelectionOrderIndex;
    private final String translatableKey;
    private final boolean joinable;
    private final Disposition disposition;
    private final FactionType factionType;
    private final ResourceLocation parentFactionId;
    private final HashMap<NpcRank, List<ResourceLocation>> npcDatasByRank;
    private final BannerData bannerData;
    private final SpawnDataHandler spawnDataHandler;
    private List<ResourceLocation> subFactions = null;
    private List<String> joinCommands;
    private List<String> leaveCommands;
    public List<Race> races = null;
    private List<Component> descriptions = null;
    private Component raceList = null;

    public Faction(String id, Integer factionSelectionOrderIndex, Boolean joinable, String disposition, String factionType, Optional<ResourceLocation> parentFaction, Optional<List<ResourceLocation>> newSubFactions, Optional<CompoundTag> npcs, Optional<CompoundTag> bannerDataNbt, Optional<CompoundTag> spawnsNbt, Optional<List<String>> joinCommands, Optional<List<String>> leaveCommands) {
        this.id = IdentifierUtil.getIdentifierFromString(id);

        this.factionSelectionOrderIndex = factionSelectionOrderIndex; // TODO : Validation, rework this part in the future
        this.translatableKey = "faction.".concat(this.id.toLanguageKey());
        this.joinable = joinable;
        this.disposition = Disposition.valueOf(disposition.toUpperCase());

        this.factionType = FactionType.valueOf(factionType.toUpperCase());
        this.parentFactionId = parentFaction.orElse(null);

        if(newSubFactions.isPresent()){
            this.subFactions = new ArrayList<>();
            this.subFactions.addAll(newSubFactions.get());
        }

        this.npcDatasByRank = new HashMap<>();
        npcs.ifPresent(nbtCompound -> {
            ListTag list = nbtCompound.getList("ranks", NbtType.COMPOUND);
            for(int i = 0; i < list.size(); i++){
                CompoundTag rankCompound = list.getCompound(i);
                NpcRank rank = NpcRank.valueOf(rankCompound.getString("rank").toUpperCase());
                ListTag npcDataList = rankCompound.getList("pool", NbtType.STRING);
                List<ResourceLocation> dataList = new ArrayList<>();
                for(int j = 0; j < npcDataList.size(); j++){
                    dataList.add(IdentifierUtil.getIdentifierFromString(npcDataList.getString(j)));
                }
                this.npcDatasByRank.put(rank, dataList);
            }
        });

        this.bannerData = (bannerDataNbt.isEmpty()) ? null : new BannerData(bannerDataNbt);;
        this.spawnDataHandler = new SpawnDataHandler(spawnsNbt);

        this.joinCommands = new ArrayList<>();
        joinCommands.ifPresent(nbtCompound -> this.joinCommands.addAll(nbtCompound));
        this.leaveCommands = new ArrayList<>();
        leaveCommands.ifPresent(nbtCompound -> this.leaveCommands.addAll(nbtCompound));

        this.raceList = null;
        this.descriptions = null;

        verifyData();
    }

    public Faction(String name, Boolean joinable, Disposition disposition, FactionType factionType, ResourceLocation parentFactionId, List<ResourceLocation> subFactions, HashMap<NpcRank, List<NpcData>> npcDatas, BannerData bannerData, SpawnDataHandler spawnDataHandler, List<String> joinCommand, List<String> leaveCommand){
        this.id = IdentifierUtil.getIdentifierFromString(name);

        if(factionSelectionOrderIndexPerDisposition == null)
            factionSelectionOrderIndexPerDisposition = new HashMap<>();
        if(factionSelectionOrderIndexPerDisposition.containsKey(disposition)){
            this.factionSelectionOrderIndex = factionSelectionOrderIndexPerDisposition.get(disposition).size();
            List<Integer> orderList = new ArrayList<>(factionSelectionOrderIndexPerDisposition.get(disposition));
            orderList.add(this.factionSelectionOrderIndex);
            factionSelectionOrderIndexPerDisposition.put(disposition, orderList);
        }
        else {
            int initialIndex = 0;
            this.factionSelectionOrderIndex = initialIndex;
            factionSelectionOrderIndexPerDisposition.put(disposition, List.of(initialIndex));
        }

        this.translatableKey = "faction.".concat(this.id.toLanguageKey());
        this.joinable = joinable;
        this.disposition = disposition;
        this.factionType = factionType;
        this.parentFactionId = parentFactionId;
        this.subFactions = subFactions;
        if(npcDatas == null || npcDatas.isEmpty()){
            this.npcDatasByRank = null;
        } else{
            this.npcDatasByRank = new HashMap<>();
            for(NpcRank rank : npcDatas.keySet()){
                List<ResourceLocation> listOfIdentifiers = new ArrayList<>();
                for(NpcData data : npcDatas.get(rank)){
                    listOfIdentifiers.add(data.getId());
                }
                this.npcDatasByRank.put(rank, listOfIdentifiers);
            }
        }
        this.bannerData = bannerData;;
        this.spawnDataHandler = spawnDataHandler;
        this.joinCommands = joinCommand;
        this.leaveCommands = leaveCommand;
        this.raceList = null;
        this.descriptions = null;

        verifyData();
    }

    private void verifyData(){
        if(this.id.toString().contains("dorwinion")){
            throw new RuntimeException("There is no dorwinion in Middle-earth");
        }
        if(this.id.toString().toLowerCase().contains("dorw")){
            throw new RuntimeException("Do not even try... We are watching you");
        }

        // Need these data for a functional faction
        if((this.factionType == FactionType.SUBFACTION) || (this.factionType == FactionType.FACTION) && (subFactions == null || subFactions.isEmpty())){
            if(this.npcDatasByRank == null || this.npcDatasByRank.isEmpty()){
                throw new RuntimeException("Faction [%s] is missing their npc data, make sure they have at least 1 available npc data per rank.".formatted(id));
            } else {
                if(!npcDatasByRank.containsKey(NpcRank.MILITIA)
                        || !npcDatasByRank.containsKey(NpcRank.SOLDIER)
                        || !npcDatasByRank.containsKey(NpcRank.KNIGHT)
                        || !npcDatasByRank.containsKey(NpcRank.VETERAN)
                        || !npcDatasByRank.containsKey(NpcRank.LEADER)) {
                    throw new RuntimeException("Faction [%s] is missing their npc data, make sure they have at least 1 npc data per rank.".formatted(id));
                }
            }
            if(this.bannerData == null){
                throw new RuntimeException("Faction [%s] is missing their banner data, make sure they have one.".formatted(id));
            }
        }
    }

    private String getIdValue() {
        return this.id.toString();
    }

    public Integer getFactionSelectionOrderIndex() {
        return this.factionSelectionOrderIndex;
    }

    private Boolean getJoinable() {
        return joinable;
    }

    private Optional<ResourceLocation> getParentFactionIdentifier() {
        if(this.parentFactionId == null)
            return Optional.empty();
        return Optional.of(this.parentFactionId);
    }
    public ResourceLocation getParentFactionId() {
        return parentFactionId;
    }

    private Optional<List<ResourceLocation>> getSubfactionIds() {
        if(this.subFactions == null)
            return Optional.empty();
        return Optional.of(subFactions);
    }
    private Optional<CompoundTag> getBannerNbt() {
        if(this.bannerData == null)
            return Optional.empty();
        return this.bannerData.getNbt();
    }
    private Optional<CompoundTag> getSpawnDataNbt() {
        if(this.spawnDataHandler == null)
            return Optional.empty();

        return this.spawnDataHandler.serializeNbt();
    }

    public Optional<CompoundTag> getNpcValues() {
        if(this.npcDatasByRank == null || this.npcDatasByRank.isEmpty())
            return Optional.empty();
        CompoundTag nbtCompound = new CompoundTag();
        ListTag ranks = new ListTag();
        for(NpcRank rank : this.npcDatasByRank.keySet()){
            CompoundTag rankNbt = new CompoundTag();
            rankNbt.putString("rank", rank.toString().toUpperCase());
            ListTag identifiers = new ListTag();
            for(ResourceLocation npcDataIdentifier : this.npcDatasByRank.get(rank).stream().toList()) {
                identifiers.add(StringTag.valueOf(npcDataIdentifier.toString()));
            }
            rankNbt.put("pool", identifiers);
            ranks.add(rankNbt);
        }
        nbtCompound.put("ranks", ranks);
        return Optional.of(nbtCompound);
    }

    public Optional<List<String>> getJoinCommands() {
        if(this.joinCommands == null)
            return Optional.empty();
        return Optional.of(this.joinCommands);
    }

    public Optional<List<String>> getLeaveCommands() {
        if(this.leaveCommands == null)
            return Optional.empty();
        return Optional.of(this.leaveCommands);
    }

    @Override
    public String toString() {
    return id.toString();
    }

    public NpcData getRandomGear(Level world, NpcRank npcRank, Race race) {
        if(!this.npcDatasByRank.containsKey(npcRank))
            return null;
        List<NpcData> npcDataList = NpcDataLookup.getAllNpcDatasFromRace(world, getNpcPoolFromRank(npcRank), race.getId());
        if(npcDataList.isEmpty())
            return null;
        Random random = new Random();
        return npcDataList.get(random.nextInt(0, npcDataList.size()));
    }

    public NpcGearData getPreviewGear(Level world, Race selectedRace){
        if(selectedRace == null)
            return NpcGearData.Create();

        List<ResourceLocation> identifiersToUse = new ArrayList<>();
        identifiersToUse.addAll(getNpcPoolFromRank(NpcRank.MILITIA));
        identifiersToUse.addAll(getNpcPoolFromRank(NpcRank.SOLDIER));
        identifiersToUse.addAll(getNpcPoolFromRank(NpcRank.KNIGHT));
        identifiersToUse.addAll(getNpcPoolFromRank(NpcRank.VETERAN));
        identifiersToUse.addAll(getNpcPoolFromRank(NpcRank.LEADER));

        List<NpcData> npcDataList = NpcDataLookup.getAllNpcDatasFromRace(world, identifiersToUse, selectedRace.getId());
        if(npcDataList.isEmpty())
            return NpcGearData.Create();
        Random random = new Random();
        NpcData foundNpcData = npcDataList.get(random.nextInt(0, npcDataList.size()));
        return foundNpcData.getGear();
    }

    private List<ResourceLocation> getNpcPoolFromRank(NpcRank npcRank) {
        return this.npcDatasByRank.get(npcRank);
    }

    public DyeColor getBaseBannerColor(){
        if(bannerData == null) return BannerData.DEFAULT_DYE;
        return bannerData.getBaseDye();
    }

    public List<BannerData.BannerPatternWithColor> getBannerPatternsWithColors(Level world) {
        if(bannerData == null) return null;
        return bannerData.getBannerPatternsWithColors(world);
    }

    public ItemStack getBannerItem(Level world){
        return bannerData.getBannerItem(world, Component.translatable("block.me.faction_banner", getFullName()).withStyle(ChatFormatting.GOLD));
    }

    public List<ResourceLocation> getSubFactions(){
        return subFactions;
    }

    public Faction getSubfaction(Level world, int index){
        if(world == null || this.subFactions == null || index >= this.subFactions.size())
            return null;
        return getSubfactionById(world, subFactions.get(index));
    }

    public Disposition getDisposition(){
        return disposition;
    }
    public String getDispositionString(){
        return disposition.name();
    }
    public String getFactionTypeString(){
        return factionType.name();
    }

    public FactionType getFactionType(){
        return factionType;
    }

    public SpawnDataHandler getSpawnData() {
        return spawnDataHandler; }

    public ResourceLocation getId() {
        return id;
    }

    public String getName() {
        return id.getPath();
    }

    public MutableComponent getFullName() {
        return MutableComponent.create(new TranslatableContents(translatableKey, "", TranslatableContents.NO_ARGS));
    }

    public MutableComponent tryGetShortName() {
        String target = translatableKey.concat(".fallback");
        String fallback = Component.translatable(translatableKey).getString();
        return MutableComponent.create(new TranslatableContents(target, fallback, TranslatableContents.NO_ARGS));
    }

    public Faction getSubfactionById(Level world, ResourceLocation id) {
        if(subFactions == null)
            return null;
        return world.registryAccess().registryOrThrow(MiddleEarthFactions.FACTION_KEY).get(id);
    }

    public List<Race> getRaces(Level world) {
        if(races != null) return races;

        List<ResourceLocation> allRaceIds = new ArrayList<>();
        for(NpcRank rank : this.npcDatasByRank.keySet()){
            List<NpcData> datas = NpcDataLookup.getAllNpcDatas(world, this.npcDatasByRank.get(rank));
            for(NpcData data : datas){
                if(data != null)
                    allRaceIds.add(data.getRaceId());
            }
        }
        races = RaceLookup.getAllRaces(world, allRaceIds);
        return races;
    }

    public boolean isJoinable() {
        return joinable;
    }

    public List<Component> getDescription() {
        if(descriptions == null){
            descriptions = new ArrayList<>();
            boolean hasDescription = true;
            String base = "description.me.%s.description_%s".formatted(id.getPath(), "%s");
            while(hasDescription){
                String langPath = base.formatted(descriptions.size());
                Component text = Component.translatable(langPath);
                if(!Objects.equals(text.getString(), langPath)){
                    descriptions.add(text);
                } else {
                    hasDescription = false;
                }
            }
        }
        return descriptions;
    }

    public Component getRaceListText(Level world) {
        if(raceList == null){
            StringBuilder raceListStringBuilder = new StringBuilder();
            if(races == null)
                races = getRaces(world);
            for(Race race : races){
                raceListStringBuilder.append(race.getFullName().getString());
                if(race != races.getLast())
                    raceListStringBuilder.append(", ");
            }
            raceList = Component.literal(raceListStringBuilder.toString());
        }
        return raceList;
    }

    public BannerPatternLayers getBannerPatternComponents(HolderGetter<BannerPattern> bannerPatternLookup) {
        if(bannerData == null)
            return null;
        return bannerData.getBannerPatternComponents(bannerPatternLookup);
    }
}
