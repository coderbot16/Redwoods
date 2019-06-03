package net.coderbot.redwoods.init;

import net.coderbot.redwoods.block.*;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.block.*;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;

public class ModBlocks {
	public static BlockCenterLog REDWOOD_LOG;
	public static BlockCenterLog FIR_LOG;
	public static BlockQuarterLog REDWOOD_LOG_QUARTER;
	public static BlockQuarterLog FIR_LOG_QUARTER;

	public static ConiferSaplingBlock REDWOOD_SAPLING;
	public static ConiferSaplingBlock FIR_SAPLING;

	public static LeavesBlock REDWOOD_LEAVES;
	public static LeavesBlock FIR_LEAVES;

	public static ConiferSaplingGenerator.TreeDefinition REDWOOD;
	public static ConiferSaplingGenerator.TreeDefinition FIR;

	public static Block REDWOOD_PLANKS;
	public static Block FIR_PLANKS;

	public static SlabBlock REDWOOD_SLAB;
	public static SlabBlock FIR_SLAB;

	public static BlockConiferStairs REDWOOD_STAIRS;
	public static BlockConiferStairs FIR_STAIRS;

	public static FenceBlock REDWOOD_FENCE;
	public static FenceBlock FIR_FENCE;

	public static FenceGateBlock REDWOOD_FENCE_GATE;
	public static FenceGateBlock FIR_FENCE_GATE;

	public static ConiferDoorBlock REDWOOD_DOOR;
	public static ConiferDoorBlock FIR_DOOR;
	
