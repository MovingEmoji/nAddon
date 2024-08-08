package cc.chocomint.nAddon.elements.expressions.citizen;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import cc.chocomint.nAddon.Main;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import net.citizensnpcs.api.npc.NPC;

public class NPCId extends SimpleExpression<Number> {
	
	private Expression<NPC> ex_npc;
	
	static {
		if(Main.getPlugin().getConfig().getBoolean("citizen") == true) {
			Skript.registerExpression(NPCId.class, Number.class, ExpressionType.COMBINED, "%npc%'s id");
			Skript.registerExpression(NPCId.class, Number.class, ExpressionType.COMBINED, "id of %npc%");
			Main.Expressions ++;
		}
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends Number> getReturnType() {
		return Number.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		ex_npc = (Expression<NPC>) exprs[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "NPCId";
	}

	@Override
	protected @Nullable Number[] get(Event e) {
		NPC npc = (NPC) ex_npc.getSingle(e);
		Number id = npc.getId();
		return new Number[] {id};
	}

}
