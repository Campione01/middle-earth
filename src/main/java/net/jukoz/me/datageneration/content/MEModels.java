package net.jukoz.me.datageneration.content;

import net.jukoz.me.MiddleEarth;
import net.minecraft.data.models.model.ModelTemplate;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.resources.ResourceLocation;
import java.util.Optional;

public class MEModels {
    public static final ModelTemplate VERTICAL_SLAB;
    public static final ModelTemplate VERTICAL_SLAB_INNER;
    public static final ModelTemplate VERTICAL_SLAB_OUTER;
    public static final ModelTemplate VERTICAL_COLUMN_SLAB;
    public static final ModelTemplate VERTICAL_COLUMN_SLAB_INNER;
    public static final ModelTemplate VERTICAL_COLUMN_SLAB_OUTER;

    public static final ModelTemplate WOOD_STOOL;
    public static final ModelTemplate WOOD_BENCH;
    public static final ModelTemplate WOOD_TABLE;
    public static final ModelTemplate WOOD_CHAIR;

    public static final ModelTemplate STONE_STOOL;
    public static final ModelTemplate STONE_TABLE;
    public static final ModelTemplate STONE_CHAIR;

    public static final ModelTemplate ROCKS_STAGE_0;
    public static final ModelTemplate ROCKS_STAGE_1;
    public static final ModelTemplate ROCKS_STAGE_2;
    public static final ModelTemplate ROCKS_STAGE_3;

    public static final ModelTemplate LARGE_DOOR_LEFT;
    public static final ModelTemplate LARGE_DOOR_LEFT_OPEN;
    public static final ModelTemplate LARGE_DOOR_RIGHT;
    public static final ModelTemplate LARGE_DOOR_RIGHT_OPEN;

    public static final ModelTemplate LARGE_THICK_DOOR_LEFT;
    public static final ModelTemplate LARGE_THICK_DOOR_LEFT_OPEN;
    public static final ModelTemplate LARGE_THICK_DOOR_RIGHT;
    public static final ModelTemplate LARGE_THICK_DOOR_RIGHT_OPEN;

    public static final ModelTemplate THICK_LADDER;

    private static ModelTemplate block(String parent, TextureSlot... requiredTextureKeys) {
        return new ModelTemplate(Optional.of(ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "block/" + parent)), Optional.empty(), requiredTextureKeys);
    }

    private static ModelTemplate block(String parent, String variant, TextureSlot... requiredTextureKeys) {
        return new ModelTemplate(Optional.of(ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "block/" + parent)), Optional.of(variant), requiredTextureKeys);
    }

    static {
        VERTICAL_SLAB = block("vertical_slab", TextureSlot.ALL, TextureSlot.PARTICLE);
        VERTICAL_SLAB_INNER = block("vertical_slab_inner", "_inner", TextureSlot.ALL, TextureSlot.PARTICLE);
        VERTICAL_SLAB_OUTER = block("vertical_slab_outer", "_outer", TextureSlot.ALL, TextureSlot.PARTICLE);
        VERTICAL_COLUMN_SLAB = block("vertical_column_slab", TextureSlot.TOP, TextureSlot.BOTTOM, TextureSlot.SIDE, TextureSlot.PARTICLE);
        VERTICAL_COLUMN_SLAB_INNER = block("vertical_column_slab_inner", "_inner", TextureSlot.TOP, TextureSlot.BOTTOM, TextureSlot.SIDE, TextureSlot.PARTICLE);
        VERTICAL_COLUMN_SLAB_OUTER = block("vertical_column_slab_outer", "_outer", TextureSlot.TOP, TextureSlot.BOTTOM, TextureSlot.SIDE, TextureSlot.PARTICLE);

        WOOD_STOOL = block("wood_stool_template", TextureSlot.ALL, TextureSlot.PARTICLE);
        WOOD_BENCH = block("wood_bench_template", TextureSlot.ALL, TextureSlot.PARTICLE);
        WOOD_TABLE = block("wood_table_template", TextureSlot.ALL, TextureSlot.PARTICLE);
        WOOD_CHAIR = block("wood_chair_template", TextureSlot.ALL, TextureSlot.PARTICLE);

        STONE_STOOL = block("stone_stool_template", TextureSlot.ALL, TextureSlot.PARTICLE);
        STONE_TABLE = block("stone_table_template", TextureSlot.ALL, TextureSlot.PARTICLE);
        STONE_CHAIR = block("stone_chair_template", TextureSlot.ALL, TextureSlot.PARTICLE);

        ROCKS_STAGE_0 = block("rocks_stage_0_template", TextureSlot.ALL, TextureSlot.PARTICLE);
        ROCKS_STAGE_1 = block("rocks_stage_1_template", "_1", TextureSlot.ALL, TextureSlot.PARTICLE);
        ROCKS_STAGE_2 = block("rocks_stage_2_template", "_2", TextureSlot.ALL, TextureSlot.PARTICLE);
        ROCKS_STAGE_3 = block("rocks_stage_3_template", "_3", TextureSlot.ALL, TextureSlot.PARTICLE);

        LARGE_DOOR_LEFT = block("large_door_left", TextureSlot.ALL, TextureSlot.PARTICLE);
        LARGE_DOOR_LEFT_OPEN = block("large_door_left_open", TextureSlot.ALL, TextureSlot.PARTICLE);
        LARGE_DOOR_RIGHT = block("large_door_right", TextureSlot.ALL, TextureSlot.PARTICLE);
        LARGE_DOOR_RIGHT_OPEN = block("large_door_right_open", TextureSlot.ALL, TextureSlot.PARTICLE);

        LARGE_THICK_DOOR_LEFT = block("thick_large_door_left", TextureSlot.ALL, TextureSlot.PARTICLE);
        LARGE_THICK_DOOR_LEFT_OPEN = block("thick_large_door_left_open", TextureSlot.ALL, TextureSlot.PARTICLE);
        LARGE_THICK_DOOR_RIGHT = block("thick_large_door_right", TextureSlot.ALL, TextureSlot.PARTICLE);
        LARGE_THICK_DOOR_RIGHT_OPEN = block("thick_large_door_right_open", TextureSlot.ALL, TextureSlot.PARTICLE);

        THICK_LADDER = block("thick_ladder", TextureSlot.TEXTURE, TextureSlot.PARTICLE);
    }
}
