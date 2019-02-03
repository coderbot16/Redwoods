package net.coderbot.redwoods.init;

import net.coderbot.redwoods.Redwoods;
import net.coderbot.redwoods.block.BlockCenterLog;
import net.coderbot.redwoods.block.BlockQuarterLog;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class ModBlocks {
	public static Block REDWOOD_LOG;
	public static Block FIR_LOG;
	public static Block REDWOOD_LOG_QUARTER;
	public static Block FIR_LOG_QUARTER;

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		REDWOOD_LOG = register(event, new BlockCenterLog(), "redwood_log");
		FIR_LOG = register(event, new BlockCenterLog(), "fir_log");

		REDWOOD_LOG_QUARTER = register(event, new BlockQuarterLog(), "redwood_log_quarter");
		FIR_LOG_QUARTER = register(event, new BlockQuarterLog(), "fir_log_quarter");
	}

	private static Block register(RegistryEvent.Register<Block> event, Block block, String name) {
		block.setRegistryName(new ResourceLocation(Redwoods.MODID, name));
		block.setUnlocalizedName(Redwoods.MODID + "." + name);

		event.getRegistry().register(block);

		return block;
	}
}
