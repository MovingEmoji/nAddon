package cc.chocomint.nAddon;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import cc.chocomint.nAddon.elements.ElementsCounter;
import cc.chocomint.nAddon.listener.EventListener;
import cc.chocomint.nAddon.listener.MessageListener;
import cc.chocomint.nAddon.register.ClassRegister;
import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;

public class Main extends JavaPlugin{
	
	private static SkriptAddon Addon;
	
	public static int Effects, Events, Expressions;
	
	public static int UPTIME_TICK;
	
	public static Main getPlugin() {
		
		return (Main) JavaPlugin.getPlugin(Main.class);
	}
	
	public static SkriptAddon getAddon() {
		
		if(Addon == null) {
			Addon = Skript.registerAddon(getPlugin()); 
		}
		return Addon;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void onEnable() {
		
		saveDefaultConfig();
		
		getServer().getPluginManager().registerEvents(new EventListener(), getPlugin());
		getServer().getMessenger().registerOutgoingPluginChannel((Plugin)this, "BungeeCord");
		getServer().getMessenger().registerIncomingPluginChannel((Plugin)this, "BungeeCord", new MessageListener());
		
		Addon = Skript.registerAddon(this);
		
		ClassRegister.registerAllSkriptClasses();
		
		Effects = Events = Expressions = 0;
		
		try {
			Addon.loadClasses("cc.chocomint.nAddon.elements", new String[0]);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ElementsCounter.infoElements();
		
		Bukkit.getServer().getScheduler().runTaskTimer(this, new BukkitRunnable() {
			
			@Override
			public void run() {
				UPTIME_TICK ++;
			}
		}, 0, 1);
	}
}
