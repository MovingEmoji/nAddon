package cc.chocomint.nAddon.elements.effects.json;

import javax.annotation.Nullable;

import org.bukkit.event.Event;
import org.json.simple.JSONObject;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class PutValue extends Effect {
	
	private Expression<Object> ex_value;
	private Expression<String> ex_key;
	private Expression<JSONObject> ex_json;
	
	static {
		Skript.registerEffect(PutValue.class, "put json value %object% at %string% in %json%");
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		
		this.ex_value = (Expression<Object>) exprs[0];
		this.ex_key = (Expression<String>) exprs[1];
		this.ex_json = (Expression<JSONObject>) exprs[2];
		
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "PutValue";
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void execute(Event e) {
		
		Object value = (Object) this.ex_value.getSingle(e);
		String key = (String) this.ex_key.getSingle(e);
		JSONObject json = (JSONObject) this.ex_json.getSingle(e);
		
		json.put(key, value);
	}

}
