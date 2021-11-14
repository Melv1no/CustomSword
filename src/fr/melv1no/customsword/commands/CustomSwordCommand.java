package fr.melv1no.customsword.commands;

import fr.melv1no.customsword.CustomSword;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

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
        customSword.customSwordItemsList.clear();
        ItemStack customSwordItem = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta customSwordItemMeta = customSwordItem.getItemMeta();


        for(String CustomSwordID : customSword.getConfig().getConfigurationSection("CustomSword").getKeys(false)){
            String partialPath = "CustomSword." + CustomSwordID;


            ArrayList<String> description = new ArrayList<>();
            for (String descriptionLine : customSword.getConfig().getStringList(partialPath + ".description")){
              description.add(descriptionLine.replace("&","§"));
            }
            customSwordItemMeta.setLore(description);





            String name;
            name = customSword.getConfStr(partialPath + ".name");
            customSwordItemMeta.setDisplayName(name);
/*
            for(String enchant : customSword.getConfig().getConfigurationSection(partialPath+".enchantement").getKeys(false)) {
            Bukkit.broadcastMessage(enchant + customSword.getConfig().getInt(partialPath+".enchantement."+enchant+".modifier"));
            customSwordItemMeta.addEnchant(Enchantment.getByName(enchant),customSword.getConfig().getInt(partialPath+".enchantement."+enchant+".modifier"), true);
            }*/
            customSwordItemMeta.addEnchant(Enchantment.DURABILITY,3,true);
            customSwordItemMeta.addEnchant(Enchantment.DAMAGE_ALL,5,true);
            customSwordItemMeta.addEnchant(Enchantment.FIRE_ASPECT,2,true);



            customSwordItem.setItemMeta(customSwordItemMeta);
            customSword.customSwordItemsList.add(customSwordItem);
            CustomSwordInventory.addItem(customSwordItem);
        }


    }


}
