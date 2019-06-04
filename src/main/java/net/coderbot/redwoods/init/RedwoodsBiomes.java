package net.coderbot.redwoods.init;

import net.coderbot.redwoods.biomes.AlpineBiome;
import net.coderbot.redwoods.biomes.ConiferousBiome;
import net.coderbot.redwoods.world.ConiferTreeFeature;
import net.coderbot.redwoods.world.MegaConiferTreeFeature;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.CountDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.GrassFeatureConfig;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

public class RedwoodsBiomes {
	public static Feature<DefaultFeatureConfig> REDWOOD_TREE;
	public static Feature<DefaultFeatureConfig> FIR_TREE;
	public static Feature<DefaultFeatureConfig> MEGA_REDWOOD_TREE;
	public static Feature<DefaultFeatureConfig> MEGA_FIR_TREE;

	public static ConiferousBiome REDWOOD_FOREST;
	public static ConiferousBiome LUSH_REDWOOD_FOREST;
	public static ConiferousBiome TEMPERATE_RAINFOREST;
	public static ConiferousBiome SNOWY_RAINFOREST;
	public static AlpineBiome ALPINE;

	public static void registerBiomes() {
		REDWOOD_TREE = Registry.register(Registry.FEATURE, "redwoods:redwood_tree",
				new ConiferTreeFeature(DefaultFeatureConfig::deserialize, false, ModBlocks.REDWOOD.wood, ModBlocks.REDWOOD.leaves)
		);

		FIR_TREE = Registry.register(Registry.FEATURE, "redwoods:fir_tree",
				new ConiferTreeFeature(DefaultFeatureConfig::deserialize, false, ModBlocks.FIR.wood, ModBlocks.FIR.leaves)
		);

		MEGA_REDWOOD_TREE = Registry.register(Registry.FEATURE, "redwoods:mega_redwood_tree",
				new MegaConiferTreeFeature(DefaultFeatureConfig::deserialize, false, ModBlocks.REDWOOD.woodSW, ModBlocks.REDWOOD.woodNW, ModBlocks.REDWOOD.woodNE, ModBlocks.REDWOOD.woodSE, ModBlocks.REDWOOD.leaves)
		);

		MEGA_FIR_TREE = Registry.register(Registry.FEATURE, "redwoods:mega_fir_tree",
				new MegaConiferTreeFeature(DefaultFeatureConfig::deserialize, false, ModBlocks.FIR.woodSW, ModBlocks.FIR.woodNW, ModBlocks.FIR.woodNE, ModBlocks.FIR.woodSE, ModBlocks.FIR.leaves)
		);

		// TODO: Biome IDs

		REDWOOD_FOREST = Registry.register(Registry.BIOME, 51, "redwoods:redwood_forest", new ConiferousBiome(
				new Biome.Settings()
						.configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)
						.precipitation(Biome.Precipitation.RAIN).category(Biome.Category.TAIGA)
						.depth(1.2F)
						.scale(0.3F)
						.temperature(1.1F)
						.downfall(1.4F)
						.waterColor(4159204)
						.waterFogColor(329011)
						.parent(null),
				7,
				MEGA_REDWOOD_TREE,
				MEGA_REDWOOD_TREE
		));

		LUSH_REDWOOD_FOREST = Registry.register(Registry.BIOME, 52, "redwoods:lush_redwood_forest", new ConiferousBiome(
				new Biome.Settings()
						.configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)
						.precipitation(Biome.Precipitation.RAIN).category(Biome.Category.TAIGA)
						.depth(1.2F)
						.scale(0.3F)
						.temperature(1.1F)
						.downfall(1.4F)
						.waterColor(4159204)
						.waterFogColor(329011)
						.parent(null),
				8,
				MEGA_REDWOOD_TREE,
				FIR_TREE
		));

		DefaultBiomeFeatures.addDefaultFlowers(LUSH_REDWOOD_FOREST);

		TEMPERATE_RAINFOREST = Registry.register(Registry.BIOME, 53, "redwoods:temperate_rainforest", new ConiferousBiome(
				new Biome.Settings()
						.configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)
						.precipitation(Biome.Precipitation.RAIN).category(Biome.Category.TAIGA)
						.depth(0.95F)
						.scale(0.55F)
						.temperature(0.6F)
						.downfall(0.9F)
						.waterColor(4159204)
						.waterFogColor(329011)
						.parent(null),
				17,
				MEGA_FIR_TREE,
				FIR_TREE
		));

		TEMPERATE_RAINFOREST.addFeature(
				GenerationStep.Feature.VEGETAL_DECORATION,
				Biome.configureFeature(Feature.GRASS,
						new GrassFeatureConfig(Blocks.FERN.getDefaultState()),
						Decorator.COUNT_HEIGHTMAP_DOUBLE,
						new CountDecoratorConfig(12)
				)
		);

		TEMPERATE_RAINFOREST.addFeature(
				GenerationStep.Feature.VEGETAL_DECORATION,
				Biome.configureFeature(Feature.GRASS,
						new GrassFeatureConfig(Blocks.GRASS.getDefaultState()),
						Decorator.COUNT_HEIGHTMAP_DOUBLE,
						new CountDecoratorConfig(4)
				)
		);

		SNOWY_RAINFOREST = Registry.register(Registry.BIOME, 54, "redwoods:snowy_rainforest", new ConiferousBiome(
				new Biome.Settings()
						.configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)
						.precipitation(Biome.Precipitation.SNOW).category(Biome.Category.TAIGA)
						.depth(0.95F)
						.scale(0.55F)
						.temperature(-0.5F)
						.downfall(1.3F)
						.waterColor(4020182)
						.waterFogColor(329011)
						.parent(null),
				17,
				MEGA_FIR_TREE,
				FIR_TREE
		));

		SNOWY_RAINFOREST.addFeature(
				GenerationStep.Feature.VEGETAL_DECORATION,
				Biome.configureFeature(Feature.GRASS,
						new GrassFeatureConfig(Blocks.GRASS.getDefaultState()),
						Decorator.COUNT_HEIGHTMAP_DOUBLE,
						new CountDecoratorConfig(4)
				)
		);

		ALPINE = Registry.register(Registry.BIOME, 55, "redwoods:alpine", new AlpineBiome(
				new Biome.Settings()
						.configureSurfaceBuilder(SurfaceBuilder.MOUNTAIN, SurfaceBuilder.GRASS_CONFIG)
						.precipitation(Biome.Precipitation.SNOW).category(Biome.Category.EXTREME_HILLS)
						.depth(1.7F)
						.scale(0.4F)
						.temperature(0.0F)
						.downfall(0.1F)
						.waterColor(4159204)
						.waterFogColor(329011)
						.parent(null),
				1,
				REDWOOD_TREE,
				FIR_TREE
		));
	}
}
