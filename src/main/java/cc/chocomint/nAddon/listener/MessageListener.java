
package cc.chocomint.nAddon.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import cc.chocomint.nAddon.customevents.PluginMessageRecievedEvent;

public class MessageListener implements PluginMessageListener {

	@Override
	public void onPluginMessageReceived(String channel, Player player, byte[] message) {
		PluginMessageRecievedEvent event = new PluginMessageRecievedEvent(channel, player, message);
		Bukkit.getPluginManager().callEvent(event);
	}

}
