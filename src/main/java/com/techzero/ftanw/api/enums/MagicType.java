package com.techzero.ftanw.api.enums;

import net.minecraft.util.text.TextFormatting;

public enum MagicType {
	NONE(TextFormatting.GRAY, "None"),
	AIR(TextFormatting.GRAY, "Air"),
	FIRE(TextFormatting.GRAY, "Fire"),
	WATER(TextFormatting.GRAY, "Water"),
	ICE(TextFormatting.GRAY, "Ice"),
	EARTH(TextFormatting.GRAY, "Earth"),
	ACID(TextFormatting.GRAY, "Acid"),
	;
	
	private TextFormatting color;
	private String name;

	MagicType(TextFormatting color, String name) {
		this.color = color;
		this.name = name;
	}
	
	public TextFormatting getColor() {
		return this.color;
	}
	
	public String getName() {
		return this.name;
	}

}
