package cc.chocomint.nAddon.elements.effects.packet;

import javax.annotation.Nullable;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import ch.njol.skript.Skript;
import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class ClientBlock extends Effect {
	
	private Expression<Player> ex_player;
	private Expression<Block> ex_block;
	private Expression<ItemType> ex_itemtype;
	
	static {
		Skript.registerEffect(ClientBlock.class, "make %players% see %blocks% as %itemtype%");
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {

		this.ex_player = (Expression<Player>) exprs[0];
		this.ex_block = (Expression<Block>) exprs[1];
		this.ex_itemtype = (Expression<ItemType>) exprs[2];
		
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "ClientBlock";
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void execute(Event e) {
		
		Player[] players = (Player[]) this.ex_player.getAll(e);
		Block[] blocks = (Block[]) this.ex_block.getAll(e);
		ItemType itemtype = (ItemType) this.ex_itemtype.getSingle(e);
		
		if(itemtype == null) {
			return;
		}
		Material material = itemtype.getRandom().getType();
		
		if(material.isBlock() != true) {
			return;
		}
		
		for(Player player : players) {
			for(Block block : blocks) {
				player.sendBlockChange(block.getLocation(), material, (byte)itemtype.getRandom().getDurability());
			}
		}
		
	}

}
