package net.jukoz.me.world.features.tree;

import net.jukoz.me.utils.NeoForgeRegistrationBridge;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.jukoz.me.MiddleEarth;
import net.jukoz.me.world.features.tree.roots.MirkwoodRootPlacer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.rootplacers.RootPlacer;
import net.minecraft.world.level.levelgen.feature.rootplacers.RootPlacerType;

public class ModRootPlacerType {
    public static final RootPlacerType<MirkwoodRootPlacer> MIRKWOOD_ROOT_PLACER = register(
            "mirkwood_root_placer", MirkwoodRootPlacer.CODEC);

    private static <P extends RootPlacer> RootPlacerType register(String id, MapCodec<P> codec) {
        return NeoForgeRegistrationBridge.register(BuiltInRegistries.ROOT_PLACER_TYPE, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, id), new RootPlacerType(codec));
    }
}
