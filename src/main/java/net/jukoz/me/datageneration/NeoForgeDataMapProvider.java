package net.jukoz.me.datageneration;

import net.jukoz.me.compat.neoforge.api.registry.CompostingChanceRegistry;
import net.jukoz.me.compat.neoforge.api.registry.FuelRegistry;
import net.jukoz.me.compat.neoforge.api.registry.OxidizableBlocksRegistry;
import net.jukoz.me.compat.neoforge.api.registry.StrippableBlockRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
import net.neoforged.neoforge.registries.datamaps.builtin.FurnaceFuel;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;
import net.neoforged.neoforge.registries.datamaps.builtin.Oxidizable;
import net.neoforged.neoforge.registries.datamaps.builtin.Strippable;
import net.neoforged.neoforge.registries.datamaps.builtin.Waxable;

import java.util.concurrent.CompletableFuture;

public class NeoForgeDataMapProvider extends DataMapProvider {
    public NeoForgeDataMapProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather(HolderLookup.Provider provider) {
        FuelRegistry.INSTANCE.entries().forEach((item, ticks) -> {
            ResourceLocation id = BuiltInRegistries.ITEM.getKey(item);
            if (item != Items.AIR) {
                builder(NeoForgeDataMaps.FURNACE_FUELS).add(id, new FurnaceFuel(ticks), false);
            }
        });

        CompostingChanceRegistry.INSTANCE.entries().forEach((item, chance) -> {
            ResourceLocation id = BuiltInRegistries.ITEM.getKey(item);
            if (item != Items.AIR) {
                builder(NeoForgeDataMaps.COMPOSTABLES).add(id, new Compostable(chance), false);
            }
        });

        StrippableBlockRegistry.entries().forEach((input, stripped) -> {
            ResourceLocation id = BuiltInRegistries.BLOCK.getKey(input);
            if (input != Blocks.AIR && stripped != Blocks.AIR) {
                builder(NeoForgeDataMaps.STRIPPABLES).add(id, new Strippable(stripped), false);
            }
        });

        OxidizableBlocksRegistry.oxidizables().forEach((lessOxidized, moreOxidized) -> {
            ResourceLocation id = BuiltInRegistries.BLOCK.getKey(lessOxidized);
            if (lessOxidized != Blocks.AIR && moreOxidized != Blocks.AIR) {
                builder(NeoForgeDataMaps.OXIDIZABLES).add(id, new Oxidizable(moreOxidized), false);
            }
        });

        OxidizableBlocksRegistry.waxables().forEach((unwaxed, waxed) -> {
            ResourceLocation id = BuiltInRegistries.BLOCK.getKey(unwaxed);
            if (unwaxed != Blocks.AIR && waxed != Blocks.AIR) {
                builder(NeoForgeDataMaps.WAXABLES).add(id, new Waxable(waxed), false);
            }
        });
    }
}
