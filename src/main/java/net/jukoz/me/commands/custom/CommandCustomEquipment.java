package net.jukoz.me.commands.custom;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.jukoz.me.MiddleEarth;
import net.jukoz.me.commands.ModCommands;
import net.jukoz.me.commands.suggestions.AllCapesSuggestionProvider;
import net.jukoz.me.commands.suggestions.AllHoodsSuggestionProvider;
import net.jukoz.me.item.ModDataComponentTypes;
import net.jukoz.me.item.dataComponents.CapeDataComponent;
import net.jukoz.me.item.dataComponents.CustomDyeableDataComponent;
import net.jukoz.me.item.dataComponents.HoodDataComponent;
import net.jukoz.me.item.items.armor.CapeChestplateItem;
import net.jukoz.me.item.items.armor.CustomChestplateItem;
import net.jukoz.me.item.items.armor.CustomHelmetItem;
import net.jukoz.me.item.items.armor.HoodHelmetItem;
import net.jukoz.me.item.utils.armor.capes.ModCapes;
import net.jukoz.me.item.utils.armor.hoods.ModHoodStates;
import net.jukoz.me.item.utils.armor.hoods.ModHoods;
import net.jukoz.me.utils.ModColors;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;
import java.util.Objects;

import static net.minecraft.commands.Commands.argument;
import static net.minecraft.commands.Commands.literal;

public class CommandCustomEquipment {
    private static final String EQUIPMENT = "equipment";
    private static final String CAPE = "cape";
    private static final String HOOD = "hood";
    private static final String CAPE_VALUE = "cape_value";
    private static final String HOOD_VALUE = "hood_value";

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext commandRegistryAccess, Commands.CommandSelection registrationEnvironment) {

        dispatcher.register(literal(ModCommands.BASE_COMMAND)
                .requires(source -> source.hasPermission(2))
                .then(literal(EQUIPMENT)
                    .then(literal(CAPE)
                        .then(argument(CAPE_VALUE, StringArgumentType.string())
                                .suggests(new AllCapesSuggestionProvider())
                                .executes(CommandCustomEquipment::setCape)))));

        dispatcher.register(literal(ModCommands.BASE_COMMAND)
                .requires(source -> source.hasPermission(2))
                .then(literal(EQUIPMENT)
                    .then(literal(HOOD)
                        .then(argument(HOOD_VALUE, StringArgumentType.string())
                                .suggests(new AllHoodsSuggestionProvider())
                                .executes(CommandCustomEquipment::setHood)))));
    }

    private static int setCape(CommandContext<CommandSourceStack> context) {
        ModCapes cape = ModCapes.valueOf(StringArgumentType.getString(context, CAPE_VALUE).toUpperCase());

        ItemStack handStack = Objects.requireNonNull(context.getSource().getPlayer()).getInventory().getSelected();

        if (handStack.isEmpty()){
            MutableComponent sourceText = Component.translatable("command.me.cape.hand_empty");
            context.getSource().sendSystemMessage(sourceText.withColor(ModColors.WARNING.color));
            return 0;
        }

        // TODO: literally cape-thingy such as gondorian_hero_cape should not be setCape-d
        if ((handStack.getItem() instanceof CustomChestplateItem || handStack.getItem() instanceof CapeChestplateItem)){
            handStack.set(ModDataComponentTypes.CAPE_DATA, CapeDataComponent.newCape(cape));
            MutableComponent sourceText = Component.translatable("command.me.cape.success").append(Component.translatable("tooltip." + MiddleEarth.MOD_ID + "." + cape.getName()));
            context.getSource().sendSystemMessage(sourceText.withColor(ModColors.SUCCESS.color));
            return 0;
        } else {
            MutableComponent sourceText = Component.translatable("command.me.cape.wrong_item");
            context.getSource().sendSystemMessage(sourceText.withColor(ModColors.WARNING.color));
            return 0;
        }
    }

    private static int setHood(CommandContext<CommandSourceStack> context) {
        ModHoods hood = ModHoods.valueOf(StringArgumentType.getString(context, HOOD_VALUE).toUpperCase());

        ItemStack handStack = Objects.requireNonNull(context.getSource().getPlayer()).getInventory().getSelected();

        if (handStack.isEmpty()){
            MutableComponent sourceText = Component.translatable("command.me.hood.hand_empty");
            context.getSource().sendSystemMessage(sourceText.withColor(ModColors.WARNING.color));
            return 0;
        }

        if ((handStack.getItem() instanceof CustomHelmetItem || handStack.getItem() instanceof HoodHelmetItem)){
            if (hood.getConstantState() == ModHoodStates.DOWN){
                handStack.set(ModDataComponentTypes.HOOD_DATA, new HoodDataComponent(true, hood, CustomDyeableDataComponent.DEFAULT_COLOR));
            } else if (hood.getConstantState() == ModHoodStates.UP || hood.getConstantState() == null){
                handStack.set(ModDataComponentTypes.HOOD_DATA, new HoodDataComponent(false, hood, CustomDyeableDataComponent.DEFAULT_COLOR));
            }
            MutableComponent sourceText = Component.translatable("command.me.hood.success").append(Component.translatable("tooltip." + MiddleEarth.MOD_ID + "." + hood.getName()));
            context.getSource().sendSystemMessage(sourceText.withColor(ModColors.SUCCESS.color));
            return 0;
        } else {
            MutableComponent sourceText = Component.translatable("command.me.hood.wrong_item");
            context.getSource().sendSystemMessage(sourceText.withColor(ModColors.WARNING.color));
            return 0;
        }
    }
}
