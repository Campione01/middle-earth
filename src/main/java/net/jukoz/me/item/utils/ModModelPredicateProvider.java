package net.jukoz.me.item.utils;

import net.jukoz.me.datageneration.content.models.SimpleSpearModel;
import net.jukoz.me.item.ModToolItems;
import net.jukoz.me.item.ModWeaponItems;
import net.jukoz.me.item.items.weapons.artefacts.ArtefactCustomGlowingDaggerWeaponItem;
import net.jukoz.me.item.items.weapons.artefacts.ArtefactCustomGlowingLongswordWeaponItem;
import net.jukoz.me.item.items.weapons.artefacts.ArtefactCustomLongswordWeaponItem;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Item;
import net.jukoz.me.item.items.PipeItem;

public class ModModelPredicateProvider {

    public static void registerAllPredicates(){
        registerShieldModel();
        registerSpearModel();
        registerBowModel();
        registerArtefactModels();
        registerGlowingArtefactModels();
        registerPipeModels();
    }

    private static void registerBowModel() {
        registerBow(ModWeaponItems.GONDORIAN_BOW);
        registerLongbow(ModWeaponItems.GONDORIAN_LONGBOW);
        registerLongbow(ModWeaponItems.GONDORIAN_NOBLE_LONGBOW);

        registerBow(ModWeaponItems.ROHIRRIC_BOW);
        registerBow(ModWeaponItems.ROHIRRIC_NOBLE_BOW);
        registerLongbow(ModWeaponItems.ROHIRRIC_LONGBOW);

        registerBow(ModWeaponItems.LORIEN_BOW);
        registerLongbow(ModWeaponItems.LORIEN_LONGBOW);
        registerLongbow(ModWeaponItems.LORIEN_NOBLE_LONGBOW);

        registerLongbow(ModWeaponItems.DALISH_LONGBOW);
        registerLongbow(ModWeaponItems.DALISH_HEYDAY_LONGBOW);
        registerLongbow(ModWeaponItems.DALISH_NOBLE_LONGBOW);

        registerBow(ModWeaponItems.EREBOR_BOW);
        registerBow(ModWeaponItems.EREBOR_NOBLE_BOW);
        registerCrossbow(ModWeaponItems.EREBOR_CROSSBOW);
        registerCrossbow(ModWeaponItems.EREBOR_NOBLE_CROSSBOW);

        registerBow(ModWeaponItems.ORCISH_BOW);

        registerBow(ModWeaponItems.MORDOR_BOW);
        registerLongbow(ModWeaponItems.MORDOR_ELITE_LONGBOW);

        registerBow(ModWeaponItems.URUK_HAI_BOW);
        registerCrossbow(ModWeaponItems.URUK_HAI_CROSSBOW);

        registerBow(ModWeaponItems.GUNDABAD_BOW);
        registerCrossbow(ModWeaponItems.GUNDABAD_CROSSBOW);
    }
    private static void registerPipeModels() {
        registerPipeModel(ModToolItems.CLAY_PIPE);
        registerPipeModel(ModToolItems.PIPE);
        registerPipeModel(ModToolItems.RIVERBEND_PIPE);
        registerPipeModel(ModToolItems.BRIMMINGBEND_PIPE);
        registerPipeModel(ModToolItems.LONGBOTTOM_PIPE);
    }

    private static void registerArtefactModels() {
        registerArtefact(ModWeaponItems.DAGAMARTH);
        registerArtefact(ModWeaponItems.HERUGRIM);
        registerArtefact(ModWeaponItems.NAZGUL_SWORD);

        registerArtefact(ModWeaponItems.HAMMER_OF_HELM_HAMMERHAND);
        registerArtefact(ModWeaponItems.MACE_OF_SAURON);

        registerArtefact(ModWeaponItems.ANGUIREL);
        registerArtefact(ModWeaponItems.LONG_FORGOTTEN_LONGSWORD);
        registerArtefact(ModWeaponItems.LONGSWORD_OF_ELDER_KINGS);
        registerArtefact(ModWeaponItems.NARSIL);
        registerArtefact(ModWeaponItems.NOLDORIN_LONGSWORD);

        registerArtefact(ModWeaponItems.BARROW_BLADE);
        registerArtefact(ModWeaponItems.MORGUL_KNIFE);
    }

