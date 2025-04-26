package com.techzero.ftanw.events;

import com.techzero.ftanw.FairyTailMod;
import com.techzero.ftanw.capabilities.magic.MagicData;
import com.techzero.ftanw.capabilities.stats.PlayerData;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = FairyTailMod.MOD_ID, bus = Bus.FORGE)
public class OnAttachCapabilitiesEvent {
	
	@SubscribeEvent
	public static void AttachCapabilitiesEntity(AttachCapabilitiesEvent<Entity> event) {
		if (event.getObject() instanceof PlayerEntity) {
			event.addCapability(new ResourceLocation(FairyTailMod.MOD_ID, "stats_capability"), new PlayerData.StatsProvider());
			event.addCapability(new ResourceLocation(FairyTailMod.MOD_ID, "magic_capability"), new MagicData.MagicProvider());
		}
	}
	
	@SubscribeEvent
	public void onPlayerClone(PlayerEvent.Clone event) {
		if (!event.isWasDeath()) return;
		event.getOriginal().getCapability(MagicData.MAGIC).ifPresent(oldStore -> {
			event.getPlayer().getCapability(MagicData.MAGIC).ifPresent(newStore -> {
				newStore.setMana(oldStore.currentMana());
				//newStore.setMaxMana(oldStore.maxMana());
			});
		});
	}

}
