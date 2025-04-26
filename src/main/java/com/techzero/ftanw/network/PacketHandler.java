package com.techzero.ftanw.network;

import com.techzero.ftanw.FairyTailMod;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class PacketHandler {
	public static final SimpleChannel INSTANCE = NetworkRegistry
			.newSimpleChannel(new ResourceLocation(FairyTailMod.MOD_ID, "main"), () -> "1", "1"::equals, "1"::equals);
	
	public static void register() {
		
	}

}
