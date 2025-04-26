package com.techzero.ftanw.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.StringTextComponent;

public class AbilitiesScreen extends Screen {

	public AbilitiesScreen() {
		super(new StringTextComponent("AbilitiesScreen"));
	}
	
	public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(matrixStack);
		super.render(matrixStack, mouseX, mouseY, partialTicks);
	}
	
	public boolean isPauseScreen() {
		return true;
	}

}
