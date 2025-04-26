package com.techzero.ftanw.items;

import com.techzero.ftanw.FairyTailMod;
import com.techzero.ftanw.client.gui.CharacterCreationScreen;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class CharacterCreationItem extends Item {

	public CharacterCreationItem(Properties p_i48487_1_) {
		super(new Item.Properties().tab(FairyTailMod.FTMISC));
	}
	
	public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
		if (!worldIn.isClientSide) {
			return ActionResult.pass(playerIn.getItemInHand(handIn));
		}
		
		Minecraft.getInstance().setScreen(new CharacterCreationScreen());
		
		return ActionResult.success(playerIn.getItemInHand(handIn));
	}

}
