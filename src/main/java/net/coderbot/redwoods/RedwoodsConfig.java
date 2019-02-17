package net.coderbot.redwoods;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Config(modid = Redwoods.MODID)
@Config.LangKey("redwoods.config.title")
public class RedwoodsConfig {
	@Config.Comment("Use the trick found in OptiLeaves 1.6.4 to significantly reduce lag from conifer leaf rendering. " +
	                "This makes leaves appear much more hollow, but looks better than Fast graphics and runs almost as well.")
	public static boolean useOptiLeaves = true;

	@Config.Comment("Whether leaves will have a nonzero light opacity, like vanilla leaves. They have no opacity by" +
	                "default to avoid lag from lighting updates and mob spawns.")
	public static boolean leavesDiffuseSkylight = false;

	@Config.Comment("The configuration for the biomes. Changing these will have no effect without a restart!")
	public static final BiomesConfiguration biomes = new BiomesConfiguration();

	public static class BiomesConfiguration {
		@Config.Comment("Settings for the Redwood Forest biome")
		public BiomeConfig redwoodForest = new BiomeConfig(8, true);

		@Config.Comment("Settings for the Lush Redwood Forest biome")
		public BiomeConfig lushRedwoodForest = new BiomeConfig(8, true);

		@Config.Comment("Settings for the Temperate Rainforest biome")
		public BiomeConfig temperateRainforest = new BiomeConfig(10, true);

		@Config.Comment("Settings for the Snowy Rainforest biome")
		public BiomeConfig snowyRainforest = new BiomeConfig(10, true);

		@Config.Comment("Settings for the Alpine biome")
		public BiomeConfig alpine = new BiomeConfig(0, false);

		public static class BiomeConfig {
			@Config.Comment("The weight of this biome in the biome generation list, 0 to disable generation")
			public int weight = 10;

			@Config.Comment("Whether this biome will be registered at all. This will break existing worlds if changed!")
			public boolean register = true;

			public BiomeConfig(int weight, boolean register) {
				this.weight = weight;
				this.register = register;
			}
		}
	}

	@Mod.EventBusSubscriber(modid = Redwoods.MODID)
	private static class EventHandler {

		/**
		 * Inject the new values and save to the config file when the config has been changed from the GUI.
		 *
		 * @param event The event
		 */
		@SubscribeEvent
		public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
			if (event.getModID().equals(Redwoods.MODID)) {
				ConfigManager.sync(Redwoods.MODID, Config.Type.INSTANCE);

				if(FMLCommonHandler.instance().getSide() == Side.CLIENT) {
					Minecraft.getMinecraft().renderGlobal.loadRenderers();
				}
			}
		}
	}
}
