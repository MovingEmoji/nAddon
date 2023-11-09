package cc.chocomint.nAddon.elements.events;

import javax.annotation.Nullable;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerVelocityEvent;
import org.bukkit.util.Vector;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;

public class VelocityEvent extends SkriptEvent {
	
	static {
		Skript.registerEvent("PlayerVelocityEvent", VelocityEvent.class, PlayerVelocityEvent.class, "player velocity event");
		
		EventValues.registerEventValue(PlayerVelocityEvent.class, Vector.class, new Getter<Vector, PlayerVelocityEvent>() {
			@Nullable
			public Vector get(PlayerVelocityEvent e) {
				return e.getVelocity();
			}
		}, 0);
		
		EventValues.registerEventValue(PlayerVelocityEvent.class, Player.class, new Getter<Player, PlayerVelocityEvent>() {
			@Nullable
			public Player get(PlayerVelocityEvent e) {
				return e.getPlayer();
			}
		}, 0);
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "PlayerVelocityEvent";
	}

	@Override
	public boolean init(Literal<?>[] args, int matchedPattern, ParseResult parseResult) {
		return true;
	}

	@Override
	public boolean check(Event e) {
		return e instanceof PlayerVelocityEvent;
	}

}
