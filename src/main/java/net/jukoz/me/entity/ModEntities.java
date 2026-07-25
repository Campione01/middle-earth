package net.jukoz.me.entity;

import net.jukoz.me.utils.NeoForgeRegistrationBridge;

import net.jukoz.me.compat.neoforge.api.object.builder.v1.entity.NeoForgeDefaultAttributeRegistry;
import net.jukoz.me.MiddleEarth;
import net.jukoz.me.block.special.fire_of_orthanc.FireOfOrthancEntity;
import net.jukoz.me.entity.barrow_wights.BarrowWightEntity;
import net.jukoz.me.entity.beasts.broadhoof.BroadhoofGoatEntity;
import net.jukoz.me.entity.beasts.trolls.petrified.PetrifiedTrollEntity;
import net.jukoz.me.entity.beasts.trolls.stone.StoneTrollEntity;
import net.jukoz.me.entity.beasts.warg.WargEntity;
import net.jukoz.me.entity.deer.DeerEntity;
import net.jukoz.me.entity.dwarves.longbeards.LongbeardDwarfEntity;
import net.jukoz.me.entity.elves.galadhrim.GaladhrimElfEntity;
import net.jukoz.me.entity.hobbits.shire.ShireHobbitEntity;
import net.jukoz.me.entity.humans.bandit.BanditHumanEntity;
import net.jukoz.me.entity.humans.dale.DaleHumanEntity;
import net.jukoz.me.entity.humans.gondor.GondorHumanEntity;
import net.jukoz.me.entity.humans.rohan.RohanHumanEntity;
import net.jukoz.me.entity.orcs.isengard.IsengardOrcEntity;
import net.jukoz.me.entity.orcs.misties.MistyGoblinEntity;
import net.jukoz.me.entity.orcs.mordor.MordorOrcEntity;
import net.jukoz.me.entity.orcs.wild.WildGoblinEntity;
import net.jukoz.me.entity.pheasant.PheasantEntity;
import net.jukoz.me.entity.projectile.boulder.BoulderEntity;
import net.jukoz.me.entity.projectile.pinecone.LitPineconeEntity;
import net.jukoz.me.entity.projectile.pebble.PebbleEntity;
import net.jukoz.me.entity.projectile.pinecone.PineconeEntity;
import net.jukoz.me.entity.projectile.spear.SpearEntity;
import net.jukoz.me.entity.seat.SeatEntity;
import net.jukoz.me.entity.snail.SnailEntity;
import net.jukoz.me.entity.spider.MirkwoodSpiderEntity;
import net.jukoz.me.entity.swan.SwanEntity;
import net.jukoz.me.entity.beasts.trolls.snow.SnowTrollEntity;
import net.jukoz.me.entity.uruks.isengard.IsengardUrukHaiEntity;
import net.jukoz.me.entity.uruks.misties.MistyHobgoblinEntity;
import net.jukoz.me.entity.uruks.mordor.MordorBlackUrukEntity;
import net.jukoz.me.utils.LoggerUtil;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

public class ModEntities {

    //TODO Npc weird animation/item holding -> model fucked

    // Barrow Wights
    public static final EntityType<BarrowWightEntity> BARROW_WIGHT = registerEntity("barrow_wight", BarrowWightEntity::new, MobCategory.CREATURE, 0.9f, 2.1f);

    // Hobbits
    public static final EntityType<ShireHobbitEntity> HOBBIT_CIVILIAN = registerEntity("hobbit_civilian", ShireHobbitEntity::new, MobCategory.CREATURE, 0.6f, 1.9f);
    public static final EntityType<ShireHobbitEntity> HOBBIT_BOUNDER = registerEntity("hobbit_bounder", ShireHobbitEntity::new, MobCategory.CREATURE, 0.6f, 1.9f);
    public static final EntityType<ShireHobbitEntity> HOBBIT_SHIRRIFF = registerEntity("hobbit_shirriff", ShireHobbitEntity::new, MobCategory.CREATURE, 0.6f, 1.9f);

