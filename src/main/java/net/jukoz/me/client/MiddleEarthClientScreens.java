package net.jukoz.me.client;

import net.jukoz.me.client.screens.MiddleEarthMapScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;

public final class MiddleEarthClientScreens {
    private MiddleEarthClientScreens() {
    }

    public static void openMiddleEarthMap() {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.screen == null) {
            minecraft.setScreen(new MiddleEarthMapScreen());
        }
    }

    public static boolean hasShiftDown() {
        return Screen.hasShiftDown();
    }

    public static boolean hasAltDown() {
        return Screen.hasAltDown();
    }
}
