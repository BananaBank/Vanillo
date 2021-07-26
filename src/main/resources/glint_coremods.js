// Written by TheDarkColour

// Type definitions
var Opcodes = Java.type('org.objectweb.asm.Opcodes');
var Label = Java.type('org.objectweb.asm.Label');
var InsnList = Java.type('org.objectweb.asm.tree.InsnList');
var VarInsnNode = Java.type('org.objectweb.asm.tree.VarInsnNode');
var MethodInsnNode = Java.type('org.objectweb.asm.tree.MethodInsnNode');
var LdcInsnNode = Java.type('org.objectweb.asm.tree.LdcInsnNode');
var LabelNode = Java.type('org.objectweb.asm.tree.LabelNode');
var TypeInsnNode = Java.type('org.objectweb.asm.tree.TypeInsnNode');
var FieldInsnNode = Java.type('org.objectweb.asm.tree.FieldInsnNode');

var ASMAPI = Java.type('net.minecraftforge.coremod.api.ASMAPI');

// Type aliases
var ItemRenderer_class = 'net.minecraft.client.renderer.entity.ItemRenderer';
  var ItemRenderer_render = 'm_115143_';
  var ItemRenderer_render_desc = '(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/client/renderer/block/model/ItemTransforms$TransformType;ZLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;IILnet/minecraft/client/resources/model/BakedModel;)V';

  var ItemRenderer_getArmorFoilBuffer = 'm_115184_'
  var ItemRenderer_getArmorFoilBuffer_desc = '(Lnet/minecraft/client/renderer/MultiBufferSource;Lnet/minecraft/client/renderer/RenderType;ZZ)Lcom/mojang/blaze3d/vertex/VertexConsumer;'

  var ItemRenderer_getCompassFoilBuffer = 'm_115180_'
  var ItemRenderer_getCompassFoilBufferDirect = 'm_115207_'
  var ItemRenderer_getFoilBuffer = 'm_115211_'
  var ItemRenderer_getFoilBufferDirect = 'm_115222_'

var ItemStack_class = 'net/minecraft/world/item/ItemStack'

var ElytraLayer_class = 'net.minecraft.client.renderer.entity.layers.ElytraLayer'
  var ElytraLayer_render = 'm_6494_'
  var ElytraLayer_render_desc = '(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/entity/LivingEntity;FFFFFF)V'

var LevelRenderer_class = 'net.minecraft.client.renderer.LevelRenderer'
  var LevelRenderer_render = 'm_109599_'
  var LevelRenderer_render_desc = '(Lcom/mojang/blaze3d/vertex/PoseStack;FJZLnet/minecraft/client/Camera;Lnet/minecraft/client/renderer/GameRenderer;Lnet/minecraft/client/renderer/LightTexture;Lcom/mojang/math/Matrix4f;)V'

// RenderType_class
  var RenderType_armorGlint = 'm_110481_'

var HumanoidArmorLayer_class = 'net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer'
  var HumanoidArmorLayer_renderArmorPiece = 'm_117118_'
  var HumanoidArmorLayer_renderArmorPiece_desc = '(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/entity/EquipmentSlot;ILnet/minecraft/client/model/HumanoidModel;)V'

