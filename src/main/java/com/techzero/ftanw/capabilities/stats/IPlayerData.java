package com.techzero.ftanw.capabilities.stats;

import net.minecraft.nbt.CompoundNBT;

public interface IPlayerData {
	int getJewels();
	void addJewels(int amount);
	
	int getLevel();
	void setLevel(int amount);
	
	
	double modifierMageExperiance();
	double getMageExperiance();
	void setMageExperiance(double amount);
	
	String getFaction();
	void setFaction(String faction);
	
	String getRank();
	void setRank(String rank);
	
	//NBT
	CompoundNBT writeNBT();
	void readNBT(CompoundNBT statsNBT);

}
