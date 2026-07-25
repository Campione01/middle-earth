package net.jukoz.me.resources.datas.factions.data;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.jukoz.me.compat.neoforge.api.util.NbtType;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Unit;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraft.world.level.block.entity.BannerPatternLayers;
import net.minecraft.world.level.block.entity.BannerPatterns;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BannerData {
    public static class BannerPatternWithColor {
        public ResourceLocation id;
        public DyeColor color;
        public ResourceKey<BannerPattern> patternRegistryKey;
        public BannerPattern pattern;

        public BannerPatternWithColor(ResourceLocation id, DyeColor dyeColor){
            this.id = id;
            this.color = dyeColor;
            this.patternRegistryKey = null;
            this.pattern = null;
        }

        public BannerPatternWithColor(ResourceKey<BannerPattern> patternRegistryKey, DyeColor dyeColor) {
            this.patternRegistryKey = patternRegistryKey;
            this.id = patternRegistryKey.location();
            this.color = dyeColor;
            this.pattern = null;
        }
        public void setPattern(BannerPattern pattern){
            this.pattern = pattern;
        }
        @Override
        public String toString() {
            return id + " in " + color.name();
        }
    }
    private DyeColor baseBannerColor;
    private final List<BannerPatternWithColor> bannerPatternWithColors;
    public static final DyeColor DEFAULT_DYE = DyeColor.PINK;

    public BannerData(DyeColor baseBannerColor, List<BannerPatternWithColor> bannerPatternWithColors){
        this.baseBannerColor = baseBannerColor;

        if (bannerPatternWithColors != null)
            this.bannerPatternWithColors = bannerPatternWithColors;
        else
            this.bannerPatternWithColors = new ArrayList<>();

    }

    public BannerData(Optional<CompoundTag> optionalBannerDataNbt) {
        if(optionalBannerDataNbt.isEmpty()){
            bannerPatternWithColors = null;
            return;
        }
        CompoundTag compound = optionalBannerDataNbt.get();

        baseBannerColor = DyeColor.byName(compound.getString("base_color"), DEFAULT_DYE);

        ListTag patterns = compound.getList("patterns", NbtType.COMPOUND);
        this.bannerPatternWithColors = new ArrayList<>();

        JsonParser jsonParser = new JsonParser();

        for(Tag element: patterns){
            JsonObject json = (JsonObject) jsonParser.parse(element.getAsString());
            ResourceLocation id = ResourceLocation.parse(json.get("id").getAsString());
            DyeColor color = DyeColor.byName(json.get("dye_color").getAsString(), DEFAULT_DYE);

            BannerPatternWithColor bannerPatternWithColor = new BannerPatternWithColor(id, color);
            bannerPatternWithColors.add(bannerPatternWithColor);
        }
    }

    public List<BannerData.BannerPatternWithColor> getBannerPatternsWithColors(Level world){
        List<BannerData.BannerPatternWithColor> patterns = new ArrayList<>();
        for(int i = 0; i < bannerPatternWithColors.size(); i++){
            BannerPattern pattern = world.registryAccess().registry(Registries.BANNER_PATTERN).get().get(bannerPatternWithColors.get(i).id);
            if(pattern == null){
                continue;
            }
            bannerPatternWithColors.get(i).setPattern(pattern);
            patterns.add(bannerPatternWithColors.get(i));
        }
        return patterns;
    }

    public DyeColor getBaseDye() {
        return baseBannerColor;
    }

    public Optional<CompoundTag> getNbt() {
        if(baseBannerColor == null || bannerPatternWithColors == null || bannerPatternWithColors.isEmpty())
            return Optional.empty();

        CompoundTag nbt = new CompoundTag();
        nbt.putString("base_color",  getBaseDye().name().toLowerCase());
        ListTag list = new ListTag();
        for(BannerPatternWithColor pattern : bannerPatternWithColors){
            CompoundTag compound = new CompoundTag();
            compound.putString("id", pattern.id.toString());
            compound.putString("dye_color",  pattern.color.name().toLowerCase());
            list.add(compound);
        }
        nbt.put("patterns", list);

        return Optional.of(nbt);
    }

    /**
     * Not datadriven, only for hardcoded factions
     * @param bannerPatternLookup
     * @return
     */
    public BannerPatternLayers getBannerPatternComponents(HolderGetter<BannerPattern> bannerPatternLookup) {
        BannerPatternLayers.Builder bannerPatternsComponentBuilder = new BannerPatternLayers.Builder();

        bannerPatternsComponentBuilder.add(new BannerPatternLayers.Layer(bannerPatternLookup.getOrThrow(BannerPatterns.BASE), baseBannerColor));
        for(BannerPatternWithColor bannerPatternWithColor :  bannerPatternWithColors){
            bannerPatternsComponentBuilder.add(bannerPatternLookup.getOrThrow(bannerPatternWithColor.patternRegistryKey), bannerPatternWithColor.color);
        }

        return bannerPatternsComponentBuilder.build();
    }

    public ItemStack getBannerItem(Level world, Component text) {
        BannerPatternLayers.Builder builder = new BannerPatternLayers.Builder();

        var registry = world.registryAccess().registryOrThrow(Registries.BANNER_PATTERN);
        for(BannerPatternWithColor bannerPatternWithColor :  bannerPatternWithColors){
            Holder<BannerPattern> bannerPattern = registry.getHolder(bannerPatternWithColor.id).get();
            BannerPatternLayers.Layer layer = new BannerPatternLayers.Layer(bannerPattern, bannerPatternWithColor.color);
            builder.add(layer);
        }

        return formatBanner(new ItemStack(Items.WHITE_BANNER), builder.build(), text);
    }

    public static ItemStack formatBanner(ItemStack itemStack, BannerPatternLayers bannerPatternsComponent, Component translationKey) {
        itemStack.set(DataComponents.BANNER_PATTERNS, bannerPatternsComponent);
        itemStack.set(DataComponents.HIDE_ADDITIONAL_TOOLTIP, Unit.INSTANCE);
        itemStack.set(DataComponents.ITEM_NAME, translationKey);
        return itemStack;
    }
}
