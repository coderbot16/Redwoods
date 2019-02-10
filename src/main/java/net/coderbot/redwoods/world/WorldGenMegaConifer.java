package net.coderbot.redwoods.world;

import net.coderbot.redwoods.block.BlockConiferLeaves;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.IPlantable;

import java.util.Random;

public class WorldGenMegaConifer extends WorldGenAbstractTree {
	private IBlockState woodSW;
	private IBlockState woodNW;
	private IBlockState woodNE;
	private IBlockState woodSE;
	private IBlockState leaves;

	private boolean doBlockNotify;

	public WorldGenMegaConifer(boolean notify, IBlockState woodSW, IBlockState woodNW, IBlockState woodNE, IBlockState woodSE, IBlockState leaves) {
		super(notify);
		this.doBlockNotify = notify;

		this.woodSW = woodSW;
		this.woodNW = woodNW;
		this.woodNE = woodNE;
		this.woodSE = woodSE;
		this.leaves = leaves;
	}

	public boolean generate(World world, Random rand, BlockPos origin) {
		// Total trunk height
		int height = rand.nextInt(16) + 32;

		// How much "bare trunk" there will be.
		int bareTrunkHeight = 1 + rand.nextInt(12);

		// Maximum leaf radius.
		// Note: Old EBXL had a maximum radius of 10, but unfortunately that would cause cascading world generation.
		// Hey, the trees are pretty massive already.
		int maxRadius = 2 + rand.nextInt(6);

		if(origin.getY() + height + 1 > world.getHeight() || origin.getY() < 1) {
			return false;
		}

		for(int dZ = 0; dZ < 1; dZ++) {
			for(int dX = 0; dX < 1; dX++) {
				BlockPos below = origin.add(dX, -1, dZ);
				IBlockState soil = world.getBlockState(below);

				if(!soil.getBlock().canSustainPlant(soil, world, below, EnumFacing.UP, (IPlantable)Blocks.SAPLING)) {
					return false;
				}
			}
		}

		if(!checkForObstructions(world, origin, height, bareTrunkHeight, maxRadius)) {
			return false;
		}

		for(int dZ = 0; dZ < 1; dZ++) {
			for(int dX = 0; dX < 1; dX++) {
				BlockPos below = origin.add(dX, -1, dZ);

				setBlockAndNotifyAdequately(world, below, Blocks.DIRT.getDefaultState());
			}
		}

		boolean oldAllowLeavesDecay = BlockConiferLeaves.allowLeavesDecay;
		if(!doBlockNotify) {
			BlockConiferLeaves.allowLeavesDecay = false;
		}

		growLeaves(world, origin, height, bareTrunkHeight, maxRadius);
		growTrunk(world, origin, height);

		if(!doBlockNotify) {
			BlockConiferLeaves.allowLeavesDecay = oldAllowLeavesDecay;
		}

		return true;
	}

	private boolean checkForObstructions(World world, BlockPos origin, int height, int bareTrunkHeight, int radius) {
		BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos(origin);

		for(int i = 0; i < bareTrunkHeight; i++) {
			boolean canReplaceAll =
					canReplaceBlock(world, world.getBlockState(pos.setPos(origin.getX(), origin.getY() + i, origin.getZ())), pos) &&
					canReplaceBlock(world, world.getBlockState(pos.setPos(origin.getX() + 1, origin.getY() + i, origin.getZ())), pos) &&
					canReplaceBlock(world, world.getBlockState(pos.setPos(origin.getX(), origin.getY() + i, origin.getZ() + 1)), pos) &&
					canReplaceBlock(world, world.getBlockState(pos.setPos(origin.getX() + 1, origin.getY() + i, origin.getZ() + 1)), pos);

			if(!canReplaceAll) {
				return false;
			}
		}

		for(int dY = bareTrunkHeight; dY < height; dY++) {
			for(int dZ = -radius; dZ <= radius + 1; dZ++) {
				for(int dX = -radius; dX <= radius + 1; dX++) {
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
			for(int dZ = -radius; dZ <= radius + 1; dZ++) {
				for(int dX = -radius; dX <= radius + 1; dX++) {
					if(radius > 0 && (dZ == -radius || dZ == radius + 1) && (dX == -radius || dX == radius + 1)) {
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

	private void growTrunk(World world, BlockPos origin, int height) {
		BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos(origin);

		for(int i = 0; i < height; i++) {
			setBlockAndNotifyAdequately(world, pos.setPos(origin.getX(), origin.getY() + i, origin.getZ()), woodNW);
			setBlockAndNotifyAdequately(world, pos.setPos(origin.getX() + 1, origin.getY() + i, origin.getZ()), woodNE);
			setBlockAndNotifyAdequately(world, pos.setPos(origin.getX(), origin.getY() + i, origin.getZ() + 1), woodSW);
			setBlockAndNotifyAdequately(world, pos.setPos(origin.getX() + 1, origin.getY() + i, origin.getZ() + 1), woodSE);
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

	@Override
	protected void setBlockAndNotifyAdequately(World worldIn, BlockPos pos, IBlockState state)
	{
		if (this.doBlockNotify)
		{
			worldIn.setBlockState(pos, state, 3);
		}
		else
		{
			// Don't notify neighbors, don't load adjacent chunks.
			// The leaves will be just fine.

			worldIn.setBlockState(pos, state, 2 | 16);
		}
	}
}
