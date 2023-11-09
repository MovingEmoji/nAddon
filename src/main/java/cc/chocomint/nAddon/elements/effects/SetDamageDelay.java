package cc.chocomint.nAddon.elements.effects;

import javax.annotation.Nullable;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class SetDamageDelay extends Effect {
	
	private Expression<LivingEntity> ex_entity;
	private Expression<Number> ex_tick;
	
	static {
		Skript.registerEffect(SetDamageDelay.class, "set %player%'s maximum damage delay to %number%");
		Skript.registerEffect(SetDamageDelay.class, "set maximum damage delay of %player% to %number%");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		
		this.ex_entity = (Expression<LivingEntity>) exprs[0];
		this.ex_tick = (Expression<Number>) exprs[1];
		
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "SetDamageTick";
	}

	@Override
	protected void execute(Event e) {
		
		LivingEntity entity = (LivingEntity) this.ex_entity.getSingle(e);
		Number tick = (Number) this.ex_tick.getSingle(e);
		
		entity.setMaximumNoDamageTicks(tick.intValue());
		
		
	}

}
