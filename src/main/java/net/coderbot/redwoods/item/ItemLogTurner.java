package net.coderbot.redwoods.item;

import net.coderbot.redwoods.block.BlockQuarterLog;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.List;

public class ItemLogTurner extends Item {
	public ItemLogTurner(Item.Settings settings) {
		super(settings);
	}

	public ActionResult useOnBlock(ItemUsageContext context) {
		BlockPos pos = context.getBlockPos();
		World world = context.getWorld();

		BlockState state = world.getBlockState(pos);

		if(!(state.getBlock() instanceof PillarBlock)) {
			return ActionResult.PASS;
		}

		Direction.Axis currentAxis = state.get(PillarBlock.AXIS);

		if(context.getPlayer() != null && context.getPlayer().isSneaking()) {
			if(state.getBlock() instanceof BlockQuarterLog) {
				BlockQuarterLog.BarkSide cycled = cycleBarkSide(state.get(BlockQuarterLog.BARK_SIDE));
				Direction.Axis newAxis = currentAxis;

				// First cycle the bark side. If we return to the start, then cycle the axis too.
				if(cycled == BlockQuarterLog.BarkSide.SOUTHWEST) {
					newAxis = cycleAxis(currentAxis);
				}

				world.setBlockState(pos, state
						.with(PillarBlock.AXIS, newAxis)
						.with(BlockQuarterLog.BARK_SIDE, cycled)
				);

				return ActionResult.SUCCESS;
			} else {
				world.setBlockState(pos, state.with(PillarBlock.AXIS, cycleAxis(currentAxis)));

				return ActionResult.SUCCESS;
			}
		} else {
			Direction.Axis newAxis = context.getFacing().getAxis();

			if(currentAxis != newAxis) {
				world.setBlockState(pos, state.with(PillarBlock.AXIS, newAxis));

				return ActionResult.SUCCESS;
			}
		}

		if(state.getBlock() instanceof BlockQuarterLog) {
			world.setBlockState(pos, state.cycle(BlockQuarterLog.BARK_SIDE));

			return ActionResult.SUCCESS;
		} else {
			return ActionResult.PASS;
		}
	}

	private static Direction.Axis cycleAxis(Direction.Axis axis) {
		switch(axis) {
			case X: return Direction.Axis.Y;
			case Y: return Direction.Axis.Z;
			default: return Direction.Axis.X;
		}
	}

	private static BlockQuarterLog.BarkSide cycleBarkSide(BlockQuarterLog.BarkSide side) {
		switch (side) {
			case SOUTHWEST: return BlockQuarterLog.BarkSide.NORTHWEST;
			case NORTHWEST: return BlockQuarterLog.BarkSide.NORTHEAST;
			case NORTHEAST: return BlockQuarterLog.BarkSide.SOUTHEAST;
			default: return BlockQuarterLog.BarkSide.SOUTHWEST;
		}
	}

	@Environment(EnvType.CLIENT)
	public void buildTooltip(ItemStack stack, /*TODO: @Nullable*/ World world, List<Component> tooltip, TooltipContext context) {
		super.buildTooltip(stack, world, tooltip, context);

		// TODO: Use TranslatableComponent
		tooltip.add(new TextComponent("Use on a log to instantly set its axis to the side you clicked"));
		tooltip.add(new TextComponent("When used on a quarter log, it will try to set the axis, and if it already matches, it will then rotate the bark side clockwise"));
		tooltip.add(new TextComponent("When used while sneaking, it will simply cycle the axis and bark side"));
	}
}
