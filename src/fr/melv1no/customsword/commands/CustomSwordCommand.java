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
        CustomSwordInventory = customSword.getServer().createInventory(null, 27, customSword.getConfStr("gui.title"));
        InitInvenory();
        player.openInventory(CustomSwordInventory);
        return false;
    }

    private void InitInvenory() {

        for(String CustomSwordID : customSword.getConfig().getConfigurationSection("CustomSword").getKeys(false)){
            String PartialPath = "CustomSword." + CustomSwordID;
            String name;
            name = customSword.getConfStr(PartialPath + ".name");
            ItemStack customSwordItem = new ItemStack(Material.DIAMOND_SWORD);
            ItemMeta customSwordItemMeta = customSwordItem.getItemMeta();
            customSwordItemMeta.hasLore();
            customSwordItemMeta.setDisplayName(name);
            customSwordItem.setItemMeta(customSwordItemMeta);
            CustomSwordInventory.addItem(customSwordItem);
        }


    }


}
