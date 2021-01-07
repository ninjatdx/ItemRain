package com.formas.itemrain.init;

import java.lang.reflect.Field;

import net.minecraft.world.GameRules;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

@SuppressWarnings("deprecation")
public class GameRuleInit
{
	public static GameRules.RuleKey<GameRules.BooleanValue> ITEM_RAIN = null;
	public static GameRules.RuleKey<GameRules.BooleanValue> MODDED_ITEMS = null;

	public static void registerGameRules() {
		try {
			registerBooleanGameRule(GameRuleInit.class.getField("ITEM_RAIN"), "itemRain", false);
			registerBooleanGameRule(GameRuleInit.class.getField("MODDED_ITEMS"), "rainModItems", true);
		}
		catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private static void registerBooleanGameRule(Field field, String name, boolean defaultValue) {
		DeferredWorkQueue.runLater(() -> {
			try {	
				field.set(null, GameRules.register(name, (GameRules.RuleType<GameRules.BooleanValue>) ObfuscationReflectionHelper.findMethod(GameRules.BooleanValue.class, "func_223568_b", boolean.class).invoke(null, defaultValue)));
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