    //HUMANS
    //Gondor
    public static final EntityType<GondorHumanEntity> GONDORIAN_MILITIA = registerEntity("gondorian_militia", GondorHumanEntity::new, MobCategory.CREATURE, 0.7F, 1.9F);
    public static final EntityType<GondorHumanEntity> GONDORIAN_SOLDIER = registerEntity("gondorian_soldier", GondorHumanEntity::new, MobCategory.CREATURE, 0.7F, 1.9F);
    public static final EntityType<GondorHumanEntity> GONDORIAN_KNIGHT = registerEntity("gondorian_knight", GondorHumanEntity::new, MobCategory.CREATURE, 0.7F, 1.9F);
    public static final EntityType<GondorHumanEntity> GONDORIAN_VETERAN = registerEntity("gondorian_veteran", GondorHumanEntity::new, MobCategory.CREATURE, 0.7F, 1.9F);
    public static final EntityType<GondorHumanEntity> GONDORIAN_LEADER = registerEntity("gondorian_leader", GondorHumanEntity::new, MobCategory.CREATURE, 0.7F, 1.9F);

    //Rohan
    public static final EntityType<RohanHumanEntity> ROHIRRIM_MILITIA = registerEntity("rohirrim_militia", RohanHumanEntity::new, MobCategory.CREATURE, 0.7F, 1.9F);
    public static final EntityType<RohanHumanEntity> ROHIRRIM_SOLDIER = registerEntity("rohirrim_soldier", RohanHumanEntity::new, MobCategory.CREATURE, 0.7F, 1.9F);
    public static final EntityType<RohanHumanEntity> ROHIRRIM_KNIGHT = registerEntity("rohirrim_knight", RohanHumanEntity::new, MobCategory.CREATURE, 0.7F, 1.9F);
    public static final EntityType<RohanHumanEntity> ROHIRRIM_VETERAN = registerEntity("rohirrim_veteran", RohanHumanEntity::new, MobCategory.CREATURE, 0.7F, 1.9F);
    public static final EntityType<RohanHumanEntity> ROHIRRIM_LEADER = registerEntity("rohirrim_leader", RohanHumanEntity::new, MobCategory.CREATURE, 0.7F, 1.9F);

    //Dale
    public static final EntityType<DaleHumanEntity> DALISH_MILITIA = registerEntity("dalish_militia", DaleHumanEntity::new, MobCategory.CREATURE, 0.7F, 1.9F);
    public static final EntityType<DaleHumanEntity> DALISH_SOLDIER = registerEntity("dalish_soldier", DaleHumanEntity::new, MobCategory.CREATURE, 0.7F, 1.9F);
    public static final EntityType<DaleHumanEntity> DALISH_KNIGHT = registerEntity("dalish_knight", DaleHumanEntity::new, MobCategory.CREATURE, 0.7F, 1.9F);
    public static final EntityType<DaleHumanEntity> DALISH_VETERAN = registerEntity("dalish_veteran", DaleHumanEntity::new, MobCategory.CREATURE, 0.7F, 1.9F);
    public static final EntityType<DaleHumanEntity> DALISH_LEADER = registerEntity("dalish_leader", DaleHumanEntity::new, MobCategory.CREATURE, 0.7F, 1.9F);


    //Bandits
    public static final EntityType<BanditHumanEntity> BANDIT_MILITIA = registerEntity("bandit_militia", BanditHumanEntity::new, MobCategory.CREATURE, 0.7F, 1.9F);
    public static final EntityType<BanditHumanEntity> BANDIT_SOLDIER = registerEntity("bandit_soldier", BanditHumanEntity::new, MobCategory.CREATURE, 0.7F, 1.9F);
    public static final EntityType<BanditHumanEntity> BANDIT_CHIEFTAIN = registerEntity("bandit_chieftain", BanditHumanEntity::new, MobCategory.CREATURE, 0.7F, 1.9F);
    public static final EntityType<WildGoblinEntity> WILD_GOBLIN = registerEntity("wild_goblin", WildGoblinEntity::new, MobCategory.MONSTER, 0.7F, 1.9F);

