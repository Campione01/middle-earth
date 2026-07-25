package net.jukoz.me.item.items.weapons.ranged;

import net.jukoz.me.MiddleEarth;
import net.jukoz.me.item.utils.MEEquipmentTooltip;
import net.jukoz.me.item.utils.ModRangedWeaponTypes;
import net.jukoz.me.utils.ModFactions;
import net.jukoz.me.utils.ModSubFactions;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class CustomLongbowWeaponItem extends BowItem implements MEEquipmentTooltip{
    private final ModFactions faction;
    private final ModSubFactions subFaction;
    public ModRangedWeaponTypes type;

    public static final int DEFAULT_RANGE = 25;

    public CustomLongbowWeaponItem(ModRangedWeaponTypes type) {
        super(new Item.Properties().durability(type.durability));
        this.faction = null;
        this.subFaction = null;
        this.type = type;
    }

    public CustomLongbowWeaponItem(ModFactions faction, ModRangedWeaponTypes type) {
        super(new Item.Properties().durability(type.durability));
        this.faction = faction;
        this.subFaction = null;
        this.type = type;
    }

    public CustomLongbowWeaponItem(ModSubFactions subFaction, ModRangedWeaponTypes type) {
        super(new Item.Properties().durability(type.durability));
        this.faction = subFaction.getParent();
        this.subFaction = subFaction;
        this.type = type;
    }

    @Override
    public void releaseUsing(ItemStack stack, Level world, LivingEntity user, int remainingUseTicks) {
        if (user instanceof Player playerEntity) {
            ItemStack itemStack = playerEntity.getProjectile(stack);
            if (!itemStack.isEmpty()) {
                int i = this.getUseDuration(stack, user) - remainingUseTicks;
                float f = getPullProgressLongbow(i);
                if (!((double)f < 0.1)) {
                    List<ItemStack> list = draw(stack, itemStack, playerEntity);
                    if (world instanceof ServerLevel) {
                        ServerLevel serverWorld = (ServerLevel)world;
                        if (!list.isEmpty()) {
                            this.shoot(serverWorld, playerEntity, playerEntity.getUsedItemHand(), stack, list, f * 5.0F, 1.0F, f == 1.0F, (LivingEntity)null);
                        }
                    }

                    world.playSound((Player)null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F / (world.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F);
                    playerEntity.awardStat(Stats.ITEM_USED.get(this));
                }
            }
        }
    }

    public static float getPullProgressLongbow(int useTicks) {
        float f = (float)useTicks / 20.0F;
        f = (f * f + f * 2.0F) / 4.5F;
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }

    @Override
    public List<Component> getAdditionalShiftLines(ItemStack stack) {
        List<Component> list = new ArrayList<>(List.of());

        list.add(Component.translatable("tooltip." + MiddleEarth.MOD_ID + ".weapon_type").append(Component.translatable("tooltip." + MiddleEarth.MOD_ID + "." + this.type.name)));

        return list;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag type) {
        appendBaseTooltip(tooltip, stack, this.faction, this.subFaction);
        super.appendHoverText(stack, context, tooltip, type);
    }

    @Override
    public Component getName(ItemStack stack) {
        if(BuiltInRegistries.ITEM.getKey(this).getPath().contains("_noble")
                || BuiltInRegistries.ITEM.getKey(this).getPath().contains("_elite")
                || BuiltInRegistries.ITEM.getKey(this).getPath().contains("uruk_hai")
                || BuiltInRegistries.ITEM.getKey(this).getPath().contains("heyday")
                || BuiltInRegistries.ITEM.getKey(this).getPath().contains("numenorean")){
            return Component.translatable(this.getDescriptionId(stack)).withStyle(ChatFormatting.GOLD);
        }
        return super.getName(stack);
    }

    @Override
    public int getDefaultProjectileRange() {
        return 25;
    }
}
