package cc.chocomint.nAddon.elements.expressions;

import javax.annotation.Nullable;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;

import cc.chocomint.nAddon.Main;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class NoDamageTick extends SimpleExpression<Number> {
	
	private Expression<LivingEntity> ex_entity;
	
	static {
		Skript.registerExpression(NoDamageTick.class, Number.class, ExpressionType.COMBINED, "%livingentity%'s damage delay");
		Skript.registerExpression(NoDamageTick.class, Number.class, ExpressionType.COMBINED, "damage delay of %livingentity%");
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

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		
		this.ex_entity = (Expression<LivingEntity>) exprs[0];
		
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "NoDamageTick";
	}

	@Override
	protected @Nullable Number[] get(Event e) {
		
		LivingEntity entity = (LivingEntity) this.ex_entity.getSingle(e);
		
		int tick = entity.getNoDamageTicks();
		
		return new Number[] {(Number) tick};
	}

}
