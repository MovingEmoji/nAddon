package cc.chocomint.nAddon.elements.effects.scoreboard;

import javax.annotation.Nullable;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class CreatePlayerSidebar extends Effect {
	
	private Expression<Player> ex_player;
	private Expression<String> ex_string;
	
	static {
		Skript.registerEffect(CreatePlayerSidebar.class, "create %player%'s sidebar named %string%");
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		
		this.ex_player = (Expression<Player>) exprs[0];
		this.ex_string = (Expression<String>) exprs[1];
		
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "CreatePlayerSidebar";
	}

	@Override
	protected void execute(Event e) {

		Player player = (Player) this.ex_player.getSingle(e);
		String string = (String) this.ex_string.getSingle(e);
		Scoreboard board = player.getScoreboard();
		Objective object = board.getObjective(DisplaySlot.SIDEBAR);
		
		if(object == null) {
			object = board.registerNewObjective("Sidebar", "dummy");
			object.setDisplaySlot(DisplaySlot.SIDEBAR);
		}
		
		object.setDisplayName(string);
	
		
	}

}
