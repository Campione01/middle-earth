package net.jukoz.me.commands;

import net.jukoz.me.commands.custom.*;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

public class ModCommands {
    public static String BASE_COMMAND = "middle_earth";
    public static void register() {
        NeoForge.EVENT_BUS.addListener(ModCommands::registerCommands);
    }

    private static void registerCommands(RegisterCommandsEvent event) {
        // Faction Commands
        CommandFaction.register(event.getDispatcher(), event.getBuildContext(), event.getCommandSelection());
        CommandSpawn.register(event.getDispatcher(), event.getBuildContext(), event.getCommandSelection());
        CommandRace.register(event.getDispatcher(), event.getBuildContext(), event.getCommandSelection());

        // Onboarding Commands
        CommandOnboarding.register(event.getDispatcher(), event.getBuildContext(), event.getCommandSelection());

        //Misc commands
        CommandCustomEquipment.register(event.getDispatcher(), event.getBuildContext(), event.getCommandSelection());
        CommandInformation.register(event.getDispatcher(), event.getBuildContext(), event.getCommandSelection());
        CommandDimensionTeleport.register(event.getDispatcher(), event.getBuildContext(), event.getCommandSelection());
    }
}
