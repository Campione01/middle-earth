package net.jukoz.me.entity;

import net.jukoz.me.entity.beasts.broadhoof.BroadhoofGoatEntity;
import net.jukoz.me.entity.beasts.trolls.TrollEntity;
import net.jukoz.me.entity.beasts.warg.WargEntity;
import net.jukoz.me.entity.dwarves.longbeards.LongbeardDwarfEntity;
import net.jukoz.me.entity.elves.galadhrim.GaladhrimElfEntity;
import net.jukoz.me.entity.goals.CustomBowAttackGoal;
import net.jukoz.me.entity.goals.NpcTargetPlayerGoal;
import net.jukoz.me.entity.hobbits.shire.ShireHobbitEntity;
import net.jukoz.me.entity.humans.bandit.BanditHumanEntity;
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
import net.jukoz.me.exceptions.FactionIdentifierException;
import net.jukoz.me.item.items.weapons.ranged.CustomLongbowWeaponItem;
import net.jukoz.me.resources.StateSaverAndLoader;
import net.jukoz.me.resources.datas.Disposition;
import net.jukoz.me.resources.datas.DispositionUtil;
import net.jukoz.me.resources.datas.RaceType;
import net.jukoz.me.resources.datas.factions.Faction;
import net.jukoz.me.resources.datas.factions.FactionLookup;
import net.jukoz.me.resources.datas.factions.FactionUtil;
import net.jukoz.me.resources.datas.npcs.NpcData;
import net.jukoz.me.resources.datas.npcs.NpcUtil;
import net.jukoz.me.resources.datas.npcs.data.NpcGearData;
import net.jukoz.me.resources.datas.npcs.data.NpcRank;
import net.jukoz.me.resources.datas.races.Race;
import net.jukoz.me.resources.datas.races.RaceLookup;
import net.jukoz.me.resources.persistent_datas.PlayerData;
import net.jukoz.me.utils.LoggerUtil;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.item.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Predicate;

public class NpcEntity extends PathfinderMob implements RangedAttackMob {
    protected Disposition disposition;
    private ResourceLocation raceId;
    private Item bow;
    private final CustomBowAttackGoal<NpcEntity> bowAttackGoal = new CustomBowAttackGoal<NpcEntity>(this, 1.0, 16, 30.0f);
    private final MeleeAttackGoal meleeAttackGoal = new MeleeAttackGoal(this, 1.5, false);
    public NpcRank rank;
    protected NpcEntity(EntityType<? extends PathfinderMob> entityType, Level world) {
        super(entityType, world);
        this.updateAttackType();
        for (int i = 0; i < 4; i++) {
            Arrays.fill(this.armorDropChances, 0.0f);
        }
    }

