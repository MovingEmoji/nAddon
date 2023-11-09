package cc.chocomint.nAddon.elements.effects.scoreboard;

import javax.annotation.Nullable;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.scoreboard.Scoreboard;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class SetScoreboard extends Effect{
	
	private Expression<Player> ex_player;
	private Expression<Scoreboard> ex_scoreboard;
	
	static {
		Skript.registerEffect(SetScoreboard.class, "set %player%'s scoreboard to %scoreboard%");
		Skript.registerEffect(SetScoreboard.class, "set scoreboard of %player% to %scoreboard%");
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		
		this.ex_player = (Expression<Player>) exprs[0];
		this.ex_scoreboard = (Expression<Scoreboard>) exprs[1];
		
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "SetScoreboard";
	}

	@Override
	protected void execute(Event e) {
		
		Player player = (Player) this.ex_player.getSingle(e);
		Scoreboard scoreboard = (Scoreboard) this.ex_scoreboard.getSingle(e);
		
		player.setScoreboard(scoreboard);
		
		
	}

}
