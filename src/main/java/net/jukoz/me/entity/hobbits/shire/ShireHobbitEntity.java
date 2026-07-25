package net.jukoz.me.entity.hobbits.shire;

import net.jukoz.me.entity.NpcEntity;
import net.jukoz.me.entity.goals.NpcTargetPlayerGoal;
import net.jukoz.me.entity.humans.bandit.BanditHumanEntity;
import net.jukoz.me.entity.humans.rohan.RohanHumanEntity;
import net.jukoz.me.entity.orcs.misties.MistyGoblinEntity;
import net.jukoz.me.entity.orcs.mordor.MordorOrcEntity;
import net.jukoz.me.entity.projectile.pebble.PebbleEntity;
import net.jukoz.me.entity.spider.MirkwoodSpiderEntity;
import net.jukoz.me.entity.beasts.trolls.TrollEntity;
import net.jukoz.me.entity.uruks.misties.MistyHobgoblinEntity;
import net.jukoz.me.entity.uruks.mordor.MordorBlackUrukEntity;
import net.jukoz.me.exceptions.FactionIdentifierException;
import net.jukoz.me.item.ModResourceItems;
import net.jukoz.me.item.items.weapons.ranged.PebbleItem;
import net.jukoz.me.resources.MiddleEarthFactions;
import net.jukoz.me.resources.MiddleEarthRaces;
import net.jukoz.me.resources.datas.Disposition;
import net.jukoz.me.resources.datas.factions.FactionLookup;
import net.jukoz.me.resources.datas.npcs.data.NpcRank;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.Nullable;

public class ShireHobbitEntity extends NpcEntity {
    private static float FLEE_DISTANCE = 8f;
    private static float FLEE_SPEED_MIN = 0.8f;
    private static float FLEE_SPEED_MAX = 1.2f;

    public ShireHobbitEntity(EntityType<? extends NpcEntity> entityType, Level world) {
        super(entityType, world);
        String name = this.getTypeName().toString();
        if(name.contains("civilian")){
            this.setRank(NpcRank.CIVILIAN);
        }else if(name.contains("bounder")){
            this.setRank(NpcRank.MILITIA);
            this.setBow(Items.BOW);
        }else if (name.contains("shirriff")) {
            this.setRank(NpcRank.SOLDIER);
        }
    }
    @Override
    protected ResourceLocation getFactionId() {
        return MiddleEarthFactions.SHIRE.getId();
    }
    @Override
    protected ResourceLocation getRaceId() { return MiddleEarthRaces.HOBBIT.getId(); }
    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, MobSpawnType spawnReason, @Nullable SpawnGroupData entityData) {
        entityData = super.finalizeSpawn(world, difficulty, spawnReason, entityData);
        RandomSource random = world.getRandom();
        this.populateDefaultEquipmentSlots(random, difficulty);
        return entityData;
    }

    @Override
    protected void registerGoals() {
        ResourceLocation factionId = getFactionId();
        if(factionId == null)
            disposition = Disposition.NEUTRAL;
        else {
            try {
                disposition = FactionLookup.getFactionById(level(), factionId).getDisposition();
            } catch (FactionIdentifierException e) {
                disposition = Disposition.NEUTRAL; // Attacks everyone, no judgement made
            }
        }

        int i = 0;
        this.goalSelector.addGoal(++i, new FloatGoal(this));
        this.goalSelector.addGoal(++i, new AvoidEntityGoal<>(this, TrollEntity.class, FLEE_DISTANCE, FLEE_SPEED_MIN, FLEE_SPEED_MAX));
        this.goalSelector.addGoal(++i, new AvoidEntityGoal<>(this, MordorBlackUrukEntity.class, FLEE_DISTANCE, FLEE_SPEED_MIN, FLEE_SPEED_MAX));
        this.goalSelector.addGoal(++i, new AvoidEntityGoal<>(this, MistyHobgoblinEntity.class, FLEE_DISTANCE, FLEE_SPEED_MIN, FLEE_SPEED_MAX));
        this.goalSelector.addGoal(++i, new AvoidEntityGoal<>(this, MordorOrcEntity.class, FLEE_DISTANCE, FLEE_SPEED_MIN, FLEE_SPEED_MAX));
        this.goalSelector.addGoal(++i, new AvoidEntityGoal<>(this, MistyGoblinEntity.class, FLEE_DISTANCE, FLEE_SPEED_MIN, FLEE_SPEED_MAX));
        this.goalSelector.addGoal(++i, new AvoidEntityGoal<>(this, BanditHumanEntity.class, FLEE_DISTANCE, FLEE_SPEED_MIN, FLEE_SPEED_MAX));
        this.goalSelector.addGoal(++i, new AvoidEntityGoal<>(this, MirkwoodSpiderEntity.class, FLEE_DISTANCE, FLEE_SPEED_MIN, FLEE_SPEED_MAX));
        this.goalSelector.addGoal(++i, new RangedAttackGoal(this, 1.0, 12, 24, 20.0f));
        this.goalSelector.addGoal(++i, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(++i, new LookAtPlayerGoal(this, Player.class, 6.0f));
        this.goalSelector.addGoal(++i, new RandomLookAroundGoal(this));
        i = 1;
        this.targetSelector.addGoal(++i, new HurtByTargetGoal(this).setAlertOthers());
        initGoodTargetSelector(i);
    }

    public static AttributeSupplier.Builder setSoldierAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.3f)
                .add(Attributes.MAX_HEALTH, 20.0)
                .add(Attributes.ATTACK_SPEED, 1.0)
                .add(Attributes.FOLLOW_RANGE, 48.0)
                .add(Attributes.ATTACK_DAMAGE, 1.0);
    }
    public static AttributeSupplier.Builder setKnightAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.3f)
                .add(Attributes.MAX_HEALTH, 22.0)
                .add(Attributes.ATTACK_SPEED, 1.0)
                .add(Attributes.FOLLOW_RANGE, 48.0)
                .add(Attributes.ATTACK_DAMAGE, 1.5);
    }

    public ShireHobbitVariant getVariant() {
        return ShireHobbitVariant.byId(this.getId());
    }

    @Override
    protected void actuallyHurt(DamageSource source, float amount) {
        if(source.getEntity() instanceof ShireHobbitEntity){
            return;
        }
        super.actuallyHurt(source, amount);
    }

    @Override
    public void performRangedAttack(LivingEntity target, float pullProgress) {
        Item item = ModResourceItems.PEBBLE;
        ItemStack itemStack = new ItemStack(item);
        double d = target.getX() - this.getX();
        double e = target.getY(0.3333333333333333) - this.getY();
        double f = target.getZ() - this.getZ();
        double g = Math.sqrt(d * d + f * f);

        PebbleEntity pebbleEntity = new PebbleEntity(level(), this, PebbleItem.DAMAGE);
        pebbleEntity.setItem(itemStack);
        pebbleEntity.shoot(d, e + g * (double)0.2f, f, 0.8f, 14 - level().getDifficulty().getId() * 4);
        level().addFreshEntity(pebbleEntity);
        this.playSound(SoundEvents.SNOWBALL_THROW, 1.0f, 1.0f / (this.getRandom().nextFloat() * 0.05f + 0.8f));
    }
}
