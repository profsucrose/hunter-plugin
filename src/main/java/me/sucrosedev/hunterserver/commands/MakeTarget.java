package me.sucrosedev.hunterserver.commands;

import me.sucrosedev.hunterserver.listeners.HunterManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class MakeTarget implements CommandExecutor {
    public static ArrayList<String> targets = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!sender.isOp()) {
            sender.sendMessage(ChatColor.RED + "This command requires operator permissions");
            return false;
        }

        if (args.length < 1) {
            sender.sendMessage(ChatColor.RED + "Player not specified");
            return false;
        }

        String playerName = args[0];

        if (targets.contains(playerName)) {
            targets.remove(playerName);
            HunterManager.hunterArray.add(playerName);
            return true;
        }

        targets.add(playerName);
        if (HunterManager.hunterArray.contains(playerName)) {
            HunterManager.hunterArray.remove(playerName);
            Bukkit.getPlayer(playerName).getInventory().remove(Material.COMPASS);
        }
        Bukkit.broadcastMessage(ChatColor.GOLD + playerName + " is now a target!");

        return true;

    }
}
