package net.coderbot.redwoods.init;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public static void registerModels(ModelRegistryEvent event) {
		registerModel(REDWOOD_LOG);
		registerModel(FIR_LOG);
		registerModel(REDWOOD_LOG_QUARTER);
		registerModel(FIR_LOG_QUARTER);

		registerModel(REDWOOD_SAPLING);
		registerModel(FIR_SAPLING);

		registerModel(REDWOOD_LEAVES);
		registerModel(FIR_LEAVES);
	}

	private static Item register(RegistryEvent.Register<Item> event, Block block) {
		Item item = new ItemBlock(block);
		item.setRegistryName(block.getRegistryName());
		item.setUnlocalizedName(block.getUnlocalizedName());

		event.getRegistry().register(item);

		return item;
	}

	@SideOnly(Side.CLIENT)
	private static void registerModel(Item item) {
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
}
