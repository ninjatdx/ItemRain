package com.formas.itemrain.init;

import com.formas.itemrain.ItemRain;

import net.minecraft.item.Item;
import net.minecraft.item.Rarity;
import net.minecraft.item.SimpleFoiledItem;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit
{
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ItemRain.MOD_ID);
	
	public static final RegistryObject<Item> TROPHY = ITEMS.register("trophy",
			() -> new SimpleFoiledItem(new Item.Properties().maxStackSize(1).rarity(Rarity.EPIC)));
}
