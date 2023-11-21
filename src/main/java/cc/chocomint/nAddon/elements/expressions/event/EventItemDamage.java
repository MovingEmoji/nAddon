package cc.chocomint.nAddon.elements.expressions.event;

import javax.annotation.Nullable;

import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerItemDamageEvent;

import cc.chocomint.nAddon.Main;
import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;

public class EventItemDamage extends SimpleExpression<Number> {
	
	static {
		Skript.registerExpression(EventItemDamage.class, Number.class, ExpressionType.SIMPLE, "item damage");
		Main.Expressions ++;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends Number> getReturnType() {
		return Number.class;
	}

	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "EventItemDamage";
	}

	@Override
	protected @Nullable Number[] get(Event e) {
		return new Number[] {(Number) ((PlayerItemDamageEvent) e).getDamage()};
	}
	
	public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
		
		Number damage = (Number) delta[0];
		
		switch(mode) {
			case SET:
				((PlayerItemDamageEvent) e).setDamage(damage.intValue());
				break;
				
			case ADD:
				((PlayerItemDamageEvent) e).setDamage(((PlayerItemDamageEvent) e).getDamage() + damage.intValue());
				break;
			
			case REMOVE:
				((PlayerItemDamageEvent) e).setDamage(((PlayerItemDamageEvent) e).getDamage() - damage.intValue());
				break;
				
			default:
				break;
		}
	}
	
	public Class<?>[] acceptChange(Changer.ChangeMode mode) {
		
		switch(mode) {
			case SET:
				return (Class[]) CollectionUtils.array((Object[]) new Class[] {Number.class});
				
			case ADD:
				return (Class[]) CollectionUtils.array((Object[]) new Class[] {Number.class});
				
			case REMOVE:
				return (Class[]) CollectionUtils.array((Object[]) new Class[] {Number.class});
				
			default:
				return null;
		}
	}

}
