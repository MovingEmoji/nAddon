package cc.chocomint.nAddon.elements.effects.citizen;

import javax.annotation.Nullable;

import org.bukkit.Location;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import cc.chocomint.nAddon.Main;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import net.citizensnpcs.api.npc.NPC;

public class TeleportNPC extends Effect {
	
	private Expression<NPC> ex_npc;
	private Expression<Location> ex_locaiton;
	
	static {
		if(Main.getPlugin().getConfig().getBoolean("citizen") == true) {
			Skript.registerEffect(TeleportNPC.class, "teleport npc %npc% to %location%");
			Main.Effects ++;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		this.ex_npc = (Expression<NPC>) exprs[0];
		this.ex_locaiton = (Expression<Location>) exprs[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "TeleportNPC";
	}

	@Override
	protected void execute(Event e) {
		NPC npc = (NPC) this.ex_npc.getSingle(e);
		Location location = (Location) this.ex_locaiton.getSingle(e);
		npc.teleport(location, TeleportCause.PLUGIN);
		
	}
	
}
