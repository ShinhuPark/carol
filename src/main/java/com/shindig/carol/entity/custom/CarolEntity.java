package com.shindig.carol.entity.custom;

import com.shindig.carol.Carol;
import com.shindig.carol.entity.ModDamageTypes;
import com.shindig.carol.entity.ModEntities;
import com.shindig.carol.entity.data.CarolTrackedDataHandlerRegistry;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.*;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.block.enums.BedPart;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Hand;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.function.ValueLists;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;
import java.util.function.IntFunction;
import java.util.function.Predicate;

public class CarolEntity extends TameableEntity {
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState sleepingAnimationState = new AnimationState();
    public final AnimationState sittingAnimationState = new AnimationState();
    public final AnimationState parryingAnimationState = new AnimationState();

    private int idleAnimationTimeout = 0;

    private static final TrackedData<CarolEntity.State> STATE = DataTracker.registerData(CarolEntity.class, CarolTrackedDataHandlerRegistry.CAROL_STATE);
    private static final TrackedData<Integer> COLLAR_COLOR = DataTracker.registerData(CarolEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Integer> STUCK_TAP_NOTE_COUNT = DataTracker.registerData(CarolEntity.class, TrackedDataHandlerRegistry.INTEGER);
    @Nullable
    private CarolEntity.CarolFleeGoal<PlayerEntity> fleeGoal;
    @Nullable
    private net.minecraft.entity.ai.goal.TemptGoal temptGoal;
    public int stuckTapNoteTimer;
    public int parryTimer;
    public Vec3d parriedTapNoteVelocity;

    public CarolEntity(EntityType<? extends CarolEntity> entityType, World world) {
        super(entityType, world);
        this.onTamedChanged();
    }

    @Override
    protected void initGoals() {
        this.temptGoal = new CarolEntity.TemptGoal(this, 0.6, stack -> stack.isIn(ItemTags.CAT_FOOD), true);
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(1, new TameableEntity.TameableEscapeDangerGoal(1.5));
        this.goalSelector.add(1, new CarolEntity.ParryGoal(this));
        this.goalSelector.add(2, new SitGoal(this));
        this.goalSelector.add(3, new CarolEntity.SleepWithOwnerGoal(this));
        this.goalSelector.add(4, this.temptGoal);
        this.goalSelector.add(5, new GoToBedAndSleepGoal(this, 1.1, 8));
        this.goalSelector.add(6, new FollowOwnerGoal(this, 1.0, 10.0F, 5.0F));
        this.goalSelector.add(7, new CarolSitOnBlockGoal(this, 0.8));
        this.goalSelector.add(8, new PounceAtTargetGoal(this, 0.3F));
        this.goalSelector.add(9, new AttackGoal(this));
        this.goalSelector.add(10, new AnimalMateGoal(this, 0.8));
        this.goalSelector.add(11, new WanderAroundFarGoal(this, 0.8, 1.0000001E-5F));
        this.goalSelector.add(12, new LookAtEntityGoal(this, PlayerEntity.class, 10.0F));
        this.targetSelector.add(1, new UntamedActiveTargetGoal(this, RabbitEntity.class, false, null));
        this.targetSelector.add(1, new UntamedActiveTargetGoal(this, TurtleEntity.class, false, TurtleEntity.BABY_TURTLE_ON_LAND_FILTER));
    }

    public DyeColor getCollarColor() {
        return DyeColor.byId(this.dataTracker.get(COLLAR_COLOR));
    }

    private void setCollarColor(DyeColor color) {
        this.dataTracker.set(COLLAR_COLOR, color.getId());
    }

    public final int getStuckTapNoteCount() {
        return this.dataTracker.get(STUCK_TAP_NOTE_COUNT);
    }

    public final void setStuckTapNoteCount(int stuckTapNoteCount) {
        this.dataTracker.set(STUCK_TAP_NOTE_COUNT, stuckTapNoteCount);
    }

    public State getState() {
        return this.dataTracker.get(STATE);
    }

    public void setState(State state) {
        this.dataTracker.set(STATE,state);
    }

    @Override
    public void setInSittingPose(boolean inSittingPose) {
        super.setInSittingPose(inSittingPose);
        if (inSittingPose) {
            this.setState(State.SITTING);
        } else {
            this.setState(State.IDLE);
        }
    }

    @Override
    public void setSitting(boolean sitting) {
        super.setSitting(sitting);
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(STATE, CarolEntity.State.IDLE);
        builder.add(COLLAR_COLOR, DyeColor.RED.getId());
        builder.add(STUCK_TAP_NOTE_COUNT, 0);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putByte("CollarColor", (byte)this.getCollarColor().getId());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if (nbt.contains("CollarColor", NbtElement.NUMBER_TYPE)) {
            this.setCollarColor(DyeColor.byId(nbt.getInt("CollarColor")));
        }
    }

    @Override
    public void mobTick() {
        if (this.getMoveControl().isMoving()) {
            double d = this.getMoveControl().getSpeed();
            if (d == 0.6) {
                this.setPose(EntityPose.CROUCHING);
                this.setSprinting(false);
            } else if (d == 1.33) {
                this.setPose(EntityPose.STANDING);
                this.setSprinting(true);
            } else {
                this.setPose(EntityPose.STANDING);
                this.setSprinting(false);
            }
        } else {
            this.setPose(EntityPose.STANDING);
            this.setSprinting(false);
        }
    }

    /* SOUNDS */
    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        if (this.isTamed()) {
            if (this.isInLove()) {
                return SoundEvents.ENTITY_CAT_PURR;
            } else {
                return this.random.nextInt(4) == 0 ? SoundEvents.ENTITY_CAT_PURREOW : SoundEvents.ENTITY_CAT_AMBIENT;
            }
        } else {
            return SoundEvents.ENTITY_CAT_STRAY_AMBIENT;
        }
    }


