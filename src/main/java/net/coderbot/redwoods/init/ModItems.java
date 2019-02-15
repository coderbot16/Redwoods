package net.coderbot.redwoods.init;

import net.coderbot.redwoods.Redwoods;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSlab;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;

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

		REDWOOD_PLANKS = register(event, ModBlocks.REDWOOD_PLANKS);
		FIR_PLANKS = register(event, ModBlocks.FIR_PLANKS);

		REDWOOD_SLAB = register(event, new ItemSlab(ModBlocks.REDWOOD_SLAB, ModBlocks.REDWOOD_SLAB, ModBlocks.REDWOOD_DOUBLE_SLAB), "redwood_slab");
		FIR_SLAB = register(event, new ItemSlab(ModBlocks.FIR_SLAB, ModBlocks.FIR_SLAB, ModBlocks.FIR_DOUBLE_SLAB), "fir_slab");

		REDWOOD_STAIRS = register(event, ModBlocks.REDWOOD_STAIRS);
		FIR_STAIRS = register(event, ModBlocks.FIR_STAIRS);

		REDWOOD_FENCE = register(event, ModBlocks.REDWOOD_FENCE);
		FIR_FENCE = register(event, ModBlocks.FIR_FENCE);

		REDWOOD_FENCE_GATE = register(event, ModBlocks.REDWOOD_FENCE_GATE);
		FIR_FENCE_GATE = register(event, ModBlocks.FIR_FENCE_GATE);
	}

	public static void registerOreDict() {
		registerOre("logWood", REDWOOD_LOG, FIR_LOG, REDWOOD_LOG_QUARTER, FIR_LOG_QUARTER);
		registerOre("treeSapling", REDWOOD_SAPLING, FIR_SAPLING);
		registerOre("treeLeaves", REDWOOD_LEAVES, FIR_LEAVES);
		registerOre("plankWood", REDWOOD_PLANKS, FIR_PLANKS);
		registerOre("slabWood", REDWOOD_SLAB, FIR_SLAB);
		registerOre("stairWood", REDWOOD_STAIRS, FIR_STAIRS);
		registerOre("fenceWood", REDWOOD_FENCE, FIR_FENCE);
		registerOre("fenceGateWood", REDWOOD_FENCE_GATE, FIR_FENCE_GATE);
		// TODO: doorWood
	}

	private static Item register(RegistryEvent.Register<Item> event, Block block) {
		ResourceLocation name = block.getRegistryName();

		if(name == null) {
			throw new NullPointerException();
		}

		Item item = new ItemBlock(block);
		item.setRegistryName(name);
		item.setUnlocalizedName(block.getUnlocalizedName());
		item.setCreativeTab(Redwoods.TAB);

		event.getRegistry().register(item);

		return item;
	}

	private static Item register(RegistryEvent.Register<Item> event, Item item, String name) {
		item.setRegistryName(new ResourceLocation(Redwoods.MODID, name));
		item.setUnlocalizedName(Redwoods.MODID + "." + name);
		item.setCreativeTab(Redwoods.TAB);

		event.getRegistry().register(item);

		return item;
	}

	private static void registerOre(String name, Item... items) {
		for(Item item: items) {
			OreDictionary.registerOre(name, item);
		}
	}
}
