package cc.chocomint.nAddon.elements.effects;

import java.util.Collection;

import javax.annotation.Nullable;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.potion.PotionEffect;

import cc.chocomint.nAddon.Main;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class ClearAllPotionEffects extends Effect {
	
	private Expression<Player> ex_player;
	
	static {
		Skript.registerEffect(ClearAllPotionEffects.class, "clear %player%'s potion effects");
		Skript.registerEffect(ClearAllPotionEffects.class, "clear potion effects of %player%");
		Main.Effects ++;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		
		this.ex_player = (Expression<Player>) exprs[0];
		
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "ClearAllPotionEffects";
	}

	@Override
	protected void execute(Event e) {
		
		Player player = (Player) this.ex_player.getSingle(e);
		Collection<PotionEffect> effects = player.getActivePotionEffects();
		
		for(PotionEffect effect : effects) {
			player.removePotionEffect(effect.getType());
		}
		
	}

}
