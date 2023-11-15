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

public class JsonValue extends SimpleExpression<Object> {
	
	private Expression<String> ex_key;
	private Expression<JSONObject> ex_json;
	
	static {
		Skript.registerExpression(JsonValue.class, Object.class, ExpressionType.COMBINED, "json value of %string% in %json%");
		Main.Expressions ++;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends Object> getReturnType() {
		return Object.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		
		this.ex_key = (Expression<String>) exprs[0];
		this.ex_json = (Expression<JSONObject>) exprs[1];
		
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "JsonValue";
	}

	@Override
	protected @Nullable Object[] get(Event e) {

		String key = (String) ex_key.getSingle(e);
		JSONObject json = (JSONObject) ex_json.getSingle(e);
		
		return new Object[] {json.get(key)};
	}

}
