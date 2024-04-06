package cc.chocomint.nAddon.elements.expressions;

import javax.annotation.Nullable;

import org.bukkit.ChatColor;
import org.bukkit.event.Event;

import cc.chocomint.nAddon.Main;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class LastColor extends SimpleExpression<String> {
	
	private Expression<String> ex_text;
	
	static {
		Skript.registerExpression(LastColor.class, String.class, ExpressionType.COMBINED, "last color of %string%");
		Skript.registerExpression(LastColor.class, String.class, ExpressionType.COMBINED, "%string%'s last color");
		Main.Expressions ++;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		this.ex_text = (Expression<String>) exprs[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "LastColor";
	}

	@Override
	protected @Nullable String[] get(Event e) {
		String text = (String) ex_text.getSingle(e);
		String color = ChatColor.getLastColors(text);
		return new String[] {color};
	}

}
