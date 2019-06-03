package net.coderbot.redwoods;

import net.coderbot.redwoods.init.ModBlocks;
import net.coderbot.redwoods.init.ModItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.render.ColorProviderRegistry;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.FoliageColors;

public class RedwoodsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ColorProviderRegistry.BLOCK.register(
				(block, world, pos, layer) -> world != null && pos != null ? BiomeColors.getFoliageColor(world, pos) : FoliageColors.getDefaultColor(),
				ModBlocks.FIR_LEAVES, ModBlocks.REDWOOD_LEAVES
		);

		ColorProviderRegistry.ITEM.register(
				(item, layer) -> FoliageColors.getColor(0.5, 1.0),
				ModItems.FIR_LEAVES, ModItems.REDWOOD_LEAVES
		);
	}
}
