package net.jukoz.me.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.jukoz.me.item.ModDataComponentTypes;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import java.util.function.Consumer;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

    @Shadow public abstract <T extends TooltipProvider> void addToTooltip(DataComponentType<T> componentType, Item.TooltipContext context, Consumer<Component> textConsumer, TooltipFlag type);

    @Shadow public abstract Item getItem();

    @WrapOperation(method = "getTooltipLines", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/world/item/ItemStack;addToTooltip(Lnet/minecraft/core/component/DataComponentType;Lnet/minecraft/world/item/Item$TooltipContext;Ljava/util/function/Consumer;Lnet/minecraft/world/item/TooltipFlag;)V",
            ordinal = 0))

    private void addCustomTooltips(ItemStack instance, DataComponentType componentType, Item.TooltipContext context, Consumer<Component> textConsumer, TooltipFlag type, Operation<Void> original, @Local Consumer<Component> consumer){
        this.addToTooltip(ModDataComponentTypes.TEMPERATURE_DATA, context, consumer, type);
    }
}
