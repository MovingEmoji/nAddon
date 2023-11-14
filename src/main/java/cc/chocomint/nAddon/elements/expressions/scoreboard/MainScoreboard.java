package cc.chocomint.nAddon.elements.expressions.scoreboard;

import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.scoreboard.Scoreboard;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class MainScoreboard extends SimpleExpression<Scoreboard> {
	
	static {
		Skript.registerExpression(MainScoreboard.class, Scoreboard.class, ExpressionType.COMBINED, "main scoreboard");
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends Scoreboard> getReturnType() {
		return Scoreboard.class;
	}

	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "MainScoreboard";
	}

	@Override
	protected @Nullable Scoreboard[] get(Event e) {
		Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
		return new Scoreboard[] {scoreboard};
	}

}
