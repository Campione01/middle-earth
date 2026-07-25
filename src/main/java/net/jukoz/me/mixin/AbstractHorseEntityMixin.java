package net.jukoz.me.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.jukoz.me.item.ModFoodItems;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AbstractHorse.class)
public class AbstractHorseEntityMixin {

    @WrapOperation(method = "handleEating", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z", ordinal = 3))
    private boolean receiveFoodLettuce(ItemStack instance, Item item, Operation<Boolean> original) {
        return original.call(instance, item) || instance.is(ModFoodItems.LETTUCE);
    }

    @WrapOperation(method = "handleEating", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z", ordinal = 5))
    private boolean receiveFoodHorseFeed(ItemStack instance, Item item, Operation<Boolean> original) {
        return original.call(instance, item) || instance.is(ModFoodItems.SACK_OF_HORSEFEED);
    }
}