    // Dwarfs
    public static final EntityType<LongbeardDwarfEntity> LONGBEARD_MILITIA = registerEntity("longbeard_militia", LongbeardDwarfEntity::new, MobCategory.CREATURE, 0.8f, 1.9f);
    public static final EntityType<LongbeardDwarfEntity> LONGBEARD_SOLDIER = registerEntity("longbeard_soldier", LongbeardDwarfEntity::new, MobCategory.CREATURE, 0.8f, 1.9f);
    public static final EntityType<LongbeardDwarfEntity> LONGBEARD_ELITE = registerEntity("longbeard_elite", LongbeardDwarfEntity::new, MobCategory.CREATURE, 0.8f, 1.9f);
    public static final EntityType<LongbeardDwarfEntity> LONGBEARD_VETERAN = registerEntity("longbeard_veteran", LongbeardDwarfEntity::new, MobCategory.CREATURE, 0.8f, 1.9f);
    public static final EntityType<LongbeardDwarfEntity> LONGBEARD_LEADER = registerEntity("longbeard_leader", LongbeardDwarfEntity::new, MobCategory.CREATURE, 0.8f, 1.9f);

    // Elves
    public static final EntityType<GaladhrimElfEntity> LORIEN_MILITIA = registerEntity("lorien_militia", GaladhrimElfEntity::new, MobCategory.CREATURE, 0.75f, 1.9f);
    public static final EntityType<GaladhrimElfEntity> LORIEN_SOLDIER = registerEntity("lorien_soldier", GaladhrimElfEntity::new, MobCategory.CREATURE, 0.75f, 1.9f);
    public static final EntityType<GaladhrimElfEntity> LORIEN_KNIGHT = registerEntity("lorien_knight", GaladhrimElfEntity::new, MobCategory.CREATURE, 0.75f, 1.9f);
    public static final EntityType<GaladhrimElfEntity> LORIEN_VETERAN = registerEntity("lorien_veteran", GaladhrimElfEntity::new, MobCategory.CREATURE, 0.75f, 1.9f);
    public static final EntityType<GaladhrimElfEntity> LORIEN_LEADER = registerEntity("lorien_leader", GaladhrimElfEntity::new, MobCategory.CREATURE, 0.75f, 1.9f);

    //ORCS
    //Mordor
    public static final EntityType<MordorOrcEntity> MORDOR_ORC_SNAGA = registerEntity("mordor_orc_snaga", MordorOrcEntity::new, MobCategory.CREATURE, 0.7f, 1.9f);
    public static final EntityType<MordorOrcEntity> MORDOR_ORC_SOLDIER = registerEntity("mordor_orc_soldier", MordorOrcEntity::new, MobCategory.CREATURE, 0.7f, 1.9f);
    //Misties
    public static final EntityType<MistyGoblinEntity> MISTY_GOBLIN_SNAGA = registerEntity("misty_goblin_snaga", MistyGoblinEntity::new, MobCategory.CREATURE, 0.7f, 1.9f);
    public static final EntityType<MistyGoblinEntity> MISTY_GOBLIN_WARRIOR = registerEntity("misty_goblin_warrior", MistyGoblinEntity::new, MobCategory.CREATURE, 0.7f, 1.9f);
    //Isengard
    public static final EntityType<IsengardOrcEntity> ISENGARD_ORC_SNAGA = registerEntity("isengard_orc_snaga", IsengardOrcEntity::new, MobCategory.CREATURE, 0.7f, 1.9f);
    public static final EntityType<IsengardOrcEntity> ISENGARD_ORC_WARRIOR = registerEntity("isengard_orc_warrior", IsengardOrcEntity::new, MobCategory.CREATURE, 0.7f, 1.9f);

