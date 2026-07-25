package net.jukoz.me.item.items;

import net.jukoz.me.item.ModResourceItems;
import net.jukoz.me.particles.ModParticleTypes;
import net.jukoz.me.sound.ModSounds;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class PipeItem extends Item {
    private final int USAGE_TIME = 60;
    private boolean smoking;
    private int usesPerLeaf;


    public PipeItem(Item.Properties settings, int amountOfUses) {
        super(settings.durability(amountOfUses));
        usesPerLeaf = amountOfUses;
    }


    @Override
    public int getBarColor(ItemStack stack) {
        return 0x01e81b0;
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        if (stack.isDamageableItem()) {
            return Math.round(13.0F - (float) stack.getDamageValue() * 13.0F / (float) stack.getMaxDamage());
        }
        return super.getBarWidth(stack);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player user, InteractionHand hand) {
        ItemStack itemStack = user.getItemInHand(hand);
        if (itemStack.isDamageableItem() && itemStack.getDamageValue() >= itemStack.getMaxDamage()) {
            // Attempt to refill the pipe using a leaf
            ItemStack driedPipeweedStack = user.getInventory().items.stream()
                    .filter(stack -> stack.getItem() == ModResourceItems.DRIED_PIPEWEED)
                    .findFirst()
                    .orElse(ItemStack.EMPTY);

            if (driedPipeweedStack.isEmpty() && !user.isCreative()) {
                return InteractionResultHolder.fail(itemStack);
            }

            driedPipeweedStack.consume(1, user);
            itemStack.setDamageValue(0);
            ((Player)user).getCooldowns().addCooldown(this, 20);
            world.playSound(null, user.getX(), user.getY(), user.getZ(), ModSounds.PIPE_REFILL, SoundSource.PLAYERS, 1.0F, 1.0F);

        }
        else{
            world.playSound(null, user.getX(), user.getY(), user.getZ(), ModSounds.PIPE_IGNITE, SoundSource.PLAYERS, 1.0F, 1.0F);
            user.startUsingItem(hand);
            this.smoking = true;
            user.awardStat(Stats.ITEM_USED.get(this));
            itemStack.setDamageValue(itemStack.getDamageValue() + 1);
        }
        return InteractionResultHolder.consume(itemStack);
    }

    @Override
    public void releaseUsing(ItemStack stack, Level world, LivingEntity user, int remainingUseTicks) {
        // add a particle of smoke traveling away from the player "a final breath" -- froosty
        if(smoking && remainingUseTicks < USAGE_TIME / 2){
            spawnSmoke(remainingUseTicks, user, world);
        }
        this.smoking = false;
        ((Player)user).getCooldowns().addCooldown(this, 20);

    }

    @Override
    public void onUseTick(Level world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        if (smoking) {
            int totalTicks = USAGE_TIME - remainingUseTicks;
            int frequency = Math.max(4, (int) Math.pow((USAGE_TIME - totalTicks) / 10.0, 2));
            if (remainingUseTicks % frequency == 0) {
                if (!world.isClientSide && world instanceof ServerLevel serverWorld) {
                    serverWorld.sendParticles(
                            ParticleTypes.SMOKE,
                            user.getX() + user.getViewVector(1.0F).x * 0.5,
                            user.getY() + user.getEyeHeight(user.getPose()) + user.getViewVector(1.0F).y * 0.5 + 0.04,
                            user.getZ() + user.getViewVector(1.0F).z * 0.5,
                            1, // Number of particles
                            0, // Offset X
                            0.02, // Offset Y
                            0, // Offset Z
                            0 // Speed
                    );
                }
            }
        }
    }

    public ItemStack finishUsingItem(ItemStack item, Level world, LivingEntity user){
        spawnSmoke(0, user, world);

        this.smoking = false;
        ((Player)user).getCooldowns().addCooldown(this, 20);
        return item;
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity user) {
        return USAGE_TIME;
    }

    public void spawnSmoke(int remainingUseTicks, LivingEntity user, Level world){
        float f = (float) (USAGE_TIME - remainingUseTicks) / 500;
        Vec3 vec = user.getViewVector(1.0F);
        world.addParticle(ModParticleTypes.RING_OF_SMOKE,
                user.getX() + vec.x * 0.5,
                user.getY() + user.getEyeHeight(user.getPose()) + vec.y * 0.5,
                user.getZ() + vec.z * 0.5,
                vec.x * f, vec.y *f, vec.z *f);
        //https://pixabay.com/service/license-summary/
        //https://pixabay.com//?utm_source=link-attribution&utm_medium=referral&utm_campaign=music&utm_content=106654"
        world.playSound(null, user.getX(), user.getY(), user.getZ(), ModSounds.PIPE_EXHALE, SoundSource.PLAYERS, 1.0F, 1.0F);
    }
    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return this.smoking ? UseAnim.TOOT_HORN : UseAnim.NONE;
    }

    public boolean isSmoking() {
        return this.smoking;
    }
}