package cc.chocomint.nAddon.listener;

import cc.chocomint.nAddon.Main;
import cc.chocomint.nAddon.elements.effects.packet.TagAboveHead;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.npc.CitizensNPC;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class PacketListener {

    public static boolean pass = false;

    public static void registerPacketListener() {
        ProtocolManager manager = ProtocolLibrary.getProtocolManager();

        PacketType[] packets = {
                PacketType.Play.Server.REL_ENTITY_MOVE,
                PacketType.Play.Server.REL_ENTITY_MOVE_LOOK,
        };

        manager.addPacketListener(new PacketAdapter(
                Main.getPlugin(),
                ListenerPriority.HIGHEST,
                packets
        ) {
            @Override
            public void onPacketSending(PacketEvent event) {
                Entity entity = event.getPacket().getEntityModifier(event).read(0);
                if(entity instanceof Player && !CitizensAPI.getNPCRegistry().isNPC(entity)) {
                    ArmorStand stand = TagAboveHead.tags.get(entity.getName());
                    if(stand != null) {
                        //PacketPlayOutEntity.PacketPlayOutRelEntityMove packet = new PacketPlayOutEntity.PacketPlayOutRelEntityMove(stand.getEntityId(), event.getPacket().getBytes().read(0), event.getPacket().getBytes().read(1), event.getPacket().getBytes().read(2), true);
                        Location location = entity.getLocation();
                        PacketPlayOutEntityTeleport packet = new PacketPlayOutEntityTeleport(
                                stand.getEntityId(),
                                MathHelper.floor(location.getX() * 32.0),
                                MathHelper.floor((location.getY() + 2.1) * 32.0),
                                MathHelper.floor(location.getZ() * 32.0),
                                (byte) 0, (byte) 0, true);
                        pass = true;
                        ((CraftPlayer) event.getPlayer()).getHandle().playerConnection.sendPacket(packet);
                    }
                }
            }
        });

        manager.addPacketListener(new PacketAdapter(
                Main.getPlugin(),
                ListenerPriority.HIGHEST,
                PacketType.Play.Server.ENTITY_TELEPORT
        ) {
            @Override
            public void onPacketSending(PacketEvent event) {
                Entity entity = event.getPacket().getEntityModifier(event).read(0);
                if(entity instanceof Player && !CitizensAPI.getNPCRegistry().isNPC(entity)) {
                    ArmorStand stand = TagAboveHead.tags.get(entity.getName());
                    if(stand != null) {
                        PacketPlayOutEntityTeleport packet = new PacketPlayOutEntityTeleport(
                                stand.getEntityId(),
                                event.getPacket().getIntegers().read(1),
                                MathHelper.f((event.getPacket().getIntegers().read(2) / 32.0 + 2.1) * 32.0),
                                event.getPacket().getIntegers().read(3),
                                (byte) 0, (byte) 0, true);
                        pass = true;
                        ((CraftPlayer) event.getPlayer()).getHandle().playerConnection.sendPacket(packet);
                    }
                } else if(entity instanceof ArmorStand) {
                    if(pass != true) {
                        event.setCancelled(true);
                    } else {
                        pass = false;
                    }
                }
            }
        });
    }
}
