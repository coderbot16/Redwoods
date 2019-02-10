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
