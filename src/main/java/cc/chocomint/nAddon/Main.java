package cc.chocomint.nAddon;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	
	public void onEnable() {
		Bukkit.getServer().broadcastMessage("test");
	}
}
