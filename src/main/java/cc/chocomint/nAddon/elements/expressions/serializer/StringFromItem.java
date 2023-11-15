package cc.chocomint.nAddon.elements.expressions.serializer;

import java.io.ByteArrayOutputStream;

import javax.annotation.Nullable;

import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import cc.chocomint.nAddon.Main;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class StringFromItem extends SimpleExpression<String> {
	
	private Expression<ItemStack> ex_item;
	
	static {
		Skript.registerExpression(StringFromItem.class, String.class, ExpressionType.COMBINED, "string from item %itemstack%");
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

		this.ex_item = (Expression<ItemStack>) exprs[0];
		
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "StringFromItem";
	}

	@Override
	protected @Nullable String[] get(Event e) {
		
		ItemStack item = (ItemStack) this.ex_item.getSingle(e);
		
		String item_str = null;
		
		ByteArrayOutputStream outputstream = new ByteArrayOutputStream();
		
		try {
			
			BukkitObjectOutputStream bukkitstream = new BukkitObjectOutputStream(outputstream);
			
			bukkitstream.writeObject(item);
			item_str = Base64Coder.encodeLines(outputstream.toByteArray());
			
			bukkitstream.close();
			outputstream.close();
			
		} catch(Exception exception) {
			exception.printStackTrace();
		}
 		
		return new String[] {item_str};
	}

}
