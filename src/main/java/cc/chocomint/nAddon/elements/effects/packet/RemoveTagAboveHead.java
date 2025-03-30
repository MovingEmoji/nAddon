package cc.chocomint.nAddon.elements.effects.packet;

import cc.chocomint.nAddon.Main;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RemoveTagAboveHead extends Effect {

    private Expression<Player> ex_player;

    static {
        Skript.registerEffect(RemoveTagAboveHead.class, "remove %player%'s tag above head");
        Main.Effects ++;
    }

    @Override
    protected void execute(Event e) {

        Player player = (Player) ex_player.getSingle(e);

        ArmorStand paststand = TagAboveHead.tags.get(player.getName());
        if(paststand != null) {
            TagAboveHead.tags.remove(player.getName());
            paststand.remove();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {

        this.ex_player = (Expression<Player>) exprs[0];

        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "TagAboveHead";
    }
}
