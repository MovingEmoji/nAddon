package cc.chocomint.nAddon.elements.events;

import javax.annotation.Nullable;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import cc.chocomint.nAddon.Main;
import cc.chocomint.nAddon.customevents.PluginMessageRecievedEvent;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;

public class PluginMessageEvent extends SkriptEvent{
	
	static {	
		Skript.registerEvent("PluginMessageEvent", PluginMessageEvent.class, PluginMessageRecievedEvent.class, "plugin message event");
		
		EventValues.registerEventValue(PluginMessageRecievedEvent.class, String.class, new Getter<String, PluginMessageRecievedEvent>() {
			@Nullable
			public String get(PluginMessageRecievedEvent e) {
				return e.getChannel();
			}
		}, 0);
		
		EventValues.registerEventValue(PluginMessageRecievedEvent.class, Player.class, new Getter<Player, PluginMessageRecievedEvent>() {
			@Nullable
			public Player get(PluginMessageRecievedEvent e) {
				return e.getPlayer();
			}
		}, 0);
		
		EventValues.registerEventValue(PluginMessageRecievedEvent.class, byte[].class, new Getter<byte[], PluginMessageRecievedEvent>() {
			@Nullable
			public byte[] get(PluginMessageRecievedEvent e) {
				return e.getMessage();
			}
		}, 0);
		
		Main.Events ++;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "PluginMessageEvent";
	}

	@Override
	public boolean init(Literal<?>[] args, int matchedPattern, ParseResult parseResult) {
		return true;
	}

	@Override
	public boolean check(Event e) {
		return e instanceof PluginMessageRecievedEvent;
	}

}
