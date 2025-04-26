package com.techzero.ftanw.capabilities.magic;

import com.techzero.ftanw.api.enums.MagicType;

import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;

public interface IMagicData {
	IMagicData setOwner(LivingEntity paramLivingEntity);
	
	MagicType getMagicPower();
	void setMagicPower(MagicType magicPower);
	
	int currentMana();
	int maxMana();
	void setMana(int mana);
	
	CompoundNBT writeNBT();
	void readNBT(CompoundNBT nbt);

}
