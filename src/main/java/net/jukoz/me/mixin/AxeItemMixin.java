package net.jukoz.me.mixin;

import net.jukoz.me.item.items.shields.CustomShieldItem;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AxeItem.class)
public class AxeItemMixin {
    @Redirect(
            method = "playerHasShieldUseIntent(Lnet/minecraft/world/item/context/UseOnContext;)Z",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z"
            )
    )
    private static boolean shield_api$shouldCancelStripAttempt(ItemStack instance, Item item) {
        return instance.is(Items.SHIELD) || instance.getItem() instanceof CustomShieldItem;
    }
}
