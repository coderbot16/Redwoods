package net.coderbot.redwoods.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;

public class BlockConiferStairs extends BlockStairs {
	public BlockConiferStairs(Block base) {
		super(base.getDefaultState());

		useNeighborBrightness = true;
	}
}
