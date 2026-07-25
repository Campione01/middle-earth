package net.jukoz.me.commands.custom;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.jukoz.me.commands.CommandUtils;
import net.jukoz.me.utils.ModColors;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands.CommandSelection;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.arguments.ResourceLocationArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.jukoz.me.commands.ModCommands;
import net.jukoz.me.commands.suggestions.AllAvailableSpawnSuggestionProvider;
import net.jukoz.me.commands.suggestions.FactionSuggestionProvider;
import net.jukoz.me.exceptions.FactionIdentifierException;
import net.jukoz.me.exceptions.IdenticalFactionException;
import net.jukoz.me.exceptions.NoFactionException;
import net.jukoz.me.exceptions.SpawnIdentifierException;
import net.jukoz.me.resources.StateSaverAndLoader;
import net.jukoz.me.resources.datas.factions.Faction;
import net.jukoz.me.resources.datas.factions.FactionLookup;
import net.jukoz.me.resources.datas.factions.FactionUtil;
import net.jukoz.me.resources.persistent_datas.PlayerData;
import net.jukoz.me.utils.LoggerUtil;
import org.jetbrains.annotations.Nullable;

import static net.minecraft.commands.Commands.literal;
import static net.minecraft.commands.Commands.argument;
import static net.minecraft.commands.Commands.*;

