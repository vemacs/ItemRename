package org.chaospvp.rename;

import com.google.common.base.Joiner;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class ItemRename extends JavaPlugin {
    private static final Joiner joiner = Joiner.on(" ");

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("rename")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (args.length == 0) {
                    return false;
                }
                if (p.getItemInHand().getType() == Material.MOB_SPAWNER) {
                    p.sendMessage(ChatColor.RED + "Renaming mob spawners is not supported.");
                    return true;
                }
                if (p.getItemInHand().getType() == Material.TRIPWIRE_HOOK) {
                    p.sendMessage(ChatColor.RED + "Renaming crate keys is not supported.");
                    return true;
                }
                if (p.getItemInHand().getType() != Material.AIR && p.getItemInHand().getType() != null) {
                    ItemStack item = p.getItemInHand();
                    ItemMeta meta = item.getItemMeta();
                    String name = joiner.join(args);
                    meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
                    item.setItemMeta(meta);
                    p.sendMessage(ChatColor.GREEN + "Item has been renamed to " +
                            ChatColor.YELLOW + item.getItemMeta().getDisplayName());
                } else {
                    p.sendMessage(ChatColor.RED + "Please hold an item to rename.");
                }
                return true;
            } else {
                sender.sendMessage(ChatColor.RED + "This command can only be run by a player.");
                return true;
            }
        }
        return false;
    }
}
