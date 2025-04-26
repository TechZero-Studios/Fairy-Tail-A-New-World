package com.techzero.ftanw.capabilities.magic;

import java.util.concurrent.Callable;

import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class MagicData {
	
	@CapabilityInject(IMagicData.class)
	public static Capability<IMagicData> MAGIC = null;
	
	public static class MagicStorage implements IStorage<IMagicData> {

		@Override
		public INBT writeNBT(Capability<IMagicData> capability, IMagicData instance, Direction side) {
			return MAGIC.writeNBT(instance, side);
		}

		@Override
		public void readNBT(Capability<IMagicData> capability, IMagicData instance, Direction side, INBT nbt) {
			if (nbt instanceof CompoundNBT) {
				MAGIC.readNBT(instance, side, nbt);
			}
		}
	}
	
	public static class MagicFactory implements Callable<IMagicData> {
		@Override
		public IMagicData call() throws Exception {
			return new MagicHandler();
		}
	}
	
	public static class MagicProvider implements ICapabilitySerializable<CompoundNBT> {
		
		private MagicHandler magicHandler = new MagicHandler();
		private LazyOptional<MagicHandler> magicOptional = LazyOptional.of(() -> magicHandler);

		@SuppressWarnings("unchecked")
		@Override
		public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
			return cap == MAGIC ? (LazyOptional<T>) magicOptional : LazyOptional.empty();
		}

		@Override
		public CompoundNBT serializeNBT() {
			return magicHandler.writeNBT();
		}

		@Override
		public void deserializeNBT(CompoundNBT nbt) {
			this.magicHandler.readNBT(nbt);
		}
		
	}
	
	public static IMagicData getClientMagicData(ClientPlayerEntity player) {
		IMagicData props = (IMagicData)player.getCapability(MAGIC).orElse(new MagicHandler());
		props.setOwner(player);
		return props;
	}

	public static IMagicData getServerMagicData(ServerPlayerEntity player) {
		return player.getCapability(MAGIC).orElse(null);
	}

}
