package cc.chocomint.nAddon.elements.effects;

import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import cc.chocomint.nAddon.Main;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class AttackEntity extends Effect {
	
	private Expression<LivingEntity> ex_attacker;
	private Expression<LivingEntity> ex_victim;
	private Expression<Number> ex_damage;
	
	static {
		Skript.registerEffect(AttackEntity.class, "make %livingentity% attack %livingentity% by %number%");
		Main.Effects ++;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		
		this.ex_attacker = (Expression<LivingEntity>) exprs[0];
		this.ex_victim = (Expression<LivingEntity>) exprs[1];
		this.ex_damage = (Expression<Number>) exprs[2];
		
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "AttackEntity";
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void execute(Event e) {
		
		LivingEntity attacker = (LivingEntity) this.ex_attacker.getSingle(e);
		LivingEntity victim = (LivingEntity) this.ex_victim.getSingle(e);
		Number damage = (Number) this.ex_damage.getSingle(e);
		EntityDamageByEntityEvent event = new EntityDamageByEntityEvent(attacker, victim, DamageCause.ENTITY_ATTACK, damage.doubleValue());
		victim.damage(damage.doubleValue(), attacker);
		Bukkit.getPluginManager().callEvent(event);
		
		
	}

}
