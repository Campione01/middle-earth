package net.jukoz.me.client;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BannerPattern;

public final class ModTexturedRenderLayers {
    private static final Map<ResourceLocation, Material> HEATER_SHIELD_PATTERN_TEXTURES = new HashMap<>();
    private static final Map<ResourceLocation, Material> KITE_SHIELD_PATTERN_TEXTURES = new HashMap<>();
    private static final Map<ResourceLocation, Material> ROUND_SHIELD_PATTERN_TEXTURES = new HashMap<>();

    private ModTexturedRenderLayers() {
    }

    public static Material heaterShieldBase() {
        return shieldMaterial("entity/heater_shield/base");
    }

    public static Material kiteShieldBase() {
        return shieldMaterial("entity/kite_shield/base");
    }

    public static Material roundShieldBase() {
        return shieldMaterial("entity/round_shield/base");
    }

    public static Material getHeaterShieldPatternTextureId(Holder<BannerPattern> pattern) {
        return (Material)HEATER_SHIELD_PATTERN_TEXTURES.computeIfAbsent(((BannerPattern)pattern.value()).assetId(), (id) -> {
            ResourceLocation identifier = id.withPrefix("entity/heater_shield/");
            return new Material(Sheets.SHIELD_SHEET, identifier);
        });
    }

    public static Material getKiteShieldPatternTextureId(Holder<BannerPattern> pattern) {
        return (Material)KITE_SHIELD_PATTERN_TEXTURES.computeIfAbsent(((BannerPattern)pattern.value()).assetId(), (id) -> {
            ResourceLocation identifier = id.withPrefix("entity/kite_shield/");
            return new Material(Sheets.SHIELD_SHEET, identifier);
        });
    }

    public static Material getRoundShieldPatternTextureId(Holder<BannerPattern> pattern) {
        return (Material)ROUND_SHIELD_PATTERN_TEXTURES.computeIfAbsent(((BannerPattern)pattern.value()).assetId(), (id) -> {
            ResourceLocation identifier = id.withPrefix("entity/round_shield/");
            return new Material(Sheets.SHIELD_SHEET, identifier);
        });
    }

    private static Material shieldMaterial(String texture) {
        return new Material(Sheets.SHIELD_SHEET, ResourceLocation.parse(texture));
    }
}
