package net.coderbot.redwoods.world;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableIntBoundingBox;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class MegaConiferTreeFeature extends AbstractTreeFeature<DefaultFeatureConfig> {
	private BlockState woodSW;
	private BlockState woodNW;
	private BlockState woodNE;
	private BlockState woodSE;
	private BlockState leaves;

	private boolean doBlockNotify;

	private static final int EXTRA_LEAVES_HEIGHT = 2;

	public MegaConiferTreeFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> function, boolean notify, BlockState woodSW, BlockState woodNW, BlockState woodNE, BlockState woodSE, BlockState leaves) {
		super(function, notify);
		this.doBlockNotify = notify;

		this.woodSW = woodSW;
		this.woodNW = woodNW;
		this.woodNE = woodNE;
		this.woodSE = woodSE;
		this.leaves = leaves;
	}

	@Override
	public boolean generate(Set<BlockPos> blocks, ModifiableTestableWorld world, Random rand, BlockPos origin, MutableIntBoundingBox boundingBox) {
		// Total trunk height
		int height = rand.nextInt(16) + 32;

		// How much "bare trunk" there will be.
		int bareTrunkHeight = 1 + rand.nextInt(12);

		// Maximum leaf radius.
		// Note: Old EBXL had a maximum radius of 10, but unfortunately that would cause cascading world generation.
		// Hey, the trees are pretty massive already.
		int maxRadius = 2 + rand.nextInt(6);

		if(origin.getY() + height + 1 + EXTRA_LEAVES_HEIGHT > 256 || origin.getY() < 1) {
			return false;
		}

		for(int dZ = 0; dZ < 2; dZ++) {
			for(int dX = 0; dX < 2; dX++) {
				BlockPos below = origin.add(dX, -1, dZ);

				if(!isNaturalDirtOrGrass(world, below)) {
					return false;
				}
			}
		}

		if(!checkForObstructions(world, origin, height, bareTrunkHeight, maxRadius)) {
			return false;
		}

		for(int dZ = 0; dZ < 2; dZ++) {
			for(int dX = 0; dX < 2; dX++) {
				BlockPos below = origin.add(dX, -1, dZ);

				setBlockState(blocks, world, below, Blocks.DIRT.getDefaultState(), boundingBox);
			}
		}

		// TODO: Leaves Decay
		/*boolean oldAllowLeavesDecay = BlockConiferLeaves.allowLeavesDecay;
		if(!doBlockNotify) {
			BlockConiferLeaves.allowLeavesDecay = false;
		}*/

		growLeaves(blocks, world, origin, height, bareTrunkHeight, maxRadius, boundingBox);
		growTrunk(blocks, world, new BlockPos.Mutable(origin), height, boundingBox);

		/*if(!doBlockNotify) {
			BlockConiferLeaves.allowLeavesDecay = oldAllowLeavesDecay;
		}*/

		return true;
	}

	private boolean checkForObstructions(TestableWorld world, BlockPos origin, int height, int bareTrunkHeight, int radius) {
		BlockPos.Mutable pos = new BlockPos.Mutable(origin);

		for(int i = 0; i < bareTrunkHeight; i++) {
			boolean canReplaceAll =
					canTreeReplace(world, pos.set(origin.getX(), origin.getY() + i, origin.getZ())) &&
					canTreeReplace(world, pos.set(origin.getX() + 1, origin.getY() + i, origin.getZ())) &&
					canTreeReplace(world, pos.set(origin.getX(), origin.getY() + i, origin.getZ() + 1)) &&
					canTreeReplace(world, pos.set(origin.getX() + 1, origin.getY() + i, origin.getZ() + 1));

			if(!canReplaceAll) {
				return false;
			}
		}

		for(int dY = bareTrunkHeight; dY < height + EXTRA_LEAVES_HEIGHT; dY++) {
			for(int dZ = -radius; dZ <= radius + 1; dZ++) {
				for(int dX = -radius; dX <= radius + 1; dX++) {
					pos.set(origin.getX() + dX, origin.getY() + dY, origin.getZ() + dZ);

					if(!canTreeReplace(world, pos)) {
						return false;
					}
				}
			}
		}

		return true;
	}

	private void growLeaves(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos origin, int height, int bareTrunkHeight, int maxRadius, MutableIntBoundingBox boundingBox) {
		int radius = 0;
		int radiusTarget = 1;
		boolean topCone = true;

		BlockPos.Mutable pos = new BlockPos.Mutable(origin);

		for(int dY = height + EXTRA_LEAVES_HEIGHT; dY >= bareTrunkHeight; dY--) {
			for(int dZ = -radius; dZ <= radius + 1; dZ++) {
				for(int dX = -radius; dX <= radius + 1; dX++) {
					if(radius > 0 && (dZ == -radius || dZ == radius + 1) && (dX == -radius || dX == radius + 1)) {
						// Cull corners
						continue;
					}

					pos.set(origin.getX() + dX, origin.getY() + dY, origin.getZ() + dZ);

					if(AbstractTreeFeature.isAirOrLeaves(world, pos)) {
						setBlockState(blocks, world, pos, leaves, boundingBox);
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

	private void growTrunk(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable origin, int height, MutableIntBoundingBox boundingBox) {
		BlockPos.Mutable pos = new BlockPos.Mutable(origin);

		for(int i = 0; i < height; i++) {
			this.setBlockState(blocks, world, pos.set(origin.getX(), origin.getY() + i, origin.getZ()), woodNW, boundingBox);
			this.setBlockState(blocks, world, pos.set(origin.getX() + 1, origin.getY() + i, origin.getZ()), woodNE, boundingBox);
			this.setBlockState(blocks, world, pos.set(origin.getX(), origin.getY() + i, origin.getZ() + 1), woodSW, boundingBox);
			this.setBlockState(blocks, world, pos.set(origin.getX() + 1, origin.getY() + i, origin.getZ() + 1), woodSE, boundingBox);
		}
	}
}
