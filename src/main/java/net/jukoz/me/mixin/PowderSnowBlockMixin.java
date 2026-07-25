package net.jukoz.me.mixin;

import net.jukoz.me.MiddleEarth;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.PowderSnowBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PowderSnowBlock.class)
public class PowderSnowBlockMixin {
    @Unique
    private static final TagKey<Item> ME_POWDER_SNOW_WALK_ON = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "powder_snow_walk_on"));

    @Inject(method = "canEntityWalkOnPowderSnow", at = @At("RETURN"), cancellable = true)
    private static void canWalkOnPowderSnowTag(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        if (cir.getReturnValue() || !(entity instanceof LivingEntity livingEntity)) {
            return;
        }
        ItemStack feetStack = livingEntity.getItemBySlot(EquipmentSlot.FEET);
        if (feetStack.is(ME_POWDER_SNOW_WALK_ON)) {
            cir.setReturnValue(true);
        }
    }
}
