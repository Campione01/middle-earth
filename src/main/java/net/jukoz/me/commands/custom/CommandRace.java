package net.jukoz.me.commands.custom;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.jukoz.me.commands.CommandUtils;
import net.jukoz.me.commands.ModCommands;
import net.jukoz.me.commands.suggestions.AllRaceSuggestionProvider;
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
import net.minecraft.commands.arguments.ResourceLocationArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

import static net.minecraft.commands.Commands.argument;
import static net.minecraft.commands.Commands.literal;

public class CommandRace {
    public static String RACE_BASE_COMMAND = "race";
    private static final String SET = "set";
    private static final String GET = "get";
    private static final String RESET = "reset";
    private static final String PLAYER = "player";
    private static final String RACE_ID = "race_id";
    private static final int RACE_COLOR = ModColors.WARNING.color;
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext commandRegistryAccess, Commands.CommandSelection registrationEnvironment) {
        // [GET]
        CommandUtils.simpleCommand(dispatcher, RACE_BASE_COMMAND, literal(GET)
            .executes(CommandRace::getRace), PLAYER, literal(GET)
            .executes(CommandRace::getTargetRace));

        // [SET]
        dispatcher.register(literal(ModCommands.BASE_COMMAND)
                .requires(source -> source.hasPermission(2)) // Require OP
                .then(literal(RACE_BASE_COMMAND)
                .then(argument(PLAYER, EntityArgument.player())
                    .then(literal(SET) // With Player Target
                    .then(argument(RACE_ID, ResourceLocationArgument.id())
                    .suggests(new AllRaceSuggestionProvider())
                    .executes(CommandRace::setTargetRace))))
                .then(literal(SET) // Without Target
                .then(argument(RACE_ID, ResourceLocationArgument.id())
                .suggests(new AllRaceSuggestionProvider())
                .executes(CommandRace::setRace)))));

        // [RESET]
        dispatcher.register(literal(ModCommands.BASE_COMMAND)
                .requires(source -> source.hasPermission(2)) // Require OP
                .then(literal(RACE_BASE_COMMAND)
                .then(argument(PLAYER, EntityArgument.player())
                    .then(literal(RESET) // With Player Target
                    .executes(CommandRace::resetTargetRace)))
                .then(literal(RESET) // Without Target
                .executes(CommandRace::resetRace))));
    }

    private static int getRace(CommandContext<CommandSourceStack> context) {
        if(context.getSource().isPlayer()) {
            ServerPlayer source = context.getSource().getPlayer();
            if(source != null){
                PlayerData playerData = StateSaverAndLoader.getPlayerStateReadOnly(source);
                if(playerData != null){
                    ResourceLocation id = playerData.getRace();
                    if(id != null){
                        Race race = RaceLookup.getRace(context.getSource().getLevel(), id);
                        if(race != null){
                            MutableComponent sourceText = Component.translatable("command.me.race.get.success",
                                    race.getFullName().plainCopy().withColor(RACE_COLOR));
                            source.sendSystemMessage(sourceText.withColor(ModColors.SUCCESS.color));
                            return 0;
                        }
                    }
                }
                MutableComponent sourceText = Component.translatable("command.me.race.get.fail");
                source.sendSystemMessage(sourceText.withColor(ModColors.WARNING.color));
            }
        }
        return 0;
    }

    private static int getTargetRace(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        if(context.getSource().isPlayer()) {
            ServerPlayer targetPlayer = EntityArgument.getPlayer(context, PLAYER);
            ServerPlayer source = context.getSource().getPlayer();
            if(source != null){
                PlayerData playerData = StateSaverAndLoader.getPlayerStateReadOnly(targetPlayer);
                if(playerData != null){
                    ResourceLocation id = playerData.getRace();
                    if(id != null){
                        Race race = RaceLookup.getRace(context.getSource().getLevel(), id);
                        if(race != null){
                            MutableComponent sourceText = Component.translatable("command.me.race.get.target.success", targetPlayer.getName(),
                                    race.getFullName().plainCopy().withColor(RACE_COLOR));
                            source.sendSystemMessage(sourceText.withColor(ModColors.SUCCESS.color));
                            return 0;
                        }
                    }
                }
                MutableComponent sourceText = Component.translatable("command.me.race.get.target.fail", targetPlayer.getName());
                source.sendSystemMessage(sourceText.withColor(ModColors.WARNING.color));
            }
        }
        return 0;
    }

    private static int setRace(CommandContext<CommandSourceStack> context) {
        if(context.getSource().isPlayer()) {
            ResourceLocation raceId = ResourceLocationArgument.getId(context, RACE_ID);
            ServerPlayer source = context.getSource().getPlayer();

            if(raceId != null && source != null) {
                    PlayerData playerData = StateSaverAndLoader.getPlayerState(source);
                    if(playerData != null){
                        Race race = RaceLookup.getRace(source.level(), raceId);
                        if(race != null){
                            RaceUtil.updateRace(source, race, true);
                            MutableComponent sourceText = Component.translatable("command.me.race.set.success",
                                    race.getFullName().plainCopy().withColor(RACE_COLOR));
                            source.sendSystemMessage(sourceText.withColor(ModColors.SUCCESS.color));
                            return 0;
                        }
                    }
                    MutableComponent sourceText = Component.translatable("command.me.race.set.fail", raceId.toString());
                    source.sendSystemMessage(sourceText.withColor(ModColors.WARNING.color));
            }
        }
        return 0;
    }

    private static int setTargetRace(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        ResourceLocation raceId = ResourceLocationArgument.getId(context, RACE_ID);
        ServerPlayer targetPlayer = EntityArgument.getPlayer(context, PLAYER);
        ServerPlayer source = context.getSource().getPlayer();

        if(raceId != null && targetPlayer != null && source != null) {
            PlayerData playerData = StateSaverAndLoader.getPlayerState(targetPlayer);
            if(playerData != null){
                Race race = RaceLookup.getRace(source.level(), raceId);
                if(race != null){
                    RaceUtil.updateRace(targetPlayer, race, true);
                    MutableComponent sourceText = Component.translatable("command.me.race.set.target.success", targetPlayer.getName(),
                            race.getFullName().plainCopy().withColor(RACE_COLOR));
                    source.sendSystemMessage(sourceText.withColor(ModColors.SUCCESS.color));
                    MutableComponent targetText = Component.translatable("command.me.race.set.success",
                            race.getFullName().plainCopy().withColor(RACE_COLOR));
                    targetPlayer.sendSystemMessage(targetText.withColor(ModColors.SUCCESS.color));
                    return 0;
                }
            }
            MutableComponent sourceText = Component.translatable("command.me.race.set.target.fail", raceId.toString());
            source.sendSystemMessage(sourceText.withColor(ModColors.WARNING.color));
        }
        return 0;
    }


    private static int resetRace(CommandContext<CommandSourceStack> context) {
        if(context.getSource().isPlayer()) {
            ServerPlayer source = context.getSource().getPlayer();

            if(source != null) {
                PlayerData playerData = StateSaverAndLoader.getPlayerState(source);
                if(playerData != null){
                    RaceUtil.updateRace(source, null, true);
                    RaceUtil.reset(source);
                    MutableComponent sourceText = Component.translatable("command.me.race.reset.success");
                    source.sendSystemMessage(sourceText.withColor(ModColors.SUCCESS.color));
                    return 0;
                }
                MutableComponent sourceText = Component.translatable("command.me.race.reset.fail");
                source.sendSystemMessage(sourceText.withColor(ModColors.WARNING.color));
            }
        }
        return 0;
    }

    private static int resetTargetRace(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        ServerPlayer targetPlayer = EntityArgument.getPlayer(context, PLAYER);
        ServerPlayer source = context.getSource().getPlayer();

        PlayerData playerData = StateSaverAndLoader.getPlayerState(targetPlayer);
        if(targetPlayer != null){
            RaceUtil.updateRace(targetPlayer, null, true);
            RaceUtil.reset(targetPlayer);

            if(source != null && targetPlayer != null) {
                MutableComponent sourceText = Component.translatable("command.me.race.reset.target.success", targetPlayer.getName());
                targetPlayer.sendSystemMessage(sourceText.withColor(ModColors.SUCCESS.color));
            }
            MutableComponent targetText = Component.translatable("command.me.race.reset.success");
            targetPlayer.sendSystemMessage(targetText.withColor(ModColors.SUCCESS.color));
            return 0;
        }
        MutableComponent sourceText = Component.translatable("command.me.race.reset.target.fail", targetPlayer.getName());
        source.sendSystemMessage(sourceText.withColor(ModColors.WARNING.color));

        return 0;
    }
}
