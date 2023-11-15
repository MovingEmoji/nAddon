package cc.chocomint.nAddon.elements.expressions;

import javax.annotation.Nullable;

import org.bukkit.event.Event;
import org.bukkit.util.Vector;

import cc.chocomint.nAddon.Main;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class NormalizedVelocity extends SimpleExpression<Vector> {
	
	private Expression<Vector> ex_velocity;
	
	static {
		Skript.registerExpression(NormalizedVelocity.class, Vector.class, ExpressionType.COMBINED, "normalized value of %vector%");
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

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		
		this.ex_velocity = (Expression<Vector>) exprs[0];
		
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "NormalizedVelocity";
	}

	@Override
	protected @Nullable Vector[] get(Event e) {
		
		Vector vector = (Vector) this.ex_velocity.getSingle(e);
		
		return new Vector[] {vector.normalize()};
	}

}
