package com.mcavenue.skyblock;

import com.mcavenue.skyblock.command.IslandCommand;
import com.mcavenue.skyblock.config.IslandConfig;
import com.mcavenue.skyblock.island.IslandHandler;
import com.mcavenue.skyblock.player.PlayerHandler;
import com.mcavenue.skyblock.player.PlayerListener;
import org.bukkit.plugin.java.JavaPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class Skyblock extends JavaPlugin {

    private AnnotationConfigApplicationContext context;

    @Override
    public void onEnable() {
        setupContext();
        registerListeners();

        IslandCommand command = (IslandCommand) context.getBean("islandCommand");
        getCommand("is").setExecutor(command);
    }

    @Override
    public void onDisable() {
        IslandHandler islandHandler = (IslandHandler) context.getBean("islandHandler");
        islandHandler.save();

        PlayerHandler playerHandler = (PlayerHandler) context.getBean("playerHandler");
        playerHandler.save();
    }

    private void registerListeners() {
        PlayerListener playerListener = (PlayerListener) context.getBean("playerListener");
        getServer().getPluginManager().registerEvents(playerListener, this);
    }

    private void setupContext() {
        this.context = new AnnotationConfigApplicationContext();
        context.setClassLoader(Skyblock.class.getClassLoader());
        context.register(SpringContext.class);
        context.refresh();
    }
}
