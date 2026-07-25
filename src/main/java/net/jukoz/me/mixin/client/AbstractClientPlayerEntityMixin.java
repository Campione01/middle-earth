package net.jukoz.me.mixin.client;

import net.jukoz.me.compat.neoforge.dist.EnvType;
import net.jukoz.me.compat.neoforge.dist.Environment;
import net.jukoz.me.statusEffects.ModStatusEffects;
import net.jukoz.me.utils.HallucinationData;
import net.jukoz.me.utils.IEntityDataSaver;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin(AbstractClientPlayer.class)
public abstract class AbstractClientPlayerEntityMixin {

    @Inject(method = "getFieldOfViewModifier()F", at =@At("TAIL"), cancellable = true)
    private void injected(CallbackInfoReturnable<Float> cir) {
    Player player = Minecraft.getInstance().player;
        assert player != null;
        if(player.hasEffect(ModStatusEffects.HALLUCINATION)){
            float intensity = (float) HallucinationData.readHallucination((IEntityDataSaver) player) / 100f;
            cir.setReturnValue(cir.getReturnValue() * (1 - intensity/4));
        }
    }

    @Redirect(method = "getFieldOfViewModifier", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z"))
    private boolean init(ItemStack itemStack, Item item) {
        return itemStack.getItem() instanceof BowItem;
    }
}
