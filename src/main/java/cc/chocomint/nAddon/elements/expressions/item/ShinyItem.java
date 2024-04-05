package cc.chocomint.nAddon.elements.expressions.item;

import javax.annotation.Nullable;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import cc.chocomint.nAddon.Main;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ShinyItem extends SimpleExpression<ItemStack>{
	
	private Expression<ItemStack> ex_item;
	
	static {
		Skript.registerExpression(ShinyItem.class, ItemStack.class, ExpressionType.COMBINED, "shiny %itemstack%");
		Main.Expressions ++;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		this.ex_item = (Expression<ItemStack>) exprs[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "ShinyItem";
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends ItemStack> getReturnType() {
		return ItemStack.class;
	}

	@Override
	protected @Nullable ItemStack[] get(Event e) {
		ItemStack item = (ItemStack) this.ex_item.getSingle(e);
		if (item.getType() == Material.BOW) {
			item.addUnsafeEnchantment(Enchantment.DIG_SPEED, 1);
		} else {
			item.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 1);
		}
		ItemMeta meta = item.getItemMeta();
		meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		item.setItemMeta(meta);
		return new ItemStack[] {item};
	}

}
