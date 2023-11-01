package cc.chocomint.nAddon.register;

import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.registrations.Classes;
import net.citizensnpcs.Citizens;

public class ClassRegister {
	
	public static void registerAllSkriptClasses() {
		
		Classes.registerClass(new ClassInfo<Scoreboard>(Scoreboard.class, "scoreboard"));
		Classes.registerClass(new ClassInfo<Team>(Team.class, "team"));
		Classes.registerClass(new ClassInfo<Citizens>(Citizens.class, "citizen"));
		
	}
}
