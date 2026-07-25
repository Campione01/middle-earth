package net.jukoz.me.commands.custom;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.jukoz.me.commands.CommandUtils;
import net.jukoz.me.commands.ModCommands;
import net.jukoz.me.resources.StateSaverAndLoader;
import net.jukoz.me.resources.persistent_datas.PlayerData;
import net.jukoz.me.utils.LoggerUtil;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import static net.minecraft.commands.Commands.argument;
import static net.minecraft.commands.Commands.literal;

public class CommandInformation {
    private static final String INFO_BASE_COMMAND = "info";
    private static final String PLAYER = "player";

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext commandRegistryAccess, Commands.CommandSelection registrationEnvironment) {
        // [INFO]
        dispatcher.register(literal(ModCommands.BASE_COMMAND)
                .requires(source -> source.hasPermission(2)) // Require OP
                .then(literal(INFO_BASE_COMMAND)
                .then(argument(PLAYER, EntityArgument.player()) // With Player Target
                    .executes(CommandInformation::getTargetInfo))
                .executes(CommandInformation::getInfo)));
    }

    private static int getInfo(CommandContext<CommandSourceStack> context) {
        if(!context.getSource().isPlayer() || context.getSource().getPlayer() == null)
            return 1;

        ServerPlayer source = context.getSource().getPlayer();

        PlayerData data = StateSaverAndLoader.getPlayerStateReadOnly(source);

        source.sendSystemMessage(Component.literal(data == null ? "No Data" : data.toString()));
        return 0;
    }

    private static int getTargetInfo(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        ServerPlayer targettedPlayer = EntityArgument.getPlayer(context, PLAYER);

        PlayerData data = StateSaverAndLoader.getPlayerStateReadOnly(targettedPlayer);

        context.getSource().sendSystemMessage(Component.literal(data == null ? "No Data" : data.toString()));
        return 0;
    }

    private static int run(CommandContext<CommandSourceStack> context) {
        try{
            ServerPlayer targettedPlayer = EntityArgument.getPlayer(context, PLAYER);

            PlayerData data = StateSaverAndLoader.getPlayerStateReadOnly(targettedPlayer);

            targettedPlayer.sendSystemMessage(Component.nullToEmpty(data == null ? "No Data" : data.toString()));

            return 1;
        } catch (Exception e){
            LoggerUtil.logError("GetPlayerInformationCommand", e);
            return 0;
        }
    }
}
