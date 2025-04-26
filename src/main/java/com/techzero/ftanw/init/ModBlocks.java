package com.techzero.ftanw.init;

import com.techzero.ftanw.FairyTailMod;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = FairyTailMod.MOD_ID, bus = Bus.FORGE)
public class ModBlocks {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
			FairyTailMod.MOD_ID);

	// ORES
	public static final RegistryObject<Block> ADAMANTITE_ORE = BLOCKS.register("adamantite_ore",
			() -> new Block(AbstractBlock.Properties.of(Material.STONE).strength(3.0f).requiresCorrectToolForDrops()));

}
