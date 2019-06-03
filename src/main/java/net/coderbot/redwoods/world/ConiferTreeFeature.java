package net.coderbot.redwoods.world;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableIntBoundingBox;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class ConiferTreeFeature extends AbstractTreeFeature<DefaultFeatureConfig> {
	private BlockState wood;
	private BlockState leaves;

	private boolean doBlockNotify;

	public ConiferTreeFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> function, boolean notify, BlockState wood, BlockState leaves) {
		super(function, notify);
		this.doBlockNotify = notify;

		this.wood = wood;
		this.leaves = leaves;
	}

	@Override
	public boolean generate(Set<BlockPos> blocks, ModifiableTestableWorld world, Random rand, BlockPos origin, MutableIntBoundingBox boundingBox) {
		// Total trunk height
		int height = rand.nextInt(8) + 24;

		// How much "bare trunk" there will be.
		int bareTrunkHeight = 1 + rand.nextInt(12);

		// Maximum leaf radius.
		int maxRadius = 2 + rand.nextInt(6);

		if(origin.getY() + height + 1 > 256 || origin.getY() < 1) {
			return false;
		}

		BlockPos below = origin.down();

		if(!isNaturalDirtOrGrass(world, below)) {
			return false;
		}

		if(!checkForObstructions(world, origin, height, bareTrunkHeight, maxRadius)) {
			return false;
		}

		// TODO: Leaves Decay
		/*boolean oldAllowLeavesDecay = BlockConiferLeaves.allowLeavesDecay;
		if(!doBlockNotify) {
			BlockConiferLeaves.allowLeavesDecay = false;
		}*/

		setBlockState(blocks, world, origin.down(), Blocks.DIRT.getDefaultState(), boundingBox);
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
			if(!canTreeReplace(world, pos.setOffset(Direction.UP))) {
				return false;
			}
		}

		for(int dY = bareTrunkHeight; dY < height; dY++) {
			for(int dZ = -radius; dZ <= radius; dZ++) {
				for(int dX = -radius; dX <= radius; dX++) {
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

		for(int dY = height; dY >= bareTrunkHeight; dY--) {
			for(int dZ = -radius; dZ <= radius; dZ++) {
				for(int dX = -radius; dX <= radius; dX++) {
					if(radius > 0 && Math.abs(dZ) == radius && Math.abs(dX) == radius) {
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

	private void growTrunk(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable pos, int height, MutableIntBoundingBox boundingBox) {
		for(int i = 0; i < height; i++) {
			setBlockState(blocks, world, pos, wood, boundingBox);

			pos.setOffset(Direction.UP);
		}
	}
}
