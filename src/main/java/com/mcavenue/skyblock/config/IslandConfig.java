package com.mcavenue.skyblock.config;

import com.mcavenue.skyblock.island.Island;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;


@Data
@Component
public class IslandConfig {

    private List<Island> islands;
}
