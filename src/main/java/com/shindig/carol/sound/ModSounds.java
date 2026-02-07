package com.shindig.carol.sound;

import com.shindig.carol.Carol;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    private ModSounds() {
        // private empty constructor to avoid accidental instantiation
    }

    // ITEM_METAL_WHISTLE is the name of the custom sound event
    // and is called in the mod to use the custom sound
    public static final SoundEvent MAIMAI_GREAT = registerSound("maimai_great");
    public static final SoundEvent MAIMAI_BREAK_CRITICAL = registerSound("maimai_break_critical");
    public static final SoundEvent MAIMAI_FULL_COMBO = registerSound("maimai_full_combo");
    public static final SoundEvent MAIMAI_GOOD = registerSound("maimai_good");
    public static final SoundEvent MAIMAI_NORMAL = registerSound("maimai_normal");
    public static final SoundEvent MAIMAI_ANSWER = registerSound("maimai_answer");

    // actual registration of all the custom SoundEvents
    private static SoundEvent registerSound(String id) {
        Identifier identifier = Identifier.of(Carol.MOD_ID, id);
        return Registry.register(Registries.SOUND_EVENT, identifier, SoundEvent.of(identifier));
    }

    // This static method starts class initialization, which then initializes
    // the static class variables (e.g. ITEM_METAL_WHISTLE).
    public static void initialize() {
        Carol.LOGGER.info("Registering Mod Sounds for " + Carol.MOD_ID);
        // Technically this method can stay empty, but some developers like to notify
        // the console, that certain parts of the mod have been successfully initialized
    }
}
