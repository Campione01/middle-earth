package net.jukoz.me.datageneration;

import net.jukoz.me.compat.neoforge.api.datagen.v1.NeoForgeDataOutput;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.minecraft.data.PackOutput;

public final class NeoForgeDataGeneration {
    private NeoForgeDataGeneration() {
    }

    public static void gatherData(GatherDataEvent event) {
        DataGeneration.isDataGen = true;
        event.createDatapackRegistryObjects(DataGeneration.createRegistrySetBuilder());
        PackOutput packOutput = event.getGenerator().getPackOutput();
        NeoForgeDataOutput output = new NeoForgeDataOutput(packOutput.getOutputFolder());
        if (event.includeServer()) {
            event.addProvider(new NeoForgeDataMapProvider(packOutput, event.getLookupProvider()));
            event.addProvider(new BlockTagProvider(output, event.getLookupProvider()));
            event.addProvider(new BlockLootTableProvider(output, event.getLookupProvider()));
            event.addProvider(new ItemTagProvider(output, event.getLookupProvider()));
            event.getGenerator().getPackGenerator(true, "me_recipes", "")
                    .addProvider(recipeOutput -> new RecipeProvider(recipeOutput, event.getLookupProvider()));
            event.getGenerator().getPackGenerator(true, "me_artisan_handheld_recipes", "")
                    .addProvider(recipeOutput -> new ArtisanTableHandheldRecipeProvider(recipeOutput, event.getLookupProvider()));
            event.getGenerator().getPackGenerator(true, "me_artisan_armor_recipes", "")
                    .addProvider(recipeOutput -> new ArtisanTableArmorRecipeProvider(recipeOutput, event.getLookupProvider()));
            event.addProvider(new RaceProvider(output, event.getLookupProvider()));
            event.addProvider(new NpcProvider(output, event.getLookupProvider()));
            event.addProvider(new FactionProvider(output, event.getLookupProvider()));
        }
        if (event.includeClient()) {
            HelpingGenerator.generateFiles();
            event.addProvider(new ModelProvider(output));
        }
    }
}
