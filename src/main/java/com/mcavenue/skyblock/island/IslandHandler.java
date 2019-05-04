package com.mcavenue.skyblock.island;

import com.mcavenue.skyblock.config.ConfigManager;
import com.mcavenue.skyblock.config.IslandConfig;
import com.mcavenue.skyblock.player.SBPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IslandHandler {

    private ConfigManager<IslandConfig> config;
    private List<Island> islands;

    private Location previous;

    @Autowired
    public IslandHandler(@Qualifier("island-config") ConfigManager<IslandConfig> config) {
        this.config = config;
        this.islands = config.getConfig().getIslands();

        if (!islands.isEmpty()) {
            this.previous = islands.get(islands.size() - 1).getSpawn().bukkitLocation();
        }
    }

    public Island getPlayerIsland(SBPlayer player) {
        for (Island island : islands) {
            if (island.getUuid().equals(player.getUuid())) {
                return island;
            }
        }
        return null;
    }



    public IslandLocation getNextLocation() {

        //TODO implement the island sorting type

        /**
         * Up = +X, 0Z
         * Down = -X, 0Z
         * Right = 0X, +Z
         * Left = 0X, -Z
         * DIAG-Right = +Z, +X / X=Z
         * DIAG-LEFT = -Z, +X, X=Z
         * DOWN-DIAG-LEFT = -Z, -X / X=Z
         * Down-DIAG-RIGHT = +z, -X. X=Z
         */

        int lastX = this.previous.getBlockX();
        int lastZ = this.previous.getBlockZ();
        int y = 100;

        //TODO finish island location calculations

        if (lastX > 0 && lastZ == 0) {
            //Last was UP
        }

        return new IslandLocation("world", 0, 100, 0);
    }

    public void createIsland(SBPlayer player) {
        Island island = new Island(player.getUuid(), getNextLocation());
        islands.add(island);

        //TODO Generate islands

        Bukkit.getWorld("world").getBlockAt(getNextLocation().bukkitLocation()).setType(Material.STONE);
    }

    public void save() {
        this.config.getConfig().setIslands(islands);
        this.config.saveConfig();
    }
}
