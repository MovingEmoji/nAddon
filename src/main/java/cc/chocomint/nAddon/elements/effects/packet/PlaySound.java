package cc.chocomint.nAddon.elements.effects.packet;

import javax.annotation.Nullable;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import cc.chocomint.nAddon.Main;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class PlaySound extends Effect{
	
	private Expression<String> ex_sound;
	private Expression<Player> ex_player;
	private Expression<Number> ex_volume;
	private Expression<Number> ex_pitch;
	
	static {
		Skript.registerEffect(PlaySound.class, "play %string% to %players% at volume %number% [with pitch %number%]");
		Main.Effects ++;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {

		try {
			
			Sound.valueOf(exprs[0].toString().replace("\"", "").trim().replace(" ", "_").toUpperCase());
			this.ex_sound = (Expression<String>) exprs[0];
			
		} catch (Exception exception) {
			
			Skript.error(exprs[0].toString().replace("\"", "") + " is not a valid sound type");
			return false;
		}
		
		this.ex_player = (Expression<Player>) exprs[1];
		this.ex_volume = (Expression<Number>) exprs[2];
		this.ex_pitch = (Expression<Number>) exprs[3];
		
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "PlaySound";
	}

	@Override
	protected void execute(Event e) {
		
		Sound sound = Sound.valueOf(((String)this.ex_sound.getSingle(e)).replace("\"", "").trim().replace(" ", "_").toUpperCase());
		Player[] players = (Player[]) this.ex_player.getAll(e);
		Number volume = (Number) this.ex_volume.getSingle(e);
		Number pitch = (Number) this.ex_pitch.getSingle(e);
		
		if(pitch != null) {
			
			for(Player player : players) {
				player.playSound(player.getLocation(), sound, volume.floatValue(), pitch.floatValue());
			}
			
		} else {
			
			for(Player player : players) {
				player.playSound(player.getLocation(), sound, volume.floatValue(), 1.0F);
			}
			
		}
	}

}
