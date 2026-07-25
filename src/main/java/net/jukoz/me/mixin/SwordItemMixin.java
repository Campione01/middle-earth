package net.jukoz.me.mixin;

import net.jukoz.me.MiddleEarth;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.component.Tool;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(SwordItem.class)
public class SwordItemMixin {

    @Inject(method = "createToolProperties", at = @At(value = "HEAD"), cancellable = true)
    private static void createToolProperties(CallbackInfoReturnable<Tool> cir) {
        cir.setReturnValue(new Tool(List.of(Tool.Rule.minesAndDrops(TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "cobwebs")), 15.0f),
                Tool.Rule.overrideSpeed(BlockTags.SWORD_EFFICIENT, 1.5f)), 1.0f, 2));
    }
}
