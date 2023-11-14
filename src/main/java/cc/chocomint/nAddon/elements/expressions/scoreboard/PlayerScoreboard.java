package cc.chocomint.nAddon.elements.expressions.scoreboard;

import javax.annotation.Nullable;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.scoreboard.Scoreboard;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class PlayerScoreboard extends SimpleExpression<Scoreboard> {
	
	private Expression<Player> ex_player;
	
	static {
		Skript.registerExpression(PlayerScoreboard.class, Scoreboard.class, ExpressionType.COMBINED, "%player%'s scoreboard");
		Skript.registerExpression(PlayerScoreboard.class, Scoreboard.class, ExpressionType.COMBINED, "scoreboard of %player%");
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends Scoreboard> getReturnType() {
		return Scoreboard.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		
		this.ex_player = (Expression<Player>) exprs[0];
		
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "PlayerScoreboard";
	}

	@Override
	protected @Nullable Scoreboard[] get(Event e) {
		
		Player player = (Player) this.ex_player.getSingle(e);
		
		return new Scoreboard[] {player.getScoreboard()};
	}

}
