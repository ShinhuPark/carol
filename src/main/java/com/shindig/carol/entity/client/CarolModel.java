package com.shindig.carol.entity.client;

import com.google.common.collect.ImmutableList;
import com.shindig.carol.Carol;
import com.shindig.carol.entity.custom.CarolEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;

import java.util.List;

public class CarolModel<T extends CarolEntity> extends SinglePartEntityModel<T> {
    public static final EntityModelLayer CAROL = new EntityModelLayer(Identifier.of(Carol.MOD_ID, "carol"), "main");

    private final List<ModelPart> parts;

    private final ModelPart root;
    private final ModelPart torso;
    private final ModelPart head;
    private final ModelPart earL;
    private final ModelPart earR;
    private final ModelPart hairback;
    private final ModelPart hairbackM;
    private final ModelPart hairbackL;
    private final ModelPart hairbackL2;
    private final ModelPart hairbackL3;
    private final ModelPart hairbackR;
    private final ModelPart hairbackR2;
    private final ModelPart hairbackR3;
    private final ModelPart hairF;
    private final ModelPart whisker;
    private final ModelPart fishR;
    private final ModelPart fishL;
    private final ModelPart arm_L;
    private final ModelPart arm_R;
    private final ModelPart leg_L;
    private final ModelPart leg_R;
    private final ModelPart bell;
    private final ModelPart tail1;
    private final ModelPart tail2;
    private final ModelPart tail3;
    private final ModelPart tail4;
    private final ModelPart tail5;
    private final ModelPart skirt;
    private final ModelPart top;
    private final ModelPart bottom;
    private final ModelPart panty;
    private final ModelPart pantyline;
    public CarolModel(ModelPart root) {
        this.root = root.getChild("root");
        this.torso = this.root.getChild("torso");
        this.head = this.torso.getChild("head");
        this.earL = this.head.getChild("earL");
        this.earR = this.head.getChild("earR");
        this.hairback = this.head.getChild("hairback");
        this.hairbackM = this.hairback.getChild("hairbackM");
        this.hairbackL = this.hairback.getChild("hairbackL");
        this.hairbackL2 = this.hairback.getChild("hairbackL2");
        this.hairbackL3 = this.hairback.getChild("hairbackL3");
        this.hairbackR = this.hairback.getChild("hairbackR");
        this.hairbackR2 = this.hairback.getChild("hairbackR2");
        this.hairbackR3 = this.hairback.getChild("hairbackR3");
        this.hairF = this.head.getChild("hairF");
        this.whisker = this.head.getChild("whisker");
        this.fishR = this.head.getChild("fishR");
        this.fishL = this.head.getChild("fishL");
        this.arm_L = this.torso.getChild("arm_L");
        this.arm_R = this.torso.getChild("arm_R");
        this.leg_L = this.torso.getChild("leg_L");
        this.leg_R = this.torso.getChild("leg_R");
        this.bell = this.torso.getChild("bell");
        this.tail1 = this.torso.getChild("tail1");
        this.tail2 = this.tail1.getChild("tail2");
        this.tail3 = this.tail2.getChild("tail3");
        this.tail4 = this.tail3.getChild("tail4");
        this.tail5 = this.tail4.getChild("tail5");
        this.skirt = this.torso.getChild("skirt");
        this.top = this.skirt.getChild("top");
        this.bottom = this.skirt.getChild("bottom");
        this.panty = this.torso.getChild("panty");
        this.pantyline = this.panty.getChild("pantyline");

        this.parts = (List<ModelPart>)root.traverse().filter(part -> !part.isEmpty()).collect(ImmutableList.toImmutableList());
        //Carol.LOGGER.info("list of stuckable parts are " + this.parts);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData torso = root.addChild("torso", ModelPartBuilder.create().uv(37, 0).cuboid(-3.5F, -2.75F, -2.0F, 7.0F, 1.0F, 4.0F, new Dilation(0.001F))
                .uv(0, 35).cuboid(-3.0F, -6.0F, -1.5F, 6.0F, 6.0F, 3.0F, new Dilation(0.001F))
                .uv(19, 35).cuboid(-3.5F, -6.5F, -2.0F, 7.0F, 1.0F, 4.0F, new Dilation(0.001F)), ModelTransform.pivot(0.0F, -8.0F, 0.0F));

        ModelPartData apronR_r1 = torso.addChild("apronR_r1", ModelPartBuilder.create().uv(18, 103).cuboid(-0.5F, -1.7574F, 3.4926F, 2.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 1.0F, 0.0F, -2.3562F, -0.3927F, 3.1416F));

        ModelPartData apronL_r1 = torso.addChild("apronL_r1", ModelPartBuilder.create().uv(25, 103).cuboid(-1.5F, -1.7574F, 3.4926F, 2.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 1.0F, 0.0F, -2.3562F, 0.3927F, 3.1416F));

        ModelPartData apronM_r1 = torso.addChild("apronM_r1", ModelPartBuilder.create().uv(96, 56).cuboid(-1.5F, -1.7574F, 3.4926F, 3.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 1.0F, 0.0F, -2.3562F, 0.0F, 3.1416F));

        ModelPartData wingsmallL_r1 = torso.addChild("wingsmallL_r1", ModelPartBuilder.create().uv(106, 35).cuboid(-0.75F, -2.0F, 0.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, -1.5F, -3.0F, 0.2979F, 1.1268F, 0.5344F));

