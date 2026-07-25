package net.jukoz.me.compat.neoforge.api.datagen.v1;

import net.minecraft.data.PackOutput;

import java.nio.file.Path;

public class NeoForgeDataOutput extends PackOutput {
    public NeoForgeDataOutput(Path outputFolder) {
        super(outputFolder);
    }

    public String getModId() {
        return "me";
    }
}
