package net.coderbot.redwoods.block;

import net.minecraft.block.BlockDoor;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class BlockConiferDoor extends BlockDoor {
	private Item item;

	public BlockConiferDoor() {
		super(Material.WOOD);
		setSoundType(SoundType.WOOD);
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return state.getValue(HALF) == BlockDoor.EnumDoorHalf.UPPER ? Items.AIR : item;
	}

	public ItemStack getItem(World world, BlockPos pos, IBlockState state) {
		return new ItemStack(this.item);
	}
}
