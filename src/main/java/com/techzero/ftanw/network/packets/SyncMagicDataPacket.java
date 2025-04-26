package com.techzero.ftanw.network.packets;

import java.util.function.Supplier;

import com.techzero.ftanw.capabilities.magic.MagicData;
import com.techzero.ftanw.client.overlay.ManaOverlay;
import com.techzero.ftanw.network.NetworkHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

public class SyncMagicDataPacket {
	final CompoundNBT magicNBT;
	
	public SyncMagicDataPacket(CompoundNBT magicData) {
		this.magicNBT = magicData;
	}
	
	public static void sendToClient(ServerPlayerEntity player, CompoundNBT magicNBT) {
		NetworkHandler.INSTANCE.sendTo(new SyncMagicDataPacket(magicNBT), player.connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
	}
	
	public static void encode(SyncMagicDataPacket packet, PacketBuffer buffer) {
		buffer.writeNbt(packet.magicNBT);
	}
	
	public static SyncMagicDataPacket decode(PacketBuffer buffer) {
		return new SyncMagicDataPacket(buffer.readNbt());
	}
	
	public void handle(Supplier<NetworkEvent.Context> ctx) {
		ctx.get().enqueueWork(() -> {
			ClientPlayerEntity player = Minecraft.getInstance().player;
			if (player != null) {
				player.getCapability(MagicData.MAGIC).ifPresent(magic -> {
					magic.readNBT(magicNBT);
					
					ManaOverlay.updateManaBar(magic.currentMana(), magic.maxMana());
					System.out.println("Received Mana: " + magic.currentMana() + "/" + magic.maxMana());
					
				});
			}
		});
		ctx.get().setPacketHandled(true);
	}

}
