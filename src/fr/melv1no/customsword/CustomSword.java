package fr.melv1no.customsword;

import fr.melv1no.customsword.commands.CustomSwordCommand;
import fr.melv1no.customsword.listener.PlayerCombatEvent;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;


public class CustomSword extends JavaPlugin {
    public ArrayList<ItemStack> customSwordItemsList = new ArrayList<>();
    @Override
    public void onEnable() {
        saveDefaultConfig();
        if(!getConfig().getBoolean("plugin.enable")){
            this.getPluginLoader().disablePlugin(this);
        }
        Bukkit.getPluginCommand("cs").setExecutor(new CustomSwordCommand(this));
        getServer().getPluginManager().registerEvents(new PlayerCombatEvent(this), this);
    }
    public String getConfStr(String confPath){
        return this.getConfig().getString(confPath).replace("&", "§");
    }
}
