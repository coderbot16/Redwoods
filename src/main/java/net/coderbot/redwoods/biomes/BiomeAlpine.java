package net.coderbot.redwoods.biomes;

import net.minecraft.block.BlockTallGrass;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeHills;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class BiomeAlpine extends BiomeHills {
	private WorldGenAbstractTree[] trees;
	private int fernPercentage;

	public BiomeAlpine(BiomeConiferous.Properties properties) {
		super(Type.NORMAL, properties.biomeProperties);

		topBlock = Blocks.STONE.getDefaultState();
		fillerBlock = Blocks.STONE.getDefaultState();

		decorator.treesPerChunk = properties.treesPerChunk;
		decorator.flowersPerChunk = properties.flowerCount;
		decorator.grassPerChunk = properties.grassCount;
		decorator.mushroomsPerChunk = 2;

		spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 5, 4, 4));

		fernPercentage = properties.fernPercentage;

		trees = properties.getTrees();
		if(trees.length == 0) {
			decorator.treesPerChunk = 0;
		}
	}

	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
		if(trees.length == 0) {
			return new WorldGenTrees(false);
		} else if(trees.length == 1) {
			return trees[0];
		}

		return trees[rand.nextInt(trees.length)];
	}

	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random rand) {
		int percent = rand.nextInt(100); // 0-99

		if(percent < fernPercentage) {
			return new WorldGenTallGrass(BlockTallGrass.EnumType.FERN);
		} else {
			return new WorldGenTallGrass(BlockTallGrass.EnumType.GRASS);
		}
	}
}
