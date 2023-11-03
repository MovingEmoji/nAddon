package cc.chocomint.nAddon.elements.effects.scoreboard;

import javax.annotation.Nullable;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.scoreboard.Team;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class ResetPlayerSidebar extends Effect {
	
	private Expression<Player> ex_player;
	private Expression<String> ex_score;
	private Expression<Number> ex_line;
	
	static {
		Skript.registerEffect(ResetPlayerSidebar.class, "reset %player%'s sidebar %string% at line %number%");
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		
		this.ex_player = (Expression<Player>) exprs[0];
		this.ex_score = (Expression<String>) exprs[1];
		this.ex_line = (Expression<Number>) exprs[2];
		
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "ResetPlayerSidebar";
	}

	@Override
	protected void execute(Event e) {
		
		Player player = (Player) this.ex_player.getSingle(e);
		String score = (String) this.ex_score.getSingle(e);
		Number line = (Number) this.ex_line.getSingle(e);
		
		Team team = player.getScoreboard().getTeam("scoreboard-" + line);
		
		if(team != null) {
			if(team.getEntries().contains(score)) {
				
				team.unregister();
				player.getScoreboard().resetScores(score);
			}
		}
	}

}
