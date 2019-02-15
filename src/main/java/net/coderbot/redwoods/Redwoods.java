package net.coderbot.redwoods;

import net.coderbot.redwoods.init.ModBlocks;
import net.coderbot.redwoods.init.ModItems;
import net.coderbot.redwoods.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import javax.annotation.Nonnull;

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

	public static CreativeTabs TAB;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(proxy);

        TAB = new CreativeTabs("redwoods") {
			@Override
			@Nonnull
			public ItemStack getTabIconItem() {
				return new ItemStack(ModItems.REDWOOD_SAPLING);
			}
		};
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		ModItems.registerOreDict();
		GameRegistry.addSmelting(ModBlocks.REDWOOD_LOG, new ItemStack(Items.COAL, 1, 1), 0.15F);
		GameRegistry.addSmelting(ModBlocks.REDWOOD_LOG_QUARTER, new ItemStack(Items.COAL, 1, 1), 0.15F);
		GameRegistry.addSmelting(ModBlocks.FIR_LOG, new ItemStack(Items.COAL, 1, 1), 0.15F);
		GameRegistry.addSmelting(ModBlocks.FIR_LOG_QUARTER, new ItemStack(Items.COAL, 1, 1), 0.15F);
	}
}
