package net.jukoz.me.entity.orcs.isengard;

import net.jukoz.me.entity.NpcEntity;
import net.jukoz.me.entity.dwarves.longbeards.LongbeardDwarfEntity;
import net.jukoz.me.entity.elves.galadhrim.GaladhrimElfEntity;
import net.jukoz.me.entity.hobbits.shire.ShireHobbitEntity;
import net.jukoz.me.entity.humans.bandit.BanditHumanEntity;
import net.jukoz.me.entity.humans.gondor.GondorHumanEntity;
import net.jukoz.me.entity.humans.rohan.RohanHumanEntity;
import net.jukoz.me.entity.orcs.OrcNpcEntity;
import net.jukoz.me.entity.orcs.misties.MistyGoblinEntity;
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
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class IsengardOrcEntity extends OrcNpcEntity {
    public IsengardOrcEntity(EntityType<? extends NpcEntity> entityType, Level world) {
        super(entityType, world);
        String name = this.getTypeName().toString();
        if(name.contains("snaga")){
            this.setRank(NpcRank.MILITIA);
            this.setBow(Items.BOW);
        } else if (name.contains("warrior")) {
            this.setRank(NpcRank.SOLDIER);
            this.setBow(Items.BOW);
        }
    }
    @Override
    protected ResourceLocation getFactionId() {
        return MiddleEarthFactions.ISENGARD.getId();
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
                .add(Attributes.MAX_HEALTH, 20.0)
                .add(Attributes.ATTACK_SPEED, 1.5)
                .add(Attributes.FOLLOW_RANGE, 32.0)
                .add(Attributes.ATTACK_DAMAGE, 2.0);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        int index = 4;
        initNeutralTargetSelector(index);
    }
    @Override
    protected void actuallyHurt(DamageSource source, float amount) {
        if(source.getEntity() instanceof IsengardOrcEntity){
            return;
        }
        super.actuallyHurt(source, amount);
    }
    public IsengardOrcVariant getVariant() {
        return IsengardOrcVariant.byId(this.getId());
    }
}
