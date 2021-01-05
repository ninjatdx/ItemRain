package com.formas.itemrain.events;

import java.util.Arrays;

import com.formas.itemrain.ItemRain;

import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.Rarity;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = ItemRain.MOD_ID, bus = Bus.FORGE)
public class ItemRainEvent
{
	@SubscribeEvent
	public static void onPlayerTick(PlayerTickEvent event)
	{
		PlayerEntity player = event.player;
		World world = event.player.world;
		Item[] items = ForgeRegistries.ITEMS.getValues().toArray(new Item[ForgeRegistries.ITEMS.getValues().size()]);
		
		Item spawnItem = items[world.rand.nextInt(items.length)];
		Item[] blockedItems = new Item[] 
				{
					Items.DEBUG_STICK,
					Items.BARRIER,
					Items.STRUCTURE_VOID,
					Items.STRUCTURE_BLOCK,
					Items.COMMAND_BLOCK,
					Items.COMMAND_BLOCK_MINECART,
					Items.CHAIN_COMMAND_BLOCK,
					Items.REPEATING_COMMAND_BLOCK,
					Items.SPAWNER,
					Items.JIGSAW,
					Items.WRITTEN_BOOK,
					Items.KNOWLEDGE_BOOK,
					Items.ENCHANTED_BOOK
				};
		boolean isVanilla = spawnItem.getCreatorModId(new ItemStack(spawnItem)) == "minecraft" || spawnItem.getCreatorModId(new ItemStack(spawnItem)) == ItemRain.MOD_ID;
		boolean canSpawn = !Arrays.stream(blockedItems).anyMatch(spawnItem::equals) && world.getGameRules().getBoolean(ItemRain.MODDED_ITEMS) ? true : isVanilla;
		int offsetX = world.rand.nextInt(8 + 8) - 8;
		int offsetZ = world.rand.nextInt(8 + 8) - 8;
		if(world.getGameRules().getBoolean(ItemRain.ITEM_RAIN))
		{
			if(canSpawn)
			{
				if(spawnItem.getRarity(new ItemStack(spawnItem)) == Rarity.COMMON)
				{
					if(world.rand.nextInt(30) == 0)
					{
						world.addEntity(new ItemEntity(world, player.getPosX() + offsetX, 200, player.getPosZ() + offsetZ, new ItemStack(spawnItem)));
					}
				} else if(spawnItem.getRarity(new ItemStack(spawnItem)) == Rarity.UNCOMMON) {
					if(world.rand.nextInt(40) == 0)
					{
						world.addEntity(new ItemEntity(world, player.getPosX() + offsetX, 200, player.getPosZ() + offsetZ, new ItemStack(spawnItem)));
					}
				} else if(spawnItem.getRarity(new ItemStack(spawnItem)) == Rarity.RARE) {
					if(world.rand.nextInt(45) == 0)
					{
						world.addEntity(new ItemEntity(world, player.getPosX() + offsetX, 200, player.getPosZ() + offsetZ, new ItemStack(spawnItem)));
					}
				} else if(spawnItem.getRarity(new ItemStack(spawnItem)) == Rarity.EPIC) {
					if(world.rand.nextInt(50) == 0)
					{
						world.addEntity(new ItemEntity(world, player.getPosX() + offsetX, 200, player.getPosZ() + offsetZ, new ItemStack(spawnItem)));
					}
				} else {
					if(world.rand.nextInt(40) == 0)
					{
						world.addEntity(new ItemEntity(world, player.getPosX() + offsetX, 200, player.getPosZ() + offsetZ, new ItemStack(spawnItem)));
					}
				}
			} else {
				if(world.rand.nextInt(40) == 0)
				{
					world.addEntity(new TNTEntity(world, player.getPosX(), player.getPosY() + 40, player.getPosZ(), null));
				}
			}
		}
	}
}
