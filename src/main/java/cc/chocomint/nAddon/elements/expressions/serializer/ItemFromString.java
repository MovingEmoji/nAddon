package cc.chocomint.nAddon.elements.expressions.serializer;

import java.io.ByteArrayInputStream;

import javax.annotation.Nullable;

import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ItemFromString extends SimpleExpression<ItemStack> {
	
	private Expression<String> ex_item_str;
	
	static {
		Skript.registerExpression(ItemFromString.class, ItemStack.class, ExpressionType.COMBINED, "item from string %string%");
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends ItemStack> getReturnType() {
		return ItemStack.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		
		this.ex_item_str = (Expression<String>) exprs[0];
		
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "ItemFromString";
	}

	@Override
	protected @Nullable ItemStack[] get(Event e) {

		String item_str = (String) this.ex_item_str.getSingle(e);
		
		ItemStack item = null;
		ByteArrayInputStream inputstream = new ByteArrayInputStream(Base64Coder.decodeLines(item_str));
		
		try {
			
			BukkitObjectInputStream bukkitstream = new BukkitObjectInputStream(inputstream);
			
			item = (ItemStack) bukkitstream.readObject();
			
			bukkitstream.close();
			inputstream.close();
			
		} catch(Exception exception) {
			exception.printStackTrace();
		}
		
		return new ItemStack[] {item};
	}

}
