package cc.chocomint.nAddon.customevents;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class CreatedDynamicValueEvent extends Event {
	
	public static final HandlerList HANDLERS = new HandlerList();
	private final String key;
	
	public static HandlerList getHandlerList() {
		return HANDLERS;
	}

	@Override
	public HandlerList getHandlers() {
		return HANDLERS;
	}
	
	public CreatedDynamicValueEvent(String key) {
		
		this.key = key;
		
	}
	
	public String getKey() {
		return this.key;
	}

}
