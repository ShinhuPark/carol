package com.shindig.carol.entity.client;

import com.shindig.carol.entity.custom.CarolEntity;

import com.shindig.carol.entity.custom.TapNoteProjectileEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
public class CarolStuckTapNotesFeatureRenderer<T extends CarolEntity, M extends CarolModel<T>> extends CarolStuckObjectsFeatureRenderer<T, M> {
    private final EntityRenderDispatcher dispatcher;

    public CarolStuckTapNotesFeatureRenderer(EntityRendererFactory.Context context, LivingEntityRenderer<T, M> entityRenderer) {
        super(entityRenderer);
        this.dispatcher = context.getRenderDispatcher();
    }

    @Override
    protected int getObjectCount(T entity) {
        return entity.getStuckTapNoteCount();
    }

    @Override
    protected void renderObject(
            MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, Entity entity, float directionX, float directionY, float directionZ, float tickDelta
    ) {
        float f = MathHelper.sqrt(directionX * directionX + directionZ * directionZ);
        TapNoteProjectileEntity tapNoteEntity = new TapNoteProjectileEntity(entity.getWorld(), entity.getX(), entity.getY(), entity.getZ(), ItemStack.EMPTY, null);
        tapNoteEntity.setYaw((float)(Math.atan2(directionX, directionZ) * 180.0F / (float)Math.PI));
        tapNoteEntity.setPitch((float)(Math.atan2(directionY, f) * 180.0F / (float)Math.PI));
        tapNoteEntity.prevYaw = tapNoteEntity.getYaw();
        tapNoteEntity.prevPitch = tapNoteEntity.getPitch();
        this.dispatcher.render(tapNoteEntity, 0.0, 0.75f, 0.0, 0.0F, tickDelta, matrices, vertexConsumers, light);
    }
}