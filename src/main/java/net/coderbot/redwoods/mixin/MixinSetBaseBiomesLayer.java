package net.coderbot.redwoods.mixin;

import net.coderbot.redwoods.init.RedwoodsBiomes;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.layer.SetBaseBiomesLayer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

// TODO: Awaiting Fabric biomes API
@Mixin(SetBaseBiomesLayer.class)
public class MixinSetBaseBiomesLayer {
	@Shadow
	@Final
	@Mutable
	private static int[] TEMPERATE_BIOMES;

	@Shadow
	@Final
	@Mutable
	private static int[] SNOWY_BIOMES;

	static {
		int REDWOOD_FOREST_ID = Registry.BIOME.getRawId(RedwoodsBiomes.REDWOOD_FOREST);
		int LUSH_REDWOOD_FOREST_ID = Registry.BIOME.getRawId(RedwoodsBiomes.LUSH_REDWOOD_FOREST);
		int TEMPERATE_RAINFOREST_ID = Registry.BIOME.getRawId(RedwoodsBiomes.TEMPERATE_RAINFOREST);
		int SNOWY_RAINFOREST_ID = Registry.BIOME.getRawId(RedwoodsBiomes.SNOWY_RAINFOREST);
		int ALPINE_BIOME_ID = Registry.BIOME.getRawId(RedwoodsBiomes.ALPINE);

		// TODO: rip every other temperate biome in the game
		// TEMPERATE_BIOMES = new int[] { REDWOOD_FOREST_ID, LUSH_REDWOOD_FOREST_ID };
		TEMPERATE_BIOMES = new int[] { ALPINE_BIOME_ID };
		SNOWY_BIOMES = new int[] { SNOWY_RAINFOREST_ID };
	}
}
