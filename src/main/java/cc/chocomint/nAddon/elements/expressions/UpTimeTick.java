package cc.chocomint.nAddon.elements.expressions;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import cc.chocomint.nAddon.Main;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class UpTimeTick extends SimpleExpression<Number> {
	
	static {
		Skript.registerExpression(UpTimeTick.class, Number.class, ExpressionType.COMBINED, "plugin uptime tick");
		Main.Expressions ++;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends Number> getReturnType() {
		return Number.class;
	}

	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "UpTimeTick";
	}

	@Override
	protected @Nullable Number[] get(Event e) {		
		return new Number[] {(Number) Main.UPTIME_TICK};
	}

}
