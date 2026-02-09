package com.shindig.carol.entity.client;

import com.shindig.carol.Carol;
import com.shindig.carol.entity.custom.TapNoteProjectileEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

public class TapNoteProjectileRenderer extends EntityRenderer<TapNoteProjectileEntity> {
    protected TapNoteProjectileModel model;

    public TapNoteProjectileRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.model = new TapNoteProjectileModel(ctx.getPart(TapNoteProjectileModel.TAP_NOTE));
    }

    @Override
    public void render(TapNoteProjectileEntity entity, float yaw, float tickDelta, MatrixStack matrices,
                       VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();

        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(MathHelper.lerp(tickDelta, entity.prevYaw, entity.getYaw())));
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(MathHelper.lerp(tickDelta, entity.prevPitch, entity.getPitch())));
        matrices.scale(0.5f, 0.5f, 0.5f);
        matrices.translate(0, -0.5f, 0);


        VertexConsumer vertexConsumer = ItemRenderer.getDirectItemGlintConsumer(vertexConsumers,
                this.model.getLayer(Identifier.of(Carol.MOD_ID, "textures/entity/tap_note/tap_note.png")), false, true);
        this.model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV);
        matrices.pop();
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers,light);
    }

    @Override
    public Identifier getTexture(TapNoteProjectileEntity entity) {
        return Identifier.of(Carol.MOD_ID, "textures/entity/tap_note/tap_note.png");
    }
}
