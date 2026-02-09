package com.shindig.carol.entity.client;

import com.shindig.carol.Carol;
import com.shindig.carol.entity.custom.CarolEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.random.Random;

@Environment(EnvType.CLIENT)
public abstract class CarolStuckObjectsFeatureRenderer<T extends CarolEntity, M extends CarolModel<T>> extends FeatureRenderer<T, M> {
    public CarolStuckObjectsFeatureRenderer(LivingEntityRenderer<T, M> entityRenderer) {
        super(entityRenderer);
    }

    protected abstract int getObjectCount(T entity);

    protected abstract void renderObject(
            MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, Entity entity, float directionX, float directionY, float directionZ, float tickDelta
    );

    public void render(
            MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, T livingEntity, float f, float g, float h, float j, float k, float l
    ) {
        int m = this.getObjectCount(livingEntity);
        Random random = Random.create(livingEntity.getId());
        if (m > 0) {
            for (int n = 0; n < m; n++) {
                matrixStack.push();
                ModelPart modelPart = this.getContextModel().getRandomPart(random);
                //Carol.LOGGER.info("Stuck at " + modelPart);
                ModelPart.Cuboid cuboid = modelPart.getRandomCuboid(random);
                modelPart.rotate(matrixStack);

                float o = random.nextFloat();
                float p = random.nextFloat();
                float q = random.nextFloat();

//                float r = MathHelper.lerp(o, cuboid.minX, cuboid.maxX) / 16.0F;
//                float s = MathHelper.lerp(p, cuboid.minY, cuboid.maxY) / 16.0F;
//                float t = MathHelper.lerp(q, cuboid.minZ, cuboid.maxZ) / 16.0F;

                float r = MathHelper.lerp(0.5f, cuboid.minX, cuboid.maxX) / 16.0F;
                float s = MathHelper.lerp(0.5f, cuboid.minY, cuboid.maxY) / 16.0F;
                float t = MathHelper.lerp(0.5f, cuboid.minZ, cuboid.maxZ) / 16.0F;
                matrixStack.translate(r, s, t);
                o = -1.0F * (o * 2.0F - 1.0F);
                p = -1.0F * (p * 2.0F - 1.0F);
                q = -1.0F * (q * 2.0F - 1.0F);
                //this.renderObject(matrixStack, vertexConsumerProvider, i, livingEntity, o, p, q, h);
                this.renderObject(matrixStack, vertexConsumerProvider, i, livingEntity, 0, 0, 0, h);

                matrixStack.pop();
            }
        }
    }
}
