package com.techzero.ftanw.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.techzero.ftanw.capabilities.magic.MagicData;
import com.techzero.ftanw.capabilities.stats.PlayerData;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class StatsScreen extends Screen {
	public StatsScreen() {
		super(new StringTextComponent("StatsScreen"));
	}
	
	public void updateMagicData() {
		MagicData.getClientMagicData(Minecraft.getInstance().player);
	}
	
	@Override
	public void init() {
		int buttonWidth = 80;
		int buttonHeight = 20;
		int x = 10;
		int y = this.height - buttonHeight - 10;
		
		this.addButton(new Button(x, y, buttonWidth, buttonHeight, new StringTextComponent("Abilities"), (button) -> {
			this.minecraft.setScreen(new AbilitiesScreen());
		}));
	}
	
	@Override
	public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(matrixStack);
		
		int padding = 20;
		int modelX = padding;
		int modelY = padding;
		float scale = 18.75f;
		int headWidth = (int) (8 * scale);
		
		
		drawPlayerHead(matrixStack, modelX, modelY, scale);
		
		int statsX = modelX + headWidth + 150;
		int statsY = modelY + 10;
		
		
		this.minecraft.player.getCapability(PlayerData.STATS).ifPresent(playerData -> {
			int lineSpacing = 20;
			float textScale = 1.5f;
			
			matrixStack.pushPose();
			matrixStack.translate(statsX / textScale, statsY / textScale, 0);
			matrixStack.scale(textScale, textScale, textScale);
			
			drawString(matrixStack, this.font, new TranslationTextComponent("Jewels: " + playerData.getJewels()), 0, 0, 0xFFFFFF);
			drawString(matrixStack, this.font, new TranslationTextComponent("Level: " + playerData.getLevel()), 0, lineSpacing, 0xFFFFFF);
			drawString(matrixStack, this.font, new TranslationTextComponent("Faction: " + playerData.getFaction()), 0, (3 * lineSpacing), 0xFFFFFF);
			drawString(matrixStack, this.font, new TranslationTextComponent("Mage Rank: " + playerData.getRank()), 0, (4 * lineSpacing), 0xFFFFFF);
			
			matrixStack.popPose();
		});
		
		this.minecraft.player.getCapability(MagicData.MAGIC).ifPresent(magicData -> {
			int lineSpacing = 20;
			float textScale = 1.5f;
			
			matrixStack.pushPose();
			matrixStack.translate(statsX / textScale, statsY / textScale, 0);
			matrixStack.scale(textScale, textScale, textScale);
			
			drawString(matrixStack, this.font, new TranslationTextComponent("Magic Power: " + magicData.getMagicPower()), 0, (2 * lineSpacing), 0xFFFFFF);
			
			matrixStack.popPose();
		});
		
		super.render(matrixStack, mouseX, mouseY, partialTicks);
	}
	
	@Override
	public boolean isPauseScreen() {
		return true;
	}
	
	private void drawPlayerHead(MatrixStack matrixStack, int modelX, int modelY, float scale) {
	    // Get the player's texture (head)
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
