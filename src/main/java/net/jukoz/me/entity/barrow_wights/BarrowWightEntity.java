/*
 * Decompiled with CFR 0.2.0 (FabricMC d28b102d).
 */
package net.jukoz.me.entity.barrow_wights;

import net.jukoz.me.entity.beasts.trolls.TrollEntity;
import net.jukoz.me.entity.dwarves.longbeards.LongbeardDwarfEntity;
import net.jukoz.me.entity.elves.galadhrim.GaladhrimElfEntity;
import net.jukoz.me.entity.hobbits.shire.ShireHobbitEntity;
import net.jukoz.me.entity.humans.gondor.GondorHumanEntity;
import net.jukoz.me.entity.humans.rohan.RohanHumanEntity;
import net.jukoz.me.entity.orcs.misties.MistyGoblinEntity;
import net.jukoz.me.entity.orcs.mordor.MordorOrcEntity;
import net.jukoz.me.entity.spider.MirkwoodSpiderEntity;
import net.jukoz.me.entity.uruks.misties.MistyHobgoblinEntity;
import net.jukoz.me.entity.uruks.mordor.MordorBlackUrukEntity;
import net.jukoz.me.statusEffects.ModStatusEffects;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class BarrowWightEntity extends Monster {
    private static final int MAX_HEALTH = 40;
    private static final float MOVEMENT_SPEED = 0.6f;
    private static final float KNOCKBACK_RESISTANCE = 1.0f;
    private static final float ATTACK_KNOCKBACK = 1.2f;
    private static final int ATTACK_DAMAGE = 3;
    private static final EntityDataAccessor<Boolean> CAN_SCREAM;
    private static final EntityDataAccessor<Integer> SCREAMING_TIME;
    public static final String LAST_SCREAM_TIME_KEY = "ScreamDelayTime";
    private int lastScreamTime;
    private static final int SCREAM_DELAY = 150;
    private static final int SCREAM_EFFECT_DURATION = 100;

    public static final int SCREAM_ACTION_TIME = 35;

    public BarrowWightEntity(EntityType<? extends Monster> entityType, Level world) {
        super(entityType, world);
    }
    public static AttributeSupplier.Builder setAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, MOVEMENT_SPEED)
                .add(Attributes.MAX_HEALTH, MAX_HEALTH)
                .add(Attributes.KNOCKBACK_RESISTANCE, KNOCKBACK_RESISTANCE)
                .add(Attributes.ATTACK_SPEED, 0.85)
                .add(Attributes.FOLLOW_RANGE, 28.0)
                .add(Attributes.ATTACK_DAMAGE, ATTACK_DAMAGE)
                .add(Attributes.ATTACK_KNOCKBACK, ATTACK_KNOCKBACK);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));

        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, MOVEMENT_SPEED , false));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, MOVEMENT_SPEED * 0.8f));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, LivingEntity.class, 32.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));

        int i = 0;
        this.targetSelector.addGoal(++i, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(++i, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(++i, new NearestAttackableTargetGoal<>(this, TrollEntity.class, true));
        this.targetSelector.addGoal(++i, new NearestAttackableTargetGoal<>(this, MordorBlackUrukEntity.class, true));
        this.targetSelector.addGoal(++i, new NearestAttackableTargetGoal<>(this, MistyHobgoblinEntity.class, true));
        this.targetSelector.addGoal(++i, new NearestAttackableTargetGoal<>(this, MordorOrcEntity.class, true));
        this.targetSelector.addGoal(++i, new NearestAttackableTargetGoal<>(this, MistyGoblinEntity.class, true));
        this.targetSelector.addGoal(++i, new NearestAttackableTargetGoal<>(this, MirkwoodSpiderEntity.class, true));
        this.targetSelector.addGoal(++i, new NearestAttackableTargetGoal<>(this, GondorHumanEntity.class, true));
        this.targetSelector.addGoal(++i, new NearestAttackableTargetGoal<>(this, RohanHumanEntity.class, true));
        this.targetSelector.addGoal(++i, new NearestAttackableTargetGoal<>(this, GaladhrimElfEntity.class, true));
        this.targetSelector.addGoal(++i, new NearestAttackableTargetGoal<>(this, LongbeardDwarfEntity.class, true));
        this.targetSelector.addGoal(++i, new NearestAttackableTargetGoal<>(this, ShireHobbitEntity.class, true));
    }

    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(CAN_SCREAM, true);
        builder.define(SCREAMING_TIME, 0);
    }

    public boolean canScream() {
        return this.getEntityData().get(CAN_SCREAM);
    }
    public Integer getScreamingActionTime() {
        return this.getEntityData().get(SCREAMING_TIME);
    }

    public void setCanScream(boolean canScream) {
        if(!canScream) {
            this.lastScreamTime = SCREAM_DELAY;
        }
        this.entityData.set(CAN_SCREAM, canScream);
    }

    private void setScreamedTime(int time) {
        this.lastScreamTime = time;
    }

    public void setScreamingActionTime(int screamingTime) {
        this.entityData.set(SCREAMING_TIME, screamingTime);
    }

    @Override
    public void tick() {
        if (!this.level().isClientSide && this.isAlive() && !this.isNoAi()) {
            LivingEntity target = getTarget();

            int screamingTime = this.getScreamingActionTime();
            if(screamingTime > 0 ){
                screamingTime --;
                this.setScreamingActionTime(screamingTime);

                if(this.getScreamingActionTime() <= 0){
                    if(target != null && target.isAlwaysTicking()){
                        int value = RandomSource.create().nextInt(0, 100);
                        if(value < 5){
                            target.sendSystemMessage(Component.literal("The barrows says BOO!!!!"));
                        }
                        target.addEffect(new MobEffectInstance(ModStatusEffects.HALLUCINATION, SCREAM_EFFECT_DURATION), this);
                        target.addEffect(new MobEffectInstance(MobEffects.DARKNESS, SCREAM_EFFECT_DURATION), this);
                    }

                    this.setScreamingActionTime(-1);
                }
            }
            if (!this.canScream()) {
                --this.lastScreamTime;
                if (this.lastScreamTime < 0) {
                    this.setCanScream(true);
                }
            }
            if(this.getTarget() == null){
                this.setCanScream(false);
            } else if(this.canScream()) {
                tryToScream();
            }
        }
        super.tick();
    }

    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putInt(LAST_SCREAM_TIME_KEY, !this.canScream() ? this.lastScreamTime : -1);
    }

    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        if (nbt.contains(LAST_SCREAM_TIME_KEY, 99) && nbt.getInt(LAST_SCREAM_TIME_KEY) > -1) {
            this.setScreamedTime(nbt.getInt(LAST_SCREAM_TIME_KEY));
        }
    }

    protected void tryToScream() {
        LivingEntity target = getTarget();

        if(target == null) return;
        if(target.distanceTo(this) > 25) return;
        if(target.distanceTo(this) < 5) return;

        if(target.hasEffect(ModStatusEffects.HALLUCINATION)) return;

        this.setScreamingActionTime(SCREAM_ACTION_TIME);
        this.setCanScream(false);
    }

    public boolean canFreeze() {
        return false;
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.SCULK_SHRIEKER_SHRIEK;
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.SCULK_SHRIEKER_SHRIEK;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.SCULK_SHRIEKER_BREAK;
    }

    SoundEvent getStepSound() {
        return SoundEvents.SKELETON_STEP;
    }

    @Override
    public float getVoicePitch() {
        return 0.1f;
    }

    @Override
    protected void dropCustomDeathLoot(ServerLevel world, DamageSource source, boolean causedByPlayer) {
        super.dropCustomDeathLoot(world, source, causedByPlayer);
        Entity entity = source.getEntity();
        if (entity instanceof Creeper creeperEntity) {
            if (creeperEntity.canDropMobsSkull()) {
                creeperEntity.increaseDroppedSkulls();
                this.spawnAtLocation(Items.SKELETON_SKULL);
            }
        }
    }
    public BarrowWightVariant getVariant() {
        return BarrowWightVariant.BASIC;
    }

    static {
        CAN_SCREAM = SynchedEntityData.defineId(BarrowWightEntity.class, EntityDataSerializers.BOOLEAN);
        SCREAMING_TIME = SynchedEntityData.defineId(BarrowWightEntity.class, EntityDataSerializers.INT);
    }
}