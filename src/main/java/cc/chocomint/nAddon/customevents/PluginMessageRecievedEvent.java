package cc.chocomint.nAddon.customevents;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PluginMessageRecievedEvent extends Event {
	
	public static final HandlerList HANDLERS = new HandlerList();
	private final String channel;
	private final Player player;
	private final byte[] message;
	
	public static HandlerList getHandlerList() {
		return HANDLERS;
	}
	
	public PluginMessageRecievedEvent(String channel, Player player, byte[] message) {
	
		this.channel = channel;
		this.player = player;
		this.message = message;
	}

	@Override
	public HandlerList getHandlers() {
		return HANDLERS;
	}
	
	public String getChannel() {
		return this.channel;
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public byte[] getMessage() {
		return this.message;
	}

}
