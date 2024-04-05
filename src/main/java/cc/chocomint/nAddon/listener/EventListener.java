package cc.chocomint.nAddon.listener;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import cc.chocomint.nAddon.Main;
import cc.chocomint.nAddon.customevents.CreatedDynamicValueEvent;
import cc.chocomint.nAddon.elements.effects.SetDynamicValue;
import cc.chocomint.nAddon.elements.expressions.web.RegisterWebApp;

public class EventListener implements Listener {
	
	@EventHandler
	public void onDynamicValue(CreatedDynamicValueEvent event) {
		if(Main.getPlugin().getConfig().getBoolean("webdynamicvalue") == true) {
			RegisterWebApp.WEB_APP.get(event.getKey(), ctx -> {
				ctx.json(SetDynamicValue.DynamicValue.get(event.getKey()));
			});
			Bukkit.broadcastMessage(RegisterWebApp.WEB_APP.toString() + "," + event.getKey());
		}
	}
	
	@EventHandler
	public void onPlayerLogin(PlayerLoginEvent event) {
		
		String ip = event.getHostname().toString().split(":")[0];
		List<String> tunnelips = Main.getPlugin().getConfig().getStringList("tunnelip");
		if(Main.getPlugin().getConfig().getBoolean("proxytunnel")) {
			if(tunnelips.contains(ip)) {
				event.allow();
			} else {
				Bukkit.getLogger().info(ip);
				event.disallow(PlayerLoginEvent.Result.KICK_OTHER, ChatColor.RED + "this proxy is not allowed");
			}
		}
	}
	
}
 