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

public class LastCreatedNPC extends SimpleExpression<NPC> {
	
	public static NPC lastnpc;
	
	static {
		if(Main.getPlugin().getConfig().getBoolean("citizen") == true) {
			Skript.registerExpression(LastCreatedNPC.class, NPC.class, ExpressionType.COMBINED, "last [created] npc");
			Main.Expressions ++;
		}
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends NPC> getReturnType() {
		return NPC.class;
	}

	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "LastCreatedNPC";
	}

	@Override
	protected @Nullable NPC[] get(Event e) {
		if(lastnpc != null) {
			return new NPC[] {lastnpc};
		}
		return null;
	}

}
