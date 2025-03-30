package cc.chocomint.nAddon.elements.effects.packet;

import javax.annotation.Nullable;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import cc.chocomint.nAddon.Main;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class RespawnPlayer extends Effect {
	
	private Expression<Player> ex_player;
	
	static {
		Skript.registerEffect(RespawnPlayer.class, "force respawn %player%");
		Main.Effects ++;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		
		this.ex_player = (Expression<Player>) exprs[0];
		
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "RespawnPlayer";
	}

	@Override
	protected void execute(Event e) {
		
		Player player = (Player) this.ex_player.getSingle(e);
		
		player.spigot().respawn();
		
	}

}
