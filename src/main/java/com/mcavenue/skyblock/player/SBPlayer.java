package com.mcavenue.skyblock.player;

import com.mcavenue.skyblock.island.Island;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class SBPlayer {

    private Island mainIsland;
    private List<SBPlayer> friends;
    private String name;
    private UUID uuid;
}
