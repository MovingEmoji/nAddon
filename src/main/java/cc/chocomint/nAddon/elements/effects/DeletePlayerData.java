package cc.chocomint.nAddon.elements.effects;

import java.io.File;

import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import cc.chocomint.nAddon.Main;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class DeletePlayerData extends Effect {
	
	private Expression<Player> ex_player;
	
	static {
		Skript.registerEffect(DeletePlayerData.class, "delete %player%'s playerdata");
		Main.Effects ++;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		
		this.ex_player = (Expression<Player>) exprs[0];
		
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "DeletePlayerData";
	}

	@Override
	protected void execute(Event e) {
		
		Player player = (Player) this.ex_player.getSingle(e);
		
		File datafile = new File(player.getWorld().getWorldFolder().getAbsolutePath() + "/playerdata/" + player.getUniqueId() + ".dat");
		File statsfile = new File(player.getWorld().getWorldFolder().getAbsolutePath() + "/stats/" + player.getUniqueId() + ".json");
		if(!datafile.exists()) {
			datafile = new File(Bukkit.getWorld("world").getWorldFolder().getAbsolutePath() + "/playerdata/" + player.getUniqueId() + ".dat");
		}

		if(!statsfile.exists()) {
			statsfile = new File(Bukkit.getWorld("world").getWorldFolder().getAbsolutePath() + "/stats/" + player.getUniqueId() + ".json");
		}
		statsfile.delete();
		datafile.delete();
		
	}

}
