package com.mcavenue.skyblock;

import com.mcavenue.skyblock.config.ConfigManager;
import com.mcavenue.skyblock.config.IslandConfig;
import com.mcavenue.skyblock.config.PlayerConfig;
import org.bukkit.Bukkit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.mcavenue.skyblock")
class SpringContext {

    @Bean(name = "player-config")
    public ConfigManager<PlayerConfig> playerConfig() {
        ConfigManager<PlayerConfig> config = new ConfigManager<>("players.json", PlayerConfig.class);
        config.init();

        return config;
    }

    @Bean(name = "island-config")
    public ConfigManager<IslandConfig> islandConfig() {
        ConfigManager<IslandConfig> config = new ConfigManager<>("islands.json", IslandConfig.class);
        config.init();

        return config;
    }

    @Bean
    public Skyblock plugin() {
        return (Skyblock) Bukkit.getPluginManager().getPlugin("Skyblock");
    }
}
