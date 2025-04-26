package com.techzero.ftanw.world.gen;

import com.techzero.ftanw.init.ModBlocks;

import net.minecraft.block.Block;
import net.minecraftforge.common.util.Lazy;

public enum OreType {
	
	ADAMANTITE(Lazy.of(ModBlocks.ADAMANTITE_ORE), 10, 25, 50, 15);
	
	private final Lazy<Block> block;
	private final int maxVeinSize;
	private final int minHeight;
	private final int maxHeight;
	private final int veinCount;
	
	OreType(Lazy<Block> block, int maxVeinSize, int minHeight, int maxHeight, int veinCount) {
		this.block = block;
		this.maxVeinSize = maxVeinSize;
		this.minHeight = minHeight;
		this.maxHeight = maxHeight;
		this.veinCount = veinCount;
	}

	public Lazy<Block> getBlock() {
		return block;
	}

	public int getMaxVeinSize() {
		return maxVeinSize;
	}

	public int getMinHeight() {
		return minHeight;
	}

	public int getMaxHeight() {
		return maxHeight;
	}
	
	public int getVeinCount() {
		return veinCount;
	}
	
	public static OreType get(Block block) {
		for (OreType ore : values()) {
			if(block == ore.block) {
				return ore;
			}
		}
		return null;
	}

}
