package com.shindig.carol.entity;

import com.shindig.carol.Carol;
import com.shindig.carol.entity.custom.CarolEntity;
import com.shindig.carol.entity.custom.TapNoteProjectileEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<CarolEntity> CAROL = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(Carol.MOD_ID, "carol"),
            EntityType.Builder.create(CarolEntity::new, SpawnGroup.CREATURE)
                    .dimensions(1f, 2.0f).build());

    public static final EntityType<TapNoteProjectileEntity> TAP_NOTE = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(Carol.MOD_ID, "tap_note"),
            EntityType.Builder.<TapNoteProjectileEntity>create(TapNoteProjectileEntity::new, SpawnGroup.MISC)
                    .dimensions(0.5f, 0.5f).build());

    public static void initialize() {
        Carol.LOGGER.info("Registering Mod Entities for " + Carol.MOD_ID);
    }
}
