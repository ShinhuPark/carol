package com.shindig.carol.entity;

import com.shindig.carol.Carol;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class ModDamageTypes {
    public static final RegistryKey<DamageType> TAP_NOTE = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(Carol.MOD_ID, "tap_note"));
    public static DamageSource of(World world, RegistryKey<DamageType> key, Entity source, Entity attacker) {
        return new DamageSource(world.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(key), source, attacker);
    }

}
