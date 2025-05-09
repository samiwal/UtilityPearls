package net.whale.UtilityPearls.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;


public class ModCommands {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("utilitypearls")
                        .requires(source -> source.hasPermission(2))
                        .then(Commands.literal("speed")
                            .then(Commands.argument("speed", FloatArgumentType.floatArg(0.0F))
                                .executes(ModCommands::executespeed)))
                        .then(Commands.literal("lifetime")
                                .then(Commands.argument("lifetime", IntegerArgumentType.integer(0))
                                        .executes(ModCommands::executelifetime)))
        );
    }
    private static int executelifetime(CommandContext<CommandSourceStack> context) {
        int lifetime = IntegerArgumentType.getInteger(context,"lifetime");
        UtilityPearlData.get((context.getSource().getLevel())).setLifetime(lifetime);
        context.getSource().sendSuccess(
                () -> net.minecraft.network.chat.Component.literal(
                        "Changed lifetime of UtilityPearl in " + getDimension(context) + "to " + lifetime + " ticks"
                ),true);
        return 1;
    }

    private static int executespeed(CommandContext<CommandSourceStack> context) {
        float speed = FloatArgumentType.getFloat(context,"speed");
        UtilityPearlData.get((context.getSource().getLevel())).setSpeed(speed);
        if(speed > 4.2){
            context.getSource().sendSystemMessage(Component.literal("Warning: Speeds greater than 4.2 may cause visual and chunk loading glitches!"));
        }
        context.getSource().sendSuccess(
                () -> Component.literal(
                        "Changed speed of UtilityPearl in " + getDimension(context) + "to " + speed),
                true);
        return 1;
    }
    private static String getDimension(CommandContext<CommandSourceStack> context){
        String[] dimension1 = context.getSource().getLevel().dimension().location().toString().split(":");
        String[] dimension = dimension1[1].split("_");
        StringBuilder formattedName = new StringBuilder();
        for (String word : dimension) {
            formattedName.append(word.substring(0, 1).toUpperCase());
            formattedName.append(word.substring(1).toLowerCase());
            formattedName.append(" ");
        }
        return formattedName.toString();
    }
}
