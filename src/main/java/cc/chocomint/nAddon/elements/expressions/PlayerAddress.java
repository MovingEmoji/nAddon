package cc.chocomint.nAddon.elements.expressions;

import javax.annotation.Nullable;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import cc.chocomint.nAddon.Main;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class PlayerAddress extends SimpleExpression<String> {
	
	private Expression<Player> ex_player;
	
	static {
		Skript.registerExpression(PlayerAddress.class, String.class, ExpressionType.COMBINED, "%player%'s ip");
		Skript.registerExpression(PlayerAddress.class, String.class, ExpressionType.COMBINED, "%player%'s address");
		Skript.registerExpression(PlayerAddress.class, String.class, ExpressionType.COMBINED, "ip of %player%");
		Skript.registerExpression(PlayerAddress.class, String.class, ExpressionType.COMBINED, "address of %player%");
		Main.Expressions ++;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		this.ex_player = (Expression<Player>) exprs[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "PlayerAddres";
	}

	@Override
	protected @Nullable String[] get(Event e) {
		
		Player player = (Player) this.ex_player.getSingle(e);
		String address = player.getAddress().getHostString();
		
 		return new String[] {address};
	}

}
