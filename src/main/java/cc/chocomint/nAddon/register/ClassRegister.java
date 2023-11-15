package cc.chocomint.nAddon.register;

import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.json.simple.JSONObject;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.registrations.Classes;
import io.javalin.Javalin;
import net.citizensnpcs.api.npc.NPC;

public class ClassRegister {
	
	public static void registerAllSkriptClasses() {
		
		Classes.registerClass(new ClassInfo<Scoreboard>(Scoreboard.class, "scoreboard"));
		Classes.registerClass(new ClassInfo<Team>(Team.class, "team"));
		Classes.registerClass(new ClassInfo<NPC>(NPC.class, "npc"));
		Classes.registerClass(new ClassInfo<Javalin>(Javalin.class, "javalin"));
		Classes.registerClass(new ClassInfo<JSONObject>(JSONObject.class, "json"));
		
	}
}
