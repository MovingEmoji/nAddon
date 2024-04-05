package cc.chocomint.nAddon.elements.effects.citizen;

import javax.annotation.Nullable;

import org.bukkit.Location;
import org.bukkit.event.Event;


import cc.chocomint.nAddon.Main;
import cc.chocomint.nAddon.elements.expressions.citizen.LastCreatedNPC;
import ch.njol.skript.Skript;
import ch.njol.skript.entity.EntityType;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;

public class CreateNPC extends Effect {
	
	private Expression<String> ex_name;
	private Expression<EntityType> ex_type;
	private Expression<Location> ex_location;
	
	static {
		if(Main.getPlugin().getConfig().getBoolean("citizen") == true) {
			Skript.registerEffect(CreateNPC.class, "create npc named %string% [with type %entitytype%] at %location%");
			Main.Effects ++;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		
		this.ex_name = (Expression<String>) exprs[0];
		this.ex_type = (Expression<EntityType>) exprs[1];
		this.ex_location = (Expression<Location>) exprs[2];
		
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "CreateNPC";
	}

	@SuppressWarnings("unused")
	@Override
	protected void execute(Event e) {
		
		String name = (String) this.ex_name.getSingle(e);
		EntityType type = (EntityType) this.ex_type.getSingle(e);
		Location location = (Location) this.ex_location.getSingle(e);
		org.bukkit.entity.EntityType bukkittype = convert(type.toString());
		NPC npc = null;
		
		if(type != null) {
			
			npc = CitizensAPI.getNPCRegistry().createNPC(bukkittype, name);
			
		} else {
			npc = CitizensAPI.getNPCRegistry().createNPC(org.bukkit.entity.EntityType.PLAYER, name);
		}
		
		if(npc != null) {
			npc.spawn(location);
			LastCreatedNPC.lastnpc = npc;
		}
		
	}
	@SuppressWarnings("unused")
	private org.bukkit.entity.EntityType convert(String type) {
	    return org.bukkit.entity.EntityType.valueOf(type.toUpperCase().replace(" ", "_").trim());
	  }

}
