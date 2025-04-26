package com.techzero.ftanw.network.packets;

import java.util.function.Supplier;

import com.techzero.ftanw.capabilities.stats.PlayerData;
import com.techzero.ftanw.network.NetworkHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

public class SyncPlayerDataPacket {
	final CompoundNBT statsNBT;
	
	public SyncPlayerDataPacket(CompoundNBT playerData) {
		this.statsNBT = playerData;
	}
	
	public static void sendToClient(ServerPlayerEntity player, CompoundNBT statsNBT) {
		NetworkHandler.INSTANCE.sendTo(new SyncPlayerDataPacket(statsNBT), player.connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
	}
	
	public static void encode(SyncPlayerDataPacket packet, PacketBuffer buffer) {
		buffer.writeNbt(packet.statsNBT);
	}
	
	public static SyncPlayerDataPacket decode(PacketBuffer buffer) {
		return new SyncPlayerDataPacket(buffer.readNbt());
	}
	
	public void handle(Supplier<NetworkEvent.Context> ctx) {
		ctx.get().enqueueWork(() -> {
			ClientPlayerEntity player = Minecraft.getInstance().player;
			if (player != null) {
				player.getCapability(PlayerData.STATS).ifPresent(stats -> {
					stats.readNBT(statsNBT);
				});
			}
		});
		ctx.get().setPacketHandled(true);
	}

}
