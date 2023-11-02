package cc.chocomint.nAddon.elements.effects.tablist;

import java.lang.reflect.Field;

import javax.annotation.Nullable;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;

public class CustomTablist extends Effect {
	
	private Expression<String> ex_header;
	private Expression<String> ex_footer;
	private Expression<Player> ex_player;
	
	static {
		Skript.registerEffect(CustomTablist.class, "set tab header to %string% and footer to %string% for %players%");
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		
		this.ex_header = (Expression<String>) exprs[0];
		this.ex_footer = (Expression<String>) exprs[1];
		this.ex_player = (Expression<Player>) exprs[2];
		
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "CustomTablist";
	}

	@Override
	protected void execute(Event e) {
		
		String header = (String) ex_header.getSingle(e);
		String footer = (String) ex_footer.getSingle(e);
		Player[] players = (Player[]) ex_player.getAll(e);
		
		IChatBaseComponent headercomp = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + header.replace("\"", "") + "\"}");
		IChatBaseComponent footercomp = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + footer.replace("\"", "") + "\"}");
		
		PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();
		
		try {
			
			Field headerField = packet.getClass().getDeclaredField("a");
			headerField.setAccessible(true);
			headerField.set(packet, headercomp);
			headerField.setAccessible(!headerField.isAccessible());
			
			Field footerField = packet.getClass().getDeclaredField("b");
			footerField.setAccessible(true);
			footerField.set(packet, footercomp);
			footerField.setAccessible(!footerField.isAccessible());
			
		} catch (Exception exeption) {
			
			exeption.printStackTrace();
		}
		
		for(Player player : players) {
			
			CraftPlayer cplayer = (CraftPlayer) player;
			
			cplayer.getHandle().playerConnection.sendPacket((Packet<?>) packet);
			
		}
		
	}

}
