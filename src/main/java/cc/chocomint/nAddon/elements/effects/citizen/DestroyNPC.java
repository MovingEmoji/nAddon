package cc.chocomint.nAddon.elements.effects.citizen;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import cc.chocomint.nAddon.Main;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import net.citizensnpcs.api.npc.NPC;

public class DestroyNPC extends Effect {
	
	private Expression<NPC> ex_npc;
	
	static {
		if(Main.getPlugin().getConfig().getBoolean("citizen") == true) {
			Skript.registerEffect(DestroyNPC.class, "destroy %npc%");
			Main.Effects ++;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		this.ex_npc = (Expression<NPC>) exprs[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "DestoryNPC";
	}

	@Override
	protected void execute(Event e) {
		NPC npc = (NPC) this.ex_npc.getSingle(e);
		npc.destroy();
	}

}
