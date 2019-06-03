package net.coderbot.redwoods.block;

import net.minecraft.block.Block;
import net.minecraft.block.LogBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.MaterialColor;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.math.Direction;

public class BlockQuarterLog extends LogBlock {
	public static final EnumProperty<BarkSide> BARK_SIDE = EnumProperty.create("bark_side", BarkSide.class);

	public BlockQuarterLog(Block.Settings settings) {
		super(MaterialColor.BROWN, settings);
	}

	@Override
	protected void appendProperties(StateFactory.Builder<Block, BlockState> builder) {
		super.appendProperties(builder);

		builder.add(BARK_SIDE);
	}

	public BlockState getPlacementState(ItemPlacementContext context) {
		// BarkSide side = BarkSide.fromHit(context.getPlayerFacing().getAxis());

		// TODO BarkSide side = BarkSide.fromHit(EnumAxis.fromFacingAxis(facing.getAxis()), hitX, hitY, hitZ);

		// return super.getPlacementState(context).with(BARK_SIDE, side);

		throw new UnsupportedOperationException("TODO: getPlacementState");
	}

	public enum BarkSide implements StringIdentifiable {
		SOUTHWEST("southwest"),
		NORTHWEST("northwest"),
		NORTHEAST("northeast"),
		SOUTHEAST("southeast");

		final String name;

		BarkSide(String name) {
			this.name = name;
		}

		public static BarkSide fromHit(Direction.Axis axis, float hitX, float hitY, float hitZ) {
			boolean hitEast;
			boolean hitSouth;

			switch(axis) {
				case Y:
					hitEast = hitX >= 0.5;
					hitSouth = hitZ >= 0.5;
					break;
				case X:
					hitEast = hitY <= 0.5;
					hitSouth = hitZ >= 0.5;
					break;
				default:
					hitEast = hitX >= 0.5;
					hitSouth = hitY >= 0.5;
					break;
			}

			// Logic of placement: The quadrant the player clicks on should be the one farthest from the bark sides.
			return BarkSide.fromHalves(!hitEast, !hitSouth);
		}

		public static BarkSide fromHalves(boolean east, boolean south) {
			if (east) {
				if (south) {
					return BarkSide.SOUTHEAST;
				} else {
					return BarkSide.NORTHEAST;
				}
			} else {
				if (south) {
					return BarkSide.SOUTHWEST;
				} else {
					return BarkSide.NORTHWEST;
				}
			}
		}

		public String toString() {
			return this.name;
		}

		public String asString() {
			return this.name;
		}
	}
}
