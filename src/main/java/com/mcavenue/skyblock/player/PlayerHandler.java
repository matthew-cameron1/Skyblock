package com.mcavenue.skyblock.player;
import com.mcavenue.skyblock.config.ConfigManager;
import com.mcavenue.skyblock.config.PlayerConfig;
import org.bukkit.entity.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PlayerHandler {

    private final ConfigManager<PlayerConfig> config;

    private List<SBPlayer> players;

    @Autowired
    public PlayerHandler(ConfigManager<PlayerConfig> config) {
        this.config = config;
        this.players = config.getConfig().getPlayers();
    }

    public SBPlayer getPlayer(String name) {
        for (SBPlayer sbPlayer : players) {
            if (sbPlayer.getName().equalsIgnoreCase(name)) {
                return sbPlayer;
            }
        }
        return null;
    }

    public SBPlayer getPlayer(Player player) {
        return getPlayer(player.getName());
    }

    public void join(Player player) {
        SBPlayer sbPlayer = getPlayer(player);

        if (sbPlayer == null) {
            sbPlayer = new SBPlayer();
            sbPlayer.setFriends(new ArrayList<>());
            sbPlayer.setMainIsland(null);
            sbPlayer.setName(player.getName());
            sbPlayer.setUuid(player.getUniqueId());
            this.players.add(sbPlayer);
        }
    }

    public void save() {
        config.getConfig().setPlayers(players);
        config.saveConfig();
    }
}
