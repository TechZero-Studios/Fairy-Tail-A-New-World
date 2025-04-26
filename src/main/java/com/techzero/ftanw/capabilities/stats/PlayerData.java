package com.techzero.ftanw.capabilities.stats;

import java.util.concurrent.Callable;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;


public class PlayerData {
	
	@CapabilityInject(IPlayerData.class)
	public static Capability<IPlayerData> STATS = null;
	
	public static class StatsStorage implements IStorage<IPlayerData> {

		@Override
		public INBT writeNBT(Capability<IPlayerData> capability, IPlayerData instance, Direction side) {
			return STATS.writeNBT(instance, side);
		}

		@Override
		public void readNBT(Capability<IPlayerData> capability, IPlayerData instance, Direction side, INBT nbt) {
			if (nbt instanceof CompoundNBT) {
				STATS.readNBT(instance, side, nbt);
			}
		}
		
	}
	
	public static class StatsFactory implements Callable<IPlayerData> {
		@Override
		public IPlayerData call() {
			return new StatsHandler();
		}
		
	}
	
	public static class StatsProvider implements ICapabilitySerializable<CompoundNBT> {
		
		private StatsHandler statsHandler = new StatsHandler();
		private LazyOptional<StatsHandler> statsOptional = LazyOptional.of(() -> statsHandler);

		@SuppressWarnings("unchecked")
		@Override
		public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
			return cap == STATS ? (LazyOptional<T>) statsOptional : LazyOptional.empty();
		}

		@Override
		public CompoundNBT serializeNBT() {
			return statsHandler.writeNBT();
		}

		@Override
		public void deserializeNBT(CompoundNBT nbt) {
			this.statsHandler.readNBT(nbt);
		}
		
	}

}
