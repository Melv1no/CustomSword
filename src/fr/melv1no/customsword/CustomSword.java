package fr.melv1no.customsword;

import fr.melv1no.customsword.commands.CustomSwordCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
