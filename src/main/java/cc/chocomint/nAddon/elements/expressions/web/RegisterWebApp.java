package cc.chocomint.nAddon.elements.expressions.web;

import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;

import cc.chocomint.nAddon.Main;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.javalin.Javalin;

public class RegisterWebApp extends SimpleExpression<Javalin> {
	
	private Expression<Number> ex_port;
	
	static {
		Skript.registerExpression(RegisterWebApp.class, Javalin.class, ExpressionType.COMBINED, "register new web app with port %number%");
		Main.Expressions ++;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends Javalin> getReturnType() {
		return Javalin.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		
		this.ex_port = (Expression<Number>) exprs[0];
		
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "RegisterWebApp";
	}

	@Override
	protected @Nullable Javalin[] get(Event e) {
		
		Number port = (Number) this.ex_port.getSingle(e);
		
		ClassLoader mainloader = Thread.currentThread().getContextClassLoader();
		
		Thread.currentThread().setContextClassLoader(Main.class.getClassLoader());
		Javalin app = Javalin.create().start(port.intValue());
		Thread.currentThread().setContextClassLoader(mainloader);
		
		Bukkit.getLogger().info("Starting WebApp...");
		
		return new Javalin[] {app};
	}

}
