package net.jukoz.me;

import net.jukoz.me.utils.NeoForgeRegistrationBridge;

import net.jukoz.me.compat.neoforge.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.jukoz.me.compat.neoforge.api.client.particle.v1.ParticleFactoryRegistry;
import net.jukoz.me.compat.neoforge.api.client.rendering.v1.*;
import net.jukoz.me.block.*;
import net.jukoz.me.block.special.bellows.BellowsBlockEntityRenderer;
import net.jukoz.me.block.special.fire_of_orthanc.FireOfOrthancEntityRenderer;
import net.jukoz.me.block.special.forge.ForgeEntityRenderer;
import net.jukoz.me.block.special.reinforcedChest.ReinforcedChestEntityRenderer;
import net.jukoz.me.block.special.shapingAnvil.ShapingAnvilEntityRenderer;
import net.jukoz.me.client.model.equipment.CustomBootsModel;
import net.jukoz.me.client.model.equipment.CustomChestplateModel;
import net.jukoz.me.client.model.equipment.CustomHelmetModel;
import net.jukoz.me.client.model.equipment.CustomLeggingsModel;
import net.jukoz.me.client.model.equipment.chest.capes.armored.CapeMediumModel;
import net.jukoz.me.client.model.equipment.head.helmets.RohirricHelmetArmorAddonModel;
import net.jukoz.me.client.model.equipment.head.hoods.armored.HoodModel;
import net.jukoz.me.client.model.hand.HeldBannerEntityModel;
import net.jukoz.me.client.model.hand.shields.HeaterShieldEntityModel;
import net.jukoz.me.client.model.hand.shields.KiteShieldEntityModel;
import net.jukoz.me.client.model.hand.shields.RoundShieldEntityModel;
import net.jukoz.me.client.renderer.*;
import net.jukoz.me.datageneration.VariantsModelProvider;
import net.jukoz.me.datageneration.content.models.*;
import net.jukoz.me.datageneration.content.tags.Crops;
import net.jukoz.me.entity.ModEntities;
import net.jukoz.me.entity.barrow_wights.BarrowWightEntityRenderer;
import net.jukoz.me.entity.beasts.broadhoof.BroadhoofGoatRenderer;
import net.jukoz.me.entity.beasts.trolls.petrified.PetrifiedTrollRenderer;
import net.jukoz.me.entity.beasts.trolls.snow.SnowTrollRenderer;
import net.jukoz.me.entity.beasts.trolls.stone.StoneTrollRenderer;
import net.jukoz.me.entity.beasts.warg.WargRenderer;
import net.jukoz.me.entity.deer.DeerRenderer;
import net.jukoz.me.entity.dwarves.longbeards.LongbeardDwarfRenderer;
import net.jukoz.me.entity.elves.galadhrim.GaladhrimElfRenderer;
import net.jukoz.me.entity.hobbits.shire.ShireHobbitRenderer;
import net.jukoz.me.entity.humans.bandit.BanditHumanRenderer;
import net.jukoz.me.entity.humans.dale.DaleHumanRenderer;
import net.jukoz.me.entity.humans.gondor.GondorHumanRenderer;
import net.jukoz.me.entity.humans.rohan.RohanHumanRenderer;
import net.jukoz.me.entity.model.ModEntityModels;
import net.jukoz.me.entity.orcs.isengard.IsengardOrcRenderer;
import net.jukoz.me.entity.orcs.misties.MistyGoblinRenderer;
import net.jukoz.me.entity.orcs.mordor.MordorOrcRenderer;
import net.jukoz.me.entity.orcs.wild.WildGoblinRenderer;
import net.jukoz.me.entity.pheasant.PheasantRenderer;
import net.jukoz.me.entity.projectile.boulder.BoulderEntityRenderer;
import net.jukoz.me.entity.projectile.spear.SpearEntityRenderer;
import net.jukoz.me.entity.seat.SeatRenderer;
import net.jukoz.me.entity.snail.SnailRenderer;
import net.jukoz.me.entity.spider.MirkwoodSpiderRenderer;
import net.jukoz.me.entity.swan.SwanRenderer;
import net.jukoz.me.entity.uruks.isengard.IsengardUrukHaiRenderer;
import net.jukoz.me.entity.uruks.misties.MistyHobgoblinRenderer;
import net.jukoz.me.entity.uruks.mordor.MordorBlackUrukRenderer;
import net.jukoz.me.event.KeyInputHandler;
import net.jukoz.me.gui.ModScreenHandlers;
import net.jukoz.me.gui.artisantable.ArtisanTableScreen;
import net.jukoz.me.gui.forge.ForgeAlloyingScreen;
import net.jukoz.me.gui.forge.ForgeHeatingScreen;
import net.jukoz.me.gui.shapinganvil.ShapingAnvilScreen;
import net.jukoz.me.gui.wood_pile.WoodPileScreen;
import net.jukoz.me.item.ModEquipmentItems;
import net.jukoz.me.item.ModResourceItems;
import net.jukoz.me.item.ModWeaponItems;
import net.jukoz.me.item.dataComponents.CustomDyeableDataComponent;
import net.jukoz.me.item.utils.ModModelPredicateProvider;
import net.jukoz.me.item.utils.armor.ModArmorModels;
import net.jukoz.me.network.ModClientNetworkHandler;
import net.jukoz.me.network.connections.ConnectionToServer;
import net.jukoz.me.particles.ModParticleTypes;
import net.jukoz.me.particles.custom.AnvilBonkParticle;
import net.jukoz.me.particles.custom.LeavesParticle;
import net.jukoz.me.particles.custom.RingOfSmokeParticle;
import net.jukoz.me.utils.LoggerUtil;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockModelShaper;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.util.RandomSource;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

public class MiddleEarthClient {
    private static final boolean AUDIT_MODELS = Boolean.getBoolean("middleearth.auditModels");
    private static final int MODEL_AUDIT_SAMPLE_LIMIT = 12;
    private static final java.util.Set<Item> DYEABLE_COLOR_ITEMS = new java.util.LinkedHashSet<>();
    
