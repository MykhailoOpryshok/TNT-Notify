package com.opryshok;

import com.opryshok.commands.TNTNotifyCommand;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TNTNotify implements ModInitializer {
	public static String MOD_ID = "tnt-notify";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		CommandRegistrationCallback.EVENT.register(TNTNotifyCommand::register);
	}
}