package cc.chocomint.nAddon.elements.effects;

import javax.annotation.Nullable;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class RevealPlayer extends Effect{
	
	private Expression<Player> ex_player;
	private Expression<Player> ex_target;
	
	static {
		Skript.registerEffect(RevealPlayer.class, "reveal %player% from %players%");
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		
		this.ex_player = (Expression<Player>) exprs[0];
		this.ex_target = (Expression<Player>) exprs[1];
		
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "RevealPlayer";
	}

	@Override
	protected void execute(Event e) {
		
		Player player = (Player) this.ex_player.getSingle(e);
		Player[] targets = (Player[]) this.ex_target.getAll(e);
		
		for(Player target : targets) {
			target.showPlayer(player);
		}
		
	}

}
