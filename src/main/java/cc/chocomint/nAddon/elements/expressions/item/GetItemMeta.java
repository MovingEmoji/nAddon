package cc.chocomint.nAddon.elements.expressions.item;

import javax.annotation.Nullable;

import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import cc.chocomint.nAddon.Main;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class GetItemMeta extends SimpleExpression<ItemMeta> {
	
	private Expression<ItemStack> ex_item;
	
	static {
		Skript.registerExpression(GetItemMeta.class, ItemMeta.class, ExpressionType.SIMPLE, "%itemstack%'s meta");
		Skript.registerExpression(GetItemMeta.class, ItemMeta.class, ExpressionType.SIMPLE, "meta of %itemstack%");
		Main.Expressions ++;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends ItemMeta> getReturnType() {
		return ItemMeta.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		this.ex_item = (Expression<ItemStack>) exprs[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "GetItemMeta";
	}

	@Override
	protected @Nullable ItemMeta[] get(Event e) {
		
		ItemStack item = (ItemStack) this.ex_item.getSingle(e);
		ItemMeta meta = item.getItemMeta();
		
		return new ItemMeta[] {meta};
	}

}
