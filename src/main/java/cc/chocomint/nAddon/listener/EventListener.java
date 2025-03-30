package cc.chocomint.nAddon.listener;

import java.util.List;

import cc.chocomint.nAddon.elements.effects.packet.TagAboveHead;
import net.minecraft.server.v1_8_R3.MathHelper;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityTeleport;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import cc.chocomint.nAddon.Main;
import cc.chocomint.nAddon.customevents.CreatedDynamicValueEvent;
import cc.chocomint.nAddon.elements.effects.web.SetDynamicValue;
import cc.chocomint.nAddon.elements.expressions.web.RegisterWebApp;
import org.bukkit.event.player.PlayerQuitEvent;

public class EventListener implements Listener {
	
	@EventHandler
	public void onPlayerLogin(PlayerLoginEvent event) {
		
		String ip = event.getHostname().toString().split(":")[0];
		List<String> tunnelips = Main.getPlugin().getConfig().getStringList("tunnelip");
		if(Main.getPlugin().getConfig().getBoolean("proxytunnel")) {
			if(tunnelips.contains(ip)) {
				event.allow();
				Thread thread = new Thread(new Runnable() {
					@Override
					public void run() {
                        try {
                            Thread.sleep(2500);
							for(Player player : Bukkit.getOnlinePlayers()) {
								ArmorStand stand = TagAboveHead.tags.get(player.getName());
								if(stand != null) {
									PacketPlayOutEntityTeleport packet = new PacketPlayOutEntityTeleport(
											stand.getEntityId(),
											MathHelper.floor(player.getLocation().getX() * 32.0),
											MathHelper.floor((player.getLocation().getY() + 2.1) * 32.0),
											MathHelper.floor(player.getLocation().getZ() * 32.0),
											(byte) 0, (byte) 0, true);
									PacketListener.pass = true;
									((CraftPlayer) event.getPlayer()).getHandle().playerConnection.sendPacket(packet);
								}
							}
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }

                    }
				});
				thread.start();
			} else {
				Bukkit.getLogger().info(ip);
				event.disallow(PlayerLoginEvent.Result.KICK_OTHER, ChatColor.RED + "this proxy is not allowed");
			}
		}
	}

	@EventHandler
	public void onPlayerLogout(PlayerQuitEvent event) {
		ArmorStand stand = TagAboveHead.tags.get(event.getPlayer().getName());
		if(stand != null) {
			TagAboveHead.tags.remove(event.getPlayer().getName());
			stand.remove();
		}
	}
	
}
 