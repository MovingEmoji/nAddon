package cc.chocomint.nAddon.elements;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import cc.chocomint.nAddon.Main;

public class ElementsCounter {
	
	public static void infoElements() {
		
		Bukkit.getLogger().info("Successfully registered Elements.");
		Bukkit.getLogger().info(ChatColor.AQUA + "Events: " + ChatColor.RESET + Main.Events + ChatColor.AQUA + "  Effects: " + ChatColor.RESET + Main.Effects + ChatColor.AQUA + "  Expressions: " + ChatColor.RESET + Main.Expressions);
	}

}
