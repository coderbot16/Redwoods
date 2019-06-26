package net.coderbot.redwoods.init;

import net.coderbot.redwoods.Redwoods;
import net.coderbot.redwoods.item.LogTurnerItem;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.TallBlockItem;
import net.minecraft.util.registry.Registry;

public class RedwoodsItems {
	public static Item REDWOOD_LOG;
	public static Item FIR_LOG;
	public static Item REDWOOD_LOG_QUARTER;
	public static Item FIR_LOG_QUARTER;

	public static Item REDWOOD_SAPLING;
	public static Item FIR_SAPLING;

	public static Item REDWOOD_LEAVES;
	public static Item FIR_LEAVES;

	public static Item REDWOOD_PLANKS;
	public static Item FIR_PLANKS;

	public static Item REDWOOD_SLAB;
	public static Item FIR_SLAB;

	public static Item REDWOOD_STAIRS;
	public static Item FIR_STAIRS;

	public static Item REDWOOD_FENCE;
	public static Item FIR_FENCE;

	public static Item REDWOOD_FENCE_GATE;
	public static Item FIR_FENCE_GATE;

	public static Item REDWOOD_DOOR;
	public static Item FIR_DOOR;

	public static Item LOG_TURNER;
	
	public static void registerItems() {
		REDWOOD_LOG = register("redwoods:redwood_log", RedwoodsBlocks.REDWOOD_LOG);
		FIR_LOG = register("redwoods:fir_log", RedwoodsBlocks.FIR_LOG);
		REDWOOD_LOG_QUARTER = register("redwoods:redwood_log_quarter", RedwoodsBlocks.REDWOOD_LOG_QUARTER);
		FIR_LOG_QUARTER = register("redwoods:fir_log_quarter", RedwoodsBlocks.FIR_LOG_QUARTER);

		REDWOOD_SAPLING = register("redwoods:redwood_sapling", RedwoodsBlocks.REDWOOD_SAPLING);
		FIR_SAPLING = register("redwoods:fir_sapling", RedwoodsBlocks.FIR_SAPLING);

		REDWOOD_LEAVES = register("redwoods:redwood_leaves", RedwoodsBlocks.REDWOOD_LEAVES);
		FIR_LEAVES = register("redwoods:fir_leaves", RedwoodsBlocks.FIR_LEAVES);

		REDWOOD_PLANKS = register("redwoods:redwood_planks", RedwoodsBlocks.REDWOOD_PLANKS);
		FIR_PLANKS = register("redwoods:fir_planks", RedwoodsBlocks.FIR_PLANKS);

		REDWOOD_SLAB = register("redwoods:redwood_slab", RedwoodsBlocks.REDWOOD_SLAB);
		FIR_SLAB = register("redwoods:fir_slab", RedwoodsBlocks.FIR_SLAB);

		REDWOOD_STAIRS = register("redwoods:redwood_stairs", RedwoodsBlocks.REDWOOD_STAIRS);
		FIR_STAIRS = register("redwoods:fir_stairs", RedwoodsBlocks.FIR_STAIRS);

		REDWOOD_FENCE = register("redwoods:redwood_fence", RedwoodsBlocks.REDWOOD_FENCE);
		FIR_FENCE = register("redwoods:fir_fence", RedwoodsBlocks.FIR_FENCE);

		REDWOOD_FENCE_GATE = register("redwoods:redwood_fence_gate", RedwoodsBlocks.REDWOOD_FENCE_GATE);
		FIR_FENCE_GATE = register("redwoods:fir_fence_gate", RedwoodsBlocks.FIR_FENCE_GATE);

		REDWOOD_DOOR = Registry.register(Registry.ITEM, "redwoods:redwood_door", new TallBlockItem(RedwoodsBlocks.REDWOOD_DOOR, new Item.Settings().itemGroup(Redwoods.ITEM_GROUP)));
		FIR_DOOR = Registry.register(Registry.ITEM, "redwoods:fir_door", new TallBlockItem(RedwoodsBlocks.FIR_DOOR, new Item.Settings().itemGroup(Redwoods.ITEM_GROUP)));

		LOG_TURNER = Registry.register(Registry.ITEM, "redwoods:log_turner", new LogTurnerItem(new Item.Settings().itemGroup(Redwoods.ITEM_GROUP)));
	}

	private static Item register(String name, Block block) {
		Item item = new BlockItem(block, new Item.Settings().itemGroup(Redwoods.ITEM_GROUP));
		return Registry.register(Registry.ITEM, name, item);
	}
}
