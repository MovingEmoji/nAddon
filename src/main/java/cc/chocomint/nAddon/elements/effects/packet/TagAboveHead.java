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

public class TagAboveHead extends Effect {

    public static ConcurrentHashMap<String, ArmorStand> tags = new ConcurrentHashMap<>();

    private Expression<Player> ex_player;
    private Expression<String> ex_string;

    static {
        Skript.registerEffect(TagAboveHead.class, "set %player%'s tag above head to %string%");
        Main.Effects ++;
    }

    @Override
    protected void execute(Event e) {

        Player player = (Player) ex_player.getSingle(e);
        String string = ex_string.getSingle(e);

        ArmorStand paststand = tags.get(player.getName());
        if(paststand != null) {
            tags.remove(player.getName());
            paststand.remove();
        }

        Location location = player.getLocation();
        location.setY(location.getY() + 2.1);
        ArmorStand stand = (ArmorStand) player.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        stand.setGravity(false);
        stand.setMarker(true);
        stand.setBasePlate(false);
        stand.setSmall(true);
        stand.setVisible(false);
        stand.setCustomName(string);
        stand.setCustomNameVisible(true);

        tags.put(player.getName(),stand);
        PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy(stand.getEntityId());
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {

        this.ex_player = (Expression<Player>) exprs[0];
        this.ex_string = (Expression<String>) exprs[1];

        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "TagAboveHead";
    }
}
