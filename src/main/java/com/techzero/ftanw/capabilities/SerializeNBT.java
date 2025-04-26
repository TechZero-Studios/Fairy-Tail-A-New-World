package com.techzero.ftanw.capabilities;

import net.minecraft.nbt.CompoundNBT;

public interface SerializeNBT {
	
	CompoundNBT serializeNBT();
	
	void deserializeNBT(CompoundNBT nbt);

}
