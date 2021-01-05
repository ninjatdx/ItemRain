package com.formas.itemrain;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.formas.itemrain.init.ItemInit;

import net.minecraft.world.GameRules;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("itemrain")
@Mod.EventBusSubscriber(modid = ItemRain.MOD_ID, bus = Bus.MOD)
public class ItemRain
{
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "itemrain";
    public static GameRules.RuleKey<GameRules.BooleanValue> ITEM_RAIN;
    public static GameRules.RuleKey<GameRules.BooleanValue> MODDED_ITEMS;
    public static ItemRain instance;

    public ItemRain() {
    	final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    	modEventBus.addListener(this::setup);
    	modEventBus.addListener(this::doClientStuff);
    	
    	ITEM_RAIN = GameRules.register("itemRain", GameRules.BooleanValue.create(false));
    	MODDED_ITEMS = GameRules.register("rainModItems", GameRules.BooleanValue.create(true));
    	ItemInit.ITEMS.register(modEventBus);
    	
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
    	
    }

    private void doClientStuff(final FMLClientSetupEvent event)
    {
    	
    }
}
