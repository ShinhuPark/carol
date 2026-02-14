package com.shindig.carol.entity.data;

import com.shindig.carol.entity.custom.CarolEntity;
import net.minecraft.entity.data.TrackedDataHandler;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;

public class CarolTrackedDataHandlerRegistry {

    public static final TrackedDataHandler<CarolEntity.State> CAROL_STATE = TrackedDataHandler.create(CarolEntity.State.PACKET_CODEC);

    public static void initialize() {
        TrackedDataHandlerRegistry.register(CAROL_STATE);
    }
}
