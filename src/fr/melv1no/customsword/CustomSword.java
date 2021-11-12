package fr.melv1no.customsword;

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
        CheckDirectory();
    }

    private void CheckDirectory() {
        Path customSwordPath = Paths.get("plugins/CustomSword/CustomSword/");
        if(!Files.exists(customSwordPath)){
            returnError("§c Le dossier Custom Sword n'existe pas !\n Tentative de création !");
            try{
                Files.createDirectory(customSwordPath);
                returnSuccess("Le dossier Custom Sword a été créer !");
            }catch(Exception e){
                returnError("L'installation du plugin a échoué !\n" + e.getMessage());
                this.getPluginLoader().disablePlugin(this);
            }
        }
    }

    public void returnError(String message){
        Bukkit.getServer().getConsoleSender().sendMessage("§4" +message);
    }
    public void returnSuccess(String message){
        Bukkit.getServer().getConsoleSender().sendMessage("§a" + message);
    }

}
