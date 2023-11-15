package cc.chocomint.nAddon.elements.effects.tablist;

import javax.annotation.Nullable;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import cc.chocomint.nAddon.Main;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class SetTabName extends Effect {
	
	private Expression<Player> ex_player;
	private Expression<String> ex_name;
	
	static {
		Skript.registerEffect(SetTabName.class, "set %player%'s tab name to %string%");
		Skript.registerEffect(SetTabName.class, "set tab name of %player% to %string%");
		Main.Effects ++;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		
		this.ex_player = (Expression<Player>) exprs[0];
		this.ex_name = (Expression<String>) exprs[1];
		
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "SetTabName";
	}

	@Override
	protected void execute(Event e) {
		
		Player player = (Player) this.ex_player.getSingle(e);
		String name = (String) this.ex_name.getSingle(e);
		
		player.setPlayerListName(name);
	}

}
