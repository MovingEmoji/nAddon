package cc.chocomint.nAddon.elements.effects;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.json.simple.JSONObject;

import cc.chocomint.nAddon.Main;
import cc.chocomint.nAddon.customevents.CreatedDynamicValueEvent;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class SetDynamicValue extends Effect {
	
	public static Map<String, JSONObject> DynamicValue = new HashMap<String, JSONObject>();
	
	private Expression<String> ex_key;
	private Expression<JSONObject> ex_json;
	
	static {
		Skript.registerEffect(SetDynamicValue.class, "set dynamic value named %string% to %json%");
		Main.Effects ++;
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
		return "CreateDynamicValue";
	}

	@Override
	protected void execute(Event e) {
		String key = (String) ex_key.getSingle(e);
		JSONObject json = (JSONObject) ex_json.getSingle(e);
		boolean firstcreate = false;
		if(DynamicValue.get(key) == null) {
			firstcreate = true;
		}
		DynamicValue.put(key, json);
		if(firstcreate == true) {
			CreatedDynamicValueEvent event = new CreatedDynamicValueEvent(key);
			Bukkit.getPluginManager().callEvent(event);
		}
	}

}
