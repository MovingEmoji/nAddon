package cc.chocomint.nAddon.elements.expressions;

import javax.annotation.Nullable;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class EnchantLevel extends SimpleExpression<Number> {
	
	private Expression<Enchantment> ex_enchant;
	private Expression<ItemStack> ex_item;
	
	static {
		Skript.registerExpression(EnchantLevel.class, Number.class, ExpressionType.COMBINED, "enchant[ment] level (from|of) %enchantment% (of|in|on) %itemstack%");
	}
 
	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends Number> getReturnType() {
		return Number.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		
		this.ex_enchant = (Expression<Enchantment>) exprs[0];
		this.ex_item = (Expression<ItemStack>) exprs[1];
		
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "EnchantLevel";
	}

	@SuppressWarnings("deprecation")
	@Override
	protected @Nullable Number[] get(Event e) {
		
		Enchantment enchant = (Enchantment) this.ex_enchant.getSingle(e);
		ItemStack item = (ItemStack) this.ex_item.getSingle(e);
		
		return new Number[] {new Integer(item.getEnchantmentLevel(enchant))};
		
	}

}
