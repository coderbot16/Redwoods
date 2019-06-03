package net.coderbot.redwoods.block;

import net.minecraft.block.LogBlock;
import net.minecraft.block.MaterialColor;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MiningToolItem;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockCenterLog extends LogBlock {
	private BlockQuarterLog quarter;

	public BlockCenterLog(Settings settings) {
		super(MaterialColor.SPRUCE, settings);
	}

	public void setQuarter(BlockQuarterLog quarter) {
		this.quarter = quarter;
	}

	@Override
	@SuppressWarnings("deprecation") // wtf?
	public boolean activate(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		ItemStack heldStack = player.getEquippedStack(hand == Hand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND);

		if(heldStack.isEmpty()) {
			return false;
		}

		Item held = heldStack.getItem();
		if(!(held instanceof MiningToolItem)) {
			return false;
		}

		MiningToolItem tool = (MiningToolItem) held;

		if(tool.isEffectiveOn(state) && tool.getBlockBreakingSpeed(heldStack, state) > 1.0) {
			worldIn.setBlockState(pos, this.removeBark(state, pos, hit));
			
			heldStack.applyDamage(1, player, consumedPlayer -> consumedPlayer.sendToolBreakStatus(hand));

			return true;
		}

		return false;
	}

	public BlockState removeBark(BlockState existing, BlockPos pos, BlockHitResult hit) {
		// TODO: Implement removeBark, figure out hitX/hitY/hitZ
		throw new UnsupportedOperationException("removeBark not implemented yet");
		/*BlockQuarterLog.BarkSide side = BlockQuarterLog.BarkSide.fromHit(existing.get(AXIS), hitX, hitY, hitZ);

		return quarter.getDefaultState()
				.with(BlockQuarterLog.BARK_SIDE, side)
				.with(AXIS, existing.get(AXIS));*/
	}
}
