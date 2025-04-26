package com.techzero.ftanw.client.overlay;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.techzero.ftanw.FairyTailMod;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = FairyTailMod.MOD_ID, bus = Bus.FORGE, value = Dist.CLIENT)
public class CombatBarOverlay {
	private static final ResourceLocation COMBAT_BAR_TEXTURE = new ResourceLocation("ftanw", "textures/gui/combat_bar.png");
	@SuppressWarnings("unused")
	private static final int SLOT_COUNT = 8;
	private static int selectedSlot = 0;
	
	public static void onRenderGameOverlay(RenderGameOverlayEvent.Post event) {
		if (event.getType() != RenderGameOverlayEvent.ElementType.ALL) return;
		Minecraft mc = Minecraft.getInstance();
		if (mc.options.hideGui) return;
		
		MatrixStack matrixStack = event.getMatrixStack();
		int screenWidth = mc.getWindow().getGuiScaledWidth();
		int screenHeight = mc.getWindow().getGuiScaledHeight();
		
		int barWidth = 182;
		int barHeight = 22;
		int x = (screenWidth - barWidth) / 2;
		int y = screenHeight - barHeight - 5;
		
		mc.getTextureManager().bind(COMBAT_BAR_TEXTURE);
		mc.gui.blit(matrixStack, x, y, 0, 0, barWidth, barHeight);
		
		int selectedX = x + selectedSlot * 20;
		mc.gui.blit(matrixStack, selectedX, y - 1, 0, 22, 24, 24);
	}

}