    protected ResourceLocation getFactionId(){
        return null;
    }
    protected ResourceLocation getRaceId(){
        return null;
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, MobSpawnType spawnReason, @Nullable SpawnGroupData entityData) {
        entityData = super.finalizeSpawn(world, difficulty, spawnReason, entityData);
        this.updateAttackType();
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

        this.targetSelector.addGoal(0, new HurtByTargetGoal(this, this.getClass()));
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 6.0f));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));


        this.targetSelector.addGoal(2, new NpcTargetPlayerGoal(this));
    }

    public void updateAttackType() {
        if (this.level() != null && !this.level().isClientSide) {
            this.goalSelector.removeGoal(this.meleeAttackGoal);
            this.goalSelector.removeGoal(this.bowAttackGoal);

            ItemStack itemStack = this.getItemBySlot(EquipmentSlot.MAINHAND);
            if (itemStack != null && itemStack.getItem() instanceof BowItem) {
                this.bow = itemStack.getItem();
            } else {
                this.bow = null;
            }

            if (this.bow != null) {
                this.bowAttackGoal.setAttackInterval(16);
                this.goalSelector.addGoal(2, this.bowAttackGoal);
            } else {
                this.goalSelector.addGoal(2, this.meleeAttackGoal);
            }
        }
    }

    public Disposition getDisposition(){
        return disposition;
    }
    public Item getBow(){
        return this.bow;
    }

    public void setBow(Item bow){
        this.bow = bow;
    }

    public NpcRank getRank() {
        return rank;
    }

    public void setRank(NpcRank rank) {
        this.rank = rank;
    }

    @Override
    public boolean canAttack(LivingEntity target) {
        if(target == null || level().getDifficulty() == Difficulty.PEACEFUL){
            return false;
        }

        if(target instanceof Player player) {
            if(player.isCreative()){
                return false;
            }
            if(disposition != null){
                PlayerData data = StateSaverAndLoader.getPlayerStateReadOnly(player);
                if(data != null){
                    Disposition playerDisposition = data.getCurrentDisposition();
                    if(playerDisposition == disposition){
                        return false;
                    }
                    if(playerDisposition == null)
                        return true;
                    return true;
                }
            }
        }
        return super.canAttack(target);
    }

    public static enum State {
        NEUTRAL,
        ATTACKING,
    }

    public NpcEntity.State getState() {
        if (this.isAggressive()) {
            return NpcEntity.State.ATTACKING;
        }
        return NpcEntity.State.NEUTRAL;
    }

    @Override
    protected void populateDefaultEquipmentSlots(RandomSource random, DifficultyInstance localDifficulty) {
        tryToEquipGears(this.getRank(), this.getRaceId(), getFactionId());
    }

    @Override
    public boolean isPersistenceRequired() {
        return super.isPersistenceRequired();
    }

    public void setItemSlot(EquipmentSlot slot, ItemStack stack) {
        super.setItemSlot(slot, stack);
        if (!this.level().isClientSide) {
            this.updateAttackType();
        }
    }

    @Override
    public boolean canFireProjectileWeapon(ProjectileWeaponItem weapon) {
        return weapon == getBow();
    }

    public ItemStack getProjectile(ItemStack stack) {
        if (stack.getItem() instanceof BowItem) {
            Predicate<ItemStack> predicate = ((ProjectileWeaponItem)stack.getItem()).getSupportedHeldProjectiles();
            ItemStack itemStack = ProjectileWeaponItem.getHeldProjectile(this, predicate);
            return itemStack.isEmpty() ? new ItemStack(Items.ARROW) : itemStack;
        } else {
            return ItemStack.EMPTY;
        }
    }

    @Override
    public void performRangedAttack(LivingEntity target, float pullProgress) {
        ItemStack itemStack = this.getItemInHand(ProjectileUtil.getWeaponHoldingHand(this, getBow()));
        ItemStack itemStack2 = this.getProjectile(itemStack);
        AbstractArrow persistentProjectileEntity = this.createArrowProjectile(itemStack2, pullProgress, itemStack);
        double d = target.getX() - this.getX();
        double e = target.getY(0.3333333333333333) - persistentProjectileEntity.getY();
        double f = target.getZ() - this.getZ();
        double g = Math.sqrt(d * d + f * f);

        boolean isLongbow =(this.bow instanceof CustomLongbowWeaponItem);
        float power = (isLongbow) ? 2.5F : 1.6f;
        float uncertaintyBase = (isLongbow) ? 10 : 14;
        float yVelocityModifier = (isLongbow) ?  0.10000000298023224f : 0.20000000298023224f;

        persistentProjectileEntity.shoot(d, e + g * yVelocityModifier, f, power, (uncertaintyBase - this.level().getDifficulty().getId() * 4));

        this.playSound(SoundEvents.SKELETON_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.level().addFreshEntity(persistentProjectileEntity);
    }

    protected AbstractArrow createArrowProjectile(ItemStack arrow, float damageModifier, @Nullable ItemStack shotFrom) {
        return ProjectileUtil.getMobArrow(this, arrow, damageModifier, shotFrom);
    }

    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        this.updateAttackType();
    }

    @Override
    public int getBaseExperienceReward() {
        int exp = 0;
        switch (this.getRank()){
            case NpcRank.MILITIA -> exp = 10;
            case NpcRank.SOLDIER -> exp = 15;
            case NpcRank.KNIGHT -> exp = 20;
            case NpcRank.VETERAN -> exp = 25;
            case NpcRank.LEADER -> exp = 30;
        }
        return exp;
    }


    @Override
    protected void actuallyHurt(DamageSource source, float amount) {
        super.actuallyHurt(source, amount);
    }


    @Override
    protected void dropExperience(@Nullable Entity attacker) {
        if(attacker instanceof Player player && canDrop(player, null)){
            super.dropExperience(attacker);
        }
    }

    @Override
    protected void dropFromLootTable(DamageSource damageSource, boolean causedByPlayer) {
        if(damageSource.getEntity() instanceof Player player && canDrop(player, damageSource)){
            super.dropFromLootTable(damageSource, causedByPlayer);
        }
    }

    private boolean canDrop(Player player, DamageSource damageSource) {
        /*
        // If we want more control over what drop and what doesn't allow drops
        if(!causedByPlayer){
            String damageSourceValue = damageSource.getTypeRegistryEntry().getIdAsString();
            if(Objects.equals(damageSourceValue, DamageTypes.IN_WALL.getValue().toString()))
                return false;
        }
        */

        if(player != null){
            Disposition playerDisposition = DispositionUtil.getDisposition(player);
            return playerDisposition == null || playerDisposition != getDisposition();
        }

        return true;
    }

    @Override
    protected void dropCustomDeathLoot(ServerLevel world, DamageSource source, boolean causedByPlayer) {
        return;
    }

    protected void tryToEquipGears(NpcRank npcRank, ResourceLocation raceId, ResourceLocation factionId) {
        if(factionId == null)
            return;
        try{
            Faction faction = FactionLookup.getFactionById(level(), factionId);
            Race race = RaceLookup.getRace(level(), raceId);
            NpcData data = faction.getRandomGear(level(), npcRank, race);
            if(data == null)
                return;
            NpcGearData gearData = data.getGear();
            NpcUtil.equipAll(this, gearData);
        } catch (FactionIdentifierException e) {
            LoggerUtil.logError("NpcEntity::Couldn't find faction registry with [%s] for rank [%s]".formatted(factionId, npcRank.toString()));
            throw new RuntimeException(e);
        }
    }

    public int initGoodTargetSelector(int i){
        this.targetSelector.addGoal(++i, new NearestAttackableTargetGoal<>(this, TrollEntity.class, true));
        this.targetSelector.addGoal(++i, new NearestAttackableTargetGoal<>(this, WargEntity.class, true));
        this.targetSelector.addGoal(++i, new NearestAttackableTargetGoal<>(this, MirkwoodSpiderEntity.class, true));

        this.targetSelector.addGoal(++i, new NearestAttackableTargetGoal<>(this, IsengardUrukHaiEntity.class, true));
        this.targetSelector.addGoal(++i, new NearestAttackableTargetGoal<>(this, MordorBlackUrukEntity.class, true));
        this.targetSelector.addGoal(++i, new NearestAttackableTargetGoal<>(this, MistyHobgoblinEntity.class, true));
        this.targetSelector.addGoal(++i, new NearestAttackableTargetGoal<>(this, IsengardOrcEntity.class, true));
        this.targetSelector.addGoal(++i, new NearestAttackableTargetGoal<>(this, MordorOrcEntity.class, true));
        this.targetSelector.addGoal(++i, new NearestAttackableTargetGoal<>(this, MistyGoblinEntity.class, true));

        this.targetSelector.addGoal(++i, new NearestAttackableTargetGoal<>(this, WildGoblinEntity.class, true));
        this.targetSelector.addGoal(++i, new NearestAttackableTargetGoal<>(this, BanditHumanEntity.class, true));

        this.targetSelector.addGoal(++i, new NearestAttackableTargetGoal<>(this, Player.class, true));

        return i;
    }

    public int initEvilTargetSelector(int i){
        this.targetSelector.addGoal(++i, new NearestAttackableTargetGoal<>(this, MirkwoodSpiderEntity.class, true));

        this.targetSelector.addGoal(++i, new NearestAttackableTargetGoal<>(this, GondorHumanEntity.class, true));
        this.targetSelector.addGoal(++i, new NearestAttackableTargetGoal<>(this, RohanHumanEntity.class, true));
        this.targetSelector.addGoal(++i, new NearestAttackableTargetGoal<>(this, GaladhrimElfEntity.class, true));
        this.targetSelector.addGoal(++i, new NearestAttackableTargetGoal<>(this, LongbeardDwarfEntity.class, true));
        this.targetSelector.addGoal(++i, new NearestAttackableTargetGoal<>(this, ShireHobbitEntity.class, true));
        this.targetSelector.addGoal(++i, new NearestAttackableTargetGoal<>(this, DaleHumanEntity.class, true));

        this.targetSelector.addGoal(++i, new NearestAttackableTargetGoal<>(this, WildGoblinEntity.class, true));
        this.targetSelector.addGoal(++i, new NearestAttackableTargetGoal<>(this, BanditHumanEntity.class, true));

        this.targetSelector.addGoal(++i, new NearestAttackableTargetGoal<>(this, Player.class, true));

        this.targetSelector.addGoal(++i, new NearestAttackableTargetGoal<>(this, BroadhoofGoatEntity.class, true));
        return i;
    }

    public int initNeutralTargetSelector(int i){
        this.targetSelector.addGoal(++i, new NearestAttackableTargetGoal<>(this, MirkwoodSpiderEntity.class, true));

        this.targetSelector.addGoal(++i, new NearestAttackableTargetGoal<>(this, IsengardUrukHaiEntity.class, true));
        this.targetSelector.addGoal(++i, new NearestAttackableTargetGoal<>(this, MordorBlackUrukEntity.class, true));
        this.targetSelector.addGoal(++i, new NearestAttackableTargetGoal<>(this, MistyHobgoblinEntity.class, true));
        this.targetSelector.addGoal(++i, new NearestAttackableTargetGoal<>(this, IsengardOrcEntity.class, true));
        this.targetSelector.addGoal(++i, new NearestAttackableTargetGoal<>(this, MordorOrcEntity.class, true));
        this.targetSelector.addGoal(++i, new NearestAttackableTargetGoal<>(this, MistyGoblinEntity.class, true));

        this.targetSelector.addGoal(++i, new NearestAttackableTargetGoal<>(this, GondorHumanEntity.class, true));
        this.targetSelector.addGoal(++i, new NearestAttackableTargetGoal<>(this, RohanHumanEntity.class, true));
        this.targetSelector.addGoal(++i, new NearestAttackableTargetGoal<>(this, GaladhrimElfEntity.class, true));
        this.targetSelector.addGoal(++i, new NearestAttackableTargetGoal<>(this, LongbeardDwarfEntity.class, true));
        this.targetSelector.addGoal(++i, new NearestAttackableTargetGoal<>(this, ShireHobbitEntity.class, true));
        this.targetSelector.addGoal(++i, new NearestAttackableTargetGoal<>(this, DaleHumanEntity.class, true));

        this.targetSelector.addGoal(++i, new NearestAttackableTargetGoal<>(this, Player.class, true));

        this.targetSelector.addGoal(++i, new NearestAttackableTargetGoal<>(this, TrollEntity.class, true));
        this.targetSelector.addGoal(++i, new NearestAttackableTargetGoal<>(this, WargEntity.class, true));
        this.targetSelector.addGoal(++i, new NearestAttackableTargetGoal<>(this, Horse.class, true));

        return i;
    }
}
