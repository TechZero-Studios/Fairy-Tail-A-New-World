package com.techzero.ftanw.config;

import java.nio.file.Path;
import java.nio.file.Paths;

import com.techzero.ftanw.FairyTailMod;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = FairyTailMod.MOD_ID, bus = Bus.FORGE)
public class CommonConfig {
	public static final Path CONFIG_PATH = Paths.get("config", new String[] { "fairytail_config.toml" });
	
	public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec SPEC;
	
	public static final ForgeConfigSpec.ConfigValue<Integer> jewel_max;
	public static final ForgeConfigSpec.ConfigValue<Double> mana_max_modifier;
	public static final ForgeConfigSpec.ConfigValue<Integer> level_max;
	public static final ForgeConfigSpec.ConfigValue<Double> exp_modifier;
	
	static {
		BUILDER.push("General");
		jewel_max = BUILDER.comment("Maximum jewels player can have, default 1000000").define("Maximum Jewels", 1000000);
		mana_max_modifier = BUILDER.comment("Modifier for maximum mana, default 1.0, maximum 20.0").define("Mana Maximum Modifier", 1.0);
		level_max = BUILDER.comment("Maximum player level, default 100").define("Maximum Level", 100);
		exp_modifier = BUILDER.comment("Experiance gain modifier, default 1").define("Experiance Modifier", 1.0);
		BUILDER.pop();
		
		BUILDER.push("Abilities");
		
		BUILDER.pop();
		SPEC =BUILDER.build();
	}

}
