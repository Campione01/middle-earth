package net.jukoz.me.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.jukoz.me.item.items.shields.CustomShieldItem;
import net.jukoz.me.item.items.shields.CustomSiegeShieldItem;
import net.jukoz.me.item.items.weapons.CustomDaggerWeaponItem;
import net.jukoz.me.item.items.weapons.ReachWeaponItem;
import net.jukoz.me.item.items.weapons.ranged.CustomLongbowWeaponItem;
import net.jukoz.me.utils.IEntityDataSaver;
import net.jukoz.me.utils.PlayerActionHandlingContext;
import net.jukoz.me.utils.PlayerMovementData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import static net.minecraft.world.entity.EquipmentSlot.OFFHAND;

@Mixin(Player.class)
public abstract class PlayerEntityMixin extends LivingEntity {

    @Shadow public abstract ItemCooldowns getCooldowns();

    @Shadow public abstract Inventory getInventory();

    @Shadow protected abstract void blockUsingShield(LivingEntity attacker);

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, Level world) {
        super(entityType, world);
    }

    @Inject(at = @At(value = "HEAD"), method = "disableShield()V", locals = LocalCapture.CAPTURE_FAILHARD, cancellable = true)
    private void disableShieldHead(CallbackInfo ci) {
        Item activeItem = useItem.getItem();

        if (activeItem instanceof ShieldItem shield) {
            float f = 0.25F; //+ (float) EnchantmentHelper.getEfficiency(this) * 0.05F; //TODO Test this
            if (isSprinting()) {
                f += 0.75F;
            }
            if (this.getRandom().nextFloat() < f) {
                this.getCooldowns().addCooldown(shield, 100);
                this.stopUsingItem();
                this.level().broadcastEntityEvent(this, (byte) 30);
                ci.cancel();
            }
        }
    }

    @Inject(method = "disableShield", at = @At("HEAD"))
    public void shield_api$disableShield(CallbackInfo ci) {
        for (CustomShieldItem customShieldItem : CustomShieldItem.instances) {
            this.getCooldowns().addCooldown(customShieldItem, 100);
        }
    }

    @Inject(method = "getItemBySlot", at = @At("HEAD"), cancellable = true)
    public void getEquippedStack(EquipmentSlot slot, CallbackInfoReturnable<ItemStack> cir) {
        if (slot != OFFHAND || ((Object) this instanceof ServerPlayer && PlayerActionHandlingContext.isActive())) {
            return;
        }

        if (isTwoHandedItem(this.getInventory().getSelected())
                || isTwoHandedItem(this.getInventory().getItem(Inventory.SLOT_OFFHAND))) {
            cir.setReturnValue(ItemStack.EMPTY);
            cir.cancel();
        }
    }

    private static boolean isTwoHandedItem(ItemStack stack) {
        if (stack == null) {
            return false;
        }

        return stack.getItem() instanceof ReachWeaponItem weaponItem && weaponItem.type.twoHanded
                || stack.getItem() instanceof CustomSiegeShieldItem
                || stack.getItem() instanceof CustomLongbowWeaponItem;
    }

    @Inject(method = "tick", at = @At("HEAD"))
    public void tick(CallbackInfo ci) {
        PlayerMovementData.addAFKTime((IEntityDataSaver) this,1);
    }

    @Inject(method = "travel", at = @At("HEAD"))
    public void travel(CallbackInfo ci, @Local Vec3 movementInput) {
        if(movementInput.length() > 0.01f) {
            PlayerMovementData.resetAFK((IEntityDataSaver) this);
        }
    }

    @Inject(method = "attack", at = @At("HEAD"))
    public void attack(Entity target, CallbackInfo ci) {
        PlayerMovementData.resetAFK((IEntityDataSaver) this);
    }

    @ModifyVariable(method = "attack", ordinal = 3, at = @At(value = "INVOKE", shift = At.Shift.BEFORE,
            target = "Lnet/minecraft/world/entity/Entity;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z", ordinal = 0))
    public float attackBackStab(float value, Entity target) {
        ItemStack mainStack = this.getItemInHand(InteractionHand.MAIN_HAND);
        if(mainStack.getItem() instanceof CustomDaggerWeaponItem) {
            if(CustomDaggerWeaponItem.canBackStab(target, this)) {
                return value * 1.5f;
            }
        }
        return value;
    }

    @Inject(method = "resetAttackStrengthTicker", at = @At("HEAD"))
    public void resetLastAttackedTicks(CallbackInfo ci) {
        PlayerMovementData.resetAFK((IEntityDataSaver) this);
    }

}
