package com.techzero.ftanw;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.techzero.ftanw.capabilities.magic.IMagicData;
import com.techzero.ftanw.capabilities.magic.MagicData;
import com.techzero.ftanw.capabilities.stats.IPlayerData;
import com.techzero.ftanw.capabilities.stats.PlayerData;
import com.techzero.ftanw.config.CommonConfig;
import com.techzero.ftanw.init.ModBlocks;
import com.techzero.ftanw.init.ModItems;
import com.techzero.ftanw.init.ModKeybinds;
import com.techzero.ftanw.network.NetworkHandler;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(FairyTailMod.MOD_ID)
public class FairyTailMod {
	public static final String MOD_ID = "ftanw";
	private static final Logger LOGGER = LogManager.getLogger("ftanw");
	
	public FairyTailMod() {
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
		LOGGER.info("Fairy Tail: A New World Mod is loading...");
		ModLoadingContext.get().registerConfig(Type.COMMON, CommonConfig.SPEC, "fairytail-common.toml");
		ModBlocks.BLOCKS.register(eventBus);
		ModItems.ITEMS.register(eventBus);
		
		
		eventBus.addListener(this::onCommonSetup);
		eventBus.addListener(this::onClientSetup);
	}
	
	@SubscribeEvent
	public void onClientSetup(FMLClientSetupEvent event) {
		ModKeybinds.register(event);
	}
	
	public void onCommonSetup(FMLCommonSetupEvent event) {
		CapabilityManager.INSTANCE.register(IPlayerData.class, new PlayerData.StatsStorage(), new PlayerData.StatsFactory());
		CapabilityManager.INSTANCE.register(IMagicData.class, new MagicData.MagicStorage(), new MagicData.MagicFactory());
		NetworkHandler.register();
	}
	
	public static final ItemGroup FTMISC = new ItemGroup("fairy_tail_misc") {
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(Items.IRON_NUGGET);
			
		}
	};
	
	public static final ItemGroup FTWEAPONS = new ItemGroup("fairy_tail_weapons") {
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(Items.IRON_SWORD);
			
		}
	};
	
	public static final ItemGroup FTBLOCKS = new ItemGroup("fairy_tail_blocks") {
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(ModBlocks.ADAMANTITE_ORE.get());
			
		}
	};

}
