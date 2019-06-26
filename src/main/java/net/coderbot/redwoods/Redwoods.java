package net.coderbot.redwoods;

import net.coderbot.redwoods.init.RedwoodsBlocks;
import net.coderbot.redwoods.init.RedwoodsItems;
import net.coderbot.redwoods.init.RedwoodsBiomes;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class Redwoods implements ModInitializer {
	public static ItemGroup ITEM_GROUP;

	@Override
	public void onInitialize() {
		ITEM_GROUP = FabricItemGroupBuilder.build(new Identifier("redwoods", "items"), () -> new ItemStack(RedwoodsItems.REDWOOD_SAPLING));

		RedwoodsBlocks.registerBlocks();
		RedwoodsItems.registerItems();
		RedwoodsBiomes.registerBiomes();
	}

	// TODO: Bark ("Wood") and stripped logs/bark
	// TODO: Boats (not really possible without a custom entity or mixin, probably)
}
