package net.coderbot.redwoods.block;

import net.coderbot.redwoods.world.ConiferTreeFeature;
import net.coderbot.redwoods.world.MegaConiferTreeFeature;
import net.minecraft.block.BlockState;
import net.minecraft.block.sapling.LargeTreeSaplingGenerator;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

import java.util.Random;

public class ConiferSaplingGenerator extends LargeTreeSaplingGenerator {
	private TreeDefinition definition;

	public ConiferSaplingGenerator(TreeDefinition definition) {
		super();

		this.definition = definition;
	}

	@Override
	protected AbstractTreeFeature<DefaultFeatureConfig> createTreeFeature(Random random_1) {
		return new ConiferTreeFeature(DefaultFeatureConfig::deserialize, true, definition.wood, definition.leaves);
	}

	@Override
	protected AbstractTreeFeature<DefaultFeatureConfig> createLargeTreeFeature(Random var1) {
		return new MegaConiferTreeFeature(DefaultFeatureConfig::deserialize, true, definition.woodSW, definition.woodNW, definition.woodNE, definition.woodSE, definition.leaves);
	}

	public static class TreeDefinition {
		public BlockState wood;
		public BlockState woodSW;
		public BlockState woodNW;
		public BlockState woodNE;
		public BlockState woodSE;
		public BlockState leaves;
	}
}
