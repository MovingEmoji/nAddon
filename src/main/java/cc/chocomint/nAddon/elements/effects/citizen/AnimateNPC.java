package cc.chocomint.nAddon.elements.effects.citizen;

import javax.annotation.Nullable;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import cc.chocomint.nAddon.Main;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.util.PlayerAnimation;
import net.citizensnpcs.util.Util;

public class AnimateNPC extends Effect {
	
	private Expression<NPC> ex_npc;
	private Expression<String> ex_animation;
	
	static {
		if(Main.getPlugin().getConfig().getBoolean("citizen") == true) {
			Skript.registerEffect(AnimateNPC.class, "make %npc% animate %string%");
			Main.Effects ++;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		this.ex_npc = (Expression<NPC>) exprs[0];
		this.ex_animation = (Expression<String>) exprs[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "AnimateNPC";
	}

	@Override
	protected void execute(Event e) {
		NPC npc = (NPC) this.ex_npc.getSingle(e);
		String animation = this.ex_animation.getSingle(e);
		PlayerAnimation playeranimation = (PlayerAnimation)Util.matchEnum((Enum[])PlayerAnimation.values(), animation);
		playeranimation.play((Player) npc.getEntity());
	}

}
