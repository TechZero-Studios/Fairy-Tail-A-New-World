package com.techzero.ftanw.capabilities.magic;

import com.techzero.ftanw.api.enums.MagicType;
import com.techzero.ftanw.config.CommonConfig;

import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;

public class MagicHandler implements IMagicData {
	
	private MagicType magicPower = MagicType.NONE;
	private int baseMana = 25;
	private int maxMana = (int) (baseMana * CommonConfig.mana_max_modifier.get());
	private int currentMana = maxMana;
	

	@Override
	public MagicType getMagicPower() {
		return this.magicPower;
	}

	@Override
	public void setMagicPower(MagicType magicPower) {
		this.magicPower = magicPower;
	}

	@Override
	public int currentMana() {
		return this.currentMana;
	}

	@Override
	public int maxMana() {
		return this.maxMana;
	}

	@Override
	public void setMana(int mana) {
		this.currentMana = mana;
	}

	@Override
	public CompoundNBT writeNBT() {
		CompoundNBT nbt = new CompoundNBT();
		nbt.putString("MagicPower", magicPower.name());
		nbt.putInt("CurrentMana", currentMana);
		nbt.putInt("MaxMana", maxMana);
		return nbt;
	}

	@Override
	public void readNBT(CompoundNBT nbt) {
		this.magicPower = MagicType.valueOf(nbt.getString("MagicPower"));
		this.currentMana = nbt.getInt("CurrentMana");
		this.maxMana = nbt.getInt("MaxMana");
	}

	@Override
	public IMagicData setOwner(LivingEntity entity) {
		return this;
	}

}
