package cc.chocomint.nAddon.customevents;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.json.simple.JSONObject;

public class CreatedDynamicValueEvent extends Event {
	
	public static final HandlerList HANDLERS = new HandlerList();
	private final String key;
	private final Object object;
	
	public static HandlerList getHandlerList() {
		return HANDLERS;
	}

	@Override
	public HandlerList getHandlers() {
		return HANDLERS;
	}
	
	public CreatedDynamicValueEvent(String key, Object object) {
		
		this.key = key;
		this.object = object;
	}
	
	public String getKey() {
		return this.key;
	}

	public Object getJson() {
		return this.object;
	}
}