    //URUKS
    //Mordor
    public static final EntityType<MordorBlackUrukEntity> MORDOR_BLACK_URUK_SOLDIER = registerEntity("mordor_black_uruk_soldier", MordorBlackUrukEntity::new, MobCategory.CREATURE, 0.85f, 1.9f);
    public static final EntityType<MordorBlackUrukEntity> MORDOR_BLACK_URUK_VETERAN = registerEntity("mordor_black_uruk_veteran", MordorBlackUrukEntity::new, MobCategory.CREATURE, 0.85f, 1.9f);
    public static final EntityType<MordorBlackUrukEntity> MORDOR_BLACK_URUK_LEADER = registerEntity("mordor_black_uruk_leader", MordorBlackUrukEntity::new, MobCategory.CREATURE, 0.85f, 1.9f);
    //Misties
    public static final EntityType<MistyHobgoblinEntity> MISTY_HOBGOBLIN_SOLDIER = registerEntity("misty_hobgoblin_soldier", MistyHobgoblinEntity::new, MobCategory.CREATURE, 0.85f, 1.9f);
    public static final EntityType<MistyHobgoblinEntity> MISTY_HOBGOBLIN_VETERAN = registerEntity("misty_hobgoblin_veteran", MistyHobgoblinEntity::new, MobCategory.CREATURE, 0.85f, 1.9f);
    public static final EntityType<MistyHobgoblinEntity> MISTY_HOBGOBLIN_LEADER = registerEntity("misty_hobgoblin_leader", MistyHobgoblinEntity::new, MobCategory.CREATURE, 0.85f, 1.9f);

    //Isengard
    public static final EntityType<IsengardUrukHaiEntity> ISENGARD_URUK_HAI_SOLDIER = registerEntity("isengard_uruk_hai_soldier", IsengardUrukHaiEntity::new, MobCategory.CREATURE, 0.85f, 1.9f);
    public static final EntityType<IsengardUrukHaiEntity> ISENGARD_URUK_HAI_VETERAN = registerEntity("isengard_uruk_hai_veteran", IsengardUrukHaiEntity::new, MobCategory.CREATURE, 0.85f, 1.9f);
    public static final EntityType<IsengardUrukHaiEntity> ISENGARD_URUK_HAI_LEADER = registerEntity("isengard_uruk_hai_leader", IsengardUrukHaiEntity::new, MobCategory.CREATURE, 0.85f, 1.9f);

    // Wargs
    public static final EntityType<WargEntity> WARG = registerEntity("warg", WargEntity::new, MobCategory.CREATURE, 1.4f, 1.4f);

    // Goat
    public static final EntityType<BroadhoofGoatEntity> BROADHOOF_GOAT = registerEntity("broadhoof_goat", BroadhoofGoatEntity::new, MobCategory.CREATURE, 1.4f, 1.4f);

    // Spiders
    public static final EntityType<MirkwoodSpiderEntity> MIRKWOOD_SPIDER = registerEntity("mirkwood_spider", MirkwoodSpiderEntity::new, MobCategory.CREATURE, 1.15f, 0.9f);

    // Trolls
    public static final EntityType<SnowTrollEntity> SNOW_TROLL = registerEntity("snow_troll", SnowTrollEntity::new, MobCategory.CREATURE, 2.2f, 2.5f);
    public static final EntityType<StoneTrollEntity> STONE_TROLL = registerEntity("stone_troll", StoneTrollEntity::new, MobCategory.CREATURE, 1.4f, 3.4f);
    public static final EntityType<PetrifiedTrollEntity> PETRIFIED_TROLL = registerEntity("petrified_troll", PetrifiedTrollEntity::new, MobCategory.CREATURE, 1.4f, 3.4f);

