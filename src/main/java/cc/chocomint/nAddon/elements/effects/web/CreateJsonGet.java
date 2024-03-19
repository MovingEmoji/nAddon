package cc.chocomint.nAddon.elements.effects.web;


import javax.annotation.Nullable;

import org.bukkit.event.Event;
import org.json.simple.JSONObject;

import cc.chocomint.nAddon.Main;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import io.javalin.Javalin;

public class CreateJsonGet extends Effect {
	
	private Expression<Javalin> ex_app;
	private Expression<String> ex_path;
	private Expression<JSONObject> ex_json;
	
	static {
		Skript.registerEffect(CreateJsonGet.class, "create new get in %javalin% at %string% with json[object] %json%");
		Main.Effects ++;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		
		this.ex_app = (Expression<Javalin>) exprs[0];
		this.ex_path = (Expression<String>) exprs[1];
		this.ex_json = (Expression<JSONObject>) exprs[2];
		
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "CreateGet";
	}

	@Override
	protected void execute(Event e) {
		
		Javalin app = (Javalin) this.ex_app.getSingle(e);
		String path = (String) this.ex_path.getSingle(e);
		JSONObject json = (JSONObject) this.ex_json.getSingle(e);
		app.get(path, ctx -> ctx.json(json));
	}
}
