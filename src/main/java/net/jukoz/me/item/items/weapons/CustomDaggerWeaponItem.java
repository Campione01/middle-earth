package net.jukoz.me.item.items.weapons;

import net.jukoz.me.MiddleEarth;
import net.jukoz.me.item.utils.MEEquipmentTooltip;
import net.jukoz.me.item.utils.ModWeaponTypes;
import net.jukoz.me.utils.ModFactions;
import net.jukoz.me.utils.ModSubFactions;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import org.joml.Vector2f;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

public class CustomDaggerWeaponItem extends ReachWeaponItem implements MEEquipmentTooltip {
    public static final ResourceLocation ENTITY_INTERACTION_RANGE_MODIFIER_ID = ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "entity_interaction_range");

    public CustomDaggerWeaponItem(Tier toolMaterial) {
        super(toolMaterial, ModWeaponTypes.DAGGER);
    }

    public CustomDaggerWeaponItem(Tier toolMaterial, ModFactions faction) {
        super(toolMaterial, faction, ModWeaponTypes.DAGGER);
    }

    public CustomDaggerWeaponItem(Tier toolMaterial, ModSubFactions subFaction) {
        super(toolMaterial, subFaction, ModWeaponTypes.DAGGER);
    }

    @Override
    public List<Component> getAdditionalShiftLines(ItemStack stack) {
        List<Component> list = new ArrayList<>(List.of());

        list.add(Component.translatable("tooltip." + MiddleEarth.MOD_ID + ".weapon_type").append(Component.translatable("tooltip." + MiddleEarth.MOD_ID + "." + this.type.name)));
        list.add(Component.translatable("tooltip." + MiddleEarth.MOD_ID + ".backstab"));

        return list;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag type) {
        appendBaseTooltip(tooltip, stack, this.faction, this.subFaction);
    }

    public static boolean canBackStab(Entity target, Entity attacker) {
        Vector2f direction = new Vector2f((float) target.getLookAngle().x, (float) target.getLookAngle().z).normalize();
        Vector3f attackerPosDifference = target.position().add(attacker.position().scale(-1)).toVector3f();
        Vector2f attackerDirection = new Vector2f(attackerPosDifference.x, attackerPosDifference.z).normalize();
        float dotProduct = direction.dot(attackerDirection);
        return dotProduct > 0.2f;
    }
}
