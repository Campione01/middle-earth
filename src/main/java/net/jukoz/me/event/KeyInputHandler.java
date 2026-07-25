package net.jukoz.me.event;

import com.mojang.blaze3d.platform.InputConstants;
import net.jukoz.me.compat.neoforge.api.client.networking.v1.ClientPlayNetworking;
import net.jukoz.me.network.packets.C2S.HoodStateTogglePacket;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.common.NeoForge;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {

    public static final String ME_KEY_CATEGORY = "key.category.me.me";
    public static final String ME_KEY_HOOD_STATE_TOGGLE = "key.me.hood_state_toggle";
    public static final String ME_KEY_MAP_TELEPORT = "key.me.map_teleport";
    public static final String ME_KEY_MAP_FULLSCREEN_TOGGLE = "key.me.map_fullscreen_toggle";

    public static KeyMapping hoodStateToggleKey;
    // Used in MiddleEarthMapScreen
    public static KeyMapping mapTeleportKey;
    public static KeyMapping mapFullscreenToggle;

    private static boolean registeredClientEvents;

    public static void register(IEventBus modEventBus) {
        createKeyMappings();
        modEventBus.addListener(KeyInputHandler::registerKeyMappings);
        if (!registeredClientEvents) {
            NeoForge.EVENT_BUS.addListener(KeyInputHandler::onClientTick);
            registeredClientEvents = true;
        }
    }

    private static void createKeyMappings() {
        if (hoodStateToggleKey != null) {
            return;
        }

        hoodStateToggleKey = new KeyMapping(
                ME_KEY_HOOD_STATE_TOGGLE,
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_J,
                ME_KEY_CATEGORY
        );

        mapTeleportKey = new KeyMapping(
                ME_KEY_MAP_TELEPORT,
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_N,
                ME_KEY_CATEGORY
        );

        mapFullscreenToggle = new KeyMapping(
                ME_KEY_MAP_FULLSCREEN_TOGGLE,
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_M,
                ME_KEY_CATEGORY
        );
    }

    private static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        createKeyMappings();
        event.register(hoodStateToggleKey);
        event.register(mapTeleportKey);
        event.register(mapFullscreenToggle);
    }

    private static void onClientTick(ClientTickEvent.Post event) {
        if (hoodStateToggleKey != null && Minecraft.getInstance().player != null && hoodStateToggleKey.consumeClick()) {
            ClientPlayNetworking.send(new HoodStateTogglePacket());
        }
    }
}
