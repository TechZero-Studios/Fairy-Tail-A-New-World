package com.techzero.ftanw.common.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.techzero.ftanw.capabilities.stats.PlayerData;
import com.techzero.ftanw.config.CommonConfig;
import com.techzero.ftanw.network.packets.SyncPlayerDataPacket;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.StringTextComponent;

public class SetLevelCommand {
	
	public static void register(CommandDispatcher<CommandSource> dispatcher) {
		dispatcher.register(Commands.literal("setlevel")
				.requires(source -> source.hasPermission(2))
						.then(Commands.argument("level", IntegerArgumentType.integer(1, CommonConfig.level_max.get()))
								.executes(context -> {
									CommandSource source = context.getSource();
									ServerPlayerEntity player = source.getPlayerOrException();
									int level = IntegerArgumentType.getInteger(context, "level");
									
									try {
										player.getCapability(PlayerData.STATS).ifPresent(stats -> {
											stats.setLevel(level);
											CompoundNBT statsNBT = stats.writeNBT();
											SyncPlayerDataPacket.sendToClient(player, statsNBT);
											source.sendSuccess(new StringTextComponent("Level set to " + level), true);
										});
										return Command.SINGLE_SUCCESS;
									} catch (IllegalArgumentException e) {
										source.sendFailure(new StringTextComponent("Couldn't set level."));
										return 0;
									}
								})));
	}

}
