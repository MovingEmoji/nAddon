package cc.chocomint.nAddon.elements.effects.web;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import cc.chocomint.nAddon.Main;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import io.javalin.Javalin;

public class CloseWebApp extends Effect {
	
	private Expression<Javalin> ex_app;
	
	static {
		Skript.registerEffect(CloseWebApp.class, "close web app %javalin%");
		Main.Effects ++;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		
		this.ex_app = (Expression<Javalin>) exprs[0];
		
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "CloseWebApp";
	}

	@Override
	protected void execute(Event e) {
		
		Javalin app = (Javalin) this.ex_app.getSingle(e);
		
		app.close();
		
	}

}
