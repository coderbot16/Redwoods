package net.coderbot.redwoods.block;

import net.coderbot.redwoods.RedwoodsConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.BitSet;
import java.util.Random;

public class BlockConiferLeaves extends BlockLeaves {
	private Item sapling;

	public BlockConiferLeaves() {
		super();

		this.sapling = sapling;
		this.setDefaultState(this.blockState.getBaseState().withProperty(CHECK_DECAY, true).withProperty(DECAYABLE, true));

		this.setLightOpacity(RedwoodsConfig.leavesDiffuseSkylight ? 1 : 0);
	}

	public void setSapling(Item sapling) {
		this.sapling = sapling;
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		return super.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, meta, placer, hand).withProperty(DECAYABLE, false);
	}

	protected void dropApple(World worldIn, BlockPos pos, IBlockState state, int chance) {}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(DECAYABLE, (meta & 4) == 0).withProperty(CHECK_DECAY, (meta & 8) > 0);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;

		if (!state.getValue(DECAYABLE))
		{
			i = 4;
		}

		if (state.getValue(CHECK_DECAY))
		{
			i |= 8;
		}

		return i;
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		if(world.isRemote) {
			return;
		}

		if(!state.getValue(CHECK_DECAY) || !state.getValue(DECAYABLE)) {
			return;
		}

		if(DecayAlgorithm.canStay(world, pos)) {
			world.setBlockState(pos, world.getBlockState(pos).withProperty(CHECK_DECAY, false));
		} else {
			this.dropBlockAsItem(world, pos, world.getBlockState(pos), 0);
			world.setBlockToAir(pos);
		}
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, CHECK_DECAY, DECAYABLE);
	}

	@Override
	public BlockPlanks.EnumType getWoodType(int meta) {
		return BlockPlanks.EnumType.SPRUCE;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return sapling;
	}

	@Override
	public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack)
	{
		if (!worldIn.isRemote && stack.getItem() == Items.SHEARS)
		{
			player.addStat(StatList.getBlockStats(this));
			spawnAsEntity(worldIn, pos, new ItemStack(this));
		}
		else
		{
			super.harvestBlock(worldIn, player, pos, state, te, stack);
		}
	}

	@Override
	public NonNullList<ItemStack> onSheared(ItemStack item, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune)
	{
		return NonNullList.withSize(1, new ItemStack(this));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return Blocks.LEAVES.getBlockLayer();
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return Blocks.LEAVES.isOpaqueCube(state);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		if (!Minecraft.getMinecraft().gameSettings.fancyGraphics || RedwoodsConfig.useOptiLeaves) {
			return !(blockAccess.getBlockState(pos.offset(side)).getBlock() instanceof BlockConiferLeaves);
		} else {
			return true;
		}
	}

	// Set to false during worldgen to prevent massive cascading and block update lag.
	public static boolean allowLeavesDecay = true;

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
	{
		if(!allowLeavesDecay) {
			return;
		}

		if (worldIn.isAreaLoaded(pos.add(-5, -5, -5), pos.add(5, 5, 5)))
		{
			for (BlockPos blockpos : BlockPos.getAllInBox(pos.add(-4, -4, -4), pos.add(4, 4, 4)))
			{
				IBlockState iblockstate = worldIn.getBlockState(blockpos);

				if (iblockstate.getBlock().isLeaves(iblockstate, worldIn, blockpos))
				{
					iblockstate.getBlock().beginLeavesDecay(iblockstate, worldIn, blockpos);
				}
			}
		}
	}

	/**
	 * Meant to mimic the behavior of vanilla leaf decay in {@link BlockLeaves#updateTick(World, BlockPos, IBlockState, Random)}
	 * However, the algorithm has been revamped to be far more performant at its scale, going from O(radius^4) to O(radius^3)
	 * In other words, decay checks for conifer leaves won't kill your TPS as much as it would with the naive vanilla algorithm.
	 *
	 * Performance data: This appears to take around 40-50μs to check a single leaf block on an i7-4790.
	 */
	private static class DecayAlgorithm {
		private static final int FIELD_SIZE = 32;
		private static final int CENTER_OFFSET = FIELD_SIZE / 2;
		private static final byte MARKER_LEAVES = -1;
		private static final byte MARKER_IGNORE = -2;
		private static final int SCAN_RADIUS = 8;
		private static final int ITERATIONS = 13;

		private static final byte[] CELLS = new byte[FIELD_SIZE * FIELD_SIZE * FIELD_SIZE];
		private static BitSet fillingQueue = new BitSet(CELLS.length);
		private static BitSet drainingQueue = new BitSet(CELLS.length);

		private static int at(int dX, int dY, int dZ) {
			int aX = dX + CENTER_OFFSET;
			int aY = dY + CENTER_OFFSET;
			int aZ = dZ + CENTER_OFFSET;

			return aY * FIELD_SIZE * FIELD_SIZE + aZ * FIELD_SIZE + aX;
		}

		private static int atX(int index) {
			return (index % FIELD_SIZE) - CENTER_OFFSET;
		}

		private static int atZ(int index) {
			return ((index / FIELD_SIZE) % FIELD_SIZE) - CENTER_OFFSET;
		}

		private static int atY(int index) {
			return ((index / FIELD_SIZE) / FIELD_SIZE) - CENTER_OFFSET;
		}

		private static void markNeighbors(int dX, int dY, int dZ, BitSet queue) {
			queue.set(at(dX + 1, dY, dZ));
			queue.set(at(dX - 1, dY, dZ));
			queue.set(at(dX, dY + 1, dZ));
			queue.set(at(dX, dY - 1, dZ));
			queue.set(at(dX, dY, dZ + 1));
			queue.set(at(dX, dY, dZ - 1));
		}

		private static byte maximumNeighbor(int dX, int dY, int dZ) {
			return (byte)Math.max(
					Math.max(
							Math.max(
									CELLS[at(dX + 1, dY, dZ)],
									CELLS[at(dX - 1, dY, dZ)]
							),
							Math.max(
									CELLS[at(dX, dY + 1, dZ)],
									CELLS[at(dX, dY - 1, dZ)]
							)
					),
					Math.max(
							CELLS[at(dX, dY, dZ + 1)],
							CELLS[at(dX, dY, dZ - 1)]
					)
			);
		}

		static boolean canStay(World world, BlockPos origin) {
			// Make sure that we don't inadvertently load chunks!
			if (!world.isAreaLoaded(origin, SCAN_RADIUS + 2)) {
				return true;
			}

			BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

			fillingQueue.clear();
			drainingQueue.clear();

			// For each block in the possible radius:
			for (int dY = -SCAN_RADIUS; dY <= SCAN_RADIUS; ++dY) {
				for (int dZ = -SCAN_RADIUS; dZ <= SCAN_RADIUS; ++dZ) {
					for (int dX = -SCAN_RADIUS; dX <= SCAN_RADIUS; ++dX) {
						pos.setPos(origin.getX() + dX, origin.getY() + dY, origin.getZ() + dZ);

						IBlockState state = world.getBlockState(pos);
						Block block = state.getBlock();

						byte light;

						// Check if the block is leaves or can sustain leaves. Otherwise, disregard it.
						if (block.canSustainLeaves(state, world, pos)) {
							light = ITERATIONS;
							markNeighbors(dX, dY, dZ, fillingQueue);
						} else if(block.isLeaves(state, world, pos)) {
							light = MARKER_LEAVES;
						} else {
							light = MARKER_IGNORE;
						}

						CELLS[at(dX, dY, dZ)] = light;
					}
				}
			}

			// Propagate the distance values just like in a lighting algorithm.
			for (byte distance = 1; distance <= ITERATIONS; ++distance) {
				{
					// Swap the queues.
					BitSet temp = drainingQueue;
					drainingQueue = fillingQueue;
					fillingQueue = temp;
				}

				int drainIndex = 0;
				while(!drainingQueue.isEmpty()) {
					drainIndex = drainingQueue.nextSetBit(drainIndex);
					drainingQueue.clear(drainIndex);

					if (CELLS[drainIndex] != MARKER_LEAVES) {
						continue;
					}

					int dX = atX(drainIndex);
					int dY = atY(drainIndex);
					int dZ = atZ(drainIndex);

					int potentialValue = maximumNeighbor(dX, dY, dZ) - 1;
					if (potentialValue >= 0) {
						CELLS[drainIndex] = (byte) potentialValue;
						markNeighbors(dX, dY, dZ, fillingQueue);
					}
				}
			}

			// Finally: check if we got sustained
			return CELLS[at(0, 0, 0)] >= 0;
		}
	}
}
