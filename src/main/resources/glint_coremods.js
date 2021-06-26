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

function initializeCoreMod() {
    return {
        'ItemRendererPatch': {
            'target': {
                'type': 'METHOD',
                'class': 'net.minecraft.client.renderer.ItemRenderer',
                'methodName': 'func_229111_a_', // render
                'methodDesc': '(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/renderer/model/ItemCameraTransforms$TransformType;ZLcom/mojang/blaze3d/matrix/MatrixStack;Lnet/minecraft/client/renderer/IRenderTypeBuffer;IILnet/minecraft/client/renderer/model/IBakedModel;)V'
            },
            'transformer': function (method) {
                var insnList = method.instructions; // method instructions
                var toApply = []; // instruction list modifications

                var getCompassFoilBuffer = ASMAPI.mapMethod('func_241731_a_');
                var getCompassFoilBufferDirect = ASMAPI.mapMethod('func_241732_b_');
                var getFoilBuffer = ASMAPI.mapMethod('func_229113_a_');
                var getFoilBufferDirect = ASMAPI.mapMethod('func_239391_c_');

                onInvokeStatic(insnList, function(insn) {
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
                    ASMAPI.log('DEBUG', 'ItemRenderer Patch ' + i + ' successful');
                    toApply[i]();
                }

                return method;
            }
        },
        'ItemRendererPatch2': {
            'target': {
                'type': 'METHOD',
                'class': 'net.minecraft.client.renderer.ItemRenderer',
                'methodName': 'func_239386_a_', // getArmorFoilBuffer
                'methodDesc': '(Lnet/minecraft/client/renderer/IRenderTypeBuffer;Lnet/minecraft/client/renderer/RenderType;ZZ)Lcom/mojang/blaze3d/vertex/IVertexBuilder;'
            },
            'transformer': function(method) {
                method.instructions = ASMAPI.listOf();

                var l0 = new Label();
                mv.visitLabel(l0);
                mv.visitLineNumber(22, l0);
                mv.visitVarInsn(ILOAD, 3);
                var l1 = new Label();
                mv.visitJumpInsn(IFEQ, l1);
                mv.visitVarInsn(ALOAD, 0);
                mv.visitFieldInsn(GETSTATIC, "rusty/vanillo/client/glint/GlintHooks", "ARMOR_CONTEXT", "Ljava/lang/ThreadLocal;");
                mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/ThreadLocal", "get", "()Ljava/lang/Object;", false);
                mv.visitTypeInsn(CHECKCAST, "net/minecraft/item/ItemStack");
                mv.visitVarInsn(ILOAD, 2);
                mv.visitMethodInsn(INVOKESTATIC, "rusty/vanillo/client/glint/ColoredGlints", "getArmorGlint", "(Lnet/minecraft/item/ItemStack;Z)Lnet/minecraft/client/renderer/RenderType;", false);
                mv.visitMethodInsn(INVOKEINTERFACE, "net/minecraft/client/renderer/IRenderTypeBuffer", "getBuffer", "(Lnet/minecraft/client/renderer/RenderType;)Lcom/mojang/blaze3d/vertex/IVertexBuilder;", true);
                mv.visitVarInsn(ALOAD, 0);
                mv.visitVarInsn(ALOAD, 1);
                mv.visitMethodInsn(INVOKEINTERFACE, "net/minecraft/client/renderer/IRenderTypeBuffer", "getBuffer", "(Lnet/minecraft/client/renderer/RenderType;)Lcom/mojang/blaze3d/vertex/IVertexBuilder;", true);
                mv.visitMethodInsn(INVOKESTATIC, "com/mojang/blaze3d/vertex/VertexBuilderUtils", "create", "(Lcom/mojang/blaze3d/vertex/IVertexBuilder;Lcom/mojang/blaze3d/vertex/IVertexBuilder;)Lcom/mojang/blaze3d/vertex/IVertexBuilder;", false);
                var l2 = new Label();
                mv.visitJumpInsn(GOTO, l2);
                mv.visitLabel(l1);
                mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
                mv.visitVarInsn(ALOAD, 0);
                mv.visitVarInsn(ALOAD, 1);
                mv.visitMethodInsn(INVOKEINTERFACE, "net/minecraft/client/renderer/IRenderTypeBuffer", "getBuffer", "(Lnet/minecraft/client/renderer/RenderType;)Lcom/mojang/blaze3d/vertex/IVertexBuilder;", true);
                mv.visitLabel(l2);
                mv.visitFrame(Opcodes.F_SAME1, 0, null, 1, new Object[]{"com/mojang/blaze3d/vertex/IVertexBuilder"});
                mv.visitInsn(ARETURN);
                var l3 = new Label();
                mv.visitLabel(l3);
                mv.visitLocalVariable("buffer", "Lnet/minecraft/client/renderer/IRenderTypeBuffer;", null, l0, l3, 0);
                mv.visitLocalVariable("layer", "Lnet/minecraft/client/renderer/RenderType;", null, l0, l3, 1);
                mv.visitLocalVariable("regularType", "Z", null, l0, l3, 2);
                mv.visitLocalVariable("p_239386_3_", "Z", null, l0, l3, 3);
                mv.visitMaxs(3, 4);

                return method;
            }
        },
        'WorldRendererPatch': {
            'target': {
                'type': 'METHOD',
                'class': 'net.minecraft.client.renderer.WorldRenderer',
                'methodName': 'func_228426_a_', // renderLevel
                'methodDesc': '(Lcom/mojang/blaze3d/matrix/MatrixStack;FJZLnet/minecraft/client/renderer/ActiveRenderInfo;Lnet/minecraft/client/renderer/GameRenderer;Lnet/minecraft/client/renderer/LightTexture;Lnet/minecraft/util/math/vector/Matrix4f;)V'
            },
            'transformer': function(method) {
                var insnList = method.instructions; // method instructions
                var toApply = [];
                var name = ASMAPI.mapMethod('func_239270_k_'); // armorGlint

                onInvokeStatic(insnList, function(insn) {
                    if (insn.name.equals(name)) {
                        // insert patch before glint
                        toApply.push(function() {
                            insnList.insertBefore(insn.getPrevious(), ASMAPI.listOf(
                                new VarInsnNode(Opcodes.ALOAD, insn.getPrevious().var), // clone because some mods screw up the local variables
                                new MethodInsnNode(Opcodes.INVOKESTATIC, "rusty/vanillo/client/glint/ColoredGlints", "endBatch", "(Lnet/minecraft/client/renderer/IRenderTypeBuffer$Impl;)V", false)
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
                'class': 'net.minecraft.client.renderer.entity.layers.ElytraLayer',
                'methodName': 'func_225628_a_',
                'methodDesc': '(Lcom/mojang/blaze3d/matrix/MatrixStack;Lnet/minecraft/client/renderer/IRenderTypeBuffer;ILnet/minecraft/entity/LivingEntity;FFFFFF)V'
            },
            'transformer': function (method) {
                var insnList = method.instructions; // method instructions

                onInvokeStatic(insnList, function(insn) {
                    // If we are at the correct method
                    if (insn.desc.equals('(Lnet/minecraft/client/renderer/IRenderTypeBuffer;Lnet/minecraft/client/renderer/RenderType;ZZ)Lcom/mojang/blaze3d/vertex/IVertexBuilder;')) {
                        // Load extra ItemStack variable for glint context
                        insnList.insertBefore(insn.getPrevious().getPrevious().getPrevious().getPrevious().getPrevious().getPrevious(), new VarInsnNode(Opcodes.ALOAD, 11));

                        // Change method node
                        insn.desc = '(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/renderer/IRenderTypeBuffer;Lnet/minecraft/client/renderer/RenderType;ZZ)Lcom/mojang/blaze3d/vertex/IVertexBuilder;';
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
                'type': 'CLASS',
                'name': 'net.minecraft.client.renderer.entity.layers.BipedArmorLayer'
            },
            'transformer': function(klass) {
                for (var j = 0; j < klass.methods.size(); ++j) {
                    var method = klass.methods.get(j);
                    var insnList = method.instructions; // method instructions

                    if (method.name.equals(ASMAPI.mapMethod('func_241739_a_'))) { // renderArmorPiece
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

                return klass;
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
    invokestatic.desc = "(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/renderer/IRenderTypeBuffer;Lnet/minecraft/client/renderer/RenderType;Lcom/mojang/blaze3d/matrix/MatrixStack$Entry;)Lcom/mojang/blaze3d/vertex/IVertexBuilder;";

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
    invokestatic.desc = "(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/renderer/IRenderTypeBuffer;Lnet/minecraft/client/renderer/RenderType;ZZ)Lcom/mojang/blaze3d/vertex/IVertexBuilder;";

    return function() {
        list.insertBefore(aload5, aload1);
    };
}
