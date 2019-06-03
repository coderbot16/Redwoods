package net.coderbot.redwoods.block;

import net.minecraft.block.Block;
import net.minecraft.block.SaplingBlock;

public class ConiferSaplingBlock extends SaplingBlock {
	public ConiferSaplingBlock(ConiferSaplingGenerator.TreeDefinition definition, Block.Settings block) {
		super(new ConiferSaplingGenerator(definition), block);
	}
}
