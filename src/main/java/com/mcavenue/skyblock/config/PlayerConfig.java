package com.mcavenue.skyblock.config;

import com.mcavenue.skyblock.player.SBPlayer;
import lombok.Data;

import java.util.List;

@Data
public class PlayerConfig {

    private List<SBPlayer> players;
}
