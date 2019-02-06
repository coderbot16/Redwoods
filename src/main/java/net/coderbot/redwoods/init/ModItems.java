package net.coderbot.redwoods.init;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class ModItems {
	public static Item REDWOOD_LOG;
	public static Item FIR_LOG;
	public static Item REDWOOD_LOG_QUARTER;
	public static Item FIR_LOG_QUARTER;

	public static Item REDWOOD_SAPLING;
	public static Item FIR_SAPLING;

	public static Item REDWOOD_LEAVES;
	public static Item FIR_LEAVES;

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		REDWOOD_LOG = register(event, ModBlocks.REDWOOD_LOG);
		FIR_LOG = register(event, ModBlocks.FIR_LOG);
		REDWOOD_LOG_QUARTER = register(event, ModBlocks.REDWOOD_LOG_QUARTER);
		FIR_LOG_QUARTER = register(event, ModBlocks.FIR_LOG_QUARTER);

		REDWOOD_SAPLING = register(event, ModBlocks.REDWOOD_SAPLING);
		FIR_SAPLING = register(event, ModBlocks.FIR_SAPLING);

		REDWOOD_LEAVES = register(event, ModBlocks.REDWOOD_LEAVES);
		FIR_LEAVES = register(event, ModBlocks.FIR_LEAVES);
	}

	private static Item register(RegistryEvent.Register<Item> event, Block block) {
		Item item = new ItemBlock(block);
		item.setRegistryName(block.getRegistryName());
		item.setUnlocalizedName(block.getUnlocalizedName());

		event.getRegistry().register(item);

		return item;
	}
}