	public static void registerBlocks() {
		REDWOOD_LOG = Registry.register(Registry.BLOCK, "redwoods:redwood_log", new BlockCenterLog(Block.Settings.copy(Blocks.SPRUCE_LOG)));
		FIR_LOG = Registry.register(Registry.BLOCK, "redwoods:fir_log", new BlockCenterLog(Block.Settings.copy(Blocks.SPRUCE_LOG)));

		REDWOOD_LOG_QUARTER = Registry.register(Registry.BLOCK, "redwoods:redwood_log_quarter", new BlockQuarterLog(Block.Settings.copy(Blocks.SPRUCE_LOG)));
		FIR_LOG_QUARTER = Registry.register(Registry.BLOCK, "redwoods:fir_log_quarter", new BlockQuarterLog(Block.Settings.copy(Blocks.SPRUCE_LOG)));

		REDWOOD_LOG.setQuarter(REDWOOD_LOG_QUARTER);
		FIR_LOG.setQuarter(FIR_LOG_QUARTER);

		REDWOOD_LEAVES = Registry.register(Registry.BLOCK, "redwoods:redwood_leaves", new LeavesBlock(Block.Settings.copy(Blocks.SPRUCE_LEAVES)));
		FIR_LEAVES = Registry.register(Registry.BLOCK, "redwoods:fir_leaves", new LeavesBlock(Block.Settings.copy(Blocks.SPRUCE_LEAVES)));

		REDWOOD = new ConiferSaplingGenerator.TreeDefinition();
		REDWOOD.wood = REDWOOD_LOG.getDefaultState().with(BlockCenterLog.AXIS, Direction.Axis.Y);
		REDWOOD.woodSW = REDWOOD_LOG_QUARTER.getDefaultState().with(BlockQuarterLog.AXIS, Direction.Axis.Y).with(BlockQuarterLog.BARK_SIDE, BlockQuarterLog.BarkSide.SOUTHWEST);
		REDWOOD.woodNW = REDWOOD_LOG_QUARTER.getDefaultState().with(BlockQuarterLog.AXIS, Direction.Axis.Y).with(BlockQuarterLog.BARK_SIDE, BlockQuarterLog.BarkSide.NORTHWEST);
		REDWOOD.woodNE = REDWOOD_LOG_QUARTER.getDefaultState().with(BlockQuarterLog.AXIS, Direction.Axis.Y).with(BlockQuarterLog.BARK_SIDE, BlockQuarterLog.BarkSide.NORTHEAST);
		REDWOOD.woodSE = REDWOOD_LOG_QUARTER.getDefaultState().with(BlockQuarterLog.AXIS, Direction.Axis.Y).with(BlockQuarterLog.BARK_SIDE, BlockQuarterLog.BarkSide.SOUTHEAST);
		REDWOOD.leaves = REDWOOD_LEAVES.getDefaultState();

		FIR = new ConiferSaplingGenerator.TreeDefinition();
		FIR.wood = FIR_LOG.getDefaultState().with(BlockCenterLog.AXIS, Direction.Axis.Y);
		FIR.woodSW = FIR_LOG_QUARTER.getDefaultState().with(BlockQuarterLog.AXIS, Direction.Axis.Y).with(BlockQuarterLog.BARK_SIDE, BlockQuarterLog.BarkSide.SOUTHWEST);
		FIR.woodNW = FIR_LOG_QUARTER.getDefaultState().with(BlockQuarterLog.AXIS, Direction.Axis.Y).with(BlockQuarterLog.BARK_SIDE, BlockQuarterLog.BarkSide.NORTHWEST);
		FIR.woodNE = FIR_LOG_QUARTER.getDefaultState().with(BlockQuarterLog.AXIS, Direction.Axis.Y).with(BlockQuarterLog.BARK_SIDE, BlockQuarterLog.BarkSide.NORTHEAST);
		FIR.woodSE = FIR_LOG_QUARTER.getDefaultState().with(BlockQuarterLog.AXIS, Direction.Axis.Y).with(BlockQuarterLog.BARK_SIDE, BlockQuarterLog.BarkSide.SOUTHEAST);
		FIR.leaves = FIR_LEAVES.getDefaultState();

		REDWOOD_SAPLING = Registry.register(Registry.BLOCK, "redwoods:redwood_sapling", new ConiferSaplingBlock(REDWOOD, Block.Settings.copy(Blocks.SPRUCE_SAPLING)));
		FIR_SAPLING = Registry.register(Registry.BLOCK, "redwoods:fir_sapling", new ConiferSaplingBlock(FIR, Block.Settings.copy(Blocks.SPRUCE_SAPLING)));

		REDWOOD_PLANKS = Registry.register(Registry.BLOCK, "redwoods:redwood_planks", new Block(Block.Settings.copy(Blocks.SPRUCE_PLANKS)));
		FIR_PLANKS = Registry.register(Registry.BLOCK, "redwoods:fir_planks", new Block(Block.Settings.copy(Blocks.SPRUCE_PLANKS)));

		REDWOOD_SLAB = Registry.register(Registry.BLOCK, "redwoods:redwood_slab", new SlabBlock(Block.Settings.copy(Blocks.SPRUCE_SLAB)));
		FIR_SLAB = Registry.register(Registry.BLOCK, "redwoods:fir_slab", new SlabBlock(Block.Settings.copy(Blocks.SPRUCE_SLAB)));

		REDWOOD_STAIRS = Registry.register(Registry.BLOCK, "redwoods:redwood_stairs", new BlockConiferStairs(REDWOOD_PLANKS, Block.Settings.copy(Blocks.SPRUCE_STAIRS)));
		FIR_STAIRS = Registry.register(Registry.BLOCK, "redwoods:fir_stairs", new BlockConiferStairs(FIR_PLANKS, Block.Settings.copy(Blocks.SPRUCE_STAIRS)));

		REDWOOD_FENCE = Registry.register(Registry.BLOCK, "redwoods:redwood_fence", new FenceBlock(Block.Settings.copy(Blocks.SPRUCE_FENCE)));
		FIR_FENCE = Registry.register(Registry.BLOCK, "redwoods:fir_fence", new FenceBlock(Block.Settings.copy(Blocks.SPRUCE_FENCE)));

		REDWOOD_FENCE_GATE = Registry.register(Registry.BLOCK, "redwoods:redwood_fence_gate", new FenceGateBlock(Block.Settings.copy(Blocks.SPRUCE_FENCE)));
		FIR_FENCE_GATE = Registry.register(Registry.BLOCK, "redwoods:fir_fence_gate", new FenceGateBlock(Block.Settings.copy(Blocks.SPRUCE_FENCE)));

		REDWOOD_DOOR = Registry.register(Registry.BLOCK, "redwoods:redwood_door", new ConiferDoorBlock(Block.Settings.copy(Blocks.SPRUCE_DOOR)));
		FIR_DOOR = Registry.register(Registry.BLOCK, "redwoods:fir_door", new ConiferDoorBlock(Block.Settings.copy(Blocks.SPRUCE_DOOR)));

		// Burn the blocks!
		registerFlammables();
	}

	private static void registerFlammables() {
		FlammableBlockRegistry.getDefaultInstance().add(REDWOOD_LOG, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(FIR_LOG, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(REDWOOD_LOG_QUARTER, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(FIR_LOG_QUARTER, 5, 5);

		FlammableBlockRegistry.getDefaultInstance().add(REDWOOD_LEAVES, 30, 60);
		FlammableBlockRegistry.getDefaultInstance().add(FIR_LEAVES, 30, 60);

		FlammableBlockRegistry.getDefaultInstance().add(REDWOOD_PLANKS, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(FIR_PLANKS, 5, 20);

		FlammableBlockRegistry.getDefaultInstance().add(REDWOOD_SLAB, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(FIR_SLAB, 5, 20);

		FlammableBlockRegistry.getDefaultInstance().add(REDWOOD_STAIRS, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(FIR_STAIRS, 5, 20);

		FlammableBlockRegistry.getDefaultInstance().add(REDWOOD_FENCE, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(FIR_FENCE, 5, 20);

		FlammableBlockRegistry.getDefaultInstance().add(REDWOOD_FENCE_GATE, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(FIR_FENCE_GATE, 5, 20);
	}
}
