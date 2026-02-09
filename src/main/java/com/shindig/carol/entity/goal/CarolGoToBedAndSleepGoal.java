package com.shindig.carol.entity.goal;

import com.shindig.carol.entity.custom.CarolEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.MoveToTargetPosGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;

import java.util.EnumSet;

public class CarolGoToBedAndSleepGoal extends MoveToTargetPosGoal {
    private final CarolEntity carol;

    public CarolGoToBedAndSleepGoal(CarolEntity carol, double speed, int range) {
        super(carol, speed, range, 6);
        this.carol = carol;
        this.lowestY = -2;
        this.setControls(EnumSet.of(Control.JUMP, Control.MOVE));
    }

    @Override
    public boolean canStart() {
        return this.carol.isTamed() && !this.carol.isSitting() && !this.carol.isInSleepingPose() && super.canStart();
    }

    @Override
    public void start() {
        super.start();
        this.cat.setInSittingPose(false);
    }

    @Override
    protected int getInterval(PathAwareEntity mob) {
        return 40;
    }

    @Override
    public void stop() {
        super.stop();
        this.cat.setInSleepingPose(false);
    }

    @Override
    public void tick() {
        super.tick();
        this.cat.setInSittingPose(false);
        if (!this.hasReached()) {
            this.cat.setInSleepingPose(false);
        } else if (!this.cat.isInSleepingPose()) {
            this.cat.setInSleepingPose(true);
        }
    }

    @Override
    protected boolean isTargetPos(WorldView world, BlockPos pos) {
        return world.isAir(pos.up()) && world.getBlockState(pos).isIn(BlockTags.BEDS);
    }
}