function initializeCoreMod() {
    return {
        'ItemRendererPatch': {
            'target': {
                'type': 'METHOD',
                'class': ItemRenderer_class,
                'methodName': ItemRenderer_render, // render
                'methodDesc': ItemRenderer_render_desc
            },
            'transformer': function (method) {
                var insnList = method.instructions; // method instructions
                var toApply = []; // instruction list modifications

                var getCompassFoilBuffer = ASMAPI.mapMethod(ItemRenderer_getCompassFoilBuffer); // getCompassFoilBuffer
                var getCompassFoilBufferDirect = ASMAPI.mapMethod(ItemRenderer_getCompassFoilBufferDirect); // getCompassFoilBufferDirect
                var getFoilBuffer = ASMAPI.mapMethod(ItemRenderer_getFoilBuffer); // getFoilBuffer
                var getFoilBufferDirect = ASMAPI.mapMethod(ItemRenderer_getFoilBufferDirect); // getFoilBufferDirect

                onInvokeStatic(insnList, function(insn) {
                    // We don't have to change these, these are our names
                    if (insn.name.equals(getCompassFoilBuffer)) {
                        toApply.push(patchCompassFoil(insnList, insn, 'getCompassFoilBufferDirect'));
                    } else if (insn.name.equals(getCompassFoilBufferDirect)) {
                        toApply.push(patchCompassFoil(insnList, insn, 'getCompassFoilBuffer'));
                    } else if (insn.name.equals(getFoilBuffer)) {
                        toApply.push(patchItemFoil(insnList, insn, 'getFoilBufferDirect'));
                    } else if (insn.name.equals(getFoilBufferDirect)) {
                        toApply.push(patchItemFoil(insnList, insn, 'getFoilBuffer'));
                    }
                });

                // Apply instruction list modifications after the list has been iterated through
                for (var i = 0; i < toApply.length; ++i) {
                    ASMAPI.log('DEBUG', 'ItemRenderer Patched ' + i + ' successful');
                    toApply[i]();
                }

                return method;
            }
        },
        'ItemRendererPatch2': {
            'target': {
                'type': 'METHOD',
                'class': ItemRenderer_class,
                'methodName': ItemRenderer_getArmorFoilBuffer, // getArmorFoilBuffer
                'methodDesc': ItemRenderer_getArmorFoilBuffer_desc
            },
            'transformer': function(method) {
                method.instructions = ASMAPI.listOf();

                var l0 = new Label();
                method.visitLabel(l0);
                method.visitLineNumber(22, l0);
                method.visitVarInsn(Opcodes.ILOAD, 3);
                var l1 = new Label();
                method.visitJumpInsn(Opcodes.IFEQ, l1);
                method.visitVarInsn(Opcodes.ALOAD, 0);
                method.visitFieldInsn(Opcodes.GETSTATIC, "rusty/vanillo/client/glint/GlintHooks", "ARMOR_CONTEXT", "Ljava/lang/ThreadLocal;");
                method.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/ThreadLocal", "get", "()Ljava/lang/Object;", false);
                method.visitTypeInsn(Opcodes.CHECKCAST, ItemStack_class);
                method.visitVarInsn(Opcodes.ILOAD, 2);
                method.visitMethodInsn(Opcodes.INVOKESTATIC, "rusty/vanillo/client/glint/ColoredGlints", "getArmorGlint", "(Lnet/minecraft/world/item/ItemStack;Z)Lnet/minecraft/client/renderer/RenderType;", false);
                method.visitMethodInsn(Opcodes.INVOKEINTERFACE, "net/minecraft/client/renderer/MultiBufferSource", "getBuffer", "(Lnet/minecraft/client/renderer/RenderType;)Lcom/mojang/blaze3d/vertex/VertexConsumer;", true);
                method.visitVarInsn(Opcodes.ALOAD, 0);
                method.visitVarInsn(Opcodes.ALOAD, 1);
                method.visitMethodInsn(Opcodes.INVOKEINTERFACE, "net/minecraft/client/renderer/MultiBufferSource", "getBuffer", "(Lnet/minecraft/client/renderer/RenderType;)Lcom/mojang/blaze3d/vertex/VertexConsumer;", true);
                method.visitMethodInsn(Opcodes.INVOKESTATIC, "com/mojang/blaze3d/vertex/VertexMultiConsumer", "create", "(Lcom/mojang/blaze3d/vertex/VertexConsumer;Lcom/mojang/blaze3d/vertex/VertexConsumer;)Lcom/mojang/blaze3d/vertex/VertexConsumer;", false);
                var l2 = new Label();
                method.visitJumpInsn(Opcodes.GOTO, l2);
                method.visitLabel(l1);
                method.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
                method.visitVarInsn(Opcodes.ALOAD, 0);
                method.visitVarInsn(Opcodes.ALOAD, 1);
                method.visitMethodInsn(Opcodes.INVOKEINTERFACE, "net/minecraft/client/renderer/MultiBufferSource", "getBuffer", "(Lnet/minecraft/client/renderer/RenderType;)Lcom/mojang/blaze3d/vertex/VertexConsumer;", true);
                method.visitLabel(l2);
                method.visitFrame(Opcodes.F_SAME1, 0, null, 1, ["com/mojang/blaze3d/vertex/VertexConsumer"]); //was new Object
                method.visitInsn(Opcodes.ARETURN);
                var l3 = new Label();
                method.visitLabel(l3);
                method.visitLocalVariable("buffer", "Lnet/minecraft/client/renderer/MultiBufferSource;", null, l0, l3, 0);
                method.visitLocalVariable("layer", "Lnet/minecraft/client/renderer/RenderType;", null, l0, l3, 1);
                method.visitLocalVariable("regularType", "Z", null, l0, l3, 2);
                method.visitLocalVariable("p_239386_3_", "Z", null, l0, l3, 3);
                method.visitMaxs(3, 4);

                return method;
            }
        },
        'WorldRendererPatch': {
            'target': {
                'type': 'METHOD',
                'class': LevelRenderer_class,
                'methodName': LevelRenderer_render, // renderLevel
                'methodDesc': LevelRenderer_render_desc
            },
            'transformer': function(method) {
                var insnList = method.instructions; // method instructions
                var toApply = [];
                var name = ASMAPI.mapMethod(RenderType_armorGlint); // armorGlint

                onInvokeStatic(insnList, function(insn) {
                    if (insn.name.equals(name)) {
                        // insert patch before glint
                        toApply.push(function() {
                            insnList.insertBefore(insn.getPrevious(), ASMAPI.listOf(
                                new VarInsnNode(Opcodes.ALOAD, insn.getPrevious().var), // clone because some mods screw up the local variables
                                new MethodInsnNode(Opcodes.INVOKESTATIC, "rusty/vanillo/client/glint/ColoredGlints", "endBatch", "(Lnet/minecraft/client/renderer/MultiBufferSource$BufferSource;)V", false)
                            ));
                        });
                    }
                });

                for (var i = 0; i < toApply.length; ++i) {
                    toApply[i]();
                }

                return method;
            }
        },
        'ElytraLayer': {
            'target': {
                'type': 'METHOD',
                'class': ElytraLayer_class,
                'methodName': ElytraLayer_render, // render
                'methodDesc': ElytraLayer_render_desc
            },
            'transformer': function (method) {
                var insnList = method.instructions; // method instructions

                onInvokeStatic(insnList, function(insn) {
                    // If we are at the correct method
                    if (insn.desc.equals('(Lnet/minecraft/client/renderer/MultiBufferSource;Lnet/minecraft/client/renderer/RenderType;ZZ)Lcom/mojang/blaze3d/vertex/VertexConsumer;')) {
                        // Load extra ItemStack variable for glint context
                        insnList.insertBefore(insn.getPrevious().getPrevious().getPrevious().getPrevious().getPrevious().getPrevious(), new VarInsnNode(Opcodes.ALOAD, 11));

                        // Change method node
                        insn.desc = '(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/client/renderer/MultiBufferSource;Lnet/minecraft/client/renderer/RenderType;ZZ)Lcom/mojang/blaze3d/vertex/VertexConsumer;';
                        insn.owner = 'rusty/vanillo/client/glint/GlintHooks';
                        insn.name = 'getElytraFoil';

                        ASMAPI.log('DEBUG', 'Patched ElytraLayer successfully');
                    }
                });

                return method;
            }
        },
        'BipedArmorLayer': {
            'target': {
                'type': 'METHOD',
                'class': HumanoidArmorLayer_class,
                'methodName': HumanoidArmorLayer_renderArmorPiece,
                'methodDesc': HumanoidArmorLayer_renderArmorPiece_desc
            },
            'transformer': function(method) {
                var insnList = method.instructions; // method instructions

                for (var i = 0; i < insnList.size(); ++i) {
                    var insn = insnList.get(i);

                    if (insn.getOpcode() == Opcodes.ASTORE) {
                        insnList.insertBefore(insn.getNext(), ASMAPI.listOf(
                            new FieldInsnNode(Opcodes.GETSTATIC, 'rusty/vanillo/client/glint/GlintHooks', 'ARMOR_CONTEXT', 'Ljava/lang/ThreadLocal;'),
                            new VarInsnNode(Opcodes.ALOAD, 7),
                            new MethodInsnNode(Opcodes.INVOKEVIRTUAL, 'java/lang/ThreadLocal', 'set', '(Ljava/lang/Object;)V', false)
                        ));

                        ASMAPI.log('DEBUG', 'Patched BipedLayer at renderArmorPiece');
                        break;
                    }
                }
            }
        }
    }
}

