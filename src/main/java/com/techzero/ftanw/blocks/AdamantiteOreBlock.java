package com.techzero.ftanw.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class AdamantiteOreBlock extends Block {

	public AdamantiteOreBlock() {
		super(AbstractBlock.Properties.of(Material.STONE)
				.strength(3.0f, 3.0f)
				.sound(SoundType.STONE)
				.requiresCorrectToolForDrops());
	}

}
