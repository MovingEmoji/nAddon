package cc.chocomint.nAddon;

import java.io.IOException;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import cc.chocomint.nAddon.listener.EventListener;
import cc.chocomint.nAddon.listener.MessageListener;
import cc.chocomint.nAddon.register.ClassRegister;
import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;

public class Main extends JavaPlugin{
	
	private static SkriptAddon Addon;
	
	public static boolean swm;
	
	
	public static Main getPlugin() {
		
		return (Main) JavaPlugin.getPlugin(Main.class);
	}
	
	public static SkriptAddon getAddon() {
		
		if(Addon == null) {
			Addon = Skript.registerAddon(getPlugin()); 
		}
		return Addon;
	}
	
	public void onEnable() {
		
		saveDefaultConfig();
		
		getServer().getPluginManager().registerEvents(new EventListener(), getPlugin());
		getServer().getMessenger().registerOutgoingPluginChannel((Plugin)this, "BungeeCord");
		getServer().getMessenger().registerIncomingPluginChannel((Plugin)this, "BungeeCord", new MessageListener());
		
		Skript.registerAddon(this);
		
		ClassRegister.registerAllSkriptClasses();
		
		try {
			getAddon().loadClasses("com.nagua.npatch.elements", new String[0]);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
