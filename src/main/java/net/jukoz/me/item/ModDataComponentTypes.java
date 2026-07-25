package net.jukoz.me.item;

import net.jukoz.me.utils.NeoForgeRegistrationBridge;

import net.jukoz.me.MiddleEarth;
import net.jukoz.me.item.dataComponents.*;
import net.jukoz.me.utils.LoggerUtil;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import java.util.function.UnaryOperator;

public class ModDataComponentTypes {

    public static final DataComponentType<TemperatureDataComponent> TEMPERATURE_DATA = register("temperature", (builder) -> {
        return builder.persistent(TemperatureDataComponent.CODEC).networkSynchronized(TemperatureDataComponent.PACKET_CODEC);
    });
    public static final DataComponentType<CapeDataComponent> CAPE_DATA = register("cape", (builder) -> {
        return builder.persistent(CapeDataComponent.CODEC).networkSynchronized(CapeDataComponent.PACKET_CODEC);
    });

    public static final DataComponentType<HoodDataComponent> HOOD_DATA = register("hood", (builder) -> {
        return builder.persistent(HoodDataComponent.CODEC).networkSynchronized(HoodDataComponent.PACKET_CODEC);
    });

    public static final DataComponentType<CustomDyeableDataComponent> DYE_DATA = register("dye", (builder) -> {
        return builder.persistent(CustomDyeableDataComponent.CODEC).networkSynchronized(CustomDyeableDataComponent.PACKET_CODEC);
    });

    public static final DataComponentType<MountArmorAddonComponent> MOUNT_ARMOR_DATA = register("mount_armor_addon", (builder) -> {
        return builder.persistent(MountArmorAddonComponent.CODEC).networkSynchronized(MountArmorAddonComponent.PACKET_CODEC);
    });

    private static <T> DataComponentType<T> register(String id, UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
        return NeoForgeRegistrationBridge.register(BuiltInRegistries.DATA_COMPONENT_TYPE, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, id), ((DataComponentType.Builder)builderOperator.apply(DataComponentType.builder())).build());
    }

    public static void registerModComponentTypes() {
        LoggerUtil.logDebugMsg("Registering Mod Component Types Items for " + MiddleEarth.MOD_ID);
    }
}
