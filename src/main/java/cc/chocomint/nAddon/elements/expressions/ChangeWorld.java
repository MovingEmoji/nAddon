package cc.chocomint.nAddon.elements.expressions;

import javax.annotation.Nullable;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.Event;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ChangeWorld extends SimpleExpression<Location> {
	
	private Expression<Location> ex_location;
	private Expression<World> ex_world;
	
	static {
		Skript.registerExpression(ChangeWorld.class, Location.class, ExpressionType.COMBINED, "change world %location% to %world%");
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends Location> getReturnType() {
		return Location.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		
		this.ex_location = (Expression<Location>) exprs[0];
		this.ex_world = (Expression<World>) exprs[1];
		
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "ChangeWorld";
	}

	@Override
	protected @Nullable Location[] get(Event e) {
		
		Location location = (Location) this.ex_location.getSingle(e).clone();
		World world = (World) this.ex_world.getSingle(e);
		
		location.setWorld(world);
		
		return new Location[] {location};
	}

}
