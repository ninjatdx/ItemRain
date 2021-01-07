package com.formas.itemrain;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.formas.itemrain.commands.ItemRainCommand;
import com.formas.itemrain.init.GameRuleInit;
import com.formas.itemrain.init.ItemInit;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.LiteralCommandNode;

import net.minecraft.command.CommandSource;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("itemrain")
@Mod.EventBusSubscriber(modid = ItemRain.MOD_ID, bus = Bus.MOD)
public class ItemRain
{
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "itemrain";
    public static ItemRain instance;

    public ItemRain() {
    	final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    	modEventBus.addListener(this::setup);
    	modEventBus.addListener(this::doClientStuff);
    	modEventBus.addListener(this::serverStarting);
    	
    	GameRuleInit.registerGameRules();
    	ItemInit.ITEMS.register(modEventBus);
    	
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
    	
    }

    private void doClientStuff(final FMLClientSetupEvent event)
    {
    	
    }
    
    @SubscribeEvent
    public void serverStarting(final FMLServerStartingEvent event)
    {
        LOGGER.debug("Adding commands...");
        register(event.getCommandDispatcher());
    }

    
    public static void register(CommandDispatcher<CommandSource> dispatcher)
    {
    	LiteralCommandNode<CommandSource> CMD = ItemRainCommand.register(dispatcher);
    	LOGGER.info(CMD);
    }
}
