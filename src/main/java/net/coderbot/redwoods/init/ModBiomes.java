// TODO: Biomes
/*package net.coderbot.redwoods.init;

import net.coderbot.redwoods.Redwoods_old;
import net.coderbot.redwoods.RedwoodsConfig;
import net.coderbot.redwoods.biomes.BiomeAlpine;
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

	// Mega Redwoods_old
	public static BiomeConiferous REDWOOD_FOREST;
	public static BiomeConiferous.Properties REDWOOD_FOREST_PROPS;

	// Normal Firs + Mega Redwoods_old
	public static BiomeConiferous LUSH_REDWOOD_FOREST;
	public static BiomeConiferous.Properties LUSH_REDWOOD_FOREST_PROPS;

	// Normal Firs + Mega Firs
	public static BiomeConiferous TEMPERATE_RAINFOREST;
	public static BiomeConiferous.Properties TEMPERATE_RAINFOREST_PROPS;

	// Normal Firs + Mega Firs + Snow
	public static BiomeConiferous SNOWY_RAINFOREST;
	public static BiomeConiferous.Properties SNOWY_RAINFOREST_PROPS;

	// Sparse Normal Firs + Sparse Normal Redwoods_old + Snow + Basin
	public static BiomeAlpine ALPINE;
	public static BiomeConiferous.Properties ALPINE_PROPS;

	@SubscribeEvent
	public static void registerBiomes(RegistryEvent.Register<Biome> event) {
		// Redwood Forest
		if(RedwoodsConfig.biomes.redwoodForest.register) {
			REDWOOD_FOREST_PROPS = new BiomeConiferous.Properties("Redwood Forest");
			REDWOOD_FOREST_PROPS.biomeProperties
					.setTemperature(1.1F)
					.setRainfall(1.4F)
					.setBaseHeight(1.2F)
					.setHeightVariation(0.3F);
			REDWOOD_FOREST_PROPS.redwoodMega = true;
			REDWOOD_FOREST_PROPS.fernPercentage = RedwoodsConfig.biomes.redwoodForest.fernPercentage;
			REDWOOD_FOREST_PROPS.grassCount = RedwoodsConfig.biomes.redwoodForest.grassCount;
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

			int weight = RedwoodsConfig.biomes.redwoodForest.weight;

			if(weight > 0) {
				BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(REDWOOD_FOREST, weight));
				BiomeManager.addSpawnBiome(REDWOOD_FOREST);
			}
		}

		// Lush Redwood Forest
		if(RedwoodsConfig.biomes.lushRedwoodForest.register) {
			LUSH_REDWOOD_FOREST_PROPS = new BiomeConiferous.Properties("Lush Redwood Forest");
			LUSH_REDWOOD_FOREST_PROPS.biomeProperties
					.setTemperature(1.1F)
					.setRainfall(1.4F)
					.setBaseHeight(1.2F)
					.setHeightVariation(0.3F);
			LUSH_REDWOOD_FOREST_PROPS.redwoodMega = true;
			LUSH_REDWOOD_FOREST_PROPS.firNormal = true;
			LUSH_REDWOOD_FOREST_PROPS.fernPercentage = RedwoodsConfig.biomes.lushRedwoodForest.fernPercentage;
			LUSH_REDWOOD_FOREST_PROPS.grassCount = RedwoodsConfig.biomes.lushRedwoodForest.grassCount;
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

			int weight = RedwoodsConfig.biomes.lushRedwoodForest.weight;

			if(weight > 0) {
				BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(LUSH_REDWOOD_FOREST, weight));
				BiomeManager.addSpawnBiome(LUSH_REDWOOD_FOREST);
			}
		}

		if(RedwoodsConfig.biomes.temperateRainforest.register) {
			// Temperate Rainforest
			TEMPERATE_RAINFOREST_PROPS = new BiomeConiferous.Properties("Temperate Rainforest");
			TEMPERATE_RAINFOREST_PROPS.biomeProperties
					.setTemperature(0.6F)
					.setRainfall(0.9F)
					.setBaseHeight(0.95F)
					.setHeightVariation(0.55F);
			TEMPERATE_RAINFOREST_PROPS.firMega = true;
			TEMPERATE_RAINFOREST_PROPS.firNormal = true;
			TEMPERATE_RAINFOREST_PROPS.fernPercentage = RedwoodsConfig.biomes.temperateRainforest.fernPercentage;
			TEMPERATE_RAINFOREST_PROPS.grassCount = RedwoodsConfig.biomes.temperateRainforest.grassCount;
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

			int weight = RedwoodsConfig.biomes.temperateRainforest.weight;

			if(weight > 0) {
				BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(TEMPERATE_RAINFOREST, weight));
				BiomeManager.addSpawnBiome(TEMPERATE_RAINFOREST);
			}
		}

		if(RedwoodsConfig.biomes.snowyRainforest.register) {
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
			SNOWY_RAINFOREST_PROPS.fernPercentage = RedwoodsConfig.biomes.snowyRainforest.fernPercentage;
			SNOWY_RAINFOREST_PROPS.grassCount = RedwoodsConfig.biomes.snowyRainforest.grassCount;
			SNOWY_RAINFOREST_PROPS.treesPerChunk = 17;
			SNOWY_RAINFOREST = registerConiferBiome(event, "snowy_rainforest", SNOWY_RAINFOREST_PROPS);
			BiomeDictionary.addTypes(SNOWY_RAINFOREST,
					BiomeDictionary.Type.DENSE,
					BiomeDictionary.Type.SNOWY,
					BiomeDictionary.Type.FOREST,
					BiomeDictionary.Type.CONIFEROUS,
					BiomeDictionary.Type.HILLS,
					BiomeDictionary.Type.LUSH
			);

			int weight = RedwoodsConfig.biomes.snowyRainforest.weight;

			if(weight > 0) {
				BiomeManager.addBiome(BiomeManager.BiomeType.ICY, new BiomeManager.BiomeEntry(SNOWY_RAINFOREST, weight));
			}
		}

		if(RedwoodsConfig.biomes.alpine.register) {
			// Alpine
			ALPINE_PROPS = new BiomeConiferous.Properties("Alpine");
			ALPINE_PROPS.biomeProperties
					.setTemperature(0.0F)
					.setRainfall(0.1F)
					.setBaseHeight(1.7F)
					.setHeightVariation(0.4F)
					.setSnowEnabled();
			ALPINE_PROPS.redwoodNormal = true;
			ALPINE_PROPS.firNormal = true;
			ALPINE_PROPS.fernPercentage = RedwoodsConfig.biomes.alpine.fernPercentage;
			ALPINE_PROPS.grassCount = RedwoodsConfig.biomes.alpine.grassCount;
			ALPINE_PROPS.flowerCount = 4;
			ALPINE_PROPS.treesPerChunk = 1;
			ALPINE = registerAlpineBiome(event, "alpine", ALPINE_PROPS);
			BiomeDictionary.addTypes(ALPINE,
					BiomeDictionary.Type.SNOWY,
					BiomeDictionary.Type.CONIFEROUS,
					BiomeDictionary.Type.MOUNTAIN
			);

			int weight = RedwoodsConfig.biomes.alpine.weight;

			if(weight > 0) {
				BiomeManager.addBiome(BiomeManager.BiomeType.ICY, new BiomeManager.BiomeEntry(ALPINE, weight));
			}
		}
	}

	private static BiomeConiferous registerConiferBiome(RegistryEvent.Register<Biome> event, String name, BiomeConiferous.Properties properties) {
		BiomeConiferous biome = new BiomeConiferous(properties);
		biome.setRegistryName(Redwoods_old.MODID, name);

		event.getRegistry().register(biome);

		return biome;
	}

	private static BiomeAlpine registerAlpineBiome(RegistryEvent.Register<Biome> event, String name, BiomeConiferous.Properties properties) {
		BiomeAlpine biome = new BiomeAlpine(properties);
		biome.setRegistryName(Redwoods_old.MODID, name);

		event.getRegistry().register(biome);

		return biome;
	}
}
*/