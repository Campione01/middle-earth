package net.jukoz.me.item.dataComponents;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.jukoz.me.MiddleEarth;
import net.jukoz.me.utils.ModColors;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipProvider;
import java.util.function.Consumer;

public record TemperatureDataComponent(int temperature) implements TooltipProvider {

    private static final Codec<TemperatureDataComponent> BASE_CODEC = RecordCodecBuilder.create((instance) -> {
        return instance.group(Codec.INT.fieldOf("temperature").forGetter(TemperatureDataComponent::temperature)).apply(instance, TemperatureDataComponent::new);
    });
    public static final Codec<TemperatureDataComponent> CODEC = Codec.withAlternative(BASE_CODEC, Codec.INT, temperature -> new TemperatureDataComponent((int)temperature));
    public static final StreamCodec<ByteBuf, TemperatureDataComponent> PACKET_CODEC = StreamCodec.composite(ByteBufCodecs.INT, TemperatureDataComponent::temperature, TemperatureDataComponent::new);

    @Override
    public void addToTooltip(Item.TooltipContext context, Consumer<Component> tooltip, TooltipFlag type) {
        if (this.temperature >= 80) tooltip.accept(Component.translatable("tooltip." + MiddleEarth.MOD_ID + ".temp_5").withColor(ModColors.TEMP_5.color));
        if (this.temperature < 80 && this.temperature >= 60) tooltip.accept(Component.translatable("tooltip." + MiddleEarth.MOD_ID + ".temp_4").withColor(ModColors.TEMP_4.color));
        if (this.temperature < 60 && this.temperature >= 40) tooltip.accept(Component.translatable("tooltip." + MiddleEarth.MOD_ID + ".temp_3").withColor(ModColors.TEMP_3.color));
        if (this.temperature < 40 && this.temperature >= 20) tooltip.accept(Component.translatable("tooltip." + MiddleEarth.MOD_ID + ".temp_2").withColor(ModColors.TEMP_2.color));
        if (this.temperature < 20 && this.temperature >= 0) tooltip.accept(Component.translatable("tooltip." + MiddleEarth.MOD_ID + ".temp_1").withColor(ModColors.TEMP_1.color));
    }

    @Override
    public int temperature() {
        return temperature;
    }

}
