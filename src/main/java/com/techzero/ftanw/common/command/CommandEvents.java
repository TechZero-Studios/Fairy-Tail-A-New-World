package com.techzero.ftanw.common.command;

import com.mojang.brigadier.CommandDispatcher;
import com.techzero.ftanw.FairyTailMod;

import net.minecraft.command.CommandSource;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = FairyTailMod.MOD_ID, bus = Bus.FORGE)
public class CommandEvents {
	
	@SubscribeEvent
	public static void onCommandRegister(RegisterCommandsEvent event) {
		CommandDispatcher<CommandSource> dispatcher = event.getDispatcher();
		SetMagicCommand.register(dispatcher);
		SetManaCommand.register(dispatcher);
		SetLevelCommand.register(dispatcher);
	}

}
