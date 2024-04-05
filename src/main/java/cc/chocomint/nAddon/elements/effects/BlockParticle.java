package cc.chocomint.nAddon.elements.effects;

import javax.annotation.Nullable;

import org.bukkit.Location;
import org.bukkit.event.Event;

import cc.chocomint.nAddon.Main;
import ch.njol.skript.Skript;
import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class BlockParticle extends Effect {
	
	private Expression<Number> ex_times;
	private Expression<Location> ex_location;
	private Expression<ItemType> ex_itemtype;
	
	static {
		Skript.registerEffect(BlockParticle.class, "play block particle %number% times at %location% with %itemtype%");
		Main.Effects ++;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		this.ex_times = (Expression<Number>) exprs[0];
		this.ex_location = (Expression<Location>) exprs[1];
		this.ex_itemtype = (Expression<ItemType>) exprs[2];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "BlockParticle";
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void execute(Event e) {
		
		Number times = (Number) this.ex_times.getSingle(e);
		Location location = (Location) this.ex_location.getSingle(e);
		ItemType itemtype = (ItemType) this.ex_itemtype.getSingle(e);
		
		for(int i = 0; i < times.intValue(); i++) {
			location.getWorld().playEffect(location, org.bukkit.Effect.TILE_BREAK, itemtype.getRandom().getType().getId());
		}
		
	}

}
