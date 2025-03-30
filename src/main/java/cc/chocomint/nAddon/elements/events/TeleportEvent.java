package cc.chocomint.nAddon.elements.events;

import javax.annotation.Nullable;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityTeleportEvent;

import cc.chocomint.nAddon.Main;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;

public class TeleportEvent extends SkriptEvent {
	
	static {
		Skript.registerEvent("TeleportEvent", TeleportEvent.class, EntityTeleportEvent.class, "[naddon ]entity teleport");
		
		EventValues.registerEventValue(EntityTeleportEvent.class, Entity.class, new Getter<Entity, EntityTeleportEvent>() {
			@Nullable
			public Entity get(EntityTeleportEvent e) {
				return e.getEntity();
			}
		}, 0);
		
		EventValues.registerEventValue(EntityTeleportEvent.class, Location.class, new Getter<Location, EntityTeleportEvent>() {
			@Nullable
			public Location get(EntityTeleportEvent e) {
				return e.getTo();
			}
		}, EventValues.TIME_FUTURE);
		
		EventValues.registerEventValue(EntityTeleportEvent.class, Location.class, new Getter<Location, EntityTeleportEvent>() {
			@Nullable
			public Location get(EntityTeleportEvent e) {
				return e.getFrom();
			}
		}, EventValues.TIME_PAST);
		Main.Events ++;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "EntityTeleportEvent";
	}

	@Override
	public boolean init(Literal<?>[] args, int matchedPattern, ParseResult parseResult) {
		return true;
	}

	@Override
	public boolean check(Event e) {
		return e instanceof EntityTeleportEvent;
	}

}
