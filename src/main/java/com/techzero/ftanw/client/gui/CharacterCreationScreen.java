package com.techzero.ftanw.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;

public class CharacterCreationScreen extends Screen {

	public CharacterCreationScreen() {
		super(new StringTextComponent("CharacterCreationScreen"));
	}
	
	public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(matrixStack);
		
		int padding = 20;
		int modelX = padding;
		int modelY = padding;
		float scale = 18.75f;
		@SuppressWarnings("unused")
		int headWidth = (int) (8 * scale);
		
		drawPlayerHead(matrixStack, modelX, modelY, scale);
		
		super.render(matrixStack, mouseX, mouseY, partialTicks);
	}
	
	public boolean isPauseScreen() {
		return true;
	}
	
	private void drawPlayerHead(MatrixStack matrixStack, int modelX, int modelY, float scale) {
		Minecraft minecraft = this.minecraft;
		AbstractClientPlayerEntity player = minecraft.player;
		
		if (player != null) {
	        ResourceLocation skinTexture = player.getSkinTextureLocation();

	        // Original face size
	        int faceWidth = 8;
	        int faceHeight = 8;

	        // Bind player skin texture
	        this.minecraft.getTextureManager().bind(skinTexture);

	        // Push Matrix (to apply scaling)
	        matrixStack.pushPose();

	        // Move the face render position
	        matrixStack.translate(modelX, modelY, 0);
	        
	        // Apply scaling
	        matrixStack.scale(scale, scale, scale);

	        // Draw the scaled face
	        StatsScreen.blit(matrixStack, 0, 0, 8, 8, faceWidth, faceHeight, 64, 64);

	        // Pop Matrix (restore previous state)
	        matrixStack.popPose();
	    }
	}

}
