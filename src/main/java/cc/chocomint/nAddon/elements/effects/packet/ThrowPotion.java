package cc.chocomint.nAddon.elements.effects.packet;

import javax.annotation.Nullable;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftThrownPotion;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import cc.chocomint.nAddon.Main;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class ThrowPotion extends Effect {
	
	private Expression<ItemStack> ex_item;
	private Expression<LivingEntity> ex_entity;
	
	static {
		Skript.registerEffect(ThrowPotion.class, "throw potion %itemstack% from %entity%");
		Main.Effects ++;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		
		this.ex_item = (Expression<ItemStack>) exprs[0];
		this.ex_entity = (Expression<LivingEntity>) exprs[1];
		
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "ThrowPotion";
	}

	@Override
	protected void execute(Event e) {
		
		ItemStack item = (ItemStack) ex_item.getSingle(e);
		LivingEntity entity = (LivingEntity) ex_entity.getSingle(e);
		ThrownPotion projectile = entity.launchProjectile(ThrownPotion.class);
		((CraftThrownPotion) projectile).getHandle().setPotionValue(item.getDurability());
	}

}
