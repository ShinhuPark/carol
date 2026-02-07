package com.shindig.carol.item.custom;

import com.shindig.carol.Carol;
import com.shindig.carol.entity.custom.TapNoteProjectileEntity;
import com.shindig.carol.sound.ModSounds;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class TapNoteItem extends Item {
    public TapNoteItem(Settings settings){
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        Carol.LOGGER.info("Tap Note is used!");
        ItemStack itemStack = user.getStackInHand(hand);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), ModSounds.MAIMAI_NORMAL, SoundCategory.NEUTRAL, 0.5f, 0.4f / (world.getRandom().nextFloat() * 0.4f + 0.8f));
        if (!world.isClient) {
            Carol.LOGGER.info("isClient is false!");
            TapNoteProjectileEntity tapNote = new TapNoteProjectileEntity(world, user);
            tapNote.setVelocity(user, user.getPitch(), user.getYaw(), 0.0f, 1.5f,0f);
            world.spawnEntity(tapNote);
        }

        user.incrementStat(Stats.USED.getOrCreateStat(this));
        if(!user.getAbilities().creativeMode) {
            itemStack.decrement(1);
        }
    return TypedActionResult.success(itemStack, world.isClient());
    }

}
