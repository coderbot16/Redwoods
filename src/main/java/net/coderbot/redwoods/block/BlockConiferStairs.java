package net.coderbot.redwoods.block;

import net.minecraft.block.Block;
import net.minecraft.block.StairsBlock;

public class BlockConiferStairs extends StairsBlock {
	public BlockConiferStairs(Block base, Settings settings) {
		super(base.getDefaultState(), settings);

		// TODO useNeighborBrightness = true;
	}
}
