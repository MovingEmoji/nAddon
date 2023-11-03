package cc.chocomint.nAddon.elements.effects;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import cc.chocomint.nAddon.Main;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class LoadTemplateWorld extends Effect {
	
	private Expression<String> ex_future_world;
	private Expression<String> ex_from_world;
	
	static {
		if(Main.getPlugin().getConfig().getBoolean("slimeworld") == true) {
			Skript.registerEffect(LoadTemplateWorld.class, "load template world %string% from %string%");
		}
	}

	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		
		this.ex_future_world = (Expression<String>) ex_future_world;
		this.ex_from_world = (Expression<String>) ex_from_world;
		
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void execute(Event e) {
		// TODO Auto-generated method stub
		
	}

}
