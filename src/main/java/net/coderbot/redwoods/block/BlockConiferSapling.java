package net.coderbot.redwoods.block;

import net.coderbot.redwoods.world.WorldGenConifer;
import net.coderbot.redwoods.world.WorldGenMegaConifer;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.event.terraingen.TerrainGen;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;

@ParametersAreNonnullByDefault
public class BlockConiferSapling extends BlockBush implements IGrowable {
	public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 1);
	protected static final AxisAlignedBB SAPLING_AABB = new AxisAlignedBB(0.1, 0.0, 0.1, 0.9, 0.8, 0.9);

	private WorldGenConifer generator;
	private WorldGenMegaConifer generatorMega;

	public BlockConiferSapling(TreeDefinition tree) {
		super();

		this.setDefaultState(this.blockState.getBaseState().withProperty(STAGE, 0));
		setSoundType(SoundType.PLANT);
		setHardness(0.0F);

		generator = new WorldGenConifer(true, tree.wood, tree.leaves);
		generatorMega = new WorldGenMegaConifer(true, tree.woodSW, tree.woodNW, tree.woodNE, tree.woodSE, tree.leaves);
	}

	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		return SAPLING_AABB;
	}

	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
	{
		if (!worldIn.isRemote)
		{
			super.updateTick(worldIn, pos, state, rand);

			if (!worldIn.isAreaLoaded(pos, 1)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light
			if (worldIn.getLightFromNeighbors(pos.up()) >= 9 && rand.nextInt(7) == 0)
			{
				this.grow(worldIn, pos, state, rand);
			}
		}
	}

	public void grow(World worldIn, BlockPos pos, IBlockState state, Random rand)
	{
		if (state.getValue(STAGE) == 0)
		{
			worldIn.setBlockState(pos, state.cycleProperty(STAGE), 4);
		}
		else
		{
			this.generateTree(worldIn, pos, state, rand);
		}
	}

	public void generateTree(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (!TerrainGen.saplingGrowTree(worldIn, rand, pos)) return;

		int dX = 0;
		int dZ;
		boolean mega = false;

		outer:
		for(dZ = 0; dZ >= -1; dZ--) {
			for(dX = 0; dX >= -1; dX--) {
				if(isTwoByTwoOfType(worldIn, pos, dX, dZ)) {
					mega = true;
					break outer;
				}
			}
		}

		if(!mega) {
			worldIn.setBlockToAir(pos);

			if(!generator.generate(worldIn, rand, pos)) {
				worldIn.setBlockState(pos, this.getDefaultState().withProperty(STAGE, 1));
			}
		} else {
			IBlockState[][] oldStates = new IBlockState[2][2];

			BlockPos generatePos = pos.add(dX, 0, dZ);

			for(dZ = 0; dZ < 2; dZ++) {
				for(dX = 0; dX < 2; dX++) {
					BlockPos sapling = generatePos.add(dX, 0, dZ);

					oldStates[dZ][dX] = worldIn.getBlockState(sapling);
					worldIn.setBlockToAir(sapling);
				}
			}

			if(!generatorMega.generate(worldIn, rand, generatePos)) {
				for(dZ = 0; dZ < 2; dZ++) {
					for(dX = 0; dX < 2; dX++) {
						BlockPos sapling = generatePos.add(dX, 0, dZ);

						worldIn.setBlockState(sapling, oldStates[dZ][dX]);
					}
				}
			}
		}
	}

	private boolean isTwoByTwoOfType(World worldIn, BlockPos pos, int dX, int dZ)
	{
		return this.isTypeAt(worldIn, pos.add(dX, 0, dZ))
				&& this.isTypeAt(worldIn, pos.add(dX + 1, 0, dZ))
				&& this.isTypeAt(worldIn, pos.add(dX, 0, dZ + 1))
				&& this.isTypeAt(worldIn, pos.add(dX + 1, 0, dZ + 1));
	}

	private boolean isTypeAt(World worldIn, BlockPos pos)
	{
		return worldIn.getBlockState(pos).getBlock() == this;
	}

	public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient)
	{
		return true;
	}

	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state)
	{
		return (double)worldIn.rand.nextFloat() < 0.45D;
	}

	public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state)
	{
		this.grow(worldIn, pos, state, rand);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(STAGE, (meta & 8) >> 3);
	}

	public int getMetaFromState(IBlockState state)
	{
		return state.getValue(STAGE) << 3;
	}

	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, STAGE);
	}

	public static class TreeDefinition {
		public IBlockState wood;
		public IBlockState woodSW;
		public IBlockState woodNW;
		public IBlockState woodNE;
		public IBlockState woodSE;
		public IBlockState leaves;
	}
}
