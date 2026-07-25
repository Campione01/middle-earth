package net.jukoz.me.datageneration;

import net.jukoz.me.compat.neoforge.api.datagen.v1.provider.NeoForgeRecipeProvider;
import net.jukoz.me.compat.neoforge.impl.recipe.ingredient.builtin.ComponentsIngredient;
import net.jukoz.me.MiddleEarth;
import net.jukoz.me.block.special.forge.MetalTypes;
import net.jukoz.me.datageneration.custom.ArtisanTableRecipeJsonBuilder;
import net.jukoz.me.item.ModEquipmentItems;
import net.jukoz.me.item.ModResourceItems;
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
import java.util.concurrent.CompletableFuture;

public class ArtisanTableArmorRecipeProvider extends RecipeProvider {

    private final CompletableFuture<HolderLookup.Provider> registryLookup;

    public ArtisanTableArmorRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
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
        ItemStack goldArmorPlate = new ItemStack(ModResourceItems.ARMOR_PLATE);
        goldArmorPlate.set(DataComponents.TRIM, new ArmorTrim(getArmorTrimMaterialsRegistry().getOrThrow(ResourceKey.create(Registries.TRIM_MATERIAL,
                ResourceLocation.parse(MetalTypes.GOLD.getName()))), getPattern()));
        
