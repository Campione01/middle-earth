package net.jukoz.me.commands.custom;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.jukoz.me.compat.neoforge.api.networking.v1.ServerPlayNetworking;
import net.jukoz.me.commands.CommandUtils;
import net.jukoz.me.commands.ModCommands;
import net.jukoz.me.commands.suggestions.AllRaceSuggestionProvider;
import net.jukoz.me.config.ModServerConfigs;
import net.jukoz.me.network.packets.S2C.PacketForceOnboardingScreen;
import net.jukoz.me.network.packets.S2C.PacketOnboardingResult;
import net.jukoz.me.resources.StateSaverAndLoader;
import net.jukoz.me.resources.datas.races.Race;
import net.jukoz.me.resources.datas.races.RaceLookup;
import net.jukoz.me.resources.datas.races.RaceUtil;
import net.jukoz.me.resources.persistent_datas.PlayerData;
import net.jukoz.me.utils.ModColors;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;

import static net.minecraft.commands.Commands.argument;
import static net.minecraft.commands.Commands.literal;

public class CommandOnboarding {
    public static String ONBOARDING_BASE_COMMAND = "onboarding";
    private static final String OPEN = "open";
    private static final String TRY = "try";
    private static final String PLAYER = "player";
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext commandRegistryAccess, Commands.CommandSelection registrationEnvironment) {
        // [TRY OPEN]
        dispatcher.register(literal(ModCommands.BASE_COMMAND)
                .requires(source -> source.hasPermission(2)) // Require OP
                .then(literal(ONBOARDING_BASE_COMMAND)
                        .then(literal(TRY)
                        .then(literal(OPEN)
                        .then(argument(PLAYER, EntityArgument.player())
                        .executes(CommandOnboarding::tryOpenForTarget)))))
                .then(literal(ONBOARDING_BASE_COMMAND)
                        .then(literal(TRY)
                        .then(literal(OPEN)
                        .executes(CommandOnboarding::tryOpen)))));

        // [OPEN]
        CommandUtils.simpleCommand(dispatcher, ONBOARDING_BASE_COMMAND,
                literal(OPEN).executes(CommandOnboarding::open),
                PLAYER, literal(OPEN).executes(CommandOnboarding::openForTarget));
    }

    private static int open(CommandContext<CommandSourceStack> context) {
        if(context.getSource().isPlayer()) {
            ServerPlayer source = context.getSource().getPlayer();
            if(source != null){
                ServerPlayNetworking.send(source, new PacketForceOnboardingScreen(ModServerConfigs.DELAY_ON_TELEPORT_CONFIRMATION));
            }
        }
        return 0;
    }

    private static int openForTarget(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        ServerPlayer targetPlayer = EntityArgument.getPlayer(context, PLAYER);
        if(targetPlayer != null){
            ServerPlayNetworking.send(targetPlayer, new PacketForceOnboardingScreen(ModServerConfigs.DELAY_ON_TELEPORT_CONFIRMATION));
        }

        return 0;
    }

    private static int tryOpen(CommandContext<CommandSourceStack> context) {
        if(context.getSource().isPlayer()) {
            ServerPlayer source = context.getSource().getPlayer();
            if(source != null){
                PlayerData data = StateSaverAndLoader.getPlayerStateReadOnly(source);
                if(data == null || !data.hasAffilition()){
                    ServerPlayNetworking.send(source, new PacketForceOnboardingScreen(ModServerConfigs.DELAY_ON_TELEPORT_CONFIRMATION));
                } else {
                    MutableComponent sourceText = Component.translatable("command.me.open.onboarding.error");
                    source.sendSystemMessage(sourceText.withColor(ModColors.WARNING.color));
                }
            }
        }
        return 0;
    }

    private static int tryOpenForTarget(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        ServerPlayer targetPlayer = EntityArgument.getPlayer(context, PLAYER);
        if(targetPlayer != null){
            PlayerData data = StateSaverAndLoader.getPlayerStateReadOnly(targetPlayer);
            if(data == null || !data.hasAffilition()){
                ServerPlayNetworking.send(targetPlayer, new PacketForceOnboardingScreen(ModServerConfigs.DELAY_ON_TELEPORT_CONFIRMATION));
                MutableComponent sourceText = Component.translatable("command.me.open_target.onboarding.success", targetPlayer.getName());
                context.getSource().sendSystemMessage(sourceText.withColor(ModColors.SUCCESS.color));
            } else {
                MutableComponent sourceText = Component.translatable("command.me.open_target.onboarding.error",targetPlayer.getName());
                context.getSource().sendSystemMessage(sourceText.withColor(ModColors.WARNING.color));
            }
        }

        return 0;
    }
}
