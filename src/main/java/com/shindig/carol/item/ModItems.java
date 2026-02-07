package com.shindig.carol.item;

import com.shindig.carol.entity.ModEntities;
import com.shindig.carol.item.custom.TapNoteItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import com.shindig.carol.Carol;
public class ModItems {

    public static final Item TAP_NOTE = registerItem(
            new TapNoteItem(new Item.Settings().maxCount(16)),
            "tap_note");

    public static final Item CAROL_SPAWN_EGG = registerItem(
            new SpawnEggItem(ModEntities.CAROL, 0xfefcfd, 0xc81e15, new Item.Settings()),
            "carol_spawn_egg");

    public static Item registerItem(Item item, String id) {
        //Create a identifier for an item
        Identifier itemID = Identifier.of(Carol.MOD_ID, id);

        //Register an item
        Item registeredItem = Registry.register(Registries.ITEM, itemID, item);

        //Return the registered item
        return registeredItem;
    }
    public static void initialize() {
        Carol.LOGGER.info("Registering Mod Items for " + Carol.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT)
                .register((itemGroup) -> itemGroup.add(ModItems.TAP_NOTE));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS)
                .register((itemGroup) -> itemGroup.add(ModItems.CAROL_SPAWN_EGG));
    }
}
