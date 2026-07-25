package net.jukoz.me.item.dataComponents;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.jukoz.me.MiddleEarth;
import net.jukoz.me.item.ModDataComponentTypes;
import net.jukoz.me.recipe.ModTags;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.FastColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import java.util.Iterator;
import java.util.List;

public record CustomDyeableDataComponent(int customRgb) {
    private static final Codec<CustomDyeableDataComponent> BASE_CODEC = RecordCodecBuilder.create((instance) -> {
        return instance.group(Codec.INT.fieldOf("customRgb").forGetter(CustomDyeableDataComponent::customRgb)).apply(instance, CustomDyeableDataComponent::new);
    });
    public static final Codec<CustomDyeableDataComponent> CODEC;
    public static final StreamCodec<ByteBuf, CustomDyeableDataComponent> PACKET_CODEC;
    public static final int DEFAULT_COLOR = -6265536;

    public static int getColor(ItemStack stack, int defaultColor) {
        CustomDyeableDataComponent dyedColorComponent = stack.get(ModDataComponentTypes.DYE_DATA);
        return dyedColorComponent != null ? FastColor.ARGB32.opaque(dyedColorComponent.customRgb()) : defaultColor;
    }

    public static ItemStack setColor(ItemStack stack, List<DyeItem> dyes) {
        if (!stack.is(ModTags.DYEABLE)) {
            return ItemStack.EMPTY;
        } else {
            ItemStack itemStack = stack.copyWithCount(1);
            int i = 0;
            int j = 0;
            int k = 0;
            int l = 0;
            int m = 0;
            CustomDyeableDataComponent dyedColorComponent = (CustomDyeableDataComponent) itemStack.get(ModDataComponentTypes.DYE_DATA);
            int n;
            int o;
            int p;
            if(dyedColorComponent != null){
                if (!(dyedColorComponent.customRgb == stack.getItem().getDefaultInstance().get(ModDataComponentTypes.DYE_DATA).customRgb())) {
                    if(dyedColorComponent.customRgb != CustomDyeableDataComponent.DEFAULT_COLOR){
                        n = FastColor.ARGB32.red(dyedColorComponent.customRgb());
                        o = FastColor.ARGB32.green(dyedColorComponent.customRgb());
                        p = FastColor.ARGB32.blue(dyedColorComponent.customRgb());
                        l += Math.max(n, Math.max(o, p));
                        i += n;
                        j += o;
                        k += p;
                        ++m;
                    }
                }
            }

            int s;
            for(Iterator var16 = dyes.iterator(); var16.hasNext(); ++m) {
                DyeItem dyeItem = (DyeItem)var16.next();
                p = dyeItem.getDyeColor().getTextureDiffuseColor();
                int q = FastColor.ARGB32.red(p);
                int r = FastColor.ARGB32.green(p);
                s = FastColor.ARGB32.blue(p);
                l += Math.max(q, Math.max(r, s));
                i += q;
                j += r;
                k += s;
            }

            n = i / m;
            o = j / m;
            p = k / m;
            float f = (float) l / (float) m;
            float g = (float) Math.max(n, Math.max(o, p));
            n = (int) ((float) n * f / g);
            o = (int) ((float) o * f / g);
            p = (int) ((float) p * f / g);
            s = FastColor.ARGB32.color(0, n, o, p);
            itemStack.set(ModDataComponentTypes.DYE_DATA, new CustomDyeableDataComponent(s));
            return itemStack;
        }
    }


    public int customRgb() {
        return this.customRgb;
    }

    static {
        CODEC = Codec.withAlternative(BASE_CODEC, Codec.INT, CustomDyeableDataComponent::new);
        PACKET_CODEC = StreamCodec.composite(ByteBufCodecs.INT, CustomDyeableDataComponent::customRgb, CustomDyeableDataComponent::new);
    }
}