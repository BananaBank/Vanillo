package team.bananabank.vanillo.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import it.unimi.dsi.fastutil.objects.Object2ByteMap;
import it.unimi.dsi.fastutil.objects.Object2ByteOpenHashMap;
import net.minecraft.Util;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;

public class GlintCommand {
    // Copy here to avoid loading client-side code
    private static final String[] COLOR_NAMES = new String[] { "white", "orange", "magenta", "light_blue", "yellow", "lime", "pink", "gray", "light_gray", "cyan", "purple", "blue", "brown", "green", "red", "black", "rainbow" };
    private static final Object2ByteMap<String> COLORS = Util.make(new Object2ByteOpenHashMap<>(17), map -> {
        for (byte i = 0; i < COLOR_NAMES.length; ++i) {
            map.put(COLOR_NAMES[i], i);
        }
    });

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("glint").requires(source -> {
            return source.hasPermission(2);
        }).then(Commands.argument("color", StringArgumentType.word()).executes(context -> {
            String args = context.getArgument("color", String.class);

            if (COLORS.containsKey(args)) {
                ServerPlayer src = context.getSource().getPlayerOrException();
                ItemStack stack = src.getItemInHand(InteractionHand.MAIN_HAND);

                stack.getOrCreateTag().putByte("Glint", COLORS.getByte(args));
            }

            return 0;
        })));
    }
}