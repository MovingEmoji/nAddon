package cc.chocomint.nAddon.register;

import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.json.simple.JSONObject;

import cc.chocomint.nAddon.Main;
import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.registrations.Classes;
import io.javalin.Javalin;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.util.PlayerAnimation;

public class ClassRegister {
	
	public static void registerAllSkriptClasses() {
		
		Classes.registerClass(new ClassInfo<Scoreboard>(Scoreboard.class, "scoreboard"));
		Classes.registerClass(new ClassInfo<Team>(Team.class, "team"));
		if(Main.getPlugin().getConfig().getBoolean("citizen") == true) {
			Classes.registerClass(new ClassInfo<NPC>(NPC.class, "npc"));
			Classes.registerClass(new ClassInfo<PlayerAnimation>(PlayerAnimation.class, "playeranimation"));
		}
		Classes.registerClass(new ClassInfo<Javalin>(Javalin.class, "javalin"));
		Classes.registerClass(new ClassInfo<JSONObject>(JSONObject.class, "json"));
	}
}
