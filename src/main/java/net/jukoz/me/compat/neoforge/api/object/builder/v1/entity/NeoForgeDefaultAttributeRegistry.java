package net.jukoz.me.compat.neoforge.api.object.builder.v1.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

public final class NeoForgeDefaultAttributeRegistry {
    private static final Map<EntityType<? extends LivingEntity>, Supplier<AttributeSupplier.Builder>> PENDING = new LinkedHashMap<>();

    private NeoForgeDefaultAttributeRegistry() {
    }

    public static void register(IEventBus modEventBus) {
        modEventBus.addListener(NeoForgeDefaultAttributeRegistry::onEntityAttributeCreation);
    }

    public static <T extends LivingEntity> void register(EntityType<T> type, AttributeSupplier.Builder builder) {
        register(type, () -> builder);
    }

    public static <T extends LivingEntity> void register(EntityType<T> type, Supplier<AttributeSupplier.Builder> builderSupplier) {
        PENDING.put(type, builderSupplier);
    }

    private static void onEntityAttributeCreation(EntityAttributeCreationEvent event) {
        PENDING.forEach((type, builderSupplier) -> event.put(type, builderSupplier.get().build()));
    }
}
