package cc.chocomint.nAddon.elements.expressions.scoreboard;

import javax.annotation.Nullable;

import org.bukkit.event.Event;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import cc.chocomint.nAddon.Main;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ScoreboardTeam extends SimpleExpression<Team> {
	
	private Expression<String> ex_name;
	private Expression<Scoreboard> ex_scoreboard;
	
	static {
		Skript.registerExpression(ScoreboardTeam.class, Team.class, ExpressionType.COMBINED, "team named %string% in %scoreboard%");
		Main.Expressions ++;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends Team> getReturnType() {
		return Team.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		
		this.ex_name = (Expression<String>) exprs[0];
		this.ex_scoreboard = (Expression<Scoreboard>) exprs[1];
		
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "ScoreboardTeam";
	}

	@Override
	protected Team[] get(Event e) {
		
		String name = (String) this.ex_name.getSingle(e);
		Scoreboard scoreboard = (Scoreboard) this.ex_scoreboard.getSingle(e);
		
		Team team = scoreboard.getTeam(name);
		return new Team[] {team};
	}

}
