package cc.chocomint.nAddon.elements.events;

import javax.annotation.Nullable;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;

public class ItemDamageEvent extends SkriptEvent {
	
	static {
		Skript.registerEvent("ItemDamageEvent", ItemDamageEvent.class, PlayerItemDamageEvent.class, "[player] item damage");
		
		EventValues.registerEventValue(PlayerItemDamageEvent.class, ItemStack.class, new Getter<ItemStack, PlayerItemDamageEvent>() {
			@Nullable
			public ItemStack get(PlayerItemDamageEvent event) {
				return event.getItem();
			}
		}, 0);
		
		EventValues.registerEventValue(PlayerItemDamageEvent.class, Player.class, new Getter<Player, PlayerItemDamageEvent>() {
			@Nullable
			public Player get(PlayerItemDamageEvent event) {
				return event.getPlayer();
			}
		}, 0);
		
		EventValues.registerEventValue(PlayerItemDamageEvent.class, Number.class, new Getter<Number, PlayerItemDamageEvent>() {
			@Nullable
			public Number get(PlayerItemDamageEvent event) {
				return new Integer(event.getDamage());
			}
		}, 0);
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "ItemDamageEvent";
	}

	@Override
	public boolean init(Literal<?>[] args, int matchedPattern, ParseResult parseResult) {
		return true;
	}

	@Override
	public boolean check(Event e) {
		return e instanceof PlayerItemDamageEvent;
	}

}
