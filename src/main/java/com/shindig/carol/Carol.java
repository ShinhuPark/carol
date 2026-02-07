package com.shindig.carol;

import com.shindig.carol.entity.ModEntities;
import com.shindig.carol.entity.custom.CarolEntity;
import com.shindig.carol.item.ModItems;
import com.shindig.carol.sound.ModSounds;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Carol implements ModInitializer {
	public static final String MOD_ID = "carol";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");

        ModItems.initialize();
        ModEntities.initialize();
        ModSounds.initialize();

        FabricDefaultAttributeRegistry.register(ModEntities.CAROL, CarolEntity.createAttributes());
        LOGGER.info("Mod items, entities, sounds have been initialized!");
	}
}