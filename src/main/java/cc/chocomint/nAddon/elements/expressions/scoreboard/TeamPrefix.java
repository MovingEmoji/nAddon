package cc.chocomint.nAddon.elements.expressions.scoreboard;

import javax.annotation.Nullable;

import org.bukkit.event.Event;
import org.bukkit.scoreboard.Team;

import cc.chocomint.nAddon.Main;
import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;

public class TeamPrefix extends SimpleExpression<String> {
	
	private Expression<Team> ex_team;
	
	static {
		Skript.registerExpression(TeamPrefix.class, String.class, ExpressionType.COMBINED, "prefix of team %team%");
		Skript.registerExpression(TeamPrefix.class, String.class, ExpressionType.COMBINED, "team %team%'s prefix");
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
		
		this.ex_team = (Expression<Team>) exprs[0];
		
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "TeamPrefix";
	}

	@Override
	protected @Nullable String[] get(Event e) {
		
		Team team = (Team) this.ex_team.getSingle(e);
		
		String prefix = team.getPrefix();
		
		return new String[] {prefix};
	}
	
	public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
		
		Team team = (Team) this.ex_team.getSingle(e);
		
		switch(mode) {
			case SET:
				team.setPrefix((String) delta[0]);
				break;
				
			case DELETE:
				team.setPrefix("");
				break;
				
			default:
				break;
		}
	}
	
	public Class<?>[] acceptChange(Changer.ChangeMode mode) {
		
		switch(mode) {
			case SET:
				return (Class[]) CollectionUtils.array((Object[]) new Class[] {String.class}); 
				
			case DELETE:
				return (Class[]) CollectionUtils.array((Object[]) new Class[] {String.class}); 
				
			default:
				return null;
		}
	}
}