        ModelPartData wingbigL_r1 = torso.addChild("wingbigL_r1", ModelPartBuilder.create().uv(9, 102).cuboid(-0.75F, -2.0F, 0.0F, 1.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(2.5F, -3.5F, -3.25F, 0.0F, 1.1781F, -0.1309F));

        ModelPartData wingsmallR_r1 = torso.addChild("wingsmallR_r1", ModelPartBuilder.create().uv(101, 75).cuboid(-0.25F, -2.0F, 0.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-2.0F, -1.5F, -3.0F, 0.2979F, -1.1268F, -0.5344F));

        ModelPartData wingbigR_r1 = torso.addChild("wingbigR_r1", ModelPartBuilder.create().uv(94, 101).cuboid(-0.25F, -2.0F, 0.0F, 1.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-2.5F, -3.5F, -3.25F, 0.0F, -1.1781F, 0.1309F));

        ModelPartData boob_r1 = torso.addChild("boob_r1", ModelPartBuilder.create().uv(37, 6).cuboid(-3.0F, -2.5F, -2.0F, 6.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -4.0F, 0.0F, 1.0472F, 0.0F, 0.0F));

        ModelPartData head = torso.addChild("head", ModelPartBuilder.create().uv(58, 53).cuboid(-3.0F, -9.5F, -2.5F, 6.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-4.5F, -9.0F, -4.5F, 9.0F, 9.0F, 9.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -6.0F, 0.0F));

        ModelPartData earL = head.addChild("earL", ModelPartBuilder.create().uv(0, 45).cuboid(-1.6218F, 0.0126F, -1.5F, 3.0F, 8.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(6.5F, -10.0F, 1.5F, 0.0F, 0.0F, 0.7854F));

        ModelPartData earL_r1 = earL.addChild("earL_r1", ModelPartBuilder.create().uv(45, 47).cuboid(-0.0858F, -0.0429F, -1.5F, 3.0F, 7.0F, 3.0F, new Dilation(-0.001F)), ModelTransform.of(-1.536F, 0.0555F, 0.0F, 0.0F, 0.0F, 0.3927F));

        ModelPartData earL_r2 = earL.addChild("earL_r2", ModelPartBuilder.create().uv(50, 13).cuboid(-3.0858F, -0.0429F, -1.5F, 3.0F, 7.0F, 3.0F, new Dilation(-0.002F)), ModelTransform.of(1.464F, 0.0555F, 0.0F, 0.0F, 0.0F, -0.3927F));

        ModelPartData earR = head.addChild("earR", ModelPartBuilder.create().uv(45, 35).cuboid(-1.3782F, 0.0126F, -1.5F, 3.0F, 8.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-6.5F, -10.0F, 1.5F, 0.0F, 0.0F, -0.7854F));

        ModelPartData earR_r1 = earR.addChild("earR_r1", ModelPartBuilder.create().uv(13, 53).cuboid(-2.9142F, -0.0429F, -1.5F, 3.0F, 7.0F, 3.0F, new Dilation(-0.001F)), ModelTransform.of(1.536F, 0.0555F, 0.0F, 0.0F, 0.0F, -0.3927F));

        ModelPartData earR_r2 = earR.addChild("earR_r2", ModelPartBuilder.create().uv(50, 24).cuboid(0.0858F, -0.0429F, -1.5F, 3.0F, 7.0F, 3.0F, new Dilation(-0.002F)), ModelTransform.of(-1.464F, 0.0555F, 0.0F, 0.0F, 0.0F, 0.3927F));

        ModelPartData hairback = head.addChild("hairback", ModelPartBuilder.create(), ModelTransform.of(0.0F, -9.0F, 3.0F, 0.1309F, 0.0F, 0.0F));

        ModelPartData hairbackM = hairback.addChild("hairbackM", ModelPartBuilder.create().uv(54, 65).cuboid(-1.5F, 5.1716F, -1.0441F, 3.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -5.1716F, 1.0441F));

        ModelPartData hairbackM_r1 = hairbackM.addChild("hairbackM_r1", ModelPartBuilder.create().uv(84, 0).cuboid(-1.5F, 0.0F, 0.0F, 3.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 24.5778F, 2.816F, 0.5236F, 0.0F, 0.0F));

        ModelPartData hairbackM_r2 = hairbackM.addChild("hairbackM_r2", ModelPartBuilder.create().uv(26, 66).cuboid(-1.5F, 0.0F, 0.0F, 3.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 19.9584F, 0.9026F, 0.3927F, 0.0F, 0.0F));

        ModelPartData hairbackM_r3 = hairbackM.addChild("hairbackM_r3", ModelPartBuilder.create().uv(46, 81).cuboid(-1.5F, 0.0F, 0.0F, 3.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 15.1288F, -0.3915F, 0.2618F, 0.0F, 0.0F));

        ModelPartData hairbackM_r4 = hairbackM.addChild("hairbackM_r4", ModelPartBuilder.create().uv(65, 65).cuboid(-1.5F, 0.0F, 0.0F, 3.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 10.1716F, -1.0441F, 0.1309F, 0.0F, 0.0F));

        ModelPartData hairbackL = hairback.addChild("hairbackL", ModelPartBuilder.create().uv(13, 71).cuboid(-1.5F, 5.1716F, -1.0441F, 3.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -5.1716F, 1.0441F, 0.0F, -0.1309F, -0.1309F));

        ModelPartData hairbackL_r1 = hairbackL.addChild("hairbackL_r1", ModelPartBuilder.create().uv(86, 32).cuboid(-1.5F, 0.0F, 0.0F, 3.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 24.5778F, 2.816F, 0.5236F, 0.0F, 0.0F));

        ModelPartData hairbackL_r2 = hairbackL.addChild("hairbackL_r2", ModelPartBuilder.create().uv(0, 72).cuboid(-1.5F, 0.0F, 0.0F, 3.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 19.9584F, 0.9026F, 0.3927F, 0.0F, 0.0F));

        ModelPartData hairbackL_r3 = hairbackL.addChild("hairbackL_r3", ModelPartBuilder.create().uv(57, 81).cuboid(-1.5F, 0.0F, 0.0F, 3.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 15.1288F, -0.3915F, 0.2618F, 0.0F, 0.0F));

        ModelPartData hairbackL_r4 = hairbackL.addChild("hairbackL_r4", ModelPartBuilder.create().uv(37, 71).cuboid(-1.5F, 0.0F, 0.0F, 3.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 10.1716F, -1.0441F, 0.1309F, 0.0F, 0.0F));

        ModelPartData hairbackL2 = hairback.addChild("hairbackL2", ModelPartBuilder.create().uv(73, 0).cuboid(-1.5F, 5.1716F, -1.0441F, 3.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -5.1716F, 1.0441F, 0.0176F, -0.2607F, -0.2652F));

        ModelPartData hairbackL_r5 = hairbackL2.addChild("hairbackL_r5", ModelPartBuilder.create().uv(86, 40).cuboid(-1.5F, 0.0F, 0.0F, 3.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 24.5778F, 2.816F, 0.5236F, 0.0F, 0.0F));

        ModelPartData hairbackL_r6 = hairbackL2.addChild("hairbackL_r6", ModelPartBuilder.create().uv(59, 73).cuboid(-1.5F, 0.0F, 0.0F, 3.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 19.9584F, 0.9026F, 0.3927F, 0.0F, 0.0F));

        ModelPartData hairbackL_r7 = hairbackL2.addChild("hairbackL_r7", ModelPartBuilder.create().uv(81, 66).cuboid(-1.5F, 0.0F, 0.0F, 3.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 15.1288F, -0.3915F, 0.2618F, 0.0F, 0.0F));

        ModelPartData hairbackL_r8 = hairbackL2.addChild("hairbackL_r8", ModelPartBuilder.create().uv(48, 73).cuboid(-1.5F, 0.0F, 0.0F, 3.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 10.1716F, -1.0441F, 0.1309F, 0.0F, 0.0F));

        ModelPartData hairbackL3 = hairback.addChild("hairbackL3", ModelPartBuilder.create().uv(70, 73).cuboid(-1.5F, 5.1716F, -1.0441F, 3.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -5.1716F, 1.0441F, 0.0537F, -0.3432F, -0.4054F));

        ModelPartData hairbackL_r9 = hairbackL3.addChild("hairbackL_r9", ModelPartBuilder.create().uv(86, 48).cuboid(-1.5F, 0.0F, 0.0F, 3.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 24.5778F, 2.816F, 0.5236F, 0.0F, 0.0F));

        ModelPartData hairbackL_r10 = hairbackL3.addChild("hairbackL_r10", ModelPartBuilder.create().uv(75, 34).cuboid(-1.5F, 0.0F, 0.0F, 3.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 19.9584F, 0.9026F, 0.3927F, 0.0F, 0.0F));

        ModelPartData hairbackL_r11 = hairbackL3.addChild("hairbackL_r11", ModelPartBuilder.create().uv(68, 81).cuboid(-1.5F, 0.0F, 0.0F, 3.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 15.1288F, -0.3915F, 0.2618F, 0.0F, 0.0F));

        ModelPartData hairbackL_r12 = hairbackL3.addChild("hairbackL_r12", ModelPartBuilder.create().uv(24, 74).cuboid(-1.5F, 0.0F, 0.0F, 3.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 10.1716F, -1.0441F, 0.1309F, 0.0F, 0.0F));

        ModelPartData hairbackR = hairback.addChild("hairbackR", ModelPartBuilder.create().uv(76, 16).cuboid(-1.5F, 5.1716F, -1.0441F, 3.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -5.1716F, 1.0441F, 0.0F, 0.1309F, 0.1309F));

        ModelPartData hairbackR_r1 = hairbackR.addChild("hairbackR_r1", ModelPartBuilder.create().uv(11, 87).cuboid(-1.5F, 0.0F, 0.0F, 3.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 24.5778F, 2.816F, 0.5236F, 0.0F, 0.0F));

        ModelPartData hairbackR_r2 = hairbackR.addChild("hairbackR_r2", ModelPartBuilder.create().uv(76, 58).cuboid(-1.5F, 0.0F, 0.0F, 3.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 19.9584F, 0.9026F, 0.3927F, 0.0F, 0.0F));

        ModelPartData hairbackR_r3 = hairbackR.addChild("hairbackR_r3", ModelPartBuilder.create().uv(22, 82).cuboid(-1.5F, 0.0F, 0.0F, 3.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 15.1288F, -0.3915F, 0.2618F, 0.0F, 0.0F));

        ModelPartData hairbackR_r4 = hairbackR.addChild("hairbackR_r4", ModelPartBuilder.create().uv(76, 24).cuboid(-1.5F, 0.0F, 0.0F, 3.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 10.1716F, -1.0441F, 0.1309F, 0.0F, 0.0F));

        ModelPartData hairbackR2 = hairback.addChild("hairbackR2", ModelPartBuilder.create().uv(11, 79).cuboid(-1.5F, 5.1716F, -1.0441F, 3.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -5.1716F, 1.0441F, 0.0176F, 0.2607F, 0.2652F));

        ModelPartData hairbackR_r5 = hairbackR2.addChild("hairbackR_r5", ModelPartBuilder.create().uv(87, 16).cuboid(-1.5F, 0.0F, 0.0F, 3.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 24.5778F, 2.816F, 0.5236F, 0.0F, 0.0F));

        ModelPartData hairbackR_r6 = hairbackR2.addChild("hairbackR_r6", ModelPartBuilder.create().uv(0, 80).cuboid(-1.5F, 0.0F, 0.0F, 3.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 19.9584F, 0.9026F, 0.3927F, 0.0F, 0.0F));

        ModelPartData hairbackR_r7 = hairbackR2.addChild("hairbackR_r7", ModelPartBuilder.create().uv(79, 82).cuboid(-1.5F, 0.0F, 0.0F, 3.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 15.1288F, -0.3915F, 0.2618F, 0.0F, 0.0F));

        ModelPartData hairbackR_r8 = hairbackR2.addChild("hairbackR_r8", ModelPartBuilder.create().uv(35, 79).cuboid(-1.5F, 0.0F, 0.0F, 3.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 10.1716F, -1.0441F, 0.1309F, 0.0F, 0.0F));

        ModelPartData hairbackR3 = hairback.addChild("hairbackR3", ModelPartBuilder.create().uv(75, 42).cuboid(-1.5F, 5.1716F, -1.0441F, 3.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -5.1716F, 1.0441F, 0.0537F, 0.3432F, 0.4054F));

        ModelPartData hairbackR_r9 = hairbackR3.addChild("hairbackR_r9", ModelPartBuilder.create().uv(87, 8).cuboid(-1.5F, 0.0F, 0.0F, 3.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 24.5778F, 2.816F, 0.5236F, 0.0F, 0.0F));

        ModelPartData hairbackR_r10 = hairbackR3.addChild("hairbackR_r10", ModelPartBuilder.create().uv(76, 8).cuboid(-1.5F, 0.0F, 0.0F, 3.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 19.9584F, 0.9026F, 0.3927F, 0.0F, 0.0F));

        ModelPartData hairbackR_r11 = hairbackR3.addChild("hairbackR_r11", ModelPartBuilder.create().uv(81, 74).cuboid(-1.5F, 0.0F, 0.0F, 3.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 15.1288F, -0.3915F, 0.2618F, 0.0F, 0.0F));

        ModelPartData hairbackR_r12 = hairbackR3.addChild("hairbackR_r12", ModelPartBuilder.create().uv(75, 50).cuboid(-1.5F, 0.0F, 0.0F, 3.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 10.1716F, -1.0441F, 0.1309F, 0.0F, 0.0F));

        ModelPartData hairF = head.addChild("hairF", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -8.5F, -3.75F));

        ModelPartData hairF_r1 = hairF.addChild("hairF_r1", ModelPartBuilder.create().uv(104, 0).cuboid(0.75F, 0.75F, -1.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.1731F, -0.0227F, -0.1289F));

        ModelPartData hairF_r2 = hairF.addChild("hairF_r2", ModelPartBuilder.create().uv(86, 103).cuboid(-1.75F, 0.75F, -1.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.1731F, 0.0227F, 0.1289F));

        ModelPartData hairF_r3 = hairF.addChild("hairF_r3", ModelPartBuilder.create().uv(32, 103).cuboid(-1.0F, 0.75F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(87, 24).cuboid(-2.0F, -0.25F, -1.0F, 4.0F, 2.0F, 2.0F, new Dilation(0.002F))
                .uv(60, 0).cuboid(-2.0F, -0.75F, -1.0F, 4.0F, 2.0F, 2.0F, new Dilation(0.001F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

        ModelPartData whisker = head.addChild("whisker", ModelPartBuilder.create(), ModelTransform.pivot(-7.0784F, -1.5F, 0.0F));

        ModelPartData whiskersmall2R_r1 = whisker.addChild("whiskersmall2R_r1", ModelPartBuilder.create().uv(56, 6).cuboid(0.0F, 0.0F, -4.0F, 4.0F, 1.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, -0.3491F));

        ModelPartData whiskersmall2L_r1 = whisker.addChild("whiskersmall2L_r1", ModelPartBuilder.create().uv(58, 41).cuboid(-4.0F, 0.0F, -4.0F, 4.0F, 1.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(14.1569F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.3491F));

        ModelPartData whiskersmall1R_r1 = whisker.addChild("whiskersmall1R_r1", ModelPartBuilder.create().uv(26, 53).cuboid(0.0F, 0.0F, -4.0F, 4.0F, 1.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        ModelPartData whiskersmall1L_r1 = whisker.addChild("whiskersmall1L_r1", ModelPartBuilder.create().uv(58, 35).cuboid(-4.0F, 0.0F, -4.0F, 4.0F, 1.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(14.1569F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

        ModelPartData whiskerbig2R_r1 = whisker.addChild("whiskerbig2R_r1", ModelPartBuilder.create().uv(25, 19).cuboid(0.0F, 0.0F, -6.0F, 6.0F, 1.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(-1.4142F, -2.0F, 0.0F, 0.0F, -0.7854F, -0.2182F));

        ModelPartData whiskerbig2L_r1 = whisker.addChild("whiskerbig2L_r1", ModelPartBuilder.create().uv(25, 27).cuboid(-6.0F, 0.0F, -6.0F, 6.0F, 1.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(15.5711F, -2.0F, 0.0F, 0.0F, 0.7854F, 0.2182F));

        ModelPartData whiskerbig1R_r1 = whisker.addChild("whiskerbig1R_r1", ModelPartBuilder.create().uv(0, 19).cuboid(0.0F, 0.0F, -6.0F, 6.0F, 1.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(-1.4142F, -2.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        ModelPartData whiskerbig1L_r1 = whisker.addChild("whiskerbig1L_r1", ModelPartBuilder.create().uv(0, 27).cuboid(-6.0F, 0.0F, -6.0F, 6.0F, 1.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(15.5711F, -2.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

        ModelPartData fishR = head.addChild("fishR", ModelPartBuilder.create().uv(0, 57).cuboid(-0.5F, -1.0F, 0.0F, 1.0F, 2.0F, 5.0F, new Dilation(0.002F)), ModelTransform.of(-4.5F, -5.75F, -3.25F, -0.1797F, -0.6784F, 0.2817F));

        ModelPartData cube_r1 = fishR.addChild("cube_r1", ModelPartBuilder.create().uv(105, 58).cuboid(-0.5F, 2.25F, -2.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -1.7654F, 2.3478F, 1.1781F, 0.0F, 0.0F));

        ModelPartData cube_r2 = fishR.addChild("cube_r2", ModelPartBuilder.create().uv(13, 49).cuboid(-0.5F, 0.0F, -1.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -1.7654F, 1.8478F, 1.1781F, 0.0F, 0.0F));

        ModelPartData cube_r3 = fishR.addChild("cube_r3", ModelPartBuilder.create().uv(46, 108).cuboid(-0.5F, 0.0F, -2.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.001F)), ModelTransform.of(0.0F, -1.7654F, 1.8478F, 0.3927F, 0.0F, 0.0F));

        ModelPartData cube_r4 = fishR.addChild("cube_r4", ModelPartBuilder.create().uv(105, 53).cuboid(-0.5F, -4.25F, -2.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.001F)), ModelTransform.of(0.0F, 1.7654F, 2.3478F, -1.1781F, 0.0F, 0.0F));

        ModelPartData cube_r5 = fishR.addChild("cube_r5", ModelPartBuilder.create().uv(13, 45).cuboid(-0.5F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.001F)), ModelTransform.of(0.0F, 1.7654F, 1.8478F, -1.1781F, 0.0F, 0.0F));

        ModelPartData cube_r6 = fishR.addChild("cube_r6", ModelPartBuilder.create().uv(53, 108).cuboid(-0.5F, -1.0F, -2.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 1.7654F, 1.8478F, -0.3927F, 0.0F, 0.0F));

        ModelPartData cube_r7 = fishR.addChild("cube_r7", ModelPartBuilder.create().uv(105, 89).cuboid(-0.5F, -1.0F, -1.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.003F)), ModelTransform.of(0.0F, 0.0F, 1.5F, 0.7854F, 0.0F, 0.0F));

        ModelPartData fishL = head.addChild("fishL", ModelPartBuilder.create().uv(63, 12).cuboid(-0.5F, -1.0F, 0.0F, 1.0F, 2.0F, 5.0F, new Dilation(0.002F)), ModelTransform.of(4.5F, -5.75F, -3.25F, -0.1797F, 0.6784F, -0.2817F));

        ModelPartData cube_r8 = fishL.addChild("cube_r8", ModelPartBuilder.create().uv(106, 30).cuboid(-0.5F, 2.25F, -2.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -1.7654F, 2.3478F, 1.1781F, 0.0F, 0.0F));

        ModelPartData cube_r9 = fishL.addChild("cube_r9", ModelPartBuilder.create().uv(100, 25).cuboid(-0.5F, 0.0F, -1.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -1.7654F, 1.8478F, 1.1781F, 0.0F, 0.0F));

        ModelPartData cube_r10 = fishL.addChild("cube_r10", ModelPartBuilder.create().uv(67, 108).cuboid(-0.5F, 0.0F, -2.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.001F)), ModelTransform.of(0.0F, -1.7654F, 1.8478F, 0.3927F, 0.0F, 0.0F));

        ModelPartData cube_r11 = fishL.addChild("cube_r11", ModelPartBuilder.create().uv(106, 25).cuboid(-0.5F, -4.25F, -2.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.001F)), ModelTransform.of(0.0F, 1.7654F, 2.3478F, -1.1781F, 0.0F, 0.0F));

        ModelPartData cube_r12 = fishL.addChild("cube_r12", ModelPartBuilder.create().uv(76, 66).cuboid(-0.5F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.001F)), ModelTransform.of(0.0F, 1.7654F, 1.8478F, -1.1781F, 0.0F, 0.0F));

        ModelPartData cube_r13 = fishL.addChild("cube_r13", ModelPartBuilder.create().uv(60, 108).cuboid(-0.5F, -1.0F, -2.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 1.7654F, 1.8478F, -0.3927F, 0.0F, 0.0F));

        ModelPartData cube_r14 = fishL.addChild("cube_r14", ModelPartBuilder.create().uv(105, 94).cuboid(-0.5F, -1.0F, -1.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.003F)), ModelTransform.of(0.0F, 0.0F, 1.5F, 0.7854F, 0.0F, 0.0F));

        ModelPartData arm_L = torso.addChild("arm_L", ModelPartBuilder.create(), ModelTransform.pivot(3.0F, -5.25F, 0.0F));

        ModelPartData pawL_r1 = arm_L.addChild("pawL_r1", ModelPartBuilder.create().uv(83, 109).mirrored().cuboid(-1.0F, 4.3F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)).mirrored(false)
                .uv(103, 101).mirrored().cuboid(-1.0F, 4.5F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.001F)).mirrored(false), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.48F));

        ModelPartData handL_r1 = arm_L.addChild("handL_r1", ModelPartBuilder.create().uv(68, 103).cuboid(-1.0F, 4.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.2F))
                .uv(87, 56).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -3.1416F, 0.0F, 2.6616F));

        ModelPartData arm_R = torso.addChild("arm_R", ModelPartBuilder.create(), ModelTransform.pivot(-3.0F, -5.0F, 0.5F));

        ModelPartData pawR_r1 = arm_R.addChild("pawR_r1", ModelPartBuilder.create().uv(83, 109).cuboid(-1.0F, 4.3F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(103, 101).cuboid(-1.0F, 4.5F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.001F))
                .uv(77, 103).cuboid(-1.0F, 4.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.2F))
                .uv(33, 87).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -0.25F, -0.5F, 0.0F, 0.0F, 0.48F));

        ModelPartData leg_L = torso.addChild("leg_L", ModelPartBuilder.create().uv(32, 41).cuboid(-1.5F, 0.0F, -1.5F, 3.0F, 8.0F, 3.0F, new Dilation(0.0F))
                .uv(26, 59).cuboid(-1.5F, 6.0F, -2.25F, 3.0F, 2.0F, 4.0F, new Dilation(0.2F)), ModelTransform.pivot(2.0F, 0.0F, 0.0F));

        ModelPartData leg_R = torso.addChild("leg_R", ModelPartBuilder.create().uv(19, 41).cuboid(-1.5F, 0.0F, -1.5F, 3.0F, 8.0F, 3.0F, new Dilation(0.0F))
                .uv(60, 58).cuboid(-1.5F, 6.0F, -2.25F, 3.0F, 2.0F, 4.0F, new Dilation(0.2F)), ModelTransform.pivot(-2.0F, 0.0F, 0.0F));

        ModelPartData bell = torso.addChild("bell", ModelPartBuilder.create().uv(59, 97).cuboid(-1.5F, 0.25F, -1.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(42, 104).cuboid(-0.5F, 0.0F, -1.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(2, 90).cuboid(-1.0F, 0.25F, -0.5F, 2.0F, 2.0F, 1.0F, new Dilation(-0.001F))
                .uv(51, 104).cuboid(-1.25F, 1.5F, -1.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(60, 104).cuboid(0.25F, 1.5F, -1.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(68, 97).cuboid(0.5F, 0.25F, -1.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -6.25F, -3.0F));

        ModelPartData tail1 = torso.addChild("tail1", ModelPartBuilder.create().uv(63, 20).cuboid(-1.0F, -1.0F, -0.25F, 2.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -0.75F, 1.75F, -1.0374F, 0.3133F, -0.481F));

        ModelPartData tail2 = tail1.addChild("tail2", ModelPartBuilder.create().uv(63, 27).cuboid(-1.0F, -1.0F, -0.25F, 2.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 3.75F, 0.0436F, 0.0873F, 0.0F));

        ModelPartData tail3 = tail2.addChild("tail3", ModelPartBuilder.create().uv(13, 64).cuboid(-1.0F, -1.0F, -0.25F, 2.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 3.75F, 0.0873F, 0.1745F, 0.0F));

        ModelPartData tail4 = tail3.addChild("tail4", ModelPartBuilder.create().uv(41, 64).cuboid(-1.0F, -1.0F, -0.25F, 2.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 3.75F, 0.1309F, 0.2618F, 0.0F));

        ModelPartData tail5 = tail4.addChild("tail5", ModelPartBuilder.create().uv(0, 65).cuboid(-1.0F, -1.0F, -0.25F, 2.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 3.75F, 0.1745F, 0.2618F, 0.0F));

        ModelPartData skirt = torso.addChild("skirt", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 1.0F, 0.0F));

        ModelPartData top = skirt.addChild("top", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -2.5F, 0.0F));

        ModelPartData skirt_top_r1 = top.addChild("skirt_top_r1", ModelPartBuilder.create().uv(107, 9).cuboid(-1.0F, 0.7975F, 0.4725F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.5F, 0.0F, -1.9635F, -0.6545F, -3.1416F));

        ModelPartData skirt_top_r2 = top.addChild("skirt_top_r2", ModelPartBuilder.create().uv(107, 13).cuboid(-1.0F, 0.7975F, 0.4725F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.5F, 0.0F, -1.9635F, 0.0F, -3.1416F));

        ModelPartData skirt_top_r3 = top.addChild("skirt_top_r3", ModelPartBuilder.create().uv(93, 107).cuboid(-1.0F, 0.7975F, 0.4725F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.5F, 0.0F, -1.9635F, 0.5672F, 3.1416F));

        ModelPartData skirt_top_r4 = top.addChild("skirt_top_r4", ModelPartBuilder.create().uv(7, 108).cuboid(-1.0F, 0.7975F, 0.4725F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -1.9635F, -0.6545F, -3.1416F));

        ModelPartData skirt_top_r5 = top.addChild("skirt_top_r5", ModelPartBuilder.create().uv(32, 108).cuboid(-1.0F, 0.7975F, 0.4725F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -1.9635F, 0.5672F, 3.1416F));

        ModelPartData skirt_top_r6 = top.addChild("skirt_top_r6", ModelPartBuilder.create().uv(39, 108).cuboid(-1.0F, 0.7975F, 0.4725F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -1.9635F, 0.0F, -3.1416F));

        ModelPartData skirt_top_r7 = top.addChild("skirt_top_r7", ModelPartBuilder.create().uv(103, 106).cuboid(-1.0F, 0.7975F, 0.4725F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.5F, 0.0F, 1.1781F, 0.6545F, 0.0F));

        ModelPartData skirt_top_r8 = top.addChild("skirt_top_r8", ModelPartBuilder.create().uv(0, 107).cuboid(-1.0F, 0.7975F, 0.4725F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.5F, 0.0F, 1.1781F, 0.0F, 0.0F));

        ModelPartData skirt_top_r9 = top.addChild("skirt_top_r9", ModelPartBuilder.create().uv(107, 5).cuboid(-1.0F, 0.7975F, 0.4725F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.5F, 0.0F, 1.1781F, -0.5672F, 0.0F));

        ModelPartData skirt_top_r10 = top.addChild("skirt_top_r10", ModelPartBuilder.create().uv(106, 43).cuboid(-1.0F, 0.7975F, 0.4725F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 1.1781F, 0.6545F, 0.0F));

        ModelPartData skirt_top_r11 = top.addChild("skirt_top_r11", ModelPartBuilder.create().uv(106, 47).cuboid(-1.0F, 0.7975F, 0.4725F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 1.1781F, -0.5672F, 0.0F));

        ModelPartData skirt_top_r12 = top.addChild("skirt_top_r12", ModelPartBuilder.create().uv(106, 39).cuboid(-1.0F, 0.7975F, 0.4725F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 1.1781F, 0.0F, 0.0F));

        ModelPartData bottom = skirt.addChild("bottom", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData skirt_inside_r1 = bottom.addChild("skirt_inside_r1", ModelPartBuilder.create().uv(100, 19).cuboid(-1.5F, 0.9926F, 2.9926F, 3.0F, 4.0F, 1.0F, new Dilation(-0.001F))
                .uv(9, 95).cuboid(-1.5F, 0.2426F, 3.2426F, 3.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.7854F, -1.1781F, 0.0F));

        ModelPartData skirt_inside_r2 = bottom.addChild("skirt_inside_r2", ModelPartBuilder.create().uv(0, 101).cuboid(-1.5F, 0.9926F, 2.9926F, 3.0F, 4.0F, 1.0F, new Dilation(-0.001F))
                .uv(31, 96).cuboid(-1.5F, 0.2426F, 3.2426F, 3.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.7854F, -0.7854F, 0.0F));

        ModelPartData skirt_inside_r3 = bottom.addChild("skirt_inside_r3", ModelPartBuilder.create().uv(101, 63).cuboid(-1.5F, 0.9926F, 2.9926F, 3.0F, 4.0F, 1.0F, new Dilation(-0.001F))
                .uv(40, 96).cuboid(-1.5F, 0.2426F, 3.2426F, 3.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.7854F, -0.3927F, 0.0F));

        ModelPartData skirt_inside_r4 = bottom.addChild("skirt_inside_r4", ModelPartBuilder.create().uv(101, 69).cuboid(-1.5F, 0.9926F, 2.9926F, 3.0F, 4.0F, 1.0F, new Dilation(-0.001F))
                .uv(49, 96).cuboid(-1.5F, 0.2426F, 3.2426F, 3.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.0F));

        ModelPartData skirt_inside_r5 = bottom.addChild("skirt_inside_r5", ModelPartBuilder.create().uv(85, 97).cuboid(-1.5F, 0.9926F, 2.9926F, 3.0F, 4.0F, 1.0F, new Dilation(-0.001F))
                .uv(92, 65).cuboid(-1.5F, 0.2426F, 3.2426F, 3.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.7854F, 0.3927F, 0.0F));

        ModelPartData skirt_inside_r6 = bottom.addChild("skirt_inside_r6", ModelPartBuilder.create().uv(98, 7).cuboid(-1.5F, 0.9926F, 2.9926F, 3.0F, 4.0F, 1.0F, new Dilation(-0.001F))
                .uv(92, 72).cuboid(-1.5F, 0.2426F, 3.2426F, 3.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.7854F, 0.7854F, 0.0F));

        ModelPartData skirt_inside_r7 = bottom.addChild("skirt_inside_r7", ModelPartBuilder.create().uv(98, 13).cuboid(-1.5F, 0.9926F, 2.9926F, 3.0F, 4.0F, 1.0F, new Dilation(-0.001F))
                .uv(0, 94).cuboid(-1.5F, 0.2426F, 3.2426F, 3.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.7854F, 1.1781F, 0.0F));

        ModelPartData skirt_inside_r8 = bottom.addChild("skirt_inside_r8", ModelPartBuilder.create().uv(99, 79).cuboid(-1.5F, 0.9926F, 2.9926F, 3.0F, 4.0F, 1.0F, new Dilation(-0.001F))
                .uv(95, 0).cuboid(-1.5F, 0.2426F, 3.2426F, 3.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, -0.7854F));

        ModelPartData skirt_inside_r9 = bottom.addChild("skirt_inside_r9", ModelPartBuilder.create().uv(97, 35).cuboid(-1.5F, 0.9926F, 2.9926F, 3.0F, 4.0F, 1.0F, new Dilation(-0.001F))
                .uv(22, 90).cuboid(-1.5F, 0.2426F, 3.2426F, 3.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -2.3562F, 1.1781F, -3.1416F));

        ModelPartData skirt_inside_r10 = bottom.addChild("skirt_inside_r10", ModelPartBuilder.create().uv(97, 41).cuboid(-1.5F, 0.9926F, 2.9926F, 3.0F, 4.0F, 1.0F, new Dilation(-0.001F))
                .uv(78, 90).cuboid(-1.5F, 0.2426F, 3.2426F, 3.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -2.3562F, 0.7854F, 3.1416F));

        ModelPartData skirt_inside_r11 = bottom.addChild("skirt_inside_r11", ModelPartBuilder.create().uv(97, 47).cuboid(-1.5F, 0.9926F, 2.9926F, 3.0F, 4.0F, 1.0F, new Dilation(-0.001F))
                .uv(90, 82).cuboid(-1.5F, 0.2426F, 3.2426F, 3.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -2.3562F, 0.3927F, 3.1416F));

        ModelPartData skirt_inside_r12 = bottom.addChild("skirt_inside_r12", ModelPartBuilder.create().uv(76, 97).cuboid(-1.5F, 0.9926F, 2.9926F, 3.0F, 4.0F, 1.0F, new Dilation(-0.001F))
                .uv(87, 90).cuboid(-1.5F, 0.2426F, 3.2426F, 3.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -2.3562F, 0.0F, 3.1416F));

        ModelPartData skirt_inside_r13 = bottom.addChild("skirt_inside_r13", ModelPartBuilder.create().uv(97, 29).cuboid(-1.5F, 0.9926F, 2.9926F, 3.0F, 4.0F, 1.0F, new Dilation(-0.001F))
                .uv(69, 89).cuboid(-1.5F, 0.2426F, 3.2426F, 3.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -2.3562F, -0.3927F, 3.1416F));

        ModelPartData skirt_inside_r14 = bottom.addChild("skirt_inside_r14", ModelPartBuilder.create().uv(18, 97).cuboid(-1.5F, 0.9926F, 2.9926F, 3.0F, 4.0F, 1.0F, new Dilation(-0.001F))
                .uv(60, 89).cuboid(-1.5F, 0.2426F, 3.2426F, 3.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -2.3562F, -0.7854F, 3.1416F));

        ModelPartData skirt_inside_r15 = bottom.addChild("skirt_inside_r15", ModelPartBuilder.create().uv(96, 95).cuboid(-1.5F, 0.9926F, 2.9926F, 3.0F, 4.0F, 1.0F, new Dilation(-0.001F))
                .uv(51, 89).cuboid(-1.5F, 0.2426F, 3.2426F, 3.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -2.3562F, -1.1781F, 3.1416F));

        ModelPartData skirt_inside_r16 = bottom.addChild("skirt_inside_r16", ModelPartBuilder.create().uv(96, 89).cuboid(-1.5F, 0.9926F, 2.9926F, 3.0F, 4.0F, 1.0F, new Dilation(-0.001F))
                .uv(42, 89).cuboid(-1.5F, 0.2426F, 3.2426F, 3.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.7854F));

        ModelPartData panty = torso.addChild("panty", ModelPartBuilder.create().uv(43, 58).cuboid(-3.0F, -3.25F, -3.0F, 4.0F, 1.0F, 4.0F, new Dilation(0.001F))
                .uv(58, 47).cuboid(1.0F, -3.25F, -3.0F, 4.0F, 1.0F, 4.0F, new Dilation(0.001F))
                .uv(37, 13).cuboid(0.0F, -2.75F, -3.0F, 2.0F, 1.0F, 4.0F, new Dilation(0.001F)), ModelTransform.pivot(-1.0F, 2.75F, 1.0F));

        ModelPartData pantyribbonL_r1 = panty.addChild("pantyribbonL_r1", ModelPartBuilder.create().uv(42, 37).cuboid(0.0F, -0.5F, 0.0F, 0.0F, 1.0F, 0.0F, new Dilation(0.2F)), ModelTransform.of(1.25F, -2.75F, -3.0F, 0.0F, 0.0F, -1.9635F));

        ModelPartData pantyribbonR_r1 = panty.addChild("pantyribbonR_r1", ModelPartBuilder.create().uv(42, 35).cuboid(0.0F, -0.5F, 0.0F, 0.0F, 1.0F, 0.0F, new Dilation(0.2F)), ModelTransform.of(0.75F, -2.75F, -3.0F, 0.0F, 0.0F, -1.1781F));

        ModelPartData pantyline = panty.addChild("pantyline", ModelPartBuilder.create().uv(42, 39).cuboid(1.0F, -1.5F, -3.0F, 0.0F, 0.0F, 0.0F, new Dilation(0.05F))
                .uv(43, 35).cuboid(1.0F, -1.5F, -2.5F, 0.0F, 0.0F, 0.0F, new Dilation(0.05F))
                .uv(43, 36).cuboid(1.0F, -1.5F, -2.0F, 0.0F, 0.0F, 0.0F, new Dilation(0.05F))
                .uv(43, 39).cuboid(1.0F, -1.5F, -0.5F, 0.0F, 0.0F, 0.0F, new Dilation(0.05F))
                .uv(43, 38).cuboid(1.0F, -1.5F, -1.5F, 0.0F, 0.0F, 0.0F, new Dilation(0.05F))
                .uv(43, 37).cuboid(1.0F, -1.5F, -1.0F, 0.0F, 0.0F, 0.0F, new Dilation(0.05F))
                .uv(44, 36).cuboid(1.0F, -1.5F, 1.0F, 0.0F, 0.0F, 0.0F, new Dilation(0.05F))
                .uv(44, 37).cuboid(1.0F, -2.0F, 1.0F, 0.0F, 0.0F, 0.0F, new Dilation(0.05F))
                .uv(44, 38).cuboid(1.0F, -2.5F, 1.0F, 0.0F, 0.0F, 0.0F, new Dilation(0.05F))
                .uv(44, 35).cuboid(1.0F, -1.5F, 0.0F, 0.0F, 0.0F, 0.0F, new Dilation(0.05F))
                .uv(43, 40).cuboid(1.0F, -1.5F, 0.5F, 0.0F, 0.0F, 0.0F, new Dilation(0.05F))
                .uv(42, 40).cuboid(1.0F, -2.0F, -3.0F, 0.0F, 0.0F, 0.0F, new Dilation(0.05F)), ModelTransform.pivot(0.0F, -0.25F, 0.0F));
        return TexturedModelData.of(modelData, 128, 128);
    }
    @Override
    public void setAngles(CarolEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        this.setHeadAngles(netHeadYaw, headPitch);

        this.animateMovement(CarolAnimations.WALK, limbSwing, limbSwingAmount, 1f, 1f);
        this.updateAnimation(entity.idleAnimationState, CarolAnimations.IDLE, ageInTicks, 1f);
    }

    private void setHeadAngles(float headYaw, float headPitch) {
        headYaw = MathHelper.clamp(headYaw, -30.0F, 30.0F);
        headPitch = MathHelper.clamp(headPitch, -25.0F, 45.0F);

        this.head.yaw = headYaw * 0.017453292F;
        this.head.pitch = headPitch * 0.017453292F;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        root.render(matrices, vertexConsumer, light, overlay, color);
    }

    @Override
    public ModelPart getPart() {
        return root;
    }

    public ModelPart getRandomPart(Random random) {
        return (ModelPart)this.parts.get(random.nextInt(this.parts.size()));
    }
}
