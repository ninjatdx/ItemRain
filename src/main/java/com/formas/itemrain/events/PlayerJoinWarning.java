package com.formas.itemrain.events;

import com.formas.itemrain.ItemRain;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.VersionChecker;
import net.minecraftforge.fml.VersionChecker.CheckResult;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.forgespi.language.IModInfo;

@Mod.EventBusSubscriber(modid = ItemRain.MOD_ID, bus = Bus.FORGE)
public class PlayerJoinWarning
{
	@SubscribeEvent
	public static void playerJoins(PlayerEvent.PlayerLoggedInEvent event)
	{
		ModContainer mod = ModList.get().getModContainerById(ItemRain.MOD_ID).get();
		CheckResult info = VersionChecker.getResult((IModInfo) mod.getModInfo());
		if(info.status == VersionChecker.Status.OUTDATED)
		{
			PlayerEntity player = event.getPlayer();
	        if (player instanceof ServerPlayerEntity)
	        {
	        	Style style = new Style().setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new StringTextComponent("\u00A72Click to go to the CurseForge's page"))).setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.curseforge.com/minecraft/mc-mods/item-rain"));
	        	player.sendMessage(new StringTextComponent("\u00A73" + player.getName().getFormattedText() + "\u00A7r >> Your Item Rain is \u00A7coutdated\u00A7r! You're currently on\u00A7c " + mod.getModInfo().getVersion() + "\u00A7r and the most recent version is \u00A72" + info.target + "\u00A7r. Go to \u00A7b").appendSibling(new StringTextComponent("the Curseforge page").setStyle(style)).appendSibling(new StringTextComponent("\u00A7r to update it.")));	  
	        }
		} else {
			PlayerEntity player = event.getPlayer();
	        if (player instanceof ServerPlayerEntity)
	        {
	        	player.sendMessage(new StringTextComponent("\u00A73" + player.getName().getFormattedText() + "\u00A7r >> Item Rain \u00A7a" + mod.getModInfo().getVersion() + " \u00A7ris loaded! Status: \u00A75" + info.status));
	        }
        }
	}
}
