package net.coderbot.redwoods.item;

import net.coderbot.redwoods.block.BlockQuarterLog;
import net.minecraft.block.BlockLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class ItemLogTurner extends Item {
	public ItemLogTurner() {
		super();
	}

	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		IBlockState state = world.getBlockState(pos);

		if(!(state.getBlock() instanceof BlockLog)) {
			return EnumActionResult.PASS;
		}

		BlockLog.EnumAxis currentAxis = state.getValue(BlockLog.LOG_AXIS);

		if(player.isSneaking()) {
			if(state.getBlock() instanceof BlockQuarterLog) {
				BlockQuarterLog.BarkSide cycled = cycleBarkSide(state.getValue(BlockQuarterLog.BARK_SIDE));
				BlockLog.EnumAxis newAxis = currentAxis;

				// First cycle the bark side. If we return to the start, then cycle the axis too.
				if(cycled == BlockQuarterLog.BarkSide.SOUTHWEST) {
					newAxis = cycleAxis(currentAxis);
				}

				world.setBlockState(pos, state
						.withProperty(BlockLog.LOG_AXIS, newAxis)
						.withProperty(BlockQuarterLog.BARK_SIDE, cycled)
				);

				return EnumActionResult.SUCCESS;
			} else {
				world.setBlockState(pos, state.withProperty(BlockLog.LOG_AXIS, cycleAxis(currentAxis)));

				return EnumActionResult.SUCCESS;
			}
		} else if(currentAxis != BlockLog.EnumAxis.NONE) {
			BlockLog.EnumAxis newAxis = BlockLog.EnumAxis.fromFacingAxis(facing.getAxis());

			if(currentAxis != newAxis) {
				world.setBlockState(pos, state.withProperty(BlockLog.LOG_AXIS, newAxis));

				return EnumActionResult.SUCCESS;
			}
		}

		if(state.getBlock() instanceof BlockQuarterLog) {
			world.setBlockState(pos, state.cycleProperty(BlockQuarterLog.BARK_SIDE));

			return EnumActionResult.SUCCESS;
		} else {
			return EnumActionResult.PASS;
		}
	}

	private static BlockLog.EnumAxis cycleAxis(BlockLog.EnumAxis axis) {
		switch(axis) {
			case X: return BlockLog.EnumAxis.Y;
			case Y: return BlockLog.EnumAxis.Z;
			case Z: return BlockLog.EnumAxis.X;
			default: return BlockLog.EnumAxis.NONE;
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

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flag) {
		super.addInformation(stack, world, tooltip, flag);
		tooltip.add("Use on a log to instantly set its axis to the side you clicked");
		tooltip.add("When used on a quarter log, it will try to set the axis, and if it already matches, it will then rotate the bark side clockwise");
		tooltip.add("When used while sneaking, it will simply cycle the axis and bark side");
	}
}
