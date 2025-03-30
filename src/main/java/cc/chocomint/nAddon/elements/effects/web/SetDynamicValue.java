package cc.chocomint.nAddon.elements.effects.web;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

import cc.chocomint.nAddon.customevents.CreatedDynamicValueEvent;
import cc.chocomint.nAddon.elements.expressions.web.RegisterWebApp;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;

import cc.chocomint.nAddon.Main;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.json.simple.JSONObject;

public class SetDynamicValue extends Effect {
	
	public static JSONObject dynamicjson;
	
	private Expression<Object> ex_value;
	private Expression<String> ex_key;
	
	static {
		if(Main.getPlugin().getConfig().getBoolean("webdynamicvalue")) {
			Skript.registerEffect(SetDynamicValue.class, "set dynamic value %object% at %string%");
			dynamicjson = new JSONObject();
			Main.Effects ++;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {

		this.ex_value = (Expression<Object>) exprs[0];
		this.ex_key = (Expression<String>) exprs[1];

		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "CreateDynamicValue";
	}

	@Override
	protected void execute(Event e) {
		Object value = (Object) ex_value.getSingle(e);
		String key = (String) ex_key.getSingle(e);

		dynamicjson.put(key, value);
		CreatedDynamicValueEvent event = new CreatedDynamicValueEvent(key, value);
		Bukkit.getPluginManager().callEvent(event);
	}

}