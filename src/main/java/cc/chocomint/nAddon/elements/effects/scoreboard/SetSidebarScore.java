package cc.chocomint.nAddon.elements.effects.scoreboard;

import javax.annotation.Nullable;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Team;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class SetSidebarScore extends Effect {
	
	private Expression<Player> ex_player;
	private Expression<String> ex_name;
	private Expression<String> ex_value;
	private Expression<Number> ex_line;
	
	static {
		Skript.registerEffect(SetSidebarScore.class, "set %player%'s sidebar %string% to %string% at line %number%");
		Skript.registerEffect(SetSidebarScore.class, "set sidebar of %player% %string% to %string% at line %number%");
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		
		ex_player = (Expression<Player>) exprs[0];
		ex_name = (Expression<String>) exprs[1];
		ex_value = (Expression<String>) exprs[2];
		ex_line = (Expression<Number>) exprs[3];
		
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "SetSidebarScore";
	}

	@Override
	protected void execute(Event e) {
		
		Player player = (Player) ex_player.getSingle(e);
		String name = (String) ex_name.getSingle(e);
		String value = (String) ex_value.getSingle(e);
		Number line = (Number) ex_line.getSingle(e);
		
		Team team = player.getScoreboard().getTeam("scoreboard-" + line);
		
		if(team == null) {
			team = player.getScoreboard().registerNewTeam("scoreboard-" + line);
			team.addEntry(name);
			player.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getScore(name).setScore(line.intValue());
		}
		team.setSuffix(value);
	}

}
