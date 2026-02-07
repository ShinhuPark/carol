package com.shindig.carol.entity.custom;

import com.shindig.carol.entity.ModEntities;
import com.shindig.carol.item.ModItems;
import com.shindig.carol.sound.ModSounds;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.math.Vector2f;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

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
        if(rotation >= 360) {
            rotation = 0;
        }
        return rotation;
    }

    public boolean isGrounded() {return inGround;}

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

        Vec3d vec3d2 = vec3d.normalize().multiply((double)0.05F);
        this.setPos(this.getX() - vec3d2.x, this.getY() - vec3d2.y, this.getZ() - vec3d2.z);
        this.playSound(this.getSound(), 1.0F, 1.0F);
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
        entity.damage(this.getDamageSources().thrown(this, this.getOwner()), 4);
        if (!entity.isAlive()) {
            this.playSound(ModSounds.MAIMAI_FULL_COMBO, 1.0f, 1.0f);
            this.playSound(ModSounds.MAIMAI_BREAK_CRITICAL, 1.0f, 1.0f);
        }
        this.playSound(ModSounds.MAIMAI_ANSWER, 2.0f, 1.0f);
        if(!this.getWorld().isClient()) {
            this.getWorld().sendEntityStatus(this, (byte)3);
            this.discard();
        }
    }
}


