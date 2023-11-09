package cc.chocomint.nAddon.elements.effects.packet;

import javax.annotation.Nullable;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class DeleteStuffArrow extends Effect {
	
	private Expression<Player> ex_player;
	
	static {
		Skript.registerEffect(DeleteStuffArrow.class, "delete %player%'s stuff arrows");
		Skript.registerEffect(DeleteStuffArrow.class, "delete stuff arrows of %player%");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		
		this.ex_player = (Expression<Player>) exprs[0];
		
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "RemoveStuffArrow";
	}

	@Override
	protected void execute(Event e) {
		
		Player player = (Player) this.ex_player.getSingle(e);
		CraftPlayer cplayer = (CraftPlayer) player;
		
		if(player != null) {
			try {
				
				cplayer.getHandle().getDataWatcher().watch(9, Byte.valueOf((byte) 0));
				
			} catch(Exception exception) {
				
				exception.printStackTrace();
			}
		}
		
	}

}
