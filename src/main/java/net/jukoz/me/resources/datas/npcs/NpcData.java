package net.jukoz.me.resources.datas.npcs;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.jukoz.me.compat.neoforge.api.util.NbtType;
import net.jukoz.me.resources.datas.factions.FactionUtil;
import net.jukoz.me.resources.datas.npcs.data.NpcGearData;
import net.jukoz.me.resources.datas.races.Race;
import net.jukoz.me.resources.datas.races.RaceUtil;
import net.jukoz.me.utils.IdentifierUtil;
import net.jukoz.me.utils.LoggerUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NpcData {
    public static final Codec<NpcData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("id").forGetter(NpcData::getIdValue),
            Codec.STRING.fieldOf("race").forGetter(NpcData::getRaceIdValue),
            CompoundTag.CODEC.fieldOf("gears").forGetter(NpcData::getGearDataValues)
    ).apply(instance, NpcData::new));

    private final ResourceLocation id;
    private final ResourceLocation raceId;
    private final List<NpcGearData> gearDatas;
    public NpcData(String id, String raceId, CompoundTag gearDatas){
        this.id = IdentifierUtil.getIdentifierFromString(id);
        this.raceId = IdentifierUtil.getIdentifierFromString(raceId);

        ListTag npcGears = gearDatas.getList("pool", NbtType.COMPOUND);
        List<NpcGearData> npcGearDatas = new ArrayList<>();
        for(int j = 0; j < npcGears.size(); j++) {
            CompoundTag compound = npcGears.getCompound(j);
            npcGearDatas.add(NpcGearData.readNbt(compound));
        }
        this.gearDatas = npcGearDatas;
    }

    public NpcData(ResourceLocation id, Race race, List<NpcGearData> gearDatas){
        this.id = id;
        this.raceId = race.getId();
        this.gearDatas = gearDatas;
    }

    private String getIdValue() {
        return id.toString();
    }

    private String getRaceIdValue() {
        return raceId.toString();
    }

    private CompoundTag getGearDataValues() {
        CompoundTag nbt = new CompoundTag();
        ListTag gears = new ListTag();
        for(NpcGearData npcGearData : this.gearDatas){
            gears.add(NpcGearData.createNbt(npcGearData));
        }
        nbt.put("pool", gears);
        return nbt;
    }

    public String getName(){
        return id.getPath();
    }

    public ResourceLocation getId() {
        return id;
    }

    public ResourceLocation getRaceId() {
        return raceId;
    }

    public NpcGearData getGear() {
        if(gearDatas == null)
            return null;

        if(gearDatas.size() == 1)
            return gearDatas.getFirst();

        Random random = new Random();
        return gearDatas.get(random.nextInt(0, gearDatas.size()));
    }
}
