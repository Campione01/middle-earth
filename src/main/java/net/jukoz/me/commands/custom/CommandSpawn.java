package net.jukoz.me.commands.custom;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.jukoz.me.utils.ModColors;
import net.jukoz.me.commands.ModCommands;
import net.jukoz.me.commands.suggestions.AllAvailableSpawnSuggestionProvider;
import net.jukoz.me.commands.suggestions.AllSpawnSuggestionProvider;
import net.jukoz.me.exceptions.FactionIdentifierException;
import net.jukoz.me.resources.StateSaverAndLoader;
import net.jukoz.me.resources.datas.factions.Faction;
import net.jukoz.me.resources.datas.factions.FactionUtil;
import net.jukoz.me.resources.persistent_datas.PlayerData;
import net.jukoz.me.world.dimension.ModDimensions;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.arguments.ResourceLocationArgument;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import static net.minecraft.commands.Commands.argument;
import static net.minecraft.commands.Commands.literal;

public class CommandSpawn {
    private static final String SPAWN_BASE_COMMAND = "spawn";
    private static final String SET = "set";
    private static final String OVERWORLD = "overworld";
    private static final String MIDDLE_EARTH = "middle_earth";
    private static final String OVERWORLD_COORD = "overworld_blockpos";
    private static final String GET = "get";
    private static final String TP = "tp";
    private static final String TO = "to";
    private static final String RESET = "reset";
    private static final String WELCOME = "welcome_needed";
    private static final String SPAWN_ID = "spawn_id";
    private static final String PLAYER = "player";

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext commandRegistryAccess, Commands.CommandSelection registrationEnvironment) {
        // [GET OVERWORLD SPAWN]
        dispatcher.register(literal(ModCommands.BASE_COMMAND)
                .requires(source -> source.hasPermission(2)) // Require OP
                .then(literal(SPAWN_BASE_COMMAND)
                .then(argument(PLAYER, EntityArgument.player()) // With Player Target
                    .then(literal(GET)
                    .then(literal(OVERWORLD)
                    .executes(CommandSpawn::getPlayerSpawnOverworld))))
                .then(literal(GET) // Without Target
                .then(literal(OVERWORLD)
                .executes(CommandSpawn::getSpawnOverworld)))));

        // [GET SPAWN ID]
        dispatcher.register(literal(ModCommands.BASE_COMMAND)
                .requires(source -> source.hasPermission(2)) // Require OP
                .then(literal(SPAWN_BASE_COMMAND)
                    .then(argument(PLAYER, EntityArgument.player()) // With Player Target
                        .then(literal(GET)
                        .then(literal(MIDDLE_EARTH)
                        .executes(CommandSpawn::getPlayerSpawnMiddleEarth))))
                    .then(literal(GET) // Without Target
                    .then(literal(MIDDLE_EARTH)
                    .executes(CommandSpawn::getSpawnMiddleEarth)))));

        // [TP - MIDDLE_EARTH]
        dispatcher.register(literal(ModCommands.BASE_COMMAND)
                .requires(source -> source.hasPermission(2)) // Require OP
                .then(literal(SPAWN_BASE_COMMAND)
                    .then(argument(PLAYER, EntityArgument.player())
                        .then(literal(TP) // With Player Target
                        .then(literal(MIDDLE_EARTH)
                        .then(argument(WELCOME, BoolArgumentType.bool())
                        .executes(CommandSpawn::teleportPlayerToSpawnMiddleEarth)))))
                    .then(literal(TP) // Without Target
                    .then(literal(MIDDLE_EARTH)
                    .then(argument(WELCOME, BoolArgumentType.bool())
                    .executes(CommandSpawn::teleportToSpawnMiddleEarth))))));

        // [TP - OVERWORLD]
        dispatcher.register(literal(ModCommands.BASE_COMMAND)
                .requires(source -> source.hasPermission(2)) // Require OP
                .then(literal(SPAWN_BASE_COMMAND)
                .then(argument(PLAYER, EntityArgument.player())
                    .then(literal(TP) // With Player Target
                    .then(literal(OVERWORLD)
                    .executes(CommandSpawn::teleportPlayerToSpawnOverworld))))
                .then(literal(TP) // Without Target
                .then(literal(OVERWORLD)
                .executes(CommandSpawn::teleportToSpawnOverworld)))));

        // [SET - OVERWORLD]
        dispatcher.register(literal(ModCommands.BASE_COMMAND)
                .requires(source -> source.hasPermission(2)) // Require OP
                .then(literal(SPAWN_BASE_COMMAND)
                        .then(argument(PLAYER, EntityArgument.player())
                                .then(literal(SET)
                                .then(literal(OVERWORLD) // With Player Target
                                .then(argument(OVERWORLD_COORD, BlockPosArgument.blockPos())
                                .executes(CommandSpawn::setPlayerSpawnOverworld)))))
                        .then(literal(SET) // Without Target
                        .then(literal(OVERWORLD)
                        .then(argument(OVERWORLD_COORD, BlockPosArgument.blockPos())
                        .executes(CommandSpawn::setSpawnOverworld))))));

        // [SET - MIDDLE_EARTH]
        dispatcher.register(literal(ModCommands.BASE_COMMAND)
                .requires(source -> source.hasPermission(2)) // Require OP
                .then(literal(SPAWN_BASE_COMMAND)
                    .then(argument(PLAYER, EntityArgument.player())
                        .then(literal(SET)
                        .then(literal(MIDDLE_EARTH) // With Player Target
                        .then(argument(SPAWN_ID, ResourceLocationArgument.id())
                        .suggests(new AllAvailableSpawnSuggestionProvider())
                        .executes(CommandSpawn::setPlayerSpawnMiddleEarth)))))
                    .then(literal(SET) // Without Target
                    .then(literal(MIDDLE_EARTH)
                    .then(argument(SPAWN_ID, ResourceLocationArgument.id())
                    .suggests(new AllAvailableSpawnSuggestionProvider())
                    .executes(CommandSpawn::setSpawnMiddleEarth))))));

        // [RESET - OVERWORLD]
        dispatcher.register(literal(ModCommands.BASE_COMMAND)
            .requires(source -> source.hasPermission(2)) // Require OP
            .then(literal(SPAWN_BASE_COMMAND)
            .then(argument(PLAYER, EntityArgument.player())
                .then(literal(RESET) // With Player Target
                .then(literal(OVERWORLD)
                .executes(CommandSpawn::resetPlayerSpawnOverworld))))
            .then(literal(RESET) // Without Target
            .then(literal(OVERWORLD)
            .executes(CommandSpawn::resetSpawnOverworld)))));

        // [RESET - MIDDLE_EARTH]
        dispatcher.register(literal(ModCommands.BASE_COMMAND)
                .requires(source -> source.hasPermission(2)) // Require OP
                .then(literal(SPAWN_BASE_COMMAND)
                .then(argument(PLAYER, EntityArgument.player())
                    .then(literal(RESET) // With Player Target
                    .then(literal(MIDDLE_EARTH)
                    .executes(CommandSpawn::resetPlayerSpawnMiddleEarth))))
                .then(literal(RESET) // Without Target
                .then(literal(MIDDLE_EARTH)
                .executes(CommandSpawn::resetSpawnMiddleEarth)))));

        // [TP TO - SPAWN ID]
        dispatcher.register(literal(ModCommands.BASE_COMMAND)
                .requires(source -> source.hasPermission(2)) // Require OP
                .then(literal(TP)
                    .then(argument(PLAYER, EntityArgument.player())
                    .then(literal(TO)
                    .then(argument(SPAWN_ID, ResourceLocationArgument.id())
                    .suggests(new AllSpawnSuggestionProvider())
                    .executes(CommandSpawn::forceTeleportPlayerToSpawnMiddleEarth))))
                .then(literal(TO) // Without Target
                .then(argument(SPAWN_ID, ResourceLocationArgument.id())
                .suggests(new AllSpawnSuggestionProvider())
                .executes(CommandSpawn::forceTeleportToSpawnMiddleEarth)))));
    }

    // region Getters
    private static int getSpawnOverworld(CommandContext<CommandSourceStack> context) {
        if(!context.getSource().isPlayer() || context.getSource().getPlayer() == null)
            return 0;

        ServerPlayer player = context.getSource().getPlayer();
        PlayerData data = StateSaverAndLoader.getPlayerStateReadOnly(player);

        if(data != null && data.getOverworldSpawnCoordinates() != null){
            BlockPos pos = data.getOverworldSpawnCoordinates();
            MutableComponent sourceText = Component.translatable("command.me.get.spawn.overworld.success", pos.getX(), pos.getY(), pos.getZ());
            context.getSource().getPlayer().sendSystemMessage(sourceText.withColor(ModColors.SUCCESS.color));
        } else {
            BlockPos pos = context.getSource().getServer().overworld().getSharedSpawnPos();
            MutableComponent sourceText = Component.translatable("command.me.get.spawn.overworld.no_spawn", pos.getX(), pos.getY(), pos.getZ());
            context.getSource().getPlayer().sendSystemMessage(sourceText.withColor(ModColors.WARNING.color));
        }
        return 0;
    }

    private static int getPlayerSpawnOverworld(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        ServerPlayer targetedPlayer = EntityArgument.getPlayer(context, PLAYER);
        PlayerData data = StateSaverAndLoader.getPlayerStateReadOnly(targetedPlayer);
        if(data != null && data.getOverworldSpawnCoordinates() != null){
            BlockPos pos = data.getOverworldSpawnCoordinates();
            MutableComponent sourceText = Component.translatable("command.me.get.player.spawn.overworld.success", targetedPlayer.getName(), pos.getX(), pos.getY(), pos.getZ());
            context.getSource().sendSystemMessage(sourceText.withColor(ModColors.SUCCESS.color));
        } else {
            BlockPos pos = context.getSource().getServer().overworld().getSharedSpawnPos();
            MutableComponent sourceText = Component.translatable("command.me.get.player.spawn.overworld.no_spawn", targetedPlayer.getName(), pos.getX(), pos.getY(), pos.getZ());
            context.getSource().sendSystemMessage(sourceText.withColor(ModColors.WARNING.color));
        }
        return 0;
    }

    private static int getSpawnMiddleEarth(CommandContext<CommandSourceStack> context) {
        if(!context.getSource().isPlayer() || context.getSource().getPlayer() == null)
            return 0;
        PlayerData data = StateSaverAndLoader.getPlayerStateReadOnly(context.getSource().getPlayer());
        ResourceLocation spawnId = data == null ? null : data.getCurrentSpawnId();
        if(spawnId != null){
            BlockPos pos = FactionUtil.getSpawnBlockPos(context.getSource().getLevel() ,spawnId);
            MutableComponent sourceText = Component.translatable("command.me.get.spawn.middle_earth.success", Component.translatable("spawn."+spawnId.toLanguageKey()), pos.getX(), pos.getY(), pos.getZ());
            context.getSource().getPlayer().sendSystemMessage(sourceText.withColor(ModColors.SUCCESS.color));
        } else {
            MutableComponent sourceText = Component.translatable("command.me.get.spawn.middle_earth.no_spawn");
            context.getSource().sendSystemMessage(sourceText.withColor(ModColors.WARNING.color));
        }
        return 0;

    }

    private static int getPlayerSpawnMiddleEarth(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        if(!context.getSource().isPlayer()) return 0;

        ServerPlayer targetedPlayer = EntityArgument.getPlayer(context, PLAYER);

        PlayerData data = StateSaverAndLoader.getPlayerStateReadOnly(targetedPlayer);
        ResourceLocation spawnId = data == null ? null : data.getCurrentSpawnId();
        if(spawnId != null){
            BlockPos pos = FactionUtil.getSpawnBlockPos(context.getSource().getLevel() ,spawnId);
            MutableComponent sourceText = Component.translatable("command.me.get.player.spawn.middle_earth.success", targetedPlayer.getName(), Component.translatable("spawn."+spawnId.toLanguageKey()), pos.getX(), pos.getY(), pos.getZ());
            context.getSource().sendSystemMessage(sourceText.withColor(ModColors.SUCCESS.color));
        } else {
            MutableComponent sourceText = Component.translatable("command.me.get.player.spawn.middle_earth.no_spawn", targetedPlayer.getName());
            context.getSource().sendSystemMessage(sourceText.withColor(ModColors.WARNING.color));
        }
        return 0;
    }
    // endregion

    // region Setters
    private static int setSpawnMiddleEarth(CommandContext<CommandSourceStack> context) {
        if(!context.getSource().isPlayer() || context.getSource().getPlayer() == null)
            return 0;
        ServerPlayer sourcePlayer = context.getSource().getPlayer();

        ResourceLocation foundId = ResourceLocationArgument.getId(context, SPAWN_ID);
        PlayerData playerData = StateSaverAndLoader.getPlayerState(sourcePlayer);

        if(!playerData.hasAffilition()){
            MutableComponent sourceText = Component.translatable("command.me.set.spawn.middle_earth.no_faction");
            context.getSource().sendSystemMessage(sourceText.withColor(ModColors.WARNING.color));
            return 0;
        }

        try{
            if(playerData.setSpawnMiddleEarthId(context.getSource().getLevel(), foundId)){
                Faction faction = playerData.getCurrentFaction(context.getSource().getLevel());
                if(faction.getSpawnData() != null){
                    BlockPos pos =  FactionUtil.getSpawnBlockPos(context.getSource().getLevel(), foundId);
                    if(pos != null) {
                        if(ModDimensions.isInMiddleEarth(sourcePlayer.level()))
                            sourcePlayer.setRespawnPosition(ModDimensions.ME_WORLD_KEY, pos, 0, true, true);
                        MutableComponent sourceText = Component.translatable("command.me.set.spawn.middle_earth.success", Component.translatable("spawn."+foundId.toLanguageKey()), pos.getX(), pos.getY(), pos.getZ());
                        sourcePlayer.sendSystemMessage(sourceText.withColor(ModColors.SUCCESS.color));
                        return 0;
                    }
                }
            }
            MutableComponent sourceText = Component.translatable("command.me.set.spawn.middle_earth.no_spawn_found", foundId.toString());
            sourcePlayer.sendSystemMessage(sourceText.withColor(ModColors.WARNING.color));
        } catch (FactionIdentifierException e){
            MutableComponent errorMessage = Component.translatable(FactionIdentifierException.KEY, playerData.getCurrentFactionId().toString());
            sourcePlayer.sendSystemMessage(errorMessage.withColor(ModColors.ALERT.color));
        }

        return 0;
    }

    private static int setPlayerSpawnMiddleEarth(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        ServerPlayer targetedPlayer = EntityArgument.getPlayer(context, PLAYER);

        ResourceLocation foundId = ResourceLocationArgument.getId(context, SPAWN_ID);
        PlayerData playerData = StateSaverAndLoader.getPlayerState(targetedPlayer);

        if(!playerData.hasAffilition()){
            MutableComponent sourceText = Component.translatable("command.me.set.player.spawn.middle_earth.no_faction", targetedPlayer.getName());
            context.getSource().sendSystemMessage(sourceText.withColor(ModColors.WARNING.color));
            return 0;
        }

        try{
            if(playerData.setSpawnMiddleEarthId(context.getSource().getLevel(), foundId)){
                Faction faction = playerData.getCurrentFaction(context.getSource().getLevel());
                if(faction != null && faction.getSpawnData() != null){
                    BlockPos pos =  faction.getSpawnData().getSpawnBlockPos(foundId);
                    if(pos != null) {
                        if(ModDimensions.isInMiddleEarth(targetedPlayer.level()))
                            targetedPlayer.setRespawnPosition(ModDimensions.ME_WORLD_KEY, pos, 0, true, true);

                        MutableComponent targetText = Component.translatable("command.me.set.spawn.middle_earth.success", Component.translatable("spawn."+foundId.toLanguageKey()), pos.getX(), pos.getY(), pos.getZ());
                        targetedPlayer.sendSystemMessage(targetText.withColor(ModColors.SUCCESS.color));
                        MutableComponent sourceText = Component.translatable("command.me.set.player.spawn.middle_earth.success", targetedPlayer.getName(),Component.translatable("spawn."+foundId.toLanguageKey()), pos.getX(), pos.getY(), pos.getZ());
                        context.getSource().sendSystemMessage(sourceText.withColor(ModColors.SUCCESS.color));
                        return 0;
                    }
                }
            }
            MutableComponent sourceText = Component.translatable("command.me.set.spawn.middle_earth.no_spawn_found", foundId.toString());
            context.getSource().sendSystemMessage(sourceText.withColor(ModColors.WARNING.color));
        } catch (FactionIdentifierException e){
            MutableComponent errorMessage = Component.translatable(FactionIdentifierException.KEY, playerData.getCurrentFactionId().toString());
            context.getSource().sendSystemMessage(errorMessage.withColor(ModColors.ALERT.color));
        }

        return 0;
    }

    private static int setSpawnOverworld(CommandContext<CommandSourceStack> context) {
        if(!context.getSource().isPlayer() || context.getSource().getPlayer() == null)
            return 0;
        BlockPos pos = BlockPosArgument.getBlockPos(context, OVERWORLD_COORD);
        ServerPlayer player = context.getSource().getPlayer();
        PlayerData data = StateSaverAndLoader.getPlayerState(player);
        data.setOverworldSpawn(pos);
        if(ModDimensions.isInOverworld(player.level()))
            player.setRespawnPosition(Level.OVERWORLD, pos, 0, true, true);

        MutableComponent sourceText = Component.translatable("command.me.set.spawn.overworld.success", pos.getX(), pos.getY(), pos.getZ());
        context.getSource().sendSystemMessage(sourceText.withColor(ModColors.SUCCESS.color));

        return 0;
    }

    private static int setPlayerSpawnOverworld(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        ServerPlayer targetedPlayer = EntityArgument.getPlayer(context, PLAYER);
        BlockPos pos = BlockPosArgument.getBlockPos(context, OVERWORLD_COORD);

        PlayerData data = StateSaverAndLoader.getPlayerState(targetedPlayer);
        data.setOverworldSpawn(pos);

        MutableComponent sourceText = Component.translatable("command.me.set.player.spawn.overworld.success", targetedPlayer.getName() ,pos.getX(), pos.getY(), pos.getZ());
        context.getSource().sendSystemMessage(sourceText.withColor(ModColors.SUCCESS.color));

        MutableComponent targetText = Component.translatable("command.me.set.spawn.overworld.success", pos.getX(), pos.getY(), pos.getZ());
        targetedPlayer.sendSystemMessage(targetText.withColor(ModColors.SUCCESS.color));

        return 0;
    }

    // endregion

    // region Resets
    private static int resetPlayerSpawnOverworld(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        ServerPlayer targetedPlayer = EntityArgument.getPlayer(context, PLAYER);
        PlayerData data = StateSaverAndLoader.getPlayerState(targetedPlayer);
        BlockPos pos = context.getSource().getServer().overworld().getSharedSpawnPos();
        data.setOverworldSpawn(pos);

        MutableComponent sourceText = Component.translatable("command.me.reset.player.spawn.overworld.success", targetedPlayer.getName() ,pos.getX(), pos.getY(), pos.getZ());
        context.getSource().sendSystemMessage(sourceText.withColor(ModColors.SUCCESS.color));
        MutableComponent targetText = Component.translatable("command.me.reset.spawn.overworld.success", pos.getX(), pos.getY(), pos.getZ());
        targetedPlayer.sendSystemMessage(targetText.withColor(ModColors.SUCCESS.color));
        return 0;
    }

    private static int resetSpawnOverworld(CommandContext<CommandSourceStack> context) {
        if(!context.getSource().isPlayer() || context.getSource().getPlayer() == null)
            return 0;
        ServerPlayer player = context.getSource().getPlayer();
        PlayerData data = StateSaverAndLoader.getPlayerState(player);
        BlockPos pos = context.getSource().getServer().overworld().getSharedSpawnPos();
        data.setOverworldSpawn(pos);
        MutableComponent sourceText = Component.translatable("command.me.reset.spawn.overworld.success",pos.getX(), pos.getY(), pos.getZ());
        player.sendSystemMessage(sourceText.withColor(ModColors.SUCCESS.color));
        return 0;
    }

    private static int resetPlayerSpawnMiddleEarth(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        ServerPlayer targetedPlayer = EntityArgument.getPlayer(context, PLAYER);
        PlayerData playerData = StateSaverAndLoader.getPlayerState(targetedPlayer);

        if(!playerData.hasAffilition()){
            MutableComponent sourceText = Component.translatable("command.me.reset.player.spawn.middle_earth.no_faction", targetedPlayer.getName());
            context.getSource().sendSystemMessage(sourceText.withColor(ModColors.WARNING.color));
            return 0;
        }

        try{
            Faction faction = playerData.getCurrentFaction(context.getSource().getLevel());
            ResourceLocation spawnId = faction.getSpawnData().getDefaultSpawn();
            BlockPos pos = FactionUtil.getSpawnBlockPos(context.getSource().getLevel(), spawnId);
            if(pos != null){
                playerData.setSpawnMiddleEarthId(context.getSource().getLevel(), spawnId);
                if(ModDimensions.isInMiddleEarth(targetedPlayer.level()))
                    targetedPlayer.setRespawnPosition(ModDimensions.ME_WORLD_KEY, pos, 0, true, true);

                MutableComponent sourceText = Component.translatable("command.me.reset.player.spawn.middle_earth.success", targetedPlayer.getName(), Component.translatable("spawn." + spawnId.toLanguageKey()),pos.getX(), pos.getY(), pos.getZ());
                context.getSource().sendSystemMessage(sourceText.withColor(ModColors.SUCCESS.color));

                MutableComponent targetText = Component.translatable("command.me.reset.spawn.middle_earth.success", Component.translatable("spawn." + spawnId.toLanguageKey()), pos.getX(), pos.getY(), pos.getZ());
                targetedPlayer.sendSystemMessage(targetText.withColor(ModColors.SUCCESS.color));
                return 0;
            }
        } catch (FactionIdentifierException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    private static int resetSpawnMiddleEarth(CommandContext<CommandSourceStack> context) {
        if(!context.getSource().isPlayer() || context.getSource().getPlayer() == null)
            return 0;
        ServerPlayer player = context.getSource().getPlayer();
        PlayerData playerData = StateSaverAndLoader.getPlayerState(player);
        if(!playerData.hasAffilition()){
            MutableComponent sourceText = Component.translatable("command.me.reset.spawn.middle_earth.no_faction");
            player.sendSystemMessage(sourceText.withColor(ModColors.WARNING.color));
            return 0;
        }

        try{
            Faction faction = playerData.getCurrentFaction(context.getSource().getLevel());
            ResourceLocation foundSpawn = faction.getSpawnData().getDefaultSpawn();
            BlockPos newSpawn = faction.getSpawnData().getSpawnBlockPos(foundSpawn);

            if(newSpawn != null){
                if(ModDimensions.isInMiddleEarth(player.level()))
                    player.setRespawnPosition(ModDimensions.ME_WORLD_KEY, newSpawn, 0, true, true);

                playerData.setSpawnMiddleEarthId(context.getSource().getLevel(), foundSpawn);
                MutableComponent sourceText = Component.translatable("command.me.reset.spawn.middle_earth.success", Component.translatable("spawn." + foundSpawn.toLanguageKey()), newSpawn.getX(), newSpawn.getY(), newSpawn.getZ());
                player.sendSystemMessage(sourceText.withColor(ModColors.SUCCESS.color));
                return 0;
            }
        } catch (FactionIdentifierException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    // endregion

    // region Teleports
    private static int teleportToSpawnMiddleEarth(CommandContext<CommandSourceStack> context) {
        if(!context.getSource().isPlayer() || context.getSource().getPlayer() == null)
            return 0;

        boolean welcomeNeeded = BoolArgumentType.getBool(context, WELCOME);

        ServerPlayer player = context.getSource().getPlayer();
        PlayerData data = StateSaverAndLoader.getPlayerStateReadOnly(player);
        if(data != null){
            if(data.hasAffilition()){
                Vec3 spawnCoordinates = data.getSpawnMiddleEarthCoordinate(context.getSource().getLevel());
                if(ModDimensions.isInOverworld(player.level()) && data.getOverworldSpawnCoordinates() == null){
                    data.setOverworldSpawn(player.blockPosition());
                }
                if(spawnCoordinates != null) {
                    BlockPos pos = new BlockPos((int) spawnCoordinates.x, (int) spawnCoordinates.y, (int) spawnCoordinates.z);
                    if(ModDimensions.isInMiddleEarth(player.level()))
                        player.setRespawnPosition(ModDimensions.ME_WORLD_KEY, pos, 0, true, true);
                    ModDimensions.teleportPlayerToMe(player, new Vec3(spawnCoordinates.x, spawnCoordinates.y, spawnCoordinates.z), true, welcomeNeeded);
                    MutableComponent sourceText = Component.translatable("command.me.teleport.spawn.middle_earth.success", Component.translatable("spawn."+ data.getCurrentSpawnId().toLanguageKey()));
                    context.getSource().sendSystemMessage(sourceText.withColor(ModColors.SUCCESS.color));
                    return 0;
                }
            }
        }
        MutableComponent sourceText = Component.translatable("command.me.teleport.spawn.middle_earth.no_spawn");
        context.getSource().sendSystemMessage(sourceText.withColor(ModColors.WARNING.color));
        return 0;
    }

    private static int teleportPlayerToSpawnMiddleEarth(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        ServerPlayer target = EntityArgument.getPlayer(context, PLAYER);
        boolean welcomeNeeded = BoolArgumentType.getBool(context, WELCOME);

        PlayerData data = StateSaverAndLoader.getPlayerStateReadOnly(target);
        if(data != null){
            if(data.hasAffilition()){
                Vec3 spawnCoordinates = data.getSpawnMiddleEarthCoordinate(context.getSource().getLevel());
                if(ModDimensions.isInOverworld(target.level()) && data.getOverworldSpawnCoordinates() == null){
                    data.setOverworldSpawn(target.blockPosition());
                }
                if(spawnCoordinates != null) {
                    BlockPos pos = new BlockPos((int) spawnCoordinates.x, (int) spawnCoordinates.y, (int) spawnCoordinates.z);
                    if(ModDimensions.isInMiddleEarth(target.level()))
                        target.setRespawnPosition(ModDimensions.ME_WORLD_KEY, pos, 0, true, true);
                    ModDimensions.teleportPlayerToMe(target, new Vec3(spawnCoordinates.x, spawnCoordinates.y, spawnCoordinates.z), true, welcomeNeeded);
                    MutableComponent sourceText = Component.translatable("command.me.teleport.player.spawn.middle_earth.success", target.getName(), Component.translatable("spawn."+data.getCurrentSpawnId().toLanguageKey()), pos.getX(), pos.getY(), pos.getZ());
                    context.getSource().sendSystemMessage(sourceText.withColor(ModColors.SUCCESS.color));
                    MutableComponent targetText = Component.translatable("command.me.teleport.spawn.middle_earth.success", Component.translatable("spawn."+data.getCurrentSpawnId().toLanguageKey()));
                    target.sendSystemMessage(targetText.withColor(ModColors.SUCCESS.color));
                    return 0;
                }
            }
        }
        MutableComponent sourceText = Component.translatable("command.me.teleport.player.spawn.middle_earth.no_spawn", target.getName());
        context.getSource().sendSystemMessage(sourceText.withColor(ModColors.WARNING.color));
        return 0;
    }


    private static int teleportToSpawnOverworld(CommandContext<CommandSourceStack> context) {
        if(!context.getSource().isPlayer() || context.getSource().getPlayer() == null)
            return 0;

        ServerPlayer player = context.getSource().getPlayer();
        if(ModDimensions.teleportPlayerToOverworld(player)){
            MutableComponent sourceText = Component.translatable("command.me.teleport.spawn.middle_earth.success");
            context.getSource().sendSystemMessage(sourceText.withColor(ModColors.SUCCESS.color));
            return 0;
        }
        MutableComponent sourceText = Component.translatable("command.me.teleport.spawn.middle_earth.error");
        context.getSource().sendSystemMessage(sourceText.withColor(ModColors.WARNING.color));
        return 0;
    }

    private static int teleportPlayerToSpawnOverworld(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        ServerPlayer player = EntityArgument.getPlayer(context, PLAYER);

        if(ModDimensions.teleportPlayerToOverworld(player)){
            MutableComponent sourceText = Component.translatable("command.me.teleport.player.spawn.middle_earth.success", player.getName(), player.getX(), player.getY(), player.getZ());
            context.getSource().sendSystemMessage(sourceText.withColor(ModColors.SUCCESS.color));
            return 0;
        }
        MutableComponent sourceText = Component.translatable("command.me.teleport.player.spawn.middle_earth.no_spawn", player.getName());
        context.getSource().sendSystemMessage(sourceText.withColor(ModColors.WARNING.color));
        return 0;
    }

    private static int forceTeleportToSpawnMiddleEarth(CommandContext<CommandSourceStack> context) {
        if(!context.getSource().isPlayer() || context.getSource().getPlayer() == null)
            return 0;
        ResourceLocation spawnId = ResourceLocationArgument.getId(context, SPAWN_ID);

        if(FactionUtil.forceTeleportToSpawnMiddleEarthId(context.getSource().getPlayer(), spawnId)){
            BlockPos pos = FactionUtil.getSpawnBlockPos(context.getSource().getLevel(), spawnId);
            MutableComponent targetText = Component.translatable("command.me.teleport.to.spawn.middle_earth.success", Component.translatable("spawn."+spawnId.toLanguageKey()), pos.getX(), pos.getY(), pos.getZ());
            context.getSource().sendSystemMessage(targetText.withColor(ModColors.SUCCESS.color));
        } else {
            MutableComponent sourceText = Component.translatable("command.me.teleport.to.spawn.middle_earth.error", Component.translatable("spawn."+spawnId.toLanguageKey()));
            context.getSource().sendSystemMessage(sourceText.withColor(ModColors.WARNING.color));
        }
        return 0;
    }

    private static int forceTeleportPlayerToSpawnMiddleEarth(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        ServerPlayer targetedPlayer = EntityArgument.getPlayer(context, PLAYER);
        ResourceLocation spawnId = ResourceLocationArgument.getId(context, SPAWN_ID);

        if(FactionUtil.forceTeleportToSpawnMiddleEarthId(targetedPlayer, spawnId)){
            BlockPos pos = FactionUtil.getSpawnBlockPos(context.getSource().getLevel(), spawnId);
            MutableComponent targetText = Component.translatable("command.me.teleport.to.spawn.middle_earth.success", Component.translatable("spawn."+spawnId.toLanguageKey()), pos.getX(), pos.getY(), pos.getZ());
            targetedPlayer.sendSystemMessage(targetText.withColor(ModColors.SUCCESS.color));
            MutableComponent sourceText = Component.translatable("command.me.teleport.player.to.spawn.middle_earth.success", targetedPlayer.getName(),Component.translatable("spawn."+spawnId.toLanguageKey()), pos.getX(), pos.getY(), pos.getZ());
            context.getSource().sendSystemMessage(sourceText.withColor(ModColors.SUCCESS.color));
        } else {
            MutableComponent sourceText = Component.translatable("command.me.teleport.player.to.spawn.middle_earth.error", Component.translatable("spawn."+spawnId.toLanguageKey()));
            context.getSource().sendSystemMessage(sourceText.withColor(ModColors.WARNING.color));
        }

        return 0;
    }
    // endregion
}
