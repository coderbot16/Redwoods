package net.coderbot.redwoods.world;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.IPlantable;

import java.util.Random;

public class WorldGenConifer extends WorldGenAbstractTree {
	private IBlockState wood;
	private IBlockState leaves;

	public WorldGenConifer(boolean notify, IBlockState wood, IBlockState leaves) {
		super(notify);

		this.wood = wood;
		this.leaves = leaves;
	}

	public boolean generate(World world, Random rand, BlockPos origin) {
		// Total trunk height
		int height = rand.nextInt(8) + 24;

		// How much "bare trunk" there will be.
		int bareTrunkHeight = 1 + rand.nextInt(12);

		// Maximum leaf radius.
		int maxRadius = 2 + rand.nextInt(6);

		if(origin.getY() + height + 1 > world.getHeight() || origin.getY() < 1) {
			return false;
		}

		BlockPos below = origin.down();
		IBlockState soil = world.getBlockState(below);

		if(!soil.getBlock().canSustainPlant(soil, world, below, EnumFacing.UP, (IPlantable)Blocks.SAPLING)) {
			return false;
		}

		if(!checkForObstructions(world, origin, height, bareTrunkHeight, maxRadius)) {
			return false;
		}

		setBlockAndNotifyAdequately(world, origin.down(), Blocks.DIRT.getDefaultState());
		growLeaves(world, origin, height, bareTrunkHeight, maxRadius);
		growTrunk(world, new BlockPos.MutableBlockPos(origin), height);

		return true;
	}

	private boolean checkForObstructions(World world, BlockPos origin, int height, int bareTrunkHeight, int radius) {
		BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos(origin);

		for(int i = 0; i < bareTrunkHeight; i++) {
			IBlockState state = world.getBlockState(pos.move(EnumFacing.UP));

			if(!canReplaceBlock(world, state, pos)) {
				return false;
			}
		}

		for(int dY = bareTrunkHeight; dY < height; dY++) {
			for(int dZ = -radius; dZ <= radius; dZ++) {
				for(int dX = -radius; dX <= radius; dX++) {
					pos.setPos(origin.getX() + dX, origin.getY() + dY, origin.getZ() + dZ);

					IBlockState state = world.getBlockState(pos);
					if(!canReplaceBlock(world, state, pos)) {
						return false;
					}
				}
			}
		}

		return true;
	}

	private void growLeaves(World world, BlockPos origin, int height, int bareTrunkHeight, int maxRadius) {
		int radius = 0;
		int radiusTarget = 1;
		boolean topCone = true;

		BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos(origin);

		for(int dY = height; dY >= bareTrunkHeight; dY--) {
			for(int dZ = -radius; dZ <= radius; dZ++) {
				for(int dX = -radius; dX <= radius; dX++) {
					if(radius > 0 && Math.abs(dZ) == radius && Math.abs(dX) == radius) {
						// Cull corners
						continue;
					}

					pos.setPos(origin.getX() + dX, origin.getY() + dY, origin.getZ() + dZ);

					IBlockState existing = world.getBlockState(pos);
					if(existing.getBlock().canBeReplacedByLeaves(existing, world, pos)) {
						setBlockAndNotifyAdequately(world, pos, leaves);
					}
				}
			}

			radius += 1;

			if(radius > radiusTarget) {
				if(topCone) {
					radius = 0;
					radiusTarget = Math.min(2, maxRadius);
					topCone = false;
				} else {
					radius = 1;
					radiusTarget = Math.min(radiusTarget + 1, maxRadius);
				}
			}
		}
	}

	private void growTrunk(World world, BlockPos.MutableBlockPos pos, int height) {
		for(int i = 0; i < height; i++) {
			setBlockAndNotifyAdequately(world, pos, wood);

			pos.move(EnumFacing.UP);
		}
	}

	private static boolean canReplaceBlock(World world, IBlockState state, BlockPos pos) {
		Block block = state.getBlock();

		if(block.isAir(state, world, pos) || block.isLeaves(state, world, pos) || block.isWood(world, pos)) {
			return true;
		}

		Material material = state.getMaterial();
		if(material == Material.AIR || material == Material.LEAVES) {
			return true;
		}

		return block == Blocks.GRASS || block == Blocks.DIRT || block == Blocks.LOG || block == Blocks.LOG2 || block == Blocks.SAPLING || block == Blocks.VINE;
	}
}
