package com.techzero.ftanw.common.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.techzero.ftanw.api.enums.MagicType;
import com.techzero.ftanw.capabilities.magic.MagicData;
import com.techzero.ftanw.network.packets.SyncMagicDataPacket;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.StringTextComponent;

public class SetMagicCommand {

	public static void register(CommandDispatcher<CommandSource> dispatcher) {
		dispatcher.register(Commands.literal("setmagic")
				.requires(source -> source.hasPermission(2))
				.then(Commands.argument("magicType", StringArgumentType.word())
						.suggests((context, builder) -> {
							for (MagicType type : MagicType.values()) {
								builder.suggest(type.name().toLowerCase());
							}
							return builder.buildFuture();
						})
						.executes(context -> {
							CommandSource source = context.getSource();
							ServerPlayerEntity player = source.getPlayerOrException();
							String magicTypeStr = StringArgumentType.getString(context, "magicType").toUpperCase();
							
							System.out.println("Received magic type: " + magicTypeStr);
							
							try {
								MagicType magicType = MagicType.valueOf(magicTypeStr);
								
								player.getCapability(MagicData.MAGIC).ifPresent(magicData -> {
									magicData.setMagicPower(magicType);
										
									CompoundNBT magicNBT = magicData.writeNBT();
									System.out.println("[DEBUG] Sending magic data to client.");
									SyncMagicDataPacket.sendToClient(player, magicNBT);
										
									source.sendSuccess(new StringTextComponent("Set magic type to: " + magicType.name()), true);
								});
								return Command.SINGLE_SUCCESS;
								
							} catch (IllegalArgumentException e) {
								source.sendFailure(new StringTextComponent("Invalid magic type! Use one of: " + getMagicTypesList()));
								return 0;
							}
						})
						)
				);
			}
	
	private static String getMagicTypesList() {
		StringBuilder builder = new StringBuilder();
		for (MagicType type : MagicType.values()) {
			builder.append(type.name().toLowerCase()).append(", ");
		}
		return builder.substring(0, builder.length() - 2);
	}

}
