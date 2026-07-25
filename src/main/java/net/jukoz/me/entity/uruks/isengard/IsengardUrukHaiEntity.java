package net.jukoz.me.entity.uruks.isengard;

import net.jukoz.me.entity.NpcEntity;
import net.jukoz.me.entity.dwarves.longbeards.LongbeardDwarfEntity;
import net.jukoz.me.entity.elves.galadhrim.GaladhrimElfEntity;
import net.jukoz.me.entity.hobbits.shire.ShireHobbitEntity;
import net.jukoz.me.entity.humans.bandit.BanditHumanEntity;
import net.jukoz.me.entity.humans.gondor.GondorHumanEntity;
import net.jukoz.me.entity.humans.rohan.RohanHumanEntity;
import net.jukoz.me.entity.orcs.isengard.IsengardOrcEntity;
import net.jukoz.me.entity.uruks.UrukNpcEntity;
import net.jukoz.me.item.ModEquipmentItems;
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
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.Nullable;

public class IsengardUrukHaiEntity extends UrukNpcEntity {

    public IsengardUrukHaiEntity(EntityType<? extends NpcEntity> entityType, Level world) {
        super(entityType, world);
        String name = this.getTypeName().toString();
        if (name.contains("soldier")) {
            this.setRank(NpcRank.KNIGHT);
        }else if (name.contains("veteran")) {
            this.setRank(NpcRank.VETERAN);
        }else if (name.contains("leader")) {
            this.setRank(NpcRank.LEADER);
        }
    }
    @Override
    protected ResourceLocation getFactionId() {
        return MiddleEarthFactions.ISENGARD.getId();
    }
    @Override
    protected ResourceLocation getRaceId() { return MiddleEarthRaces.URUK.getId(); }
    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, MobSpawnType spawnReason, @Nullable SpawnGroupData entityData) {
        entityData = super.finalizeSpawn(world, difficulty, spawnReason, entityData);
        RandomSource random = world.getRandom();
        this.populateDefaultEquipmentSlots(random, difficulty);
        return entityData;
    }
    public static AttributeSupplier.Builder setKnightAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.25f)
                .add(Attributes.MAX_HEALTH, 22.0)
                .add(Attributes.ATTACK_SPEED, 1.5)
                .add(Attributes.FOLLOW_RANGE, 32.0)
                .add(Attributes.ATTACK_DAMAGE, 2.5);
    }
    public static AttributeSupplier.Builder setVeteranAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.25f)
                .add(Attributes.MAX_HEALTH, 24.0)
                .add(Attributes.ATTACK_SPEED, 1.5)
                .add(Attributes.FOLLOW_RANGE, 32.0)
                .add(Attributes.ATTACK_DAMAGE, 3.0);
    }
    public static AttributeSupplier.Builder setLeaderAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.25f)
                .add(Attributes.MAX_HEALTH, 26.0)
                .add(Attributes.ATTACK_SPEED, 1.5)
                .add(Attributes.FOLLOW_RANGE, 32.0)
                .add(Attributes.ATTACK_DAMAGE, 3.5);
    }
    @Override
    protected void actuallyHurt(DamageSource source, float amount) {
        if(source.getEntity() instanceof IsengardUrukHaiEntity){
            return;
        }
        super.actuallyHurt(source, amount);
    }
    public IsengardUrukHaiVariant getVariant() {
        return IsengardUrukHaiVariant.byId(this.getId());
    }
}
