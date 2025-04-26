package com.techzero.ftanw.init;

import com.techzero.ftanw.FairyTailMod;
import com.techzero.ftanw.items.CharacterCreationItem;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, FairyTailMod.MOD_ID);
	
	//ORES
	public static final RegistryObject<Item> ADAMANTITE_ORE_ITEM = ITEMS.register("adamantite_ore", () -> new BlockItem(ModBlocks.ADAMANTITE_ORE.get(),new Item.Properties().tab(FairyTailMod.FTBLOCKS)));
	
	public static final RegistryObject<Item> CHARACTER_CREATION_ITEM = ITEMS.register("character_creation_item", () -> new CharacterCreationItem(new Item.Properties()));
	
	@SubscribeEvent
	public static void onRegisterItems(IEventBus eventBus) {
		ITEMS.register(eventBus);
	}

}
