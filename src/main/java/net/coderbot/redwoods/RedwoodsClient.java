package net.coderbot.redwoods;

import net.coderbot.redwoods.init.RedwoodsBlocks;
import net.coderbot.redwoods.init.RedwoodsItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.render.ColorProviderRegistry;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.FoliageColors;

public class RedwoodsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ColorProviderRegistry.BLOCK.register(
				(block, world, pos, layer) -> world != null && pos != null ? BiomeColors.getFoliageColor(world, pos) : FoliageColors.getDefaultColor(),
				RedwoodsBlocks.FIR_LEAVES, RedwoodsBlocks.REDWOOD_LEAVES
		);

		ColorProviderRegistry.ITEM.register(
				(item, layer) -> FoliageColors.getColor(0.5, 1.0),
				RedwoodsItems.FIR_LEAVES, RedwoodsItems.REDWOOD_LEAVES
		);
	}
}
