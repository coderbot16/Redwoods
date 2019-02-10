package net.coderbot.redwoods.biomes;

import net.coderbot.redwoods.block.BlockConiferSapling;
import net.coderbot.redwoods.init.ModBlocks;
import net.coderbot.redwoods.world.WorldGenConifer;
import net.coderbot.redwoods.world.WorldGenMegaConifer;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.BiomeDictionary;

import java.util.Random;

public class BiomeConiferous extends Biome {
	WorldGenAbstractTree[] trees;

	public BiomeConiferous(Properties properties) {
		super(properties.biomeProperties);

		decorator.treesPerChunk = 7;
		decorator.flowersPerChunk = 0;
		decorator.grassPerChunk = 16; // TODO: Use ferns
		decorator.mushroomsPerChunk = 2;

		spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 5, 4, 4));

		trees = properties.getTrees();
		if(trees.length == 0) {
			decorator.treesPerChunk = 0;
		}
	}

	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
		if(trees.length == 0) {
			return null;
		} else if(trees.length == 1) {
			return trees[0];
		}

		return trees[rand.nextInt(trees.length)];
	}

	public static class Properties {
		public BiomeProperties biomeProperties;
		public boolean redwoodNormal;
		public boolean redwoodMega;
		public boolean firNormal;
		public boolean firMega;

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
				BlockConiferSapling.TreeDefinition tree = ModBlocks.REDWOOD;
				trees[enabled] = new WorldGenMegaConifer(false, tree.woodSW, tree.woodNW, tree.woodNE, tree.woodSE, tree.leaves);
			}

			return trees;
		}
	}
}
