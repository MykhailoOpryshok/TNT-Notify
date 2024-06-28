package com.opryshok.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class TNTNotifyCommand {
    public static boolean isNotify = true;
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        dispatcher.register(CommandManager.literal("TNTNotify").executes(TNTNotifyCommand::run));
    }

    private static int run(CommandContext<ServerCommandSource> serverCommandSourceCommandContext) {
        PlayerEntity player = serverCommandSourceCommandContext.getSource().getPlayer();
        if (player != null && player.hasPermissionLevel(2)) {
            isNotify = !isNotify;
            player.sendMessage(Text.literal("TNTNotify " + isNotify).formatted(Formatting.RED));
        }
        return 0;
    }
}
