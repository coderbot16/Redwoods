package net.coderbot.redwoods;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = Redwoods.MODID, name = Redwoods.NAME, version = Redwoods.VERSION)
public class Redwoods
{
    public static final String MODID = "redwoods";
    public static final String NAME = "Redwoods";
    public static final String VERSION = "1.0";

    private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // some example code
        logger.info("REKT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }
}
