package cc.chocomint.nAddon.elements.expressions.scoreboard;

import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.scoreboard.Scoreboard;

import cc.chocomint.nAddon.Main;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class NewScoreboard extends SimpleExpression<Scoreboard> {
	
	static {
		Skript.registerExpression(NewScoreboard.class, Scoreboard.class, ExpressionType.COMBINED, "new scoreboard");
		Main.Expressions ++;
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
		return "NewScoreboard";
	}

	@Override
	protected @Nullable Scoreboard[] get(Event e) {
		Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
		return new Scoreboard[] {scoreboard};
	}

}