    ///* Weapons *///
    public static final EntityType<FireOfOrthancEntity> FIRE_OF_ORTHANC = registerEntity("fire_of_orthanc", FireOfOrthancEntity::new, MobCategory.MISC, 0.65F, 0.65F);
    public static final EntityType<PebbleEntity> PEBBLE = registerEntity("pebble", PebbleEntity::new, MobCategory.MISC, 0.25F, 0.25F);
    public static final EntityType<PineconeEntity> PINECONE = registerEntity("pinecone", PineconeEntity::new, MobCategory.MISC, 0.25F, 0.25F);
    public static final EntityType<LitPineconeEntity> LIT_PINECONE = registerEntity("lit_pinecone", LitPineconeEntity::new, MobCategory.MISC, 0.25F, 0.25F);
    public static final EntityType<BoulderEntity> BOULDER = registerEntity("boulder", BoulderEntity::new, MobCategory.MISC, 1f, 1f);
    public static final EntityType<SpearEntity> SPEAR = registerEntity("spear", SpearEntity::new, MobCategory.MISC, 0.5F, 0.5F);

    ///* Animals *///
    public static final EntityType<SwanEntity> SWAN = registerEntity("swan", SwanEntity::new, MobCategory.CREATURE, 0.6f, 0.9f);
    public static final EntityType<PheasantEntity> PHEASANT = registerEntity("pheasant", PheasantEntity::new, MobCategory.CREATURE, 0.6f, 0.8f);
    public static final EntityType<SnailEntity> SNAIL = registerEntity("snail", SnailEntity::new, MobCategory.CREATURE, 0.3f, 0.3f);
    public static final EntityType<DeerEntity> DEER = registerEntity("deer", DeerEntity::new, MobCategory.CREATURE, 1.3f, 1.8f);

    // Seat
    public static final EntityType<SeatEntity> SEAT_ENTITY = registerEntity("seat_entity", SeatEntity::new, MobCategory.MISC, 0.1F, 0.1F);