    public static final ModelLayerLocation CUSTOM_ARMOR_HELMET = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "armor"), "_1");
    public static final ModelLayerLocation CUSTOM_ARMOR_CHESTPLATE = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "armor"), "_2");
    public static final ModelLayerLocation CUSTOM_ARMOR_LEGGINGS = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "armor"), "_3");
    public static final ModelLayerLocation CUSTOM_ARMOR_BOOTS = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "armor"), "_4");
    public static final ModelLayerLocation HELMET_ADDON_MODEL_LAYER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "armor"), "helmet_addon");
    public static final ModelLayerLocation CAPE_MODEL_LAYER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "armor"), "cape");
    public static final ModelLayerLocation HOOD_MODEL_LAYER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "armor"), "hood");

    public static final ModelLayerLocation HEATER_SHIELD_LAYER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "heater_shield"), "main");
    public static final ModelLayerLocation KITE_SHIELD_LAYER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "kite_shield"), "main");
    public static final ModelLayerLocation ROUND_SHIELD_LAYER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "round_shield"), "main");

    public static final ModelLayerLocation HELD_BANNER_LAYER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "held_banner"), "main");

    public static void registerClientSetup(IEventBus modEventBus) {
        registerEarlyClientRenderers();
        KeyInputHandler.register(modEventBus);
        modEventBus.addListener(MiddleEarthClient::onClientSetup);
        modEventBus.addListener(MiddleEarthClient::registerMenuScreens);
        modEventBus.addListener(MiddleEarthClient::registerBlockColors);
        modEventBus.addListener(MiddleEarthClient::registerItemColors);
        modEventBus.addListener(MiddleEarthClient::registerLayerDefinitions);
        modEventBus.addListener(MiddleEarthClient::registerAdditionalModels);
        modEventBus.addListener(MiddleEarthClient::repairMissingBlockItemModels);
        modEventBus.addListener(MiddleEarthClient::auditBakedModels);
        modEventBus.addListener(ParticleFactoryRegistry::registerProviders);
        modEventBus.addListener(BuiltinItemRendererRegistry::registerClientExtensions);
    }

    private static void registerEarlyClientRenderers() {
        BuiltinItemRendererRegistry.INSTANCE.register(ModWeaponItems.HEATER_SHIELD, new ModBuiltInModelItemRenderer());
        BuiltinItemRendererRegistry.INSTANCE.register(ModWeaponItems.KITE_SHIELD, new ModBuiltInModelItemRenderer());
        BuiltinItemRendererRegistry.INSTANCE.register(ModWeaponItems.ROUND_SHIELD, new ModBuiltInModelItemRenderer());
        BuiltinItemRendererRegistry.INSTANCE.register(ModWeaponItems.HELD_BANNER, new ModBuiltInModelItemRenderer());

        for(ModArmorModels.ModHelmetModels model : ModArmorModels.ModHelmetModels.values()){
            ArmorRenderer.register(new HelmetArmorRenderer(model.getModel()), model.getItem());
        }
        for(ModArmorModels.ModChestplateModels model : ModArmorModels.ModChestplateModels.values()){
            ArmorRenderer.register(new ChestplateArmorRenderer(model.getModel()), model.getItem());
        }
        ModEquipmentItems.armorPiecesListHelmets.forEach(armor -> ArmorRenderer.register(new HelmetArmorRenderer(), armor.asItem()));
        ModEquipmentItems.armorPiecesListChestplates.forEach(armor -> ArmorRenderer.register(new ChestplateArmorRenderer(), armor.asItem()));
        ModEquipmentItems.armorPiecesListLeggings.forEach(armor -> ArmorRenderer.register(new LeggingsArmorRenderer(), armor.asItem()));
        ModEquipmentItems.armorPiecesListBoots.forEach(armor -> ArmorRenderer.register(new BootsArmorRenderer(), armor.asItem()));
        ModEquipmentItems.hoods.forEach(hood -> ArmorRenderer.register(new HoodRenderer(), hood));
        ModEquipmentItems.capes.forEach(cape -> ArmorRenderer.register(new CapeRenderer(), cape));

        ParticleFactoryRegistry.getInstance().register(ModParticleTypes.MALLORN_LEAVES_PARTICLE, LeavesParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticleTypes.MIRKWOOD_LEAVES_PARTICLE, LeavesParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticleTypes.ANVIL_SPARK_PARTICLE, AnvilBonkParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticleTypes.RING_OF_SMOKE, RingOfSmokeParticle.Factory::new);
    }

    private static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> new MiddleEarthClient().onInitializeClient());
    }

    private static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        registerModelLayers();
        EntityModelLayerRegistry.registerLayerDefinitions(event);
    }

    private static void registerAdditionalModels(ModelEvent.RegisterAdditional event) {
        registerDynamicItemModels(identifier -> event.register(ModelResourceLocation.standalone(identifier)));
    }

    private static void repairMissingBlockItemModels(ModelEvent.ModifyBakingResult event) {
        BakedModel missingModel = event.getModels().get(ModelBakery.MISSING_MODEL_VARIANT);
        int checked = 0;
        int repaired = 0;
        int missingBlockModels = 0;
        List<ResourceLocation> repairedSamples = new ArrayList<>();
        List<ResourceLocation> missingBlockSamples = new ArrayList<>();

        for (Item item : BuiltInRegistries.ITEM) {
            ResourceLocation itemId = BuiltInRegistries.ITEM.getKey(item);
            if (!MiddleEarth.MOD_ID.equals(itemId.getNamespace()) || !(item instanceof BlockItem blockItem)) {
                continue;
            }

            checked++;
            ModelResourceLocation itemModelId = ModelResourceLocation.inventory(itemId);
            BakedModel itemModel = event.getModels().get(itemModelId);
            if (itemModel != null && itemModel != missingModel) {
                continue;
            }

            ModelResourceLocation blockStateModelId = BlockModelShaper.stateToModelLocation(blockItem.getBlock().defaultBlockState());
            BakedModel blockModel = event.getModels().get(blockStateModelId);
            if (blockModel == null || blockModel == missingModel) {
                missingBlockModels++;
                if (missingBlockSamples.size() < MODEL_AUDIT_SAMPLE_LIMIT) {
                    missingBlockSamples.add(itemId);
                }
                continue;
            }

            event.getModels().put(itemModelId, blockModel);
            repaired++;
            if (repairedSamples.size() < MODEL_AUDIT_SAMPLE_LIMIT) {
                repairedSamples.add(itemId);
            }
        }

        if (repaired > 0 || missingBlockModels > 0 || AUDIT_MODELS) {
            LoggerUtil.logWarn("Middle-earth block item model repair: checked=" + checked
                    + ", repaired=" + repaired
                    + ", missingBlockModels=" + missingBlockModels
                    + ", repairedSamples=" + repairedSamples
                    + ", missingBlockSamples=" + missingBlockSamples);
        }
    }

    private static void auditBakedModels(ModelEvent.BakingCompleted event) {
        if (!AUDIT_MODELS) {
            return;
        }

        BakedModel missingModel = event.getModelManager().getMissingModel();
        int blockItems = 0;
        int missingBlockItemModels = 0;
        List<ResourceLocation> missingSamples = new ArrayList<>();
        List<String> textureSamples = new ArrayList<>();

        for (Item item : BuiltInRegistries.ITEM) {
            ResourceLocation itemId = BuiltInRegistries.ITEM.getKey(item);
            if (!MiddleEarth.MOD_ID.equals(itemId.getNamespace()) || !(item instanceof BlockItem)) {
                continue;
            }

            blockItems++;
            BakedModel model = event.getModels().get(ModelResourceLocation.inventory(itemId));
            if (model == null || model == missingModel) {
                missingBlockItemModels++;
                if (missingSamples.size() < MODEL_AUDIT_SAMPLE_LIMIT) {
                    missingSamples.add(itemId);
                }
            } else if (shouldSampleModelTexture(itemId)) {
                textureSamples.add(itemId + " particle=" + model.getParticleIcon().contents().name()
                        + " quads=" + getModelSpriteSample(model));
            }
        }

        LoggerUtil.logInfoMsg("Middle-earth baked model audit: blockItems=" + blockItems
                + ", missingBlockItemModels=" + missingBlockItemModels
                + ", missingSamples=" + missingSamples
                + ", textureSamples=" + textureSamples);
    }

    private static boolean shouldSampleModelTexture(ResourceLocation itemId) {
        String path = itemId.getPath();
        return path.equals("mossy_smooth_dolomite")
                || path.equals("mossy_cobbled_dolomite")
                || path.equals("mossy_dolomite_bricks")
                || path.equals("dark_cyan_roof_tiles");
    }

    private static Set<ResourceLocation> getModelSpriteSample(BakedModel model) {
        Set<ResourceLocation> sprites = new LinkedHashSet<>();
        collectModelSprites(model, null, sprites);
        for (Direction direction : Direction.values()) {
            collectModelSprites(model, direction, sprites);
        }
        return sprites;
    }

    private static void collectModelSprites(BakedModel model, Direction direction, Set<ResourceLocation> sprites) {
        for (var quad : model.getQuads(null, direction, RandomSource.create(0L))) {
            sprites.add(quad.getSprite().contents().name());
            if (sprites.size() >= MODEL_AUDIT_SAMPLE_LIMIT) {
                return;
            }
        }
    }

    private static void registerDynamicItemModels(Consumer<ResourceLocation> registrar) {
        for(Item item : SimpleBigItemModel.items) {
            registrar.accept(VariantsModelProvider.getInventoryModelIdentifierVariant(item));
        }

        for(Item item : SimpleBigItemModel.bigBows) {
            registrar.accept(VariantsModelProvider.getInventoryModelIdentifierVariant(item));
            registrar.accept(VariantsModelProvider.getInventoryLongbowModelIdentifierVariant(item, 0));
            registrar.accept(VariantsModelProvider.getInventoryLongbowModelIdentifierVariant(item, 1));
            registrar.accept(VariantsModelProvider.getInventoryLongbowModelIdentifierVariant(item, 2));
        }

        for(Item item : SimpleBigItemModel.artefacts) {
            registrar.accept(VariantsModelProvider.getInventoryModelIdentifierVariant(item));
        }

        for(Item item : SimpleBigItemModel.artefactsGlowing) {
            registrar.accept(VariantsModelProvider.getInventoryModelGlowingItem(item));
        }

        for(Item item : SimpleBigItemModel.artefactsBroken) {
            registrar.accept(VariantsModelProvider.getInventoryModelBrokenItem(item));
        }

        for(Item item : SimpleSpearModel.items) {
            registrar.accept(VariantsModelProvider.getInventoryModelIdentifierVariant(item));
        }

        for(Item item : SimpleBigItemModel.genericItems){
            registrar.accept(VariantsModelProvider.getInventoryModelIdentifierVariant(item));
        }

        for(Item item : HotMetalsModel.ingots) {
            registrar.accept(VariantsModelProvider.getHotModelIdentifierVariant(item));
        }

        for(Item item : HotMetalsModel.nuggets) {
            registrar.accept(VariantsModelProvider.getHotModelIdentifierVariant(item));
        }

        for(Item item : HotMetalsModel.items) {
            registrar.accept(VariantsModelProvider.getHotModelIdentifierVariant(item));
        }
    }

    private static void registerModelLayers() {
        ModEntityModels.getModels();

        EntityModelLayerRegistry.registerModelLayer(CUSTOM_ARMOR_HELMET, CustomHelmetModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(CUSTOM_ARMOR_CHESTPLATE, CustomChestplateModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(CUSTOM_ARMOR_LEGGINGS, CustomLeggingsModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(CUSTOM_ARMOR_BOOTS, CustomBootsModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(HELMET_ADDON_MODEL_LAYER, RohirricHelmetArmorAddonModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(CAPE_MODEL_LAYER, CapeMediumModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(HOOD_MODEL_LAYER, HoodModel::getTexturedModelData);

        EntityModelLayerRegistry.registerModelLayer(HEATER_SHIELD_LAYER, HeaterShieldEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(KITE_SHIELD_LAYER, KiteShieldEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(ROUND_SHIELD_LAYER, RoundShieldEntityModel::getTexturedModelData);

        EntityModelLayerRegistry.registerModelLayer(HELD_BANNER_LAYER, HeldBannerEntityModel::getTexturedModelData);
    }

    public void onInitializeClient() {
        ModClientNetworkHandler.register(new ConnectionToServer());

        EntityRendererRegistry.register(ModEntities.BARROW_WIGHT, BarrowWightEntityRenderer::new);
        // Entities
        EntityRendererRegistry.register(ModEntities.HOBBIT_CIVILIAN, ShireHobbitRenderer::new);
        EntityRendererRegistry.register(ModEntities.HOBBIT_BOUNDER, ShireHobbitRenderer::new);
        EntityRendererRegistry.register(ModEntities.HOBBIT_SHIRRIFF, ShireHobbitRenderer::new);

        EntityRendererRegistry.register(ModEntities.GONDORIAN_MILITIA, GondorHumanRenderer::new);
        EntityRendererRegistry.register(ModEntities.GONDORIAN_SOLDIER, GondorHumanRenderer::new);
        EntityRendererRegistry.register(ModEntities.GONDORIAN_KNIGHT, GondorHumanRenderer::new);
        EntityRendererRegistry.register(ModEntities.GONDORIAN_VETERAN, GondorHumanRenderer::new);
        EntityRendererRegistry.register(ModEntities.GONDORIAN_LEADER, GondorHumanRenderer::new);

        EntityRendererRegistry.register(ModEntities.ROHIRRIM_MILITIA, RohanHumanRenderer::new);
        EntityRendererRegistry.register(ModEntities.ROHIRRIM_SOLDIER, RohanHumanRenderer::new);
        EntityRendererRegistry.register(ModEntities.ROHIRRIM_KNIGHT, RohanHumanRenderer::new);
        EntityRendererRegistry.register(ModEntities.ROHIRRIM_VETERAN, RohanHumanRenderer::new);
        EntityRendererRegistry.register(ModEntities.ROHIRRIM_LEADER, RohanHumanRenderer::new);

        EntityRendererRegistry.register(ModEntities.DALISH_MILITIA, DaleHumanRenderer::new);
        EntityRendererRegistry.register(ModEntities.DALISH_SOLDIER, DaleHumanRenderer::new);
        EntityRendererRegistry.register(ModEntities.DALISH_KNIGHT, DaleHumanRenderer::new);
        EntityRendererRegistry.register(ModEntities.DALISH_VETERAN, DaleHumanRenderer::new);
        EntityRendererRegistry.register(ModEntities.DALISH_LEADER, DaleHumanRenderer::new);

        EntityRendererRegistry.register(ModEntities.LONGBEARD_MILITIA, LongbeardDwarfRenderer::new);
        EntityRendererRegistry.register(ModEntities.LONGBEARD_SOLDIER, LongbeardDwarfRenderer::new);
        EntityRendererRegistry.register(ModEntities.LONGBEARD_ELITE, LongbeardDwarfRenderer::new);
        EntityRendererRegistry.register(ModEntities.LONGBEARD_VETERAN, LongbeardDwarfRenderer::new);
        EntityRendererRegistry.register(ModEntities.LONGBEARD_LEADER, LongbeardDwarfRenderer::new);

        EntityRendererRegistry.register(ModEntities.LORIEN_MILITIA, GaladhrimElfRenderer::new);
        EntityRendererRegistry.register(ModEntities.LORIEN_SOLDIER, GaladhrimElfRenderer::new);
        EntityRendererRegistry.register(ModEntities.LORIEN_KNIGHT, GaladhrimElfRenderer::new);
        EntityRendererRegistry.register(ModEntities.LORIEN_VETERAN, GaladhrimElfRenderer::new);
        EntityRendererRegistry.register(ModEntities.LORIEN_LEADER, GaladhrimElfRenderer::new);
        
        EntityRendererRegistry.register(ModEntities.MORDOR_ORC_SNAGA, MordorOrcRenderer::new);
        EntityRendererRegistry.register(ModEntities.MORDOR_ORC_SOLDIER, MordorOrcRenderer::new);
        EntityRendererRegistry.register(ModEntities.MORDOR_BLACK_URUK_SOLDIER, MordorBlackUrukRenderer::new);
        EntityRendererRegistry.register(ModEntities.MORDOR_BLACK_URUK_VETERAN, MordorBlackUrukRenderer::new);
        EntityRendererRegistry.register(ModEntities.MORDOR_BLACK_URUK_LEADER, MordorBlackUrukRenderer::new);
        
        EntityRendererRegistry.register(ModEntities.MISTY_GOBLIN_SNAGA, MistyGoblinRenderer::new);
        EntityRendererRegistry.register(ModEntities.MISTY_GOBLIN_WARRIOR, MistyGoblinRenderer::new);
        EntityRendererRegistry.register(ModEntities.MISTY_HOBGOBLIN_SOLDIER, MistyHobgoblinRenderer::new);
        EntityRendererRegistry.register(ModEntities.MISTY_HOBGOBLIN_VETERAN, MistyHobgoblinRenderer::new);
        EntityRendererRegistry.register(ModEntities.MISTY_HOBGOBLIN_LEADER, MistyHobgoblinRenderer::new);

        EntityRendererRegistry.register(ModEntities.ISENGARD_ORC_SNAGA, IsengardOrcRenderer::new);
        EntityRendererRegistry.register(ModEntities.ISENGARD_ORC_WARRIOR, IsengardOrcRenderer::new);
        EntityRendererRegistry.register(ModEntities.ISENGARD_URUK_HAI_SOLDIER, IsengardUrukHaiRenderer::new);
        EntityRendererRegistry.register(ModEntities.ISENGARD_URUK_HAI_VETERAN, IsengardUrukHaiRenderer::new);
        EntityRendererRegistry.register(ModEntities.ISENGARD_URUK_HAI_LEADER, IsengardUrukHaiRenderer::new);

        EntityRendererRegistry.register(ModEntities.STONE_TROLL, StoneTrollRenderer::new);
        EntityRendererRegistry.register(ModEntities.PETRIFIED_TROLL, PetrifiedTrollRenderer::new);

        EntityRendererRegistry.register(ModEntities.BROADHOOF_GOAT, BroadhoofGoatRenderer::new);

        EntityRendererRegistry.register(ModEntities.WARG, WargRenderer::new);
        EntityRendererRegistry.register(ModEntities.MIRKWOOD_SPIDER, MirkwoodSpiderRenderer::new);
        EntityRendererRegistry.register(ModEntities.SNOW_TROLL, SnowTrollRenderer::new);
        //EntityRendererRegistry.register(ModEntities.BALROG, BalrogRenderer::new);

        EntityRendererRegistry.register(ModEntities.BANDIT_MILITIA, BanditHumanRenderer::new);
        EntityRendererRegistry.register(ModEntities.BANDIT_SOLDIER, BanditHumanRenderer::new);
        EntityRendererRegistry.register(ModEntities.BANDIT_CHIEFTAIN, BanditHumanRenderer::new);
        EntityRendererRegistry.register(ModEntities.WILD_GOBLIN, WildGoblinRenderer::new);

        EntityRendererRegistry.register(ModEntities.FIRE_OF_ORTHANC, FireOfOrthancEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.PEBBLE, ThrownItemRenderer::new);
        EntityRendererRegistry.register(ModEntities.PINECONE, ThrownItemRenderer::new);
        EntityRendererRegistry.register(ModEntities.LIT_PINECONE, ThrownItemRenderer::new);
        EntityRendererRegistry.register(ModEntities.SPEAR, SpearEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.BOULDER, BoulderEntityRenderer::new);

        SimpleDyeableItemModel.items.forEach(this::registerDyeableItem);

        registerDyeableItem(ModEquipmentItems.BROADHOOF_GOAT_PADDED_ARMOR);
        registerDyeableItem(ModEquipmentItems.BROADHOOF_GOAT_ORNAMENTED_PADDED_ARMOR);

        registerDyeableItem(ModEquipmentItems.WARG_LEATHER_ARMOR);
        registerDyeableItem(ModEquipmentItems.WARG_REINFORCED_LEATHER_ARMOR);


        // Animals
        EntityRendererRegistry.register(ModEntities.SWAN, SwanRenderer::new);
        EntityRendererRegistry.register(ModEntities.PHEASANT, PheasantRenderer::new);
        EntityRendererRegistry.register(ModEntities.SNAIL, SnailRenderer::new);
        EntityRendererRegistry.register(ModEntities.DEER, DeerRenderer::new);

        EntityRendererRegistry.register(ModEntities.SEAT_ENTITY, SeatRenderer::new);

        ModModelPredicateProvider.registerAllPredicates();

        BlockEntityRenderers.register(ModBlockEntities.TREATED_ANVIL, ShapingAnvilEntityRenderer::new);
        BlockEntityRenderers.register(ModBlockEntities.FORGE, ForgeEntityRenderer::new);
        BlockEntityRenderers.register(ModBlockEntities.REINFORCED_CHEST, ReinforcedChestEntityRenderer::new);
        BlockEntityRenderers.register(ModBlockEntities.BELLOWS, BellowsBlockEntityRenderer::new);

        BuiltinItemRendererRegistry.INSTANCE.register(ModWeaponItems.HEATER_SHIELD, new ModBuiltInModelItemRenderer());
        BuiltinItemRendererRegistry.INSTANCE.register(ModWeaponItems.KITE_SHIELD, new ModBuiltInModelItemRenderer());
        BuiltinItemRendererRegistry.INSTANCE.register(ModWeaponItems.ROUND_SHIELD, new ModBuiltInModelItemRenderer());

        BuiltinItemRendererRegistry.INSTANCE.register(ModWeaponItems.HELD_BANNER, new ModBuiltInModelItemRenderer());

        for(ModArmorModels.ModHelmetModels model : ModArmorModels.ModHelmetModels.values()){
            ArmorRenderer.register(new HelmetArmorRenderer(model.getModel()), model.getItem());
        }

        for(ModArmorModels.ModChestplateModels model : ModArmorModels.ModChestplateModels.values()){
            ArmorRenderer.register(new ChestplateArmorRenderer(model.getModel()), model.getItem());
        }

        ModEquipmentItems.armorPiecesListHelmets.forEach(armor -> {
            ArmorRenderer.register(new HelmetArmorRenderer(), armor.asItem());
        });
        ModEquipmentItems.armorPiecesListChestplates.forEach(armor -> {
            ArmorRenderer.register(new ChestplateArmorRenderer(), armor.asItem());
        });
        ModEquipmentItems.armorPiecesListLeggings.forEach(armor -> {
            ArmorRenderer.register(new LeggingsArmorRenderer(), armor.asItem());
        });
        ModEquipmentItems.armorPiecesListBoots.forEach(armor -> {
            ArmorRenderer.register(new BootsArmorRenderer(), armor.asItem());
        });

        ModEquipmentItems.hoods.forEach(hood -> {
            ArmorRenderer.register(new HoodRenderer(), hood);
        });
        ModEquipmentItems.capes.forEach(cape -> {
            ArmorRenderer.register(new CapeRenderer(), cape);
        });

        ParticleFactoryRegistry.getInstance().register(ModParticleTypes.MALLORN_LEAVES_PARTICLE, LeavesParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticleTypes.MIRKWOOD_LEAVES_PARTICLE, LeavesParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticleTypes.ANVIL_SPARK_PARTICLE, AnvilBonkParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticleTypes.RING_OF_SMOKE, RingOfSmokeParticle.Factory::new);

        initializeRenderLayerMap();
    }

    private void initializeRenderLayerMap() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.MORGUL_IVY, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.HANGING_COBWEB, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.CORNER_COBWEB, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.MIRKWOOD_SPIDER_EGG, RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.BROWN_GRASS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.GREEN_SHRUB, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.SMALL_DRY_SHRUB, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.FROZEN_SHRUB, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.MALLOS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.ELANOR, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.TAN_SHRUB, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.STRAWBERRY_BUSH, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.TOUGH_BERRY_BUSH, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.YELLOW_FLOWER, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.GREEN_JEWEL_CORNFLOWER, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.MIRKWOOD_ROOTS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.MIRKWOOD_HANGING_ROOTS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.PINE_BRANCHES, RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.LIGHT_BLUE_FLOWERS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.MAGENTA_FLOWERS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.ORANGE_FLOWERS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.PINK_FLOWERS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.PURPLE_FLOWERS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.RED_FLOWERS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.WHITE_FLOWERS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.YELLOW_FLOWERS, RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.LAVENDER, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.YELLOW_TROLLIUS, RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.DRY_GRASS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.DYING_GRASS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.FROZEN_GRASS, RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.GRIM_GRASS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.TEMPERATE_GRASS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.GRASS_TUFT, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.FROZEN_TUFT, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.HEATHER, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.RED_HEATHER, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.DEAD_HEATHER, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.DRY_HEATHER, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.HEATH, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.WHEATGRASS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.WILD_GRASS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.WILDERGRASS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.BEACH_GRASS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.COASTAL_PANIC_GRASS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.SEDUM, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.YELLOW_SEDUM, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.BRACKEN, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.DEAD_RUSHES, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.FALSE_OATGRASS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.SHORT_CATTAILS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.SHORT_BULRUSH, RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.SHRIVELED_SHRUB, RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.SCORCHED_GRASS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.SCORCHED_TUFT, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.SCORCHED_SHRUB, RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.HOROKAKA, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.GIANT_HOROKAKA, RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.SMALL_LILY_PADS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.SMALL_FLOWERING_LILY_PADS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.LILY_PADS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.DUCKWEED, RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.FLOATING_ICE, RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GRASSY_DIRT, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GRASSY_DIRT_SLAB, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GRASSY_DIRT_STAIRS, RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PEBBLED_GRASS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PEBBLED_GRASS_SLAB, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PEBBLED_GRASS_STAIRS, RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.FIRE_OF_ORTHANC, RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POINTED_LIMESTONE, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POINTED_GALONN, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POINTED_IZHERABAN, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POINTED_DOLOMITE, RenderType.cutout());

        for(Block block : SimpleDoubleBlockModel.doubleBlocks){
            BlockRenderLayerMap.INSTANCE.putBlock(block, RenderType.cutout());
        }

        for(Block block : SimpleFlowerBedModel.flowerBeds){
            BlockRenderLayerMap.INSTANCE.putBlock(block, RenderType.cutout());
        }

        for(SimpleFlowerPotModel.FlowerPot flowerPot : SimpleFlowerPotModel.pots){
            BlockRenderLayerMap.INSTANCE.putBlock(flowerPot.pottedPlant(), RenderType.cutout());
        }

        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.MIRKWOOD_SAPLING, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.WHITE_MUSHROOM, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.WHITE_MUSHROOM_TILLER, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.MOSS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.STICKY_SNOW, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.STICKY_ICE, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.FOREST_MOSS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.CORRUPTED_MOSS, RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.SHORT_ICICLES, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.DROOPING_ICICLES, RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.TALL_BLACK_PINE_DOOR, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.REINFORCED_BLACK_PINE_DOOR, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.RICKETY_SIMPLE_LARCH_DOOR, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.SPRUCE_STABLE_DOOR, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.RUINED_DWARVEN_DOOR, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.LIGHT_BLUE_HOBBIT_DOOR, RenderType.cutout());

        for (Block block : Crops.crops){
            BlockRenderLayerMap.INSTANCE.putBlock(block, RenderType.cutout());
        }

        for(Block block : TintableCrossModel.notTintedBlocks) {
            if(block != null) BlockRenderLayerMap.INSTANCE.putBlock(block, RenderType.cutout());
        }
        for(Block block : TintableCrossModel.tintedBlocks) {
            BlockRenderLayerMap.INSTANCE.putBlock(block, RenderType.cutout());
        }

        BlockRenderLayerMap.INSTANCE.putBlock(WoodBlockSets.BEECH.ladder(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(WoodBlockSets.LARCH.ladder(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(WoodBlockSets.BLACK_LEBETHRON.ladder(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(WoodBlockSets.WHITE_LEBETHRON.ladder(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(WoodBlockSets.CHESTNUT.ladder(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(WoodBlockSets.FIR.ladder(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(WoodBlockSets.HOLLY.ladder(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(WoodBlockSets.MALLORN.ladder(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(WoodBlockSets.MAPLE.ladder(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(WoodBlockSets.SILVER_MAPLE.ladder(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(WoodBlockSets.MIRKWOOD.ladder(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(WoodBlockSets.PALM.ladder(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(WoodBlockSets.WHITE_PALM.ladder(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(WoodBlockSets.PINE.ladder(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(WoodBlockSets.BLACK_PINE.ladder(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(WoodBlockSets.WILLOW.ladder(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(WoodBlockSets.SCORCHED.ladder(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(MushroomBlockSets.MUSHROOM.ladder(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(MushroomBlockSets.DARK_MUSHROOM.ladder(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(MushroomBlockSets.GRAY_MUSHROOM.ladder(), RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.TREATED_WOOD_LADDER, RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.ROPE_LADDER, RenderType.cutout());

        for(SimpleLadderModel.Ladder block : SimpleLadderModel.vanillaLadders) {
            BlockRenderLayerMap.INSTANCE.putBlock(block.ladder(), RenderType.cutout());
        }

        BlockRenderLayerMap.INSTANCE.putBlock(MushroomBlockSets.DARK_MUSHROOM.trapdoor(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(MushroomBlockSets.GRAY_MUSHROOM.trapdoor(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(MushroomBlockSets.MUSHROOM.trapdoor(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(WoodBlockSets.SCORCHED.trapdoor(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(WoodBlockSets.WILLOW.trapdoor(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(WoodBlockSets.PALM.trapdoor(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(WoodBlockSets.WHITE_PALM.trapdoor(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(WoodBlockSets.MIRKWOOD.trapdoor(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(WoodBlockSets.MALLORN.trapdoor(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(WoodBlockSets.WHITE_LEBETHRON.trapdoor(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(WoodBlockSets.BLACK_LEBETHRON.trapdoor(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(WoodBlockSets.BEECH.trapdoor(), RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(WoodBlockSets.BEECH.door(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(WoodBlockSets.BLACK_LEBETHRON.door(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(WoodBlockSets.WHITE_LEBETHRON.door(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(WoodBlockSets.CHESTNUT.door(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(WoodBlockSets.LARCH.door(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(WoodBlockSets.MALLORN.door(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(WoodBlockSets.PALM.door(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(WoodBlockSets.WHITE_PALM.door(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(WoodBlockSets.WILLOW.door(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(WoodBlockSets.SCORCHED.door(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(MushroomBlockSets.GRAY_MUSHROOM.door(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(MushroomBlockSets.DARK_MUSHROOM.door(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(MushroomBlockSets.MUSHROOM.door(), RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(WoodBlockSets.BEECH.chair(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(WoodBlockSets.LARCH.chair(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(WoodBlockSets.BLACK_LEBETHRON.chair(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(WoodBlockSets.WHITE_LEBETHRON.chair(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(WoodBlockSets.CHESTNUT.chair(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(WoodBlockSets.FIR.chair(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(WoodBlockSets.HOLLY.chair(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(WoodBlockSets.MALLORN.chair(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(WoodBlockSets.MAPLE.chair(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(WoodBlockSets.SILVER_MAPLE.chair(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(WoodBlockSets.MIRKWOOD.chair(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(WoodBlockSets.PALM.chair(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(WoodBlockSets.WHITE_PALM.chair(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(WoodBlockSets.PINE.chair(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(WoodBlockSets.BLACK_PINE.chair(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(WoodBlockSets.WILLOW.chair(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(WoodBlockSets.SCORCHED.chair(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(MushroomBlockSets.GRAY_MUSHROOM.chair(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(MushroomBlockSets.DARK_MUSHROOM.chair(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(MushroomBlockSets.MUSHROOM.chair(), RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.TREATED_WOOD_CHAIR, RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.FALLEN_LEAVES, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.FALLEN_MALLORN_LEAVES, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.FALLEN_MIRKWOOD_LEAVES, RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.QUARTZ_CLUSTER, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SMALL_QUARTZ_BUD, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MEDIUM_QUARTZ_BUD, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LARGE_QUARTZ_BUD, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.RED_AGATE_CLUSTER, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SMALL_RED_AGATE_BUD, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MEDIUM_RED_AGATE_BUD, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LARGE_RED_AGATE_BUD, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CITRINE_CLUSTER, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SMALL_CITRINE_BUD, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MEDIUM_CITRINE_BUD, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LARGE_CITRINE_BUD, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GLOWSTONE_CLUSTER, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SMALL_GLOWSTONE_BUD, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MEDIUM_GLOWSTONE_BUD, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LARGE_GLOWSTONE_BUD, RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.DWARVEN_LANTERN, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.WALL_DWARVEN_LANTERN, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.CRYSTAL_LAMP, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.WALL_CRYSTAL_LAMP, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.SILVER_LANTERN, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.WALL_SILVER_LANTERN, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.ELVEN_LANTERN, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.WALL_ELVEN_LANTERN, RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.BRONZE_CHAIN, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.BRONZE_BROAD_CHAIN, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.SPIKY_CHAIN, RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.NET, RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.COPPER_BARS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EXPOSED_COPPER_BARS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WEATHERED_COPPER_BARS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.OXIDIZED_COPPER_BARS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WAXED_COPPER_BARS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WAXED_EXPOSED_COPPER_BARS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WAXED_WEATHERED_COPPER_BARS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WAXED_OXIDIZED_COPPER_BARS, RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.TREATED_STEEL_BARS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.TREATED_STEEL_DOOR, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.TREATED_STEEL_TRAPDOOR, RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SILVER_BARS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GILDED_BARS, RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.WILD_CARROT, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.WILD_POTATO, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.WILD_BEETROOT, RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.AZALEA_FLOWER_GROWTH, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.DRY_GROWTH, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.FROZEN_GROWTH, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.GREEN_GROWTH, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.IVY_GROWTH, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.LILAC_FLOWER_GROWTH, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.PINK_FLOWER_GROWTH, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.RED_FLOWER_GROWTH, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.WHITE_FLOWER_GROWTH, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.YELLOW_FLOWER_GROWTH, RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.MEDGON_SPIKE, RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.WOOD_FRAMED_WINDOW, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.WOOD_FRAMED_WINDOW_PANE, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.WATTLE_AND_BRICK_WINDOW, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.WATTLE_AND_BRICK_WINDOW_PANE, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.WATTLE_FRAMED_WINDOW, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.WATTLE_FRAMED_WINDOW_PANE, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.DARK_WATTLE_FRAMED_WINDOW, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.DARK_WATTLE_FRAMED_WINDOW_PANE, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.BLACK_WATTLE_FRAMED_WINDOW, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.BLACK_WATTLE_FRAMED_WINDOW_PANE, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.GREEN_WATTLE_FRAMED_WINDOW, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.GREEN_WATTLE_FRAMED_WINDOW_PANE, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.RED_WATTLE_FRAMED_WINDOW, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.RED_WATTLE_FRAMED_WINDOW_PANE, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.WHITE_WATTLE_FRAMED_WINDOW, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.WHITE_WATTLE_FRAMED_WINDOW_PANE, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.WHITE_DAUB_HOBBIT_WINDOW, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.WHITE_DAUB_HOBBIT_WINDOW_PANE, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.YELLOW_DAUB_HOBBIT_WINDOW, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.YELLOW_DAUB_HOBBIT_WINDOW_PANE, RenderType.translucent());

        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.PLASTER_HOBBIT_WINDOW, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.PLASTER_HOBBIT_WINDOW_PANE, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.PLASTER_ROUND_WINDOW, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.PLASTER_ROUND_WINDOW_PANE, RenderType.translucent());

        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.WHITE_DAUB_ROUND_WINDOW, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.WHITE_DAUB_ROUND_WINDOW_PANE, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.YELLOW_DAUB_ROUND_WINDOW, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.YELLOW_DAUB_ROUND_WINDOW_PANE, RenderType.translucent());

        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.GONLUIN_CARVED_WINDOW, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.GONLUIN_CARVED_WINDOW_PANE, RenderType.translucent());

        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.TUFF_CARVED_WINDOW, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.TUFF_CARVED_WINDOW_PANE, RenderType.translucent());

        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.BLACKSTONE_CARVED_WINDOW, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.BLACKSTONE_CARVED_WINDOW_PANE, RenderType.translucent());

        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.IZHERABAN_CARVED_WINDOW, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.IZHERABAN_CARVED_WINDOW_PANE, RenderType.translucent());

        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.MEDGON_CARVED_WINDOW, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.MEDGON_CARVED_WINDOW_PANE, RenderType.translucent());

        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.MUD_BRICK_ROUND_WINDOW, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.MUD_BRICK_ROUND_WINDOW_PANE, RenderType.translucent());

        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.LEAD_GLASS, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.BLUE_STAINED_LEAD_GLASS, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.BLACK_STAINED_LEAD_GLASS, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.BROWN_STAINED_LEAD_GLASS, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.CYAN_STAINED_LEAD_GLASS, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.GRAY_STAINED_LEAD_GLASS, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.GREEN_STAINED_LEAD_GLASS, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.LIGHT_BLUE_STAINED_LEAD_GLASS, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.LIGHT_GRAY_STAINED_LEAD_GLASS, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.LIME_STAINED_LEAD_GLASS, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.MAGENTA_STAINED_LEAD_GLASS, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.ORANGE_STAINED_LEAD_GLASS, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.PINK_STAINED_LEAD_GLASS, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.PURPLE_STAINED_LEAD_GLASS, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.RED_STAINED_LEAD_GLASS, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.WHITE_STAINED_LEAD_GLASS, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.YELLOW_STAINED_LEAD_GLASS, RenderType.translucent());
        
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.LEAD_GLASS_PANE, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.BLUE_STAINED_LEAD_GLASS_PANE, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.BLACK_STAINED_LEAD_GLASS_PANE, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.BROWN_STAINED_LEAD_GLASS_PANE, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.CYAN_STAINED_LEAD_GLASS_PANE, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.GRAY_STAINED_LEAD_GLASS_PANE, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.GREEN_STAINED_LEAD_GLASS_PANE, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.LIGHT_BLUE_STAINED_LEAD_GLASS_PANE, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.LIGHT_GRAY_STAINED_LEAD_GLASS_PANE, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.LIME_STAINED_LEAD_GLASS_PANE, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.MAGENTA_STAINED_LEAD_GLASS_PANE, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.ORANGE_STAINED_LEAD_GLASS_PANE, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.PINK_STAINED_LEAD_GLASS_PANE, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.PURPLE_STAINED_LEAD_GLASS_PANE, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.RED_STAINED_LEAD_GLASS_PANE, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.WHITE_STAINED_LEAD_GLASS_PANE, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.YELLOW_STAINED_LEAD_GLASS_PANE, RenderType.translucent());

        BlockRenderLayerMap.INSTANCE.putBlock(ModResourceItems.REEDS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.TALL_CATTAILS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModNatureBlocks.TALL_BULRUSH, RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.WATERING_CAN, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.WOODEN_BUCKET, RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.BROWN_JUG, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.LARGE_JUG, RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.AMPHORA, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.BROWN_AMPHORA, RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.BROWN_JAR, RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.BROWN_FAT_POT, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.FAT_POT, RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.CANDLE_HEAP, RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.BIG_BRAZIER, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.SMALL_BRAZIER, RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.GILDED_BIG_BRAZIER, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.GILDED_SMALL_BRAZIER, RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.FIRE_BOWL, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.TORCH_OF_ORTHANC, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.BONFIRE, RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.SCONCE, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.GILDED_SCONCE, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.ORCISH_SCONCE, RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.WALL_SCONCE, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.GILDED_WALL_SCONCE, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.ORCISH_WALL_SCONCE, RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.GALONN_STATUE, RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.ARKENSTONE, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.WALL_ARKENSTONE, RenderType.translucent());

        SimpleWoodChairModel.vanillaChairs.forEach(block -> {
            BlockRenderLayerMap.INSTANCE.putBlock(block.base(), RenderType.cutout());
        });

        BlockRenderLayerMap.INSTANCE.putBlock(ModDecorativeBlocks.BELLOWS, RenderType.cutout());
    }

    private static void registerMenuScreens(RegisterMenuScreensEvent event) {
        event.register(ModScreenHandlers.FORGE_ALLOYING_SCREEN_HANDLER, ForgeAlloyingScreen::new);
        event.register(ModScreenHandlers.FORGE_HEATING_SCREEN_HANDLER, ForgeHeatingScreen::new);
        event.register(ModScreenHandlers.ARTISAN_SCREEN_HANDLER, ArtisanTableScreen::new);
        event.register(ModScreenHandlers.TREATED_ANVIL_SCREEN_HANDLER, ShapingAnvilScreen::new);
        event.register(ModScreenHandlers.WOOD_PILE_SCREEN_HANDLER, WoodPileScreen::new);
    }

    private static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
        event.register((state, view, pos, tintIndex) -> {
            if (view == null || pos == null) {
                return GrassColor.getDefaultColor();
            }
            return BiomeColors.getAverageGrassColor(view, pos);
        }, ModNatureBlocks.WILD_GRASS, ModNatureBlocks.GRASS_TUFT, ModNatureBlocks.WHEATGRASS,
                ModBlocks.GRASSY_DIRT, ModBlocks.GRASSY_DIRT_SLAB, ModBlocks.GRASSY_DIRT_STAIRS,
                ModBlocks.PEBBLED_GRASS, ModBlocks.PEBBLED_GRASS_SLAB, ModBlocks.PEBBLED_GRASS_STAIRS,
                ModBlocks.TURF, ModBlocks.TURF_SLAB, ModBlocks.TURF_STAIRS, ModBlocks.TURF_VERTICAL_SLAB);

        event.register((state, view, pos, tintIndex) -> {
            if (view == null || pos == null) {
                return FoliageColor.getDefaultColor();
            }
            return BiomeColors.getAverageFoliageColor(view, pos);
        }, ModNatureBlocks.FALLEN_LEAVES);
    }

    private static void registerItemColors(RegisterColorHandlersEvent.Item event) {
        event.register((stack, tintIndex) -> GrassColor.getDefaultColor(), ModNatureBlocks.WILD_GRASS, ModNatureBlocks.GRASS_TUFT, ModNatureBlocks.WHEATGRASS,
                ModBlocks.GRASSY_DIRT, ModBlocks.GRASSY_DIRT_SLAB, ModBlocks.GRASSY_DIRT_STAIRS,
                ModBlocks.PEBBLED_GRASS, ModBlocks.PEBBLED_GRASS_SLAB, ModBlocks.PEBBLED_GRASS_STAIRS,
                ModBlocks.TURF, ModBlocks.TURF_SLAB, ModBlocks.TURF_STAIRS, ModBlocks.TURF_VERTICAL_SLAB);

        event.register((stack, tintIndex) -> FoliageColor.getDefaultColor(), ModNatureBlocks.FALLEN_LEAVES);
        SimpleDyeableItemModel.items.forEach(item -> registerDyeableItemColor(event, item));
        registerDyeableItemColor(event, ModEquipmentItems.BROADHOOF_GOAT_PADDED_ARMOR);
        registerDyeableItemColor(event, ModEquipmentItems.BROADHOOF_GOAT_ORNAMENTED_PADDED_ARMOR);
        registerDyeableItemColor(event, ModEquipmentItems.WARG_LEATHER_ARMOR);
        registerDyeableItemColor(event, ModEquipmentItems.WARG_REINFORCED_LEATHER_ARMOR);
        DYEABLE_COLOR_ITEMS.forEach(item -> registerDyeableItemColor(event, item));
    }

    private static void registerDyeableItemColor(RegisterColorHandlersEvent.Item event, Item item) {
        event.register((stack, tintIndex) -> tintIndex > 0 ? -1 : CustomDyeableDataComponent.getColor(stack, CustomDyeableDataComponent.DEFAULT_COLOR), item);
    }

    private void registerDyeableItem(Item item) {
        DYEABLE_COLOR_ITEMS.add(item);
    }
}
