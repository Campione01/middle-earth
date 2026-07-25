package net.jukoz.me.commands.custom;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.jukoz.me.commands.ModCommands;
import net.jukoz.me.world.dimension.ModDimensions;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.arguments.coordinates.Coordinates;
import net.minecraft.commands.arguments.coordinates.Vec3Argument;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.Vec3;

public class CommandDimensionTeleport {
    private static final String TP = "tp";
    private static final String PLAYER = "player";
    private static final String LOCATION = "location";
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext commandRegistryAccess, Commands.CommandSelection registrationEnvironment) {
        dispatcher.register((Commands.literal(ModCommands.BASE_COMMAND)
                .requires(source -> source.hasPermission(2)))
                .then((Commands.literal(TP))
                .then(Commands.argument(PLAYER, EntityArgument.player())
                .then(Commands.argument(LOCATION, Vec3Argument.vec3())
                .executes(CommandDimensionTeleport::run)))));
    }

    public static int run(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        try{
            Coordinates posArgument = Vec3Argument.getCoordinates(context, LOCATION);
            Vec3 coordinates = posArgument.getPosition(context.getSource());

            ServerPlayer targettedPlayer = EntityArgument.getPlayer(context, PLAYER);

            ModDimensions.teleportPlayerToMe(targettedPlayer, coordinates, true, true);
            return 1;
        } catch (Exception e){
            return 0;
        }
    }
}
