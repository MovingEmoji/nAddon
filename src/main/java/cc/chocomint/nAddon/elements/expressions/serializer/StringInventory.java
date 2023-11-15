package cc.chocomint.nAddon.elements.expressions.serializer;

import java.io.ByteArrayOutputStream;

import javax.annotation.Nullable;

import org.bukkit.event.Event;
import org.bukkit.inventory.Inventory;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import cc.chocomint.nAddon.Main;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class StringInventory extends SimpleExpression<String> {
	
	private Expression<Inventory> ex_inventory;
	
	static {
		Skript.registerExpression(StringInventory.class, String.class, ExpressionType.COMBINED, "string inventory %inventory%");
		Main.Expressions ++;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		
		this.ex_inventory = (Expression<Inventory>) exprs[0];
		
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "StringInventory";
	}

	@Override
	protected @Nullable String[] get(Event e) {
		
		Inventory inventory = (Inventory) this.ex_inventory.getSingle(e);
		
		String inventory_str = null;
		
		ByteArrayOutputStream outputstream = new ByteArrayOutputStream();
		
		try {
			
			BukkitObjectOutputStream bukkitstream = new BukkitObjectOutputStream(outputstream);
			
			bukkitstream.writeObject(inventory.getContents());
			inventory_str = Base64Coder.encodeLines(outputstream.toByteArray());
			
			bukkitstream.close();
			outputstream.close();
			
		} catch(Exception exception) {
			exception.printStackTrace();
		}
		
		return new String[] {inventory_str};
	}

}
