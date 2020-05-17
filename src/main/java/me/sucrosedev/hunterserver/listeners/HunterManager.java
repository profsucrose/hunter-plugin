package me.sucrosedev.hunterserver.listeners;

import me.sucrosedev.hunterserver.commands.MakeTarget;
import me.sucrosedev.hunterserver.utils.Numeric;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;

public class HunterManager implements Listener {
    public static ArrayList<String> hunterArray = new ArrayList<>();
    private static HashMap<String, String> hunterToCurrentTargetName = new HashMap<>();

    @EventHandler
    public static void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        hunterArray.add(player.getDisplayName());
        player.getInventory().addItem( new ItemStack(Material.COMPASS) );
    }

    @EventHandler
    public static void onPlayerMove(PlayerMoveEvent e) {

        for (int i = 0; i < hunterArray.size(); i++) {

            String hunterName = hunterArray.get(i);

            String closestTargetName = "";
            Location closestTargetLoc = Bukkit.getWorld("world").getSpawnLocation(); // Default location so JVM doesn't get mad
            double closestHunterTargetDistance = Double.POSITIVE_INFINITY;
            for (int j = 0; j < MakeTarget.targets.size(); j++) {
                Location hunterLoc = Bukkit.getPlayer(hunterName).getLocation();
                Location targetLoc = Bukkit.getPlayer(MakeTarget.targets.get(j)).getLocation();

                double hunterTargetDistance = Numeric.getLocationDistance(hunterLoc, targetLoc);
                if (hunterTargetDistance < closestHunterTargetDistance) {
                    closestHunterTargetDistance = hunterTargetDistance;
                    closestTargetName = MakeTarget.targets.get(j);
                    closestTargetLoc = targetLoc;
                }
            }

            Bukkit.getPlayer(hunterName).setCompassTarget(closestTargetLoc);
            if (!hunterToCurrentTargetName.get(hunterName).equals(closestTargetName)) {
                hunterToCurrentTargetName.put(hunterName, closestTargetName);
                Bukkit.getPlayer(hunterName).sendMessage(ChatColor.GREEN + "Compass is now tracking " + closestTargetName);
            }
        }

    }
}
