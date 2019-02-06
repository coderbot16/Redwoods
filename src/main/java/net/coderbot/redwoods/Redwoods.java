package net.coderbot.redwoods;

import net.coderbot.redwoods.proxy.CommonProxy;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Redwoods.MODID, name = Redwoods.NAME, version = Redwoods.VERSION)
public class Redwoods
{
	public static final String MODID = "redwoods";
	public static final String NAME = "Redwoods";
	public static final String VERSION = "1.0";
	
	@SidedProxy(
			serverSide = "net.coderbot.redwoods.proxy.CommonProxy",
			clientSide = "net.coderbot.redwoods.proxy.ClientProxy"
	)
	public static CommonProxy proxy;
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(proxy);
	}
}
