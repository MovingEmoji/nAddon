package cc.chocomint.nAddon.elements.expressions;

import javax.annotation.Nullable;

import org.bukkit.event.Event;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import cc.chocomint.nAddon.Main;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class PlayerNameFromUUID extends SimpleExpression<String> {
	
	private Expression<String> ex_uuid;
	
	static {
		Skript.registerExpression(PlayerNameFromUUID.class, String.class, ExpressionType.COMBINED, "username from [uuid] %string%");
		Main.Expressions ++;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		this.ex_uuid = (Expression<String>) exprs[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "PlayerNameFromUUID";
	}

	@Override
	protected @Nullable String[] get(Event e) {
		String uuid = (String) ex_uuid.getSingle(e);
		String username = null;
		if (Main.userCache != null) {
			JSONArray array = (JSONArray) Main.userCache.get("");
			for (Object obj : array) {
				JSONObject profile = (JSONObject) obj;
				String profileUUID = (String) profile.get("uuid");
				if (profileUUID.equals(uuid)) {
					username = (String) profile.get("name");
					break;
				}
			}
		}
		return new String[] {username};
	}

}
