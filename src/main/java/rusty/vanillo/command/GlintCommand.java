package rusty.vanillo.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import it.unimi.dsi.fastutil.objects.Object2ByteMap;
import it.unimi.dsi.fastutil.objects.Object2ByteOpenHashMap;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.Util;
import rusty.vanillo.client.glint.ColoredGlints;

public class GlintCommand {
    private static final Object2ByteMap<String> COLORS = Util.make(new Object2ByteOpenHashMap(17), map -> {
        for (byte i = 0; i < ColoredGlints.COLORS.length; ++i) {
            map.put(ColoredGlints.COLORS[i], i);
        }
    });

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("glint").requires(source -> {
            return source.hasPermission(2);
        }).then(Commands.argument("color", StringArgumentType.word()).executes(context -> {
            String args = context.getArgument("color", String.class);

            if (COLORS.containsKey(args)) {
                ServerPlayerEntity src = context.getSource().getPlayerOrException();
                ItemStack stack = src.getItemInHand(Hand.MAIN_HAND);

                stack.getOrCreateTag().putByte("Glint", COLORS.getByte(args));
            }

            return 0;
        })));
    }
}