package cc.chocomint.nAddon.elements.effects.json;

import javax.annotation.Nullable;

import org.bukkit.event.Event;
import org.json.simple.JSONObject;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class RemoveValue extends Effect {
	
	private Expression<String> ex_key;
	private Expression<JSONObject> ex_json;
	
	static {
		Skript.registerEffect(RemoveValue.class, "remove json value at %string% in %json%");
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
		return "PutValue";
	}

	@Override
	protected void execute(Event e) {
		
		String key = (String) this.ex_key.getSingle(e);
		JSONObject json = (JSONObject) this.ex_json.getSingle(e);
		
		json.remove(key);
	}

}
