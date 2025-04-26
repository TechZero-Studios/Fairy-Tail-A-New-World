package com.techzero.ftanw.network;

import com.techzero.ftanw.FairyTailMod;
import com.techzero.ftanw.network.packets.SyncMagicDataPacket;
import com.techzero.ftanw.network.packets.SyncPlayerDataPacket;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class NetworkHandler {
	private static final String PROTOCOL_VERSION = "1";
	public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
			new ResourceLocation(FairyTailMod.MOD_ID, "main"), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals,
			PROTOCOL_VERSION::equals);

	private static int packetId = 1;

	public static void register() {
		INSTANCE.messageBuilder(SyncMagicDataPacket.class, packetId, NetworkDirection.PLAY_TO_CLIENT)
		.decoder(SyncMagicDataPacket::decode)
		.encoder(SyncMagicDataPacket::encode)
		.consumer(SyncMagicDataPacket::handle)
		.add();
		
		INSTANCE.messageBuilder(SyncPlayerDataPacket.class, packetId, NetworkDirection.PLAY_TO_CLIENT)
		.decoder(SyncPlayerDataPacket::decode)
		.encoder(SyncPlayerDataPacket::encode)
		.consumer(SyncPlayerDataPacket::handle)
		.add();
		
	}

}