public class CommandFaction {
    public static String FACTION_BASE_COMMAND = "faction";
    private static final String JOIN = "join";
    private static final String GET = "get";
    private static final String CLEAR = "clear";
    private static final String FACTION_ID = "faction_id";
    private static final String SPAWN_ID = "spawn_id";
    private static final String PLAYER = "player";
    private static final String BANNER = "banner";

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext commandRegistryAccess, CommandSelection registrationEnvironment) {
        // [GET]
        CommandUtils.simpleCommand(dispatcher, FACTION_BASE_COMMAND, literal(GET) // With Player Target
        .executes(CommandFaction::getFaction), PLAYER, literal(GET) // With Player Target
        .executes(CommandFaction::getTargetFaction));

        // [CLEAR]
        dispatcher.register(literal(ModCommands.BASE_COMMAND)
                .requires(source -> source.hasPermission(2)) // Require OP
                .then(literal(FACTION_BASE_COMMAND)
                .then(argument(PLAYER, EntityArgument.player())
                    .then(literal(CLEAR) // With Player Target
                    .executes(CommandFaction::clearTargetFaction))))
                .then(literal(FACTION_BASE_COMMAND)
                .then(literal(CLEAR) // Without Target
                .executes(CommandFaction::clearFaction))));

        // [JOIN]
        dispatcher.register(literal(ModCommands.BASE_COMMAND)
                .requires(source -> source.hasPermission(2)) // Require OP
                .then(literal(FACTION_BASE_COMMAND)
                .then(argument(PLAYER, EntityArgument.player())
                    .then(literal(JOIN) // With Player Target
                    .then(argument(FACTION_ID, ResourceLocationArgument.id())
                    .suggests(new FactionSuggestionProvider())
                    .executes(CommandFaction::forceTargetToJoinFaction))))
                .then(literal(JOIN) // Without Target
                .then(argument(FACTION_ID, ResourceLocationArgument.id())
                .suggests(new FactionSuggestionProvider())
                .executes(CommandFaction::joinFaction)))));

        // [JOIN + SET SPAWN]
        dispatcher.register((literal(ModCommands.BASE_COMMAND)
                .requires(source -> source.hasPermission(2))) // Require OP
                .then((literal(FACTION_BASE_COMMAND))
                    .then(argument(PLAYER, EntityArgument.player())
                        .then((literal(JOIN) // With Player Target
                        .then(argument(FACTION_ID, ResourceLocationArgument.id())
                        .suggests(new FactionSuggestionProvider())
                        .then(argument(SPAWN_ID, ResourceLocationArgument.id())
                        .suggests(new AllAvailableSpawnSuggestionProvider())
                        .executes(CommandFaction::forceTargetToJoinFaction))))))

                    .then((literal(JOIN)) // No Player Target
                    .then(argument(FACTION_ID, ResourceLocationArgument.id())
                    .suggests(new FactionSuggestionProvider())
                    .then(argument(SPAWN_ID, ResourceLocationArgument.id())
                    .suggests(new AllAvailableSpawnSuggestionProvider())
                    .executes(CommandFaction::joinFaction))))));

        // [GET BANNER]
        dispatcher.register(literal(ModCommands.BASE_COMMAND)
                .requires(source -> source.hasPermission(2)) // Require OP
                .then(literal(FACTION_BASE_COMMAND)
                    .then(literal(BANNER)
                    .then(argument(FACTION_ID, ResourceLocationArgument.id())
                    .suggests(new FactionSuggestionProvider())
                    .executes(CommandFaction::getBanner)))));
    }

    private static int getBanner(CommandContext<CommandSourceStack> context) {
        if(context.getSource().isPlayer()) {
            ServerPlayer source = context.getSource().getPlayer();
            ResourceLocation factionIdentifier = ResourceLocationArgument.getId(context, FACTION_ID);;

            try{
                Faction faction = FactionLookup.getFactionById(source.level(), factionIdentifier);
                source.addItem(faction.getBannerItem(source.level()));
                MutableComponent sourceText = Component.translatable("command.me.faction.banner.success", faction.getFullName().withStyle(ChatFormatting.GOLD));
                source.sendSystemMessage(sourceText.withColor(ModColors.SUCCESS.color));
            } catch (FactionIdentifierException e){
                MutableComponent sourceText = Component.translatable("command.me.faction.banner.fail_id", Component.nullToEmpty(factionIdentifier.toString()));
                source.sendSystemMessage(sourceText.withColor(ModColors.ALERT.color));
                return 0;
            } catch (Exception e){
                MutableComponent sourceText = Component.translatable("command.me.faction.banner.fail_error", Component.nullToEmpty(factionIdentifier.toString()));
                source.sendSystemMessage(sourceText.withColor(ModColors.ALERT.color));
                return 0;
            }
        }
        return 1;
    }

    private static int getFaction(CommandContext<CommandSourceStack> context) {
        if(context.getSource().isPlayer()) {
            ServerPlayer source = context.getSource().getPlayer();
            if(source != null){
                PlayerData playerData = StateSaverAndLoader.getPlayerStateReadOnly(source);
                if(playerData != null && playerData.hasAffilition()){
                    try{
                        Faction currentFaction = playerData.getCurrentFaction(context.getSource().getLevel());
                        MutableComponent sourceText = Component.translatable("command.me.get.faction.success", currentFaction.getFullName());
                        source.sendSystemMessage(sourceText.withColor(ModColors.SUCCESS.color));
                    } catch (FactionIdentifierException e) {
                        MutableComponent sourceText = Component.translatable("command.me.get.faction.no_faction");
                        source.sendSystemMessage(sourceText.withColor(ModColors.WARNING.color));
                    }
                }
            }
        }
        return 0;
    }

    private static int getTargetFaction(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        ServerPlayer targetedPlayer = EntityArgument.getPlayer(context, PLAYER);
        if(targetedPlayer != null && context.getSource().isPlayer()) {
            ServerPlayer source = context.getSource().getPlayer();
            if(source != null){
                PlayerData playerData = StateSaverAndLoader.getPlayerStateReadOnly(targetedPlayer);
                if(playerData != null && playerData.hasAffilition()){
                    try{
                        Faction foundFaction = playerData.getCurrentFaction(context.getSource().getLevel());
                        MutableComponent sourceText = Component.translatable("command.me.get.player.faction.success", targetedPlayer.getName(), foundFaction.getFullName());
                        source.sendSystemMessage(sourceText.withColor(ModColors.SUCCESS.color));
                    } catch (FactionIdentifierException e) {
                        MutableComponent sourceText = Component.translatable("command.me.get.player.faction.no_faction", targetedPlayer.getName());
                        source.sendSystemMessage(sourceText.withColor(ModColors.WARNING.color));
                    }
                }
            }
        }
        return 0;
    }

    private static int clearFaction(CommandContext<CommandSourceStack> context) {
        if(context.getSource().isPlayer()) {
            ServerPlayer source = context.getSource().getPlayer();
            if(source != null) {
                try {
                    if (FactionUtil.clearFaction(source)) {
                        MutableComponent sourceText = Component.translatable("command.me.clear.faction.success");
                        source.sendSystemMessage(sourceText.withColor(ModColors.SUCCESS.color));
                    }
                    return 1;
                } catch (NoFactionException e) {
                    MutableComponent sourceText = Component.translatable(NoFactionException.KEY_SOURCE);
                    source.sendSystemMessage(sourceText.withColor(ModColors.ALERT.color));
                } catch (FactionIdentifierException e) {
                    MutableComponent sourceText = Component.translatable(FactionIdentifierException.KEY);
                    source.sendSystemMessage(sourceText.withColor(ModColors.ALERT.color));
                }
            }
        }
        return 0;
    }

    private static int clearTargetFaction(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        ServerPlayer targetedPlayer = EntityArgument.getPlayer(context, PLAYER);
        boolean isPlayerSource = context.getSource().isPlayer();

        if(targetedPlayer != null){
            ServerPlayer playerSource = null;
            if(isPlayerSource)
                playerSource = context.getSource().getPlayer();
            if(context.getSource() != null) {
                try {
                    if (FactionUtil.clearFaction(playerSource)) {
                        MutableComponent sourceText = Component.translatable("command.me.clear.player.faction.success", targetedPlayer.getName());
                        context.getSource().sendSystemMessage(sourceText.withColor(ModColors.SUCCESS.color));
                    }
                    return 1;
                } catch (NoFactionException e) {
                    MutableComponent sourceText = Component.translatable(NoFactionException.KEY_TARGET, targetedPlayer.getName());
                    context.getSource().sendSystemMessage(sourceText.withColor(ModColors.ALERT.color));
                } catch (FactionIdentifierException e) {
                    MutableComponent sourceText = Component.translatable(FactionIdentifierException.KEY);
                    context.getSource().sendSystemMessage(sourceText.withColor(ModColors.ALERT.color));
                }
            }
        }

        return 0;
    }
    private static int joinFaction(CommandContext<CommandSourceStack> context) {
        ServerPlayer targetedPlayer = context.getSource().getPlayer();
        ResourceLocation factionIdentifier = ResourceLocationArgument.getId(context, FACTION_ID);;
        if(targetedPlayer != null){
            ResourceLocation spawnIdentifier = null;
            try{
                spawnIdentifier = ResourceLocationArgument.getId(context, SPAWN_ID);
            } catch (Exception ignored){
            }
            boolean success =  updateFactionFromCommand(targetedPlayer, context.getSource(), factionIdentifier, spawnIdentifier);
            if(success){
                return 1;
            }
        }

        return 0;
    }

    private static int forceTargetToJoinFaction(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        ServerPlayer targetedPlayer = EntityArgument.getPlayer(context, PLAYER);
        ResourceLocation factionIdentifier = ResourceLocationArgument.getId(context, FACTION_ID);
        ResourceLocation spawnIdentifier = null;
        try{
            spawnIdentifier = ResourceLocationArgument.getId(context, SPAWN_ID);
        } catch (Exception ignored){
        }

        boolean success = updateFactionFromCommand(targetedPlayer, context.getSource(), factionIdentifier, spawnIdentifier);
        if(success){
            try{
                Faction faction = FactionLookup.getFactionById(context.getSource().getLevel(), factionIdentifier);
                if(context.getSource().isPlayer()){
                    ServerPlayer source = context.getSource().getPlayer();
                    MutableComponent sourceText = Component.translatable("command.me.join.faction.join.success", targetedPlayer.getName(), faction.getFullName());
                    source.sendSystemMessage(sourceText.withColor(ModColors.SUCCESS.color));
                }
            } catch (FactionIdentifierException e) {
                LoggerUtil.logDebugMsg("Faction Id does not exist");
            }

            return 1;
        }
        return 0;
    }

    public static boolean updateFactionFromCommand(ServerPlayer target, CommandSourceStack source, ResourceLocation factionIdentifier, @Nullable ResourceLocation spawnId) {
        Faction foundFaction = null;
        boolean commandOnSelf = source.isPlayer() && source.getPlayer() == target;
        try {
            foundFaction = FactionLookup.getFactionById(source.getLevel(), factionIdentifier);
            FactionUtil.updateFaction(target, foundFaction, spawnId);
            return true;
        } catch (FactionIdentifierException e){
            MutableComponent errorMessage = Component.translatable(FactionIdentifierException.KEY, factionIdentifier.toString());
            source.sendSystemMessage(errorMessage.withColor(ModColors.ALERT.color));
            return false;
        } catch (IdenticalFactionException e){
            if(commandOnSelf){ // Player on himself
                MutableComponent errorMessage = Component.translatable(IdenticalFactionException.KEY_SOURCE, (foundFaction == null) ? factionIdentifier.toString() : foundFaction.getFullName());
                source.sendSystemMessage(errorMessage.withColor(ModColors.ALERT.color));
                return false;
            }  else { // Player on another target or Command block
                MutableComponent errorMessage = Component.translatable(IdenticalFactionException.KEY_TARGET, target.getName(), (foundFaction == null) ? factionIdentifier.toString() : foundFaction.getFullName());
                source.sendSystemMessage(errorMessage.withColor(ModColors.ALERT.color));
                return false;
            }
        } catch (SpawnIdentifierException e){
                MutableComponent errorMessage;
                if(spawnId != null){
                    errorMessage = Component.translatable(SpawnIdentifierException.KEY, spawnId.toString());
                } else {
                    errorMessage = Component.translatable("command.me.fail");
                }
                source.sendSystemMessage(errorMessage.withColor(ModColors.ALERT.color));
                return false;
        } catch (NoFactionException e){
            if(commandOnSelf){ // Player on himself
                MutableComponent errorMessage = Component.translatable(NoFactionException.KEY_SOURCE);
                source.sendSystemMessage(errorMessage.withColor(ModColors.ALERT.color));
                return false;
            }  else { // Player on another target or Command block
                MutableComponent errorMessage = Component.translatable(NoFactionException.KEY_TARGET, target);
                source.sendSystemMessage(errorMessage.withColor(ModColors.ALERT.color));
                return false;
            }
        }
    }
}
