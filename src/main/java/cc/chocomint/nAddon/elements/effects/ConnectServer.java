package cc.chocomint.nAddon.elements.effects;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.annotation.Nullable;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import cc.chocomint.nAddon.Main;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class ConnectServer extends Effect {
	
	private Expression<Player> ex_player;
	private Expression<String> ex_string;
	
	static {
		Skript.registerEffect(ConnectServer.class, "send %players% to server %string%");
		Main.Effects ++;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		
		this.ex_player = (Expression<Player>) exprs[0];
		this.ex_string = (Expression<String>) exprs[1];
		
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "ConnectServer";
	}

	@Override
	protected void execute(Event e) {
		
		Player[] players = (Player[]) this.ex_player.getAll(e);
		String string = (String) this.ex_string.getSingle(e);
		ByteArrayOutputStream b = new ByteArrayOutputStream();
	    DataOutputStream out = new DataOutputStream(b);
	    try {
	      out.writeUTF("Connect");
	      out.writeUTF(string);
	    } catch (IOException iOException) {}
		
		for(Player player : players) {
			player.sendPluginMessage(Main.getPlugin(), "BungeeCord", b.toByteArray());
		}
	}

}
