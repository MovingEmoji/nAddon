package cc.chocomint.nAddon.elements.expressions.event;

import javax.annotation.Nullable;

import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerVelocityEvent;
import org.bukkit.util.Vector;

import cc.chocomint.nAddon.Main;
import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;

public class EventVelocity extends SimpleExpression<Vector> {
	
	static {
		Skript.registerExpression(EventVelocity.class, Vector.class, ExpressionType.COMBINED, "event-velocity");
		Main.Expressions ++;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends Vector> getReturnType() {
		return Vector.class;
	}

	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "EventVelocity";
	}

	@Override
	protected @Nullable Vector[] get(Event e) {
		return new Vector[] {((PlayerVelocityEvent) e).getVelocity()};
	}
	
	@Override
	public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
		
		switch(mode) {
			case SET:
				Vector vector = (Vector) delta[0];
				((PlayerVelocityEvent) e).setVelocity(vector);
				break;
				
			default:
				break;
		}
		
	}
	
	@Override
	public Class<?>[] acceptChange(Changer.ChangeMode mode) {
		
		switch(mode) {
			case SET:
				return (Class[]) CollectionUtils.array((Object[]) new Class[] {Vector.class});
				
			default:
				return null;
		}
	}

}
