package com.opryshok.mixin;

import com.opryshok.TNTNotify;
import com.opryshok.commands.TNTNotifyCommand;
import net.minecraft.block.TntBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TntBlock.class)
public class TntBlockMixin {
	@Inject(method = "primeTnt(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/entity/LivingEntity;)V", at = @At("HEAD"))
	private static void primeTnt(World world, BlockPos pos, LivingEntity igniter, CallbackInfo ci){
		if(igniter != null && !world.isClient){
			String message = String.format("Гравець %s підпалив динаміт [%d, %d, %d]",
					igniter.getName().getString(), pos.getX(), pos.getY(), pos.getZ());

			String command = String.format("/tp %d %d %d", pos.getX(), pos.getY(), pos.getZ());

			for(PlayerEntity admin : world.getPlayers()){
				if(admin.hasPermissionLevel(2) && TNTNotifyCommand.isNotify){
					admin.sendMessage(Text.literal(message).formatted(Formatting.RED).styled(style ->
							style.withClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, command))));
				}
			}
		}
	}

}