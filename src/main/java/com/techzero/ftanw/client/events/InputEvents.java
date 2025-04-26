package com.techzero.ftanw.client.events;

import com.techzero.ftanw.FairyTailMod;
import com.techzero.ftanw.client.gui.StatsScreen;
import com.techzero.ftanw.init.ModKeybinds;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = FairyTailMod.MOD_ID, bus = Bus.FORGE, value = Dist.CLIENT)
public class InputEvents {
	
	@SubscribeEvent
	public static void onKeyPressed(TickEvent.ClientTickEvent event) {
		Minecraft mc = Minecraft.getInstance();
		if (mc.level == null) return;
		
		if (ModKeybinds.statsKey.consumeClick()) {  //key == ModKeybinds.statsKey.getKey().getValue() && action == GLFW.GLFW_PRESS) {
			if (mc.screen == null || mc.screen instanceof StatsScreen) {
				mc.setScreen(new StatsScreen());
			}
		}
		
		if (ModKeybinds.combatBarKey.consumeClick()) {  //key == ModKeybinds.combatBarKey.getKey().getValue() && action == GLFW.GLFW_PRESS) {
			if (mc.screen == null || mc.screen instanceof StatsScreen) {
				mc.setScreen(new StatsScreen());
			}
		}
	}

}
