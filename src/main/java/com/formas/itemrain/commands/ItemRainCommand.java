package com.formas.itemrain.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.tree.LiteralCommandNode;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.GameRules;

public class ItemRainCommand implements Command<CommandSource> {

    public static LiteralCommandNode<CommandSource> register(CommandDispatcher<CommandSource> dispatcher) {
        LiteralArgumentBuilder<CommandSource> itemrainconfig = Commands.literal("ircfg")
                .requires(cs -> cs.hasPermissionLevel(2));
        GameRules.visitAll(new GameRules.IRuleEntryVisitor() {
            public <T extends GameRules.RuleValue<T>> void visit(GameRules.RuleKey<T> key, GameRules.RuleType<T> type) {
                itemrainconfig.then(Commands.literal(key.getName()).executes((src) -> ItemRainCommand.get(src.getSource(), key)).then(type.createArgument("value").executes((src) -> ItemRainCommand.set(src, key))));
            }
        });
        return dispatcher.register(itemrainconfig);
    }

    private static <T extends GameRules.RuleValue<T>> int set(CommandContext<CommandSource> src, GameRules.RuleKey<T> key) {
        CommandSource commandsource = src.getSource();
        T t = commandsource.getServer().getGameRules().get(key);
        t.updateValue(src, "value");
        commandsource.sendFeedback(new TranslationTextComponent("commands.gamerule.set", key.getName(), t.toString()), true);
        return t.intValue();
    }

    private static <T extends GameRules.RuleValue<T>> int get(CommandSource src, GameRules.RuleKey<T> key) {
        T t = src.getServer().getGameRules().get(key);
        src.sendFeedback(new TranslationTextComponent("commands.gamerule.query", key.getName(), t.toString()), false);
        return t.intValue();
    }

    @Override
    public int run(CommandContext<CommandSource> context) throws CommandSyntaxException {
        return 0;
    }
}

