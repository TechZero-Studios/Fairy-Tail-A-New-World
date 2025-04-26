package com.techzero.ftanw.capabilities.stats;

import com.techzero.ftanw.config.CommonConfig;

import net.minecraft.nbt.CompoundNBT;

public class StatsHandler implements IPlayerData{
	
	private int jewels = 0;
	private int level = 1;
	private double experiance = 0.0;
	private String faction = "None";
	private String rank = "None";
	
	@Override
	public CompoundNBT writeNBT() {
		CompoundNBT nbt = new CompoundNBT();
		nbt.putInt("Jewels", jewels);
		nbt.putInt("MageLevel", level);
		nbt.putString("Faction", faction);
		nbt.putString("MageRank", rank);
		return nbt;
	}

	@Override
	public void readNBT(CompoundNBT nbt) {
		this.jewels = nbt.getInt("Jewels");
		this.level = nbt.getInt("MageLevel");
		this.faction = nbt.getString("Faction");
		this.rank = nbt.getString("MageRank");
	}

	@Override
	public int getJewels() {
		return this.jewels;
	}

	@Override
	public void addJewels(int amount) {
		this.jewels += amount;
	}

	@Override
	public int getLevel() {
		return this.level;
	}

	@Override
	public void setLevel(int amount) {
		this.level = amount;
	}

	@Override
	public String getFaction() {
		return this.faction;
	}

	@Override
	public void setFaction(String faction) {
		this.faction = faction;
	}

	@Override
	public String getRank() {
		return this.rank;
	}

	@Override
	public void setRank(String rank) {
		this.rank = rank;
	}

	@Override
	public double modifierMageExperiance() {
		return CommonConfig.exp_modifier.get();
	}

	@Override
	public double getMageExperiance() {
		return this.experiance;
	}

	@Override
	public void setMageExperiance(double amount) {
		this.experiance = amount;
	}

}