        ItemStack steelArmorPlate = new ItemStack(ModResourceItems.ARMOR_PLATE);
        steelArmorPlate.set(DataComponents.TRIM, new ArmorTrim(getArmorTrimMaterialsRegistry().getOrThrow(ResourceKey.create(Registries.TRIM_MATERIAL,
                ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, MetalTypes.STEEL.getName()))), getPattern()));
        ItemStack steelHelmetPlate = new ItemStack(ModResourceItems.HELMET_PLATE);
        steelHelmetPlate.set(DataComponents.TRIM, new ArmorTrim(getArmorTrimMaterialsRegistry().getOrThrow(ResourceKey.create(Registries.TRIM_MATERIAL,
                ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, MetalTypes.STEEL.getName()))), getPattern()));
        ItemStack steelChainmail = new ItemStack(ModResourceItems.MAIL);
        steelChainmail.set(DataComponents.TRIM, new ArmorTrim(getArmorTrimMaterialsRegistry().getOrThrow(ResourceKey.create(Registries.TRIM_MATERIAL,
                ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, MetalTypes.STEEL.getName()))), getPattern()));
        ItemStack steelScaleMail = new ItemStack(ModResourceItems.SCALE_MAIL);
        steelScaleMail.set(DataComponents.TRIM, new ArmorTrim(getArmorTrimMaterialsRegistry().getOrThrow(ResourceKey.create(Registries.TRIM_MATERIAL,
                ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, MetalTypes.STEEL.getName()))), getPattern()));

        ItemStack edhelSteelArmorPlate = new ItemStack(ModResourceItems.ARMOR_PLATE);
        edhelSteelArmorPlate.set(DataComponents.TRIM, new ArmorTrim(getArmorTrimMaterialsRegistry().getOrThrow(ResourceKey.create(Registries.TRIM_MATERIAL,
                ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, MetalTypes.EDHEL_STEEL.getName()))), getPattern()));
        ItemStack edhelSteelHelmetPlate = new ItemStack(ModResourceItems.HELMET_PLATE);
        edhelSteelHelmetPlate.set(DataComponents.TRIM, new ArmorTrim(getArmorTrimMaterialsRegistry().getOrThrow(ResourceKey.create(Registries.TRIM_MATERIAL,
                ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, MetalTypes.EDHEL_STEEL.getName()))), getPattern()));
        ItemStack edhelSteelChainmail = new ItemStack(ModResourceItems.MAIL);
        edhelSteelChainmail.set(DataComponents.TRIM, new ArmorTrim(getArmorTrimMaterialsRegistry().getOrThrow(ResourceKey.create(Registries.TRIM_MATERIAL,
                ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, MetalTypes.EDHEL_STEEL.getName()))), getPattern()));
        ItemStack edhelSteelScaleMail = new ItemStack(ModResourceItems.SCALE_MAIL);
        edhelSteelScaleMail.set(DataComponents.TRIM, new ArmorTrim(getArmorTrimMaterialsRegistry().getOrThrow(ResourceKey.create(Registries.TRIM_MATERIAL,
                ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, MetalTypes.EDHEL_STEEL.getName()))), getPattern()));

        ItemStack khazadSteelArmorPlate = new ItemStack(ModResourceItems.ARMOR_PLATE);
        khazadSteelArmorPlate.set(DataComponents.TRIM, new ArmorTrim(getArmorTrimMaterialsRegistry().getOrThrow(ResourceKey.create(Registries.TRIM_MATERIAL,
                ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, MetalTypes.KHAZAD_STEEL.getName()))), getPattern()));
        ItemStack khazadSteelHelmetPlate = new ItemStack(ModResourceItems.HELMET_PLATE);
        khazadSteelHelmetPlate.set(DataComponents.TRIM, new ArmorTrim(getArmorTrimMaterialsRegistry().getOrThrow(ResourceKey.create(Registries.TRIM_MATERIAL,
                ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, MetalTypes.KHAZAD_STEEL.getName()))), getPattern()));
        ItemStack khazadSteelChainmail = new ItemStack(ModResourceItems.MAIL);
        khazadSteelChainmail.set(DataComponents.TRIM, new ArmorTrim(getArmorTrimMaterialsRegistry().getOrThrow(ResourceKey.create(Registries.TRIM_MATERIAL,
                ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, MetalTypes.KHAZAD_STEEL.getName()))), getPattern()));
        ItemStack khazadSteelScaleMail = new ItemStack(ModResourceItems.SCALE_MAIL);
        khazadSteelScaleMail.set(DataComponents.TRIM, new ArmorTrim(getArmorTrimMaterialsRegistry().getOrThrow(ResourceKey.create(Registries.TRIM_MATERIAL,
                ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, MetalTypes.KHAZAD_STEEL.getName()))), getPattern()));

        ItemStack burzumSteelArmorPlate = new ItemStack(ModResourceItems.ARMOR_PLATE);
        burzumSteelArmorPlate.set(DataComponents.TRIM, new ArmorTrim(getArmorTrimMaterialsRegistry().getOrThrow(ResourceKey.create(Registries.TRIM_MATERIAL,
                ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, MetalTypes.BURZUM_STEEL.getName()))), getPattern()));
        ItemStack burzumSteelHelmetPlate = new ItemStack(ModResourceItems.HELMET_PLATE);
        burzumSteelHelmetPlate.set(DataComponents.TRIM, new ArmorTrim(getArmorTrimMaterialsRegistry().getOrThrow(ResourceKey.create(Registries.TRIM_MATERIAL,
                ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, MetalTypes.BURZUM_STEEL.getName()))), getPattern()));
        ItemStack burzumSteelChainmail = new ItemStack(ModResourceItems.MAIL);
        burzumSteelChainmail.set(DataComponents.TRIM, new ArmorTrim(getArmorTrimMaterialsRegistry().getOrThrow(ResourceKey.create(Registries.TRIM_MATERIAL,
                ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, MetalTypes.BURZUM_STEEL.getName()))), getPattern()));
        ItemStack burzumSteelScaleMail = new ItemStack(ModResourceItems.SCALE_MAIL);
        burzumSteelScaleMail.set(DataComponents.TRIM, new ArmorTrim(getArmorTrimMaterialsRegistry().getOrThrow(ResourceKey.create(Registries.TRIM_MATERIAL,
                ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, MetalTypes.BURZUM_STEEL.getName()))), getPattern()));

        ItemStack ironArmorPlate = new ItemStack(ModResourceItems.ARMOR_PLATE);
        ironArmorPlate.set(DataComponents.TRIM, new ArmorTrim(getArmorTrimMaterialsRegistry().getOrThrow(ResourceKey.create(Registries.TRIM_MATERIAL,
                ResourceLocation.parse(MetalTypes.IRON.getName()))), getPattern()));
        ItemStack ironHelmetPlate = new ItemStack(ModResourceItems.HELMET_PLATE);
        ironHelmetPlate.set(DataComponents.TRIM, new ArmorTrim(getArmorTrimMaterialsRegistry().getOrThrow(ResourceKey.create(Registries.TRIM_MATERIAL,
                ResourceLocation.parse(MetalTypes.IRON.getName()))), getPattern()));
        ItemStack ironChainmail = new ItemStack(ModResourceItems.MAIL);
        ironChainmail.set(DataComponents.TRIM, new ArmorTrim(getArmorTrimMaterialsRegistry().getOrThrow(ResourceKey.create(Registries.TRIM_MATERIAL,
                ResourceLocation.parse(MetalTypes.IRON.getName()))), getPattern()));

        ItemStack bronzeArmorPlate = new ItemStack(ModResourceItems.ARMOR_PLATE);
        bronzeArmorPlate.set(DataComponents.TRIM, new ArmorTrim(getArmorTrimMaterialsRegistry().getOrThrow(ResourceKey.create(Registries.TRIM_MATERIAL,
                ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, MetalTypes.BRONZE.getName()))), getPattern()));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.SHIRRIFF_HAT.getDefaultInstance(), "hat", Disposition.GOOD)
                .input(Items.STRING)
                .input(Items.LEATHER)
                .input(Items.FEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.LEATHER),
                        NeoForgeRecipeProvider.has(Items.LEATHER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.SHIRRIFF_HAT).getPath() + "_artisan"));

        //region MEN

        //region GENERIC

        //T1
        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.STRAW_HAT.getDefaultInstance(), "hat", Disposition.NEUTRAL)
                .input(Items.STRING)
                .input(ModResourceItems.STRAW)
                .input(Items.STRING)
                .input(ModResourceItems.STRAW)
                .input(ModResourceItems.STRAW)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.STRAW),
                        NeoForgeRecipeProvider.has(ModResourceItems.STRAW))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.STRAW_HAT).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.WOVEN_HAT.getDefaultInstance(), "hat", Disposition.NEUTRAL)
                .input(Items.STRING)
                .input(Items.WHITE_WOOL)
                .input(Items.STRING)
                .input(Items.STRING)
                .input(Items.STRING)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.WHITE_WOOL),
                        NeoForgeRecipeProvider.has(Items.WHITE_WOOL))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.WOVEN_HAT).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.BYCOCKET.getDefaultInstance(), "hat", Disposition.NEUTRAL)
                .input(Items.LEATHER)
                .input(Items.WHITE_WOOL)
                .input(Items.LEATHER)
                .input(Items.STRING)
                .input(Items.STRING)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.WHITE_WOOL),
                        NeoForgeRecipeProvider.has(Items.WHITE_WOOL))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.BYCOCKET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.WANDERER_HAT.getDefaultInstance(), "hat", Disposition.NEUTRAL)
                .input(ModResourceItems.FABRIC)
                .input(Items.LEATHER)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.FABRIC),
                        NeoForgeRecipeProvider.has(ModResourceItems.FABRIC))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.WANDERER_HAT).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.ARMING_COAT.getDefaultInstance(), "chestplate", Disposition.NEUTRAL)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.FABRIC),
                        NeoForgeRecipeProvider.has(ModResourceItems.FABRIC))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.ARMING_COAT).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.ARMING_SKIRT.getDefaultInstance(), "leggings", Disposition.NEUTRAL)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.FABRIC),
                        NeoForgeRecipeProvider.has(ModResourceItems.FABRIC))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.ARMING_SKIRT).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.SHOES.getDefaultInstance(), "boots", Disposition.NEUTRAL)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.FABRIC),
                        NeoForgeRecipeProvider.has(ModResourceItems.FABRIC))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.SHOES).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.WORK_SHOES.getDefaultInstance(), "boots", Disposition.NEUTRAL)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.FABRIC),
                        NeoForgeRecipeProvider.has(ModResourceItems.FABRIC))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.WORK_SHOES).getPath() + "_artisan"));

        //T2
        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.LEATHER_SKULLCAP.getDefaultInstance(), "helmet", Disposition.NEUTRAL)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.LEATHER),
                        NeoForgeRecipeProvider.has(Items.LEATHER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.LEATHER_SKULLCAP).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GAMBESON_CAP.getDefaultInstance(), "helmet", Disposition.NEUTRAL)
                .input(Items.LEATHER)
                .input(Items.WHITE_WOOL)
                .input(Items.LEATHER)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.LEATHER),
                        NeoForgeRecipeProvider.has(Items.LEATHER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GAMBESON_CAP).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GAMBESON_COWL.getDefaultInstance(), "helmet", Disposition.NEUTRAL)
                .input(Items.LEATHER)
                .input(Items.WHITE_WOOL)
                .input(Items.LEATHER)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.LEATHER),
                        NeoForgeRecipeProvider.has(Items.LEATHER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GAMBESON_COWL).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.KETTLE_HAT.getDefaultInstance(), "helmet", Disposition.NEUTRAL)
                .input(Items.IRON_NUGGET)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironArmorPlate.getItem()), ironArmorPlate.getComponentsPatch()))
                .input(Items.IRON_NUGGET)
                .input(Items.IRON_NUGGET)
                .input(Items.IRON_NUGGET)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ironArmorPlate.getItem()),
                        NeoForgeRecipeProvider.has(ironArmorPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.KETTLE_HAT).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GAMBESON.getDefaultInstance(), "chestplate", Disposition.NEUTRAL)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(Items.LEATHER)
                .input(Items.WHITE_WOOL)
                .input(Items.LEATHER)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.LEATHER),
                        NeoForgeRecipeProvider.has(Items.LEATHER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GAMBESON).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.LEATHER_VEST.getDefaultInstance(), "chestplate", Disposition.NEUTRAL)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.LEATHER),
                        NeoForgeRecipeProvider.has(Items.LEATHER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.LEATHER_VEST).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.LEATHER_SCALE_VEST.getDefaultInstance(), "chestplate", Disposition.NEUTRAL)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.LEATHER),
                        NeoForgeRecipeProvider.has(Items.LEATHER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.LEATHER_SCALE_VEST).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.STURDY_BOOTS.getDefaultInstance(), "boots", Disposition.NEUTRAL)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.LEATHER),
                        NeoForgeRecipeProvider.has(Items.LEATHER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.STURDY_BOOTS).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.TRAVELLING_BOOTS.getDefaultInstance(), "boots", Disposition.NEUTRAL)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.LEATHER),
                        NeoForgeRecipeProvider.has(Items.LEATHER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.TRAVELLING_BOOTS).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.HIGH_CUT_BOOTS.getDefaultInstance(), "boots", Disposition.NEUTRAL)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.LEATHER),
                        NeoForgeRecipeProvider.has(Items.LEATHER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.HIGH_CUT_BOOTS).getPath() + "_artisan"));
        //T3
        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.MAIL_COIF.getDefaultInstance(), "helmet", Disposition.NEUTRAL)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ironChainmail.getItem()),
                        NeoForgeRecipeProvider.has(ironChainmail.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.MAIL_COIF).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.CLOSED_MAIL_COIF.getDefaultInstance(), "helmet", Disposition.NEUTRAL)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ironChainmail.getItem()),
                        NeoForgeRecipeProvider.has(ironChainmail.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.CLOSED_MAIL_COIF).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.KETTLE_HAT_WITH_COIF.getDefaultInstance(), "helmet", Disposition.NEUTRAL)
                .input(ModEquipmentItems.MAIL_COIF)
                .input(ModEquipmentItems.KETTLE_HAT)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.MAIL_COIF),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.MAIL_COIF))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.KETTLE_HAT_WITH_COIF).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.KETTLE_HAT_WITH_CLOSED_COIF.getDefaultInstance(), "helmet", Disposition.NEUTRAL)
                .input(ModEquipmentItems.CLOSED_MAIL_COIF)
                .input(ModEquipmentItems.KETTLE_HAT)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.CLOSED_MAIL_COIF),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.CLOSED_MAIL_COIF))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.KETTLE_HAT_WITH_CLOSED_COIF).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.OPEN_FACE_HELMET.getDefaultInstance(), "helmet", Disposition.NEUTRAL)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironArmorPlate.getItem()), ironArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironHelmetPlate.getItem()), ironHelmetPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironArmorPlate.getItem()), ironArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ironChainmail.getItem()),
                        NeoForgeRecipeProvider.has(ironChainmail.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.OPEN_FACE_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.MAIL_HAUBERK.getDefaultInstance(), "chestplate", Disposition.NEUTRAL)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ironChainmail.getItem()),
                        NeoForgeRecipeProvider.has(ironChainmail.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.MAIL_HAUBERK).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.MAIL_SHIRT.getDefaultInstance(), "chestplate", Disposition.NEUTRAL)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ironChainmail.getItem()),
                        NeoForgeRecipeProvider.has(ironChainmail.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.MAIL_SHIRT).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.MAIL_SKIRT.getDefaultInstance(), "leggings", Disposition.NEUTRAL)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ironChainmail.getItem()),
                        NeoForgeRecipeProvider.has(ironChainmail.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.MAIL_SKIRT).getPath() + "_artisan"));

        //T4
        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.SALLET.getDefaultInstance(), "helmet", Disposition.NEUTRAL)
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelHelmetPlate.getItem()), steelHelmetPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelChainmail.getItem()), steelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelChainmail.getItem()), steelChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(steelHelmetPlate.getItem()),
                        NeoForgeRecipeProvider.has(steelHelmetPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.SALLET).getPath() + "_artisan"));

        //HOODS
        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.HOOD.getDefaultInstance(), "hood", Disposition.NEUTRAL)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(Items.STRING)
                .input(ModResourceItems.FABRIC)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.FABRIC),
                        NeoForgeRecipeProvider.has(ModResourceItems.FABRIC))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.HOOD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.TALL_HOOD.getDefaultInstance(), "hood", Disposition.NEUTRAL)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(Items.STRING)
                .input(ModResourceItems.FABRIC)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.FABRIC),
                        NeoForgeRecipeProvider.has(ModResourceItems.FABRIC))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.TALL_HOOD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.BLACK_FUR_HOOD.getDefaultInstance(), "hood", Disposition.NEUTRAL)
                .input(ModResourceItems.FUR)
                .input(ModEquipmentItems.HOOD)
                .input(ModResourceItems.FUR)
                .input(ModResourceItems.FUR)
                .input(Items.STRING)
                .input(ModResourceItems.FUR)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.HOOD),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.HOOD))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.BLACK_FUR_HOOD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.BROWN_FUR_HOOD.getDefaultInstance(), "hood", Disposition.NEUTRAL)
                .input(ModResourceItems.FUR)
                .input(ModEquipmentItems.HOOD)
                .input(ModResourceItems.FUR)
                .input(ModResourceItems.FUR)
                .input(Items.STRING)
                .input(ModResourceItems.FUR)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.HOOD),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.HOOD))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.BROWN_FUR_HOOD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GRAY_FUR_HOOD.getDefaultInstance(), "hood", Disposition.NEUTRAL)
                .input(ModResourceItems.FUR)
                .input(ModEquipmentItems.HOOD)
                .input(ModResourceItems.FUR)
                .input(ModResourceItems.FUR)
                .input(Items.STRING)
                .input(ModResourceItems.FUR)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.HOOD),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.HOOD))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GRAY_FUR_HOOD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.TAN_FUR_HOOD.getDefaultInstance(), "hood", Disposition.NEUTRAL)
                .input(ModResourceItems.FUR)
                .input(ModEquipmentItems.HOOD)
                .input(ModResourceItems.FUR)
                .input(ModResourceItems.FUR)
                .input(Items.STRING)
                .input(ModResourceItems.FUR)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.HOOD),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.HOOD))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.TAN_FUR_HOOD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.WHITE_FUR_HOOD.getDefaultInstance(), "hood", Disposition.NEUTRAL)
                .input(ModResourceItems.FUR)
                .input(ModEquipmentItems.HOOD)
                .input(ModResourceItems.FUR)
                .input(ModResourceItems.FUR)
                .input(Items.STRING)
                .input(ModResourceItems.FUR)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.HOOD),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.HOOD))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.WHITE_FUR_HOOD).getPath() + "_artisan"));

        //CAPES
        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.CAPE.getDefaultInstance(), "cape", Disposition.NEUTRAL)
                .input(ModResourceItems.FABRIC)
                .input(Items.STRING)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.FABRIC),
                        NeoForgeRecipeProvider.has(ModResourceItems.FABRIC))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.CAPE).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.SURCOAT.getDefaultInstance(), "cape", Disposition.NEUTRAL)
                .input(ModResourceItems.FABRIC)
                .input(Items.STRING)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(Items.LEATHER)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(Items.STRING)
                .input(ModResourceItems.FABRIC)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.FABRIC),
                        NeoForgeRecipeProvider.has(ModResourceItems.FABRIC))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.SURCOAT).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.CLOAK.getDefaultInstance(), "cape", Disposition.NEUTRAL)
                .input(ModResourceItems.FABRIC)
                .input(Items.STRING)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.FABRIC),
                        NeoForgeRecipeProvider.has(ModResourceItems.FABRIC))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.CLOAK).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.WANDERER_ROBES.getDefaultInstance(), "cape", Disposition.NEUTRAL)
                .input(ModResourceItems.FABRIC)
                .input(Items.LEATHER)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(Items.LEATHER)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.FABRIC),
                        NeoForgeRecipeProvider.has(ModResourceItems.FABRIC))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.WANDERER_ROBES).getPath() + "_artisan"));


        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.BLACK_FUR_CLOAK.getDefaultInstance(), "cape", Disposition.NEUTRAL)
                .input(Items.STRING)
                .input(ModEquipmentItems.BLACK_FUR)
                .input(Items.STRING)
                .input(Items.STRING)
                .input(ModEquipmentItems.CLOAK)
                .input(Items.STRING)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.CLOAK),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.CLOAK))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.BLACK_FUR_CLOAK).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.BROWN_FUR_CLOAK.getDefaultInstance(), "cape", Disposition.NEUTRAL)
                .input(Items.STRING)
                .input(ModEquipmentItems.BROWN_FUR)
                .input(Items.STRING)
                .input(Items.STRING)
                .input(ModEquipmentItems.CLOAK)
                .input(Items.STRING)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.CLOAK),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.CLOAK))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.BROWN_FUR_CLOAK).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GRAY_FUR_CLOAK.getDefaultInstance(), "cape", Disposition.NEUTRAL)
                .input(Items.STRING)
                .input(ModEquipmentItems.GRAY_FUR)
                .input(Items.STRING)
                .input(Items.STRING)
                .input(ModEquipmentItems.CLOAK)
                .input(Items.STRING)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.CLOAK),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.CLOAK))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GRAY_FUR_CLOAK).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.TAN_FUR_CLOAK.getDefaultInstance(), "cape", Disposition.NEUTRAL)
                .input(Items.STRING)
                .input(ModEquipmentItems.TAN_FUR)
                .input(Items.STRING)
                .input(Items.STRING)
                .input(ModEquipmentItems.CLOAK)
                .input(Items.STRING)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.CLOAK),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.CLOAK))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.TAN_FUR_CLOAK).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.WHITE_FUR_CLOAK.getDefaultInstance(), "cape", Disposition.NEUTRAL)
                .input(Items.STRING)
                .input(ModEquipmentItems.WHITE_FUR)
                .input(Items.STRING)
                .input(Items.STRING)
                .input(ModEquipmentItems.CLOAK)
                .input(Items.STRING)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.CLOAK),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.CLOAK))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.WHITE_FUR_CLOAK).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.BLACK_FUR.getDefaultInstance(), "cape", Disposition.NEUTRAL)
                .input(ModResourceItems.FUR)
                .input(ModResourceItems.FUR)
                .input(ModResourceItems.FUR)
                .input(ModResourceItems.FUR)
                .input(Items.STRING)
                .input(ModResourceItems.FUR)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.FABRIC),
                        NeoForgeRecipeProvider.has(ModResourceItems.FABRIC))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.BLACK_FUR).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.BROWN_FUR.getDefaultInstance(), "cape", Disposition.NEUTRAL)
                .input(ModResourceItems.FUR)
                .input(ModResourceItems.FUR)
                .input(ModResourceItems.FUR)
                .input(ModResourceItems.FUR)
                .input(Items.STRING)
                .input(ModResourceItems.FUR)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.FABRIC),
                        NeoForgeRecipeProvider.has(ModResourceItems.FABRIC))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.BROWN_FUR).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GRAY_FUR.getDefaultInstance(), "cape", Disposition.NEUTRAL)
                .input(ModResourceItems.FUR)
                .input(ModResourceItems.FUR)
                .input(ModResourceItems.FUR)
                .input(ModResourceItems.FUR)
                .input(Items.STRING)
                .input(ModResourceItems.FUR)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.FABRIC),
                        NeoForgeRecipeProvider.has(ModResourceItems.FABRIC))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GRAY_FUR).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.TAN_FUR.getDefaultInstance(), "cape", Disposition.NEUTRAL)
                .input(ModResourceItems.FUR)
                .input(ModResourceItems.FUR)
                .input(ModResourceItems.FUR)
                .input(ModResourceItems.FUR)
                .input(Items.STRING)
                .input(ModResourceItems.FUR)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.FABRIC),
                        NeoForgeRecipeProvider.has(ModResourceItems.FABRIC))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.TAN_FUR).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.WHITE_FUR.getDefaultInstance(), "cape", Disposition.NEUTRAL)
                .input(ModResourceItems.FUR)
                .input(ModResourceItems.FUR)
                .input(ModResourceItems.FUR)
                .input(ModResourceItems.FUR)
                .input(Items.STRING)
                .input(ModResourceItems.FUR)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.FABRIC),
                        NeoForgeRecipeProvider.has(ModResourceItems.FABRIC))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.WHITE_FUR).getPath() + "_artisan"));
        //endregion

        //region GONDOR
        //T2
        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GONDORIAN_BOOTS.getDefaultInstance(), "boots", Disposition.GOOD)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.LEATHER),
                        NeoForgeRecipeProvider.has(Items.LEATHER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GONDORIAN_BOOTS).getPath() + "_artisan"));

        //T3
        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GONDORIAN_CABASSET_HELMET.getDefaultInstance(), "helmet", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironArmorPlate.getItem()), ironArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironHelmetPlate.getItem()), ironHelmetPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironArmorPlate.getItem()), ironArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ironHelmetPlate.getItem()),
                        NeoForgeRecipeProvider.has(ironHelmetPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GONDORIAN_CABASSET_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GONDORIAN_MAIL_HAUBERK.getDefaultInstance(), "chestplate", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ironChainmail.getItem()),
                        NeoForgeRecipeProvider.has(ironChainmail.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GONDORIAN_MAIL_HAUBERK).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GONDORIAN_LEATHER_CHESTPLATE.getDefaultInstance(), "chestplate", Disposition.GOOD)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(ModEquipmentItems.GONDORIAN_MAIL_HAUBERK)
                .input(Items.LEATHER)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironArmorPlate.getItem()), ironArmorPlate.getComponentsPatch()))
                .input(Items.LEATHER)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironArmorPlate.getItem()), ironArmorPlate.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.GONDORIAN_MAIL_HAUBERK),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.GONDORIAN_MAIL_HAUBERK))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GONDORIAN_LEATHER_CHESTPLATE).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GONDORIAN_LEATHER_CUIRASS.getDefaultInstance(), "chestplate", Disposition.GOOD)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(ModEquipmentItems.GONDORIAN_MAIL_HAUBERK)
                .input(Items.LEATHER)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironArmorPlate.getItem()), ironArmorPlate.getComponentsPatch()))
                .input(Items.LEATHER)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironArmorPlate.getItem()), ironArmorPlate.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.GONDORIAN_MAIL_HAUBERK),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.GONDORIAN_MAIL_HAUBERK))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GONDORIAN_LEATHER_CUIRASS).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GONDORIAN_TABBARD.getDefaultInstance(), "chestplate", Disposition.GOOD)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModEquipmentItems.GONDORIAN_MAIL_HAUBERK)
                .input(ModResourceItems.FABRIC)
                .input(Items.LEATHER)
                .input(ModResourceItems.FABRIC)
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.GONDORIAN_MAIL_HAUBERK),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.GONDORIAN_MAIL_HAUBERK))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GONDORIAN_TABBARD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GONDORIAN_MAIL_COAT.getDefaultInstance(), "leggings", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ironChainmail.getItem()),
                        NeoForgeRecipeProvider.has(ironChainmail.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GONDORIAN_MAIL_COAT).getPath() + "_artisan"));

        //T4
        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GONDORIAN_SOLDIER_HELMET.getDefaultInstance(), "helmet", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelHelmetPlate.getItem()), steelHelmetPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelChainmail.getItem()), steelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelChainmail.getItem()), steelChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(steelHelmetPlate.getItem()),
                        NeoForgeRecipeProvider.has(steelHelmetPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GONDORIAN_SOLDIER_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GONDORIAN_SOLDIER_CHESTPLATE.getDefaultInstance(), "chestplate", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .input(ModResourceItems.FABRIC)
                .input(ModEquipmentItems.GONDORIAN_MAIL_HAUBERK)
                .input(ModResourceItems.FABRIC)
                .componentInput(new ComponentsIngredient(Ingredient.of(steelChainmail.getItem()), steelChainmail.getComponentsPatch()))
                .input(ModResourceItems.FABRIC)
                .componentInput(new ComponentsIngredient(Ingredient.of(steelChainmail.getItem()), steelChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.GONDORIAN_MAIL_HAUBERK),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.GONDORIAN_MAIL_HAUBERK))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GONDORIAN_SOLDIER_CHESTPLATE).getPath() + "_artisan"));

        //T5 PLATE
        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GONDORIAN_PLATE_HELMET.getDefaultInstance(), "helmet", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelHelmetPlate.getItem()), steelHelmetPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(steelHelmetPlate.getItem()),
                        NeoForgeRecipeProvider.has(steelHelmetPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GONDORIAN_PLATE_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GONDORIAN_PLATE_CHESTPLATE.getDefaultInstance(), "chestplate", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelChainmail.getItem()), steelChainmail.getComponentsPatch()))
                .input(ModEquipmentItems.GONDORIAN_SOLDIER_CHESTPLATE)
                .componentInput(new ComponentsIngredient(Ingredient.of(steelChainmail.getItem()), steelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelChainmail.getItem()), steelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelChainmail.getItem()), steelChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.GONDORIAN_SOLDIER_CHESTPLATE),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.GONDORIAN_SOLDIER_CHESTPLATE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GONDORIAN_PLATE_CHESTPLATE).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GONDORIAN_PLATE_LEGGINGS.getDefaultInstance(), "leggings", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .input(ModEquipmentItems.GONDORIAN_MAIL_COAT)
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelChainmail.getItem()), steelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelChainmail.getItem()), steelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelChainmail.getItem()), steelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelChainmail.getItem()), steelChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.GONDORIAN_MAIL_COAT),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.GONDORIAN_MAIL_COAT))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GONDORIAN_PLATE_LEGGINGS).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GONDORIAN_PLATE_BOOTS.getDefaultInstance(), "boots", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(steelArmorPlate.getItem()),
                        NeoForgeRecipeProvider.has(steelArmorPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GONDORIAN_PLATE_BOOTS).getPath() + "_artisan"));

        //T5 CAPTAIN
        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GONDORIAN_CAPTAIN_HELMET.getDefaultInstance(), "helmet", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(goldArmorPlate.getItem()), goldArmorPlate.getComponentsPatch()))
                .input(ModEquipmentItems.GONDORIAN_PLATE_HELMET)
                .componentInput(new ComponentsIngredient(Ingredient.of(goldArmorPlate.getItem()), goldArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelChainmail.getItem()), steelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelChainmail.getItem()), steelChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.GONDORIAN_PLATE_HELMET),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.GONDORIAN_PLATE_HELMET))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GONDORIAN_CAPTAIN_HELMET).getPath() + "_artisan"));

        //T5 KING'S GUARD
        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GONDORIAN_KINGS_GUARD_HELMET.getDefaultInstance(), "helmet", Disposition.GOOD)
                .input(Items.FEATHER)
                .input(ModEquipmentItems.GONDORIAN_PLATE_HELMET)
                .input(Items.FEATHER)
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.GONDORIAN_PLATE_HELMET),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.GONDORIAN_PLATE_HELMET))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GONDORIAN_KINGS_GUARD_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GONDORIAN_KINGS_GUARD_CHESTKPLATE.getDefaultInstance(), "chestplate", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(goldArmorPlate.getItem()), goldArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(goldArmorPlate.getItem()), goldArmorPlate.getComponentsPatch()))
                .input(ModResourceItems.FABRIC)
                .input(ModEquipmentItems.GONDORIAN_PLATE_CHESTPLATE)
                .input(ModResourceItems.FABRIC)
                .componentInput(new ComponentsIngredient(Ingredient.of(steelChainmail.getItem()), steelChainmail.getComponentsPatch()))
                .input(ModResourceItems.FABRIC)
                .componentInput(new ComponentsIngredient(Ingredient.of(steelChainmail.getItem()), steelChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.GONDORIAN_PLATE_CHESTPLATE),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.GONDORIAN_PLATE_CHESTPLATE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GONDORIAN_KINGS_GUARD_CHESTKPLATE).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GONDORIAN_KINGS_GUARD_LEGGINGS.getDefaultInstance(), "leggings", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(steelChainmail.getItem()), steelChainmail.getComponentsPatch()))
                .input(ModEquipmentItems.GONDORIAN_PLATE_LEGGINGS)
                .componentInput(new ComponentsIngredient(Ingredient.of(steelChainmail.getItem()), steelChainmail.getComponentsPatch()))
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.GONDORIAN_PLATE_LEGGINGS),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.GONDORIAN_PLATE_LEGGINGS))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GONDORIAN_KINGS_GUARD_LEGGINGS).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GONDORIAN_KINGS_GUARD_BOOTS.getDefaultInstance(), "boots", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(goldArmorPlate.getItem()), goldArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(goldArmorPlate.getItem()), goldArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(goldArmorPlate.getItem()),
                        NeoForgeRecipeProvider.has(goldArmorPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GONDORIAN_KINGS_GUARD_BOOTS).getPath() + "_artisan"));

        //T5 CITADEL GUARD
        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GONDORIAN_CITADEL_GUARD_HELMET.getDefaultInstance(), "helmet", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(goldArmorPlate.getItem()), goldArmorPlate.getComponentsPatch()))
                .input(ModEquipmentItems.GONDORIAN_PLATE_HELMET)
                .componentInput(new ComponentsIngredient(Ingredient.of(goldArmorPlate.getItem()), goldArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelChainmail.getItem()), steelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelChainmail.getItem()), steelChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.GONDORIAN_PLATE_HELMET),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.GONDORIAN_PLATE_HELMET))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GONDORIAN_CITADEL_GUARD_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GONDORIAN_CITADEL_GUARD_CHESTPLATE.getDefaultInstance(), "chestplate", Disposition.GOOD)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModEquipmentItems.GONDORIAN_PLATE_CHESTPLATE)
                .input(ModResourceItems.FABRIC)
                .componentInput(new ComponentsIngredient(Ingredient.of(steelChainmail.getItem()), steelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(goldArmorPlate.getItem()), goldArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelChainmail.getItem()), steelChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.GONDORIAN_PLATE_CHESTPLATE),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.GONDORIAN_PLATE_CHESTPLATE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GONDORIAN_CITADEL_GUARD_CHESTPLATE).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GONDORIAN_CITADEL_GUARD_LEGGINGS.getDefaultInstance(), "leggings", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(steelChainmail.getItem()), steelChainmail.getComponentsPatch()))
                .input(ModEquipmentItems.GONDORIAN_PLATE_LEGGINGS)
                .componentInput(new ComponentsIngredient(Ingredient.of(steelChainmail.getItem()), steelChainmail.getComponentsPatch()))
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.GONDORIAN_PLATE_LEGGINGS),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.GONDORIAN_PLATE_LEGGINGS))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GONDORIAN_CITADEL_GUARD_LEGGINGS).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GONDORIAN_CITADEL_GUARD_BOOTS.getDefaultInstance(), "boots", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(goldArmorPlate.getItem()), goldArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(goldArmorPlate.getItem()), goldArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(goldArmorPlate.getItem()),
                        NeoForgeRecipeProvider.has(goldArmorPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GONDORIAN_CITADEL_GUARD_BOOTS).getPath() + "_artisan"));
        
        //T5 FOUNTAIN GUARD
        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GONDORIAN_FOUNTAIN_GUARD_HELMET.getDefaultInstance(), "helmet", Disposition.GOOD)
                .input(ModResourceItems.MITHRIL_NUGGET)
                .input(ModEquipmentItems.GONDORIAN_CAPTAIN_HELMET)
                .input(ModResourceItems.MITHRIL_NUGGET)
                .input(ModResourceItems.SWAN_FEATHER)
                .input(ModResourceItems.SWAN_FEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.GONDORIAN_CAPTAIN_HELMET),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.GONDORIAN_CAPTAIN_HELMET))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GONDORIAN_FOUNTAIN_GUARD_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GONDORIAN_FOUNTAIN_GUARD_CHESTPLATE.getDefaultInstance(), "chestplate", Disposition.GOOD)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModEquipmentItems.GONDORIAN_PLATE_CHESTPLATE)
                .input(ModResourceItems.FABRIC)
                .componentInput(new ComponentsIngredient(Ingredient.of(steelChainmail.getItem()), steelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(goldArmorPlate.getItem()), goldArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelChainmail.getItem()), steelChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.GONDORIAN_PLATE_CHESTPLATE),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.GONDORIAN_PLATE_CHESTPLATE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GONDORIAN_FOUNTAIN_GUARD_CHESTPLATE).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GONDORIAN_FOUNTAIN_GUARD_LEGGINGS.getDefaultInstance(), "leggings", Disposition.GOOD)
                .input(ModResourceItems.FABRIC)
                .input(ModEquipmentItems.GONDORIAN_PLATE_LEGGINGS)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .componentInput(new ComponentsIngredient(Ingredient.of(steelChainmail.getItem()), steelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelChainmail.getItem()), steelChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.GONDORIAN_PLATE_LEGGINGS),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.GONDORIAN_PLATE_LEGGINGS))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GONDORIAN_FOUNTAIN_GUARD_LEGGINGS).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GONDORIAN_FOUNTAIN_GUARD_BOOTS.getDefaultInstance(), "boots", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(goldArmorPlate.getItem()), goldArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(goldArmorPlate.getItem()), goldArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(goldArmorPlate.getItem()),
                        NeoForgeRecipeProvider.has(goldArmorPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GONDORIAN_FOUNTAIN_GUARD_BOOTS).getPath() + "_artisan"));

        //HOODS
        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GONDORIAN_CITADEL_GUARD_HOOD.getDefaultInstance(), "hood", Disposition.GOOD)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(Items.BLACK_DYE)
                .input(ModResourceItems.FABRIC)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.FABRIC),
                        NeoForgeRecipeProvider.has(ModResourceItems.FABRIC))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GONDORIAN_CITADEL_GUARD_HOOD).getPath() + "_artisan"));

        //CAPES
        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GONDORIAN_CAPTAIN_CAPE.getDefaultInstance(), "cape", Disposition.GOOD)
                .input(ModResourceItems.FABRIC)
                .input(Items.IRON_NUGGET)
                .input(ModResourceItems.FABRIC)
                .input(Items.BLACK_DYE)
                .input(ModResourceItems.FABRIC)
                .input(Items.BLACK_DYE)
                .input(Items.WHITE_DYE)
                .input(ModResourceItems.FABRIC)
                .input(Items.WHITE_DYE)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.FABRIC),
                        NeoForgeRecipeProvider.has(ModResourceItems.FABRIC))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GONDORIAN_CAPTAIN_CAPE).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GONDORIAN_HERO_CAPE.getDefaultInstance(), "cape", Disposition.GOOD)
                .input(ModResourceItems.FABRIC)
                .input(Items.IRON_NUGGET)
                .input(ModResourceItems.FABRIC)
                .input(Items.WHITE_DYE)
                .input(ModResourceItems.FABRIC)
                .input(Items.WHITE_DYE)
                .input(Items.BLACK_DYE)
                .input(ModResourceItems.FABRIC)
                .input(Items.BLACK_DYE)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.FABRIC),
                        NeoForgeRecipeProvider.has(ModResourceItems.FABRIC))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GONDORIAN_HERO_CAPE).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GONDORIAN_KINGS_GUARD_CAPE.getDefaultInstance(), "cape", Disposition.GOOD)
                .input(ModResourceItems.FABRIC)
                .input(Items.GOLD_NUGGET)
                .input(ModResourceItems.FABRIC)
                .input(Items.BLACK_DYE)
                .input(ModResourceItems.FABRIC)
                .input(Items.BLACK_DYE)
                .input(Items.WHITE_DYE)
                .input(ModResourceItems.FABRIC)
                .input(Items.WHITE_DYE)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.FABRIC),
                        NeoForgeRecipeProvider.has(ModResourceItems.FABRIC))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GONDORIAN_KINGS_GUARD_CAPE).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GONDORIAN_CITADEL_GUARD_CAPE.getDefaultInstance(), "cape", Disposition.GOOD)
                .input(ModResourceItems.FABRIC)
                .input(Items.GOLD_NUGGET)
                .input(ModResourceItems.FABRIC)
                .input(Items.BLACK_DYE)
                .input(ModResourceItems.FABRIC)
                .input(Items.BLACK_DYE)
                .input(Items.YELLOW_DYE)
                .input(ModResourceItems.FABRIC)
                .input(Items.YELLOW_DYE)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.FABRIC),
                        NeoForgeRecipeProvider.has(ModResourceItems.FABRIC))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GONDORIAN_CITADEL_GUARD_CAPE).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GONDORIAN_FOUNTAIN_GUARD_CAPE.getDefaultInstance(), "cape", Disposition.GOOD)
                .input(ModResourceItems.FABRIC)
                .input(Items.GOLD_NUGGET)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(Items.BLACK_DYE)
                .input(ModResourceItems.FABRIC)
                .input(Items.BLACK_DYE)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.FABRIC),
                        NeoForgeRecipeProvider.has(ModResourceItems.FABRIC))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GONDORIAN_FOUNTAIN_GUARD_CAPE).getPath() + "_artisan"));
        //endregion

        //region ROHAN
        //T1
        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.ROHIRRIC_REINFORCED_COAT.getDefaultInstance(), "chestplate", Disposition.GOOD)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModEquipmentItems.ARMING_COAT)
                .input(ModResourceItems.FABRIC)
                .input(Items.LEATHER)
                .input(ModResourceItems.FABRIC)
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.ARMING_COAT),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.ARMING_COAT))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.ROHIRRIC_REINFORCED_COAT).getPath() + "_artisan"));

        //T2
        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.ROHIRRIC_LEATHER_HELMET.getDefaultInstance(), "helmet", Disposition.GOOD)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.LEATHER),
                        NeoForgeRecipeProvider.has(Items.LEATHER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.ROHIRRIC_LEATHER_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.ROHIRRIC_REINFORCED_LEATHER_HELMET.getDefaultInstance(), "helmet", Disposition.GOOD)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.IRON_NUGGET)
                .input(Items.IRON_NUGGET)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.LEATHER),
                        NeoForgeRecipeProvider.has(Items.LEATHER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.ROHIRRIC_REINFORCED_LEATHER_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.ROHIRRIC_ORNAMENTED_LEATHER_HELMET.getDefaultInstance(), "helmet", Disposition.GOOD)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(ModResourceItems.BRONZE_NUGGET)
                .input(ModResourceItems.BRONZE_NUGGET)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.LEATHER),
                        NeoForgeRecipeProvider.has(Items.LEATHER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.ROHIRRIC_ORNAMENTED_LEATHER_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.ROHIRRIC_LEATHER_VEST.getDefaultInstance(), "chestplate", Disposition.GOOD)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(ModResourceItems.FABRIC)
                .input(ModEquipmentItems.ARMING_COAT)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(Items.LEATHER)
                .input(ModResourceItems.FABRIC)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.ARMING_COAT),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.ARMING_COAT))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.ROHIRRIC_LEATHER_VEST).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.ROHIRRIC_LEATHER_SCALE_VEST.getDefaultInstance(), "chestplate", Disposition.GOOD)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(ModResourceItems.FABRIC)
                .input(ModEquipmentItems.ARMING_COAT)
                .input(ModResourceItems.FABRIC)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.ARMING_COAT),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.ARMING_COAT))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.ROHIRRIC_LEATHER_SCALE_VEST).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.ROHIRRIC_GAMBESON.getDefaultInstance(), "chestplate", Disposition.GOOD)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(ModResourceItems.FABRIC)
                .input(ModEquipmentItems.GAMBESON)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.GAMBESON),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.GAMBESON))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.ROHIRRIC_GAMBESON).getPath() + "_artisan"));

        //T3
        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.ROHIRRIC_MILITIA_HELMET.getDefaultInstance(), "helmet", Disposition.GOOD)
                .input(Items.LEATHER)
                .componentInput(new ComponentsIngredient(Ingredient.of(bronzeArmorPlate.getItem()), bronzeArmorPlate.getComponentsPatch()))
                .input(Items.LEATHER)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironArmorPlate.getItem()), ironArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironArmorPlate.getItem()), ironArmorPlate.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(bronzeArmorPlate.getItem()),
                        NeoForgeRecipeProvider.has(bronzeArmorPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.ROHIRRIC_MILITIA_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.ROHIRRIC_BRACED_MILITIA_HELMET.getDefaultInstance(), "helmet", Disposition.GOOD)
                .input(Items.LEATHER)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironArmorPlate.getItem()), ironArmorPlate.getComponentsPatch()))
                .input(Items.LEATHER)
                .componentInput(new ComponentsIngredient(Ingredient.of(bronzeArmorPlate.getItem()), bronzeArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(bronzeArmorPlate.getItem()), bronzeArmorPlate.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ironArmorPlate.getItem()),
                        NeoForgeRecipeProvider.has(ironArmorPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.ROHIRRIC_BRACED_MILITIA_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.ROHIRRIC_REINFORCED_MILITIA_HELMET.getDefaultInstance(), "helmet", Disposition.GOOD)
                .input(Items.LEATHER)
                .componentInput(new ComponentsIngredient(Ingredient.of(bronzeArmorPlate.getItem()), bronzeArmorPlate.getComponentsPatch()))
                .input(Items.LEATHER)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironArmorPlate.getItem()), ironArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironArmorPlate.getItem()), ironArmorPlate.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(bronzeArmorPlate.getItem()),
                        NeoForgeRecipeProvider.has(bronzeArmorPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.ROHIRRIC_REINFORCED_MILITIA_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.ROHIRRIC_ORNAMENTED_MILITIA_HELMET.getDefaultInstance(), "helmet", Disposition.GOOD)
                .input(Items.LEATHER)
                .componentInput(new ComponentsIngredient(Ingredient.of(bronzeArmorPlate.getItem()), bronzeArmorPlate.getComponentsPatch()))
                .input(Items.LEATHER)
                .componentInput(new ComponentsIngredient(Ingredient.of(bronzeArmorPlate.getItem()), bronzeArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(bronzeArmorPlate.getItem()), bronzeArmorPlate.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(bronzeArmorPlate.getItem()),
                        NeoForgeRecipeProvider.has(bronzeArmorPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.ROHIRRIC_ORNAMENTED_MILITIA_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.ROHIRRIC_REINFORCED_LEATHER_VEST.getDefaultInstance(), "chestplate", Disposition.GOOD)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(ModResourceItems.FABRIC)
                .input(ModEquipmentItems.MAIL_HAUBERK)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(Items.LEATHER)
                .input(ModResourceItems.FABRIC)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.MAIL_HAUBERK),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.MAIL_HAUBERK))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.ROHIRRIC_REINFORCED_LEATHER_VEST).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.ROHIRRIC_REINFORCED_LEATHER_SCALE_VEST.getDefaultInstance(), "chestplate", Disposition.GOOD)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(ModResourceItems.FABRIC)
                .input(ModEquipmentItems.MAIL_HAUBERK)
                .input(ModResourceItems.FABRIC)
                .input(Items.LEATHER)
                .input(ModResourceItems.FABRIC)
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.MAIL_HAUBERK),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.MAIL_HAUBERK))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.ROHIRRIC_REINFORCED_LEATHER_SCALE_VEST).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.ROHIRRIC_BRACED_MAIL_SHIRT.getDefaultInstance(), "chestplate", Disposition.GOOD)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(ModResourceItems.FABRIC)
                .input(ModEquipmentItems.MAIL_HAUBERK)
                .input(ModResourceItems.FABRIC)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.MAIL_HAUBERK),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.MAIL_HAUBERK))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.ROHIRRIC_BRACED_MAIL_SHIRT).getPath() + "_artisan"));

        ///T4
        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.ROHIRRIC_SOLDIER_HELMET.getDefaultInstance(), "helmet", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelHelmetPlate.getItem()), steelHelmetPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelChainmail.getItem()), steelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelChainmail.getItem()), steelChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(steelHelmetPlate.getItem()),
                        NeoForgeRecipeProvider.has(steelHelmetPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.ROHIRRIC_SOLDIER_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.ROHIRRIC_ORNAMENTED_SOLDIER_HELMET.getDefaultInstance(), "helmet", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelHelmetPlate.getItem()), steelHelmetPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(bronzeArmorPlate.getItem()), bronzeArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(bronzeArmorPlate.getItem()), bronzeArmorPlate.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(steelHelmetPlate.getItem()),
                        NeoForgeRecipeProvider.has(steelHelmetPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.ROHIRRIC_ORNAMENTED_SOLDIER_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.ROHIRRIC_ROYAL_GUARD_HELMET.getDefaultInstance(), "helmet", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelHelmetPlate.getItem()), steelHelmetPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(bronzeArmorPlate.getItem()), bronzeArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(bronzeArmorPlate.getItem()), bronzeArmorPlate.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(steelHelmetPlate.getItem()),
                        NeoForgeRecipeProvider.has(steelHelmetPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.ROHIRRIC_ROYAL_GUARD_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.ROHIRRIC_SCALE_HAUBERK.getDefaultInstance(), "chestplate", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelScaleMail.getItem()), steelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelScaleMail.getItem()), steelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelScaleMail.getItem()), steelScaleMail.getComponentsPatch()))
                .input(Items.LEATHER)
                .input(ModResourceItems.FABRIC)
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.MAIL_HAUBERK),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.MAIL_HAUBERK))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.ROHIRRIC_SCALE_HAUBERK).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.ROHIRRIC_ORNAMENTED_SCALE_HAUBERK.getDefaultInstance(), "chestplate", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(bronzeArmorPlate.getItem()), bronzeArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(bronzeArmorPlate.getItem()), bronzeArmorPlate.getComponentsPatch()))
                .input(ModResourceItems.FABRIC)
                .input(ModEquipmentItems.ROHIRRIC_SCALE_HAUBERK)
                .input(ModResourceItems.FABRIC)
                .input(Items.GREEN_DYE)
                .input(ModResourceItems.FABRIC)
                .input(Items.GREEN_DYE)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.MAIL_HAUBERK),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.MAIL_HAUBERK))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.ROHIRRIC_ORNAMENTED_SCALE_HAUBERK).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.ROHIRRIC_SCALE_JACKET.getDefaultInstance(), "leggings", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(steelScaleMail.getItem()), steelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelScaleMail.getItem()), steelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelScaleMail.getItem()), steelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelScaleMail.getItem()), steelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelScaleMail.getItem()), steelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelChainmail.getItem()), steelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelChainmail.getItem()), steelChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.MAIL_HAUBERK),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.MAIL_HAUBERK))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.ROHIRRIC_SCALE_JACKET).getPath() + "_artisan"));

        //T5 EORLING MARSHAL
        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.EORLING_MARSHAL_HELMET.getDefaultInstance(), "helmet", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelHelmetPlate.getItem()), steelHelmetPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(steelHelmetPlate.getItem()),
                        NeoForgeRecipeProvider.has(steelHelmetPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.EORLING_MARSHAL_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.EORLING_MARSHAL_CHESTPLATE.getDefaultInstance(), "chestplate", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelChainmail.getItem()), steelChainmail.getComponentsPatch()))
                .input(ModEquipmentItems.ROHIRRIC_SCALE_HAUBERK)
                .componentInput(new ComponentsIngredient(Ingredient.of(steelChainmail.getItem()), steelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelChainmail.getItem()), steelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelChainmail.getItem()), steelChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.ROHIRRIC_SCALE_HAUBERK),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.ROHIRRIC_SCALE_HAUBERK))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.EORLING_MARSHAL_CHESTPLATE).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.EORLING_MARSHAL_LEGGINGS.getDefaultInstance(), "leggings", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .input(ModEquipmentItems.ROHIRRIC_SCALE_JACKET)
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelScaleMail.getItem()), steelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelScaleMail.getItem()), steelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelScaleMail.getItem()), steelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelScaleMail.getItem()), steelScaleMail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.ROHIRRIC_SCALE_JACKET),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.ROHIRRIC_SCALE_JACKET))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.EORLING_MARSHAL_LEGGINGS).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.EORLING_MARSHAL_BOOTS.getDefaultInstance(), "boots", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(goldArmorPlate.getItem()),
                        NeoForgeRecipeProvider.has(goldArmorPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.EORLING_MARSHAL_BOOTS).getPath() + "_artisan"));

        //T5 HORSE LORD
        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.HORSE_LORD_HELMET.getDefaultInstance(), "helmet", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelHelmetPlate.getItem()), steelHelmetPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(goldArmorPlate.getItem()), goldArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(goldArmorPlate.getItem()), goldArmorPlate.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(steelHelmetPlate.getItem()),
                        NeoForgeRecipeProvider.has(steelHelmetPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.HORSE_LORD_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.HORSE_LORD_CHESTPLATE.getDefaultInstance(), "chestplate", Disposition.GOOD)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .componentInput(new ComponentsIngredient(Ingredient.of(goldArmorPlate.getItem()), goldArmorPlate.getComponentsPatch()))
                .input(ModEquipmentItems.ROHIRRIC_SCALE_HAUBERK)
                .componentInput(new ComponentsIngredient(Ingredient.of(goldArmorPlate.getItem()), goldArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelChainmail.getItem()), steelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.ROHIRRIC_SCALE_HAUBERK),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.ROHIRRIC_SCALE_HAUBERK))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.HORSE_LORD_CHESTPLATE).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.HORSE_LORD_LEGGINGS.getDefaultInstance(), "leggings", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .input(ModEquipmentItems.ROHIRRIC_SCALE_JACKET)
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelScaleMail.getItem()), steelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelScaleMail.getItem()), steelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelScaleMail.getItem()), steelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelScaleMail.getItem()), steelScaleMail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.ROHIRRIC_SCALE_JACKET),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.ROHIRRIC_SCALE_JACKET))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.HORSE_LORD_LEGGINGS).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.HORSE_LORD_BOOTS.getDefaultInstance(), "boots", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(goldArmorPlate.getItem()), goldArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(goldArmorPlate.getItem()), goldArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(goldArmorPlate.getItem()),
                        NeoForgeRecipeProvider.has(goldArmorPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.HORSE_LORD_BOOTS).getPath() + "_artisan"));

        //CAPES
        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.ROHIRRIC_CAPE.getDefaultInstance(), "cape", Disposition.GOOD)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.BRONZE_NUGGET)
                .input(ModResourceItems.FABRIC)
                .input(Items.GREEN_DYE)
                .input(ModResourceItems.FABRIC)
                .input(Items.GREEN_DYE)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.FABRIC),
                        NeoForgeRecipeProvider.has(ModResourceItems.FABRIC))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.ROHIRRIC_CAPE).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.ROHIRRIC_ROYAL_GUARD_CAPE.getDefaultInstance(), "cape", Disposition.GOOD)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.BRONZE_NUGGET)
                .input(ModResourceItems.FABRIC)
                .input(Items.YELLOW_DYE)
                .input(ModResourceItems.FABRIC)
                .input(Items.YELLOW_DYE)
                .input(Items.GREEN_DYE)
                .input(ModResourceItems.FABRIC)
                .input(Items.GREEN_DYE)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.FABRIC),
                        NeoForgeRecipeProvider.has(ModResourceItems.FABRIC))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.ROHIRRIC_ROYAL_GUARD_CAPE).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.EORLING_MARSHAL_CAPE.getDefaultInstance(), "cape", Disposition.GOOD)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.STEEL_NUGGET)
                .input(ModResourceItems.FABRIC)
                .input(Items.GREEN_DYE)
                .input(ModResourceItems.FABRIC)
                .input(Items.GREEN_DYE)
                .input(Items.YELLOW_DYE)
                .input(ModResourceItems.FABRIC)
                .input(Items.WHITE_DYE)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.FABRIC),
                        NeoForgeRecipeProvider.has(ModResourceItems.FABRIC))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.EORLING_MARSHAL_CAPE).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.HORSE_LORD_CAPE.getDefaultInstance(), "cape", Disposition.GOOD)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.BRONZE_NUGGET)
                .input(ModResourceItems.FABRIC)
                .input(Items.BLUE_DYE)
                .input(ModResourceItems.FABRIC)
                .input(Items.BLUE_DYE)
                .input(Items.RED_DYE)
                .input(ModResourceItems.FABRIC)
                .input(Items.RED_DYE)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.FABRIC),
                        NeoForgeRecipeProvider.has(ModResourceItems.FABRIC))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.HORSE_LORD_CAPE).getPath() + "_artisan"));
        //endregion

        //region DALE

        //T1
        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.DALISH_ARMING_COAT_BLACK_FUR.getDefaultInstance(), "chestplate", Disposition.GOOD)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FUR)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FUR)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.FABRIC),
                        NeoForgeRecipeProvider.has(ModResourceItems.FABRIC))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.DALISH_ARMING_COAT_BLACK_FUR).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.DALISH_ARMING_COAT_BROWN_FUR.getDefaultInstance(), "chestplate", Disposition.GOOD)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FUR)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FUR)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.FABRIC),
                        NeoForgeRecipeProvider.has(ModResourceItems.FABRIC))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.DALISH_ARMING_COAT_BROWN_FUR).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.DALISH_ARMING_COAT_TAN_FUR.getDefaultInstance(), "chestplate", Disposition.GOOD)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FUR)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FUR)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.FABRIC),
                        NeoForgeRecipeProvider.has(ModResourceItems.FABRIC))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.DALISH_ARMING_COAT_TAN_FUR).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.DALISH_HEYDAY_ARMING_COAT.getDefaultInstance(), "chestplate", Disposition.GOOD)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(Items.ORANGE_DYE)
                .input(ModResourceItems.FABRIC)
                .input(Items.ORANGE_DYE)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.FABRIC),
                        NeoForgeRecipeProvider.has(ModResourceItems.FABRIC))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.DALISH_HEYDAY_ARMING_COAT).getPath() + "_artisan"));

        //T2
        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.DALISH_BOOTS.getDefaultInstance(), "boots", Disposition.GOOD)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.LEATHER),
                        NeoForgeRecipeProvider.has(Items.LEATHER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.DALISH_BOOTS).getPath() + "_artisan"));

        //T3
        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.DALISH_HELMET.getDefaultInstance(), "helmet", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironArmorPlate.getItem()), ironArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironHelmetPlate.getItem()), ironHelmetPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironArmorPlate.getItem()), ironArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ironHelmetPlate.getItem()),
                        NeoForgeRecipeProvider.has(ironHelmetPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.DALISH_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.DALISH_HELMET_BLACK_FUR.getDefaultInstance(), "helmet", Disposition.GOOD)
                .input(ModResourceItems.FUR)
                .input(ModEquipmentItems.DALISH_HELMET)
                .input(ModResourceItems.FUR)
                .input(ModResourceItems.FUR)
                .input(ModResourceItems.FUR)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.DALISH_HELMET),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.DALISH_HELMET))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.DALISH_HELMET_BLACK_FUR).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.DALISH_HELMET_BROWN_FUR.getDefaultInstance(), "helmet", Disposition.GOOD)
                .input(ModResourceItems.FUR)
                .input(ModEquipmentItems.DALISH_HELMET)
                .input(ModResourceItems.FUR)
                .input(ModResourceItems.FUR)
                .input(ModResourceItems.FUR)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.DALISH_HELMET),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.DALISH_HELMET))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.DALISH_HELMET_BROWN_FUR).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.DALISH_HELMET_TAN_FUR.getDefaultInstance(), "helmet", Disposition.GOOD)
                .input(ModResourceItems.FUR)
                .input(ModEquipmentItems.DALISH_HELMET)
                .input(ModResourceItems.FUR)
                .input(ModResourceItems.FUR)
                .input(ModResourceItems.FUR)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.DALISH_HELMET),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.DALISH_HELMET))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.DALISH_HELMET_TAN_FUR).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.DALISH_MAIL_HAUBERK.getDefaultInstance(), "chestplate", Disposition.GOOD)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(ModResourceItems.FABRIC)
                .input(ModEquipmentItems.MAIL_HAUBERK)
                .input(ModResourceItems.FABRIC)
                .input(Items.LEATHER)
                .input(ModResourceItems.FABRIC)
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.MAIL_HAUBERK),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.MAIL_HAUBERK))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.DALISH_MAIL_HAUBERK).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.DALISH_MAIL_COAT.getDefaultInstance(), "leggings", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .input(ModEquipmentItems.MAIL_SKIRT)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.MAIL_HAUBERK),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.MAIL_HAUBERK))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.DALISH_MAIL_COAT).getPath() + "_artisan"));

        //T4
        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.DALISH_BURGONET.getDefaultInstance(), "helmet", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelHelmetPlate.getItem()), steelHelmetPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelChainmail.getItem()), steelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelChainmail.getItem()), steelChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(steelHelmetPlate.getItem()),
                        NeoForgeRecipeProvider.has(steelHelmetPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.DALISH_BURGONET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.DALISH_SCALE_HAUBERK.getDefaultInstance(), "chestplate", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelScaleMail.getItem()), steelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelScaleMail.getItem()), steelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelScaleMail.getItem()), steelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelChainmail.getItem()), steelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelScaleMail.getItem()), steelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelChainmail.getItem()), steelChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ironHelmetPlate.getItem()),
                        NeoForgeRecipeProvider.has(ironHelmetPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.DALISH_SCALE_HAUBERK).getPath() + "_artisan"));

        //T4 HEYDAY
        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.DALISH_HEYDAY_HELMET.getDefaultInstance(), "helmet", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .input(ModEquipmentItems.DALISH_HELMET)
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .input(ModResourceItems.FUR)
                .input(ModResourceItems.FUR)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.DALISH_HELMET),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.DALISH_HELMET))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.DALISH_HEYDAY_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.DALISH_HEYDAY_CHESTPLATE.getDefaultInstance(), "chestplate", Disposition.GOOD)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(ModResourceItems.FABRIC)
                .input(ModEquipmentItems.DALISH_SCALE_HAUBERK)
                .input(ModResourceItems.FABRIC)
                .input(Items.ORANGE_DYE)
                .input(ModResourceItems.FABRIC)
                .input(Items.ORANGE_DYE)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.DALISH_SCALE_HAUBERK),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.DALISH_SCALE_HAUBERK))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.DALISH_HEYDAY_CHESTPLATE).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.DALISH_HEYDAY_MAIL_COAT.getDefaultInstance(), "leggings", Disposition.GOOD)
                .input(ModResourceItems.FABRIC)
                .input(ModEquipmentItems.DALISH_MAIL_COAT)
                .input(ModResourceItems.FABRIC)
                .componentInput(new ComponentsIngredient(Ingredient.of(steelChainmail.getItem()), steelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelChainmail.getItem()), steelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelChainmail.getItem()), steelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelChainmail.getItem()), steelChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.DALISH_MAIL_COAT),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.DALISH_MAIL_COAT))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.DALISH_HEYDAY_MAIL_COAT).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.DALISH_HEYDAY_BOOTS.getDefaultInstance(), "boots", Disposition.GOOD)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(steelArmorPlate.getItem()),
                        NeoForgeRecipeProvider.has(steelArmorPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.DALISH_HEYDAY_BOOTS).getPath() + "_artisan"));

        //T5 BARDING
        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.BARDING_SOLDIER_HELMET.getDefaultInstance(), "helmet", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelHelmetPlate.getItem()), steelHelmetPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(steelHelmetPlate.getItem()),
                        NeoForgeRecipeProvider.has(steelHelmetPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.BARDING_SOLDIER_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.BARDING_SOLDIER_CHESTPLATE.getDefaultInstance(), "chestplate", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelChainmail.getItem()), steelChainmail.getComponentsPatch()))
                .input(ModEquipmentItems.DALISH_SCALE_HAUBERK)
                .componentInput(new ComponentsIngredient(Ingredient.of(steelChainmail.getItem()), steelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelChainmail.getItem()), steelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelChainmail.getItem()), steelChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.DALISH_SCALE_HAUBERK),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.DALISH_SCALE_HAUBERK))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.BARDING_SOLDIER_CHESTPLATE).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.BARDING_MAIL_SKIRT.getDefaultInstance(), "leggings", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .input(ModEquipmentItems.DALISH_MAIL_COAT)
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelChainmail.getItem()), steelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelChainmail.getItem()), steelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelChainmail.getItem()), steelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelChainmail.getItem()), steelChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.DALISH_MAIL_COAT),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.DALISH_MAIL_COAT))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.BARDING_MAIL_SKIRT).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.BARDING_PLATED_BOOTS.getDefaultInstance(), "boots", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(steelArmorPlate.getItem()),
                        NeoForgeRecipeProvider.has(steelArmorPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.BARDING_PLATED_BOOTS).getPath() + "_artisan"));

        //T5 BARDING SERGEANT
        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.BARDING_SERGEANT_HELMET.getDefaultInstance(), "helmet", Disposition.GOOD)
                .input(Items.FEATHER)
                .input(ModEquipmentItems.BARDING_SOLDIER_HELMET)
                .input(Items.FEATHER)
                .componentInput(new ComponentsIngredient(Ingredient.of(goldArmorPlate.getItem()), goldArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(goldArmorPlate.getItem()), goldArmorPlate.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.BARDING_SOLDIER_HELMET),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.BARDING_SOLDIER_HELMET))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.BARDING_SERGEANT_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.BARDING_SERGEANT_CHESTPLATE.getDefaultInstance(), "chestplate", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(goldArmorPlate.getItem()), goldArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(goldArmorPlate.getItem()), goldArmorPlate.getComponentsPatch()))
                .input(ModResourceItems.FABRIC)
                .input(ModEquipmentItems.BARDING_SOLDIER_CHESTPLATE)
                .input(ModResourceItems.FABRIC)
                .componentInput(new ComponentsIngredient(Ingredient.of(steelChainmail.getItem()), steelChainmail.getComponentsPatch()))
                .input(ModResourceItems.FABRIC)
                .componentInput(new ComponentsIngredient(Ingredient.of(steelChainmail.getItem()), steelChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.BARDING_SERGEANT_CHESTPLATE),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.BARDING_SERGEANT_CHESTPLATE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.BARDING_SERGEANT_CHESTPLATE).getPath() + "_artisan"));

        //CAPES
        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.BARDING_SURCOAT.getDefaultInstance(), "cape", Disposition.GOOD)
                .input(ModResourceItems.FABRIC)
                .input(Items.STRING)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(Items.LEATHER)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(Items.PURPLE_DYE)
                .input(ModResourceItems.FABRIC)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.FABRIC),
                        NeoForgeRecipeProvider.has(ModResourceItems.FABRIC))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.BARDING_SURCOAT).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.BARDING_SERGEANT_CAPE.getDefaultInstance(), "cape", Disposition.GOOD)
                .input(ModResourceItems.FABRIC)
                .input(Items.STRING)
                .input(ModResourceItems.FABRIC)
                .input(Items.ORANGE_DYE)
                .input(ModResourceItems.FABRIC)
                .input(Items.ORANGE_DYE)
                .input(Items.PURPLE_DYE)
                .input(ModResourceItems.FABRIC)
                .input(Items.PURPLE_DYE)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.FABRIC),
                        NeoForgeRecipeProvider.has(ModResourceItems.FABRIC))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.BARDING_SERGEANT_CAPE).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.DALISH_HEYDAY_CLOAK.getDefaultInstance(), "cape", Disposition.GOOD)
                .input(ModResourceItems.BRONZE_NUGGET)
                .input(Items.STRING)
                .input(ModResourceItems.BRONZE_NUGGET)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(Items.RED_DYE)
                .input(ModResourceItems.FABRIC)
                .input(Items.RED_DYE)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.FABRIC),
                        NeoForgeRecipeProvider.has(ModResourceItems.FABRIC))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.DALISH_HEYDAY_CLOAK).getPath() + "_artisan"));
