package net.coderbot.redwoods.init;

import io.github.terraformersmc.terraform.block.*;
import io.github.terraformersmc.terraform.util.TerraformLargeSaplingGenerator;
import net.coderbot.redwoods.world.ConiferTreeFeature;
import net.coderbot.redwoods.world.MegaConiferTreeFeature;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.block.*;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

public class RedwoodsBlocks {
	public static LogBlock REDWOOD_LOG;
	public static LogBlock FIR_LOG;
	public static QuarterLogBlock REDWOOD_LOG_QUARTER;
	public static QuarterLogBlock FIR_LOG_QUARTER;

	public static TerraformSaplingBlock REDWOOD_SAPLING;
	public static TerraformSaplingBlock FIR_SAPLING;

	public static ExtendedLeavesBlock REDWOOD_LEAVES;
	public static ExtendedLeavesBlock FIR_LEAVES;

	public static Block REDWOOD_PLANKS;
	public static Block FIR_PLANKS;

	public static SlabBlock REDWOOD_SLAB;
	public static SlabBlock FIR_SLAB;

	public static TerraformStairsBlock REDWOOD_STAIRS;
	public static TerraformStairsBlock FIR_STAIRS;

	public static FenceBlock REDWOOD_FENCE;
	public static FenceBlock FIR_FENCE;

	public static FenceGateBlock REDWOOD_FENCE_GATE;
	public static FenceGateBlock FIR_FENCE_GATE;

	public static TerraformDoorBlock REDWOOD_DOOR;
	public static TerraformDoorBlock FIR_DOOR;
	
	public static void registerBlocks() {
		// TODO: MaterialColor
		REDWOOD_LOG = Registry.register(Registry.BLOCK, "redwoods:redwood_log", new LogBlock(MaterialColor.BROWN, Block.Settings.copy(Blocks.SPRUCE_LOG)));
		FIR_LOG = Registry.register(Registry.BLOCK, "redwoods:fir_log", new LogBlock(MaterialColor.BROWN, Block.Settings.copy(Blocks.SPRUCE_LOG)));

		REDWOOD_LOG_QUARTER = Registry.register(Registry.BLOCK, "redwoods:redwood_log_quarter", new QuarterLogBlock(MaterialColor.BROWN, Block.Settings.copy(Blocks.SPRUCE_LOG)));
		FIR_LOG_QUARTER = Registry.register(Registry.BLOCK, "redwoods:fir_log_quarter", new QuarterLogBlock(MaterialColor.BROWN, Block.Settings.copy(Blocks.SPRUCE_LOG)));

		REDWOOD_LEAVES = Registry.register(Registry.BLOCK, "redwoods:redwood_leaves", new ExtendedLeavesBlock(Block.Settings.copy(Blocks.SPRUCE_LEAVES)));
		FIR_LEAVES = Registry.register(Registry.BLOCK, "redwoods:fir_leaves", new ExtendedLeavesBlock(Block.Settings.copy(Blocks.SPRUCE_LEAVES)));

		REDWOOD_SAPLING = Registry.register(Registry.BLOCK, "redwoods:redwood_sapling", new TerraformSaplingBlock (
				new TerraformLargeSaplingGenerator (
						() -> new ConiferTreeFeature(DefaultFeatureConfig::deserialize, true, REDWOOD_LOG.getDefaultState(), REDWOOD_LEAVES.getDefaultState()),
						() -> new MegaConiferTreeFeature(
								DefaultFeatureConfig::deserialize,
								true,
								side -> REDWOOD_LOG_QUARTER.getDefaultState().with(QuarterLogBlock.BARK_SIDE, side),
								REDWOOD_LEAVES.getDefaultState()
						)
				)
		));

		FIR_SAPLING = Registry.register(Registry.BLOCK, "redwoods:fir_sapling", new TerraformSaplingBlock (
				new TerraformLargeSaplingGenerator (
						() -> new ConiferTreeFeature(DefaultFeatureConfig::deserialize, true, FIR_LOG.getDefaultState(), FIR_LEAVES.getDefaultState()),
						() -> new MegaConiferTreeFeature(
								DefaultFeatureConfig::deserialize,
								true,
								side -> FIR_LOG_QUARTER.getDefaultState().with(QuarterLogBlock.BARK_SIDE, side),
								FIR_LEAVES.getDefaultState()
						)
				)
		));

		REDWOOD_PLANKS = Registry.register(Registry.BLOCK, "redwoods:redwood_planks", new Block(Block.Settings.copy(Blocks.SPRUCE_PLANKS)));
		FIR_PLANKS = Registry.register(Registry.BLOCK, "redwoods:fir_planks", new Block(Block.Settings.copy(Blocks.SPRUCE_PLANKS)));

		REDWOOD_SLAB = Registry.register(Registry.BLOCK, "redwoods:redwood_slab", new SlabBlock(Block.Settings.copy(Blocks.SPRUCE_SLAB)));
		FIR_SLAB = Registry.register(Registry.BLOCK, "redwoods:fir_slab", new SlabBlock(Block.Settings.copy(Blocks.SPRUCE_SLAB)));

		REDWOOD_STAIRS = Registry.register(Registry.BLOCK, "redwoods:redwood_stairs", new TerraformStairsBlock(REDWOOD_PLANKS, Block.Settings.copy(Blocks.SPRUCE_STAIRS)));
		FIR_STAIRS = Registry.register(Registry.BLOCK, "redwoods:fir_stairs", new TerraformStairsBlock(FIR_PLANKS, Block.Settings.copy(Blocks.SPRUCE_STAIRS)));

		REDWOOD_FENCE = Registry.register(Registry.BLOCK, "redwoods:redwood_fence", new FenceBlock(Block.Settings.copy(Blocks.SPRUCE_FENCE)));
		FIR_FENCE = Registry.register(Registry.BLOCK, "redwoods:fir_fence", new FenceBlock(Block.Settings.copy(Blocks.SPRUCE_FENCE)));

		REDWOOD_FENCE_GATE = Registry.register(Registry.BLOCK, "redwoods:redwood_fence_gate", new FenceGateBlock(Block.Settings.copy(Blocks.SPRUCE_FENCE)));
		FIR_FENCE_GATE = Registry.register(Registry.BLOCK, "redwoods:fir_fence_gate", new FenceGateBlock(Block.Settings.copy(Blocks.SPRUCE_FENCE)));

		REDWOOD_DOOR = Registry.register(Registry.BLOCK, "redwoods:redwood_door", new TerraformDoorBlock(Block.Settings.copy(Blocks.SPRUCE_DOOR)));
		FIR_DOOR = Registry.register(Registry.BLOCK, "redwoods:fir_door", new TerraformDoorBlock(Block.Settings.copy(Blocks.SPRUCE_DOOR)));

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
