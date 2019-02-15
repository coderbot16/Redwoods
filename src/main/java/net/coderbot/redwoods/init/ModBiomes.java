package net.coderbot.redwoods.init;

import net.coderbot.redwoods.Redwoods;
import net.coderbot.redwoods.biomes.BiomeConiferous;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class ModBiomes {
	// TODO: Consider an Alpine biome.

	// Mega Redwoods
	public static BiomeConiferous REDWOOD_FOREST;
	public static BiomeConiferous.Properties REDWOOD_FOREST_PROPS;

	// Normal Firs + Mega Redwoods
	public static BiomeConiferous LUSH_REDWOOD_FOREST;
	public static BiomeConiferous.Properties LUSH_REDWOOD_FOREST_PROPS;

	// Normal Firs + Mega Firs
	public static BiomeConiferous TEMPERATE_RAINFOREST;
	public static BiomeConiferous.Properties TEMPERATE_RAINFOREST_PROPS;

	// Normal Firs + Mega Firs + Snow
	public static BiomeConiferous SNOWY_RAINFOREST;
	public static BiomeConiferous.Properties SNOWY_RAINFOREST_PROPS;

	@SubscribeEvent
	public static void registerBiomes(RegistryEvent.Register<Biome> event) {
		// Redwood Forest
		REDWOOD_FOREST_PROPS = new BiomeConiferous.Properties("Redwood Forest");
		REDWOOD_FOREST_PROPS.biomeProperties
				.setTemperature(1.1F)
				.setRainfall(1.4F)
				.setBaseHeight(1.2F)
				.setHeightVariation(0.3F);
		REDWOOD_FOREST_PROPS.redwoodMega = true;
		REDWOOD_FOREST_PROPS.fernPercentage = 100;
		REDWOOD_FOREST_PROPS.grassCount = 1;
		REDWOOD_FOREST = registerConiferBiome(event, "redwood_forest", REDWOOD_FOREST_PROPS);
		BiomeDictionary.addTypes(REDWOOD_FOREST,
				BiomeDictionary.Type.HOT,
				BiomeDictionary.Type.DENSE,
				BiomeDictionary.Type.WET,
				BiomeDictionary.Type.FOREST,
				BiomeDictionary.Type.CONIFEROUS,
				BiomeDictionary.Type.HILLS,
				BiomeDictionary.Type.LUSH
		);
		BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(REDWOOD_FOREST, 8));
		BiomeManager.addSpawnBiome(REDWOOD_FOREST);

		// Lush Redwood Forest
		LUSH_REDWOOD_FOREST_PROPS = new BiomeConiferous.Properties("Lush Redwood Forest");
		LUSH_REDWOOD_FOREST_PROPS.biomeProperties
				.setTemperature(1.1F)
				.setRainfall(1.4F)
				.setBaseHeight(1.2F)
				.setHeightVariation(0.3F);
		LUSH_REDWOOD_FOREST_PROPS.redwoodMega = true;
		LUSH_REDWOOD_FOREST_PROPS.firNormal = true;
		LUSH_REDWOOD_FOREST_PROPS.fernPercentage = 100;
		LUSH_REDWOOD_FOREST_PROPS.grassCount = 1;
		LUSH_REDWOOD_FOREST_PROPS.flowerCount = 1;
		LUSH_REDWOOD_FOREST_PROPS.treesPerChunk = 8;
		LUSH_REDWOOD_FOREST = registerConiferBiome(event, "lush_redwood_forest", LUSH_REDWOOD_FOREST_PROPS);
		BiomeDictionary.addTypes(LUSH_REDWOOD_FOREST,
				BiomeDictionary.Type.HOT,
				BiomeDictionary.Type.DENSE,
				BiomeDictionary.Type.WET,
				BiomeDictionary.Type.FOREST,
				BiomeDictionary.Type.CONIFEROUS,
				BiomeDictionary.Type.HILLS,
				BiomeDictionary.Type.LUSH
		);
		BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(LUSH_REDWOOD_FOREST, 8));
		BiomeManager.addSpawnBiome(LUSH_REDWOOD_FOREST);

		// Temperate Rainforest
		TEMPERATE_RAINFOREST_PROPS = new BiomeConiferous.Properties("Temperate Rainforest");
		TEMPERATE_RAINFOREST_PROPS.biomeProperties
				.setTemperature(0.6F)
				.setRainfall(0.9F)
				.setBaseHeight(0.95F)
				.setHeightVariation(0.55F);
		TEMPERATE_RAINFOREST_PROPS.firMega = true;
		TEMPERATE_RAINFOREST_PROPS.firNormal = true;
		TEMPERATE_RAINFOREST_PROPS.fernPercentage = 75;
		TEMPERATE_RAINFOREST_PROPS.grassCount = 16;
		TEMPERATE_RAINFOREST_PROPS.treesPerChunk = 17;
		TEMPERATE_RAINFOREST = registerConiferBiome(event, "temperate_rainforest", TEMPERATE_RAINFOREST_PROPS);
		BiomeDictionary.addTypes(TEMPERATE_RAINFOREST,
				BiomeDictionary.Type.DENSE,
				BiomeDictionary.Type.WET,
				BiomeDictionary.Type.FOREST,
				BiomeDictionary.Type.CONIFEROUS,
				BiomeDictionary.Type.HILLS,
				BiomeDictionary.Type.LUSH
		);
		BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(TEMPERATE_RAINFOREST, 10));
		BiomeManager.addSpawnBiome(TEMPERATE_RAINFOREST);

		// Snowy Rainforest
		SNOWY_RAINFOREST_PROPS = new BiomeConiferous.Properties("Snowy Rainforest");
		SNOWY_RAINFOREST_PROPS.biomeProperties
				.setTemperature(-0.5F)
				.setRainfall(1.3F)
				.setBaseHeight(0.95F)
				.setHeightVariation(0.55F)
				.setSnowEnabled();
		SNOWY_RAINFOREST_PROPS.firMega = true;
		SNOWY_RAINFOREST_PROPS.firNormal = true;
		SNOWY_RAINFOREST_PROPS.fernPercentage = 75;
		SNOWY_RAINFOREST_PROPS.grassCount = 16;
		SNOWY_RAINFOREST_PROPS.treesPerChunk = 17;
		SNOWY_RAINFOREST = registerConiferBiome(event, "snowy_rainforest", SNOWY_RAINFOREST_PROPS);
		BiomeDictionary.addTypes(SNOWY_RAINFOREST,
				BiomeDictionary.Type.DENSE,
				BiomeDictionary.Type.WET,
				BiomeDictionary.Type.FOREST,
				BiomeDictionary.Type.CONIFEROUS,
				BiomeDictionary.Type.HILLS,
				BiomeDictionary.Type.LUSH
		);
		BiomeManager.addBiome(BiomeManager.BiomeType.ICY, new BiomeManager.BiomeEntry(SNOWY_RAINFOREST, 10));
	}

	private static BiomeConiferous registerConiferBiome(RegistryEvent.Register<Biome> event, String name, BiomeConiferous.Properties properties) {
		BiomeConiferous biome = new BiomeConiferous(properties);
		biome.setRegistryName(Redwoods.MODID, name);

		event.getRegistry().register(biome);

		return biome;
	}
}
