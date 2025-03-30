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

public class SetFireTick extends Effect {
	
	private Expression<Player> ex_player;
	private Expression<Number> ex_tick;
	
	static {
		Skript.registerEffect(SetFireTick.class, "set %player%'s fire tick to %number%");
		Skript.registerEffect(SetFireTick.class, "set fire tick of %player% to %number%");
		Main.Effects ++;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		
		this.ex_player = (Expression<Player>) exprs[0];
		this.ex_tick = (Expression<Number>) exprs[1];
		
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "SetFireTick";
	}

	@Override
	protected void execute(Event e) {
		
		Player player = (Player) this.ex_player.getSingle(e);
		Number tick = (Number) this.ex_tick.getSingle(e);
		
		player.setFireTicks(tick.intValue());
		
	}

}
