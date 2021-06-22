// Written by TheDarkColour
// Type definitions

var Opcodes = Java.type('org.objectweb.asm.Opcodes');
var InsnList = Java.type('org.objectweb.asm.tree.InsnList');
var VarInsnNode = Java.type('org.objectweb.asm.tree.VarInsnNode');
var MethodInsnNode = Java.type('org.objectweb.asm.tree.MethodInsnNode');
var LdcInsnNode = Java.type('org.objectweb.asm.tree.LdcInsnNode');
var TypeInsnNode = Java.type('org.objectweb.asm.tree.TypeInsnNode');

var ASMAPI = Java.type('net.minecraftforge.coremod.api.ASMAPI');

function initializeCoreMod() {
    return {
        'ItemRendererPatch': {
            'target': {
                'type': 'METHOD',
                'class': 'net.minecraft.client.renderer.ItemRenderer',
                'methodName': 'func_229111_a_',
                'methodDesc': '(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/renderer/model/ItemCameraTransforms$TransformType;ZLcom/mojang/blaze3d/matrix/MatrixStack;Lnet/minecraft/client/renderer/IRenderTypeBuffer;IILnet/minecraft/client/renderer/model/IBakedModel;)V'
            },
            'transformer': function (method) {
                var insnList = method.instructions; // method instructions
                var toApply = []

                // iterate through each instruction
                for (var i = 0; i < insnList.size(); ++i) {
                    var insn = insnList.get(i); // current instruction

                    // If we are at a static method
                    if (insn.getOpcode() == Opcodes.INVOKESTATIC) {
                        if (insn.name.equals("getCompassFoilBufferDirect")) {
                            toApply.push(patchCompassFoil(insnList, insn, "getCompassFoilBufferDirect"));
                        } else if (insn.name.equals("getCompassFoilBuffer")) {
                            toApply.push(patchCompassFoil(insnList, insn, "getCompassFoilBuffer"));
                        } else if (insn.name.equals("getFoilBufferDirect")) {
                            toApply.push(patchItemFoil(insnList, insn, "getFoilBufferDirect"));
                        } else if (insn.name.equals("getFoilBuffer")) {
                            toApply.push(patchItemFoil(insnList, insn, "getFoilBuffer"));
                        }
                    }
                }

                for (var i = 0; i < toApply.length; ++i) {
                    toApply[i]();
                }

                return method;
            }
        },
        'WorldRendererPatch': {
            'target': {
                'type': 'METHOD',
                'class': 'net.minecraft.client.renderer.WorldRenderer',
                'methodName': 'func_228426_a_',
                'methodDesc': '(Lcom/mojang/blaze3d/matrix/MatrixStack;FJZLnet/minecraft/client/renderer/ActiveRenderInfo;Lnet/minecraft/client/renderer/GameRenderer;Lnet/minecraft/client/renderer/LightTexture;Lnet/minecraft/util/math/vector/Matrix4f;)V'
            },
            'transformer': function (method) {
                var insnList = method.instructions; // method instructions

                // declare each render layer for removal
                var layers = [ 'armorGlint', 'armorEntityGlint', 'glint', 'glintDirect', 'glintTranslucent', 'entityGlint', 'entityGlintDirect' ]
                var toRemove = []
                var toApply = []

                // iterate through each instruction
                for (var i = 0; i < insnList.size(); ++i) {
                    var insn = insnList.get(i); // current instruction

                    // If we are at a static method
                    if (insn.getOpcode() == Opcodes.INVOKESTATIC) {
                        if (insn.name.equals('armorGlint')) {
                            // insert before glint
                            var replaceLocation = insn.getPrevious();

                            // insert patch
                            toApply.push(function() {
                                insnList.insertBefore(replaceLocation, ASMAPI.listOf(
                                    new VarInsnNode(Opcodes.ALOAD, 38),
                                    new MethodInsnNode(Opcodes.INVOKESTATIC, "rusty/vanillo/client/glint/ColoredGlints", "endBatch", "(Lnet/minecraft/client/renderer/IRenderTypeBuffer$Impl;)V", false)
                                ));
                            })
                        }
                    }
                }

                for (var i = 0; i < toApply.length; ++i) {
                    toApply[i]();
                }

                return method;
            }
        }
    }
}

function patchCompassFoil(list, invokestatic, newName) {
    // load item stack into hook
    var aload5 = invokestatic.getPrevious().getPrevious().getPrevious(); // 3 back
    var aload1 = new VarInsnNode(Opcodes.ALOAD, 1);

    // redirect to hook
    invokestatic.owner = "rusty/vanillo/client/glint/GlintHooks"
    invokestatic.name = newName
    invokestatic.desc = "(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/renderer/IRenderTypeBuffer;Lnet/minecraft/client/renderer/RenderType;Lcom/mojang/blaze3d/matrix/MatrixStack$Entry;)Lcom/mojang/blaze3d/vertex/IVertexBuilder;"

    return function() {
        list.insertBefore(aload5, aload1);
    }
}

function patchItemFoil(list, invokestatic, newName) {
    // load item stack into hook
    var aload5 = invokestatic.getPrevious().getPrevious().getPrevious().getPrevious().getPrevious(); // 5 back
    var aload1 = new VarInsnNode(Opcodes.ALOAD, 1);

    // redirect to hook
    invokestatic.owner = "rusty/vanillo/client/glint/GlintHooks"
    invokestatic.name = newName
    invokestatic.desc = "(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/renderer/IRenderTypeBuffer;Lnet/minecraft/client/renderer/RenderType;ZZ)Lcom/mojang/blaze3d/vertex/IVertexBuilder;"

    return function() {
        list.insertBefore(aload5, aload1);
    }
}
