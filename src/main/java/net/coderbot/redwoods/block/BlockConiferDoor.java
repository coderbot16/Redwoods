package net.coderbot.redwoods.block;

import net.minecraft.block.BlockDoor;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockConiferDoor extends BlockDoor {
	public BlockConiferDoor() {
		super(Material.WOOD);
		setSoundType(SoundType.WOOD);
	}
}
