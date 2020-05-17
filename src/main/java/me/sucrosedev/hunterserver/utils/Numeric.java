package me.sucrosedev.hunterserver.utils;

import org.bukkit.Location;

public class Numeric {

    public static double getLocationDistance(Location loc1, Location loc2) {
        return Math.sqrt(
                Math.pow(loc1.getX() - loc2.getX(), 2)
                + Math.pow(loc1.getY() - loc2.getY(), 2)
                + Math.pow(loc1.getZ() - loc2.getZ(), 2)
        );
    }

}