    public static <T extends Entity> EntityType<T> registerEntity(String name, EntityType.EntityFactory<T> entity, MobCategory spawnGroup,
                                                                  float width, float height) {
        return NeoForgeRegistrationBridge.register(BuiltInRegistries.ENTITY_TYPE,
                ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, name),
                EntityType.Builder.of(entity, spawnGroup).sized(width, height).build(MiddleEarth.MOD_ID + ":" + name));
    }

    public static void registerModEntities() {
        NeoForgeDefaultAttributeRegistry.register(BARROW_WIGHT, BarrowWightEntity::setAttributes);
        NeoForgeDefaultAttributeRegistry.register(STONE_TROLL, StoneTrollEntity::setAttributes);
        NeoForgeDefaultAttributeRegistry.register(PETRIFIED_TROLL, PetrifiedTrollEntity::setAttributes);
        NeoForgeDefaultAttributeRegistry.register(SNOW_TROLL, SnowTrollEntity::setAttributes);

        NeoForgeDefaultAttributeRegistry.register(HOBBIT_CIVILIAN, ShireHobbitEntity::setSoldierAttributes);
        NeoForgeDefaultAttributeRegistry.register(HOBBIT_BOUNDER, ShireHobbitEntity::setSoldierAttributes);
        NeoForgeDefaultAttributeRegistry.register(HOBBIT_SHIRRIFF, ShireHobbitEntity::setKnightAttributes);
        
        NeoForgeDefaultAttributeRegistry.register(GONDORIAN_MILITIA, GondorHumanEntity::setSoldierAttributes);
        NeoForgeDefaultAttributeRegistry.register(GONDORIAN_SOLDIER, GondorHumanEntity::setSoldierAttributes);
        NeoForgeDefaultAttributeRegistry.register(GONDORIAN_KNIGHT, GondorHumanEntity::setKnightAttributes);
        NeoForgeDefaultAttributeRegistry.register(GONDORIAN_VETERAN, GondorHumanEntity::setVeteranAttributes);
        NeoForgeDefaultAttributeRegistry.register(GONDORIAN_LEADER, GondorHumanEntity::setLeaderAttributes);
        
        NeoForgeDefaultAttributeRegistry.register(ROHIRRIM_MILITIA, RohanHumanEntity::setSoldierAttributes);
        NeoForgeDefaultAttributeRegistry.register(ROHIRRIM_SOLDIER, RohanHumanEntity::setSoldierAttributes);
        NeoForgeDefaultAttributeRegistry.register(ROHIRRIM_KNIGHT, RohanHumanEntity::setKnightAttributes);
        NeoForgeDefaultAttributeRegistry.register(ROHIRRIM_VETERAN, RohanHumanEntity::setVeteranAttributes);
        NeoForgeDefaultAttributeRegistry.register(ROHIRRIM_LEADER, RohanHumanEntity::setLeaderAttributes);

        NeoForgeDefaultAttributeRegistry.register(DALISH_MILITIA, DaleHumanEntity::setSoldierAttributes);
        NeoForgeDefaultAttributeRegistry.register(DALISH_SOLDIER, DaleHumanEntity::setSoldierAttributes);
        NeoForgeDefaultAttributeRegistry.register(DALISH_KNIGHT, DaleHumanEntity::setKnightAttributes);
        NeoForgeDefaultAttributeRegistry.register(DALISH_VETERAN, DaleHumanEntity::setVeteranAttributes);
        NeoForgeDefaultAttributeRegistry.register(DALISH_LEADER, DaleHumanEntity::setLeaderAttributes);

        NeoForgeDefaultAttributeRegistry.register(LONGBEARD_MILITIA, LongbeardDwarfEntity::setSoldierAttributes);
        NeoForgeDefaultAttributeRegistry.register(LONGBEARD_SOLDIER, LongbeardDwarfEntity::setSoldierAttributes);
        NeoForgeDefaultAttributeRegistry.register(LONGBEARD_ELITE, LongbeardDwarfEntity::setKnightAttributes);
        NeoForgeDefaultAttributeRegistry.register(LONGBEARD_VETERAN, LongbeardDwarfEntity::setVeteranAttributes);
        NeoForgeDefaultAttributeRegistry.register(LONGBEARD_LEADER, LongbeardDwarfEntity::setLeaderAttributes);

        NeoForgeDefaultAttributeRegistry.register(LORIEN_MILITIA, GaladhrimElfEntity::setSoldierAttributes);
        NeoForgeDefaultAttributeRegistry.register(LORIEN_SOLDIER, GaladhrimElfEntity::setSoldierAttributes);
        NeoForgeDefaultAttributeRegistry.register(LORIEN_KNIGHT, GaladhrimElfEntity::setKnightAttributes);
        NeoForgeDefaultAttributeRegistry.register(LORIEN_VETERAN, GaladhrimElfEntity::setVeteranAttributes);
        NeoForgeDefaultAttributeRegistry.register(LORIEN_LEADER, GaladhrimElfEntity::setLeaderAttributes);
        
        NeoForgeDefaultAttributeRegistry.register(MORDOR_ORC_SNAGA, MordorOrcEntity::setSoldierAttributes);
        NeoForgeDefaultAttributeRegistry.register(MORDOR_ORC_SOLDIER, MordorOrcEntity::setSoldierAttributes);
        NeoForgeDefaultAttributeRegistry.register(MORDOR_BLACK_URUK_SOLDIER, MordorBlackUrukEntity::setKnightAttributes);
        NeoForgeDefaultAttributeRegistry.register(MORDOR_BLACK_URUK_VETERAN, MordorBlackUrukEntity::setVeteranAttributes);
        NeoForgeDefaultAttributeRegistry.register(MORDOR_BLACK_URUK_LEADER, MordorBlackUrukEntity::setLeaderAttributes);

        NeoForgeDefaultAttributeRegistry.register(MISTY_GOBLIN_SNAGA, MistyGoblinEntity::setSoldierAttributes);
        NeoForgeDefaultAttributeRegistry.register(MISTY_GOBLIN_WARRIOR, MistyGoblinEntity::setSoldierAttributes);
        NeoForgeDefaultAttributeRegistry.register(MISTY_HOBGOBLIN_SOLDIER, MordorBlackUrukEntity::setKnightAttributes);
        NeoForgeDefaultAttributeRegistry.register(MISTY_HOBGOBLIN_VETERAN, MordorBlackUrukEntity::setVeteranAttributes);
        NeoForgeDefaultAttributeRegistry.register(MISTY_HOBGOBLIN_LEADER, MordorBlackUrukEntity::setLeaderAttributes);

        NeoForgeDefaultAttributeRegistry.register(ISENGARD_ORC_SNAGA, IsengardOrcEntity::setSoldierAttributes);
        NeoForgeDefaultAttributeRegistry.register(ISENGARD_ORC_WARRIOR, IsengardOrcEntity::setSoldierAttributes);
        NeoForgeDefaultAttributeRegistry.register(ISENGARD_URUK_HAI_SOLDIER, IsengardUrukHaiEntity::setKnightAttributes);
        NeoForgeDefaultAttributeRegistry.register(ISENGARD_URUK_HAI_VETERAN, IsengardUrukHaiEntity::setVeteranAttributes);
        NeoForgeDefaultAttributeRegistry.register(ISENGARD_URUK_HAI_LEADER, IsengardUrukHaiEntity::setLeaderAttributes);

        NeoForgeDefaultAttributeRegistry.register(BROADHOOF_GOAT, BroadhoofGoatEntity::setAttributes);

        NeoForgeDefaultAttributeRegistry.register(WARG, WargEntity::setAttributes);

        NeoForgeDefaultAttributeRegistry.register(MIRKWOOD_SPIDER, MirkwoodSpiderEntity::setAttributes);

        NeoForgeDefaultAttributeRegistry.register(STONE_TROLL, StoneTrollEntity::setAttributes);
        NeoForgeDefaultAttributeRegistry.register(PETRIFIED_TROLL, PetrifiedTrollEntity::setAttributes);

        NeoForgeDefaultAttributeRegistry.register(SNOW_TROLL, SnowTrollEntity::setAttributes);

        NeoForgeDefaultAttributeRegistry.register(BANDIT_MILITIA, BanditHumanEntity::setSoldierAttributes);
        NeoForgeDefaultAttributeRegistry.register(BANDIT_SOLDIER, BanditHumanEntity::setSoldierAttributes);
        NeoForgeDefaultAttributeRegistry.register(BANDIT_CHIEFTAIN, BanditHumanEntity::setKnightAttributes);
        NeoForgeDefaultAttributeRegistry.register(WILD_GOBLIN, WildGoblinEntity::setSoldierAttributes);
        // Animals
        NeoForgeDefaultAttributeRegistry.register(SWAN, SwanEntity::createSwanAttributes);
        NeoForgeDefaultAttributeRegistry.register(PHEASANT, PheasantEntity::createPheasantAttributes);
        NeoForgeDefaultAttributeRegistry.register(SNAIL, SnailEntity::createSnailAttributes);
        NeoForgeDefaultAttributeRegistry.register(DEER, DeerEntity::createDeerAttributes);

        LoggerUtil.logDebugMsg("Registering Mod Entities for " + MiddleEarth.MOD_ID);
    }

    public static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
        event.register(DEER, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(SNAIL, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(PHEASANT, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(SWAN, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(BROADHOOF_GOAT, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(WARG, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(STONE_TROLL, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(MIRKWOOD_SPIDER, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(WILD_GOBLIN, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
        if (shouldRegisterVanillaCamelSpawnPlacement()) {
            event.register(EntityType.CAMEL, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
        } else {
            LoggerUtil.logDebugMsg("Skipping vanilla camel spawn placement because VanillaBackport is loaded.");
        }
    }

    private static boolean shouldRegisterVanillaCamelSpawnPlacement() {
        ModList modList = ModList.get();
        return !modList.isLoaded("vanillabackport");
    }
}
