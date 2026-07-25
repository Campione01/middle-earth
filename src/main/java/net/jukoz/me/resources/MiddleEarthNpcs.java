package net.jukoz.me.resources;

import net.jukoz.me.compat.neoforge.api.event.registry.DynamicRegistries;
import net.jukoz.me.MiddleEarth;
import net.jukoz.me.item.ModEquipmentItems;
import net.jukoz.me.resources.datas.npcs.data.NpcGearData;
import net.jukoz.me.resources.datas.npcs.NpcData;
import net.jukoz.me.resources.datas.npcs.data.NpcGearItemData;
import net.jukoz.me.resources.datas.npcs.data.NpcGearSlotData;
import net.jukoz.me.resources.datas.npcs.pools.*;
import net.jukoz.me.utils.LoggerUtil;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import java.util.List;
import java.util.Optional;

public class MiddleEarthNpcs {
    public final static String PATH = "npcs";
    public static final ResourceKey<Registry<NpcData>> NPC_KEY = ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, PATH));

    // [GENERIC]
    public final static NpcData HUMAN_CIVILIAN;
    public final static NpcData DWARF_CIVILIAN;
    public final static NpcData ELF_CIVILIAN;
    public final static NpcData HOBBIT_CIVILIAN;
    public final static NpcData ORC_CIVILIAN;
    public final static NpcData URUK_CIVILIAN;
    public static void register(){
        LoggerUtil.logDebugMsg("Registering Dynamic Npcs for " + MiddleEarth.MOD_ID);
        DynamicRegistries.registerSynced(NPC_KEY, NpcData.CODEC);
    }

    public static void bootstrap(BootstrapContext<NpcData> context) {
        HolderGetter<NpcData> npcRegistryEntryLookup = context.lookup(NPC_KEY);
        // [RACE / GENERIC]
        register(context, npcRegistryEntryLookup, HUMAN_CIVILIAN);
        register(context, npcRegistryEntryLookup, DWARF_CIVILIAN);
        register(context, npcRegistryEntryLookup, ELF_CIVILIAN);
        register(context, npcRegistryEntryLookup, HOBBIT_CIVILIAN);
        register(context, npcRegistryEntryLookup, ORC_CIVILIAN);
        register(context, npcRegistryEntryLookup, URUK_CIVILIAN);

        // [GONDOR]
        registerAll(context, npcRegistryEntryLookup, GondorianNpcDataPool.fetchAll());
        // [ROHAN]
        registerAll(context, npcRegistryEntryLookup, RohirricNpcDataPool.fetchAll());
        // [DALE]
        registerAll(context, npcRegistryEntryLookup, DalishNpcDataPool.fetchAll());
        // [LONGBEARDS] - [EREBOR]
        registerAll(context, npcRegistryEntryLookup, EreborNpcDataPool.fetchAll());
        // [LOTHLORIEN]
        registerAll(context, npcRegistryEntryLookup, LorienNpcDataPool.fetchAll());
        // [MORDOR]
        registerAll(context, npcRegistryEntryLookup, MordorNpcDataPool.fetchAll());
        // [MISTY MOUNTAINS GOBLINS]
        registerAll(context, npcRegistryEntryLookup, MistyMountainsGoblinsNpcDataPool.fetchAll());
        // [ISENGARD]
        registerAll(context, npcRegistryEntryLookup, IsengardNpcDataPool.fetchAll());
        // [SHIRE]
        registerAll(context, npcRegistryEntryLookup, ShireNpcDataPool.fetchAll());
        // [BANDIT]
        registerAll(context, npcRegistryEntryLookup, BanditNpcDataPool.fetchAll());
    }

    private static void registerAll(BootstrapContext<NpcData> context, HolderGetter<NpcData> npcRegistryEntryLookup, List<NpcData> npcDatas) {
        for(NpcData data : npcDatas){
            register(context, npcRegistryEntryLookup, data);
        }
    }

    public static NpcData register(BootstrapContext<NpcData> context, HolderGetter<NpcData> npcRegistryEntryLookup, NpcData npcData) {
        ResourceKey<NpcData> npcRegistryKey = of(npcData.getName());
        String name = npcRegistryKey.location().getPath();
        ResourceKey<NpcData> npcKey = ResourceKey.create(NPC_KEY, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID,name));

        Optional<Holder.Reference<NpcData>> optionalNpc = npcRegistryEntryLookup.get(npcRegistryKey);
        optionalNpc.ifPresent(npcReference -> context.register(npcKey, npcData));

        return npcData;
    }

    private static ResourceKey<NpcData> of(String name) {
        return ResourceKey.create(NPC_KEY, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, name));
    }

    static {
        // region [GENERIC]
        HUMAN_CIVILIAN = new NpcData(ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "human.civilian"), MiddleEarthRaces.HUMAN, List.of(
                NpcGearData.create()
                        .add(EquipmentSlot.HEAD, NpcGearSlotData.create()
                                .add(NpcGearItemData.create(ModEquipmentItems.LEATHER_SKULLCAP).withWeight(2))
                                .add(NpcGearItemData.create(ModEquipmentItems.STRAW_HAT).withWeight(2))
                                .add(NpcGearItemData.create(ModEquipmentItems.WOVEN_HAT).withWeight(2))
                                .add(NpcGearItemData.create(ModEquipmentItems.BYCOCKET).withWeight(1))
                                .add(NpcGearItemData.create().withWeight(4))
                        )
                        .add(EquipmentSlot.FEET, NpcGearSlotData.create()
                                .add(NpcGearItemData.create(ModEquipmentItems.STURDY_BOOTS))
                                .add(NpcGearItemData.create(ModEquipmentItems.WORK_SHOES).withWeight(2))
                                .add(NpcGearItemData.create(ModEquipmentItems.SHOES).withWeight(2))
                                .add(NpcGearItemData.create().withWeight(3))
                        )
        ));

        DWARF_CIVILIAN = new NpcData(ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "dwarf.civilian"), MiddleEarthRaces.DWARF, List.of(
                NpcGearData.create()
        ));

        ELF_CIVILIAN = new NpcData(ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "elf.civilian"), MiddleEarthRaces.ELF, List.of(
                NpcGearData.create()
        ));

        HOBBIT_CIVILIAN = new NpcData(ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "hobbit.civilian"), MiddleEarthRaces.HOBBIT, List.of(
                NpcGearData.create()
        ));

        ORC_CIVILIAN = new NpcData(ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "orc.civilian"), MiddleEarthRaces.ORC, List.of(
                NpcGearData.create()
        ));

        URUK_CIVILIAN = new NpcData(ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "uruk.civilian"), MiddleEarthRaces.URUK, List.of(
                NpcGearData.create()
        ));
        // endregion
    }
}
