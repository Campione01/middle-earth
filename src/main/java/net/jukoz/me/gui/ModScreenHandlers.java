package net.jukoz.me.gui;

import net.jukoz.me.utils.NeoForgeRegistrationBridge;

import net.jukoz.me.compat.neoforge.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.jukoz.me.MiddleEarth;
import net.jukoz.me.gui.forge.ForgeAlloyingScreenHandler;
import net.jukoz.me.gui.forge.ForgeHeatingScreenHandler;
import net.jukoz.me.gui.artisantable.ArtisanTableScreenHandler;
import net.jukoz.me.gui.shapinganvil.ShapingAnvilScreenHandler;
import net.jukoz.me.gui.wood_pile.WoodPileScreenHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;

public class ModScreenHandlers {
    public static MenuType<ArtisanTableScreenHandler> ARTISAN_SCREEN_HANDLER = new ExtendedScreenHandlerType<>(ArtisanTableScreenHandler::new, ByteBufCodecs.STRING_UTF8.cast());;
    public static MenuType<ShapingAnvilScreenHandler> TREATED_ANVIL_SCREEN_HANDLER = new ExtendedScreenHandlerType<>(ShapingAnvilScreenHandler::new, BlockPos.STREAM_CODEC.cast());;
    public static MenuType<WoodPileScreenHandler> WOOD_PILE_SCREEN_HANDLER;
    public static final MenuType<ForgeAlloyingScreenHandler> FORGE_ALLOYING_SCREEN_HANDLER = new ExtendedScreenHandlerType<>(ForgeAlloyingScreenHandler::new, BlockPos.STREAM_CODEC.cast());
    public static final MenuType<ForgeHeatingScreenHandler> FORGE_HEATING_SCREEN_HANDLER = new ExtendedScreenHandlerType<>(ForgeHeatingScreenHandler::new, BlockPos.STREAM_CODEC.cast());

    public static void registerAllScreenHandlers() {
        WOOD_PILE_SCREEN_HANDLER = new MenuType<>(WoodPileScreenHandler::new, FeatureFlags.VANILLA_SET);

        NeoForgeRegistrationBridge.register(BuiltInRegistries.MENU, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "forge_alloying"),
                FORGE_ALLOYING_SCREEN_HANDLER);
        NeoForgeRegistrationBridge.register(BuiltInRegistries.MENU, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "forge_heating"),
                FORGE_HEATING_SCREEN_HANDLER);
        NeoForgeRegistrationBridge.register(BuiltInRegistries.MENU, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "artisan_table"),
                ARTISAN_SCREEN_HANDLER);
        NeoForgeRegistrationBridge.register(BuiltInRegistries.MENU, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "treated_anvil"),
                TREATED_ANVIL_SCREEN_HANDLER);
        NeoForgeRegistrationBridge.register(BuiltInRegistries.MENU, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "wood_pile"),
                WOOD_PILE_SCREEN_HANDLER);
    }
}
