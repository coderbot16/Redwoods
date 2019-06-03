// TODO EVERYTHING

/*package net.coderbot.redwoods.proxy;

import net.coderbot.redwoods.block.BlockConiferLeaves;
import net.coderbot.redwoods.init.ModBlocks;
import net.coderbot.redwoods.init.ModItems;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ClientProxy extends CommonProxy {
	@SubscribeEvent
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomStateMapper(ModBlocks.REDWOOD_LEAVES, new StateMap.Builder().ignore(BlockConiferLeaves.CHECK_DECAY, BlockConiferLeaves.DECAYABLE).build());
		ModelLoader.setCustomStateMapper(ModBlocks.FIR_LEAVES, new StateMap.Builder().ignore(BlockConiferLeaves.CHECK_DECAY, BlockConiferLeaves.DECAYABLE).build());
		ModelLoader.setCustomStateMapper(ModBlocks.REDWOOD_FENCE_GATE, new StateMap.Builder().ignore(BlockFenceGate.POWERED).build());
		ModelLoader.setCustomStateMapper(ModBlocks.FIR_FENCE_GATE, new StateMap.Builder().ignore(BlockFenceGate.POWERED).build());
		ModelLoader.setCustomStateMapper(ModBlocks.REDWOOD_DOOR, new StateMap.Builder().ignore(BlockDoor.POWERED).build());
		ModelLoader.setCustomStateMapper(ModBlocks.FIR_DOOR, new StateMap.Builder().ignore(BlockDoor.POWERED).build());

		registerModel(ModItems.REDWOOD_LOG);
		registerModel(ModItems.FIR_LOG);
		registerModel(ModItems.REDWOOD_LOG_QUARTER);
		registerModel(ModItems.FIR_LOG_QUARTER);

		registerModel(ModItems.REDWOOD_SAPLING);
		registerModel(ModItems.FIR_SAPLING);

		registerModel(ModItems.REDWOOD_LEAVES);
		registerModel(ModItems.FIR_LEAVES);

		registerModel(ModItems.REDWOOD_PLANKS);
		registerModel(ModItems.FIR_PLANKS);

		registerModel(ModItems.REDWOOD_SLAB);
		registerModel(ModItems.FIR_SLAB);

		registerModel(ModItems.REDWOOD_STAIRS);
		registerModel(ModItems.FIR_STAIRS);

		registerModel(ModItems.REDWOOD_FENCE);
		registerModel(ModItems.FIR_FENCE);

		registerModel(ModItems.REDWOOD_FENCE_GATE);
		registerModel(ModItems.FIR_FENCE_GATE);

		registerModel(ModItems.REDWOOD_DOOR);
		registerModel(ModItems.FIR_DOOR);

		registerModel(ModItems.LOG_TURNER);
	}

	@SubscribeEvent
	public void handleBlockColors(ColorHandlerEvent.Block event) {
		event.getBlockColors().registerBlockColorHandler(
				(BlockState state, IBlockAccess worldIn, BlockPos pos, int tintIndex) ->
						worldIn.getBiome(pos).getFoliageColorAtPos(pos),
				ModBlocks.REDWOOD_LEAVES,
				ModBlocks.FIR_LEAVES
		);
	}

	@SubscribeEvent
	public void handleItemColors(ColorHandlerEvent.Item event) {
		event.getItemColors().registerItemColorHandler(
				(ItemStack stack, int tintIndex) -> ColorizerGrass.getGrassColor(0.5D, 1.0D),
				ModBlocks.REDWOOD_LEAVES,
				ModBlocks.FIR_LEAVES
		);
	}

	private void registerModel(Item item) {
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
}*/