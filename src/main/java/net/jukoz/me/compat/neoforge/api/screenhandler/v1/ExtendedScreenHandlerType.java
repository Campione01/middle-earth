package net.jukoz.me.compat.neoforge.api.screenhandler.v1;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.network.IContainerFactory;

public class ExtendedScreenHandlerType<T extends AbstractContainerMenu, D> extends MenuType<T> {
    public ExtendedScreenHandlerType(ExtendedFactory<T, D> factory, StreamCodec<? super RegistryFriendlyByteBuf, D> codec) {
        super(new IContainerFactory<>() {
            @Override
            public T create(int containerId, Inventory inventory, RegistryFriendlyByteBuf data) {
                return factory.create(containerId, inventory, codec.decode(data));
            }

            @Override
            public T create(int containerId, Inventory inventory) {
                return factory.create(containerId, inventory, null);
            }
        }, FeatureFlags.VANILLA_SET);
    }

    @FunctionalInterface
    public interface ExtendedFactory<T extends AbstractContainerMenu, D> {
        T create(int syncId, Inventory inventory, D data);
    }
}
