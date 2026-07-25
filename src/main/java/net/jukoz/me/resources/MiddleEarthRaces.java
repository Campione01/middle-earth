package net.jukoz.me.resources;

import net.jukoz.me.compat.neoforge.api.event.registry.DynamicRegistries;
import net.jukoz.me.MiddleEarth;
import net.jukoz.me.resources.datas.RaceType;
import net.jukoz.me.resources.datas.races.Race;
import net.jukoz.me.resources.datas.races.data.AttributeData;
import net.jukoz.me.utils.LoggerUtil;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attributes;
import java.util.*;

public class MiddleEarthRaces {
    public final static String PATH = "races";
    public static final ResourceKey<Registry<Race>> RACE_KEY = ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, PATH));

    public static void register(){
        LoggerUtil.logDebugMsg("Registering Dynamic Races for " + MiddleEarth.MOD_ID);
        DynamicRegistries.registerSynced(RACE_KEY, Race.CODEC);
    }

    // TODO : for/from Crab : This is only bare bone races, to be fleshed out in 1.6 -> I will take care of that :P
    public final static Race DWARF;
    public final static Race ELF;
    public final static Race HOBBIT;
    public final static Race HUMAN;
    public final static Race ORC;
    public final static Race URUK;

    public static void bootstrap(BootstrapContext<Race> context) {
        HolderGetter<Race> raceRegistryEntryLookup = context.lookup(RACE_KEY);
        // Registering all races
        register(context, raceRegistryEntryLookup, DWARF);
        register(context, raceRegistryEntryLookup, ELF);
        register(context, raceRegistryEntryLookup, HOBBIT);
        register(context, raceRegistryEntryLookup, HUMAN);
        register(context, raceRegistryEntryLookup, ORC);
        register(context, raceRegistryEntryLookup, URUK);
    }

    private static Race register(BootstrapContext<Race> context, HolderGetter<Race> raceRegistryEntryLookup, Race race) {
        ResourceKey<Race> raceRegistryKey = of(race.getId().getPath());
        String name = raceRegistryKey.location().getPath();
        ResourceKey<Race> newRace = ResourceKey.create(RACE_KEY,ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID,name));

        Optional<Holder.Reference<Race>> optionalRace = raceRegistryEntryLookup.get(raceRegistryKey);
        optionalRace.ifPresent(raceReference -> context.register(newRace, race));

        return race;
    }
    private static ResourceKey<Race> of(String name) {
        return ResourceKey.create(RACE_KEY, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, name));
    }


    static {
        DWARF = new Race(ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "dwarf"), RaceType.DWARF,
                new AttributeData(new HashMap<>(){{
                    put(Attributes.SCALE, 0.81);
                    put(Attributes.MAX_HEALTH, 22.0);
                    put(Attributes.ATTACK_DAMAGE, 1.0);
                    put(Attributes.ENTITY_INTERACTION_RANGE, 2.75);
                    put(Attributes.MOVEMENT_SPEED, 0.09);
                    put(Attributes.MINING_EFFICIENCY, 0.15);
                }}), List.of(), List.of());
        ELF = new Race(ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "elf"), RaceType.ELF,
                new AttributeData(new HashMap<>(){{
                    put(Attributes.SCALE, 1.06);
                    put(Attributes.MAX_HEALTH, 20.0);
                    put(Attributes.ATTACK_DAMAGE, 1.0);
                    put(Attributes.ENTITY_INTERACTION_RANGE, 3.25);
                    put(Attributes.MOVEMENT_SPEED, 0.1);
                    put(Attributes.FALL_DAMAGE_MULTIPLIER, 0.75);
                }}), List.of(), List.of());
        HOBBIT = new Race(ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "hobbit"), RaceType.HOBBIT,
                new AttributeData(new HashMap<>(){{
                    put(Attributes.SCALE, 0.6);
                    put(Attributes.MAX_HEALTH, 14.0);
                    put(Attributes.ATTACK_DAMAGE, 0.9);
                    put(Attributes.ENTITY_INTERACTION_RANGE, 2.5);
                    put(Attributes.MOVEMENT_SPEED, 0.115);
                    put(Attributes.SNEAKING_SPEED, 0.435);
                    put(Attributes.FALL_DAMAGE_MULTIPLIER, 0.90);
                }}), List.of(), List.of());
        HUMAN = new Race(ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "human"), RaceType.HUMAN,
                new AttributeData(new HashMap<>(){{
                    put(Attributes.SCALE, 1.0); // Basic
                }}), List.of(), List.of());
        ORC = new Race(ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "orc"), RaceType.ORC,
                new AttributeData(new HashMap<>(){{
                    put(Attributes.SCALE, 0.83);
                    put(Attributes.MAX_HEALTH, 16.0);
                    put(Attributes.ATTACK_DAMAGE, 1.0);
                    put(Attributes.ENTITY_INTERACTION_RANGE, 2.75);
                    put(Attributes.MOVEMENT_SPEED, 0.11);
                    put(Attributes.STEP_HEIGHT, 1.0);
                }}), List.of(), List.of());
        URUK = new Race(ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "uruk"), RaceType.URUK,
                new AttributeData(new HashMap<>(){{
                    put(Attributes.SCALE, 1.0);
                    put(Attributes.MAX_HEALTH,22.0);
                    put(Attributes.ATTACK_DAMAGE, 1.0);
                    put(Attributes.ENTITY_INTERACTION_RANGE, 3.0);
                    put(Attributes.MOVEMENT_SPEED, 0.09);
                    put(Attributes.BURNING_TIME, 0.70);
                }}), List.of(), List.of());
    }
}