// Applies the action to static method calls
function onInvokeStatic(list, action) {
    for (var i = 0; i < list.size(); ++i) {
        var insn = list.get(i);

        // If we are at static method
        if (insn.getOpcode() == Opcodes.INVOKESTATIC) {
            action(insn);
        }
    }
}

function patchCompassFoil(list, invokestatic, newName) {
    // load item stack into hook
    var aload5 = invokestatic.getPrevious().getPrevious().getPrevious(); // 3 back
    var aload1 = new VarInsnNode(Opcodes.ALOAD, 1);

    // redirect to hook
    invokestatic.owner = "rusty/vanillo/client/glint/GlintHooks";
    invokestatic.name = newName
    invokestatic.desc = "(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/client/renderer/MultiBufferSource;Lnet/minecraft/client/renderer/RenderType;Lcom/mojang/blaze3d/vertex/PoseStack$Pose;)Lcom/mojang/blaze3d/vertex/VertexConsumer;";

    return function() {
        list.insertBefore(aload5, aload1);
    };
}

function patchItemFoil(list, invokestatic, newName) {
    // load item stack into hook
    var aload5 = invokestatic.getPrevious().getPrevious().getPrevious().getPrevious().getPrevious(); // 5 back
    var aload1 = new VarInsnNode(Opcodes.ALOAD, 1);

    // redirect to hook
    invokestatic.owner = "rusty/vanillo/client/glint/GlintHooks";
    invokestatic.name = newName;
    invokestatic.desc = "(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/client/renderer/MultiBufferSource;Lnet/minecraft/client/renderer/RenderType;ZZ)Lcom/mojang/blaze3d/vertex/VertexConsumer;";

    return function() {
        list.insertBefore(aload5, aload1);
    };
}
