package com.techzero.ftanw.network.packets;

import java.util.function.Supplier;

import com.techzero.ftanw.capabilities.magic.IMagicData;
import com.techzero.ftanw.capabilities.magic.MagicData;
import com.techzero.ftanw.client.gui.StatsScreen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.fml.network.NetworkEvent;

public class SyncMagicDataPacketHandler {
	public static void handle(SyncMagicDataPacket packet, Supplier<NetworkEvent.Context> context) {
		ClientPlayerEntity player = Minecraft.getInstance().player;
		
		CompoundNBT magicNBT = packet.magicNBT;
		
		if (player != null) {
			IMagicData magicData = MagicData.getClientMagicData(player);
			magicData.readNBT(magicNBT);
			
			if (Minecraft.getInstance().screen instanceof StatsScreen) {
				((StatsScreen) Minecraft.getInstance().screen).updateMagicData();
			}
		}
		
		context.get().setPacketHandled(true);
	}

}
