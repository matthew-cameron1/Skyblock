package com.mcavenue.skyblock.island;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class IslandLocation {
    private int x;
    private int y;
    private int z;
    private String world;

    public IslandLocation(String world, int x, int y, int z) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Location bukkitLocation() {
        return new Location(Bukkit.getWorld(world), x, y, z);
    }
}
