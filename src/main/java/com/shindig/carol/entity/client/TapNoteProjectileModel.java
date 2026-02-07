package com.shindig.carol.entity.client;

import com.shindig.carol.Carol;
import com.shindig.carol.entity.custom.TapNoteProjectileEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class TapNoteProjectileModel extends EntityModel<TapNoteProjectileEntity> {
    public static final EntityModelLayer TAP_NOTE = new EntityModelLayer(Identifier.of(Carol.MOD_ID, "tap_note"), "main");
    private final ModelPart quater;
    private final ModelPart quater3;
    private final ModelPart quater2;
    private final ModelPart quater4;
    public TapNoteProjectileModel(ModelPart root) {
        this.quater = root.getChild("quater");
        this.quater3 = root.getChild("quater3");
        this.quater2 = root.getChild("quater2");
        this.quater4 = root.getChild("quater4");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData quater = modelPartData.addChild("quater", ModelPartBuilder.create().uv(0, 0).cuboid(4.0F, -1.0F, -5.0F, 3.0F, 1.0F, 5.0F, new Dilation(-0.002F))
                .uv(0, 6).cuboid(0.0F, -1.0F, -7.0F, 5.0F, 1.0F, 3.0F, new Dilation(-0.001F))
                .uv(0, 13).cuboid(4.0F, -1.0F, -2.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 12).cuboid(7.0F, -1.0F, -3.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F))
                .uv(0, 13).cuboid(3.0F, -1.0F, -4.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 14).cuboid(5.0F, -1.0F, -6.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 14).cuboid(2.0F, -1.0F, -4.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 13).cuboid(6.0F, -1.0F, -5.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 16.0F, -0.5F, -1.5708F, 0.0F, 0.0F));

        ModelPartData white_border_r1 = quater.addChild("white_border_r1", ModelPartBuilder.create().uv(0, 13).cuboid(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 0.0F, -5.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData white_border_r2 = quater.addChild("white_border_r2", ModelPartBuilder.create().uv(0, 13).cuboid(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(4.0F, 0.0F, -7.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData white_border_r3 = quater.addChild("white_border_r3", ModelPartBuilder.create().uv(0, 12).cuboid(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, 0.0F, -8.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData quater3 = modelPartData.addChild("quater3", ModelPartBuilder.create().uv(0, 0).cuboid(4.0F, -1.0F, -5.0F, 3.0F, 1.0F, 5.0F, new Dilation(-0.002F))
                .uv(0, 6).cuboid(0.0F, -1.0F, -7.0F, 5.0F, 1.0F, 3.0F, new Dilation(-0.001F))
                .uv(0, 13).cuboid(4.0F, -1.0F, -2.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 12).cuboid(7.0F, -1.0F, -3.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F))
                .uv(0, 13).cuboid(3.0F, -1.0F, -4.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 14).cuboid(5.0F, -1.0F, -6.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 14).cuboid(2.0F, -1.0F, -4.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 13).cuboid(6.0F, -1.0F, -5.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 16.0F, -0.5F, -1.5708F, 0.0F, 3.1416F));

        ModelPartData white_border_r4 = quater3.addChild("white_border_r4", ModelPartBuilder.create().uv(0, 13).cuboid(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 0.0F, -5.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData white_border_r5 = quater3.addChild("white_border_r5", ModelPartBuilder.create().uv(0, 13).cuboid(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(4.0F, 0.0F, -7.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData white_border_r6 = quater3.addChild("white_border_r6", ModelPartBuilder.create().uv(0, 12).cuboid(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, 0.0F, -8.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData quater2 = modelPartData.addChild("quater2", ModelPartBuilder.create().uv(0, 0).cuboid(4.0F, -1.0F, -5.0F, 3.0F, 1.0F, 5.0F, new Dilation(-0.002F))
                .uv(0, 6).cuboid(0.0F, -1.0F, -7.0F, 5.0F, 1.0F, 3.0F, new Dilation(-0.001F))
                .uv(0, 13).cuboid(4.0F, -1.0F, -2.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 12).cuboid(7.0F, -1.0F, -3.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F))
                .uv(0, 13).cuboid(3.0F, -1.0F, -4.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 14).cuboid(5.0F, -1.0F, -6.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 14).cuboid(2.0F, -1.0F, -4.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 13).cuboid(6.0F, -1.0F, -5.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 16.0F, -0.5F, -1.5708F, 0.0F, 1.5708F));

        ModelPartData white_border_r7 = quater2.addChild("white_border_r7", ModelPartBuilder.create().uv(0, 13).cuboid(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 0.0F, -5.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData white_border_r8 = quater2.addChild("white_border_r8", ModelPartBuilder.create().uv(0, 13).cuboid(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(4.0F, 0.0F, -7.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData white_border_r9 = quater2.addChild("white_border_r9", ModelPartBuilder.create().uv(0, 12).cuboid(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, 0.0F, -8.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData quater4 = modelPartData.addChild("quater4", ModelPartBuilder.create().uv(0, 0).cuboid(4.0F, -1.0F, -5.0F, 3.0F, 1.0F, 5.0F, new Dilation(-0.002F))
                .uv(0, 6).cuboid(0.0F, -1.0F, -7.0F, 5.0F, 1.0F, 3.0F, new Dilation(-0.001F))
                .uv(0, 13).cuboid(4.0F, -1.0F, -2.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 12).cuboid(7.0F, -1.0F, -3.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F))
                .uv(0, 13).cuboid(3.0F, -1.0F, -4.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 14).cuboid(5.0F, -1.0F, -6.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 14).cuboid(2.0F, -1.0F, -4.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 13).cuboid(6.0F, -1.0F, -5.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 16.0F, -0.5F, -1.5708F, 0.0F, -1.5708F));

        ModelPartData white_border_r10 = quater4.addChild("white_border_r10", ModelPartBuilder.create().uv(0, 13).cuboid(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 0.0F, -5.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData white_border_r11 = quater4.addChild("white_border_r11", ModelPartBuilder.create().uv(0, 13).cuboid(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(4.0F, 0.0F, -7.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData white_border_r12 = quater4.addChild("white_border_r12", ModelPartBuilder.create().uv(0, 12).cuboid(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, 0.0F, -8.0F, 0.0F, -1.5708F, 0.0F));
        return TexturedModelData.of(modelData, 16, 16);
    }
    @Override
    public void setAngles(TapNoteProjectileEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }
    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        quater.render(matrices, vertexConsumer, light, overlay, color);
        quater3.render(matrices, vertexConsumer, light, overlay, color);
        quater2.render(matrices, vertexConsumer, light, overlay, color);
        quater4.render(matrices, vertexConsumer, light, overlay, color);
    }
}
