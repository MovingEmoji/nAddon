package cc.chocomint.nAddon.elements.effects.packet;

import javax.annotation.Nullable;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import cc.chocomint.nAddon.Main;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.util.Time;
import ch.njol.util.Kleenean;

public class SetPlayerTime extends Effect {
	
	private Expression<Player> ex_player;
	private Expression<Time> ex_time;
	
	static {
		Skript.registerEffect(SetPlayerTime.class, "set %player%'s time to %time%");
		Skript.registerEffect(SetPlayerTime.class, "set time of %player% to %time%");
		Main.Effects ++;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		
		this.ex_player = (Expression<Player>) exprs[0];
		this.ex_time = (Expression<Time>) exprs[1];
		
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "SetPlayerTime";
	}

	@Override
	protected void execute(Event e) {
		
		Player player = (Player) this.ex_player.getSingle(e);
		Time time = (Time) this.ex_time.getSingle(e);
		
		player.setPlayerTime(time.getTicks(), false);
	}

}
