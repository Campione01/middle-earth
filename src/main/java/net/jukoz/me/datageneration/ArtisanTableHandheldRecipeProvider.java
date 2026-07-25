package net.jukoz.me.datageneration;

import net.jukoz.me.compat.neoforge.api.datagen.v1.provider.NeoForgeRecipeProvider;
import net.jukoz.me.compat.neoforge.impl.recipe.ingredient.builtin.ComponentsIngredient;
import net.jukoz.me.MiddleEarth;
import net.jukoz.me.block.special.forge.MetalTypes;
import net.jukoz.me.datageneration.custom.ArtisanTableRecipeJsonBuilder;
import net.jukoz.me.item.ModResourceItems;
import net.jukoz.me.item.ModToolItems;
import net.jukoz.me.item.ModWeaponItems;
import net.jukoz.me.item.utils.ModSmithingTrimPatterns;
import net.jukoz.me.resources.datas.Disposition;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.armortrim.ArmorTrim;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimPattern;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class ArtisanTableHandheldRecipeProvider extends RecipeProvider {

    private final CompletableFuture<HolderLookup.Provider> registryLookup;

    public ArtisanTableHandheldRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);

        this.registryLookup = registryLookupFuture;
    }

    public HolderLookup.RegistryLookup<TrimMaterial> getArmorTrimMaterialsRegistry(){
        HolderLookup.RegistryLookup<TrimMaterial> armorTrimMaterialsRegistry;

        try {
            armorTrimMaterialsRegistry = registryLookup.get().lookupOrThrow(Registries.TRIM_MATERIAL);
        } catch (Exception ignored) {
            throw new IllegalStateException("Data generation without registries failed!");
        }
        return armorTrimMaterialsRegistry;
    }

    public HolderLookup.RegistryLookup<TrimPattern> getArmorTrimPatternsRegistry(){
        HolderLookup.RegistryLookup<TrimPattern> armorTrimPatternsRegistry;

        try {
            armorTrimPatternsRegistry = registryLookup.get().lookupOrThrow(Registries.TRIM_PATTERN);
        } catch (Exception ignored) {
            throw new IllegalStateException("Data generation without registries failed!");
        }
        return armorTrimPatternsRegistry;
    }

    public Holder<TrimPattern> getPattern(){
        return getArmorTrimPatternsRegistry().getOrThrow(ModSmithingTrimPatterns.SMITHING_PART);
    }
    
    public ResourceLocation getMetalIdentifier(MetalTypes metal){
        if (metal.isVanilla()){
            return ResourceLocation.parse(metal.getName());
        } else {
            return ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, metal.getName());
        }
    }

    @Override
    public void buildRecipes(RecipeOutput exporter) {

        //region WEAPONS
        createArtisanTableSwordRecipe(exporter, MetalTypes.IRON, Items.IRON_SWORD.getDefaultInstance(), false, Disposition.NEUTRAL);
        createArtisanTableDaggerRecipe(exporter, MetalTypes.IRON, ModWeaponItems.IRON_DAGGER.getDefaultInstance(), false, Disposition.NEUTRAL);
        createArtisanTableSpearRecipe(exporter, MetalTypes.IRON, ModWeaponItems.IRON_SPEAR.getDefaultInstance(), false, Disposition.NEUTRAL);

        createArtisanTableSwordRecipe(exporter, MetalTypes.GOLD, Items.GOLDEN_SWORD.getDefaultInstance(), false, Disposition.NEUTRAL);
        createArtisanTableDaggerRecipe(exporter, MetalTypes.GOLD, ModWeaponItems.GOLDEN_DAGGER.getDefaultInstance(), false, Disposition.NEUTRAL);
        createArtisanTableSpearRecipe(exporter, MetalTypes.GOLD, ModWeaponItems.GOLDEN_SPEAR.getDefaultInstance(), false, Disposition.NEUTRAL);

        createArtisanTableSwordRecipe(exporter, MetalTypes.NETHERITE, Items.NETHERITE_SWORD.getDefaultInstance(), false, Disposition.NEUTRAL);
        createArtisanTableDaggerRecipe(exporter, MetalTypes.NETHERITE, ModWeaponItems.NETHERITE_DAGGER.getDefaultInstance(), false, Disposition.NEUTRAL);
        createArtisanTableSpearRecipe(exporter, MetalTypes.NETHERITE, ModWeaponItems.NETHERITE_SPEAR.getDefaultInstance(), false, Disposition.NEUTRAL);

        createArtisanTableSwordRecipe(exporter, MetalTypes.BRONZE, ModWeaponItems.BRONZE_SWORD.getDefaultInstance(), false, Disposition.NEUTRAL);
        createArtisanTableDaggerRecipe(exporter, MetalTypes.BRONZE, ModWeaponItems.BRONZE_DAGGER.getDefaultInstance(), false, Disposition.NEUTRAL);
        createArtisanTableSpearRecipe(exporter, MetalTypes.BRONZE, ModWeaponItems.BRONZE_SPEAR.getDefaultInstance(), false, Disposition.NEUTRAL);

        createArtisanTableSwordRecipe(exporter, MetalTypes.CRUDE, ModWeaponItems.CRUDE_FALCHION.getDefaultInstance(), false, Disposition.EVIL);
        createArtisanTableDaggerRecipe(exporter, MetalTypes.CRUDE, ModWeaponItems.CRUDE_DAGGER.getDefaultInstance(), false, Disposition.EVIL);
        createArtisanTableSpearRecipe(exporter, MetalTypes.CRUDE, ModWeaponItems.CRUDE_SPEAR.getDefaultInstance(), false, Disposition.EVIL);
        createArtisanTableLongswordRecipe(exporter, MetalTypes.CRUDE, ModWeaponItems.CRUDE_LONGBLADE.getDefaultInstance(), false, Disposition.EVIL);

        createArtisanTableSwordRecipe(exporter, MetalTypes.STEEL, ModWeaponItems.GONDORIAN_SWORD.getDefaultInstance(), false, Disposition.GOOD);
        createArtisanTableSwordRecipe(exporter, MetalTypes.STEEL, ModWeaponItems.GONDORIAN_NOBLE_SWORD.getDefaultInstance(), true, Disposition.GOOD);
        createArtisanTableSwordRecipe(exporter, MetalTypes.STEEL, ModWeaponItems.ROHIRRIC_SWORD.getDefaultInstance(), false, Disposition.GOOD);
        createArtisanTableSwordRecipe(exporter, MetalTypes.STEEL, ModWeaponItems.ROHIRRIC_NOBLE_SWORD.getDefaultInstance(), true, Disposition.GOOD);
        createArtisanTableSwordRecipe(exporter, MetalTypes.STEEL, ModWeaponItems.DALISH_SWORD.getDefaultInstance(), false, Disposition.GOOD);
        createArtisanTableSwordRecipe(exporter, MetalTypes.STEEL, ModWeaponItems.DALISH_NOBLE_SWORD.getDefaultInstance(), true, Disposition.GOOD);
        createArtisanTableSwordRecipe(exporter, MetalTypes.STEEL, ModWeaponItems.DALISH_HEYDAY_SWORD.getDefaultInstance(), true, Disposition.GOOD);
        createArtisanTableSwordRecipe(exporter, MetalTypes.STEEL, ModWeaponItems.STEEL_SWORD.getDefaultInstance(), false, Disposition.NEUTRAL);
        createArtisanTableSwordRecipe(exporter, MetalTypes.STEEL, ModWeaponItems.BLACK_NUMENOREAN_SWORD.getDefaultInstance(), true, Disposition.EVIL);

        createArtisanTableDaggerRecipe(exporter, MetalTypes.STEEL, ModWeaponItems.GONDORIAN_DAGGER.getDefaultInstance(), false, Disposition.GOOD);
        createArtisanTableDaggerRecipe(exporter, MetalTypes.STEEL, ModWeaponItems.GONDORIAN_NOBLE_DAGGER.getDefaultInstance(), true, Disposition.GOOD);
        createArtisanTableDaggerRecipe(exporter, MetalTypes.STEEL, ModWeaponItems.ROHIRRIC_DAGGER.getDefaultInstance(), false, Disposition.GOOD);
        createArtisanTableDaggerRecipe(exporter, MetalTypes.STEEL, ModWeaponItems.ROHIRRIC_NOBLE_DAGGER.getDefaultInstance(), true, Disposition.GOOD);
        createArtisanTableDaggerRecipe(exporter, MetalTypes.STEEL, ModWeaponItems.DALISH_DAGGER.getDefaultInstance(), false, Disposition.GOOD);
        createArtisanTableDaggerRecipe(exporter, MetalTypes.STEEL, ModWeaponItems.DALISH_HEYDAY_DAGGER.getDefaultInstance(), true, Disposition.GOOD);
        createArtisanTableDaggerRecipe(exporter, MetalTypes.STEEL, ModWeaponItems.DALISH_NOBLE_DAGGER.getDefaultInstance(), true, Disposition.GOOD);
        createArtisanTableDaggerRecipe(exporter, MetalTypes.STEEL, ModWeaponItems.BLACK_NUMENOREAN_DAGGER.getDefaultInstance(), true, Disposition.EVIL);

        createArtisanTableLongswordRecipe(exporter, MetalTypes.STEEL, ModWeaponItems.GONDORIAN_LONGSWORD.getDefaultInstance(), false, Disposition.GOOD);
        createArtisanTableLongswordRecipe(exporter, MetalTypes.STEEL, ModWeaponItems.GONDORIAN_NOBLE_LONGSWORD.getDefaultInstance(), true, Disposition.GOOD);
        createArtisanTableLongswordRecipe(exporter, MetalTypes.STEEL, ModWeaponItems.ROHIRRIC_LONGSWORD.getDefaultInstance(), false, Disposition.GOOD);
        createArtisanTableLongswordRecipe(exporter, MetalTypes.STEEL, ModWeaponItems.ROHIRRIC_NOBLE_LONGSWORD.getDefaultInstance(), true, Disposition.GOOD);
        createArtisanTableLongswordRecipe(exporter, MetalTypes.STEEL, ModWeaponItems.DALISH_LONGSWORD.getDefaultInstance(), false, Disposition.GOOD);
        createArtisanTableLongswordRecipe(exporter, MetalTypes.STEEL, ModWeaponItems.DALISH_HEYDAY_LONGSWORD.getDefaultInstance(), true, Disposition.GOOD);
        createArtisanTableLongswordRecipe(exporter, MetalTypes.STEEL, ModWeaponItems.DALISH_HEYDAY_SCIMITAR.getDefaultInstance(), true, Disposition.GOOD);
        createArtisanTableLongswordRecipe(exporter, MetalTypes.STEEL, ModWeaponItems.DALISH_NOBLE_LONGSWORD.getDefaultInstance(), true, Disposition.GOOD);
        createArtisanTableLongswordRecipe(exporter, MetalTypes.STEEL, ModWeaponItems.BLACK_NUMENOREAN_LONGSWORD.getDefaultInstance(), true, Disposition.EVIL);

        createArtisanTableAxeRecipe(exporter, MetalTypes.STEEL, ModWeaponItems.GONDORIAN_AXE.getDefaultInstance(), false, Optional.empty(), Disposition.GOOD);
        createArtisanTableAxeRecipe(exporter, MetalTypes.STEEL, ModWeaponItems.GONDORIAN_NOBLE_AXE.getDefaultInstance(), true, Optional.empty(), Disposition.GOOD);
        createArtisanTableAxeRecipe(exporter, MetalTypes.STEEL, ModWeaponItems.ROHIRRIC_AXE.getDefaultInstance(), false, Optional.empty(), Disposition.GOOD);
        createArtisanTableAxeRecipe(exporter, MetalTypes.STEEL, ModWeaponItems.ROHIRRIC_NOBLE_AXE.getDefaultInstance(), true, Optional.empty(), Disposition.GOOD);
        createArtisanTableAxeRecipe(exporter, MetalTypes.STEEL, ModWeaponItems.DALISH_AXE.getDefaultInstance(), false, Optional.empty(), Disposition.GOOD);
        createArtisanTableAxeRecipe(exporter, MetalTypes.STEEL, ModWeaponItems.DALISH_NOBLE_AXE.getDefaultInstance(), true, Optional.empty(), Disposition.GOOD);
        createArtisanTableAxeRecipe(exporter, MetalTypes.STEEL, ModWeaponItems.DALISH_HEYDAY_AXE.getDefaultInstance(), true, Optional.empty(), Disposition.GOOD);
        createArtisanTableAxeRecipe(exporter, MetalTypes.STEEL, ModWeaponItems.BLACK_NUMENOREAN_AXE.getDefaultInstance(), true, Optional.empty(), Disposition.EVIL);

        createArtisanTableSpearRecipe(exporter, MetalTypes.STEEL, ModWeaponItems.GONDORIAN_SPEAR.getDefaultInstance(), false, Disposition.GOOD);
        createArtisanTableSpearRecipe(exporter, MetalTypes.STEEL, ModWeaponItems.GONDORIAN_NOBLE_SPEAR.getDefaultInstance(), true, Disposition.GOOD);
        createArtisanTableSpearRecipe(exporter, MetalTypes.STEEL, ModWeaponItems.GONDORIAN_FOUNTAIN_GUARD_SPEAR.getDefaultInstance(), true, Disposition.GOOD);
        createArtisanTableSpearRecipe(exporter, MetalTypes.STEEL, ModWeaponItems.ROHIRRIC_SPEAR.getDefaultInstance(), false, Disposition.GOOD);
        createArtisanTableSpearRecipe(exporter, MetalTypes.STEEL, ModWeaponItems.ROHIRRIC_NOBLE_SPEAR.getDefaultInstance(), true, Disposition.GOOD);
        createArtisanTableSpearRecipe(exporter, MetalTypes.STEEL, ModWeaponItems.DALISH_SPEAR.getDefaultInstance(), false, Disposition.GOOD);
        createArtisanTableSpearRecipe(exporter, MetalTypes.STEEL, ModWeaponItems.DALISH_HEYDAY_SPEAR.getDefaultInstance(), true, Disposition.GOOD);
        createArtisanTableSpearRecipe(exporter, MetalTypes.STEEL, ModWeaponItems.DALISH_NOBLE_SPEAR.getDefaultInstance(), true, Disposition.GOOD);
        createArtisanTableSpearRecipe(exporter, MetalTypes.STEEL, ModWeaponItems.STEEL_SPEAR.getDefaultInstance(), false, Disposition.NEUTRAL);
        createArtisanTableSpearRecipe(exporter, MetalTypes.STEEL, ModWeaponItems.BLACK_NUMENOREAN_SPEAR.getDefaultInstance(), true, Disposition.EVIL);

        createArtisanTableSwordRecipe(exporter, MetalTypes.KHAZAD_STEEL, ModWeaponItems.EREBOR_SWORD.getDefaultInstance(), false, Disposition.GOOD);
        createArtisanTableSwordRecipe(exporter, MetalTypes.KHAZAD_STEEL, ModWeaponItems.EREBOR_NOBLE_SWORD.getDefaultInstance(), true, Disposition.GOOD);
        createArtisanTableSwordRecipe(exporter, MetalTypes.KHAZAD_STEEL, ModWeaponItems.KHAZAD_STEEL_SWORD.getDefaultInstance(), false, Disposition.GOOD);

        createArtisanTableDaggerRecipe(exporter, MetalTypes.KHAZAD_STEEL, ModWeaponItems.EREBOR_DAGGER.getDefaultInstance(), false, Disposition.GOOD);
        createArtisanTableDaggerRecipe(exporter, MetalTypes.KHAZAD_STEEL, ModWeaponItems.EREBOR_NOBLE_DAGGER.getDefaultInstance(), true, Disposition.GOOD);

        createArtisanTableLongswordRecipe(exporter, MetalTypes.KHAZAD_STEEL, ModWeaponItems.EREBOR_LONGSWORD.getDefaultInstance(), false, Disposition.GOOD);
        createArtisanTableLongswordRecipe(exporter, MetalTypes.KHAZAD_STEEL, ModWeaponItems.EREBOR_NOBLE_LONGSWORD.getDefaultInstance(), true, Disposition.GOOD);

        createArtisanTableAxeRecipe(exporter, MetalTypes.KHAZAD_STEEL, ModWeaponItems.EREBOR_AXE.getDefaultInstance(), false, Optional.empty(), Disposition.GOOD);
        createArtisanTableAxeRecipe(exporter, MetalTypes.KHAZAD_STEEL, ModWeaponItems.EREBOR_NOBLE_AXE.getDefaultInstance(), true, Optional.empty(), Disposition.GOOD);

        createArtisanTableSpearRecipe(exporter, MetalTypes.KHAZAD_STEEL, ModWeaponItems.KHAZAD_STEEL_SPEAR.getDefaultInstance(), false, Disposition.GOOD);
        createArtisanTableSpearRecipe(exporter, MetalTypes.KHAZAD_STEEL, ModWeaponItems.EREBOR_SPEAR.getDefaultInstance(), false, Disposition.GOOD);
        createArtisanTableSpearRecipe(exporter, MetalTypes.KHAZAD_STEEL, ModWeaponItems.EREBOR_NOBLE_SPEAR.getDefaultInstance(), true, Disposition.GOOD);

        createArtisanTableSwordRecipe(exporter, MetalTypes.EDHEL_STEEL, ModWeaponItems.LORIEN_SWORD.getDefaultInstance(), false, Disposition.GOOD);
        createArtisanTableSwordRecipe(exporter, MetalTypes.EDHEL_STEEL, ModWeaponItems.LORIEN_NOBLE_SWORD.getDefaultInstance(), true, Disposition.GOOD);
        createArtisanTableSwordRecipe(exporter, MetalTypes.EDHEL_STEEL, ModWeaponItems.EDHEL_STEEL_SWORD.getDefaultInstance(), false, Disposition.GOOD);

        createArtisanTableDaggerRecipe(exporter, MetalTypes.EDHEL_STEEL, ModWeaponItems.LORIEN_DAGGER.getDefaultInstance(), false, Disposition.GOOD);
        createArtisanTableDaggerRecipe(exporter, MetalTypes.EDHEL_STEEL, ModWeaponItems.LORIEN_NOBLE_DAGGER.getDefaultInstance(), true, Disposition.GOOD);

        createArtisanTableLongswordRecipe(exporter, MetalTypes.EDHEL_STEEL, ModWeaponItems.LORIEN_GLAIVE.getDefaultInstance(), false, Disposition.GOOD);
        createArtisanTableLongswordRecipe(exporter, MetalTypes.EDHEL_STEEL, ModWeaponItems.LORIEN_NOBLE_GLAIVE.getDefaultInstance(), true, Disposition.GOOD);

        createArtisanTableAxeRecipe(exporter, MetalTypes.EDHEL_STEEL, ModWeaponItems.LORIEN_AXE.getDefaultInstance(), false, Optional.empty(), Disposition.GOOD);
        createArtisanTableAxeRecipe(exporter, MetalTypes.EDHEL_STEEL, ModWeaponItems.LORIEN_NOBLE_AXE.getDefaultInstance(), true, Optional.empty(), Disposition.GOOD);

        createArtisanTableSpearRecipe(exporter, MetalTypes.EDHEL_STEEL, ModWeaponItems.EDHEL_STEEL_SPEAR.getDefaultInstance(), false, Disposition.GOOD);
        createArtisanTableSpearRecipe(exporter, MetalTypes.EDHEL_STEEL, ModWeaponItems.LORIEN_SPEAR.getDefaultInstance(), false, Disposition.GOOD);
        createArtisanTableSpearRecipe(exporter, MetalTypes.EDHEL_STEEL, ModWeaponItems.LORIEN_NOBLE_SPEAR.getDefaultInstance(), true, Disposition.GOOD);

        createArtisanTableSwordRecipe(exporter, MetalTypes.BURZUM_STEEL, ModWeaponItems.BURZUM_STEEL_SWORD.getDefaultInstance(), false, Disposition.EVIL);
        createArtisanTableSwordRecipe(exporter, MetalTypes.BURZUM_STEEL, ModWeaponItems.ORC_SWORD.getDefaultInstance(), false, Disposition.EVIL);
        createArtisanTableSwordRecipe(exporter, MetalTypes.BURZUM_STEEL, ModWeaponItems.MORDOR_FALCHION.getDefaultInstance(), false, Disposition.EVIL);
        createArtisanTableSwordRecipe(exporter, MetalTypes.BURZUM_STEEL, ModWeaponItems.MORDOR_ELITE_CLEAVER.getDefaultInstance(), true, Disposition.EVIL);
        createArtisanTableSwordRecipe(exporter, MetalTypes.BURZUM_STEEL, ModWeaponItems.ISENGARD_ORC_CLEAVER.getDefaultInstance(), false, Disposition.EVIL);
        createArtisanTableSwordRecipe(exporter, MetalTypes.BURZUM_STEEL, ModWeaponItems.URUK_HAI_FALCHION.getDefaultInstance(), true, Disposition.EVIL);
        createArtisanTableSwordRecipe(exporter, MetalTypes.BURZUM_STEEL, ModWeaponItems.GUNDABAD_FALCHION.getDefaultInstance(), false, Disposition.EVIL);
        createArtisanTableSwordRecipe(exporter, MetalTypes.BURZUM_STEEL, ModWeaponItems.GUNDABAD_ELITE_CLEAVER.getDefaultInstance(), true, Disposition.EVIL);

        createArtisanTableDaggerRecipe(exporter, MetalTypes.BURZUM_STEEL, ModWeaponItems.ORC_KNIFE.getDefaultInstance(), false, Disposition.EVIL);
        createArtisanTableDaggerRecipe(exporter, MetalTypes.BURZUM_STEEL, ModWeaponItems.MORDOR_DAGGER.getDefaultInstance(), false, Disposition.EVIL);
        createArtisanTableDaggerRecipe(exporter, MetalTypes.BURZUM_STEEL, ModWeaponItems.MORDOR_ELITE_DAGGER.getDefaultInstance(), true, Disposition.EVIL);
        createArtisanTableDaggerRecipe(exporter, MetalTypes.BURZUM_STEEL, ModWeaponItems.ISENGARD_ORC_DAGGER.getDefaultInstance(), false, Disposition.EVIL);
        createArtisanTableDaggerRecipe(exporter, MetalTypes.BURZUM_STEEL, ModWeaponItems.URUK_HAI_KNIFE.getDefaultInstance(), true, Disposition.EVIL);
        createArtisanTableDaggerRecipe(exporter, MetalTypes.BURZUM_STEEL, ModWeaponItems.GUNDABAD_SHANK.getDefaultInstance(), false, Disposition.EVIL);
        createArtisanTableDaggerRecipe(exporter, MetalTypes.BURZUM_STEEL, ModWeaponItems.GUNDABAD_ELITE_DAGGER.getDefaultInstance(), true, Disposition.EVIL);

        createArtisanTableLongswordRecipe(exporter, MetalTypes.BURZUM_STEEL, ModWeaponItems.ORC_BROADBLADE.getDefaultInstance(), false, Disposition.EVIL);
        createArtisanTableLongswordRecipe(exporter, MetalTypes.BURZUM_STEEL, ModWeaponItems.MORDOR_SCIMITAR.getDefaultInstance(), false, Disposition.EVIL);
        createArtisanTableLongswordRecipe(exporter, MetalTypes.BURZUM_STEEL, ModWeaponItems.MORDOR_ELITE_WARBLADE.getDefaultInstance(), true, Disposition.EVIL);
        createArtisanTableLongswordRecipe(exporter, MetalTypes.BURZUM_STEEL, ModWeaponItems.ISENGARD_ORC_WARBLADE.getDefaultInstance(), false, Disposition.EVIL);
        createArtisanTableLongswordRecipe(exporter, MetalTypes.BURZUM_STEEL, ModWeaponItems.URUK_HAI_WARBLADE.getDefaultInstance(), true, Disposition.EVIL);
        createArtisanTableLongswordRecipe(exporter, MetalTypes.BURZUM_STEEL, ModWeaponItems.GUNDABAD_WARBLADE.getDefaultInstance(), false, Disposition.EVIL);
        createArtisanTableLongswordRecipe(exporter, MetalTypes.BURZUM_STEEL, ModWeaponItems.GUNDABAD_ELITE_SCIMITAR.getDefaultInstance(), true, Disposition.EVIL);

        createArtisanTableAxeRecipe(exporter, MetalTypes.BURZUM_STEEL, ModWeaponItems.ORC_AXE.getDefaultInstance(), false, Optional.empty(), Disposition.EVIL);
        createArtisanTableAxeRecipe(exporter, MetalTypes.BURZUM_STEEL, ModWeaponItems.MORDOR_AXE.getDefaultInstance(), false, Optional.empty(), Disposition.EVIL);
        createArtisanTableAxeRecipe(exporter, MetalTypes.BURZUM_STEEL, ModWeaponItems.MORDOR_ELITE_AXE.getDefaultInstance(), true, Optional.empty(), Disposition.EVIL);
        createArtisanTableAxeRecipe(exporter, MetalTypes.BURZUM_STEEL, ModWeaponItems.ISENGARD_ORC_AXE.getDefaultInstance(), false, Optional.empty(), Disposition.EVIL);
        createArtisanTableAxeRecipe(exporter, MetalTypes.BURZUM_STEEL, ModWeaponItems.URUK_HAI_AXE.getDefaultInstance(), true, Optional.empty(), Disposition.EVIL);
        createArtisanTableAxeRecipe(exporter, MetalTypes.BURZUM_STEEL, ModWeaponItems.GUNDABAD_AXE.getDefaultInstance(), false, Optional.empty(), Disposition.EVIL);
        createArtisanTableAxeRecipe(exporter, MetalTypes.BURZUM_STEEL, ModWeaponItems.GUNDABAD_ELITE_BATTLEAXE.getDefaultInstance(), true, Optional.empty(), Disposition.EVIL);

        createArtisanTableSpearRecipe(exporter, MetalTypes.BURZUM_STEEL, ModWeaponItems.BURZUM_STEEL_SPEAR.getDefaultInstance(), false, Disposition.EVIL);
        createArtisanTableSpearRecipe(exporter, MetalTypes.BURZUM_STEEL, ModWeaponItems.ORC_SPEAR.getDefaultInstance(), false, Disposition.EVIL);
        createArtisanTableSpearRecipe(exporter, MetalTypes.BURZUM_STEEL, ModWeaponItems.MORDOR_SPEAR.getDefaultInstance(), false, Disposition.EVIL);
        createArtisanTableSpearRecipe(exporter, MetalTypes.BURZUM_STEEL, ModWeaponItems.MORDOR_ELITE_SPEAR.getDefaultInstance(), true, Disposition.EVIL);
        createArtisanTableSpearRecipe(exporter, MetalTypes.BURZUM_STEEL, ModWeaponItems.ISENGARD_ORC_SPEAR.getDefaultInstance(), false, Disposition.EVIL);
        createArtisanTableSpearRecipe(exporter, MetalTypes.BURZUM_STEEL, ModWeaponItems.URUK_HAI_SPEAR.getDefaultInstance(), true, Disposition.EVIL);
        createArtisanTableSpearRecipe(exporter, MetalTypes.BURZUM_STEEL, ModWeaponItems.GUNDABAD_SPEAR.getDefaultInstance(), false, Disposition.EVIL);
        createArtisanTableSpearRecipe(exporter, MetalTypes.BURZUM_STEEL, ModWeaponItems.GUNDABAD_ELITE_SPEAR.getDefaultInstance(), true, Disposition.EVIL);

        createArtisanTableBowRecipe(exporter, ModWeaponItems.GONDORIAN_BOW.getDefaultInstance(), Disposition.GOOD);
        createArtisanTableLongbowRecipe(exporter, ModWeaponItems.GONDORIAN_LONGBOW.getDefaultInstance(), Disposition.GOOD);
        createArtisanTableNobleLongbowRecipe(exporter, ModWeaponItems.GONDORIAN_NOBLE_LONGBOW.getDefaultInstance(), Disposition.GOOD);

        createArtisanTableBowRecipe(exporter, ModWeaponItems.ROHIRRIC_BOW.getDefaultInstance(), Disposition.GOOD);
        createArtisanTableNobleBowRecipe(exporter, ModWeaponItems.ROHIRRIC_NOBLE_BOW.getDefaultInstance(), Disposition.GOOD);
        createArtisanTableLongbowRecipe(exporter, ModWeaponItems.ROHIRRIC_LONGBOW.getDefaultInstance(), Disposition.GOOD);

        createArtisanTableLongbowRecipe(exporter, ModWeaponItems.DALISH_LONGBOW.getDefaultInstance(), Disposition.GOOD);
        createArtisanTableNobleLongbowRecipe(exporter, ModWeaponItems.DALISH_HEYDAY_LONGBOW.getDefaultInstance(), Disposition.GOOD);
        createArtisanTableNobleLongbowRecipe(exporter, ModWeaponItems.DALISH_NOBLE_LONGBOW.getDefaultInstance(), Disposition.GOOD);

        createArtisanTableBowRecipe(exporter, ModWeaponItems.LORIEN_BOW.getDefaultInstance(), Disposition.GOOD);
        createArtisanTableLongbowRecipe(exporter, ModWeaponItems.LORIEN_LONGBOW.getDefaultInstance(), Disposition.GOOD);
        createArtisanTableNobleLongbowRecipe(exporter, ModWeaponItems.LORIEN_NOBLE_LONGBOW.getDefaultInstance(), Disposition.GOOD);

        createArtisanTableBowRecipe(exporter, ModWeaponItems.EREBOR_BOW.getDefaultInstance(), Disposition.GOOD);
        createArtisanTableNobleBowRecipe(exporter, ModWeaponItems.EREBOR_NOBLE_BOW.getDefaultInstance(), Disposition.GOOD);
        createArtisanTableCrossbowRecipe(exporter, ModWeaponItems.EREBOR_CROSSBOW.getDefaultInstance(), Disposition.GOOD);
        createArtisanTableNobleCrossbowRecipe(exporter, ModWeaponItems.EREBOR_NOBLE_CROSSBOW.getDefaultInstance(), Disposition.GOOD);

        createArtisanTableBowRecipe(exporter, ModWeaponItems.ORCISH_BOW.getDefaultInstance(), Disposition.EVIL);

        createArtisanTableBowRecipe(exporter, ModWeaponItems.MORDOR_BOW.getDefaultInstance(), Disposition.EVIL);
        createArtisanTableNobleLongbowRecipe(exporter, ModWeaponItems.MORDOR_ELITE_LONGBOW.getDefaultInstance(), Disposition.EVIL);

        createArtisanTableNobleBowRecipe(exporter, ModWeaponItems.URUK_HAI_BOW.getDefaultInstance(), Disposition.EVIL);
        createArtisanTableNobleCrossbowRecipe(exporter, ModWeaponItems.URUK_HAI_CROSSBOW.getDefaultInstance(), Disposition.EVIL);

        createArtisanTableBowRecipe(exporter, ModWeaponItems.GUNDABAD_BOW.getDefaultInstance(), Disposition.EVIL);
        createArtisanTableCrossbowRecipe(exporter, ModWeaponItems.GUNDABAD_CROSSBOW.getDefaultInstance(), Disposition.EVIL);

        createArtisanTableBowRecipe(exporter, Items.BOW.getDefaultInstance(), Disposition.NEUTRAL);
        createArtisanTableCrossbowRecipe(exporter, Items.CROSSBOW.getDefaultInstance(), Disposition.NEUTRAL);
        //endregion

        //region TOOLS
        createToolSet(exporter, MetalTypes.BRONZE, ModToolItems.BRONZE_PICKAXE.getDefaultInstance(),
                ModToolItems.BRONZE_AXE.getDefaultInstance(),
                ModToolItems.BRONZE_SHOVEL.getDefaultInstance(),
                ModToolItems.BRONZE_HOE.getDefaultInstance(),
                Optional.empty(), Disposition.NEUTRAL);

        createToolSet(exporter, MetalTypes.CRUDE, ModToolItems.CRUDE_PICKAXE.getDefaultInstance(),
                ModToolItems.CRUDE_AXE.getDefaultInstance(),
                ModToolItems.CRUDE_SHOVEL.getDefaultInstance(),
                ModToolItems.CRUDE_HOE.getDefaultInstance(),
                Optional.empty(), Disposition.EVIL);

        createToolSet(exporter, MetalTypes.IRON, Items.IRON_PICKAXE.getDefaultInstance(),
                Items.IRON_AXE.getDefaultInstance(),
                Items.IRON_SHOVEL.getDefaultInstance(),
                Items.IRON_HOE.getDefaultInstance(),
                Optional.empty(), Disposition.NEUTRAL);

        createToolSet(exporter, MetalTypes.GOLD, Items.GOLDEN_PICKAXE.getDefaultInstance(),
                Items.GOLDEN_AXE.getDefaultInstance(),
                Items.GOLDEN_SHOVEL.getDefaultInstance(),
                Items.GOLDEN_HOE.getDefaultInstance(),
                Optional.empty(), Disposition.NEUTRAL);

        createToolSet(exporter, MetalTypes.STEEL, ModToolItems.STEEL_PICKAXE.getDefaultInstance(),
                ModToolItems.STEEL_AXE.getDefaultInstance(),
                ModToolItems.STEEL_SHOVEL.getDefaultInstance(),
                ModToolItems.STEEL_HOE.getDefaultInstance(),
                Optional.empty(), Disposition.NEUTRAL);

        createToolSet(exporter, MetalTypes.KHAZAD_STEEL, ModToolItems.KHAZAD_STEEL_PICKAXE.getDefaultInstance(),
                ModToolItems.KHAZAD_STEEL_AXE.getDefaultInstance(),
                ModToolItems.KHAZAD_STEEL_SHOVEL.getDefaultInstance(),
                ModToolItems.KHAZAD_STEEL_HOE.getDefaultInstance(),
                Optional.empty(), Disposition.GOOD);

        createToolSet(exporter, MetalTypes.EDHEL_STEEL, ModToolItems.EDHEL_STEEL_PICKAXE.getDefaultInstance(),
                ModToolItems.EDHEL_STEEL_AXE.getDefaultInstance(),
                ModToolItems.EDHEL_STEEL_SHOVEL.getDefaultInstance(),
                ModToolItems.EDHEL_STEEL_HOE.getDefaultInstance(),
                Optional.empty(), Disposition.GOOD);

        createToolSet(exporter, MetalTypes.BURZUM_STEEL, ModToolItems.BURZUM_STEEL_PICKAXE.getDefaultInstance(),
                ModToolItems.BURZUM_STEEL_AXE.getDefaultInstance(),
                ModToolItems.BURZUM_STEEL_SHOVEL.getDefaultInstance(),
                ModToolItems.BURZUM_STEEL_HOE.getDefaultInstance(),
                Optional.empty(), Disposition.EVIL);

        createToolSet(exporter, MetalTypes.MITHRIL, ModToolItems.MITHRIL_PICKAXE.getDefaultInstance(),
                ModToolItems.MITHRIL_AXE.getDefaultInstance(),
                ModToolItems.MITHRIL_SHOVEL.getDefaultInstance(),
                ModToolItems.MITHRIL_HOE.getDefaultInstance(),
                Optional.of(MetalTypes.STEEL), Disposition.NEUTRAL);

        createToolSet(exporter, MetalTypes.MITHRIL, ModToolItems.MITHRIL_PICKAXE.getDefaultInstance(),
                ModToolItems.MITHRIL_AXE.getDefaultInstance(),
                ModToolItems.MITHRIL_SHOVEL.getDefaultInstance(),
                ModToolItems.MITHRIL_HOE.getDefaultInstance(),
                Optional.of(MetalTypes.KHAZAD_STEEL), Disposition.NEUTRAL);

        createToolSet(exporter, MetalTypes.MITHRIL, ModToolItems.MITHRIL_PICKAXE.getDefaultInstance(),
                ModToolItems.MITHRIL_AXE.getDefaultInstance(),
                ModToolItems.MITHRIL_SHOVEL.getDefaultInstance(),
                ModToolItems.MITHRIL_HOE.getDefaultInstance(),
                Optional.of(MetalTypes.EDHEL_STEEL), Disposition.NEUTRAL);

        createToolSet(exporter, MetalTypes.MITHRIL, ModToolItems.MITHRIL_PICKAXE.getDefaultInstance(),
                ModToolItems.MITHRIL_AXE.getDefaultInstance(),
                ModToolItems.MITHRIL_SHOVEL.getDefaultInstance(),
                ModToolItems.MITHRIL_HOE.getDefaultInstance(),
                Optional.of(MetalTypes.BURZUM_STEEL), Disposition.NEUTRAL);

        createToolSet(exporter, MetalTypes.NETHERITE, Items.NETHERITE_PICKAXE.getDefaultInstance(),
                Items.NETHERITE_AXE.getDefaultInstance(),
                Items.NETHERITE_SHOVEL.getDefaultInstance(),
                Items.NETHERITE_HOE.getDefaultInstance(),
                Optional.empty(), Disposition.NEUTRAL);
        //endregion

        //region SHIELDS
        ItemStack ironShieldBorder = new ItemStack(ModResourceItems.SHIELD_BORDER);
        ironShieldBorder.set(DataComponents.TRIM, new ArmorTrim(getArmorTrimMaterialsRegistry().getOrThrow(ResourceKey.create(Registries.TRIM_MATERIAL,
                ResourceLocation.parse(MetalTypes.IRON.getName()))), getPattern()));

        ItemStack bronzeShieldBorder = new ItemStack(ModResourceItems.SHIELD_BORDER);
        bronzeShieldBorder.set(DataComponents.TRIM, new ArmorTrim(getArmorTrimMaterialsRegistry().getOrThrow(ResourceKey.create(Registries.TRIM_MATERIAL,
                ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, MetalTypes.BRONZE.getName()))), getPattern()));
        
        ItemStack crudeShieldBorder = new ItemStack(ModResourceItems.SHIELD_BORDER);
        crudeShieldBorder.set(DataComponents.TRIM, new ArmorTrim(getArmorTrimMaterialsRegistry().getOrThrow(ResourceKey.create(Registries.TRIM_MATERIAL,
                ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, MetalTypes.CRUDE.getName()))), getPattern()));

        ItemStack steelShieldPlate = new ItemStack(ModResourceItems.SHIELD_PLATE);
        steelShieldPlate.set(DataComponents.TRIM, new ArmorTrim(getArmorTrimMaterialsRegistry().getOrThrow(ResourceKey.create(Registries.TRIM_MATERIAL,
                ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, MetalTypes.STEEL.getName()))), getPattern()));

        ItemStack edhelSteelShieldPlate = new ItemStack(ModResourceItems.SHIELD_PLATE);
        edhelSteelShieldPlate.set(DataComponents.TRIM, new ArmorTrim(getArmorTrimMaterialsRegistry().getOrThrow(ResourceKey.create(Registries.TRIM_MATERIAL,
                ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, MetalTypes.EDHEL_STEEL.getName()))), getPattern()));

        ItemStack khazadSteelShieldPlate = new ItemStack(ModResourceItems.SHIELD_PLATE);
        khazadSteelShieldPlate.set(DataComponents.TRIM, new ArmorTrim(getArmorTrimMaterialsRegistry().getOrThrow(ResourceKey.create(Registries.TRIM_MATERIAL,
                ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, MetalTypes.KHAZAD_STEEL.getName()))), getPattern()));

        ItemStack burzumSteelShieldPlate = new ItemStack(ModResourceItems.SHIELD_PLATE);
        burzumSteelShieldPlate.set(DataComponents.TRIM, new ArmorTrim(getArmorTrimMaterialsRegistry().getOrThrow(ResourceKey.create(Registries.TRIM_MATERIAL,
                ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, MetalTypes.BURZUM_STEEL.getName()))), getPattern()));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, Items.SHIELD.getDefaultInstance(), "medium_shield", Disposition.NEUTRAL)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironShieldBorder.getItem()), ironShieldBorder.getComponentsPatch()))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.SHIELD_BORDER),
                        NeoForgeRecipeProvider.has(ModResourceItems.SHIELD_BORDER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(Items.SHIELD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.ROUND_SHIELD.getDefaultInstance(), "medium_shield", Disposition.NEUTRAL)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironShieldBorder.getItem()), ironShieldBorder.getComponentsPatch()))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.SHIELD_BORDER),
                        NeoForgeRecipeProvider.has(ModResourceItems.SHIELD_BORDER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.ROUND_SHIELD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.HEATER_SHIELD.getDefaultInstance(), "medium_shield", Disposition.NEUTRAL)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironShieldBorder.getItem()), ironShieldBorder.getComponentsPatch()))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.SHIELD_BORDER),
                        NeoForgeRecipeProvider.has(ModResourceItems.SHIELD_BORDER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.HEATER_SHIELD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.KITE_SHIELD.getDefaultInstance(), "medium_shield", Disposition.NEUTRAL)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironShieldBorder.getItem()), ironShieldBorder.getComponentsPatch()))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.SHIELD_BORDER),
                        NeoForgeRecipeProvider.has(ModResourceItems.SHIELD_BORDER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.KITE_SHIELD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.GONDORIAN_SHIELD.getDefaultInstance(), "medium_shield", Disposition.GOOD)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(ModResourceItems.GONDOR_BANNER_PATTERN)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(Items.BLACK_DYE)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironShieldBorder.getItem()), ironShieldBorder.getComponentsPatch()))
                .input(Items.BLACK_DYE)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.SHIELD_BORDER),
                        NeoForgeRecipeProvider.has(ModResourceItems.SHIELD_BORDER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.GONDORIAN_SHIELD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.GONDORIAN_TOWER_SHIELD.getDefaultInstance(), "medium_shield", Disposition.GOOD)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(ModResourceItems.GONDOR_BANNER_PATTERN)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(Items.BLACK_DYE)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironShieldBorder.getItem()), ironShieldBorder.getComponentsPatch()))
                .input(Items.BLACK_DYE)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.SHIELD_BORDER),
                        NeoForgeRecipeProvider.has(ModResourceItems.SHIELD_BORDER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.GONDORIAN_TOWER_SHIELD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.GONDORIAN_KINGS_GUARD_TOWER_SHIELD.getDefaultInstance(), "medium_shield", Disposition.GOOD)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(ModResourceItems.GONDOR_BANNER_PATTERN)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(Items.BLACK_DYE)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironShieldBorder.getItem()), ironShieldBorder.getComponentsPatch()))
                .input(Items.BLACK_DYE)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.SHIELD_BORDER),
                        NeoForgeRecipeProvider.has(ModResourceItems.SHIELD_BORDER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.GONDORIAN_KINGS_GUARD_TOWER_SHIELD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.LAST_ALLIANCE_HEIRLOOM_TOWER_SHIELD.getDefaultInstance(), "medium_shield", Disposition.GOOD)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(ModResourceItems.GONDOR_BANNER_PATTERN)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(Items.BLACK_DYE)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironShieldBorder.getItem()), ironShieldBorder.getComponentsPatch()))
                .input(Items.BLACK_DYE)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.SHIELD_BORDER),
                        NeoForgeRecipeProvider.has(ModResourceItems.SHIELD_BORDER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.LAST_ALLIANCE_HEIRLOOM_TOWER_SHIELD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.GONDORIAN_HERO_SHIELD.getDefaultInstance(), "medium_shield", Disposition.GOOD)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(Items.LEATHER)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(Items.LEATHER)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironShieldBorder.getItem()), ironShieldBorder.getComponentsPatch()))
                .input(Items.LEATHER)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.SHIELD_BORDER),
                        NeoForgeRecipeProvider.has(ModResourceItems.SHIELD_BORDER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.GONDORIAN_HERO_SHIELD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.GONDORIAN_KNIGHT_SHIELD.getDefaultInstance(), "heavy_shield", Disposition.GOOD)
                .input(ModResourceItems.STEEL_NUGGET)
                .input(ModResourceItems.GONDOR_BANNER_PATTERN)
                .input(ModResourceItems.STEEL_NUGGET)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelShieldPlate.getItem()), steelShieldPlate.getComponentsPatch()))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(ModResourceItems.STEEL_NUGGET)
                .input(Items.LEATHER)
                .input(ModResourceItems.STEEL_NUGGET)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.SHIELD_PLATE),
                        NeoForgeRecipeProvider.has(ModResourceItems.SHIELD_PLATE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.GONDORIAN_KNIGHT_SHIELD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.GONDORIAN_ORNAMENTED_KNIGHT_SHIELD.getDefaultInstance(), "heavy_shield", Disposition.GOOD)
                .input(ModResourceItems.STEEL_NUGGET)
                .input(ModResourceItems.GONDOR_BANNER_PATTERN)
                .input(ModResourceItems.STEEL_NUGGET)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelShieldPlate.getItem()), steelShieldPlate.getComponentsPatch()))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(Items.GOLD_NUGGET)
                .input(Items.GREEN_DYE)
                .input(Items.GOLD_NUGGET)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.SHIELD_PLATE),
                        NeoForgeRecipeProvider.has(ModResourceItems.SHIELD_PLATE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.GONDORIAN_ORNAMENTED_KNIGHT_SHIELD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.ROHIRRIC_SHIELD.getDefaultInstance(), "medium_shield", Disposition.GOOD)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(Items.WHITE_DYE)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(Items.GREEN_DYE)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironShieldBorder.getItem()), ironShieldBorder.getComponentsPatch()))
                .input(Items.GREEN_DYE)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.SHIELD_BORDER),
                        NeoForgeRecipeProvider.has(ModResourceItems.SHIELD_BORDER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.ROHIRRIC_SHIELD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.ROHIRRIC_BUCKING_HORSE_SHIELD.getDefaultInstance(), "medium_shield", Disposition.GOOD)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(ModResourceItems.ROHAN_BANNER_PATTERN)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(Items.GREEN_DYE)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironShieldBorder.getItem()), ironShieldBorder.getComponentsPatch()))
                .input(Items.GREEN_DYE)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.SHIELD_BORDER),
                        NeoForgeRecipeProvider.has(ModResourceItems.SHIELD_BORDER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.ROHIRRIC_BUCKING_HORSE_SHIELD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.ROHIRRIC_GALLOPING_HORSE_SHIELD.getDefaultInstance(), "medium_shield", Disposition.GOOD)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(ModResourceItems.ROHAN_BANNER_PATTERN)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(Items.GREEN_DYE)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironShieldBorder.getItem()), ironShieldBorder.getComponentsPatch()))
                .input(Items.GREEN_DYE)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.SHIELD_BORDER),
                        NeoForgeRecipeProvider.has(ModResourceItems.SHIELD_BORDER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.ROHIRRIC_GALLOPING_HORSE_SHIELD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.ROHIRRIC_HORSE_HEAD_SHIELD.getDefaultInstance(), "medium_shield", Disposition.GOOD)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(ModResourceItems.ROHAN_BANNER_PATTERN)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(Items.GREEN_DYE)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironShieldBorder.getItem()), ironShieldBorder.getComponentsPatch()))
                .input(Items.GREEN_DYE)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.SHIELD_BORDER),
                        NeoForgeRecipeProvider.has(ModResourceItems.SHIELD_BORDER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.ROHIRRIC_HORSE_HEAD_SHIELD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.ROHIRRIC_PLAINSMAN_SHIELD.getDefaultInstance(), "medium_shield", Disposition.GOOD)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(Items.YELLOW_DYE)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(Items.GREEN_DYE)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironShieldBorder.getItem()), ironShieldBorder.getComponentsPatch()))
                .input(Items.GREEN_DYE)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.SHIELD_BORDER),
                        NeoForgeRecipeProvider.has(ModResourceItems.SHIELD_BORDER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.ROHIRRIC_PLAINSMAN_SHIELD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.ROHIRRIC_TWIN_HORSES_SHIELD.getDefaultInstance(), "medium_shield", Disposition.GOOD)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(ModResourceItems.ROHAN_BANNER_PATTERN)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(Items.GREEN_DYE)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironShieldBorder.getItem()), ironShieldBorder.getComponentsPatch()))
                .input(Items.GREEN_DYE)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.SHIELD_BORDER),
                        NeoForgeRecipeProvider.has(ModResourceItems.SHIELD_BORDER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.ROHIRRIC_TWIN_HORSES_SHIELD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.ROHIRRIC_EORLING_SHIELD.getDefaultInstance(), "heavy_shield", Disposition.GOOD)
                .input(ModResourceItems.STEEL_NUGGET)
                .input(Items.YELLOW_DYE)
                .input(ModResourceItems.STEEL_NUGGET)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelShieldPlate.getItem()), steelShieldPlate.getComponentsPatch()))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(ModResourceItems.STEEL_NUGGET)
                .input(Items.LEATHER)
                .input(ModResourceItems.STEEL_NUGGET)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.SHIELD_PLATE),
                        NeoForgeRecipeProvider.has(ModResourceItems.SHIELD_PLATE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.ROHIRRIC_EORLING_SHIELD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.ROHIRRIC_ORNAMENTED_SHIELD.getDefaultInstance(), "heavy_shield", Disposition.GOOD)
                .input(ModResourceItems.STEEL_NUGGET)
                .input(ModResourceItems.ROHAN_BANNER_PATTERN)
                .input(ModResourceItems.STEEL_NUGGET)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelShieldPlate.getItem()), steelShieldPlate.getComponentsPatch()))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(Items.GOLD_NUGGET)
                .input(Items.LEATHER)
                .input(Items.GOLD_NUGGET)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.SHIELD_PLATE),
                        NeoForgeRecipeProvider.has(ModResourceItems.SHIELD_PLATE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.ROHIRRIC_ORNAMENTED_SHIELD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.ROHIRRIC_ROYAL_GUARD_SHIELD.getDefaultInstance(), "heavy_shield", Disposition.GOOD)
                .input(ModResourceItems.STEEL_NUGGET)
                .input(Items.YELLOW_DYE)
                .input(ModResourceItems.STEEL_NUGGET)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelShieldPlate.getItem()), steelShieldPlate.getComponentsPatch()))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(ModResourceItems.STEEL_NUGGET)
                .input(Items.LEATHER)
                .input(ModResourceItems.STEEL_NUGGET)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.SHIELD_PLATE),
                        NeoForgeRecipeProvider.has(ModResourceItems.SHIELD_PLATE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.ROHIRRIC_ROYAL_GUARD_SHIELD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.DALISH_BLUE_OVAL_SHIELD.getDefaultInstance(), "medium_shield", Disposition.GOOD)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(Items.BLUE_DYE)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(Items.BLUE_DYE)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironShieldBorder.getItem()), ironShieldBorder.getComponentsPatch()))
                .input(Items.BLUE_DYE)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.SHIELD_BORDER),
                        NeoForgeRecipeProvider.has(ModResourceItems.SHIELD_BORDER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.DALISH_BLUE_OVAL_SHIELD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.DALISH_BARDING_OVAL_SHIELD.getDefaultInstance(), "medium_shield", Disposition.GOOD)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(Items.BLUE_DYE)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(Items.GOLD_NUGGET)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironShieldBorder.getItem()), ironShieldBorder.getComponentsPatch()))
                .input(Items.GOLD_NUGGET)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.SHIELD_BORDER),
                        NeoForgeRecipeProvider.has(ModResourceItems.SHIELD_BORDER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.DALISH_BARDING_OVAL_SHIELD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.DALISH_BLUE_BRACED_SHIELD.getDefaultInstance(), "medium_shield", Disposition.GOOD)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(Items.IRON_NUGGET)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(Items.BLUE_DYE)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironShieldBorder.getItem()), ironShieldBorder.getComponentsPatch()))
                .input(Items.BLUE_DYE)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.SHIELD_BORDER),
                        NeoForgeRecipeProvider.has(ModResourceItems.SHIELD_BORDER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.DALISH_BLUE_BRACED_SHIELD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.DALISH_BARDING_BRACED_SHIELD.getDefaultInstance(), "medium_shield", Disposition.GOOD)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(Items.IRON_NUGGET)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(Items.GOLD_NUGGET)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironShieldBorder.getItem()), ironShieldBorder.getComponentsPatch()))
                .input(Items.GOLD_NUGGET)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.SHIELD_BORDER),
                        NeoForgeRecipeProvider.has(ModResourceItems.SHIELD_BORDER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.DALISH_BARDING_BRACED_SHIELD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.DALISH_HEAVY_SHIELD.getDefaultInstance(), "heavy_shield", Disposition.GOOD)
                .input(ModResourceItems.STEEL_NUGGET)
                .input(Items.WHITE_DYE)
                .input(ModResourceItems.STEEL_NUGGET)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelShieldPlate.getItem()), steelShieldPlate.getComponentsPatch()))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(ModResourceItems.STEEL_NUGGET)
                .input(Items.LEATHER)
                .input(ModResourceItems.STEEL_NUGGET)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.SHIELD_PLATE),
                        NeoForgeRecipeProvider.has(ModResourceItems.SHIELD_PLATE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.DALISH_HEAVY_SHIELD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.DALISH_BARDING_HEAVY_SHIELD.getDefaultInstance(), "heavy_shield", Disposition.GOOD)
                .input(ModResourceItems.STEEL_NUGGET)
                .input(Items.BLUE_DYE)
                .input(ModResourceItems.STEEL_NUGGET)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelShieldPlate.getItem()), steelShieldPlate.getComponentsPatch()))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(ModResourceItems.STEEL_NUGGET)
                .input(Items.LEATHER)
                .input(ModResourceItems.STEEL_NUGGET)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.SHIELD_PLATE),
                        NeoForgeRecipeProvider.has(ModResourceItems.SHIELD_PLATE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.DALISH_BARDING_HEAVY_SHIELD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.DALISH_ROYAL_HEAVY_SHIELD.getDefaultInstance(), "heavy_shield", Disposition.GOOD)
                .input(Items.GOLD_NUGGET)
                .input(Items.LIGHT_BLUE_DYE)
                .input(Items.GOLD_NUGGET)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelShieldPlate.getItem()), steelShieldPlate.getComponentsPatch()))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(ModResourceItems.STEEL_NUGGET)
                .input(Items.LEATHER)
                .input(ModResourceItems.STEEL_NUGGET)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.SHIELD_PLATE),
                        NeoForgeRecipeProvider.has(ModResourceItems.SHIELD_PLATE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.DALISH_ROYAL_HEAVY_SHIELD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.DALISH_ROYAL_ROUND_SHIELD.getDefaultInstance(), "heavy_shield", Disposition.GOOD)
                .input(Items.GOLD_NUGGET)
                .input(Items.LIGHT_BLUE_DYE)
                .input(Items.GOLD_NUGGET)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelShieldPlate.getItem()), steelShieldPlate.getComponentsPatch()))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(Items.GOLD_NUGGET)
                .input(Items.LEATHER)
                .input(Items.GOLD_NUGGET)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.SHIELD_PLATE),
                        NeoForgeRecipeProvider.has(ModResourceItems.SHIELD_PLATE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.DALISH_ROYAL_ROUND_SHIELD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.DALISH_HEYDAY_ROUND_SHIELD.getDefaultInstance(), "heavy_shield", Disposition.GOOD)
                .input(ModResourceItems.STEEL_NUGGET)
                .input(Items.ORANGE_DYE)
                .input(ModResourceItems.STEEL_NUGGET)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelShieldPlate.getItem()), steelShieldPlate.getComponentsPatch()))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(ModResourceItems.STEEL_NUGGET)
                .input(Items.LEATHER)
                .input(ModResourceItems.STEEL_NUGGET)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.SHIELD_PLATE),
                        NeoForgeRecipeProvider.has(ModResourceItems.SHIELD_PLATE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.DALISH_HEYDAY_ROUND_SHIELD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.LORIEN_SHIELD.getDefaultInstance(), "medium_shield", Disposition.GOOD)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(Items.LEATHER)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(Items.LEATHER)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironShieldBorder.getItem()), ironShieldBorder.getComponentsPatch()))
                .input(Items.LEATHER)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.SHIELD_BORDER),
                        NeoForgeRecipeProvider.has(ModResourceItems.SHIELD_BORDER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.LORIEN_SHIELD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.LORIEN_LAURELS_SHIELD.getDefaultInstance(), "medium_shield", Disposition.GOOD)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(Items.YELLOW_DYE)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(Items.LEATHER)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironShieldBorder.getItem()), ironShieldBorder.getComponentsPatch()))
                .input(Items.LEATHER)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.SHIELD_BORDER),
                        NeoForgeRecipeProvider.has(ModResourceItems.SHIELD_BORDER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.LORIEN_LAURELS_SHIELD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.LORIEN_MALLORN_SHIELD.getDefaultInstance(), "medium_shield", Disposition.GOOD)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(Items.YELLOW_DYE)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(Items.LEATHER)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironShieldBorder.getItem()), ironShieldBorder.getComponentsPatch()))
                .input(Items.LEATHER)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.SHIELD_BORDER),
                        NeoForgeRecipeProvider.has(ModResourceItems.SHIELD_BORDER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.LORIEN_MALLORN_SHIELD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.GALADHRIM_SHIELD.getDefaultInstance(), "heavy_shield", Disposition.GOOD)
                .input(ModResourceItems.EDHEL_STEEL_NUGGET)
                .input(Items.YELLOW_DYE)
                .input(ModResourceItems.EDHEL_STEEL_NUGGET)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .componentInput(new ComponentsIngredient(Ingredient.of(edhelSteelShieldPlate.getItem()), edhelSteelShieldPlate.getComponentsPatch()))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(ModResourceItems.EDHEL_STEEL_NUGGET)
                .input(Items.LEATHER)
                .input(ModResourceItems.EDHEL_STEEL_NUGGET)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.SHIELD_PLATE),
                        NeoForgeRecipeProvider.has(ModResourceItems.SHIELD_PLATE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.GALADHRIM_SHIELD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.GALADHRIM_LORD_SHIELD.getDefaultInstance(), "heavy_shield", Disposition.GOOD)
                .input(ModResourceItems.EDHEL_STEEL_NUGGET)
                .input(Items.YELLOW_DYE)
                .input(ModResourceItems.EDHEL_STEEL_NUGGET)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .componentInput(new ComponentsIngredient(Ingredient.of(edhelSteelShieldPlate.getItem()), edhelSteelShieldPlate.getComponentsPatch()))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(Items.GOLD_NUGGET)
                .input(Items.LEATHER)
                .input(Items.GOLD_NUGGET)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.SHIELD_PLATE),
                        NeoForgeRecipeProvider.has(ModResourceItems.SHIELD_PLATE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.GALADHRIM_LORD_SHIELD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.EREBOR_SHIELD.getDefaultInstance(), "medium_shield", Disposition.GOOD)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(ModResourceItems.BRONZE_NUGGET)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(Items.LEATHER)
                .componentInput(new ComponentsIngredient(Ingredient.of(bronzeShieldBorder.getItem()), bronzeShieldBorder.getComponentsPatch()))
                .input(Items.LEATHER)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.SHIELD_BORDER),
                        NeoForgeRecipeProvider.has(ModResourceItems.SHIELD_BORDER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.EREBOR_SHIELD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.EREBOR_CROSS_SHIELD.getDefaultInstance(), "medium_shield", Disposition.GOOD)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(ModResourceItems.BRONZE_NUGGET)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(Items.LEATHER)
                .componentInput(new ComponentsIngredient(Ingredient.of(bronzeShieldBorder.getItem()), bronzeShieldBorder.getComponentsPatch()))
                .input(Items.LEATHER)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.SHIELD_BORDER),
                        NeoForgeRecipeProvider.has(ModResourceItems.SHIELD_BORDER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.EREBOR_CROSS_SHIELD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.EREBOR_PLATED_SHIELD.getDefaultInstance(), "medium_shield", Disposition.GOOD)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(ModResourceItems.BRONZE_NUGGET)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(ModResourceItems.BRONZE_NUGGET)
                .componentInput(new ComponentsIngredient(Ingredient.of(bronzeShieldBorder.getItem()), bronzeShieldBorder.getComponentsPatch()))
                .input(ModResourceItems.BRONZE_NUGGET)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.SHIELD_BORDER),
                        NeoForgeRecipeProvider.has(ModResourceItems.SHIELD_BORDER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.EREBOR_PLATED_SHIELD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.EREBOR_ORNAMENTED_SHIELD.getDefaultInstance(), "medium_shield", Disposition.GOOD)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(Items.GOLD_NUGGET)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(ModResourceItems.BRONZE_NUGGET)
                .componentInput(new ComponentsIngredient(Ingredient.of(bronzeShieldBorder.getItem()), bronzeShieldBorder.getComponentsPatch()))
                .input(ModResourceItems.BRONZE_NUGGET)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.SHIELD_BORDER),
                        NeoForgeRecipeProvider.has(ModResourceItems.SHIELD_BORDER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.EREBOR_ORNAMENTED_SHIELD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.EREBOR_REINFORCED_SHIELD.getDefaultInstance(), "medium_shield", Disposition.GOOD)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(Items.IRON_NUGGET)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(ModResourceItems.BRONZE_NUGGET)
                .componentInput(new ComponentsIngredient(Ingredient.of(bronzeShieldBorder.getItem()), bronzeShieldBorder.getComponentsPatch()))
                .input(ModResourceItems.BRONZE_NUGGET)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.SHIELD_BORDER),
                        NeoForgeRecipeProvider.has(ModResourceItems.SHIELD_BORDER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.EREBOR_REINFORCED_SHIELD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.EREBOR_BUCKLER_SHIELD.getDefaultInstance(), "heavy_shield", Disposition.GOOD)
                .input(ModResourceItems.KHAZAD_STEEL_NUGGET)
                .input(ModResourceItems.BRONZE_INGOT)
                .input(ModResourceItems.KHAZAD_STEEL_NUGGET)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelShieldPlate.getItem()), khazadSteelShieldPlate.getComponentsPatch()))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(ModResourceItems.KHAZAD_STEEL_NUGGET)
                .input(Items.LEATHER)
                .input(ModResourceItems.KHAZAD_STEEL_NUGGET)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.SHIELD_PLATE),
                        NeoForgeRecipeProvider.has(ModResourceItems.SHIELD_PLATE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.EREBOR_BUCKLER_SHIELD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.EREBOR_TOWER_SHIELD.getDefaultInstance(), "heavy_shield", Disposition.GOOD)
                .input(ModResourceItems.KHAZAD_STEEL_NUGGET)
                .input(ModResourceItems.KHAZAD_STEEL_INGOT)
                .input(ModResourceItems.KHAZAD_STEEL_NUGGET)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelShieldPlate.getItem()), khazadSteelShieldPlate.getComponentsPatch()))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(ModResourceItems.KHAZAD_STEEL_NUGGET)
                .input(Items.LEATHER)
                .input(ModResourceItems.KHAZAD_STEEL_NUGGET)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.SHIELD_PLATE),
                        NeoForgeRecipeProvider.has(ModResourceItems.SHIELD_PLATE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.EREBOR_TOWER_SHIELD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.EREBOR_REINFORCED_TOWER_SHIELD.getDefaultInstance(), "heavy_shield", Disposition.GOOD)
                .input(ModResourceItems.KHAZAD_STEEL_NUGGET)
                .input(ModResourceItems.KHAZAD_STEEL_INGOT)
                .input(ModResourceItems.KHAZAD_STEEL_NUGGET)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelShieldPlate.getItem()), khazadSteelShieldPlate.getComponentsPatch()))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(ModResourceItems.KHAZAD_STEEL_NUGGET)
                .input(ModResourceItems.KHAZAD_STEEL_INGOT)
                .input(ModResourceItems.KHAZAD_STEEL_NUGGET)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.SHIELD_PLATE),
                        NeoForgeRecipeProvider.has(ModResourceItems.SHIELD_PLATE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.EREBOR_REINFORCED_TOWER_SHIELD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.EREBOR_ORNAMENTED_TOWER_SHIELD.getDefaultInstance(), "heavy_shield", Disposition.GOOD)
                .input(ModResourceItems.KHAZAD_STEEL_NUGGET)
                .input(Items.GOLD_NUGGET)
                .input(ModResourceItems.KHAZAD_STEEL_NUGGET)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelShieldPlate.getItem()), khazadSteelShieldPlate.getComponentsPatch()))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(ModResourceItems.KHAZAD_STEEL_NUGGET)
                .input(Items.GOLD_NUGGET)
                .input(ModResourceItems.KHAZAD_STEEL_NUGGET)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.SHIELD_PLATE),
                        NeoForgeRecipeProvider.has(ModResourceItems.SHIELD_PLATE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.EREBOR_ORNAMENTED_TOWER_SHIELD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.RAVENHILL_TOWER_SHIELD.getDefaultInstance(), "heavy_shield", Disposition.GOOD)
                .input(ModResourceItems.KHAZAD_STEEL_NUGGET)
                .input(ModResourceItems.KHAZAD_STEEL_INGOT)
                .input(ModResourceItems.KHAZAD_STEEL_NUGGET)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelShieldPlate.getItem()), khazadSteelShieldPlate.getComponentsPatch()))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(ModResourceItems.KHAZAD_STEEL_NUGGET)
                .input(Items.LEATHER)
                .input(ModResourceItems.KHAZAD_STEEL_NUGGET)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.SHIELD_PLATE),
                        NeoForgeRecipeProvider.has(ModResourceItems.SHIELD_PLATE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.RAVENHILL_TOWER_SHIELD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.RAVENHILL_REINFORCED_TOWER_SHIELD.getDefaultInstance(), "heavy_shield", Disposition.GOOD)
                .input(ModResourceItems.KHAZAD_STEEL_NUGGET)
                .input(ModResourceItems.KHAZAD_STEEL_INGOT)
                .input(ModResourceItems.KHAZAD_STEEL_NUGGET)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelShieldPlate.getItem()), khazadSteelShieldPlate.getComponentsPatch()))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(ModResourceItems.KHAZAD_STEEL_NUGGET)
                .input(ModResourceItems.KHAZAD_STEEL_INGOT)
                .input(ModResourceItems.KHAZAD_STEEL_NUGGET)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.SHIELD_PLATE),
                        NeoForgeRecipeProvider.has(ModResourceItems.SHIELD_PLATE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.RAVENHILL_REINFORCED_TOWER_SHIELD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.RAVENHILL_ORNAMENTED_TOWER_SHIELD.getDefaultInstance(), "heavy_shield", Disposition.GOOD)
                .input(ModResourceItems.KHAZAD_STEEL_NUGGET)
                .input(Items.GOLD_NUGGET)
                .input(ModResourceItems.KHAZAD_STEEL_NUGGET)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelShieldPlate.getItem()), khazadSteelShieldPlate.getComponentsPatch()))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(ModResourceItems.KHAZAD_STEEL_NUGGET)
                .input(Items.GOLD_NUGGET)
                .input(ModResourceItems.KHAZAD_STEEL_NUGGET)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.SHIELD_PLATE),
                        NeoForgeRecipeProvider.has(ModResourceItems.SHIELD_PLATE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.RAVENHILL_ORNAMENTED_TOWER_SHIELD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.MORDOR_WOODEN_SHIELD.getDefaultInstance(), "light_shield", Disposition.EVIL)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(ModResourceItems.CRUDE_INGOT)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.CRUDE_INGOT),
                        NeoForgeRecipeProvider.has(ModResourceItems.CRUDE_INGOT))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.MORDOR_WOODEN_SHIELD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.MORDOR_PAINTED_WOODEN_SHIELD.getDefaultInstance(), "light_shield", Disposition.EVIL)
                .input(ModWeaponItems.MORDOR_WOODEN_SHIELD)
                .input(ModResourceItems.MORDOR_BANNER_PATTERN)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModWeaponItems.MORDOR_WOODEN_SHIELD),
                        NeoForgeRecipeProvider.has(ModWeaponItems.MORDOR_WOODEN_SHIELD))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.MORDOR_PAINTED_WOODEN_SHIELD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.MORDOR_ROUND_SHIELD.getDefaultInstance(), "medium_shield", Disposition.EVIL)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(ModResourceItems.CRUDE_NUGGET)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(ModResourceItems.CRUDE_NUGGET)
                .componentInput(new ComponentsIngredient(Ingredient.of(crudeShieldBorder.getItem()), crudeShieldBorder.getComponentsPatch()))
                .input(ModResourceItems.CRUDE_NUGGET)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.SHIELD_BORDER),
                        NeoForgeRecipeProvider.has(ModResourceItems.SHIELD_BORDER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.MORDOR_ROUND_SHIELD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.MORDOR_PAINTED_ROUND_SHIELD.getDefaultInstance(), "medium_shield", Disposition.EVIL)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(ModResourceItems.MORDOR_BANNER_PATTERN)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(ModResourceItems.CRUDE_NUGGET)
                .componentInput(new ComponentsIngredient(Ingredient.of(crudeShieldBorder.getItem()), crudeShieldBorder.getComponentsPatch()))
                .input(ModResourceItems.CRUDE_NUGGET)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.MORDOR_BANNER_PATTERN),
                        NeoForgeRecipeProvider.has(ModResourceItems.MORDOR_BANNER_PATTERN))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.MORDOR_PAINTED_ROUND_SHIELD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.MORDOR_BLACK_ROUND_SHIELD.getDefaultInstance(), "medium_shield", Disposition.EVIL)
                .input(ModWeaponItems.MORDOR_PAINTED_ROUND_SHIELD)
                .input(Items.BLACK_DYE)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModWeaponItems.MORDOR_PAINTED_ROUND_SHIELD),
                        NeoForgeRecipeProvider.has(ModWeaponItems.MORDOR_PAINTED_ROUND_SHIELD))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.MORDOR_BLACK_ROUND_SHIELD).getPath() + "_artisan"));
        
        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.MORDOR_BRACED_SHIELD.getDefaultInstance(), "medium_shield", Disposition.EVIL)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(Items.IRON_NUGGET)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(ModResourceItems.CRUDE_NUGGET)
                .componentInput(new ComponentsIngredient(Ingredient.of(crudeShieldBorder.getItem()), crudeShieldBorder.getComponentsPatch()))
                .input(ModResourceItems.CRUDE_NUGGET)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.SHIELD_BORDER),
                        NeoForgeRecipeProvider.has(ModResourceItems.SHIELD_BORDER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.MORDOR_BRACED_SHIELD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.MORDOR_PAINTED_BRACED_SHIELD.getDefaultInstance(), "medium_shield", Disposition.EVIL)
                .input(ModWeaponItems.MORDOR_BRACED_SHIELD)
                .input(ModResourceItems.MORDOR_BANNER_PATTERN)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModWeaponItems.MORDOR_BRACED_SHIELD),
                        NeoForgeRecipeProvider.has(ModWeaponItems.MORDOR_BRACED_SHIELD))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.MORDOR_PAINTED_BRACED_SHIELD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.MORDOR_BLACK_BRACED_SHIELD.getDefaultInstance(), "medium_shield", Disposition.EVIL)
                .input(ModWeaponItems.MORDOR_PAINTED_BRACED_SHIELD)
                .input(Items.BLACK_DYE)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModWeaponItems.MORDOR_PAINTED_BRACED_SHIELD),
                        NeoForgeRecipeProvider.has(ModWeaponItems.MORDOR_PAINTED_BRACED_SHIELD))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.MORDOR_BLACK_BRACED_SHIELD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.MORDOR_LARGE_SHIELD.getDefaultInstance(), "medium_shield", Disposition.EVIL)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(Items.IRON_NUGGET)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(ModResourceItems.CRUDE_INGOT)
                .componentInput(new ComponentsIngredient(Ingredient.of(crudeShieldBorder.getItem()), crudeShieldBorder.getComponentsPatch()))
                .input(ModResourceItems.CRUDE_INGOT)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.SHIELD_BORDER),
                        NeoForgeRecipeProvider.has(ModResourceItems.SHIELD_BORDER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.MORDOR_LARGE_SHIELD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.MORDOR_PAINTED_LARGE_SHIELD.getDefaultInstance(), "medium_shield", Disposition.EVIL)
                .input(ModWeaponItems.MORDOR_LARGE_SHIELD)
                .input(ModResourceItems.MORDOR_BANNER_PATTERN)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModWeaponItems.MORDOR_LARGE_SHIELD),
                        NeoForgeRecipeProvider.has(ModWeaponItems.MORDOR_LARGE_SHIELD))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.MORDOR_PAINTED_LARGE_SHIELD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.MORDOR_BLACK_LARGE_SHIELD.getDefaultInstance(), "medium_shield", Disposition.EVIL)
                .input(ModWeaponItems.MORDOR_PAINTED_LARGE_SHIELD)
                .input(Items.BLACK_DYE)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModWeaponItems.MORDOR_PAINTED_LARGE_SHIELD),
                        NeoForgeRecipeProvider.has(ModWeaponItems.MORDOR_PAINTED_LARGE_SHIELD))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.MORDOR_BLACK_LARGE_SHIELD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.GONDORIAN_CONVERTED_SHIELD.getDefaultInstance(), "medium_shield", Disposition.EVIL)
                .input(ModWeaponItems.GONDORIAN_SHIELD)
                .input(ModResourceItems.MORDOR_BANNER_PATTERN)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModWeaponItems.GONDORIAN_SHIELD),
                        NeoForgeRecipeProvider.has(ModWeaponItems.GONDORIAN_SHIELD))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.GONDORIAN_CONVERTED_SHIELD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.MORDOR_HEAVY_SHIELD.getDefaultInstance(), "heavy_shield", Disposition.EVIL)
                .input(ModResourceItems.BURZUM_STEEL_NUGGET)
                .input(ModResourceItems.BURZUM_STEEL_NUGGET)
                .input(ModResourceItems.BURZUM_STEEL_NUGGET)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelShieldPlate.getItem()), burzumSteelShieldPlate.getComponentsPatch()))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(ModResourceItems.BURZUM_STEEL_NUGGET)
                .input(Items.LEATHER)
                .input(ModResourceItems.BURZUM_STEEL_NUGGET)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.SHIELD_PLATE),
                        NeoForgeRecipeProvider.has(ModResourceItems.SHIELD_PLATE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.MORDOR_HEAVY_SHIELD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.MORDOR_PAINTED_HEAVY_SHIELD.getDefaultInstance(), "heavy_shield", Disposition.EVIL)
                .input(ModWeaponItems.MORDOR_HEAVY_SHIELD)
                .input(ModResourceItems.MORDOR_BANNER_PATTERN)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModWeaponItems.MORDOR_HEAVY_SHIELD),
                        NeoForgeRecipeProvider.has(ModWeaponItems.MORDOR_HEAVY_SHIELD))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.MORDOR_PAINTED_HEAVY_SHIELD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.BLACK_NUMENOREAN_TOWER_SHIELD.getDefaultInstance(), "medium_shield", Disposition.EVIL)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(Items.RED_DYE)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(Items.BLACK_DYE)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironShieldBorder.getItem()), ironShieldBorder.getComponentsPatch()))
                .input(Items.BLACK_DYE)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.SHIELD_BORDER),
                        NeoForgeRecipeProvider.has(ModResourceItems.SHIELD_BORDER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.BLACK_NUMENOREAN_TOWER_SHIELD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.URUK_HAI_HEATER_SHIELD.getDefaultInstance(), "medium_shield", Disposition.EVIL)
                .input(ModResourceItems.CRUDE_NUGGET)
                .input(ModResourceItems.CRUDE_INGOT)
                .input(ModResourceItems.CRUDE_NUGGET)
                .input(ModResourceItems.CRUDE_INGOT)
                .componentInput(new ComponentsIngredient(Ingredient.of(crudeShieldBorder.getItem()), crudeShieldBorder.getComponentsPatch()))
                .input(ModResourceItems.CRUDE_INGOT)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.SHIELD_BORDER),
                        NeoForgeRecipeProvider.has(ModResourceItems.SHIELD_BORDER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.URUK_HAI_HEATER_SHIELD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.URUK_HAI_SHIELD.getDefaultInstance(), "heavy_shield", Disposition.EVIL)
                .input(ModResourceItems.BURZUM_STEEL_NUGGET)
                .input(ModResourceItems.BURZUM_STEEL_NUGGET)
                .input(ModResourceItems.BURZUM_STEEL_NUGGET)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelShieldPlate.getItem()), burzumSteelShieldPlate.getComponentsPatch()))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(ModResourceItems.BURZUM_STEEL_NUGGET)
                .input(Items.LEATHER)
                .input(ModResourceItems.BURZUM_STEEL_NUGGET)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.SHIELD_PLATE),
                        NeoForgeRecipeProvider.has(ModResourceItems.SHIELD_PLATE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.URUK_HAI_SHIELD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.URUK_HAI_WHITE_HAND_SHIELD.getDefaultInstance(), "heavy_shield", Disposition.EVIL)
                .input(ModWeaponItems.URUK_HAI_SHIELD)
                .input(ModResourceItems.ISENGARD_BANNER_PATTERN)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModWeaponItems.URUK_HAI_SHIELD),
                        NeoForgeRecipeProvider.has(ModWeaponItems.URUK_HAI_SHIELD))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.URUK_HAI_WHITE_HAND_SHIELD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.URUK_HAI_WHITE_PALMPRINT_SHIELD.getDefaultInstance(), "heavy_shield", Disposition.EVIL)
                .input(ModWeaponItems.URUK_HAI_SHIELD)
                .input(ModResourceItems.ISENGARD_BANNER_PATTERN)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModWeaponItems.URUK_HAI_SHIELD),
                        NeoForgeRecipeProvider.has(ModWeaponItems.URUK_HAI_SHIELD))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.URUK_HAI_WHITE_PALMPRINT_SHIELD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.URUK_HAI_S_RUNE_SHIELD.getDefaultInstance(), "heavy_shield", Disposition.EVIL)
                .input(ModWeaponItems.URUK_HAI_SHIELD)
                .input(ModResourceItems.ISENGARD_BANNER_PATTERN)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModWeaponItems.URUK_HAI_SHIELD),
                        NeoForgeRecipeProvider.has(ModWeaponItems.URUK_HAI_SHIELD))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.URUK_HAI_S_RUNE_SHIELD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.URUK_HAI_SIEGE_SHIELD.getDefaultInstance(), "heavy_shield", Disposition.EVIL)
                .input(ModResourceItems.BURZUM_STEEL_NUGGET)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelShieldPlate.getItem()), burzumSteelShieldPlate.getComponentsPatch()))
                .input(ModResourceItems.BURZUM_STEEL_NUGGET)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelShieldPlate.getItem()), burzumSteelShieldPlate.getComponentsPatch()))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(ModResourceItems.BURZUM_STEEL_NUGGET)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelShieldPlate.getItem()), burzumSteelShieldPlate.getComponentsPatch()))
                .input(ModResourceItems.BURZUM_STEEL_NUGGET)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.SHIELD_PLATE),
                        NeoForgeRecipeProvider.has(ModResourceItems.SHIELD_PLATE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.URUK_HAI_SIEGE_SHIELD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.GUNDABAD_WOODEN_SHIELD.getDefaultInstance(), "light_shield", Disposition.EVIL)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(ModResourceItems.CRUDE_INGOT)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.CRUDE_INGOT),
                        NeoForgeRecipeProvider.has(ModResourceItems.CRUDE_INGOT))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.GUNDABAD_WOODEN_SHIELD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.GUNDABAD_PAINTED_WOODEN_SHIELD.getDefaultInstance(), "light_shield", Disposition.EVIL)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(ModResourceItems.MISTY_MOUNTAINS_ORCS_BANNER_PATTERN)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.CRUDE_INGOT),
                        NeoForgeRecipeProvider.has(ModResourceItems.CRUDE_INGOT))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.GUNDABAD_PAINTED_WOODEN_SHIELD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.GUNDABAD_GREAT_EYE_PAINTED_WOODEN_SHIELD.getDefaultInstance(), "light_shield", Disposition.EVIL)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(ModResourceItems.MISTY_MOUNTAINS_ORCS_BANNER_PATTERN)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.CRUDE_INGOT),
                        NeoForgeRecipeProvider.has(ModResourceItems.CRUDE_INGOT))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.GUNDABAD_GREAT_EYE_PAINTED_WOODEN_SHIELD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.GUNDABAD_PEAKS_PAINTED_WOODEN_SHIELD.getDefaultInstance(), "light_shield", Disposition.EVIL)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(ModResourceItems.MISTY_MOUNTAINS_ORCS_BANNER_PATTERN)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.CRUDE_INGOT),
                        NeoForgeRecipeProvider.has(ModResourceItems.CRUDE_INGOT))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.GUNDABAD_PEAKS_PAINTED_WOODEN_SHIELD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.GUNDABAD_REINFORCED_SHIELD.getDefaultInstance(), "medium_shield", Disposition.EVIL)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(ModResourceItems.CRUDE_NUGGET)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(ModResourceItems.CRUDE_NUGGET)
                .componentInput(new ComponentsIngredient(Ingredient.of(crudeShieldBorder.getItem()), crudeShieldBorder.getComponentsPatch()))
                .input(ModResourceItems.CRUDE_INGOT)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.SHIELD_BORDER),
                        NeoForgeRecipeProvider.has(ModResourceItems.SHIELD_BORDER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.GUNDABAD_REINFORCED_SHIELD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModWeaponItems.GUNDABAD_HEAVY_SHIELD.getDefaultInstance(), "heavy_shield", Disposition.EVIL)
                .input(ModResourceItems.BURZUM_STEEL_NUGGET)
                .input(ModResourceItems.BURZUM_STEEL_NUGGET)
                .input(ModResourceItems.BURZUM_STEEL_NUGGET)
                .input(Items.LEATHER)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelShieldPlate.getItem()), burzumSteelShieldPlate.getComponentsPatch()))
                .input(Items.LEATHER)
                .input(ModResourceItems.BURZUM_STEEL_NUGGET)
                .input(Items.LEATHER)
                .input(ModResourceItems.BURZUM_STEEL_NUGGET)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.SHIELD_PLATE),
                        NeoForgeRecipeProvider.has(ModResourceItems.SHIELD_PLATE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModWeaponItems.GUNDABAD_HEAVY_SHIELD).getPath() + "_artisan"));

        //endregion

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModToolItems.PIPE.getDefaultInstance(), "pipe", Disposition.NEUTRAL)
                .input(Items.STICK)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("wooden_slabs")))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("wooden_slabs")))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.STICK),
                        NeoForgeRecipeProvider.has(Items.STICK))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModToolItems.PIPE).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModToolItems.CLAY_PIPE.getDefaultInstance(), "pipe", Disposition.NEUTRAL)
                .input(Items.STICK)
                .input(Items.TERRACOTTA)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("wooden_slabs")))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("wooden_slabs")))
                .input(Items.TERRACOTTA)
                .input(Items.TERRACOTTA)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.STICK),
                        NeoForgeRecipeProvider.has(Items.STICK))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModToolItems.CLAY_PIPE).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModToolItems.RIVERBEND_PIPE.getDefaultInstance(), "pipe", Disposition.NEUTRAL)
                .input(Items.STICK)
                .input(ModResourceItems.BRONZE_NUGGET)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("wooden_slabs")))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("wooden_slabs")))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(ModResourceItems.BRONZE_NUGGET)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.STICK),
                        NeoForgeRecipeProvider.has(Items.STICK))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModToolItems.RIVERBEND_PIPE).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModToolItems.BRIMMINGBEND_PIPE.getDefaultInstance(), "pipe", Disposition.NEUTRAL)
                .input(Items.STICK)
                .input(ModResourceItems.BRONZE_NUGGET)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("wooden_slabs")))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("wooden_slabs")))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(ModResourceItems.BRONZE_NUGGET)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.STICK),
                        NeoForgeRecipeProvider.has(Items.STICK))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModToolItems.BRIMMINGBEND_PIPE).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModToolItems.LONGBOTTOM_PIPE.getDefaultInstance(), "pipe", Disposition.NEUTRAL)
                .input(Items.STICK)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("wooden_slabs")))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("wooden_slabs")))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("planks")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.STICK),
                        NeoForgeRecipeProvider.has(Items.STICK))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModToolItems.LONGBOTTOM_PIPE).getPath() + "_artisan"));
    }

    private void createToolSet(RecipeOutput exporter, MetalTypes metal, ItemStack outputPickaxe, ItemStack outputAxe, ItemStack outputShovel, ItemStack outputHoe, Optional<MetalTypes> rodMetal, Disposition disposition) {
        createArtisanTablePickaxeRecipe(exporter, metal, outputPickaxe, rodMetal, disposition);
        createArtisanTableAxeRecipe(exporter, metal, outputAxe, false, rodMetal, disposition);
        createArtisanTableShovelRecipe(exporter, metal, outputShovel, rodMetal, disposition);
        createArtisanTableHoeRecipe(exporter, metal, outputHoe, rodMetal, disposition);
    }

    private void createArtisanTableSwordRecipe(RecipeOutput exporter, MetalTypes metal, ItemStack output, boolean noble, Disposition disposition) {
        ItemStack blade = new ItemStack(ModResourceItems.BLADE);
        blade.set(DataComponents.TRIM, new ArmorTrim(getArmorTrimMaterialsRegistry().getOrThrow(ResourceKey.create(Registries.TRIM_MATERIAL,
                getMetalIdentifier(metal))), getPattern()));

        ItemStack swordHilt = new ItemStack(ModResourceItems.SWORD_HILT);

        if (!noble) {
            swordHilt.set(DataComponents.TRIM, new ArmorTrim(getArmorTrimMaterialsRegistry().getOrThrow(ResourceKey.create(Registries.TRIM_MATERIAL,
                    getMetalIdentifier(metal))), getPattern()));
        } else {
            swordHilt.set(DataComponents.TRIM, new ArmorTrim(getArmorTrimMaterialsRegistry().getOrThrow(ResourceKey.create(Registries.TRIM_MATERIAL,
                    ResourceLocation.parse(MetalTypes.GOLD.getName()))), getPattern()));
        }

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, output, "sword", disposition)
                .componentInput(new ComponentsIngredient(Ingredient.of(blade.getItem()), blade.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(swordHilt.getItem()), swordHilt.getComponentsPatch()))
                .input(Items.STICK)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(blade.getItem()),
                        NeoForgeRecipeProvider.has(blade.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(output.getItem()).getPath() + "_artisan"));
    }

    private void createArtisanTableLongswordRecipe(RecipeOutput exporter, MetalTypes metal, ItemStack output, boolean noble, Disposition disposition) {
        ItemStack longBlade = new ItemStack(ModResourceItems.LONG_BLADE);
        longBlade.set(DataComponents.TRIM, new ArmorTrim(getArmorTrimMaterialsRegistry().getOrThrow(ResourceKey.create(Registries.TRIM_MATERIAL,
                getMetalIdentifier(metal))), getPattern()));

        ItemStack swordHilt = new ItemStack(ModResourceItems.SWORD_HILT);

        if (!noble) {
            swordHilt.set(DataComponents.TRIM, new ArmorTrim(getArmorTrimMaterialsRegistry().getOrThrow(ResourceKey.create(Registries.TRIM_MATERIAL,
                    getMetalIdentifier(metal))), getPattern()));
        } else {
            swordHilt.set(DataComponents.TRIM, new ArmorTrim(getArmorTrimMaterialsRegistry().getOrThrow(ResourceKey.create(Registries.TRIM_MATERIAL,
                    ResourceLocation.parse(MetalTypes.GOLD.getName()))), getPattern()));
        }

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, output, "sword", disposition)
                .componentInput(new ComponentsIngredient(Ingredient.of(longBlade.getItem()), longBlade.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(swordHilt.getItem()), swordHilt.getComponentsPatch()))
                .input(Items.STICK)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(longBlade.getItem()),
                        NeoForgeRecipeProvider.has(longBlade.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(output.getItem()).getPath() + "_artisan"));
    }

    private void createArtisanTableDaggerRecipe(RecipeOutput exporter, MetalTypes metal, ItemStack output, boolean noble, Disposition disposition) {
        ItemStack shortBlade = new ItemStack(ModResourceItems.SHORT_BLADE);
        shortBlade.set(DataComponents.TRIM, new ArmorTrim(getArmorTrimMaterialsRegistry().getOrThrow(ResourceKey.create(Registries.TRIM_MATERIAL,
                getMetalIdentifier(metal))), getPattern()));

        ItemStack swordHilt = new ItemStack(ModResourceItems.SWORD_HILT);

        if (!noble) {
            swordHilt.set(DataComponents.TRIM, new ArmorTrim(getArmorTrimMaterialsRegistry().getOrThrow(ResourceKey.create(Registries.TRIM_MATERIAL,
                    getMetalIdentifier(metal))), getPattern()));
        } else {
            swordHilt.set(DataComponents.TRIM, new ArmorTrim(getArmorTrimMaterialsRegistry().getOrThrow(ResourceKey.create(Registries.TRIM_MATERIAL,
                    ResourceLocation.parse(MetalTypes.GOLD.getName()))), getPattern()));
        }

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, output, "sword", disposition)
                .componentInput(new ComponentsIngredient(Ingredient.of(shortBlade.getItem()), shortBlade.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(swordHilt.getItem()), swordHilt.getComponentsPatch()))
                .input(Items.STICK)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(shortBlade.getItem()),
                        NeoForgeRecipeProvider.has(shortBlade.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(output.getItem()).getPath() + "_artisan"));
    }

    private void createArtisanTableSpearRecipe(RecipeOutput exporter, MetalTypes metal, ItemStack output, boolean noble, Disposition disposition) {
        ItemStack blade = new ItemStack(ModResourceItems.SHORT_BLADE);
        blade.set(DataComponents.TRIM, new ArmorTrim(getArmorTrimMaterialsRegistry().getOrThrow(ResourceKey.create(Registries.TRIM_MATERIAL,
                getMetalIdentifier(metal))), getPattern()));

        if (!noble) {
            ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, output, "spear", disposition)
                    .componentInput(new ComponentsIngredient(Ingredient.of(blade.getItem()), blade.getComponentsPatch()))
                    .input(Items.STICK)
                    .input(Items.STICK)
                    .unlockedBy(NeoForgeRecipeProvider.getHasName(blade.getItem()),
                            NeoForgeRecipeProvider.has(blade.getItem()))
                    .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(output.getItem()).getPath() + "_artisan"));
        } else {
            ItemStack rod = new ItemStack(ModResourceItems.ROD);
            rod.set(DataComponents.TRIM, new ArmorTrim(getArmorTrimMaterialsRegistry().getOrThrow(ResourceKey.create(Registries.TRIM_MATERIAL,
                    ResourceLocation.parse(MetalTypes.GOLD.getName()))), getPattern()));
            ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, output, "spear", disposition)
                    .componentInput(new ComponentsIngredient(Ingredient.of(blade.getItem()), blade.getComponentsPatch()))
                    .componentInput(new ComponentsIngredient(Ingredient.of(rod.getItem()), rod.getComponentsPatch()))
                    .input(Items.STICK)
                    .unlockedBy(NeoForgeRecipeProvider.getHasName(blade.getItem()),
                            NeoForgeRecipeProvider.has(blade.getItem()))
                    .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(output.getItem()).getPath() + "_artisan"));
        }
    }

    private void createArtisanTableBowRecipe(RecipeOutput exporter, ItemStack output, Disposition disposition) {
        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, output, "bow", disposition)
                .input(Items.STICK)
                .input(Items.STRING)
                .input(Items.STICK)
                .input(Items.STRING)
                .input(Items.STICK)
                .input(Items.STRING)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.STRING),
                        NeoForgeRecipeProvider.has(Items.STRING))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(output.getItem()).getPath() + "_artisan"));
    }

    private void createArtisanTableNobleBowRecipe(RecipeOutput exporter, ItemStack output, Disposition disposition) {
        ItemStack rod = new ItemStack(ModResourceItems.ROD);
        rod.set(DataComponents.TRIM, new ArmorTrim(getArmorTrimMaterialsRegistry().getOrThrow(ResourceKey.create(Registries.TRIM_MATERIAL,
                ResourceLocation.parse(MetalTypes.GOLD.getName()))), getPattern()));
        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, output, "bow", disposition)
                .input(Items.STICK)
                .input(Items.STRING)
                .componentInput(new ComponentsIngredient(Ingredient.of(rod.getItem()), rod.getComponentsPatch()))
                .input(Items.STRING)
                .input(Items.STICK)
                .input(Items.STRING)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.STRING),
                        NeoForgeRecipeProvider.has(Items.STRING))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(output.getItem()).getPath() + "_artisan"));
    }

    private void createArtisanTableLongbowRecipe(RecipeOutput exporter, ItemStack output, Disposition disposition) {
        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, output, "bow", disposition)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("wooden_fences")))
                .input(Items.STRING)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("wooden_fences")))
                .input(Items.STRING)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("wooden_fences")))
                .input(Items.STRING)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.STRING),
                        NeoForgeRecipeProvider.has(Items.STRING))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(output.getItem()).getPath() + "_artisan"));
    }

    private void createArtisanTableNobleLongbowRecipe(RecipeOutput exporter, ItemStack output, Disposition disposition) {
        ItemStack rod = new ItemStack(ModResourceItems.ROD);
        rod.set(DataComponents.TRIM, new ArmorTrim(getArmorTrimMaterialsRegistry().getOrThrow(ResourceKey.create(Registries.TRIM_MATERIAL,
                ResourceLocation.parse(MetalTypes.GOLD.getName()))), getPattern()));
        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, output, "bow", disposition)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("wooden_fences")))
                .input(Items.STRING)
                .componentInput(new ComponentsIngredient(Ingredient.of(rod.getItem()), rod.getComponentsPatch()))
                .input(Items.STRING)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.parse("wooden_fences")))
                .input(Items.STRING)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.STRING),
                        NeoForgeRecipeProvider.has(Items.STRING))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(output.getItem()).getPath() + "_artisan"));
    }

    private void createArtisanTableCrossbowRecipe(RecipeOutput exporter, ItemStack output, Disposition disposition) {
        ItemStack rod = new ItemStack(ModResourceItems.ROD);
        rod.set(DataComponents.TRIM, new ArmorTrim(getArmorTrimMaterialsRegistry().getOrThrow(ResourceKey.create(Registries.TRIM_MATERIAL,
                ResourceLocation.parse(MetalTypes.IRON.getName()))), getPattern()));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, output, "crossbow", disposition)
                .input(Items.STICK)
                .componentInput(new ComponentsIngredient(Ingredient.of(rod.getItem()), rod.getComponentsPatch()))
                .input(Items.STICK)
                .input(Items.STRING)
                .input(Blocks.TRIPWIRE_HOOK)
                .input(Items.STRING)
                .input(Items.STICK)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.STRING),
                        NeoForgeRecipeProvider.has(Items.STRING))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(output.getItem()).getPath() + "_artisan"));
    }

    private void createArtisanTableNobleCrossbowRecipe(RecipeOutput exporter, ItemStack output, Disposition disposition) {
        ItemStack rod = new ItemStack(ModResourceItems.ROD);
        rod.set(DataComponents.TRIM, new ArmorTrim(getArmorTrimMaterialsRegistry().getOrThrow(ResourceKey.create(Registries.TRIM_MATERIAL,
                ResourceLocation.parse(MetalTypes.GOLD.getName()))), getPattern()));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, output, "crossbow", disposition)
                .input(Items.STICK)
                .componentInput(new ComponentsIngredient(Ingredient.of(rod.getItem()), rod.getComponentsPatch()))
                .input(Items.STICK)
                .input(Items.STRING)
                .input(Blocks.TRIPWIRE_HOOK)
                .input(Items.STRING)
                .input(Items.STICK)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.STRING),
                        NeoForgeRecipeProvider.has(Items.STRING))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(output.getItem()).getPath() + "_artisan"));
    }

    private void createArtisanTablePickaxeRecipe(RecipeOutput exporter, MetalTypes metal, ItemStack output, Optional<MetalTypes> rodMetal, Disposition disposition) {
        ItemStack pickaxeHead = new ItemStack(ModResourceItems.PICKAXE_HEAD);
        pickaxeHead.set(DataComponents.TRIM, new ArmorTrim(getArmorTrimMaterialsRegistry().getOrThrow(ResourceKey.create(Registries.TRIM_MATERIAL,
                getMetalIdentifier(metal))), getPattern()));

        if (rodMetal.isPresent()){
            ItemStack rod = new ItemStack(ModResourceItems.ROD);
            rod.set(DataComponents.TRIM, new ArmorTrim(getArmorTrimMaterialsRegistry().getOrThrow(ResourceKey.create(Registries.TRIM_MATERIAL,
                    getMetalIdentifier(rodMetal.get()))), getPattern()));
            ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, output, "pickaxe", disposition)
                    .componentInput(new ComponentsIngredient(Ingredient.of(pickaxeHead.getItem()), pickaxeHead.getComponentsPatch()))
                    .componentInput(new ComponentsIngredient(Ingredient.of(rod.getItem()), rod.getComponentsPatch()))
                    .componentInput(new ComponentsIngredient(Ingredient.of(rod.getItem()), rod.getComponentsPatch()))
                    .unlockedBy(NeoForgeRecipeProvider.getHasName(pickaxeHead.getItem()),
                            NeoForgeRecipeProvider.has(pickaxeHead.getItem()))
                    .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(output.getItem()).getPath() + "_" + rodMetal.get().getName() + "_artisan"));
        } else {
            ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, output, "pickaxe", disposition)
                    .componentInput(new ComponentsIngredient(Ingredient.of(pickaxeHead.getItem()), pickaxeHead.getComponentsPatch()))
                    .input(Items.STICK)
                    .input(Items.STICK)
                    .unlockedBy(NeoForgeRecipeProvider.getHasName(pickaxeHead.getItem()),
                            NeoForgeRecipeProvider.has(pickaxeHead.getItem()))
                    .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(output.getItem()).getPath() + "_artisan"));
        }
    }

    private void createArtisanTableAxeRecipe(RecipeOutput exporter, MetalTypes metal, ItemStack output, boolean noble, Optional<MetalTypes> rodMetal, Disposition disposition) {
        ItemStack axeHead = new ItemStack(ModResourceItems.AXE_HEAD);
        axeHead.set(DataComponents.TRIM, new ArmorTrim(getArmorTrimMaterialsRegistry().getOrThrow(ResourceKey.create(Registries.TRIM_MATERIAL,
                getMetalIdentifier(metal))), getPattern()));

        if (!noble){
            if (rodMetal.isPresent()){
                ItemStack rod = new ItemStack(ModResourceItems.ROD);
                rod.set(DataComponents.TRIM, new ArmorTrim(getArmorTrimMaterialsRegistry().getOrThrow(ResourceKey.create(Registries.TRIM_MATERIAL,
                        getMetalIdentifier(rodMetal.get()))), getPattern()));
                ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, output, "axe", disposition)
                        .componentInput(new ComponentsIngredient(Ingredient.of(axeHead.getItem()), axeHead.getComponentsPatch()))
                        .componentInput(new ComponentsIngredient(Ingredient.of(rod.getItem()), rod.getComponentsPatch()))
                        .componentInput(new ComponentsIngredient(Ingredient.of(rod.getItem()), rod.getComponentsPatch()))
                        .unlockedBy(NeoForgeRecipeProvider.getHasName(axeHead.getItem()),
                                NeoForgeRecipeProvider.has(axeHead.getItem()))
                        .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(output.getItem()).getPath() + "_" + rodMetal.get().getName() + "_artisan"));
            } else {
                ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, output, "axe", disposition)
                        .componentInput(new ComponentsIngredient(Ingredient.of(axeHead.getItem()), axeHead.getComponentsPatch()))
                        .input(Items.STICK)
                        .input(Items.STICK)
                        .unlockedBy(NeoForgeRecipeProvider.getHasName(axeHead.getItem()),
                                NeoForgeRecipeProvider.has(axeHead.getItem()))
                        .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(output.getItem()).getPath() + "_artisan"));
            }
        } else {
            ItemStack rod = new ItemStack(ModResourceItems.ROD);
            rod.set(DataComponents.TRIM, new ArmorTrim(getArmorTrimMaterialsRegistry().getOrThrow(ResourceKey.create(Registries.TRIM_MATERIAL,
                    ResourceLocation.parse(MetalTypes.GOLD.getName()))), getPattern()));
            ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, output, "axe", disposition)
                    .componentInput(new ComponentsIngredient(Ingredient.of(axeHead.getItem()), axeHead.getComponentsPatch()))
                    .componentInput(new ComponentsIngredient(Ingredient.of(rod.getItem()), rod.getComponentsPatch()))
                    .input(Items.STICK)
                    .unlockedBy(NeoForgeRecipeProvider.getHasName(axeHead.getItem()),
                            NeoForgeRecipeProvider.has(axeHead.getItem()))
                    .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(output.getItem()).getPath() + "_artisan"));
        }
    }

    private void createArtisanTableShovelRecipe(RecipeOutput exporter, MetalTypes metal, ItemStack output, Optional<MetalTypes> rodMetal, Disposition disposition) {
        ItemStack shovelHead = new ItemStack(ModResourceItems.SHOVEL_HEAD);
        shovelHead.set(DataComponents.TRIM, new ArmorTrim(getArmorTrimMaterialsRegistry().getOrThrow(ResourceKey.create(Registries.TRIM_MATERIAL,
                getMetalIdentifier(metal))), getPattern()));

        if (rodMetal.isPresent()){
            ItemStack rod = new ItemStack(ModResourceItems.ROD);
            rod.set(DataComponents.TRIM, new ArmorTrim(getArmorTrimMaterialsRegistry().getOrThrow(ResourceKey.create(Registries.TRIM_MATERIAL,
                    getMetalIdentifier(rodMetal.get()))), getPattern()));
            ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, output, "shovel", disposition)
                    .componentInput(new ComponentsIngredient(Ingredient.of(shovelHead.getItem()), shovelHead.getComponentsPatch()))
                    .componentInput(new ComponentsIngredient(Ingredient.of(rod.getItem()), rod.getComponentsPatch()))
                    .componentInput(new ComponentsIngredient(Ingredient.of(rod.getItem()), rod.getComponentsPatch()))
                    .unlockedBy(NeoForgeRecipeProvider.getHasName(shovelHead.getItem()),
                            NeoForgeRecipeProvider.has(shovelHead.getItem()))
                    .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(output.getItem()).getPath() + "_" + rodMetal.get().getName() + "_artisan"));
        } else {
            ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, output, "shovel", disposition)
                    .componentInput(new ComponentsIngredient(Ingredient.of(shovelHead.getItem()), shovelHead.getComponentsPatch()))
                    .input(Items.STICK)
                    .input(Items.STICK)
                    .unlockedBy(NeoForgeRecipeProvider.getHasName(shovelHead.getItem()),
                            NeoForgeRecipeProvider.has(shovelHead.getItem()))
                    .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(output.getItem()).getPath() + "_artisan"));
        }
    }

    private void createArtisanTableHoeRecipe(RecipeOutput exporter, MetalTypes metal, ItemStack output, Optional<MetalTypes> rodMetal, Disposition disposition) {
        ItemStack hoeHead = new ItemStack(ModResourceItems.HOE_HEAD);
        hoeHead.set(DataComponents.TRIM, new ArmorTrim(getArmorTrimMaterialsRegistry().getOrThrow(ResourceKey.create(Registries.TRIM_MATERIAL,
                getMetalIdentifier(metal))), getPattern()));

        if (rodMetal.isPresent()){
            ItemStack rod = new ItemStack(ModResourceItems.ROD);
            rod.set(DataComponents.TRIM, new ArmorTrim(getArmorTrimMaterialsRegistry().getOrThrow(ResourceKey.create(Registries.TRIM_MATERIAL,
                    getMetalIdentifier(rodMetal.get()))), getPattern()));
            ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, output, "hoe", disposition)
                    .componentInput(new ComponentsIngredient(Ingredient.of(hoeHead.getItem()), hoeHead.getComponentsPatch()))
                    .componentInput(new ComponentsIngredient(Ingredient.of(rod.getItem()), rod.getComponentsPatch()))
                    .componentInput(new ComponentsIngredient(Ingredient.of(rod.getItem()), rod.getComponentsPatch()))
                    .unlockedBy(NeoForgeRecipeProvider.getHasName(hoeHead.getItem()),
                            NeoForgeRecipeProvider.has(hoeHead.getItem()))
                    .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(output.getItem()).getPath() + "_" + rodMetal.get().getName() + "_artisan"));
        } else {
            ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, output, "hoe", disposition)
                    .componentInput(new ComponentsIngredient(Ingredient.of(hoeHead.getItem()), hoeHead.getComponentsPatch()))
                    .input(Items.STICK)
                    .input(Items.STICK)
                    .unlockedBy(NeoForgeRecipeProvider.getHasName(hoeHead.getItem()),
                            NeoForgeRecipeProvider.has(hoeHead.getItem()))
                    .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(output.getItem()).getPath() + "_artisan"));
        }
    }
}
