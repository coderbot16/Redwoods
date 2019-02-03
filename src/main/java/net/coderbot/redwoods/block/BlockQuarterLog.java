package net.coderbot.redwoods.block;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.BlockLog;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

@MethodsReturnNonnullByDefault
public class BlockQuarterLog extends BlockLog {
	public static final PropertyEnum<BarkSide> BARK_SIDE = PropertyEnum.create("bark_side", BarkSide.class);

	public BlockQuarterLog() {
		super();
	}

	@Override
	public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return MapColor.OBSIDIAN;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		EnumAxis axis;
		BarkSide side;

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

		switch(meta & 3) {
			case 0:
				side = BarkSide.SOUTHWEST;
				break;
			case 1:
				side = BarkSide.NORTHWEST;
				break;
			case 2:
				side = BarkSide.NORTHEAST;
				break;
			default:
				side = BarkSide.SOUTHEAST;
		}

		return this.getDefaultState().withProperty(LOG_AXIS, axis).withProperty(BARK_SIDE, side);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return (state.getValue(LOG_AXIS).ordinal() << 2) | state.getValue(BARK_SIDE).ordinal();
	}

	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, LOG_AXIS, BARK_SIDE);
	}

	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		BarkSide side = BarkSide.SOUTHWEST;

		// TODO: Decide side based on quadrant of hit.

		return super.getStateForPlacement(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer).withProperty(BARK_SIDE, side);
	}

	public enum BarkSide implements IStringSerializable {
		SOUTHWEST("southwest"),
		NORTHWEST("northwest"),
		NORTHEAST("northeast"),
		SOUTHEAST("southeast");

		final String name;

		BarkSide(String name) {
			this.name = name;
		}

		public String toString() {
			return this.name;
		}

		public String getName() {
			return this.name;
		}
	}
}
