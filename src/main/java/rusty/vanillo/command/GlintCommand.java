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

public class GlintCommand {
    // Copy here to avoid loading client-side code
    private static final String[] COLOR_NAMES = new String[] { "white", "orange", "magenta", "light_blue", "yellow", "lime", "pink", "gray", "light_gray", "cyan", "purple", "blue", "brown", "green", "red", "black", "rainbow" };
    private static final Object2ByteMap<String> COLORS = Util.make(new Object2ByteOpenHashMap(17), map -> {
        for (byte i = 0; i < COLOR_NAMES.length; ++i) {
            map.put(COLOR_NAMES[i], i);
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