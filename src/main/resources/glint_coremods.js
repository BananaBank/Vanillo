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
                'class': 'net.minecraft.world.gen.layer.BiomeLayer',
                'methodName': 'func_202726_a',
                'methodDesc': '(Lnet/minecraft/world/gen/INoiseRandom;I)I'
            },
            'transformer': function (method) {
                var insnList = method.instructions;
                var arrayLength = insnList.size();

                for (var i = 0; i < arrayLength; ++i) {
                    var insn = insnList.get(i);

                    if (insn.getOpcode() == Opcodes.ILOAD && insn.getPrevious().getOpcode() == Opcodes.IFNE) {
                        var a = insnList.get(i + 1);

                        // add patch while original code is still in list
                        insnList.insertBefore(a, new MethodInsnNode(Opcodes.INVOKESTATIC, "thedarkcolour/hardcoredungeons/asm/ASMHooks", "isMushroomBiome", "(I)Z", false));

                        // remove first node
                        insnList.remove(a);

                        // modify the other node since getting correct label is difficult
                        insnList.get(i + 2).opcode = Opcodes.IFNE;

                        return method;
                    }
                }
                return method;
            }
        }
    }
}
