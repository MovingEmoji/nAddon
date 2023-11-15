package cc.chocomint.nAddon.elements.effects.packet;

import javax.annotation.Nullable;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy;

public class HideEntity extends Effect {
	
	private Expression<Entity> ex_entity;
	private Expression<Player> ex_player;
	
	static {
		Skript.registerEffect(HideEntity.class, "hide entity %entity% from %player%");
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		
		this.ex_entity = (Expression<Entity>) exprs[0];
		this.ex_player = (Expression<Player>) exprs[1];
		
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "HideEntity";
	}

	@Override
	protected void execute(Event e) {
		
		Entity entity = (Entity) this.ex_entity.getSingle(e);
		Player player = (Player) this.ex_player.getSingle(e);
		CraftPlayer cplayer = (CraftPlayer) player;
		
		PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy(entity.getEntityId());
		cplayer.getHandle().playerConnection.sendPacket(packet);
	}

}
