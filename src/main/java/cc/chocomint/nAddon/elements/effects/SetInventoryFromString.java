package cc.chocomint.nAddon.elements.effects;

import java.io.ByteArrayInputStream;

import javax.annotation.Nullable;

import org.bukkit.event.Event;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import cc.chocomint.nAddon.Main;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class SetInventoryFromString extends Effect {
	
	private Expression<Inventory> ex_inventory;
	private Expression<String> ex_str_inv;
	
	static {
		Skript.registerEffect(SetInventoryFromString.class, "set %inventory% to inventory from string %string%");
		Main.Effects ++;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		
		this.ex_inventory = (Expression<Inventory>) exprs[0];
		this.ex_str_inv = (Expression<String>) exprs[1];
		
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "SetInventoryFromString";
	}

	@Override
	protected void execute(Event e) {
		
		Inventory inventory = (Inventory) this.ex_inventory.getSingle(e);
		String str_inv = (String) this.ex_str_inv.getSingle(e);
		
		ByteArrayInputStream inputstream = new ByteArrayInputStream(Base64Coder.decodeLines(str_inv));
		
		try {
			
			BukkitObjectInputStream bukkitstream = new BukkitObjectInputStream(inputstream);
			ItemStack[] items = (ItemStack[]) bukkitstream.readObject();
			
			inventory.setContents(items);
			
			bukkitstream.close();
			inputstream.close();
			
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

}
