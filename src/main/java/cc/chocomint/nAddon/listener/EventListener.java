package cc.chocomint.nAddon.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

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
}
 