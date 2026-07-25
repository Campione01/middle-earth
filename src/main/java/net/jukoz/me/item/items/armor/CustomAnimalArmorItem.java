package net.jukoz.me.item.items.armor;

import net.jukoz.me.MiddleEarth;
import net.jukoz.me.item.ModDataComponentTypes;
import net.jukoz.me.item.dataComponents.CapeDataComponent;
import net.jukoz.me.item.dataComponents.CustomDyeableDataComponent;
import net.jukoz.me.item.dataComponents.MountArmorAddonComponent;
import net.jukoz.me.item.utils.MEEquipmentTooltip;
import net.jukoz.me.item.utils.armor.ExtendedArmorMaterial;
import net.jukoz.me.utils.ModFactions;
import net.jukoz.me.utils.ModSubFactions;
import net.minecraft.ChatFormatting;
import net.minecraft.world.item.*;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class CustomAnimalArmorItem extends ArmorItem implements MEEquipmentTooltip {
    public ModFactions faction;
    public ModSubFactions subFaction;
    private final ResourceLocation entityTexture;
    @Nullable
    private final ResourceLocation overlayTexture;
    private final net.jukoz.me.item.items.armor.CustomAnimalArmorItem.Type type;

    private ExtendedArmorMaterial material;

    public CustomAnimalArmorItem(ExtendedArmorMaterial material, String suffix, net.jukoz.me.item.items.armor.CustomAnimalArmorItem.Type type, boolean hasOverlay, Item.Properties settings, ModFactions faction) {
        super(material.material(), ArmorItem.Type.BODY, settings);
        this.material = material;
        this.type = type;
        ResourceLocation identifier = ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, type.textureIdFunction.apply(material.material().unwrapKey().orElseThrow().location()).getPath());
        identifier = suffix != null ? identifier.withSuffix(suffix) : identifier;
        this.entityTexture = identifier.withSuffix(".png");
        this.overlayTexture = hasOverlay ? identifier.withSuffix("_overlay.png") : null;

        this.faction = faction;
        this.subFaction = null;
    }

    public CustomAnimalArmorItem(ExtendedArmorMaterial material, String suffix, net.jukoz.me.item.items.armor.CustomAnimalArmorItem.Type type, boolean hasOverlay, Item.Properties settings, ModSubFactions subFaction) {
        super(material.material(), ArmorItem.Type.BODY, settings);
        this.material = material;
        this.type = type;
        ResourceLocation identifier = ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, type.textureIdFunction.apply(material.material().unwrapKey().orElseThrow().location()).getPath());
        identifier = suffix != null ? identifier.withSuffix(suffix) : identifier;
        this.entityTexture = identifier.withSuffix(".png");
        this.overlayTexture = hasOverlay ? identifier.withSuffix("_overlay.png") : null;

        this.subFaction = subFaction;
        this.faction = subFaction.getParent();
    }

    @Override
    public List<Component> getAdditionalShiftLines(ItemStack stack) {
        List<Component> list = new ArrayList<>(List.of());
        list.add(Component.translatable("tooltip." + MiddleEarth.MOD_ID + ".tier_" + this.material.tier().toString().toLowerCase()));

        return list;
    }

    @Override
    public List<Component> getAdditionalAltLines(ItemStack stack) {
        List<Component> list = new ArrayList<>(List.of());
        MountArmorAddonComponent mountArmorAddonComponent = stack.get(ModDataComponentTypes.MOUNT_ARMOR_DATA);
        CustomDyeableDataComponent dyeDataComponent = stack.get(ModDataComponentTypes.DYE_DATA);

        if(dyeDataComponent != null){
            list.add(Component.translatable("tooltip." + MiddleEarth.MOD_ID + ".color").append(": " + String.format("#%06X", (0xFFFFFF & CustomDyeableDataComponent.getColor(stack, CustomDyeableDataComponent.DEFAULT_COLOR)))).withStyle(ChatFormatting.GRAY));
        }
        if(mountArmorAddonComponent != null && mountArmorAddonComponent.topArmorAddon()) {
            list.add(Component.translatable("tooltip." + MiddleEarth.MOD_ID + ".mount_armor_addon_top"));
        }
        if(mountArmorAddonComponent != null && mountArmorAddonComponent.sideArmorAddon()) {
            list.add(Component.translatable("tooltip." + MiddleEarth.MOD_ID + ".mount_armor_addon_side"));
        }

        return list;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag type) {
        appendBaseTooltip(tooltip, stack, this.faction, this.subFaction);
        super.appendHoverText(stack, context, tooltip, type);
    }

    public ResourceLocation getEntityTexture() {
        return this.entityTexture;
    }

    @Nullable
    public ResourceLocation getOverlayTexture() {
        return this.overlayTexture;
    }

    public net.jukoz.me.item.items.armor.CustomAnimalArmorItem.Type getArmorType() {
        return this.type;
    }

    @Override
    public SoundEvent getBreakingSound() {
        return this.type.breakSound;
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }

    public static enum Type {
        WARG((id) -> {
            return id.withPath((path) -> {
                return "textures/entities/warg/feature/warg_armor_" + path;
            });
        }, SoundEvents.ITEM_BREAK),
        BROADHOOF_GOAT((id) -> {
            return id.withPath((path) -> {
                return "textures/entities/broadhoof_goat/feature/broadhoof_goat_armor_" + path;
            });
        }, SoundEvents.ITEM_BREAK);
        final Function<ResourceLocation, ResourceLocation> textureIdFunction;
        final SoundEvent breakSound;

        private Type(Function<ResourceLocation, ResourceLocation> textureIdFunction, SoundEvent breakSound) {
            this.textureIdFunction = textureIdFunction;
            this.breakSound = breakSound;
        }
    }
}
