package com.mcavenue.skyblock.player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlayerListener implements Listener {

    private PlayerHandler playerHandler;

    @Autowired
    public PlayerListener(PlayerHandler playerHandler) {
        this.playerHandler = playerHandler;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        playerHandler.join(player);
    }
}
