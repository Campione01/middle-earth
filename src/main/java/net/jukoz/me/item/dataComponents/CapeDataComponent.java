package net.jukoz.me.item.dataComponents;


import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.jukoz.me.item.ModDataComponentTypes;
import net.jukoz.me.item.utils.armor.capes.ModCapes;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.FastColor;
import net.minecraft.world.item.ItemStack;

public record CapeDataComponent(ModCapes cape, int capeColor){

    private static final Codec<CapeDataComponent> BASE_CODEC = RecordCodecBuilder.create((instance) -> {
        return instance.group(ModCapes.CODEC.fieldOf("cape").forGetter(CapeDataComponent::getCape),
                Codec.INT.optionalFieldOf("cape_color", CustomDyeableDataComponent.DEFAULT_COLOR).forGetter(CapeDataComponent::capeColor))
                .apply(instance, CapeDataComponent::new);
    });
    public static final Codec<CapeDataComponent> CODEC  = Codec.withAlternative(BASE_CODEC, Codec.BOOL, (enabled) -> {
        return new CapeDataComponent(ModCapes.CAPE, CustomDyeableDataComponent.DEFAULT_COLOR);
    });
    public static final StreamCodec<ByteBuf, CapeDataComponent> PACKET_CODEC  = StreamCodec.composite(ModCapes.PACKET_CODEC, CapeDataComponent::getCape, ByteBufCodecs.INT, CapeDataComponent::capeColor, CapeDataComponent::new);
    ;

    public CapeDataComponent(ModCapes cape, int capeColor){
        this.cape = cape;
        this.capeColor = capeColor;
    }

    public static int getColor(ItemStack stack, int defaultColor) {
        CapeDataComponent capeDataComponent = stack.get(ModDataComponentTypes.CAPE_DATA);
        return capeDataComponent != null ? FastColor.ARGB32.opaque(capeDataComponent.capeColor) : defaultColor;
    }

    public static CapeDataComponent newCape(ModCapes cape) {
        return new CapeDataComponent(cape, CustomDyeableDataComponent.DEFAULT_COLOR);
    }

    public static CapeDataComponent newCapeWithColor(ModCapes cape, int capeColor) {
        return new CapeDataComponent(cape, capeColor);
    }

    public static ItemStack setCape(ItemStack stack, ModCapes cape){
        ItemStack itemStack = stack.copyWithCount(1);

        itemStack.set(ModDataComponentTypes.CAPE_DATA, new CapeDataComponent(cape , CustomDyeableDataComponent.DEFAULT_COLOR));
        return itemStack;
    }

    public static ItemStack setCapeWithColor(ItemStack stack, ModCapes cape, int capeColor){
        ItemStack itemStack = stack.copyWithCount(1);

        itemStack.set(ModDataComponentTypes.CAPE_DATA, new CapeDataComponent(cape, capeColor));
        return itemStack;
    }


    public ModCapes getCape(){
        return cape();
    }

    @Override
    public int capeColor() {
        return capeColor;
    }
}
