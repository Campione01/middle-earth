package net.jukoz.me.mixin;

import net.jukoz.me.MiddleEarth;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(ShearsItem.class)
public class ShearItemMixin {

    @Inject(method = "createToolProperties", at = @At(value = "HEAD"), cancellable = true)
    private static void createToolProperties(CallbackInfoReturnable<Tool> cir) {
        cir.setReturnValue(new Tool(List.of(Tool.Rule.minesAndDrops(TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "cobwebs")), 15.0F), Tool.Rule.overrideSpeed(BlockTags.LEAVES, 15.0F), Tool.Rule.overrideSpeed(BlockTags.WOOL, 5.0F), Tool.Rule.overrideSpeed(List.of(Blocks.VINE, Blocks.GLOW_LICHEN), 2.0F)), 1.0F, 1));
    }

    @Inject(method = "mineBlock", at = @At(value = "HEAD"), cancellable = true)
    public void isSuitableFor(ItemStack stack, Level world, BlockState state, BlockPos pos, LivingEntity miner, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(state.is(BlockTags.LEAVES) || state.is(TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "cobwebs"))) || state.is(Blocks.SHORT_GRASS) || state.is(Blocks.FERN) || state.is(Blocks.DEAD_BUSH) || state.is(Blocks.HANGING_ROOTS) || state.is(Blocks.VINE) || state.is(Blocks.TRIPWIRE) || state.is(BlockTags.WOOL));
    }
}
