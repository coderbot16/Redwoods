package net.coderbot.redwoods.block;

import net.minecraft.block.Block;

public class BlockConiferDoubleSlab extends BlockConiferSlab {
	public BlockConiferDoubleSlab() {
		super();
	}

	public BlockConiferDoubleSlab(Block blockDropped)
	{
		super(blockDropped);
	}

	@Override
	public boolean isDouble() {
		return true;
	}
}
