package com.shindig.carol.entity.client;

import com.shindig.carol.Carol;
import com.shindig.carol.entity.custom.CarolEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.feature.StuckArrowsFeatureRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class CarolRenderer extends MobEntityRenderer<CarolEntity, CarolModel<CarolEntity>> {
    public CarolRenderer(EntityRendererFactory.Context context) {
        super(context, new CarolModel<>(context.getPart(CarolModel.CAROL)), 0.75f);
        //this.addFeature(new StuckArrowsFeatureRenderer<>(context, this));
        this.addFeature(new CarolStuckTapNotesFeatureRenderer<>(context, this));
    }

    @Override
    public Identifier getTexture(CarolEntity entity) {
        return Identifier.of(Carol.MOD_ID, "textures/entity/carol/carol.png");
    }

    @Override
    public void render(CarolEntity livingEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
        if(livingEntity.isBaby()) {
            matrixStack.scale(0.5f, 0.5f, 0.5f);
        } else {
            matrixStack.scale(1f, 1f, 1f);
        }

        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
