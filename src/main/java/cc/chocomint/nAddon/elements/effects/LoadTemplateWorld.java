package cc.chocomint.nAddon.elements.effects;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import com.grinderwolf.swm.api.loaders.SlimeLoader;
import com.grinderwolf.swm.api.world.SlimeWorld;
import com.grinderwolf.swm.plugin.SWMPlugin;
import com.grinderwolf.swm.plugin.config.ConfigManager;
import com.grinderwolf.swm.plugin.config.WorldData;
import com.grinderwolf.swm.plugin.config.WorldsConfig;

import cc.chocomint.nAddon.Main;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class LoadTemplateWorld extends Effect {
	
	private Expression<String> ex_future_world;
	private Expression<String> ex_from_world;
	
	static {
		if(Main.getPlugin().getConfig().getBoolean("slimeworld") == true) {
			Skript.registerEffect(LoadTemplateWorld.class, "load template world %string% from %string%");
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		
		this.ex_future_world = (Expression<String>) exprs[0];
		this.ex_from_world = (Expression<String>) exprs[1];
		
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "LoadTemplateWorld";
	}

	@Override
	protected void execute(Event e) {
		
		String future_world = (String) this.ex_future_world.getSingle(e);
		String from_world = (String) this.ex_from_world.getSingle(e);
		
		WorldsConfig config = ConfigManager.getWorldConfig();
		WorldData data = (WorldData) config.getWorlds().get(from_world);
		SlimeLoader loader = SWMPlugin.getInstance().getLoader(data.getDataSource());
		
		try {
			
			SlimeWorld s_world = SWMPlugin.getInstance().loadWorld(loader, from_world, true, data.toPropertyMap()).clone(future_world);
			SWMPlugin.getInstance().generateWorld(s_world);
			
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

}
