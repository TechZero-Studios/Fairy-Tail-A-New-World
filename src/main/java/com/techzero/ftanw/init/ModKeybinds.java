package com.techzero.ftanw.init;

import net.minecraft.client.settings.KeyBinding;

import org.lwjgl.glfw.GLFW;

import com.techzero.ftanw.FairyTailMod;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = FairyTailMod.MOD_ID, bus = Bus.FORGE, value = Dist.CLIENT)
public class ModKeybinds {
	public static final String CATEGORY = "key.categories.ftanw";
	public static final KeyBinding statsKey = new KeyBinding("key.stats", GLFW.GLFW_KEY_P, CATEGORY);
	public static final KeyBinding combatBarKey = new KeyBinding("key.combatBar", GLFW.GLFW_KEY_LEFT_ALT, CATEGORY);
	
	
	public static void register(final FMLClientSetupEvent event) {
		ClientRegistry.registerKeyBinding(statsKey);
		ClientRegistry.registerKeyBinding(combatBarKey);
	}

}
