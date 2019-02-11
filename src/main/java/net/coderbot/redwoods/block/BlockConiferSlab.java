package net.coderbot.redwoods.block;

import net.minecraft.block.BlockSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;

import java.util.Random;

public class BlockConiferSlab extends BlockSlab {
	public static PropertyEnum<EnumSingleVariant> VARIANT = PropertyEnum.create("variant", EnumSingleVariant.class);

	public BlockConiferSlab() {
		super(Material.WOOD);

		setSoundType(SoundType.WOOD);
		setHardness(2.0F);
		setResistance(5.0F);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(Blocks.WOODEN_SLAB);
	}

	@Override
	public String getUnlocalizedName(int meta) {
		return getUnlocalizedName();
	}

	@Override
	public boolean isDouble() {
		return false;
	}

	@Override
	public IProperty<?> getVariantProperty() {
		return VARIANT;
	}

	@Override
	public Comparable<?> getTypeForItem(ItemStack stack) {
		return EnumSingleVariant.NORMAL;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		if(isDouble()) {
			return this.getDefaultState();
		}

		return this.getDefaultState().withProperty(HALF, (meta & 8) > 0 ? EnumBlockHalf.TOP : EnumBlockHalf.BOTTOM);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		if(isDouble()) {
			return 0;
		}

		return state.getValue(HALF) == EnumBlockHalf.TOP ? 8 : 0;
	}

	@Override
	public BlockStateContainer createBlockState() {
		if(isDouble()) {
			return new BlockStateContainer(this, VARIANT);
		}

		return new BlockStateContainer(this, VARIANT, HALF);
	}

	public enum EnumSingleVariant implements IStringSerializable {
		NORMAL;

		@Override
		public String getName() {
			return "normal";
		}
	}
}
