package com.shindig.carol.entity.client;

import com.shindig.carol.Carol;
import com.shindig.carol.entity.custom.CarolEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.util.math.MatrixStack;

import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class CarolCollarFeatureRenderer extends FeatureRenderer<CarolEntity, CarolModel<CarolEntity>> {
    private static final Identifier SKIN = Identifier.of(Carol.MOD_ID,"textures/entity/carol/carol_collar.png");
    private final CarolModel<CarolEntity> model;

    public CarolCollarFeatureRenderer(FeatureRendererContext<CarolEntity, CarolModel<CarolEntity>> context, EntityModelLoader loader) {
        super(context);
        this.model = new CarolModel<>(loader.getModelPart(CarolModel.CAROL_COLLAR));
    }

    public void render(
            MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, CarolEntity carolEntity, float f, float g, float h, float j, float k, float l
    ) {
        if (carolEntity.isTamed()) {
            int m = carolEntity.getCollarColor().getEntityColor();
            render(this.getContextModel(), this.model, SKIN, matrixStack, vertexConsumerProvider, i, carolEntity, f, g, j, k, l, h, m);
        }
    }
}
