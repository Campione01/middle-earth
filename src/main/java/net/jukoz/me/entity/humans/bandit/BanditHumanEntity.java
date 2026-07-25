package net.jukoz.me.entity.humans.bandit;

import net.jukoz.me.entity.NpcEntity;
import net.jukoz.me.entity.beasts.trolls.TrollEntity;
import net.jukoz.me.entity.beasts.warg.WargEntity;
import net.jukoz.me.entity.dwarves.longbeards.LongbeardDwarfEntity;
import net.jukoz.me.entity.elves.galadhrim.GaladhrimElfEntity;
import net.jukoz.me.entity.hobbits.shire.ShireHobbitEntity;
import net.jukoz.me.entity.humans.dale.DaleHumanEntity;
import net.jukoz.me.entity.humans.gondor.GondorHumanEntity;
import net.jukoz.me.entity.humans.rohan.RohanHumanEntity;
import net.jukoz.me.entity.orcs.isengard.IsengardOrcEntity;
import net.jukoz.me.entity.orcs.misties.MistyGoblinEntity;
import net.jukoz.me.entity.orcs.mordor.MordorOrcEntity;
import net.jukoz.me.entity.orcs.wild.WildGoblinEntity;
import net.jukoz.me.entity.spider.MirkwoodSpiderEntity;
import net.jukoz.me.entity.uruks.isengard.IsengardUrukHaiEntity;
import net.jukoz.me.entity.uruks.misties.MistyHobgoblinEntity;
import net.jukoz.me.entity.uruks.mordor.MordorBlackUrukEntity;
import net.jukoz.me.resources.MiddleEarthFactions;
import net.jukoz.me.resources.MiddleEarthRaces;
import net.jukoz.me.resources.datas.npcs.data.NpcRank;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.Nullable;

public class BanditHumanEntity extends NpcEntity{

    public BanditHumanEntity(EntityType<? extends PathfinderMob> entityType, Level world) {
        super(entityType, world);
        String name = this.getTypeName().toString();
        if(name.contains("militia")){
            this.setRank(NpcRank.MILITIA);
            this.setBow(Items.BOW);
        } else if (name.contains("soldier")) {
            this.setRank(NpcRank.SOLDIER);
            this.setBow(Items.BOW);
        }else if (name.contains("chieftain")) {
            this.setRank(NpcRank.KNIGHT);
        }
    }

    @Override
    protected ResourceLocation getFactionId() {
        return MiddleEarthFactions.BANDIT.getId();
    }
    @Override
    protected ResourceLocation getRaceId() { return MiddleEarthRaces.HUMAN.getId(); }
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
                .add(Attributes.MAX_HEALTH, 20.0)
                .add(Attributes.ATTACK_SPEED, 1.5)
                .add(Attributes.FOLLOW_RANGE, 32.0)
                .add(Attributes.ATTACK_DAMAGE, 2.0);
    }
    public static AttributeSupplier.Builder setKnightAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.25f)
                .add(Attributes.MAX_HEALTH, 22.0)
                .add(Attributes.ATTACK_SPEED, 1.5)
                .add(Attributes.FOLLOW_RANGE, 32.0)
                .add(Attributes.ATTACK_DAMAGE, 2.5);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        int index = 4;
        index = initNeutralTargetSelector(index);
        this.targetSelector.addGoal(++index, new NearestAttackableTargetGoal<>(this, WildGoblinEntity.class, true));
    }

    @Override
    protected void actuallyHurt(DamageSource source, float amount) {
        if(source.getEntity() instanceof BanditHumanEntity){
            return;
        }
        super.actuallyHurt(source, amount);
    }

    public BanditHumanVariant getVariant() {
        return BanditHumanVariant.byId(this.getId());
    }
}
