package com.shindig.carol;

import com.shindig.carol.entity.ModEntities;
import com.shindig.carol.entity.client.CarolModel;
import com.shindig.carol.entity.client.CarolRenderer;
import com.shindig.carol.entity.client.TapNoteProjectileModel;
import com.shindig.carol.entity.client.TapNoteProjectileRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class CarolClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(CarolModel.CAROL, CarolModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.CAROL, CarolRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(TapNoteProjectileModel.TAP_NOTE, TapNoteProjectileModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.TAP_NOTE, TapNoteProjectileRenderer::new);
    }
}
