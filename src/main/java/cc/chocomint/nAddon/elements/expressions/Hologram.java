package cc.chocomint.nAddon.elements.expressions;

import javax.annotation.Nullable;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.Event;

import cc.chocomint.nAddon.Main;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class Hologram extends SimpleExpression<Entity> {
	
	private Expression<String> ex_text;
	private Expression<Location> ex_location;
	
	static {
		Skript.registerExpression(Hologram.class, Entity.class, ExpressionType.COMBINED, "new hologram named %string% at %location%");
		Main.Expressions ++;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends Entity> getReturnType() {
		return Entity.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		this.ex_text = (Expression<String>) exprs[0];
		this.ex_location = (Expression<Location>) exprs[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "Hologram";
	}

	@Override
	protected @Nullable Entity[] get(Event e) {
		String name = (String) ex_text.getSingle(e);
		Location location = (Location) ex_location.getSingle(e);
		ArmorStand stand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
		
		stand.setVisible(false);
		stand.setBasePlate(false);
		stand.setGravity(false);
		stand.setSmall(true);
		stand.setMarker(true);
		stand.setCustomName(name);
		stand.setCustomNameVisible(true);
		
		return new Entity[] {(Entity) stand};
	}

}
