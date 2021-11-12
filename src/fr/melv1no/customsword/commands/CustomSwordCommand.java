package fr.melv1no.customsword.commands;

import fr.melv1no.customsword.CustomSword;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CustomSwordCommand implements CommandExecutor {
    private CustomSword customSword;
    public CustomSwordCommand(CustomSword customSword) {
        this.customSword = customSword;
    }

    private Inventory CustomSwordInventory;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(!(sender instanceof Player)){
            System.err.println("Uniquement les joueurs peuvent utilisé cette commande !");
        }
        Player player = (Player) sender;
        CustomSwordInventory = Bukkit.getServer().createInventory(null, 27, customSword.getConfStr("gui.title"));
        InitInventory();
        player.openInventory(CustomSwordInventory);
        return false;
    }

    private void InitInventory() {
        for(String customSwordID : customSword.getConfig().getConfigurationSection("CustomSword").getKeys(true)){
            ItemStack test = new ItemStack(Material.ACACIA_DOOR);
            ItemMeta testmeta = test.getItemMeta();
            testmeta.setDisplayName(customSwordID);
            CustomSwordInventory.addItem(test);

        }
    }


}
