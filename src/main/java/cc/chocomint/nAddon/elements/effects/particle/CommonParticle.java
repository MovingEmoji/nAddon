package cc.chocomint.nAddon.elements.effects.particle;

import javax.annotation.Nullable;

import org.bukkit.Location;
import org.bukkit.event.Event;

import cc.chocomint.nAddon.Main;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class CommonParticle extends Effect {
	
	private Expression<Number> ex_times;
	private Expression<Location> ex_location;
	private Expression<String> ex_particle;
	
	static {
		Skript.registerEffect(CommonParticle.class, "play common particle %number% times at %location% with %string%");
		Main.Effects ++;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		this.ex_times = (Expression<Number>) exprs[0];
		this.ex_location = (Expression<Location>) exprs[1];
		this.ex_particle = (Expression<String>) exprs[2];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "CommonParticle";
	}

	@Override
	protected void execute(Event e) {
		
		Number times = (Number) this.ex_times.getSingle(e);
		Location location = (Location) this.ex_location.getSingle(e);
		String particle = (String) this.ex_particle.getSingle(e);
		
		for(int i = 0; i < times.intValue(); i++) {
			location.getWorld().playEffect(location, org.bukkit.Effect.valueOf(particle), 0);
		}
		
	}

}