    private static void registerGlowingArtefactModels() {
        registerGlowingArtefact(ModWeaponItems.GLAMDRING);
        registerGlowingArtefact(ModWeaponItems.ORCRIST);
        registerGlowingArtefact(ModWeaponItems.STING);
    }

    private static void registerBow(Item bow) {
        ItemProperties.register(bow, ResourceLocation.parse("pull"),
                (stack, world, entity, seed) -> {
                   if(entity == null) return 0.0f;
                   else if (entity.getUseItem() != stack) return 0.0f;
                   return (float)(stack.getUseDuration(entity) - entity.getUseItemRemainingTicks()) / 20.0f;
                });

        ItemProperties.register(bow, ResourceLocation.parse("pulling"),
                (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0f : 0.0f);
    }

    private static void registerLongbow(Item bow) {
        ItemProperties.register(bow, ResourceLocation.parse("pull"),
                (stack, world, entity, seed) -> {
                    if(entity == null) return 0.0f;
                    else if (entity.getUseItem() != stack) return 0.0f;
                    return (float)(stack.getUseDuration(entity) - entity.getUseItemRemainingTicks()) / 30.0f;
                });

        ItemProperties.register(bow, ResourceLocation.parse("pulling"),
                (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0f : 0.0f);
    }

    private static void registerCrossbow(Item crossbow) {
        ItemProperties.register(crossbow, ResourceLocation.parse("pull"),
                (stack, world, entity, seed) -> {
                    if (entity == null) {
                        return 0.0F;
                    } else {
                        return CrossbowItem.isCharged(stack) ? 0.0F : (float)(stack.getUseDuration(entity) - entity.getUseItemRemainingTicks()) / (float)CrossbowItem.getChargeDuration(stack, entity);
                    }
                });

        ItemProperties.register(crossbow, ResourceLocation.parse("pulling"),
                (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack && !CrossbowItem.isCharged(stack) ? 1.0F : 0.0F);

        ItemProperties.register(crossbow, ResourceLocation.parse("charged"),
                (stack, world, entity, seed) -> CrossbowItem.isCharged(stack) ? 1.0F : 0.0F);
    }

    private static void registerShieldModel() {
        ModWeaponItems.shields.forEach(ModModelPredicateProvider::registerShield);
    }

    private static void registerSpearModel() {
        SimpleSpearModel.items.forEach(ModModelPredicateProvider::registerSpear);
    }

    private static void registerShield(Item shield) {
        ItemProperties.register(shield, ResourceLocation.parse("blocking"),
                (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F);
    }

    private static void registerSpear(Item spear) {
        ItemProperties.register(spear, ResourceLocation.parse("holding"),
                (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F);
    }

    private static void registerArtefact(Item artefact) {
        ItemProperties.register(artefact, ResourceLocation.parse("broken"),
                (stack, world, entity, seed) -> ArtefactCustomLongswordWeaponItem.isUsable(stack) ? 0.0F : 1.0F);
    }

    private static void registerGlowingArtefact(Item artefact) {
        ItemProperties.register(artefact, ResourceLocation.parse("broken"),
                (stack, world, entity, seed) -> ArtefactCustomLongswordWeaponItem.isUsable(stack) ? 0.0F : 1.0F);

        if (artefact instanceof ArtefactCustomGlowingLongswordWeaponItem item){
            ItemProperties.register(item, ResourceLocation.parse("glowing"),
                    (stack, world, entity, seed) -> ArtefactCustomGlowingLongswordWeaponItem.shouldBeGlowing(world, entity) ? 1.0F : 0.0F);
        }
        if (artefact instanceof ArtefactCustomGlowingDaggerWeaponItem item){
            ItemProperties.register(item, ResourceLocation.parse("glowing"),
                    (stack, world, entity, seed) -> ArtefactCustomGlowingDaggerWeaponItem.shouldBeGlowing(world, entity) ? 1.0F : 0.0F);
        }
    }

    private static void registerPipeModel(Item pipe){
        ItemProperties.register(pipe, ResourceLocation.parse("smoking"),
                (stack, world, entity, seed) -> {
                    if (stack.getItem() instanceof PipeItem pipeItem) {
                        return pipeItem.isSmoking() ? 1.0F : 0.0F;
                    }
                    return 0.0F;
                });
    }
}
