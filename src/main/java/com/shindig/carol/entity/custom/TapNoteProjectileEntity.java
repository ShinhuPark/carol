package com.shindig.carol.entity.custom;

import com.shindig.carol.entity.ModDamageTypes;
import com.shindig.carol.entity.ModEntities;
import com.shindig.carol.item.ModItems;
import com.shindig.carol.sound.ModSounds;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.util.math.Vector2f;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ProjectileDeflection;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class TapNoteProjectileEntity extends PersistentProjectileEntity {
    private float rotation;
    public Vector2f groundedOffset;
    private BlockState inBlockState;


    public TapNoteProjectileEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public TapNoteProjectileEntity(World world, PlayerEntity player) {
        super(ModEntities.TAP_NOTE, player, world, new ItemStack(ModItems.TAP_NOTE), null);
    }

    public TapNoteProjectileEntity(World world, double x, double y, double z, ItemStack stack, @Nullable ItemStack shotFrom) {
        super(ModEntities.TAP_NOTE, x, y, z, world, stack, shotFrom);

    }

    @Override
    protected ItemStack getDefaultItemStack() {
        return new ItemStack(ModItems.TAP_NOTE);
    }

    @Override
    protected SoundEvent getHitSound() {
        return ModSounds.MAIMAI_GOOD;
    }

    public float getRenderingRotation() {
        rotation += 0.5f;
        if (rotation >= 360) {
            rotation = 0;
        }
        return rotation;
    }

    public boolean isGrounded() {
        return inGround;
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        this.inBlockState = this.getWorld().getBlockState(blockHitResult.getBlockPos());
        //super.onBlockHit(blockHitResult);
        //BlockState blockState = this.getWorld().getBlockState(blockHitResult.getBlockPos());
        //blockState.onProjectileHit(this.getWorld(), blockState, blockHitResult, this);
        Vec3d vec3d = blockHitResult.getPos().subtract(this.getX(), this.getY(), this.getZ());
        this.setVelocity(vec3d);
        ItemStack itemStack = this.getWeaponStack();
        World var5 = this.getWorld();
        if (var5 instanceof ServerWorld serverWorld) {
            if (itemStack != null) {
                this.onBlockHitEnchantmentEffects(serverWorld, blockHitResult, itemStack);
            }
        }

        Vec3d vec3d2 = vec3d.normalize().multiply((double) 0.05F);
        this.setPos(this.getX() - vec3d2.x, this.getY() - vec3d2.y, this.getZ() - vec3d2.z);
        this.playSound(this.getSound(), 0.7F, 1.0F);
        this.inGround = true;
        this.shake = 7;
        this.setCritical(false);
        //this.setPierceLevel((byte)0);
        this.setSound(ModSounds.MAIMAI_GOOD);
        //this.clearPiercingStatus();
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        //super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();

        DamageSource damageSource = ModDamageTypes.of(this.getWorld(), ModDamageTypes.TAP_NOTE, this, this.getOwner());
        //check if it successfully damaged the entity
        if (entity.damage(damageSource, 4)) {
            if (entity instanceof LivingEntity) {
                if (entity instanceof CarolEntity carol) {
                    if (!this.getWorld().isClient && this.getPierceLevel() <= 0) {
                        carol.setStuckTapNoteCount(carol.getStuckTapNoteCount() + 1);
                    }
                }

                this.playSound(ModSounds.MAIMAI_ANSWER, 2.0f, 1.0f);

                //Check if the entity is dead
                if (!entity.isAlive()) {
                    this.playSound(ModSounds.MAIMAI_FULL_COMBO, 1.0f, 1.0f);
                    this.playSound(ModSounds.MAIMAI_BREAK_CRITICAL, 1.0f, 1.0f);
                }

                //Remove Tap Note
                if (!this.getWorld().isClient()) {
                    this.getWorld().sendEntityStatus(this, (byte) 3);
                    this.discard();
                }

            }
        } else {
            if (entity instanceof CarolEntity) {
                World world = this.getWorld();
                if(!world.isClient()) {
                    ((ServerWorld) world).spawnParticles(ParticleTypes.SWEEP_ATTACK,
                            this.getX(), this.getY(), this.getZ(), 0, 0, 0, 0, 0);
                    ((ServerWorld) world).spawnParticles(ParticleTypes.ELECTRIC_SPARK,
                            this.getX(), this.getY(), this.getZ(), 20, 0, 0, 0, 2);
                }
                this.playSound(ModSounds.MAIMAI_BREAK_CRITICAL, 1.0f, 1.0f);

                this.deflect(ProjectileDeflection.SIMPLE, entity, this.getOwner(), false);
                this.setVelocity(this.getVelocity().multiply(2.0));
                if (!this.getWorld().isClient && this.getVelocity().lengthSquared() < 1.0E-7) {
                    if (this.pickupType == PersistentProjectileEntity.PickupPermission.ALLOWED) {
                        this.dropStack(this.asItemStack(), 0.1F);
                    }
                    this.discard();
                }
            }
        }
    }
}