    @Override
    public int getMinAmbientSoundDelay() {
        return 120;
    }

    public void hiss() {
        this.playSound(SoundEvents.ENTITY_CAT_HISS);
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_CAT_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_CAT_DEATH;
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3F)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 3.0);
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        if (this.isInvulnerableTo(source)) {
            return false;
        } else {

            //If damagesource is Tap Note projectile, Carol does not take damage and gets a knockback a
            if (source.isOf(ModDamageTypes.TAP_NOTE)) {
                TapNoteProjectileEntity tapNote = (TapNoteProjectileEntity) source.getSource();
                Vec3d vec3d = this.getVelocity();
                Vec3d vec3d2 = new Vec3d( this.getX() - tapNote.getX(), 0.0, this.getZ() - tapNote.getZ());
                if (vec3d2.lengthSquared() > 1.0E-7) {
                    vec3d2 = vec3d2.normalize().multiply(1.0).add(vec3d.multiply(0.2));
                }
                this.setVelocity(vec3d2.x, 0.0f, vec3d2.z);

                //Save parried Tap Note velocity which will be used in CarolEntity.ParryGoal, for making Carol look at Tap Note
                parriedTapNoteVelocity = tapNote.getVelocity().negate();
                this.setYaw(-((float)MathHelper.atan2(vec3d.x, vec3d.z)) * (180.0F / (float)Math.PI));
                this.bodyYaw = this.getYaw();

                this.setState(State.PARRYING);
                this.parryTimer = 30;



                return false;
            } else {
                return super.damage(source, amount);

            }
        }
    }

    @Override
    protected void eat(PlayerEntity player, Hand hand, ItemStack stack) {
        if (this.isBreedingItem(stack)) {
            this.playSound(SoundEvents.ENTITY_CAT_EAT, 1.0F, 1.0F);
        }

        super.eat(player, hand, stack);
    }


    @Override
    public void tick() {
        super.tick();
        if (this.temptGoal != null && this.temptGoal.isActive() && !this.isTamed() && this.age % 100 == 0) {
            this.playSound(SoundEvents.ENTITY_CAT_BEG_FOR_FOOD, 1.0F, 1.0F);
        }

        if (this.getWorld().isClient()) {
            this.setupAnimationStates();

            int i = this.getStuckTapNoteCount();
            if (i > 0) {
                if (this.stuckTapNoteTimer <= 0) {
                    this.stuckTapNoteTimer = 20 * (30 - i);
                }

                this.stuckTapNoteTimer--;
                if (this.stuckTapNoteTimer <= 0) {
                    this.setStuckTapNoteCount(i - 1);
                }
            }
        }
    }

    private void setupAnimationStates() {
        switch (this.getState()) {
            case IDLE:
                this.idleAnimationState.startIfNotRunning(this.age);
                this.sleepingAnimationState.stop();
                this.sittingAnimationState.stop();
                this.parryingAnimationState.stop();
                break;
            case SLEEPING:
                this.idleAnimationState.stop();
                this.sleepingAnimationState.startIfNotRunning(this.age);
                this.sittingAnimationState.stop();
                this.parryingAnimationState.stop();
                break;
            case SITTING:
                this.idleAnimationState.stop();
                this.sleepingAnimationState.stop();
                this.sittingAnimationState.startIfNotRunning(this.age);
                this.parryingAnimationState.stop();
                break;
            case PARRYING:
                this.idleAnimationState.stop();
                this.sleepingAnimationState.stop();
                this.sittingAnimationState.stop();
                if(parryTimer == 30){this.parryingAnimationState.start(this.age);}
                break;
        }
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isIn(ItemTags.CAT_FOOD);
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity passiveEntity) {
        CarolEntity baby = ModEntities.CAROL.create(world);

        if (baby != null && passiveEntity instanceof CarolEntity carolEntity) {
            if (this.isTamed()) {
                baby.setOwnerUuid(this.getOwnerUuid());
                baby.setTamed(true, true);
                if (this.random.nextBoolean()) {
                    baby.setCollarColor(this.getCollarColor());
                } else {
                    baby.setCollarColor(carolEntity.getCollarColor());
                }
            }
        }

        return baby;
    }

    @Override
    public boolean canBreedWith(AnimalEntity other) {
        if (!this.isTamed()) {
            return false;
        } else {
            return !(other instanceof CarolEntity carolEntity) ? false : carolEntity.isTamed() && super.canBreedWith(other);
        }
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason,
                                 @Nullable EntityData entityData) {
        return super.initialize(world, difficulty, spawnReason, entityData);
    }


    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        Item item = itemStack.getItem();
        if (this.isTamed()) {
            this.sendMessage(Text.literal("I am interacted and tamed!"));
            if (this.isOwner(player)) {
                if (item instanceof DyeItem dyeItem) {
                    DyeColor dyeColor = dyeItem.getColor();
                    if (dyeColor != this.getCollarColor()) {
                        if (!this.getWorld().isClient()) {
                            this.setCollarColor(dyeColor);
                            itemStack.decrementUnlessCreative(1, player);
                            this.setPersistent();
                        }

                        return ActionResult.success(this.getWorld().isClient());
                    }
                } else if (this.isBreedingItem(itemStack) && this.getHealth() < this.getMaxHealth()) {
                    if (!this.getWorld().isClient()) {
                        this.eat(player, hand, itemStack);
                        FoodComponent foodComponent = itemStack.get(DataComponentTypes.FOOD);
                        this.heal(foodComponent != null ? foodComponent.nutrition() : 1.0F);
                    }

                    return ActionResult.success(this.getWorld().isClient());
                }

                ActionResult actionResult = super.interactMob(player, hand);
                if (!actionResult.isAccepted()) {
                    this.setSitting(!this.isSitting());
                    return ActionResult.success(this.getWorld().isClient());
                }

                return actionResult;
            }
        } else if (this.isBreedingItem(itemStack)) {
            if (!this.getWorld().isClient()) {
                this.eat(player, hand, itemStack);
                this.tryTame(player);
                this.setPersistent();
            }

            return ActionResult.success(this.getWorld().isClient());
        }

        ActionResult actionResult = super.interactMob(player, hand);
        if (actionResult.isAccepted()) {
            this.setPersistent();
        }

        return actionResult;
    }

    @Override
    public boolean canImmediatelyDespawn(double distanceSquared) {
        return !this.isTamed() && this.age > 2400;
    }

    @Override
    public void setTamed(boolean tamed, boolean updateAttributes) {
        super.setTamed(tamed, updateAttributes);
        this.onTamedChanged();
    }

    protected void onTamedChanged() {
        if (this.fleeGoal == null) {
            this.fleeGoal = new CarolEntity.CarolFleeGoal<>(this, PlayerEntity.class, 16.0F, 0.8, 1.33);
        }

        this.goalSelector.remove(this.fleeGoal);
        if (!this.isTamed()) {
            this.goalSelector.add(4, this.fleeGoal);
        }
    }

    private void tryTame(PlayerEntity player) {
        if (this.random.nextInt(3) == 0) {
            this.setOwner(player);
            this.setSitting(true);
            this.getWorld().sendEntityStatus(this, EntityStatuses.ADD_POSITIVE_PLAYER_REACTION_PARTICLES);
        } else {
            this.getWorld().sendEntityStatus(this, EntityStatuses.ADD_NEGATIVE_PLAYER_REACTION_PARTICLES);
        }
    }

    @Override
    public boolean bypassesSteppingEffects() {
        return this.isInSneakingPose() || super.bypassesSteppingEffects();
    }


    static class CarolFleeGoal<T extends LivingEntity> extends FleeEntityGoal<T> {
        private final CarolEntity carol;

        public CarolFleeGoal(CarolEntity carol, Class<T> fleeFromType, float distance, double slowSpeed, double fastSpeed) {
            super(carol, fleeFromType, distance, slowSpeed, fastSpeed, EntityPredicates.EXCEPT_CREATIVE_OR_SPECTATOR::test);
            this.carol = carol;
        }

        @Override
        public boolean canStart() {
            return !this.carol.isTamed() && super.canStart();
        }

        @Override
        public boolean shouldContinue() {
            return !this.carol.isTamed() && super.shouldContinue();
        }
    }

    static class SleepWithOwnerGoal extends Goal {
        private final CarolEntity carol;
        @Nullable
        private PlayerEntity owner;
        @Nullable
        private BlockPos bedPos;
        private int ticksOnBed;

        public SleepWithOwnerGoal(CarolEntity carol) {
            this.carol = carol;
        }

        @Override
        public boolean canStart() {
            if (!this.carol.isTamed()) {
                return false;
            } else if (this.carol.isSitting()) {
                return false;
            } else {
                LivingEntity livingEntity = this.carol.getOwner();
                if (livingEntity instanceof PlayerEntity) {
                    this.owner = (PlayerEntity)livingEntity;
                    if (!livingEntity.isSleeping()) {
                        return false;
                    }

                    if (this.carol.squaredDistanceTo(this.owner) > 100.0) {
                        return false;
                    }

                    BlockPos blockPos = this.owner.getBlockPos();
                    BlockState blockState = this.carol.getWorld().getBlockState(blockPos);
                    if (blockState.isIn(BlockTags.BEDS)) {
                        this.bedPos = (BlockPos)blockState.getOrEmpty(BedBlock.FACING)
                                .map(direction -> blockPos.offset(direction.getOpposite()))
                                .orElseGet(() -> new BlockPos(blockPos));
                        return !this.cannotSleep();
                    }
                }

                return false;
            }
        }

        private boolean cannotSleep() {
            for (CarolEntity carolEntity : this.carol.getWorld().getNonSpectatingEntities(CarolEntity.class, new Box(this.bedPos).expand(2.0))) {
                if (carolEntity != this.carol && carolEntity.getState().equals(State.SLEEPING)) {
                    return true;
                }
            }

            return false;
        }

        @Override
        public boolean shouldContinue() {
            return this.carol.isTamed() && !this.carol.isSitting() && this.owner != null && this.owner.isSleeping() && this.bedPos != null && !this.cannotSleep();
        }

        @Override
        public void start() {
            if (this.bedPos != null) {
                this.carol.setInSittingPose(false);
                this.carol.getNavigation().startMovingTo(this.bedPos.getX(), this.bedPos.getY(), this.bedPos.getZ(), 1.1F);
            }
        }

        @Override
        public void stop() {
            this.carol.setState(State.IDLE);
            float f = this.carol.getWorld().getSkyAngle(1.0F);
            if (this.owner.getSleepTimer() >= 100 && f > 0.77 && f < 0.8 && this.carol.getWorld().getRandom().nextFloat() < 0.7) {
                this.dropMorningGifts();
            }

            this.ticksOnBed = 0;
            this.carol.getNavigation().stop();
        }

        private void dropMorningGifts() {
            Random random = this.carol.getRandom();
            BlockPos.Mutable mutable = new BlockPos.Mutable();
            mutable.set(this.carol.isLeashed() ? this.carol.getLeashHolder().getBlockPos() : this.carol.getBlockPos());
            this.carol.teleport(mutable.getX() + random.nextInt(11) - 5, mutable.getY() + random.nextInt(5) - 2, mutable.getZ() + random.nextInt(11) - 5, false);
            mutable.set(this.carol.getBlockPos());
            LootTable lootTable = this.carol.getWorld().getServer().getReloadableRegistries().getLootTable(LootTables.CAT_MORNING_GIFT_GAMEPLAY);
            LootContextParameterSet lootContextParameterSet = new LootContextParameterSet.Builder((ServerWorld)this.carol.getWorld())
                    .add(LootContextParameters.ORIGIN, this.carol.getPos())
                    .add(LootContextParameters.THIS_ENTITY, this.carol)
                    .build(LootContextTypes.GIFT);

            for (ItemStack itemStack : lootTable.generateLoot(lootContextParameterSet)) {
                this.carol
                        .getWorld()
                        .spawnEntity(
                                new ItemEntity(
                                        this.carol.getWorld(),
                                        (double)mutable.getX() - MathHelper.sin(this.carol.bodyYaw * (float) (Math.PI / 180.0)),
                                        mutable.getY(),
                                        (double)mutable.getZ() + MathHelper.cos(this.carol.bodyYaw * (float) (Math.PI / 180.0)),
                                        itemStack
                                )
                        );
            }
        }

        @Override
        public void tick() {
            if (this.owner != null && this.bedPos != null) {
                this.carol.setInSittingPose(false);
                this.carol.getNavigation().startMovingTo(this.bedPos.getX(), this.bedPos.getY(), this.bedPos.getZ(), 1.1F);
                if (this.carol.squaredDistanceTo(this.owner) < 2.5) {
                    this.ticksOnBed++;
                    if (this.ticksOnBed > this.getTickCount(16)) {
                        this.carol.setState(State.SLEEPING);
                    } else {
                        this.carol.lookAtEntity(this.owner, 45.0F, 45.0F);
                    }
                } else {
//                    this.carol.setInSleepingPose(false);
                }
            }
        }
    }

    static class TemptGoal extends net.minecraft.entity.ai.goal.TemptGoal {
        @Nullable
        private PlayerEntity player;
        private final CarolEntity carol;

        public TemptGoal(CarolEntity carol, double speed, Predicate<ItemStack> foodPredicate, boolean canBeScared) {
            super(carol, speed, foodPredicate, canBeScared);
            this.carol = carol;
        }

        @Override
        public void tick() {
            super.tick();
            if (this.player == null && this.mob.getRandom().nextInt(this.getTickCount(600)) == 0) {
                this.player = this.closestPlayer;
            } else if (this.mob.getRandom().nextInt(this.getTickCount(500)) == 0) {
                this.player = null;
            }
        }

        @Override
        protected boolean canBeScared() {
            return this.player != null && this.player.equals(this.closestPlayer) ? false : super.canBeScared();
        }

        @Override
        public boolean canStart() {
            return super.canStart() && !this.carol.isTamed();
        }
    }

    static class GoToBedAndSleepGoal extends MoveToTargetPosGoal {
        private final CarolEntity carol;

        public GoToBedAndSleepGoal(CarolEntity carol, double speed, int range) {
            super(carol, speed, range, 6);
            this.carol = carol;
            this.lowestY = -2;
            this.setControls(EnumSet.of(Goal.Control.JUMP, Goal.Control.MOVE));
        }

        @Override
        public boolean canStart() {
            return this.carol.isTamed() && !this.carol.isSitting() && !this.carol.getState().equals(State.SLEEPING) && super.canStart();
        }

        @Override
        public void start() {
            super.start();
            this.carol.setInSittingPose(false);
        }

        @Override
        protected int getInterval(PathAwareEntity mob) {
            return 40;
        }

        @Override
        public void stop() {
            super.stop();
            this.carol.setState(State.IDLE);
        }

        @Override
        public void tick() {
            super.tick();
            this.carol.setInSittingPose(false);
            if (!this.hasReached()) {
//                this.carol.setInSleepingPose(false);
            } else if (!this.carol.getState().equals(State.SLEEPING)) {
                this.carol.setState(State.SLEEPING);
            }
        }

        @Override
        protected boolean isTargetPos(WorldView world, BlockPos pos) {
            return world.isAir(pos.up()) && world.getBlockState(pos).isIn(BlockTags.BEDS);
        }
    }
    static class CarolSitOnBlockGoal extends MoveToTargetPosGoal {
        private final CarolEntity carol;

        public CarolSitOnBlockGoal(CarolEntity carol, double speed) {
            super(carol, speed, 8);
            this.carol = carol;
        }

        @Override
        public boolean canStart() {
            return this.carol.isTamed() && !this.carol.isSitting() && super.canStart();
        }

        @Override
        public void start() {
            super.start();
            this.carol.setInSittingPose(false);
        }

        @Override
        public void stop() {
            super.stop();
            this.carol.setInSittingPose(false);
        }

        @Override
        public void tick() {
            super.tick();
            this.carol.setInSittingPose(this.hasReached());
        }

        @Override
        protected boolean isTargetPos(WorldView world, BlockPos pos) {
            if (!world.isAir(pos.up())) {
                return false;
            } else {
                BlockState blockState = world.getBlockState(pos);
                if (blockState.isOf(Blocks.CHEST)) {
                    return ChestBlockEntity.getPlayersLookingInChestCount(world, pos) < 1;
                } else {
                    return blockState.isOf(Blocks.FURNACE) && blockState.get(FurnaceBlock.LIT)
                            ? true
                            : blockState.isIn(BlockTags.BEDS, state -> (Boolean)state.getOrEmpty(BedBlock.PART).map(part -> part != BedPart.HEAD).orElse(true));
                }
            }
        }
    }

    static class ParryGoal extends Goal {
        private final CarolEntity carol;
        @Nullable
        private TapNoteProjectileEntity tapNote;
        private World world;
        private double speed;

        ParryGoal(CarolEntity carol) {
            this.carol = carol;
            this.world = carol.getWorld();
            this.setControls(EnumSet.of(Goal.Control.JUMP, Goal.Control.MOVE, Goal.Control.LOOK));
        }

        @Override
        public boolean canStart() {
            if (carol.parryTimer > 0 && carol.parriedTapNoteVelocity != null) {return true;}
            return false;
        }
        @Override
        public void start() {
            carol.getNavigation().stop();
        }
        @Override
        public void tick() {
            speed = carol.getVelocity().length();
            carol.setState(State.PARRYING);
            if (carol.parriedTapNoteVelocity != null){
                Vec3d vec3d = carol.parriedTapNoteVelocity;
                carol.setYaw(-((float)MathHelper.atan2(vec3d.x, vec3d.z)) * (180.0F / (float)Math.PI));
                carol.bodyYaw = carol.getYaw();
            }
            if (!world.isClient()) {
                if (carol.parryTimer > 20) {
                    ((ServerWorld) world).spawnParticles(new BlockStateParticleEffect(ParticleTypes.BLOCK, Blocks.DIRT.getDefaultState()),
                            carol.getX(), carol.getY(), carol.getZ(), carol.parryTimer - 20, 0, 0, 0, 1);
                }
            }
            carol.parryTimer--;
        }
        public void stop() {
            carol.setState(State.IDLE);
        }
    }


    public static enum State implements StringIdentifiable {
        IDLE("idle", 0, 0),
        SLEEPING("sleeping", 20, 1),
        SITTING("sitting", 20, 2),
        PARRYING("parrying", 20, 3);

        private static final StringIdentifiable.EnumCodec<CarolEntity.State> CODEC = StringIdentifiable.createCodec(CarolEntity.State::values);
        private static final IntFunction<CarolEntity.State> INDEX_TO_VALUE = ValueLists.createIdToValueFunction(
                CarolEntity.State::getIndex, values(), ValueLists.OutOfBoundsHandling.ZERO
        );
        public static final PacketCodec<ByteBuf, CarolEntity.State> PACKET_CODEC = PacketCodecs.indexed(INDEX_TO_VALUE, CarolEntity.State::getIndex);
        private final String name;
        private final int lengthInTicks;
        private final int index;

        State(final String name, final int lengthInTicks, final int index) {
            this.name = name;
            this.lengthInTicks = lengthInTicks;
            this.index = index;
        }

        public static CarolEntity.State fromName(String name) {
            return (CarolEntity.State)CODEC.byId(name, IDLE);
        }

        @Override
        public String asString() {
            return this.name;
        }

        private int getIndex() {
            return this.index;
        }

        public int getLengthInTicks() {
            return this.lengthInTicks;
        }
    }
}


