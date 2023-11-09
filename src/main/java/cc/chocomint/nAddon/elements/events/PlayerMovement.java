package cc.chocomint.nAddon.elements.events;

import javax.annotation.Nullable;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerMoveEvent;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;

public class PlayerMovement extends SkriptEvent {
	
	static {
		Skript.registerEvent("PlayerMovementEvent", PlayerMovement.class, PlayerMoveEvent.class, "any move[ment]");
		
		EventValues.registerEventValue(PlayerMoveEvent.class, Player.class, new Getter<Player, PlayerMoveEvent>() {
			public Player get(PlayerMoveEvent e) {
				return e.getPlayer();
			}
		}, 0);
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "PlayerMovementEvent";
	}

	@Override
	public boolean init(Literal<?>[] args, int matchedPattern, ParseResult parseResult) {
		return true;
	}

	@Override
	public boolean check(Event e) {
		return e instanceof PlayerMoveEvent;
	}

}
