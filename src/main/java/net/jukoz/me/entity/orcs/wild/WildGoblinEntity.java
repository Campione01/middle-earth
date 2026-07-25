package net.jukoz.me.entity.orcs.wild;

import net.jukoz.me.entity.NpcEntity;
import net.jukoz.me.entity.humans.bandit.BanditHumanEntity;
import net.jukoz.me.entity.humans.dale.DaleHumanEntity;
import net.jukoz.me.entity.orcs.OrcNpcEntity;
import net.jukoz.me.resources.MiddleEarthFactions;
import net.jukoz.me.resources.MiddleEarthRaces;
import net.jukoz.me.resources.datas.npcs.data.NpcRank;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.*;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.Nullable;

public class WildGoblinEntity extends OrcNpcEntity {
    public WildGoblinEntity(EntityType<? extends NpcEntity> entityType, Level world) {
        super(entityType, world);
        String name = this.getTypeName().toString();
        if(name.contains("wild")){
            this.setRank(NpcRank.SOLDIER);
        }
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        int index = 4;
        index = initNeutralTargetSelector(index);
        this.targetSelector.addGoal(++index, new NearestAttackableTargetGoal<>(this, BanditHumanEntity.class, true));
    }


    @Override
    protected ResourceLocation getFactionId() {
        return MiddleEarthFactions.BANDIT.getId();
    }

    @Override
    protected ResourceLocation getRaceId() { return MiddleEarthRaces.ORC.getId(); }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, MobSpawnType spawnReason, @Nullable SpawnGroupData entityData) {
        entityData = super.finalizeSpawn(world, difficulty, spawnReason, entityData);
        RandomSource random = world.getRandom();
        this.populateDefaultEquipmentSlots(random, difficulty);
        return entityData;
    }

    public static AttributeSupplier.Builder setSoldierAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.25f)
                .add(Attributes.MAX_HEALTH, 12.0)
                .add(Attributes.ATTACK_SPEED, 0.5)
                .add(Attributes.FOLLOW_RANGE, 32.0)
                .add(Attributes.ATTACK_DAMAGE, 0.25);
    }
    @Override
    protected void actuallyHurt(DamageSource source, float amount) {
        if(source.getEntity() instanceof WildGoblinEntity){
            return;
        }
        super.actuallyHurt(source, amount);
    }
    public WildGoblinVariant getVariant() {
        return WildGoblinVariant.byId(this.getId());
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor world, MobSpawnType spawnReason) {
        if(spawnReason == MobSpawnType.NATURAL || spawnReason == MobSpawnType.CHUNK_GENERATION ){
            if(world.getMaxLocalRawBrightness(blockPosition()) > 5 || getBlockY() > 24){
                return false;
            }
        }
        return super.checkSpawnRules(world, spawnReason);
    }

    @Override
    protected void dropCustomDeathLoot(ServerLevel world, DamageSource source, boolean causedByPlayer) {
        return;
    }

    @Override
    public void dropPreservedEquipment() {
        return;
    }

    @Override
    public boolean checkSpawnObstruction(LevelReader world) {
        return super.checkSpawnObstruction(world);
    }

    @Override
    public boolean isPersistenceRequired() {
        return false;
    }
}
