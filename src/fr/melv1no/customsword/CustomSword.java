package fr.melv1no.customsword;

import fr.melv1no.customsword.commands.CustomSwordCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


public class CustomSword extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        if(!getConfig().getBoolean("plugin.enable")){
            this.getPluginLoader().disablePlugin(this);
        }
        Bukkit.getPluginCommand("cs").setExecutor(new CustomSwordCommand(this));
    }
    public String getConfStr(String confPath){
        return this.getConfig().getString(confPath).replace("&", "§");
    }
}
