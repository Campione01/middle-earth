package net.jukoz.me.item.utils;

import net.jukoz.me.MiddleEarth;
import net.jukoz.me.utils.ClientSideAccess;
import net.jukoz.me.utils.ModFactions;
import net.jukoz.me.utils.ModSubFactions;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ResolvableProfile;
import java.util.List;

public interface MEEquipmentTooltip {

    String COLOR_PREFIX = "#%06X";

    default void appendBaseTooltip(List<Component> tooltip, ItemStack stack, ModFactions faction, ModSubFactions subFaction) {
        ResolvableProfile profileComponent = stack.get(DataComponents.PROFILE);

        tooltip.add(Component.nullToEmpty(""));
        if (ClientSideAccess.hasShiftDown()) {
            if (faction != ModFactions.NONE){
                tooltip.add(Component.translatable("tooltip." + MiddleEarth.MOD_ID + ".faction").append(Component.translatable("tooltip." + MiddleEarth.MOD_ID + "." + faction.getName())));
            }
            if (subFaction != null) {
                tooltip.add(Component.translatable("tooltip." + MiddleEarth.MOD_ID + ".sub_faction").append(Component.translatable("tooltip." + MiddleEarth.MOD_ID + "." + subFaction.getName())));
            }

            if(!getAdditionalShiftLines(stack).isEmpty()) tooltip.addAll(getAdditionalShiftLines(stack));

            if (profileComponent != null && profileComponent.name().isPresent()) {
                tooltip.add(Component.translatable("tooltip." + MiddleEarth.MOD_ID + ".artisan").append(profileComponent.name().get()).withStyle(ChatFormatting.GRAY));
            }

            tooltip.add(Component.nullToEmpty(""));
        } else {
            tooltip.add(Component.translatable("tooltip." + MiddleEarth.MOD_ID + ".shift"));
        }
        if (!getAdditionalAltLines(stack).isEmpty()){
            if(ClientSideAccess.hasAltDown()){
                tooltip.add(Component.nullToEmpty(""));
                tooltip.add(Component.translatable("tooltip." + MiddleEarth.MOD_ID + ".customizations"));
                tooltip.addAll(getAdditionalAltLines(stack));
            }else {
                tooltip.add(Component.translatable("tooltip." + MiddleEarth.MOD_ID + ".alt"));
            }
        }
    }

    default void appendBaseArtefactTooltip(List<Component> tooltip, ItemStack stack) {
        tooltip.add(Component.nullToEmpty(""));
        if (ClientSideAccess.hasShiftDown()) {

            if(!(stack.getItem() instanceof BlockItem) && !(stack.getDamageValue() < stack.getMaxDamage() - 1)) {
                tooltip.add(Component.translatable("tooltip." + MiddleEarth.MOD_ID + ".broken").withStyle(ChatFormatting.GRAY));
            }

            tooltip.add(Component.translatable("tooltip." + MiddleEarth.MOD_ID + ".artefact").withStyle(ChatFormatting.GOLD));

            if(!getAdditionalShiftLines(stack).isEmpty()) tooltip.addAll(getAdditionalShiftLines(stack));

            tooltip.add(Component.literal(""));
            
            tooltip.add(Component.translatable("tooltip." + MiddleEarth.MOD_ID + "." + BuiltInRegistries.ITEM.getKey(stack.getItem()).getPath() + "_lore_0").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
            tooltip.add(Component.translatable("tooltip." + MiddleEarth.MOD_ID + "." + BuiltInRegistries.ITEM.getKey(stack.getItem()).getPath() + "_lore_1").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));

        } else {
            tooltip.add(Component.translatable("tooltip." + MiddleEarth.MOD_ID + ".shift"));
        }
        if (!getAdditionalAltLines(stack).isEmpty()){
            if(ClientSideAccess.hasAltDown()){
                tooltip.add(Component.nullToEmpty(""));
                tooltip.add(Component.translatable("tooltip." + MiddleEarth.MOD_ID + ".customizations"));
                tooltip.addAll(getAdditionalAltLines(stack));
            }else {
                tooltip.add(Component.translatable("tooltip." + MiddleEarth.MOD_ID + ".alt"));
            }
        }
    }

    default List<Component> getAdditionalShiftLines(ItemStack stack) {
        return List.of();
    }

    default List<Component> getAdditionalAltLines(ItemStack stack){
        return List.of();
    }
}
