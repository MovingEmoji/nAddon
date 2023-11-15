package cc.chocomint.nAddon.elements.expressions.serializer;

import java.io.ByteArrayInputStream;

import javax.annotation.Nullable;

import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import cc.chocomint.nAddon.Main;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ItemsFromString extends SimpleExpression<ItemStack> {
	
	private Expression<String> ex_inventory;
	
	static {
		Skript.registerExpression(ItemsFromString.class, ItemStack.class, ExpressionType.COMBINED, "items from string %string%");
		Main.Expressions ++;
	}

	@Override
	public boolean isSingle() {
		return false;
	}

	@Override
	public Class<? extends ItemStack> getReturnType() {
		return ItemStack.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		
		this.ex_inventory = (Expression<String>) exprs[0];
		
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "ItemsFromString";
	}

	@Override
	protected @Nullable ItemStack[] get(Event e) {
		
		String item_str = (String) this.ex_inventory.getSingle(e);
		
		ItemStack[] items = null;
		
		ByteArrayInputStream inputstream = new ByteArrayInputStream(Base64Coder.decodeLines(item_str));
		
		try {
			
			BukkitObjectInputStream bukkitstream = new BukkitObjectInputStream(inputstream);
			
			items = (ItemStack[]) bukkitstream.readObject();
			
			bukkitstream.close();
			inputstream.close();
			
		} catch(Exception exception) {
			exception.printStackTrace();
		}
		
		return items;
	}

}
