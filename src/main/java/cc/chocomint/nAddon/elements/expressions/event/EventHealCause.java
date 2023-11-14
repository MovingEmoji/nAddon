package cc.chocomint.nAddon.elements.expressions.event;

import javax.annotation.Nullable;

import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent.RegainReason;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class EventHealCause extends SimpleExpression<RegainReason> {
	
	static {
		Skript.registerExpression(EventHealCause.class, RegainReason.class, ExpressionType.SIMPLE, "heal cause");
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends RegainReason> getReturnType() {
		return RegainReason.class;
	}

	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "HealCause";
	}

	@Override
	protected @Nullable RegainReason[] get(Event e) {
		return new RegainReason[] {((EntityRegainHealthEvent) e).getRegainReason()};
	}

}
