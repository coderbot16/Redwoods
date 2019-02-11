package net.coderbot.redwoods.biomes;

import mcp.MethodsReturnNonnullByDefault;
import net.coderbot.redwoods.block.BlockConiferSapling;
import net.coderbot.redwoods.init.ModBlocks;
import net.coderbot.redwoods.world.WorldGenConifer;
import net.coderbot.redwoods.world.WorldGenMegaConifer;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.BiomeDictionary;

import java.util.Random;

@MethodsReturnNonnullByDefault
public class BiomeConiferous extends Biome {
	private WorldGenAbstractTree[] trees;
	private int fernPercentage;

	public BiomeConiferous(Properties properties) {
		super(properties.biomeProperties);

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

	public static class Properties {
		public BiomeProperties biomeProperties;
		public boolean redwoodNormal;
		public boolean redwoodMega;
		public boolean firNormal;
		public boolean firMega;
		public int fernPercentage = 0;
		public int grassCount = 0;
		public int flowerCount = 0;
		public int treesPerChunk = 7;

		public Properties(String name, BiomeDictionary.Type... types) {
			biomeProperties = new BiomeProperties(name);
		}

		private WorldGenAbstractTree[] getTrees() {
			int enabled = 0;

			if(redwoodNormal) {
				enabled += 1;
			}

			if(redwoodMega) {
				enabled += 1;
			}

			if(firNormal) {
				enabled += 1;
			}

			if(firMega) {
				enabled += 1;
			}

			WorldGenAbstractTree[] trees = new WorldGenAbstractTree[enabled];
			enabled = 0;

			if(redwoodNormal) {
				BlockConiferSapling.TreeDefinition tree = ModBlocks.REDWOOD;
				trees[enabled++] = new WorldGenConifer(false, tree.wood, tree.leaves);
			}

			if(redwoodMega) {
				BlockConiferSapling.TreeDefinition tree = ModBlocks.REDWOOD;
				trees[enabled++] = new WorldGenMegaConifer(false, tree.woodSW, tree.woodNW, tree.woodNE, tree.woodSE, tree.leaves);
			}

			if(firNormal) {
				BlockConiferSapling.TreeDefinition tree = ModBlocks.FIR;
				trees[enabled++] = new WorldGenConifer(false, tree.wood, tree.leaves);
			}

			if(firMega) {
				BlockConiferSapling.TreeDefinition tree = ModBlocks.FIR;
				trees[enabled] = new WorldGenMegaConifer(false, tree.woodSW, tree.woodNW, tree.woodNE, tree.woodSE, tree.leaves);
			}

			return trees;
		}
	}
}
