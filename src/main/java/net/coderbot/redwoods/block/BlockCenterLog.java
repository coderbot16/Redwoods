package net.coderbot.redwoods.block;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.BlockLog;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

@MethodsReturnNonnullByDefault
public class BlockCenterLog extends BlockLog {
	private BlockQuarterLog quarter;

	public BlockCenterLog() {
		super();
	}

	public void setQuarter(BlockQuarterLog quarter) {
		this.quarter = quarter;
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack heldStack = playerIn.getHeldItem(hand);
		if(heldStack.isEmpty()) {
			return false;
		}

		Item held = heldStack.getItem();
		if(!(held instanceof ItemTool)) {
			return false;
		}

		ItemTool tool = (ItemTool) held;

		if(tool.getToolClasses(heldStack).contains("axe") && tool.getDestroySpeed(heldStack, state) > 1.0) {
			worldIn.setBlockState(pos, this.removeBark(state, pos, facing, hitX, hitY, hitZ));

			tool.onBlockDestroyed(heldStack, worldIn, state, pos, playerIn);

			return true;
		}

		return false;
	}

	public IBlockState removeBark(IBlockState existing, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ) {
		BlockQuarterLog.BarkSide side = BlockQuarterLog.BarkSide.fromHit(existing.getValue(LOG_AXIS), hitX, hitY, hitZ);

		return quarter.getDefaultState()
				.withProperty(BlockQuarterLog.BARK_SIDE, side)
				.withProperty(LOG_AXIS, existing.getValue(LOG_AXIS));
	}

	@Override
	public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return MapColor.OBSIDIAN;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		EnumAxis axis;

		switch(meta >> 2) {
			case 0:
				axis = EnumAxis.X;
				break;
			case 1:
				axis = EnumAxis.Y;
				break;
			case 2:
				axis = EnumAxis.Z;
				break;
			default:
				axis = EnumAxis.NONE;
		}

		return this.getDefaultState().withProperty(LOG_AXIS, axis);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(LOG_AXIS).ordinal() << 2;
	}

	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, LOG_AXIS);
	}
}