//endregion
        
        //region BLACK NUMENOREANS

        //endregion

        //endregion

        //region ELVES

        //region GENERIC

        //T1
        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.ELVEN_ARMING_COAT.getDefaultInstance(), "chestplate", Disposition.GOOD)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.FABRIC),
                        NeoForgeRecipeProvider.has(ModResourceItems.FABRIC))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.ELVEN_ARMING_COAT).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.ELVEN_ARMING_SKIRT.getDefaultInstance(), "leggings", Disposition.GOOD)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.FABRIC),
                        NeoForgeRecipeProvider.has(ModResourceItems.FABRIC))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.ELVEN_ARMING_SKIRT).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.ELVEN_OPEN_ARMING_SKIRT.getDefaultInstance(), "leggings", Disposition.GOOD)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.FABRIC),
                        NeoForgeRecipeProvider.has(ModResourceItems.FABRIC))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.ELVEN_OPEN_ARMING_SKIRT).getPath() + "_artisan"));

        //T2
        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.ELVEN_BOOTS.getDefaultInstance(), "boots", Disposition.GOOD)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.LEATHER),
                        NeoForgeRecipeProvider.has(Items.LEATHER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.ELVEN_BOOTS).getPath() + "_artisan"));

        //T3
        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.ELVEN_MAIL_COIF.getDefaultInstance(), "helmet", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ironChainmail.getItem()),
                        NeoForgeRecipeProvider.has(ironChainmail.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.ELVEN_MAIL_COIF).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.ELVEN_MAIL_HAUBERK.getDefaultInstance(), "chestplate", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ironChainmail.getItem()),
                        NeoForgeRecipeProvider.has(ironChainmail.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.ELVEN_MAIL_HAUBERK).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.ELVEN_MAIL_SKIRT.getDefaultInstance(), "leggings", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ironChainmail.getItem()),
                        NeoForgeRecipeProvider.has(ironChainmail.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.ELVEN_MAIL_SKIRT).getPath() + "_artisan"));

        //endregion

        //region LOTHLORIEN
        //T1
        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.LORIEN_DIADEM.getDefaultInstance(), "helmet", Disposition.GOOD)
                .input(ModResourceItems.BRONZE_NUGGET)
                .componentInput(new ComponentsIngredient(Ingredient.of(bronzeArmorPlate.getItem()), bronzeArmorPlate.getComponentsPatch()))
                .input(ModResourceItems.BRONZE_NUGGET)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(bronzeArmorPlate.getItem()),
                        NeoForgeRecipeProvider.has(bronzeArmorPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.LORIEN_DIADEM).getPath() + "_artisan"));

        //T2
        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.LORIEN_LEATHER_HELMET.getDefaultInstance(), "helmet", Disposition.GOOD)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.LEATHER),
                        NeoForgeRecipeProvider.has(Items.LEATHER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.LORIEN_LEATHER_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.LORIEN_ARMING_COAT.getDefaultInstance(), "chestplate", Disposition.GOOD)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(ModResourceItems.FABRIC)
                .input(ModEquipmentItems.ELVEN_ARMING_COAT)
                .input(ModResourceItems.FABRIC)
                .input(Items.LEATHER)
                .input(ModResourceItems.FABRIC)
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.ELVEN_ARMING_COAT),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.ELVEN_ARMING_COAT))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.LORIEN_ARMING_COAT).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.LORIEN_ARMING_SKIRT.getDefaultInstance(), "leggings", Disposition.GOOD)
                .input(Items.LEATHER)
                .input(ModEquipmentItems.ELVEN_ARMING_SKIRT)
                .input(Items.LEATHER)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.ELVEN_ARMING_SKIRT),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.ELVEN_ARMING_SKIRT))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.LORIEN_ARMING_SKIRT).getPath() + "_artisan"));

        //T3
        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.LORIEN_MAIL_COIF_DIADEM.getDefaultInstance(), "helmet", Disposition.GOOD)
                .input(ModEquipmentItems.ELVEN_MAIL_COIF)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .input(ModEquipmentItems.LORIEN_DIADEM)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.ELVEN_MAIL_COIF),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.ELVEN_MAIL_COIF))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.LORIEN_MAIL_COIF_DIADEM).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.LORIEN_SHORT_MAIL_COIF_DIADEM.getDefaultInstance(), "helmet", Disposition.GOOD)
                .input(ModEquipmentItems.ELVEN_MAIL_COIF)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .input(ModEquipmentItems.LORIEN_DIADEM)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.ELVEN_MAIL_COIF),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.ELVEN_MAIL_COIF))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.LORIEN_SHORT_MAIL_COIF_DIADEM).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.LORIEN_MAIL_HAUBERK.getDefaultInstance(), "chestplate", Disposition.GOOD)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(ModEquipmentItems.ELVEN_MAIL_HAUBERK)
                .input(Items.LEATHER)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .input(ModResourceItems.FABRIC)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.ELVEN_MAIL_HAUBERK),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.ELVEN_MAIL_HAUBERK))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.LORIEN_MAIL_HAUBERK).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.LORIEN_MARCHWARDEN_MAIL_HAUBERK.getDefaultInstance(), "chestplate", Disposition.GOOD)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModEquipmentItems.ELVEN_MAIL_HAUBERK)
                .input(ModResourceItems.FABRIC)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.ELVEN_MAIL_HAUBERK),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.ELVEN_MAIL_HAUBERK))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.LORIEN_MARCHWARDEN_MAIL_HAUBERK).getPath() + "_artisan"));

        //T4
        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.LORIEN_SOLDIER_HELMET.getDefaultInstance(), "helmet", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(edhelSteelArmorPlate.getItem()), edhelSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(edhelSteelHelmetPlate.getItem()), edhelSteelHelmetPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(edhelSteelArmorPlate.getItem()), edhelSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(edhelSteelChainmail.getItem()), edhelSteelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(edhelSteelChainmail.getItem()), edhelSteelChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(edhelSteelHelmetPlate.getItem()),
                        NeoForgeRecipeProvider.has(edhelSteelHelmetPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.LORIEN_SOLDIER_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.LORIEN_SOLDIER_MAIL_HAUBERK.getDefaultInstance(), "chestplate", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(edhelSteelArmorPlate.getItem()), edhelSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(edhelSteelArmorPlate.getItem()), edhelSteelArmorPlate.getComponentsPatch()))
                .input(ModResourceItems.FABRIC)
                .input(ModEquipmentItems.LORIEN_MAIL_HAUBERK)
                .input(ModResourceItems.FABRIC)
                .componentInput(new ComponentsIngredient(Ingredient.of(edhelSteelArmorPlate.getItem()), edhelSteelArmorPlate.getComponentsPatch()))
                .input(ModResourceItems.FABRIC)
                .componentInput(new ComponentsIngredient(Ingredient.of(edhelSteelChainmail.getItem()), edhelSteelChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.LORIEN_MAIL_HAUBERK),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.LORIEN_MAIL_HAUBERK))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.LORIEN_SOLDIER_MAIL_HAUBERK).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.LORIEN_SOLDIER_SCALE_HAUBERK.getDefaultInstance(), "chestplate", Disposition.GOOD)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .componentInput(new ComponentsIngredient(Ingredient.of(edhelSteelScaleMail.getItem()), edhelSteelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(edhelSteelScaleMail.getItem()), edhelSteelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(edhelSteelScaleMail.getItem()), edhelSteelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(edhelSteelArmorPlate.getItem()), edhelSteelArmorPlate.getComponentsPatch()))
                .input(ModResourceItems.FABRIC)
                .componentInput(new ComponentsIngredient(Ingredient.of(edhelSteelArmorPlate.getItem()), edhelSteelArmorPlate.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(edhelSteelScaleMail.getItem()),
                        NeoForgeRecipeProvider.has(edhelSteelScaleMail.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.LORIEN_SOLDIER_SCALE_HAUBERK).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.LORIEN_SCALE_COAT.getDefaultInstance(), "leggings", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(edhelSteelScaleMail.getItem()), edhelSteelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(edhelSteelScaleMail.getItem()), edhelSteelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(edhelSteelScaleMail.getItem()), edhelSteelScaleMail.getComponentsPatch()))
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .componentInput(new ComponentsIngredient(Ingredient.of(edhelSteelScaleMail.getItem()), edhelSteelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(edhelSteelScaleMail.getItem()), edhelSteelScaleMail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(edhelSteelScaleMail.getItem()),
                        NeoForgeRecipeProvider.has(edhelSteelScaleMail.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.LORIEN_SCALE_COAT).getPath() + "_artisan"));

        //T5 GALADHRIM
        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GALADHRIM_HELMET.getDefaultInstance(), "helmet", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(edhelSteelArmorPlate.getItem()), edhelSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(edhelSteelHelmetPlate.getItem()), edhelSteelHelmetPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(edhelSteelArmorPlate.getItem()), edhelSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(edhelSteelArmorPlate.getItem()), edhelSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(edhelSteelArmorPlate.getItem()), edhelSteelArmorPlate.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(edhelSteelHelmetPlate.getItem()),
                        NeoForgeRecipeProvider.has(edhelSteelHelmetPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GALADHRIM_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GALADHRIM_CHESTPLATE.getDefaultInstance(), "chestplate", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(edhelSteelArmorPlate.getItem()), edhelSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(edhelSteelArmorPlate.getItem()), edhelSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(edhelSteelChainmail.getItem()), edhelSteelChainmail.getComponentsPatch()))
                .input(ModEquipmentItems.LORIEN_SOLDIER_SCALE_HAUBERK)
                .componentInput(new ComponentsIngredient(Ingredient.of(edhelSteelChainmail.getItem()), edhelSteelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(edhelSteelChainmail.getItem()), edhelSteelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(edhelSteelArmorPlate.getItem()), edhelSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(edhelSteelChainmail.getItem()), edhelSteelChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.LORIEN_SOLDIER_SCALE_HAUBERK),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.LORIEN_SOLDIER_SCALE_HAUBERK))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GALADHRIM_CHESTPLATE).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GALADHRIM_LEGGINGS.getDefaultInstance(), "leggings", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(edhelSteelArmorPlate.getItem()), edhelSteelArmorPlate.getComponentsPatch()))
                .input(ModEquipmentItems.LORIEN_SCALE_COAT)
                .componentInput(new ComponentsIngredient(Ingredient.of(edhelSteelArmorPlate.getItem()), edhelSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(edhelSteelScaleMail.getItem()), edhelSteelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(edhelSteelScaleMail.getItem()), edhelSteelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(edhelSteelScaleMail.getItem()), edhelSteelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(edhelSteelScaleMail.getItem()), edhelSteelScaleMail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.LORIEN_SCALE_COAT),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.LORIEN_SCALE_COAT))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GALADHRIM_LEGGINGS).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GALADHRIM_BOOTS.getDefaultInstance(), "boots", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(edhelSteelArmorPlate.getItem()), edhelSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(edhelSteelArmorPlate.getItem()), edhelSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(edhelSteelArmorPlate.getItem()), edhelSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(edhelSteelArmorPlate.getItem()), edhelSteelArmorPlate.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(edhelSteelArmorPlate.getItem()),
                        NeoForgeRecipeProvider.has(edhelSteelArmorPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GALADHRIM_BOOTS).getPath() + "_artisan"));

        //T5 GALADHRIM LORD
        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GALADHRIM_LORD_HELMET.getDefaultInstance(), "helmet", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(goldArmorPlate.getItem()), goldArmorPlate.getComponentsPatch()))
                .input(ModEquipmentItems.GALADHRIM_HELMET)
                .componentInput(new ComponentsIngredient(Ingredient.of(goldArmorPlate.getItem()), goldArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(edhelSteelChainmail.getItem()), edhelSteelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(edhelSteelChainmail.getItem()), edhelSteelChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.GALADHRIM_HELMET),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.GALADHRIM_HELMET))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GALADHRIM_LORD_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GALADHRIM_LORD_CHESTPLATE.getDefaultInstance(), "chestplate", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(goldArmorPlate.getItem()), goldArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(goldArmorPlate.getItem()), goldArmorPlate.getComponentsPatch()))
                .input(ModResourceItems.FABRIC)
                .input(ModEquipmentItems.GALADHRIM_CHESTPLATE)
                .input(ModResourceItems.FABRIC)
                .componentInput(new ComponentsIngredient(Ingredient.of(edhelSteelChainmail.getItem()), edhelSteelChainmail.getComponentsPatch()))
                .input(ModResourceItems.FABRIC)
                .componentInput(new ComponentsIngredient(Ingredient.of(edhelSteelChainmail.getItem()), edhelSteelChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.GALADHRIM_CHESTPLATE),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.GALADHRIM_CHESTPLATE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GALADHRIM_LORD_CHESTPLATE).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GALADHRIM_LORD_LEGGINGS.getDefaultInstance(), "leggings", Disposition.GOOD)
                .input(ModResourceItems.FABRIC)
                .input(ModEquipmentItems.GALADHRIM_LEGGINGS)
                .input(ModResourceItems.FABRIC)
                .componentInput(new ComponentsIngredient(Ingredient.of(edhelSteelChainmail.getItem()), edhelSteelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(edhelSteelChainmail.getItem()), edhelSteelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(edhelSteelChainmail.getItem()), edhelSteelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(edhelSteelChainmail.getItem()), edhelSteelChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.GALADHRIM_LEGGINGS),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.GALADHRIM_LEGGINGS))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GALADHRIM_LORD_LEGGINGS).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GALADHRIM_LORD_BOOTS.getDefaultInstance(), "boots", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(goldArmorPlate.getItem()), goldArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(goldArmorPlate.getItem()), goldArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(edhelSteelArmorPlate.getItem()), edhelSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(edhelSteelArmorPlate.getItem()), edhelSteelArmorPlate.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(edhelSteelHelmetPlate.getItem()),
                        NeoForgeRecipeProvider.has(edhelSteelHelmetPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GALADHRIM_LORD_BOOTS).getPath() + "_artisan"));

        //HOODS
        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.LORIEN_MARCHWARDEN_HOOD.getDefaultInstance(), "hood", Disposition.GOOD)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(Items.GRAY_DYE)
                .input(ModResourceItems.FABRIC)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.FABRIC),
                        NeoForgeRecipeProvider.has(ModResourceItems.FABRIC))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.LORIEN_MARCHWARDEN_HOOD).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GALADHRIM_HOOD.getDefaultInstance(), "hood", Disposition.GOOD)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(Items.BLUE_DYE)
                .input(ModResourceItems.FABRIC)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.FABRIC),
                        NeoForgeRecipeProvider.has(ModResourceItems.FABRIC))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GALADHRIM_HOOD).getPath() + "_artisan"));

        //CAPES
        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.LORIEN_MARCHWARDEN_CAPE.getDefaultInstance(), "cape", Disposition.GOOD)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.BRONZE_NUGGET)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(Items.GRAY_DYE)
                .input(ModResourceItems.FABRIC)
                .input(Items.GRAY_DYE)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.FABRIC),
                        NeoForgeRecipeProvider.has(ModResourceItems.FABRIC))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.LORIEN_MARCHWARDEN_CAPE).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GALADHRIM_CAPE.getDefaultInstance(), "cape", Disposition.GOOD)
                .input(Items.STRING)
                .input(ModResourceItems.FABRIC)
                .input(Items.STRING)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(Items.BLUE_DYE)
                .input(ModResourceItems.FABRIC)
                .input(Items.BLUE_DYE)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.FABRIC),
                        NeoForgeRecipeProvider.has(ModResourceItems.FABRIC))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GALADHRIM_CAPE).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GALADHRIM_LORD_SURCOAT.getDefaultInstance(), "cape", Disposition.GOOD)
                .input(ModResourceItems.FABRIC)
                .input(Items.WHITE_DYE)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(Items.LEATHER)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.EDHEL_STEEL_NUGGET)
                .input(ModResourceItems.FABRIC)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.FABRIC),
                        NeoForgeRecipeProvider.has(ModResourceItems.FABRIC))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GALADHRIM_LORD_SURCOAT).getPath() + "_artisan"));
        //endregion

        //endregion

        //region DWARVES

        //region GENERIC
        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.DWARVEN_MINER_HELMET.getDefaultInstance(), "helmet", Disposition.GOOD)
                .input(Items.CANDLE)
                .input(Items.LEATHER)
                .input(Items.CANDLE)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.FABRIC),
                        NeoForgeRecipeProvider.has(ModResourceItems.FABRIC))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.DWARVEN_MINER_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.DWARVEN_GAMBESON.getDefaultInstance(), "chestplate", Disposition.GOOD)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(Items.LEATHER)
                .input(Items.WHITE_WOOL)
                .input(Items.LEATHER)
                .input(ModResourceItems.FABRIC)
                .input(Items.LEATHER)
                .input(ModResourceItems.FABRIC)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.FABRIC),
                        NeoForgeRecipeProvider.has(ModResourceItems.FABRIC))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.DWARVEN_GAMBESON).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.DWARVEN_MAIL_COIF.getDefaultInstance(), "helmet", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ironChainmail.getItem()),
                        NeoForgeRecipeProvider.has(ironChainmail.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.DWARVEN_MAIL_COIF).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.DWARVEN_MAIL_HAUBERK.getDefaultInstance(), "chestplate", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ironChainmail.getItem()),
                        NeoForgeRecipeProvider.has(ironChainmail.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.DWARVEN_MAIL_HAUBERK).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.DWARVEN_MINER_GAMBESON.getDefaultInstance(), "chestplate", Disposition.GOOD)
                .input(ModEquipmentItems.DWARVEN_MAIL_HAUBERK)
                .input(ModEquipmentItems.DWARVEN_GAMBESON)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.DWARVEN_MAIL_HAUBERK),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.DWARVEN_MAIL_HAUBERK))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.DWARVEN_MINER_GAMBESON).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.DWARVEN_MAIL_COAT.getDefaultInstance(), "leggings", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ironChainmail.getItem()),
                        NeoForgeRecipeProvider.has(ironChainmail.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.DWARVEN_MAIL_COAT).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.DWARVEN_MAIL_CHAUSSES.getDefaultInstance(), "boots", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ironChainmail.getItem()),
                        NeoForgeRecipeProvider.has(ironChainmail.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.DWARVEN_MAIL_CHAUSSES).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.DWARVEN_BOOTS.getDefaultInstance(), "boots", Disposition.GOOD)
                .input(Items.IRON_INGOT)
                .input(Items.IRON_INGOT)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.IRON_INGOT),
                        NeoForgeRecipeProvider.has(Items.IRON_INGOT))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.DWARVEN_BOOTS).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.DWARVEN_SCALE_HAUBERK.getDefaultInstance(), "chestplate", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelScaleMail.getItem()), khazadSteelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelScaleMail.getItem()), khazadSteelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelScaleMail.getItem()), khazadSteelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelScaleMail.getItem()), khazadSteelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelScaleMail.getItem()), khazadSteelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelScaleMail.getItem()), khazadSteelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelScaleMail.getItem()), khazadSteelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelScaleMail.getItem()), khazadSteelScaleMail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(khazadSteelScaleMail.getItem()),
                        NeoForgeRecipeProvider.has(khazadSteelScaleMail.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.DWARVEN_SCALE_HAUBERK).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.DWARVEN_SCALE_COAT.getDefaultInstance(), "leggings", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelScaleMail.getItem()), khazadSteelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelScaleMail.getItem()), khazadSteelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelScaleMail.getItem()), khazadSteelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelScaleMail.getItem()), khazadSteelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelScaleMail.getItem()), khazadSteelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelScaleMail.getItem()), khazadSteelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelScaleMail.getItem()), khazadSteelScaleMail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(khazadSteelScaleMail.getItem()),
                        NeoForgeRecipeProvider.has(khazadSteelScaleMail.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.DWARVEN_SCALE_COAT).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.DWARVEN_REINFORCED_BOOTS.getDefaultInstance(), "boots", Disposition.GOOD)
                .input(ModResourceItems.KHAZAD_STEEL_INGOT)
                .input(ModResourceItems.KHAZAD_STEEL_INGOT)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.IRON_INGOT),
                        NeoForgeRecipeProvider.has(Items.IRON_INGOT))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.DWARVEN_REINFORCED_BOOTS).getPath() + "_artisan"));
        //endregion

        //region EREBOR
        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.LONGBEARD_SEGMENTED_HELMET.getDefaultInstance(), "helmet", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(bronzeArmorPlate.getItem()), bronzeArmorPlate.getComponentsPatch()))
                .input(Items.LEATHER)
                .componentInput(new ComponentsIngredient(Ingredient.of(bronzeArmorPlate.getItem()), bronzeArmorPlate.getComponentsPatch()))
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(bronzeArmorPlate.getItem()),
                        NeoForgeRecipeProvider.has(bronzeArmorPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.LONGBEARD_SEGMENTED_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.LONGBEARD_LEATHER_HAUBERK.getDefaultInstance(), "chestplate", Disposition.GOOD)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(ModResourceItems.BRONZE_NUGGET)
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.LEATHER),
                        NeoForgeRecipeProvider.has(Items.LEATHER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.LONGBEARD_LEATHER_HAUBERK).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.LONGBEARD_WANDERER_COAT.getDefaultInstance(), "chestplate", Disposition.GOOD)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.IRON_NUGGET)
                .input(Items.LEATHER)
                .input(Items.IRON_NUGGET)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.LEATHER),
                        NeoForgeRecipeProvider.has(Items.LEATHER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.LONGBEARD_WANDERER_COAT).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.LONGBEARD_LEATHER_LEGGINGS.getDefaultInstance(), "leggings", Disposition.GOOD)
                .input(Items.LEATHER)
                .input(ModResourceItems.BRONZE_NUGGET)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.LEATHER),
                        NeoForgeRecipeProvider.has(Items.LEATHER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.LONGBEARD_LEATHER_LEGGINGS).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.LONGBEARD_PARTISAN_OUTFIT.getDefaultInstance(), "chestplate", Disposition.GOOD)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModEquipmentItems.DWARVEN_MAIL_HAUBERK)
                .input(ModResourceItems.FABRIC)
                .input(Items.LEATHER)
                .input(Items.IRON_NUGGET)
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.DWARVEN_MAIL_HAUBERK),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.DWARVEN_MAIL_HAUBERK))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.LONGBEARD_PARTISAN_OUTFIT).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.LONGBEARD_REINFORCED_LEATHER_HAUBERK.getDefaultInstance(), "chestplate", Disposition.GOOD)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(ModEquipmentItems.DWARVEN_MAIL_HAUBERK)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(ModResourceItems.BRONZE_NUGGET)
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.DWARVEN_MAIL_HAUBERK),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.DWARVEN_MAIL_HAUBERK))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.LONGBEARD_REINFORCED_LEATHER_HAUBERK).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.EREBOR_LEATHER_HELMET.getDefaultInstance(), "helmet", Disposition.GOOD)
                .input(Items.IRON_INGOT)
                .input(Items.LEATHER)
                .input(Items.IRON_INGOT)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.LEATHER),
                        NeoForgeRecipeProvider.has(Items.LEATHER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.EREBOR_LEATHER_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.EREBOR_BRACED_LEATHER_HELMET.getDefaultInstance(), "helmet", Disposition.GOOD)
                .input(Items.IRON_INGOT)
                .input(Items.LEATHER)
                .input(Items.IRON_INGOT)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.LEATHER),
                        NeoForgeRecipeProvider.has(Items.LEATHER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.EREBOR_BRACED_LEATHER_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.EREBOR_NASAL_LEATHER_HELMET.getDefaultInstance(), "helmet", Disposition.GOOD)
                .input(Items.IRON_INGOT)
                .input(Items.LEATHER)
                .input(Items.IRON_INGOT)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.LEATHER),
                        NeoForgeRecipeProvider.has(Items.LEATHER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.EREBOR_NASAL_LEATHER_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.EREBOR_LEATHER_LEGGINGS.getDefaultInstance(), "leggings", Disposition.GOOD)
                .input(Items.LEATHER)
                .input(ModResourceItems.BRONZE_NUGGET)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.LEATHER),
                        NeoForgeRecipeProvider.has(Items.LEATHER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.EREBOR_LEATHER_LEGGINGS).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.EREBOR_MAIL_COIF.getDefaultInstance(), "helmet", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ironChainmail.getItem()),
                        NeoForgeRecipeProvider.has(ironChainmail.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.EREBOR_MAIL_COIF).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.EREBOR_GILDED_MAIL_COIF.getDefaultInstance(), "helmet", Disposition.GOOD)
                .input(ModResourceItems.BRONZE_NUGGET)
                .input(ModEquipmentItems.EREBOR_MAIL_COIF)
                .input(ModResourceItems.BRONZE_NUGGET)
                .input(ModResourceItems.BRONZE_NUGGET)
                .input(ModResourceItems.BRONZE_NUGGET)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.EREBOR_MAIL_COIF),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.EREBOR_MAIL_COIF))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.EREBOR_GILDED_MAIL_COIF).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.EREBOR_HELMET.getDefaultInstance(), "helmet", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironArmorPlate.getItem()), ironArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironHelmetPlate.getItem()), ironHelmetPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironArmorPlate.getItem()), ironArmorPlate.getComponentsPatch()))
                .input(Items.IRON_NUGGET)
                .input(Items.IRON_NUGGET)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ironHelmetPlate.getItem()),
                        NeoForgeRecipeProvider.has(ironHelmetPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.EREBOR_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.EREBOR_MAIL_HAUBERK.getDefaultInstance(), "chestplate", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ironChainmail.getItem()),
                        NeoForgeRecipeProvider.has(ironChainmail.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.EREBOR_MAIL_HAUBERK).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.EREBOR_PADDED_MAIL_HAUBERK.getDefaultInstance(), "chestplate", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .input(Items.LEATHER)
                .input(ModEquipmentItems.EREBOR_MAIL_HAUBERK)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(ModResourceItems.BRONZE_INGOT)
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.EREBOR_MAIL_HAUBERK),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.EREBOR_MAIL_HAUBERK))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.EREBOR_PADDED_MAIL_HAUBERK).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.EREBOR_MAIL_COAT.getDefaultInstance(), "leggings", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ironChainmail.getItem()),
                        NeoForgeRecipeProvider.has(ironChainmail.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.EREBOR_MAIL_COAT).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.EREBOR_MAIL_LEGGINGS.getDefaultInstance(), "leggings", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ironChainmail.getItem()),
                        NeoForgeRecipeProvider.has(ironChainmail.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.EREBOR_MAIL_LEGGINGS).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.EREBOR_MAIL_CHAUSSES.getDefaultInstance(), "boots", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ironChainmail.getItem()),
                        NeoForgeRecipeProvider.has(ironChainmail.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.EREBOR_MAIL_CHAUSSES).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.EREBOR_BOOTS.getDefaultInstance(), "boots", Disposition.GOOD)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.IRON_INGOT)
                .input(Items.IRON_INGOT)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.IRON_INGOT),
                        NeoForgeRecipeProvider.has(Items.IRON_INGOT))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.EREBOR_BOOTS).getPath() + "_artisan"));

        //t4
        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.RAVENHILL_HELMET.getDefaultInstance(), "helmet", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelArmorPlate.getItem()), khazadSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelHelmetPlate.getItem()), khazadSteelHelmetPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelArmorPlate.getItem()), khazadSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelChainmail.getItem()), khazadSteelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelChainmail.getItem()), khazadSteelChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(khazadSteelHelmetPlate.getItem()),
                        NeoForgeRecipeProvider.has(khazadSteelHelmetPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.RAVENHILL_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.RAVENHILL_GILDED_HELMET.getDefaultInstance(), "helmet", Disposition.GOOD)
                .input(ModEquipmentItems.EREBOR_GILDED_MAIL_COIF)
                .input(ModEquipmentItems.RAVENHILL_HELMET)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.RAVENHILL_HELMET),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.RAVENHILL_HELMET))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.RAVENHILL_GILDED_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.EREBOR_SCALE_HAUBERK.getDefaultInstance(), "chestplate", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelScaleMail.getItem()), khazadSteelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelScaleMail.getItem()), khazadSteelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelScaleMail.getItem()), khazadSteelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelScaleMail.getItem()), khazadSteelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelScaleMail.getItem()), khazadSteelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelScaleMail.getItem()), khazadSteelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelScaleMail.getItem()), khazadSteelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelScaleMail.getItem()), khazadSteelScaleMail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(khazadSteelScaleMail.getItem()),
                        NeoForgeRecipeProvider.has(khazadSteelScaleMail.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.EREBOR_SCALE_HAUBERK).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.EREBOR_LONG_COAT.getDefaultInstance(), "chestplate", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelArmorPlate.getItem()), khazadSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelArmorPlate.getItem()), khazadSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelChainmail.getItem()), khazadSteelChainmail.getComponentsPatch()))
                .input(ModEquipmentItems.EREBOR_MAIL_HAUBERK)
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelChainmail.getItem()), khazadSteelChainmail.getComponentsPatch()))
                .input(ModResourceItems.FABRIC)
                .input(Items.LEATHER)
                .input(ModResourceItems.FABRIC)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.EREBOR_MAIL_HAUBERK),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.EREBOR_MAIL_HAUBERK))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.EREBOR_LONG_COAT).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.EREBOR_BRAWLER_CHESTPLATE.getDefaultInstance(), "chestplate", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelArmorPlate.getItem()), khazadSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelArmorPlate.getItem()), khazadSteelArmorPlate.getComponentsPatch()))
                .input(ModResourceItems.FABRIC)
                .input(ModEquipmentItems.EREBOR_MAIL_HAUBERK)
                .input(ModResourceItems.FABRIC)
                .input(Items.LEATHER)
                .input(Items.BLUE_DYE)
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.EREBOR_MAIL_HAUBERK),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.EREBOR_MAIL_HAUBERK))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.EREBOR_BRAWLER_CHESTPLATE).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.EREBOR_REINFORCED_LEATHER_HAUBERK.getDefaultInstance(), "chestplate", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelArmorPlate.getItem()), khazadSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelArmorPlate.getItem()), khazadSteelArmorPlate.getComponentsPatch()))
                .input(Items.LEATHER)
                .input(ModEquipmentItems.EREBOR_MAIL_HAUBERK)
                .input(Items.LEATHER)
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelArmorPlate.getItem()), khazadSteelArmorPlate.getComponentsPatch()))
                .input(Items.LEATHER)
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelArmorPlate.getItem()), khazadSteelArmorPlate.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.EREBOR_MAIL_HAUBERK),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.EREBOR_MAIL_HAUBERK))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.EREBOR_REINFORCED_LEATHER_HAUBERK).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.EREBOR_REINFORCED_COAT.getDefaultInstance(), "chestplate", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelArmorPlate.getItem()), khazadSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelArmorPlate.getItem()), khazadSteelArmorPlate.getComponentsPatch()))
                .input(ModResourceItems.FABRIC)
                .input(ModEquipmentItems.EREBOR_MAIL_HAUBERK)
                .input(ModResourceItems.FABRIC)
                .input(Items.LEATHER)
                .input(Items.BLUE_DYE)
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.EREBOR_MAIL_HAUBERK),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.EREBOR_MAIL_HAUBERK))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.EREBOR_REINFORCED_COAT).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.RAVENHILL_REINFORCED_MAIL_HAUBERK.getDefaultInstance(), "chestplate", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelArmorPlate.getItem()), khazadSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelArmorPlate.getItem()), khazadSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelChainmail.getItem()), khazadSteelChainmail.getComponentsPatch()))
                .input(ModEquipmentItems.EREBOR_MAIL_HAUBERK)
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelChainmail.getItem()), khazadSteelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelArmorPlate.getItem()), khazadSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelChainmail.getItem()), khazadSteelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelArmorPlate.getItem()), khazadSteelArmorPlate.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.EREBOR_MAIL_HAUBERK),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.EREBOR_MAIL_HAUBERK))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.RAVENHILL_REINFORCED_MAIL_HAUBERK).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.EREBOR_SCALE_COAT.getDefaultInstance(), "leggings", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelScaleMail.getItem()), khazadSteelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelScaleMail.getItem()), khazadSteelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelScaleMail.getItem()), khazadSteelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelScaleMail.getItem()), khazadSteelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelScaleMail.getItem()), khazadSteelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelScaleMail.getItem()), khazadSteelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelScaleMail.getItem()), khazadSteelScaleMail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(khazadSteelScaleMail.getItem()),
                        NeoForgeRecipeProvider.has(khazadSteelScaleMail.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.EREBOR_SCALE_COAT).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.EREBOR_SCALE_LEGGINGS.getDefaultInstance(), "leggings", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelScaleMail.getItem()), khazadSteelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelScaleMail.getItem()), khazadSteelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelScaleMail.getItem()), khazadSteelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelScaleMail.getItem()), khazadSteelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelScaleMail.getItem()), khazadSteelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelScaleMail.getItem()), khazadSteelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelScaleMail.getItem()), khazadSteelScaleMail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(khazadSteelScaleMail.getItem()),
                        NeoForgeRecipeProvider.has(khazadSteelScaleMail.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.EREBOR_SCALE_LEGGINGS).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.RAVENHILL_BOOTS.getDefaultInstance(), "boots", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelArmorPlate.getItem()), khazadSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelArmorPlate.getItem()), khazadSteelArmorPlate.getComponentsPatch()))
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(khazadSteelArmorPlate.getItem()),
                        NeoForgeRecipeProvider.has(khazadSteelArmorPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.RAVENHILL_BOOTS).getPath() + "_artisan"));
        //t5
        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.EREBOR_PLATE_HELMET.getDefaultInstance(), "helmet", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelArmorPlate.getItem()), khazadSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelHelmetPlate.getItem()), khazadSteelHelmetPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelArmorPlate.getItem()), khazadSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelArmorPlate.getItem()), khazadSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelArmorPlate.getItem()), khazadSteelArmorPlate.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(khazadSteelHelmetPlate.getItem()),
                        NeoForgeRecipeProvider.has(khazadSteelHelmetPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.EREBOR_PLATE_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.EREBOR_PLATE_CHESTPLATE.getDefaultInstance(), "chestplate", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelArmorPlate.getItem()), khazadSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelArmorPlate.getItem()), khazadSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelScaleMail.getItem()), khazadSteelScaleMail.getComponentsPatch()))
                .input(ModEquipmentItems.EREBOR_SCALE_HAUBERK)
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelScaleMail.getItem()), khazadSteelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelScaleMail.getItem()), khazadSteelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelArmorPlate.getItem()), khazadSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelScaleMail.getItem()), khazadSteelScaleMail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.EREBOR_SCALE_HAUBERK),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.EREBOR_SCALE_HAUBERK))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.EREBOR_PLATE_CHESTPLATE).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.EREBOR_PLATE_LEGGINGS.getDefaultInstance(), "leggings", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelArmorPlate.getItem()), khazadSteelArmorPlate.getComponentsPatch()))
                .input(ModEquipmentItems.EREBOR_SCALE_LEGGINGS)
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelArmorPlate.getItem()), khazadSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelScaleMail.getItem()), khazadSteelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelScaleMail.getItem()), khazadSteelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelScaleMail.getItem()), khazadSteelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelScaleMail.getItem()), khazadSteelScaleMail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.EREBOR_SCALE_LEGGINGS),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.EREBOR_SCALE_LEGGINGS))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.EREBOR_PLATE_LEGGINGS).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.EREBOR_PLATE_BOOTS.getDefaultInstance(), "boots", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelArmorPlate.getItem()), khazadSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelArmorPlate.getItem()), khazadSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelArmorPlate.getItem()), khazadSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelArmorPlate.getItem()), khazadSteelArmorPlate.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(khazadSteelArmorPlate.getItem()),
                        NeoForgeRecipeProvider.has(khazadSteelArmorPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.EREBOR_PLATE_BOOTS).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.EREBOR_GUARD_HELMET.getDefaultInstance(), "helmet", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(goldArmorPlate.getItem()), goldArmorPlate.getComponentsPatch()))
                .input(ModEquipmentItems.EREBOR_PLATE_HELMET)
                .componentInput(new ComponentsIngredient(Ingredient.of(goldArmorPlate.getItem()), goldArmorPlate.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.EREBOR_PLATE_HELMET),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.EREBOR_PLATE_HELMET))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.EREBOR_GUARD_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.EREBOR_GATEWARDEN_HELMET.getDefaultInstance(), "helmet", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(bronzeArmorPlate.getItem()), bronzeArmorPlate.getComponentsPatch()))
                .input(ModEquipmentItems.EREBOR_PLATE_HELMET)
                .componentInput(new ComponentsIngredient(Ingredient.of(bronzeArmorPlate.getItem()), bronzeArmorPlate.getComponentsPatch()))
                .input(ModResourceItems.BRONZE_INGOT)
                .input(ModResourceItems.BRONZE_INGOT)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.EREBOR_PLATE_HELMET),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.EREBOR_PLATE_HELMET))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.EREBOR_GATEWARDEN_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.EREBOR_GATEWARDEN_CHESTPLATE.getDefaultInstance(), "chestplate", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(bronzeArmorPlate.getItem()), bronzeArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(bronzeArmorPlate.getItem()), bronzeArmorPlate.getComponentsPatch()))
                .input(ModResourceItems.FABRIC)
                .input(ModEquipmentItems.EREBOR_PLATE_CHESTPLATE)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.BRONZE_INGOT)
                .input(Items.BLUE_DYE)
                .input(ModResourceItems.BRONZE_INGOT)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.EREBOR_PLATE_CHESTPLATE),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.EREBOR_PLATE_CHESTPLATE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.EREBOR_GATEWARDEN_CHESTPLATE).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.EREBOR_GATEWARDEN_LEGGINGS.getDefaultInstance(), "leggings", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(bronzeArmorPlate.getItem()), bronzeArmorPlate.getComponentsPatch()))
                .input(ModEquipmentItems.EREBOR_PLATE_LEGGINGS)
                .componentInput(new ComponentsIngredient(Ingredient.of(bronzeArmorPlate.getItem()), bronzeArmorPlate.getComponentsPatch()))
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.EREBOR_PLATE_LEGGINGS),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.EREBOR_PLATE_LEGGINGS))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.EREBOR_GATEWARDEN_LEGGINGS).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.EREBOR_GATEWARDEN_BOOTS.getDefaultInstance(), "boots", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelArmorPlate.getItem()), khazadSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelArmorPlate.getItem()), khazadSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelArmorPlate.getItem()), khazadSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelArmorPlate.getItem()), khazadSteelArmorPlate.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(khazadSteelArmorPlate.getItem()),
                        NeoForgeRecipeProvider.has(khazadSteelArmorPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.EREBOR_GATEWARDEN_BOOTS).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.RAVENHILL_WATCHWARDEN_HELMET.getDefaultInstance(), "helmet", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(bronzeArmorPlate.getItem()), bronzeArmorPlate.getComponentsPatch()))
                .input(ModEquipmentItems.EREBOR_PLATE_HELMET)
                .componentInput(new ComponentsIngredient(Ingredient.of(bronzeArmorPlate.getItem()), bronzeArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelChainmail.getItem()), khazadSteelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelChainmail.getItem()), khazadSteelChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.EREBOR_PLATE_HELMET),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.EREBOR_PLATE_HELMET))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.RAVENHILL_WATCHWARDEN_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.RAVENHILL_WATCHWARDEN_CHESTPLATE.getDefaultInstance(), "chestplate", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(bronzeArmorPlate.getItem()), bronzeArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(bronzeArmorPlate.getItem()), bronzeArmorPlate.getComponentsPatch()))
                .input(ModResourceItems.FABRIC)
                .input(ModEquipmentItems.EREBOR_PLATE_CHESTPLATE)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.BRONZE_INGOT)
                .input(Items.GRAY_DYE)
                .input(ModResourceItems.BRONZE_INGOT)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.EREBOR_PLATE_CHESTPLATE),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.EREBOR_PLATE_CHESTPLATE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.RAVENHILL_WATCHWARDEN_CHESTPLATE).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.RAVENHILL_WATCHWARDEN_LEGGINGS.getDefaultInstance(), "leggings", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(bronzeArmorPlate.getItem()), bronzeArmorPlate.getComponentsPatch()))
                .input(ModEquipmentItems.EREBOR_PLATE_LEGGINGS)
                .componentInput(new ComponentsIngredient(Ingredient.of(bronzeArmorPlate.getItem()), bronzeArmorPlate.getComponentsPatch()))
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.EREBOR_PLATE_LEGGINGS),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.EREBOR_PLATE_LEGGINGS))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.RAVENHILL_WATCHWARDEN_LEGGINGS).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.RAVENHILL_WATCHWARDEN_BOOTS.getDefaultInstance(), "boots", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelArmorPlate.getItem()), khazadSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelArmorPlate.getItem()), khazadSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelArmorPlate.getItem()), khazadSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelArmorPlate.getItem()), khazadSteelArmorPlate.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(khazadSteelArmorPlate.getItem()),
                        NeoForgeRecipeProvider.has(khazadSteelArmorPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.RAVENHILL_WATCHWARDEN_BOOTS).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.EREBOR_CAPTAIN_HELMET.getDefaultInstance(), "helmet", Disposition.GOOD)
                .input(Items.GOAT_HORN)
                .input(ModEquipmentItems.EREBOR_GUARD_HELMET)
                .input(Items.GOAT_HORN)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.EREBOR_GUARD_HELMET),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.EREBOR_GUARD_HELMET))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.EREBOR_CAPTAIN_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.RAVENHILL_SENTINEL_HELMET.getDefaultInstance(), "helmet", Disposition.GOOD)
                .input(Items.FEATHER)
                .input(ModEquipmentItems.RAVENHILL_WATCHWARDEN_HELMET)
                .input(Items.FEATHER)
                .componentInput(new ComponentsIngredient(Ingredient.of(goldArmorPlate.getItem()), goldArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(goldArmorPlate.getItem()), goldArmorPlate.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.RAVENHILL_WATCHWARDEN_HELMET),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.RAVENHILL_WATCHWARDEN_HELMET))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.RAVENHILL_SENTINEL_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.RAVENHILL_SENTINEL_CHESTPLATE.getDefaultInstance(), "chestplate", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(goldArmorPlate.getItem()), goldArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(goldArmorPlate.getItem()), goldArmorPlate.getComponentsPatch()))
                .input(ModResourceItems.FABRIC)
                .input(ModEquipmentItems.RAVENHILL_WATCHWARDEN_CHESTPLATE)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .componentInput(new ComponentsIngredient(Ingredient.of(bronzeArmorPlate.getItem()), bronzeArmorPlate.getComponentsPatch()))
                .input(ModResourceItems.FABRIC)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.RAVENHILL_WATCHWARDEN_CHESTPLATE),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.RAVENHILL_WATCHWARDEN_CHESTPLATE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.RAVENHILL_SENTINEL_CHESTPLATE).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.RAVENHILL_SENTINEL_LEGGINGS.getDefaultInstance(), "leggings", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelArmorPlate.getItem()), khazadSteelArmorPlate.getComponentsPatch()))
                .input(ModEquipmentItems.RAVENHILL_WATCHWARDEN_LEGGINGS)
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelArmorPlate.getItem()), khazadSteelArmorPlate.getComponentsPatch()))
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.RAVENHILL_WATCHWARDEN_LEGGINGS),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.RAVENHILL_WATCHWARDEN_LEGGINGS))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.RAVENHILL_SENTINEL_LEGGINGS).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.RAVENHILL_SENTINEL_BOOTS.getDefaultInstance(), "boots", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(goldArmorPlate.getItem()), goldArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(goldArmorPlate.getItem()), goldArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelArmorPlate.getItem()), khazadSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelArmorPlate.getItem()), khazadSteelArmorPlate.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(khazadSteelArmorPlate.getItem()),
                        NeoForgeRecipeProvider.has(khazadSteelArmorPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.RAVENHILL_SENTINEL_BOOTS).getPath() + "_artisan"));

        //capes
        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.EREBOR_CAPE.getDefaultInstance(), "cape", Disposition.GOOD)
                .input(ModResourceItems.FABRIC)
                .input(Items.STRING)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(Items.BLUE_DYE)
                .input(ModResourceItems.FABRIC)
                .input(Items.BLUE_DYE)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.FABRIC),
                        NeoForgeRecipeProvider.has(ModResourceItems.FABRIC))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.EREBOR_CAPE).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.RAVENHILL_SENTINEL_CAPE.getDefaultInstance(), "cape", Disposition.GOOD)
                .input(ModResourceItems.FABRIC)
                .input(Items.STRING)
                .input(ModResourceItems.FABRIC)
                .input(Items.GRAY_DYE)
                .input(ModResourceItems.FABRIC)
                .input(Items.GRAY_DYE)
                .input(Items.YELLOW_DYE)
                .input(ModResourceItems.FABRIC)
                .input(Items.YELLOW_DYE)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.FABRIC),
                        NeoForgeRecipeProvider.has(ModResourceItems.FABRIC))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.RAVENHILL_SENTINEL_CAPE).getPath() + "_artisan"));

        //endregion

        //endregion

        //region ORCS

        //region GENERIC
        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.ORCISH_LEATHER_STRAP.getDefaultInstance(), "chestplate", Disposition.EVIL)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.LEATHER),
                        NeoForgeRecipeProvider.has(Items.LEATHER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.ORCISH_LEATHER_STRAP).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.ORCISH_SANDALS.getDefaultInstance(), "boots", Disposition.EVIL)
                .input(Items.STICK)
                .input(Items.STICK)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.FABRIC),
                        NeoForgeRecipeProvider.has(ModResourceItems.FABRIC))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.ORCISH_SANDALS).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.RUSTED_ORCISH_MAIL_COIF.getDefaultInstance(), "helmet", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ironChainmail.getItem()),
                        NeoForgeRecipeProvider.has(ironChainmail.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.RUSTED_ORCISH_MAIL_COIF).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.ORCISH_LEATHER_CHESTPLATE.getDefaultInstance(), "chestplate", Disposition.EVIL)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.LEATHER),
                        NeoForgeRecipeProvider.has(Items.LEATHER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.ORCISH_LEATHER_CHESTPLATE).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.ORCISH_LEATHER_SCALE_VEST.getDefaultInstance(), "chestplate", Disposition.EVIL)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.LEATHER),
                        NeoForgeRecipeProvider.has(Items.LEATHER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.ORCISH_LEATHER_SCALE_VEST).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.RUSTED_ORCISH_MAIL_HAUBERK.getDefaultInstance(), "chestplate", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ironChainmail.getItem()),
                        NeoForgeRecipeProvider.has(ironChainmail.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.RUSTED_ORCISH_MAIL_HAUBERK).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.RUSTED_ORCISH_MAIL_SHIRT.getDefaultInstance(), "chestplate", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ironChainmail.getItem()),
                        NeoForgeRecipeProvider.has(ironChainmail.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.RUSTED_ORCISH_MAIL_SHIRT).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.ORCISH_LEATHER_SKIRT.getDefaultInstance(), "leggings", Disposition.EVIL)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.LEATHER),
                        NeoForgeRecipeProvider.has(Items.LEATHER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.ORCISH_LEATHER_SKIRT).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.ORCISH_STRIP_LEATHER_SKIRT.getDefaultInstance(), "leggings", Disposition.EVIL)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.LEATHER),
                        NeoForgeRecipeProvider.has(Items.LEATHER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.ORCISH_STRIP_LEATHER_SKIRT).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.ORCISH_LEG_BRACER.getDefaultInstance(), "leggings", Disposition.EVIL)
                .input(Items.LEATHER)
                .input(Items.IRON_NUGGET)
                .input(Items.LEATHER)
                .input(Items.IRON_NUGGET)
                .input(Items.IRON_NUGGET)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.LEATHER),
                        NeoForgeRecipeProvider.has(Items.LEATHER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.ORCISH_LEG_BRACER).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.RUSTED_ORCISH_MAIL_COAT.getDefaultInstance(), "leggings", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ironChainmail.getItem()),
                        NeoForgeRecipeProvider.has(ironChainmail.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.RUSTED_ORCISH_MAIL_COAT).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.RUSTED_ORCISH_REINFORCED_LEATHER_SKIRT.getDefaultInstance(), "leggings", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ironChainmail.getItem()),
                        NeoForgeRecipeProvider.has(ironChainmail.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.RUSTED_ORCISH_REINFORCED_LEATHER_SKIRT).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.RUSTED_ORCISH_REINFORCED_STRIP_LEATHER_SKIRT.getDefaultInstance(), "leggings", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ironChainmail.getItem()),
                        NeoForgeRecipeProvider.has(ironChainmail.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.RUSTED_ORCISH_REINFORCED_STRIP_LEATHER_SKIRT).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.ORCISH_BLACK_FUR_BOOTS.getDefaultInstance(), "boots", Disposition.EVIL)
                .input(ModResourceItems.FUR)
                .input(ModResourceItems.FUR)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.FUR),
                        NeoForgeRecipeProvider.has(ModResourceItems.FUR))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.ORCISH_BLACK_FUR_BOOTS).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.ORCISH_BROWN_FUR_BOOTS.getDefaultInstance(), "boots", Disposition.EVIL)
                .input(ModResourceItems.FUR)
                .input(ModResourceItems.FUR)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.FUR),
                        NeoForgeRecipeProvider.has(ModResourceItems.FUR))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.ORCISH_BROWN_FUR_BOOTS).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.ORCISH_GRAY_FUR_BOOTS.getDefaultInstance(), "boots", Disposition.EVIL)
                .input(ModResourceItems.FUR)
                .input(ModResourceItems.FUR)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.FUR),
                        NeoForgeRecipeProvider.has(ModResourceItems.FUR))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.ORCISH_GRAY_FUR_BOOTS).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.ORCISH_TAN_FUR_BOOTS.getDefaultInstance(), "boots", Disposition.EVIL)
                .input(ModResourceItems.FUR)
                .input(ModResourceItems.FUR)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.FUR),
                        NeoForgeRecipeProvider.has(ModResourceItems.FUR))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.ORCISH_TAN_FUR_BOOTS).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.ORCISH_WHITE_FUR_BOOTS.getDefaultInstance(), "boots", Disposition.EVIL)
                .input(ModResourceItems.FUR)
                .input(ModResourceItems.FUR)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.FUR),
                        NeoForgeRecipeProvider.has(ModResourceItems.FUR))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.ORCISH_WHITE_FUR_BOOTS).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.ORCISH_BRACED_HELMET.getDefaultInstance(), "helmet", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironArmorPlate.getItem()), ironArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironHelmetPlate.getItem()), ironHelmetPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironArmorPlate.getItem()), ironArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ironHelmetPlate.getItem()),
                        NeoForgeRecipeProvider.has(ironHelmetPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.ORCISH_BRACED_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.ORCISH_HELMET.getDefaultInstance(), "helmet", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironArmorPlate.getItem()), ironArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironHelmetPlate.getItem()), ironHelmetPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironArmorPlate.getItem()), ironArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ironHelmetPlate.getItem()),
                        NeoForgeRecipeProvider.has(ironHelmetPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.ORCISH_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.ORCISH_MAIL_COIF.getDefaultInstance(), "helmet", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ironChainmail.getItem()),
                        NeoForgeRecipeProvider.has(ironChainmail.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.ORCISH_MAIL_COIF).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.ORCISH_MAIL_HAUBERK.getDefaultInstance(), "chestplate", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ironChainmail.getItem()),
                        NeoForgeRecipeProvider.has(ironChainmail.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.ORCISH_MAIL_HAUBERK).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.ORCISH_MAIL_SHIRT.getDefaultInstance(), "chestplate", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ironChainmail.getItem()),
                        NeoForgeRecipeProvider.has(ironChainmail.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.ORCISH_MAIL_SHIRT).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.ORCISH_MAIL_COAT.getDefaultInstance(), "leggings", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ironChainmail.getItem()),
                        NeoForgeRecipeProvider.has(ironChainmail.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.ORCISH_MAIL_COAT).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.ORCISH_REINFORCED_LEATHER_SKIRT.getDefaultInstance(), "leggings", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ironChainmail.getItem()),
                        NeoForgeRecipeProvider.has(ironChainmail.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.ORCISH_REINFORCED_LEATHER_SKIRT).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.ORCISH_REINFORCED_STRIP_LEATHER_SKIRT.getDefaultInstance(), "leggings", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ironChainmail.getItem()),
                        NeoForgeRecipeProvider.has(ironChainmail.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.ORCISH_REINFORCED_STRIP_LEATHER_SKIRT).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.ORCISH_SALLET.getDefaultInstance(), "helmet", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelHelmetPlate.getItem()), burzumSteelHelmetPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(burzumSteelHelmetPlate.getItem()),
                        NeoForgeRecipeProvider.has(burzumSteelHelmetPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.ORCISH_SALLET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.ORCISH_BELLY_PLATE_CHESTPLATE.getDefaultInstance(), "chestplate", Disposition.EVIL)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelScaleMail.getItem()), burzumSteelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelScaleMail.getItem()), burzumSteelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelScaleMail.getItem()), burzumSteelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelScaleMail.getItem()), burzumSteelScaleMail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(burzumSteelScaleMail.getItem()),
                        NeoForgeRecipeProvider.has(burzumSteelScaleMail.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.ORCISH_BELLY_PLATE_CHESTPLATE).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.ORCISH_SCALE_VEST.getDefaultInstance(), "chestplate", Disposition.EVIL)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelScaleMail.getItem()), burzumSteelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelScaleMail.getItem()), burzumSteelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelScaleMail.getItem()), burzumSteelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelScaleMail.getItem()), burzumSteelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelScaleMail.getItem()), burzumSteelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelScaleMail.getItem()), burzumSteelScaleMail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(burzumSteelScaleMail.getItem()),
                        NeoForgeRecipeProvider.has(burzumSteelScaleMail.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.ORCISH_SCALE_VEST).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.ORCISH_REINFORCED_LEG_BRACER.getDefaultInstance(), "leggings", Disposition.EVIL)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(burzumSteelArmorPlate.getItem()),
                        NeoForgeRecipeProvider.has(burzumSteelArmorPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.ORCISH_REINFORCED_LEG_BRACER).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.ORCISH_BRACED_SANDALS.getDefaultInstance(), "boots", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch())).input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)

                .unlockedBy(NeoForgeRecipeProvider.getHasName(burzumSteelArmorPlate.getItem()),
                        NeoForgeRecipeProvider.has(burzumSteelArmorPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.ORCISH_BRACED_SANDALS).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.ORCISH_CAPE.getDefaultInstance(), "cape", Disposition.EVIL)
                .input(ModResourceItems.FABRIC)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "bones")))
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "bones")))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "bones")))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "bones")))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.FABRIC),
                        NeoForgeRecipeProvider.has(ModResourceItems.FABRIC))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.ORCISH_CAPE).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.ORCISH_LONG_CAPE.getDefaultInstance(), "cape", Disposition.EVIL)
                .input(ModResourceItems.FABRIC)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "bones")))
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.FABRIC),
                        NeoForgeRecipeProvider.has(ModResourceItems.FABRIC))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.ORCISH_LONG_CAPE).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.ORCISH_SHOULDERS.getDefaultInstance(), "cape", Disposition.EVIL)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(Items.RED_DYE)
                .input(ModResourceItems.FABRIC)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.FABRIC),
                        NeoForgeRecipeProvider.has(ModResourceItems.FABRIC))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.ORCISH_SHOULDERS).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.ORCISH_BLACK_FUR_SURCOAT_WITH_BONE.getDefaultInstance(), "cape", Disposition.EVIL)
                .input(ModResourceItems.FUR)
                .input(ModResourceItems.FUR)
                .input(ModResourceItems.FUR)
                .input(ModResourceItems.FUR)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "bones")))
                .input(ModResourceItems.FUR)
                .input(Items.STRING)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "bones")))
                .input(Items.STRING)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.FUR),
                        NeoForgeRecipeProvider.has(ModResourceItems.FUR))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.ORCISH_BLACK_FUR_SURCOAT_WITH_BONE).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.ORCISH_BROWN_FUR_SURCOAT_WITH_BONE.getDefaultInstance(), "cape", Disposition.EVIL)
                .input(ModResourceItems.FUR)
                .input(ModResourceItems.FUR)
                .input(ModResourceItems.FUR)
                .input(ModResourceItems.FUR)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "bones")))
                .input(ModResourceItems.FUR)
                .input(Items.STRING)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "bones")))
                .input(Items.STRING)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.FUR),
                        NeoForgeRecipeProvider.has(ModResourceItems.FUR))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.ORCISH_BROWN_FUR_SURCOAT_WITH_BONE).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.ORCISH_GRAY_FUR_SURCOAT_WITH_BONE.getDefaultInstance(), "cape", Disposition.EVIL)
                .input(ModResourceItems.FUR)
                .input(ModResourceItems.FUR)
                .input(ModResourceItems.FUR)
                .input(ModResourceItems.FUR)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "bones")))
                .input(ModResourceItems.FUR)
                .input(Items.STRING)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "bones")))
                .input(Items.STRING)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.FUR),
                        NeoForgeRecipeProvider.has(ModResourceItems.FUR))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.ORCISH_GRAY_FUR_SURCOAT_WITH_BONE).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.ORCISH_TAN_FUR_SURCOAT_WITH_BONE.getDefaultInstance(), "cape", Disposition.EVIL)
                .input(ModResourceItems.FUR)
                .input(ModResourceItems.FUR)
                .input(ModResourceItems.FUR)
                .input(ModResourceItems.FUR)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "bones")))
                .input(ModResourceItems.FUR)
                .input(Items.STRING)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "bones")))
                .input(Items.STRING)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.FUR),
                        NeoForgeRecipeProvider.has(ModResourceItems.FUR))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.ORCISH_TAN_FUR_SURCOAT_WITH_BONE).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.ORCISH_WHITE_FUR_SURCOAT_WITH_BONE.getDefaultInstance(), "cape", Disposition.EVIL)
                .input(ModResourceItems.FUR)
                .input(ModResourceItems.FUR)
                .input(ModResourceItems.FUR)
                .input(ModResourceItems.FUR)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "bones")))
                .input(ModResourceItems.FUR)
                .input(Items.STRING)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "bones")))
                .input(Items.STRING)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.FUR),
                        NeoForgeRecipeProvider.has(ModResourceItems.FUR))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.ORCISH_WHITE_FUR_SURCOAT_WITH_BONE).getPath() + "_artisan"));
        //endregion

        //region MORDOR
        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.MORDOR_HELMET.getDefaultInstance(), "helmet", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironArmorPlate.getItem()), ironArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironHelmetPlate.getItem()), ironHelmetPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironArmorPlate.getItem()), ironArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ironHelmetPlate.getItem()),
                        NeoForgeRecipeProvider.has(ironHelmetPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.MORDOR_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.RUSTED_MORDOR_HELMET.getDefaultInstance(), "helmet", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironArmorPlate.getItem()), ironArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironHelmetPlate.getItem()), ironHelmetPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironArmorPlate.getItem()), ironArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ironHelmetPlate.getItem()),
                        NeoForgeRecipeProvider.has(ironHelmetPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.RUSTED_MORDOR_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.MORDOR_KETTLE_HAT.getDefaultInstance(), "helmet", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironArmorPlate.getItem()), ironArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironHelmetPlate.getItem()), ironHelmetPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironArmorPlate.getItem()), ironArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ironHelmetPlate.getItem()),
                        NeoForgeRecipeProvider.has(ironHelmetPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.MORDOR_KETTLE_HAT).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.RUSTED_MORDOR_KETTLE_HAT.getDefaultInstance(), "helmet", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironArmorPlate.getItem()), ironArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironHelmetPlate.getItem()), ironHelmetPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironArmorPlate.getItem()), ironArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ironHelmetPlate.getItem()),
                        NeoForgeRecipeProvider.has(ironHelmetPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.RUSTED_MORDOR_KETTLE_HAT).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.MORDOR_NASAL_HELMET.getDefaultInstance(), "helmet", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironArmorPlate.getItem()), ironArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironHelmetPlate.getItem()), ironHelmetPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironArmorPlate.getItem()), ironArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ironHelmetPlate.getItem()),
                        NeoForgeRecipeProvider.has(ironHelmetPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.MORDOR_NASAL_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.RUSTED_MORDOR_NASAL_HELMET.getDefaultInstance(), "helmet", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironArmorPlate.getItem()), ironArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironHelmetPlate.getItem()), ironHelmetPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironArmorPlate.getItem()), ironArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ironHelmetPlate.getItem()),
                        NeoForgeRecipeProvider.has(ironHelmetPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.RUSTED_MORDOR_NASAL_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.RUSTED_MORDOR_KETTLE_HAT_WITH_COIF.getDefaultInstance(), "helmet", Disposition.EVIL)
                .input(ModEquipmentItems.ORCISH_MAIL_COIF)
                .input(ModEquipmentItems.MORDOR_KETTLE_HAT)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.ORCISH_MAIL_COIF),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.ORCISH_MAIL_COIF))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.RUSTED_MORDOR_KETTLE_HAT_WITH_COIF).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.MORDOR_KETTLE_HAT_WITH_COIF.getDefaultInstance(), "helmet", Disposition.EVIL)
                .input(ModEquipmentItems.ORCISH_MAIL_COIF)
                .input(ModEquipmentItems.MORDOR_KETTLE_HAT)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.ORCISH_MAIL_COIF),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.ORCISH_MAIL_COIF))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.MORDOR_KETTLE_HAT_WITH_COIF).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.MORDOR_LEATHER_CUIRASS.getDefaultInstance(), "chestplate", Disposition.EVIL)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(ModResourceItems.FABRIC)
                .input(Items.LEATHER)
                .input(ModResourceItems.FABRIC)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.LEATHER),
                        NeoForgeRecipeProvider.has(Items.LEATHER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.MORDOR_LEATHER_CUIRASS).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.MORDOR_PAINTED_LEATHER_CUIRASS.getDefaultInstance(), "chestplate", Disposition.EVIL)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(Items.RED_DYE)
                .input(ModEquipmentItems.MORDOR_LEATHER_CUIRASS)
                .input(Items.RED_DYE)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.MORDOR_LEATHER_CUIRASS),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.MORDOR_LEATHER_CUIRASS))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.MORDOR_PAINTED_LEATHER_CUIRASS).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.MORDOR_CREST_HELMET.getDefaultInstance(), "helmet", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelHelmetPlate.getItem()), burzumSteelHelmetPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(burzumSteelHelmetPlate.getItem()),
                        NeoForgeRecipeProvider.has(burzumSteelHelmetPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.MORDOR_CREST_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.RUSTED_MORDOR_CREST_HELMET.getDefaultInstance(), "helmet", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelHelmetPlate.getItem()), burzumSteelHelmetPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(burzumSteelHelmetPlate.getItem()),
                        NeoForgeRecipeProvider.has(burzumSteelHelmetPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.RUSTED_MORDOR_CREST_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.MORDOR_MANDIBLE_HELMET.getDefaultInstance(), "helmet", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelHelmetPlate.getItem()), burzumSteelHelmetPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(burzumSteelHelmetPlate.getItem()),
                        NeoForgeRecipeProvider.has(burzumSteelHelmetPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.MORDOR_MANDIBLE_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.RUSTED_MORDOR_MANDIBLE_HELMET.getDefaultInstance(), "helmet", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelHelmetPlate.getItem()), burzumSteelHelmetPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(burzumSteelHelmetPlate.getItem()),
                        NeoForgeRecipeProvider.has(burzumSteelHelmetPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.RUSTED_MORDOR_MANDIBLE_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.MORDOR_SALLET.getDefaultInstance(), "helmet", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelHelmetPlate.getItem()), burzumSteelHelmetPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(burzumSteelHelmetPlate.getItem()),
                        NeoForgeRecipeProvider.has(burzumSteelHelmetPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.MORDOR_SALLET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.RUSTED_MORDOR_SALLET.getDefaultInstance(), "helmet", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelHelmetPlate.getItem()), burzumSteelHelmetPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(burzumSteelHelmetPlate.getItem()),
                        NeoForgeRecipeProvider.has(burzumSteelHelmetPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.RUSTED_MORDOR_SALLET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.MORDOR_CUIRASS.getDefaultInstance(), "chestplate", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .input(ModEquipmentItems.MORDOR_LEATHER_CUIRASS)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.MORDOR_LEATHER_CUIRASS),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.MORDOR_LEATHER_CUIRASS))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.MORDOR_CUIRASS).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.RUSTED_MORDOR_CUIRASS.getDefaultInstance(), "chestplate", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .input(ModEquipmentItems.MORDOR_LEATHER_CUIRASS)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.MORDOR_LEATHER_CUIRASS),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.MORDOR_LEATHER_CUIRASS))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.RUSTED_MORDOR_CUIRASS).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.MORDOR_GORGET_HAUBERK.getDefaultInstance(), "chestplate", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .input(Items.LEATHER)
                .input(ModEquipmentItems.ORCISH_MAIL_HAUBERK)
                .input(Items.LEATHER)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .input(Items.LEATHER)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.ORCISH_MAIL_HAUBERK),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.ORCISH_MAIL_HAUBERK))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.MORDOR_GORGET_HAUBERK).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.RUSTED_MORDOR_GORGET_HAUBERK.getDefaultInstance(), "chestplate", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .input(Items.LEATHER)
                .input(ModEquipmentItems.ORCISH_MAIL_HAUBERK)
                .input(Items.LEATHER)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .input(Items.LEATHER)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.ORCISH_MAIL_HAUBERK),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.ORCISH_MAIL_HAUBERK))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.RUSTED_MORDOR_GORGET_HAUBERK).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.MORDOR_REINFORCED_COAT.getDefaultInstance(), "chestplate", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .input(ModEquipmentItems.MORDOR_LEATHER_CUIRASS)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.MORDOR_LEATHER_CUIRASS),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.MORDOR_LEATHER_CUIRASS))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.MORDOR_REINFORCED_COAT).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.RUSTED_MORDOR_REINFORCED_COAT.getDefaultInstance(), "chestplate", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .input(ModEquipmentItems.MORDOR_LEATHER_CUIRASS)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.MORDOR_LEATHER_CUIRASS),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.MORDOR_LEATHER_CUIRASS))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.RUSTED_MORDOR_REINFORCED_COAT).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.MORDOR_CHESTPLATE.getDefaultInstance(), "chestplate", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .input(ModEquipmentItems.ORCISH_MAIL_HAUBERK)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .input(Items.LEATHER)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.ORCISH_MAIL_HAUBERK),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.ORCISH_MAIL_HAUBERK))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.MORDOR_CHESTPLATE).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.RUSTED_MORDOR_CHESTPLATE.getDefaultInstance(), "chestplate", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .input(ModEquipmentItems.ORCISH_MAIL_HAUBERK)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .input(Items.LEATHER)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.ORCISH_MAIL_HAUBERK),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.ORCISH_MAIL_HAUBERK))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.RUSTED_MORDOR_CHESTPLATE).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.MORDOR_PLATE_BOOTS.getDefaultInstance(), "boots", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(burzumSteelArmorPlate.getItem()),
                        NeoForgeRecipeProvider.has(burzumSteelArmorPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.MORDOR_PLATE_BOOTS).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.RUSTED_MORDOR_PLATE_BOOTS.getDefaultInstance(), "boots", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(burzumSteelArmorPlate.getItem()),
                        NeoForgeRecipeProvider.has(burzumSteelArmorPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.RUSTED_MORDOR_PLATE_BOOTS).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.MORDOR_GREAT_HELMET.getDefaultInstance(), "helmet", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelHelmetPlate.getItem()), burzumSteelHelmetPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(burzumSteelHelmetPlate.getItem()),
                        NeoForgeRecipeProvider.has(burzumSteelHelmetPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.MORDOR_GREAT_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.RUSTED_MORDOR_GREAT_HELMET.getDefaultInstance(), "helmet", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelHelmetPlate.getItem()), burzumSteelHelmetPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(burzumSteelHelmetPlate.getItem()),
                        NeoForgeRecipeProvider.has(burzumSteelHelmetPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.RUSTED_MORDOR_GREAT_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.MORDOR_SNOUT_HELMET.getDefaultInstance(), "helmet", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelHelmetPlate.getItem()), burzumSteelHelmetPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(burzumSteelHelmetPlate.getItem()),
                        NeoForgeRecipeProvider.has(burzumSteelHelmetPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.MORDOR_SNOUT_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.RUSTED_MORDOR_SNOUT_HELMET.getDefaultInstance(), "helmet", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelHelmetPlate.getItem()), burzumSteelHelmetPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(burzumSteelHelmetPlate.getItem()),
                        NeoForgeRecipeProvider.has(burzumSteelHelmetPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.RUSTED_MORDOR_SNOUT_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.MORDOR_DEGRADED_GONDORIAN_CHESTPLATE.getDefaultInstance(), "chestplate", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .input(Items.LEATHER)
                .input(ModEquipmentItems.GONDORIAN_PLATE_CHESTPLATE)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .input(Items.RED_DYE)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.GONDORIAN_PLATE_CHESTPLATE),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.GONDORIAN_PLATE_CHESTPLATE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.MORDOR_DEGRADED_GONDORIAN_CHESTPLATE).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.MORDOR_PAINTED_CUIRASS.getDefaultInstance(), "chestplate", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .input(Items.RED_DYE)
                .input(ModEquipmentItems.MORDOR_CUIRASS)
                .input(Items.RED_DYE)
                .input(ModResourceItems.FABRIC)
                .input(Items.RED_DYE)
                .input(ModResourceItems.FABRIC)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.MORDOR_CUIRASS),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.MORDOR_CUIRASS))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.MORDOR_PAINTED_CUIRASS).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.MORDOR_SCALE_COAT.getDefaultInstance(), "leggings", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelScaleMail.getItem()), burzumSteelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelScaleMail.getItem()), burzumSteelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelScaleMail.getItem()), burzumSteelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelScaleMail.getItem()), burzumSteelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelScaleMail.getItem()), burzumSteelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelScaleMail.getItem()), burzumSteelScaleMail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelScaleMail.getItem()), burzumSteelScaleMail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(burzumSteelScaleMail.getItem()),
                        NeoForgeRecipeProvider.has(burzumSteelScaleMail.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.MORDOR_SCALE_COAT).getPath() + "_artisan"));

        //T5
        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.BLACK_URUK_PLATE_HELMET.getDefaultInstance(), "helmet", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelHelmetPlate.getItem()), burzumSteelHelmetPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(burzumSteelHelmetPlate.getItem()),
                        NeoForgeRecipeProvider.has(burzumSteelHelmetPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.BLACK_URUK_PLATE_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.BLACK_URUK_PLATE_CHESTPLATE.getDefaultInstance(), "chestplate", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .input(ModEquipmentItems.MORDOR_CHESTPLATE)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.MORDOR_CHESTPLATE),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.MORDOR_CHESTPLATE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.BLACK_URUK_PLATE_CHESTPLATE).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.BLACK_URUK_PLATE_LEGGINGS.getDefaultInstance(), "leggings", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .input(ModEquipmentItems.ORCISH_MAIL_COAT)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.ORCISH_MAIL_COAT),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.ORCISH_MAIL_COAT))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.BLACK_URUK_PLATE_LEGGINGS).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.BLACK_URUK_PLATE_BOOTS.getDefaultInstance(), "boots", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(burzumSteelArmorPlate.getItem()),
                        NeoForgeRecipeProvider.has(burzumSteelArmorPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.BLACK_URUK_PLATE_BOOTS).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.BLACK_URUK_FACE_PLATE_HELMET.getDefaultInstance(), "helmet", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelHelmetPlate.getItem()), burzumSteelHelmetPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(burzumSteelHelmetPlate.getItem()),
                        NeoForgeRecipeProvider.has(burzumSteelHelmetPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.BLACK_URUK_FACE_PLATE_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.BLACK_URUK_COMMANDER_HELMET.getDefaultInstance(), "helmet", Disposition.EVIL)
                .input(Items.SKELETON_SKULL)
                .input(ModEquipmentItems.BLACK_URUK_PLATE_HELMET)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .input(Items.RED_DYE)
                .input(Items.RED_DYE)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.BLACK_URUK_PLATE_HELMET),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.BLACK_URUK_PLATE_HELMET))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.BLACK_URUK_COMMANDER_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.BLACK_URUK_COMMANDER_CHESTPLATE.getDefaultInstance(), "chestplate", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .input(Items.RED_DYE)
                .input(ModEquipmentItems.BLACK_URUK_PLATE_CHESTPLATE)
                .input(Items.RED_DYE)
                .input(ModResourceItems.FABRIC)
                .input(Items.RED_DYE)
                .input(ModResourceItems.FABRIC)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.BLACK_URUK_PLATE_CHESTPLATE),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.BLACK_URUK_PLATE_CHESTPLATE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.BLACK_URUK_COMMANDER_CHESTPLATE).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.MORDOR_BLACK_NUMENOREAN_PLATE_HELMET.getDefaultInstance(), "helmet", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelHelmetPlate.getItem()), steelHelmetPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(steelHelmetPlate.getItem()),
                        NeoForgeRecipeProvider.has(steelHelmetPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.MORDOR_BLACK_NUMENOREAN_PLATE_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.MORDOR_BLACK_NUMENOREAN_PLATE_CHESTPLATE.getDefaultInstance(), "chestplate", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .input(ModEquipmentItems.MAIL_HAUBERK)
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.MAIL_HAUBERK),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.MAIL_HAUBERK))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.MORDOR_BLACK_NUMENOREAN_PLATE_CHESTPLATE).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.MORDOR_BLACK_NUMENOREAN_PLATE_LEGGINGS.getDefaultInstance(), "leggings", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .input(ModEquipmentItems.MAIL_SKIRT)
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelChainmail.getItem()), steelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelChainmail.getItem()), steelChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.MAIL_SKIRT),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.MAIL_SKIRT))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.MORDOR_BLACK_NUMENOREAN_PLATE_LEGGINGS).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.MORDOR_BLACK_NUMENOREAN_PLATE_BOOTS.getDefaultInstance(), "boots", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(steelArmorPlate.getItem()),
                        NeoForgeRecipeProvider.has(steelArmorPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.MORDOR_BLACK_NUMENOREAN_PLATE_BOOTS).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.MORDOR_BLACK_NUMENOREAN_CAPE.getDefaultInstance(), "cape", Disposition.EVIL)
                .input(ModResourceItems.STEEL_NUGGET)
                .input(Items.STRING)
                .input(ModResourceItems.STEEL_NUGGET)
                .input(Items.BLACK_DYE)
                .input(ModResourceItems.FABRIC)
                .input(Items.BLACK_DYE)
                .input(Items.RED_DYE)
                .input(ModResourceItems.FABRIC)
                 .input(Items.RED_DYE)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModResourceItems.FABRIC),
                        NeoForgeRecipeProvider.has(ModResourceItems.FABRIC))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.MORDOR_BLACK_NUMENOREAN_CAPE).getPath() + "_artisan"));

        //endregion

        //region ISENGARD
        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.URUK_HAI_LEATHER_SCOUT_CAP.getDefaultInstance(), "helmet", Disposition.EVIL)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.LEATHER),
                        NeoForgeRecipeProvider.has(Items.LEATHER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.URUK_HAI_LEATHER_SCOUT_CAP).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.URUK_HAI_LEATHER_CHESTPLATE.getDefaultInstance(), "chestplate", Disposition.EVIL)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.LEATHER),
                        NeoForgeRecipeProvider.has(Items.LEATHER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.URUK_HAI_LEATHER_CHESTPLATE).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.URUK_HAI_PAINTED_LEATHER_CHESTPLATE.getDefaultInstance(), "chestplate", Disposition.EVIL)
                .input(ModEquipmentItems.URUK_HAI_LEATHER_CHESTPLATE)
                .input(ModResourceItems.ISENGARD_BANNER_PATTERN)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.URUK_HAI_LEATHER_CHESTPLATE),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.URUK_HAI_LEATHER_CHESTPLATE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.URUK_HAI_PAINTED_LEATHER_CHESTPLATE).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.URUK_HAI_LEATHER_HAUBERK.getDefaultInstance(), "chestplate", Disposition.EVIL)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.LEATHER),
                        NeoForgeRecipeProvider.has(Items.LEATHER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.URUK_HAI_LEATHER_HAUBERK).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.URUK_HAI_BOOTS.getDefaultInstance(), "boots", Disposition.EVIL)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.LEATHER),
                        NeoForgeRecipeProvider.has(Items.LEATHER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.URUK_HAI_BOOTS).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.URUK_HAI_LIGHT_HELMET.getDefaultInstance(), "helmet", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironArmorPlate.getItem()), ironArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironHelmetPlate.getItem()), ironHelmetPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironArmorPlate.getItem()), ironArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ironHelmetPlate.getItem()),
                        NeoForgeRecipeProvider.has(ironHelmetPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.URUK_HAI_LIGHT_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.URUK_HAI_PAINTED_LIGHT_HELMET.getDefaultInstance(), "helmet", Disposition.EVIL)
                .input(ModEquipmentItems.URUK_HAI_LIGHT_HELMET)
                .input(ModResourceItems.ISENGARD_BANNER_PATTERN)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.URUK_HAI_LIGHT_HELMET),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.URUK_HAI_LIGHT_HELMET))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.URUK_HAI_PAINTED_LIGHT_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.URUK_HAI_REINFORCED_HELMET.getDefaultInstance(), "helmet", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironArmorPlate.getItem()), ironArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironHelmetPlate.getItem()), ironHelmetPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironArmorPlate.getItem()), ironArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ironHelmetPlate.getItem()),
                        NeoForgeRecipeProvider.has(ironHelmetPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.URUK_HAI_REINFORCED_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.URUK_HAI_PAINTED_REINFORCED_HELMET.getDefaultInstance(), "helmet", Disposition.EVIL)
                .input(ModEquipmentItems.URUK_HAI_REINFORCED_HELMET)
                .input(ModResourceItems.ISENGARD_BANNER_PATTERN)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.URUK_HAI_REINFORCED_HELMET),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.URUK_HAI_REINFORCED_HELMET))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.URUK_HAI_PAINTED_REINFORCED_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.URUK_HAI_MAIL_HAUBERK.getDefaultInstance(), "chestplate", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ironHelmetPlate.getItem()),
                        NeoForgeRecipeProvider.has(ironHelmetPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.URUK_HAI_MAIL_HAUBERK).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.URUK_HAI_MAIL_COAT.getDefaultInstance(), "leggings", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ironHelmetPlate.getItem()),
                        NeoForgeRecipeProvider.has(ironHelmetPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.URUK_HAI_MAIL_COAT).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.URUK_HAI_MAIL_SKIRT.getDefaultInstance(), "leggings", Disposition.EVIL)
                .input(Items.LEATHER)
                .input(ModEquipmentItems.URUK_HAI_MAIL_COAT)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.URUK_HAI_MAIL_COAT),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.URUK_HAI_MAIL_COAT))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.URUK_HAI_MAIL_SKIRT).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.URUK_HAI_SOLDIER_HELMET.getDefaultInstance(), "helmet", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelHelmetPlate.getItem()), burzumSteelHelmetPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(burzumSteelHelmetPlate.getItem()),
                        NeoForgeRecipeProvider.has(burzumSteelHelmetPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.URUK_HAI_SOLDIER_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.URUK_HAI_PAINTED_SOLDIER_HELMET.getDefaultInstance(), "helmet", Disposition.EVIL)
                .input(ModEquipmentItems.URUK_HAI_SOLDIER_HELMET)
                .input(ModResourceItems.ISENGARD_BANNER_PATTERN)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.URUK_HAI_SOLDIER_HELMET),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.URUK_HAI_SOLDIER_HELMET))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.URUK_HAI_PAINTED_SOLDIER_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.URUK_HAI_CUIRASS.getDefaultInstance(), "chestplate", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .input(Items.LEATHER)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.STRING)
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(burzumSteelArmorPlate.getItem()),
                        NeoForgeRecipeProvider.has(burzumSteelArmorPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.URUK_HAI_CUIRASS).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.URUK_HAI_PLATE_HELMET.getDefaultInstance(), "helmet", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelHelmetPlate.getItem()), burzumSteelHelmetPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(burzumSteelHelmetPlate.getItem()),
                        NeoForgeRecipeProvider.has(burzumSteelHelmetPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.URUK_HAI_PLATE_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.URUK_HAI_PLATE_CHESTPLATE.getDefaultInstance(), "chestplate", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .input(ModEquipmentItems.URUK_HAI_CUIRASS)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.URUK_HAI_CUIRASS),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.URUK_HAI_CUIRASS))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.URUK_HAI_PLATE_CHESTPLATE).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.URUK_HAI_PLATE_LEGGINGS.getDefaultInstance(), "leggings", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .input(ModEquipmentItems.URUK_HAI_MAIL_COAT)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.URUK_HAI_MAIL_COAT),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.URUK_HAI_MAIL_COAT))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.URUK_HAI_PLATE_LEGGINGS).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.URUK_HAI_PLATE_BOOTS.getDefaultInstance(), "boots", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(burzumSteelArmorPlate.getItem()),
                        NeoForgeRecipeProvider.has(burzumSteelArmorPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.URUK_HAI_PLATE_BOOTS).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.URUK_HAI_PAINTED_PLATE_HELMET.getDefaultInstance(), "helmet", Disposition.EVIL)
                .input(ModEquipmentItems.URUK_HAI_PLATE_HELMET)
                .input(ModResourceItems.ISENGARD_BANNER_PATTERN)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.URUK_HAI_PLATE_HELMET),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.URUK_HAI_PLATE_HELMET))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.URUK_HAI_PAINTED_PLATE_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.URUK_HAI_SAPPER_HELMET.getDefaultInstance(), "helmet", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelHelmetPlate.getItem()), burzumSteelHelmetPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(burzumSteelHelmetPlate.getItem()),
                        NeoForgeRecipeProvider.has(burzumSteelHelmetPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.URUK_HAI_SAPPER_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.URUK_HAI_PAINTED_SAPPER_HELMET.getDefaultInstance(), "helmet", Disposition.EVIL)
                .input(ModEquipmentItems.URUK_HAI_SAPPER_HELMET)
                .input(ModResourceItems.ISENGARD_BANNER_PATTERN)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.URUK_HAI_SAPPER_HELMET),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.URUK_HAI_SAPPER_HELMET))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.URUK_HAI_PAINTED_SAPPER_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.URUK_HAI_BERSERKER_HELMET.getDefaultInstance(), "helmet", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelHelmetPlate.getItem()), burzumSteelHelmetPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(burzumSteelHelmetPlate.getItem()),
                        NeoForgeRecipeProvider.has(burzumSteelHelmetPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.URUK_HAI_BERSERKER_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.URUK_HAI_PAINTED_BERSERKER_HELMET.getDefaultInstance(), "helmet", Disposition.EVIL)
                .input(ModEquipmentItems.URUK_HAI_BERSERKER_HELMET)
                .input(ModResourceItems.ISENGARD_BANNER_PATTERN)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.URUK_HAI_BERSERKER_HELMET),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.URUK_HAI_BERSERKER_HELMET))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.URUK_HAI_PAINTED_BERSERKER_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.URUK_HAI_COMMANDER_HELMET.getDefaultInstance(), "helmet", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .input(ModEquipmentItems.URUK_HAI_PLATE_HELMET)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.URUK_HAI_PLATE_HELMET),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.URUK_HAI_PLATE_HELMET))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.URUK_HAI_COMMANDER_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.URUK_HAI_PAINTED_COMMANDER_HELMET.getDefaultInstance(), "helmet", Disposition.EVIL)
                .input(ModEquipmentItems.URUK_HAI_COMMANDER_HELMET)
                .input(ModResourceItems.ISENGARD_BANNER_PATTERN)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.URUK_HAI_COMMANDER_HELMET),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.URUK_HAI_COMMANDER_HELMET))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.URUK_HAI_PAINTED_COMMANDER_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.ORTHANC_GUARD_HELMET.getDefaultInstance(), "helmet", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelHelmetPlate.getItem()), steelHelmetPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(steelHelmetPlate.getItem()),
                        NeoForgeRecipeProvider.has(steelHelmetPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.ORTHANC_GUARD_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.ORTHANC_GUARD_CHESTPLATE.getDefaultInstance(), "chestplate", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .input(ModEquipmentItems.MAIL_HAUBERK)
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.MAIL_HAUBERK),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.MAIL_HAUBERK))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.ORTHANC_GUARD_CHESTPLATE).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.ORTHANC_GUARD_LEGGINGS.getDefaultInstance(), "leggings", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .input(ModEquipmentItems.MAIL_SKIRT)
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelChainmail.getItem()), steelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelChainmail.getItem()), steelChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.MAIL_SKIRT),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.MAIL_SKIRT))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.ORTHANC_GUARD_LEGGINGS).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.ORTHANC_GUARD_BOOTS.getDefaultInstance(), "boots", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(steelArmorPlate.getItem()),
                        NeoForgeRecipeProvider.has(steelArmorPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.ORTHANC_GUARD_BOOTS).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.ORTHANC_COMMANDER_HELMET.getDefaultInstance(), "helmet", Disposition.EVIL)
                .input(Items.FEATHER)
                .input(ModEquipmentItems.ORTHANC_GUARD_HELMET)
                .input(Items.FEATHER)
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(steelHelmetPlate.getItem()),
                        NeoForgeRecipeProvider.has(steelHelmetPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.ORTHANC_COMMANDER_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.ORTHANC_GUARD_CAPE.getDefaultInstance(), "cape", Disposition.EVIL)
                .input(ModResourceItems.FABRIC)
                .input(Items.STRING)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(Items.LIGHT_GRAY_DYE)
                .input(ModResourceItems.FABRIC)
                .input(Items.BLACK_DYE)
                .input(ModResourceItems.FABRIC)
                .input(Items.BLACK_DYE)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(steelHelmetPlate.getItem()),
                        NeoForgeRecipeProvider.has(steelHelmetPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.ORTHANC_GUARD_CAPE).getPath() + "_artisan"));

        //endregion

        //region MISTIES
        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GUNDABAD_BONE_PAULDRON.getDefaultInstance(), "chestplate", Disposition.EVIL)
                .input(Items.LEATHER)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "bones")))
                .input(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "bones")))
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.LEATHER),
                        NeoForgeRecipeProvider.has(Items.LEATHER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GUNDABAD_BONE_PAULDRON).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GUNDABAD_SEEKER_HELMET.getDefaultInstance(), "helmet", Disposition.EVIL)
                .input(Items.LEATHER)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironArmorPlate.getItem()), ironArmorPlate.getComponentsPatch()))
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.LEATHER),
                        NeoForgeRecipeProvider.has(Items.LEATHER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GUNDABAD_SEEKER_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GUNDABAD_LEATHER_SCALE_COAT.getDefaultInstance(), "chestplate", Disposition.EVIL)
                .input(Items.LEATHER)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "bones")))
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.LEATHER),
                        NeoForgeRecipeProvider.has(Items.LEATHER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GUNDABAD_LEATHER_SCALE_COAT).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GUNDABAD_LEATHER_LEGGINGS.getDefaultInstance(), "leggings", Disposition.EVIL)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.LEATHER),
                        NeoForgeRecipeProvider.has(Items.LEATHER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GUNDABAD_LEATHER_LEGGINGS).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GUNDABAD_LACED_BOOTS.getDefaultInstance(), "boots", Disposition.EVIL)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.LEATHER),
                        NeoForgeRecipeProvider.has(Items.LEATHER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GUNDABAD_LACED_BOOTS).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GUNDABAD_SKULLCAP_HELMET.getDefaultInstance(), "helmet", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironArmorPlate.getItem()), ironArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironHelmetPlate.getItem()), ironHelmetPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironArmorPlate.getItem()), ironArmorPlate.getComponentsPatch()))
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ironHelmetPlate.getItem()),
                        NeoForgeRecipeProvider.has(ironHelmetPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GUNDABAD_SKULLCAP_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GUNDABAD_MAIL_COIF.getDefaultInstance(), "helmet", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ironChainmail.getItem()),
                        NeoForgeRecipeProvider.has(ironChainmail.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GUNDABAD_MAIL_COIF).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GUNDABAD_MAIL_HAUBERK.getDefaultInstance(), "chestplate", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ironChainmail.getItem()),
                        NeoForgeRecipeProvider.has(ironChainmail.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GUNDABAD_MAIL_HAUBERK).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GUNDABAD_MAIL_COAT.getDefaultInstance(), "leggings", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ironChainmail.getItem()),
                        NeoForgeRecipeProvider.has(ironChainmail.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GUNDABAD_MAIL_COAT).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GUNDABAD_SOLDIER_HELMET.getDefaultInstance(), "helmet", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelHelmetPlate.getItem()), burzumSteelHelmetPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(burzumSteelHelmetPlate.getItem()),
                        NeoForgeRecipeProvider.has(burzumSteelHelmetPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GUNDABAD_SOLDIER_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GUNDABAD_SCREECHER_HELMET.getDefaultInstance(), "helmet", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelHelmetPlate.getItem()), burzumSteelHelmetPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(burzumSteelHelmetPlate.getItem()),
                        NeoForgeRecipeProvider.has(burzumSteelHelmetPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GUNDABAD_SCREECHER_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GUNDABAD_REINFORCED_LEATHER_SCALE_COAT.getDefaultInstance(), "chestplate", Disposition.EVIL)
                .input(Items.LEATHER)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "bones")))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .input(ModEquipmentItems.GUNDABAD_LEATHER_SCALE_COAT)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .input(Items.LEATHER)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.GUNDABAD_LEATHER_SCALE_COAT),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.GUNDABAD_LEATHER_SCALE_COAT))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GUNDABAD_REINFORCED_LEATHER_SCALE_COAT).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GUNDABAD_REINFORCED_LEATHER_VEST.getDefaultInstance(), "chestplate", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .input(ModEquipmentItems.ORCISH_LEATHER_SCALE_VEST)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.ORCISH_LEATHER_SCALE_VEST),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.ORCISH_LEATHER_SCALE_VEST))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GUNDABAD_REINFORCED_LEATHER_VEST).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GUNDABAD_HOBGOBLIN_PLATE_CRESTED_HELMET.getDefaultInstance(), "helmet", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelHelmetPlate.getItem()), burzumSteelHelmetPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(burzumSteelHelmetPlate.getItem()),
                        NeoForgeRecipeProvider.has(burzumSteelHelmetPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GUNDABAD_HOBGOBLIN_PLATE_CRESTED_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GUNDABAD_HOBGOBLIN_PLATE_CHESTPLATE.getDefaultInstance(), "chestplate", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .input(ModEquipmentItems.GUNDABAD_REINFORCED_LEATHER_VEST)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.GUNDABAD_REINFORCED_LEATHER_VEST),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.GUNDABAD_REINFORCED_LEATHER_VEST))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GUNDABAD_HOBGOBLIN_PLATE_CHESTPLATE).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GUNDABAD_HOBGOBLIN_MAIL_COAT.getDefaultInstance(), "leggings", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .input(ModEquipmentItems.GUNDABAD_MAIL_COAT)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.ORCISH_MAIL_COAT),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.ORCISH_MAIL_COAT))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GUNDABAD_HOBGOBLIN_MAIL_COAT).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GUNDABAD_HOBGOBLIN_PLATED_BOOTS.getDefaultInstance(), "boots", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(burzumSteelArmorPlate.getItem()),
                        NeoForgeRecipeProvider.has(burzumSteelArmorPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GUNDABAD_HOBGOBLIN_PLATED_BOOTS).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GUNDABAD_HOBGOBLIN_PLATE_LARGE_CREST_HELMET.getDefaultInstance(), "helmet", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelHelmetPlate.getItem()), burzumSteelHelmetPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(burzumSteelHelmetPlate.getItem()),
                        NeoForgeRecipeProvider.has(burzumSteelHelmetPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GUNDABAD_HOBGOBLIN_PLATE_LARGE_CREST_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GUNDABAD_HOBGOBLIN_PLATE_LONG_HORN_HELMET.getDefaultInstance(), "helmet", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelHelmetPlate.getItem()), burzumSteelHelmetPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(burzumSteelHelmetPlate.getItem()),
                        NeoForgeRecipeProvider.has(burzumSteelHelmetPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GUNDABAD_HOBGOBLIN_PLATE_LONG_HORN_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GUNDABAD_HOBGOBLIN_PLATE_SMALL_HORN_HELMET.getDefaultInstance(), "helmet", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelHelmetPlate.getItem()), burzumSteelHelmetPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(burzumSteelHelmetPlate.getItem()),
                        NeoForgeRecipeProvider.has(burzumSteelHelmetPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GUNDABAD_HOBGOBLIN_PLATE_SMALL_HORN_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GUNDABAD_CAPTAIN_HELMET.getDefaultInstance(), "helmet", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelHelmetPlate.getItem()), burzumSteelHelmetPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(burzumSteelHelmetPlate.getItem()),
                        NeoForgeRecipeProvider.has(burzumSteelHelmetPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GUNDABAD_CAPTAIN_HELMET).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GUNDABAD_HOBGOBLIN_PLATE_TROPHY_CHESTPLATE.getDefaultInstance(), "chestplate", Disposition.EVIL)
                .input(Items.SKELETON_SKULL)
                .input(Items.SKELETON_SKULL)
                .input(Items.STICK)
                .input(ModEquipmentItems.GUNDABAD_HOBGOBLIN_PLATE_CHESTPLATE)
                .input(Items.STICK)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.GUNDABAD_HOBGOBLIN_PLATE_CHESTPLATE),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.GUNDABAD_MAIL_HAUBERK))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GUNDABAD_HOBGOBLIN_PLATE_TROPHY_CHESTPLATE).getPath() + "_artisan"));

        //endregion

        //endregion

        //MOUNT ARMORS

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.GONDORIAN_HORSE_ARMOR.getDefaultInstance(), "mount_armor", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .input(ModResourceItems.FABRIC)
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .input(ModResourceItems.FABRIC)
                .componentInput(new ComponentsIngredient(Ingredient.of(steelChainmail.getItem()), steelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelChainmail.getItem()), steelChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(steelArmorPlate.getItem()),
                        NeoForgeRecipeProvider.has(steelArmorPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.GONDORIAN_HORSE_ARMOR).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.ROHIRRIC_HORSE_ARMOR.getDefaultInstance(), "mount_armor", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .input(ModResourceItems.FABRIC)
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .input(ModResourceItems.FABRIC)
                .componentInput(new ComponentsIngredient(Ingredient.of(steelChainmail.getItem()), steelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelChainmail.getItem()), steelChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(steelArmorPlate.getItem()),
                        NeoForgeRecipeProvider.has(steelArmorPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.ROHIRRIC_HORSE_ARMOR).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.DALISH_HORSE_ARMOR.getDefaultInstance(), "mount_armor", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .input(ModResourceItems.FABRIC)
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .input(ModResourceItems.FABRIC)
                .componentInput(new ComponentsIngredient(Ingredient.of(steelChainmail.getItem()), steelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelArmorPlate.getItem()), steelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(steelChainmail.getItem()), steelChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(steelArmorPlate.getItem()),
                        NeoForgeRecipeProvider.has(steelArmorPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.DALISH_HORSE_ARMOR).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.LORIEN_HORSE_ARMOR.getDefaultInstance(), "mount_armor", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(edhelSteelArmorPlate.getItem()), edhelSteelArmorPlate.getComponentsPatch()))
                .input(ModResourceItems.FABRIC)
                .componentInput(new ComponentsIngredient(Ingredient.of(edhelSteelArmorPlate.getItem()), edhelSteelArmorPlate.getComponentsPatch()))
                .input(ModResourceItems.FABRIC)
                .componentInput(new ComponentsIngredient(Ingredient.of(edhelSteelChainmail.getItem()), edhelSteelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(edhelSteelArmorPlate.getItem()), edhelSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(edhelSteelChainmail.getItem()), edhelSteelChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(edhelSteelArmorPlate.getItem()),
                        NeoForgeRecipeProvider.has(edhelSteelArmorPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.LORIEN_HORSE_ARMOR).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.BROADHOOF_GOAT_PADDED_ARMOR.getDefaultInstance(), "mount_armor", Disposition.GOOD)
                .input(Items.LEATHER)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(ModResourceItems.FABRIC)
                .input(Items.LEATHER)
                .input(ModResourceItems.FABRIC)
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.LEATHER),
                        NeoForgeRecipeProvider.has(Items.LEATHER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.BROADHOOF_GOAT_PADDED_ARMOR).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.BROADHOOF_GOAT_ORNAMENTED_PADDED_ARMOR.getDefaultInstance(), "mount_armor", Disposition.GOOD)
                .input(Items.LEATHER)
                .input(Items.GOLD_INGOT)
                .input(ModEquipmentItems.BROADHOOF_GOAT_PADDED_ARMOR)
                .input(Items.GOLD_INGOT)
                .input(Items.LEATHER)
                .input(ModResourceItems.FABRIC)
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.BROADHOOF_GOAT_PADDED_ARMOR),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.BROADHOOF_GOAT_PADDED_ARMOR))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.BROADHOOF_GOAT_ORNAMENTED_PADDED_ARMOR).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.BROADHOOF_GOAT_PLATE_ARMOR.getDefaultInstance(), "mount_armor", Disposition.GOOD)
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelArmorPlate.getItem()), khazadSteelArmorPlate.getComponentsPatch()))
                .input(ModResourceItems.FABRIC)
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelArmorPlate.getItem()), khazadSteelArmorPlate.getComponentsPatch()))
                .input(ModResourceItems.FABRIC)
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelChainmail.getItem()), khazadSteelChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelArmorPlate.getItem()), khazadSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(khazadSteelChainmail.getItem()), khazadSteelChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(khazadSteelArmorPlate.getItem()),
                        NeoForgeRecipeProvider.has(khazadSteelArmorPlate.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.BROADHOOF_GOAT_PLATE_ARMOR).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.WARG_LEATHER_ARMOR.getDefaultInstance(), "mount_armor", Disposition.EVIL)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(Items.LEATHER),
                        NeoForgeRecipeProvider.has(Items.LEATHER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.WARG_LEATHER_ARMOR).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.WARG_REINFORCED_LEATHER_ARMOR.getDefaultInstance(), "mount_armor", Disposition.EVIL)
                .input(Items.LEATHER)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "bones")))
                .input(ModEquipmentItems.WARG_LEATHER_ARMOR)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "bones")))
                .input(Items.LEATHER)
                .input(TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, "bones")))
                .input(Items.LEATHER)
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ModEquipmentItems.WARG_LEATHER_ARMOR),
                        NeoForgeRecipeProvider.has(ModEquipmentItems.WARG_LEATHER_ARMOR))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.WARG_REINFORCED_LEATHER_ARMOR).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.WARG_MORDOR_MAIL_ARMOR.getDefaultInstance(), "mount_armor", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .input(Items.LEATHER)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .input(Items.LEATHER)
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(ironChainmail.getItem()), ironChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ironChainmail.getItem()),
                        NeoForgeRecipeProvider.has(ironChainmail.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.WARG_MORDOR_MAIL_ARMOR).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.WARG_MORDOR_PLATE_ARMOR.getDefaultInstance(), "mount_armor", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .input(ModEquipmentItems.WARG_MORDOR_MAIL_ARMOR)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .input(Items.LEATHER)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ironChainmail.getItem()),
                        NeoForgeRecipeProvider.has(ironChainmail.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.WARG_MORDOR_PLATE_ARMOR).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.WARG_ISENGARD_PLATE_ARMOR.getDefaultInstance(), "mount_armor", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .input(Items.LEATHER)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .input(Items.LEATHER)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ironChainmail.getItem()),
                        NeoForgeRecipeProvider.has(ironChainmail.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.WARG_ISENGARD_PLATE_ARMOR).getPath() + "_artisan"));

        ArtisanTableRecipeJsonBuilder.createArtisanRecipe(RecipeCategory.COMBAT, ModEquipmentItems.WARG_GUNDABAD_PLATE_ARMOR.getDefaultInstance(), "mount_armor", Disposition.EVIL)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .input(Items.LEATHER)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelArmorPlate.getItem()), burzumSteelArmorPlate.getComponentsPatch()))
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .input(Items.LEATHER)
                .componentInput(new ComponentsIngredient(Ingredient.of(burzumSteelChainmail.getItem()), burzumSteelChainmail.getComponentsPatch()))
                .unlockedBy(NeoForgeRecipeProvider.getHasName(ironChainmail.getItem()),
                        NeoForgeRecipeProvider.has(ironChainmail.getItem()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(MiddleEarth.MOD_ID, BuiltInRegistries.ITEM.getKey(ModEquipmentItems.WARG_GUNDABAD_PLATE_ARMOR).getPath() + "_artisan"));

    }
}
