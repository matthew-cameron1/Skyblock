package com.mcavenue.skyblock.island;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@RequiredArgsConstructor
public class Island {

    private final UUID uuid;
    private final IslandLocation spawn;
    private final List<UUID> members = new ArrayList<>();


    public void addMember(Player player) {
        if (this.members.contains(player.getUniqueId())) {
            return;
        }

        this.members.add(player.getUniqueId());
    }

    public void removeMember(Player player) {
        if (uuid.equals(player.getUniqueId())) {
            return;
        }

        if (!this.members.contains(player.getUniqueId())) {
            return;
        }

        this.members.remove(player.getUniqueId());
    }
}
