package com.mcavenue.skyblock.command;


import com.mcavenue.skyblock.Skyblock;
import com.mcavenue.skyblock.island.IslandHandler;
import com.mcavenue.skyblock.player.PlayerHandler;
import com.mcavenue.skyblock.player.SBPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IslandCommand implements CommandExecutor {

    @Autowired
    public Skyblock plugin;

    @Autowired
    private IslandHandler islandHandler;

    @Autowired
    private PlayerHandler playerHandler;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;
        SBPlayer sbPlayer = playerHandler.getPlayer(player.getName());

        if (args.length == 0) {
            player.sendMessage(ChatColor.GREEN + "Working on help command");
            return true;
        }

        if (args.length == 1) {

            String subCommand = args[0];
            if (subCommand.equalsIgnoreCase("create")) {

                boolean hasIsland = islandHandler.getPlayerIsland(sbPlayer) != null;

                if (hasIsland) {
                    player.sendMessage(ChatColor.RED + "You already have an island!");
                    return true;
                }

                islandHandler.createIsland(sbPlayer);
                islandHandler.save();
                player.sendMessage(ChatColor.GREEN + "Created your island! Teleporting in 5 seconds...");

                Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> player.teleport(islandHandler.getPlayerIsland(sbPlayer).getSpawn().bukkitLocation()), 20 * 5L);
            }
        } else if (args.length == 2) {

            String subCommand = args[0];

            if (subCommand.equalsIgnoreCase("invite")) {
                String user = args[0];

                Player pl = Bukkit.getPlayer(user);

                if (pl == null || !pl.isOnline()) {
                    player.sendMessage(ChatColor.GOLD + ChatColor.BOLD.toString() + user + ChatColor.RED + " is offline at this time! Tell them to log on to invite them!");
                    return true;
                }

                pl.sendMessage(ChatColor.GOLD + ChatColor.BOLD.toString() + player.getName() +
                        ChatColor.YELLOW + " has invited you to join their island! " +
                        ChatColor.GREEN + ChatColor.BOLD.toString() + "ACCEPT / "
                        + ChatColor.RED + ChatColor.BOLD.toString() + "DECLINE");
                //TODO invite player
            }
        }

        return false;
    }
}
