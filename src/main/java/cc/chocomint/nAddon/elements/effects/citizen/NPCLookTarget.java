package cc.chocomint.nAddon.elements.effects.citizen;

import javax.annotation.Nullable;

import org.bukkit.Location;
import org.bukkit.event.Event;

import cc.chocomint.nAddon.Main;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import net.citizensnpcs.api.npc.NPC;

public class NPCLookTarget extends Effect {
	
	private Expression<NPC> ex_npc;
	private Expression<Location> ex_location;
	
	static {
		if(Main.getPlugin().getConfig().getBoolean("citizen") == true) {
			Skript.registerEffect(NPCLookTarget.class, "make %npc% look at %location%");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		
		this.ex_npc = (Expression<NPC>) exprs[0];
		this.ex_location = (Expression<Location>) exprs[1];
		
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "NPCLookTarget";
	}

	@Override
	protected void execute(Event e) {
		
		NPC npc = (NPC) this.ex_npc.getSingle(e);
		Location location = (Location) this.ex_location.getSingle(e);
		
		npc.faceLocation(location);
		
	}

}
