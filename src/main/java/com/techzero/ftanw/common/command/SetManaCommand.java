package com.techzero.ftanw.common.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.techzero.ftanw.capabilities.magic.IMagicData;
import com.techzero.ftanw.capabilities.magic.MagicData;
import com.techzero.ftanw.network.packets.SyncMagicDataPacket;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.StringTextComponent;

public class SetManaCommand {

	public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(
            Commands.literal("setmana")
                .then(Commands.argument("amount", IntegerArgumentType.integer(0))
                    .executes(context -> {
                    	CommandSource source = context.getSource();
                    	ServerPlayerEntity player = source.getPlayerOrException();
                        int amount = IntegerArgumentType.getInteger(context, "amount");
                        
                        player.getCapability(MagicData.MAGIC).ifPresent(magicData -> {
                        	CompoundNBT magicNBT = magicData.writeNBT();
                        	System.out.println("[DEBUG] Sending magic data to client.");
                        	SyncMagicDataPacket.sendToClient(player, magicNBT);
                        });
                        return setMana(context.getSource(), amount);
                    }))
        );
    }

    private static int setMana(CommandSource source, int amount) {
        try {
            ServerPlayerEntity player = source.getPlayerOrException();
            IMagicData magicData = MagicData.getServerMagicData(player);
            if (magicData != null) {
                magicData.setMana(amount);
                player.sendMessage(new StringTextComponent("Mana set to: " + amount), player.getUUID());
                return 1;
            } else {
                player.sendMessage(new StringTextComponent("Magic data not found!"), player.getUUID());
                return 0;
            }
        } catch (CommandSyntaxException e) {
            source.sendFailure(new StringTextComponent("This command must be run by a player."));
            return 0;
        }
    }

}
 