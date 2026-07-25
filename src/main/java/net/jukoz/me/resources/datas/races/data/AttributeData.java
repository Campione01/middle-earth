package net.jukoz.me.resources.datas.races.data;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import it.unimi.dsi.fastutil.Hash;
import net.jukoz.me.compat.neoforge.api.util.NbtType;
import net.jukoz.me.utils.IdentifierUtil;
import net.jukoz.me.utils.LoggerUtil;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AttributeData {
    HashMap<ResourceLocation, Double> datas;
    private static final List<ResourceLocation> buffReverseIdentifiers = List.of(
            IdentifierUtil.getIdentifierFromString(Attributes.SCALE.getRegisteredName()),
            IdentifierUtil.getIdentifierFromString(Attributes.FALL_DAMAGE_MULTIPLIER.getRegisteredName()),
            IdentifierUtil.getIdentifierFromString(Attributes.BURNING_TIME.getRegisteredName())
    );

    public AttributeData(CompoundTag compound) {
        if(compound == null) return;

        datas = new HashMap<>();
        ListTag attributes = compound.getList("datas", NbtType.COMPOUND);
        JsonParser jsonParser = new JsonParser();

        for(Tag element: attributes){
            JsonObject json = (JsonObject) jsonParser.parse(element.getAsString());
            String doubleRegex = "[^0-9.]";

            ResourceLocation id = IdentifierUtil.getIdentifierFromString(json.get("id").getAsString());
            double value = Double.parseDouble(json.get("value").getAsString().replaceAll(doubleRegex, ""));

            datas.put(id, value);
        }
    }
    public AttributeData(HashMap<Holder<Attribute>, Double> attributes) {
        datas = new HashMap<>();
        for(Holder<Attribute> registryEntry : attributes.keySet()){
            ResourceLocation id = IdentifierUtil.getIdentifierFromString(registryEntry.getRegisteredName());
            datas.put(id, attributes.get(registryEntry));
        }
    }

    public CompoundTag getNbt() {
        CompoundTag nbt = new CompoundTag();
        ListTag list = new ListTag();

        for(ResourceLocation id : datas.keySet()){
            CompoundTag compound = new CompoundTag();
            compound.putString("id", id.toString());
            compound.putDouble("value",  datas.get(id));
            list.add(compound);
        }
        nbt.put("datas", list);
        return nbt;
    }

    public void ApplyAll(LivingEntity entity){
        for(ResourceLocation id : datas.keySet()){
            Optional<Holder.Reference<Attribute>> attributeEntry =  BuiltInRegistries.ATTRIBUTE.getHolder(id);
            if(attributeEntry != null && attributeEntry.isPresent()){
                AttributeInstance instance = entity.getAttributes().getInstance(attributeEntry.get());
                if(instance != null){
                    instance.setBaseValue(datas.get(id));
                }
            }
        }
    }

    public double getCurrentValue(LivingEntity entity, ResourceLocation id){
        final RegistryAccess registryManager = entity.level().registryAccess();
        Attribute attribute = registryManager.registryOrThrow(Registries.ATTRIBUTE).get(id);

        Optional<Holder.Reference<Attribute>> attributeEntry = BuiltInRegistries.ATTRIBUTE.getHolder(id);
        if(attribute != null && attributeEntry != null && attributeEntry.isPresent()){
            return entity.getAttributeBaseValue(attributeEntry.get());
        }
        return -999.99;
    }

    public Map<ResourceLocation, Double> getDatas(){
        return datas;
    }

    public boolean isBuffReversed(ResourceLocation id){
        return buffReverseIdentifiers.contains(id);
    }

    private static final HashMap<ResourceLocation, Double> defaultAttributes = new HashMap<>(){{
        put(ResourceLocation.parse("minecraft:generic.armor"), 0.0);
        put(ResourceLocation.parse("minecraft:generic.armor_toughness"), 0.0);
        put(ResourceLocation.parse("minecraft:generic.attack_damage"), 0.9);
        put(ResourceLocation.parse("minecraft:generic.attack_knockback"), 0.0);
        put(ResourceLocation.parse("minecraft:generic.attack_speed"), 4.0);
        put(ResourceLocation.parse("minecraft:generic.burning_time"), 1.0);
        put(ResourceLocation.parse("minecraft:generic.explosion_knockback_resistance"), 0.0);
        put(ResourceLocation.parse("minecraft:generic.fall_damage_multiplier"), 1.0);
        put(ResourceLocation.parse("minecraft:generic.gravity"), 0.08);
        put(ResourceLocation.parse("minecraft:generic.jump_strength"), 0.41999998688697815);
        put(ResourceLocation.parse("minecraft:generic.knockback_resistance"), 0.0);
        put(ResourceLocation.parse("minecraft:generic.luck"), 0.0);
        put(ResourceLocation.parse("minecraft:generic.max_absorption"), 0.0);
        put(ResourceLocation.parse("minecraft:generic.max_health"), 20.0);
        put(ResourceLocation.parse("minecraft:generic.movement_efficiency"), 0.0);
        put(ResourceLocation.parse("minecraft:generic.movement_speed"), 0.10000000149011612);
        put(ResourceLocation.parse("minecraft:generic.oxygen_bonus"), 0.0);
        put(ResourceLocation.parse("minecraft:generic.oxygen_bonus"), 0.0);
        put(ResourceLocation.parse("minecraft:generic.safe_fall_distance"), 3.0);
        put(ResourceLocation.parse("minecraft:generic.scale"), 1.0);
        put(ResourceLocation.parse("minecraft:generic.step_height"), 0.6);
        put(ResourceLocation.parse("minecraft:generic.water_movement_efficiency"), 0.0);

        put(ResourceLocation.parse("minecraft:player.block_break_speed"), 1.0);
        put(ResourceLocation.parse("minecraft:player.block_interaction_range"), 	4.5);
        put(ResourceLocation.parse("minecraft:player.entity_interaction_range"), 3.0);
        put(ResourceLocation.parse("minecraft:player.mining_efficiency"), 0.0);
        put(ResourceLocation.parse("minecraft:player.sneaking_speed"), 0.3);
        put(ResourceLocation.parse("minecraft:player.submerged_mining_speed"), 0.2);
        put(ResourceLocation.parse("minecraft:player.sweeping_damage_ratio"), 0.0);
    }};

    public static boolean reset(Player player){
        return apply(player, defaultAttributes);
    }

    public static boolean apply(Player player, HashMap<ResourceLocation, Double> attributeList){
        for(ResourceLocation id : defaultAttributes.keySet()){
            Optional<Holder.Reference<Attribute>> attributeEntry =  BuiltInRegistries.ATTRIBUTE.getHolder(id);
            if(attributeEntry != null && attributeEntry.isPresent()){
                AttributeInstance instance = player.getAttributes().getInstance(attributeEntry.get());
                if(instance != null){
                    instance.setBaseValue(defaultAttributes.get(id));
                }
            }
        }
        return  true;
    }
}
