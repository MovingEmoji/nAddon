package cc.chocomint.nAddon.register;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import cc.chocomint.nAddon.Main;
import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.registrations.Classes;
import io.javalin.Javalin;
import net.citizensnpcs.api.npc.NPC;

public class ClassRegister {
	
	public static void registerAllSkriptClasses() {
		
		Classes.registerClass(new ClassInfo<Scoreboard>(Scoreboard.class, "scoreboard"));
		Classes.registerClass(new ClassInfo<Team>(Team.class, "team"));
		Classes.registerClass(new ClassInfo<NPC>(NPC.class, "npc"));
		
	}
	
	public static Javalin registerJavalin() {
		
		ClassLoader mainloader = Thread.currentThread().getContextClassLoader();
		int port = Main.getPlugin().getConfig().getInt("webport");
		
		Thread.currentThread().setContextClassLoader(Main.class.getClassLoader());
		Javalin app = Javalin.create().start(port);
		Thread.currentThread().setContextClassLoader(mainloader);
		
		Bukkit.getLogger().info("Starting WebApp...");
		
		return app;
		
	}
}
