package net.jukoz.me.mixin;

import net.jukoz.me.MiddleEarth;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RepairItemRecipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RepairItemRecipe.class)
public class RepairItemRecipeMixin {

    @Inject(at = @At("HEAD"), method = "canCombine", cancellable = true)
    private static void canCombine(ItemStack first, ItemStack second, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(second.is(first.getItem())
                && first.getCount() == 1
                && second.getCount() == 1

                && first.has(DataComponents.MAX_DAMAGE)
                && second.has(DataComponents.MAX_DAMAGE)

                && first.has(DataComponents.DAMAGE)
                && second.has(DataComponents.DAMAGE)

                && !first.is(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "anvil_items")))
                && !second.is(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "anvil_items")))
                );
    }
}
