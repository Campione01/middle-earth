package net.jukoz.me.resources.datas.races;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.jukoz.me.entity.ModEntities;
import net.jukoz.me.entity.NpcEntity;
import net.jukoz.me.entity.dwarves.longbeards.LongbeardDwarfEntity;
import net.jukoz.me.entity.elves.galadhrim.GaladhrimElfEntity;
import net.jukoz.me.entity.hobbits.shire.ShireHobbitEntity;
import net.jukoz.me.entity.humans.bandit.BanditHumanEntity;
import net.jukoz.me.entity.humans.gondor.GondorHumanEntity;
import net.jukoz.me.entity.orcs.mordor.MordorOrcEntity;
import net.jukoz.me.entity.uruks.mordor.MordorBlackUrukEntity;
import net.jukoz.me.resources.datas.RaceType;
import net.jukoz.me.resources.datas.races.data.AttributeData;
import net.jukoz.me.utils.IdentifierUtil;
import net.jukoz.me.utils.LoggerUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import java.util.*;

public class Race {
    public static final Codec<Race> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("id").forGetter(Race::getIdValue),
            Codec.STRING.fieldOf("type").forGetter(Race::getRaceTypeValue),
            CompoundTag.CODEC.fieldOf("attributes").forGetter(Race::getAttributeDatas),
            Codec.list(Codec.STRING, 0, 5).optionalFieldOf("command_join").forGetter(Race::getJoinCommands),
            Codec.list(Codec.STRING, 0, 5).optionalFieldOf("command_leave").forGetter(Race::getLeaveCommands)
    ).apply(instance, Race::new));

    private final ResourceLocation id;
    private final RaceType raceType;
    private final String translatableKey;
    private final AttributeData attributeData;
    private List<String> joinCommands;
    private List<String> leaveCommands;

    public Race(String id, String raceTypeValue, CompoundTag attributes, Optional<List<String>> joinCommands, Optional<List<String>> leaveCommands){
        // Create id
        this.id = IdentifierUtil.getIdentifierFromString(id);
        this.translatableKey = "race.".concat(this.id.toLanguageKey());
        // Create model
        this.raceType = RaceType.valueOf(raceTypeValue.toUpperCase());
        // Attribute Datas
        this.attributeData = new AttributeData(attributes);
        // Join commands
        this.joinCommands = new ArrayList<>();
        joinCommands.ifPresent(nbtCompound -> this.joinCommands.addAll(nbtCompound));
        // Leave commands
        this.leaveCommands = new ArrayList<>();
        leaveCommands.ifPresent(nbtCompound -> this.leaveCommands.addAll(nbtCompound));
    }

    public Race(ResourceLocation id, RaceType raceType, AttributeData attributeData, List<String> joinCommands, List<String> leaveCommands) {
        this.id = id;
        this.raceType = raceType;
        this.translatableKey = "race.".concat(this.id.toLanguageKey());
        this.attributeData = attributeData;
        this.joinCommands = joinCommands;
        this.leaveCommands = leaveCommands;
    }

    public ResourceLocation getId() {
        return id;
    }
    private String getIdValue() {
        return this.id.toString();
    }
    private String getRaceTypeValue() {
        return raceType.toString().toUpperCase();
    }
    private CompoundTag getAttributeDatas() {
        if(attributeData == null)
            return null;
        return attributeData.getNbt();
    }
    public Optional<List<String>> getJoinCommands() {
        if(this.joinCommands == null)
            return Optional.empty();
        return Optional.of(this.joinCommands);
    }
    public Optional<List<String>> getLeaveCommands() {
        if(this.leaveCommands == null)
            return Optional.empty();
        return Optional.of(this.leaveCommands);
    }

    public Component getFullName() {
        return Component.translatable(translatableKey);
    }

    public LivingEntity getModel(Level world) {
        NpcEntity entity;
        switch (raceType){
            case RaceType.HUMAN:
                entity = new GondorHumanEntity(ModEntities.GONDORIAN_SOLDIER, world);
                break;
            case RaceType.DWARF:
                entity = new LongbeardDwarfEntity(ModEntities.LONGBEARD_SOLDIER, world);
                break;
            case RaceType.HOBBIT:
                entity = new ShireHobbitEntity(ModEntities.HOBBIT_SHIRRIFF, world);
                break;
            case RaceType.ELF:
                entity = new GaladhrimElfEntity(ModEntities.LORIEN_LEADER, world);
                break;
            case RaceType.ORC:
                entity = new MordorOrcEntity(ModEntities.MORDOR_ORC_SOLDIER, world);
                break;
            case RaceType.URUK:
                entity = new MordorBlackUrukEntity(ModEntities.MORDOR_BLACK_URUK_SOLDIER, world);
                break;
            default:
                entity = new BanditHumanEntity(ModEntities.BANDIT_SOLDIER, world);
                break;
        }
        entity.setNoAi(true);
        return entity;
    }

    public void applyAttributes(Player playerEntity){
        attributeData.ApplyAll(playerEntity);
    }

    public void reverseAttributes(Player playerEntity){
        AttributeData.reset(playerEntity);
    }

    public String getTranslatableKey() {
        return translatableKey;
    }

    public RaceType getRaceType() {
        return raceType;
    }

    public void drawTooltip(LivingEntity entity, GuiGraphics context, Font renderer, int x, int y){
        List<Component> texts = new ArrayList<>();
        texts.add(getFullName());
        texts.add(Component.translatable("race_tooltip.me.attribute_header").withStyle(ChatFormatting.UNDERLINE));
        Map<ResourceLocation, Double> datas = attributeData.getDatas();
        for(ResourceLocation id : datas.keySet()){
            double value = datas.get(id);
            double difference = datas.get(id) - attributeData.getCurrentValue(entity, id);
            // Round them
            value = Math.round(value * 1000) / 1000.0;
            difference = Math.round(difference * 1000) / 1000.0;

            String differenceChar = (difference > 0) ? "+" : "";
            ChatFormatting white = ChatFormatting.WHITE;
            ChatFormatting differenceColor = (difference < 0) ? ChatFormatting.RED : (difference > 0) ? ChatFormatting.GREEN : white;
            if(attributeData.isBuffReversed(id)){
                differenceColor = (difference < 0) ? ChatFormatting.GREEN : (difference > 0) ? ChatFormatting.RED : white;
            }
            MutableComponent rawValue = Component.literal(String.valueOf(value)).withStyle(white);
            MutableComponent valueText = rawValue.append(Component.literal(" (").withStyle(white).append(Component.literal(differenceChar + difference).withStyle(differenceColor).append(Component.literal(") ").withStyle(white))));
            texts.add(valueText.append(Component.translatable("attribute.name."+id.getPath()).withStyle(ChatFormatting.WHITE)));
        }
        context.renderComponentTooltip(renderer, texts, x, y);
    }
}
