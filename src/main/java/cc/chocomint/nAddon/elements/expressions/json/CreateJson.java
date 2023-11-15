package cc.chocomint.nAddon.elements.expressions.json;

import javax.annotation.Nullable;

import org.bukkit.event.Event;
import org.json.simple.JSONObject;

import cc.chocomint.nAddon.Main;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class CreateJson extends SimpleExpression<JSONObject> {
	
	static {
		Skript.registerExpression(CreateJson.class, JSONObject.class, ExpressionType.COMBINED, "new json");
		Main.Expressions ++;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends JSONObject> getReturnType() {
		return JSONObject.class;
	}

	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "CreateJson";
	}

	@Override
	protected @Nullable JSONObject[] get(Event e) {
		return new JSONObject[] {new JSONObject()};
	}

}
