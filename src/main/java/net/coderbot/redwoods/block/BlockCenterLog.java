package net.coderbot.redwoods.block;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.BlockLog;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

@MethodsReturnNonnullByDefault
public class BlockCenterLog extends BlockLog {

	public BlockCenterLog() {
		super();
	}

	@Override
	public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return MapColor.OBSIDIAN;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		EnumAxis axis;

		switch(meta & 3) {
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
		return state.getValue(LOG_AXIS).ordinal();
	}

	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, LOG_AXIS);
	}
}
