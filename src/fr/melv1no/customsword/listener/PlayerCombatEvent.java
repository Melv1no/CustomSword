package fr.melv1no.customsword.listener;

import fr.melv1no.customsword.CustomSword;
import org.bukkit.Bukkit;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerCombatEvent implements Listener {
    private CustomSword customSword;
    public PlayerCombatEvent(CustomSword customSword){
        this.customSword = customSword;
    }

    @EventHandler
    public void onPlayerCombat(EntityDamageByEntityEvent e){
        if(e.getEntity() instanceof Cow){
           // Player hitPlayer = (((Player) e.getEntity()).getPlayer());
            Player attacker = (Player) e.getDamager();
            Bukkit.broadcastMessage("Meuhhh");
            ItemStack sword;
            int i =0;
            for( i =0; i < customSword.customSwordItemsList.size() ;i++){
                if(attacker.getItemInHand().equals(customSword.customSwordItemsList.get(i))){
                    Bukkit.broadcastMessage(customSword.customSwordItemsList.get(i).getItemMeta().getDisplayName());
                    for(String CustomSwordID : customSword.getConfig().getConfigurationSection("CustomSword").getKeys(false)){
                       if(customSword.customSwordItemsList.get(i).getItemMeta().getDisplayName() == CustomSwordID) {
                           int percentage = customSword.getConfig().getInt("CustomSword." + CustomSwordID + ".onHitPlayer.percentage");
                           int d = (int)Math.random() * 100;
                           Bukkit.broadcastMessage(String.valueOf(d));
                           if ( percentage > d) {
                               Bukkit.broadcastMessage("Test");
                              // hitPlayer.addPotionEffect(new PotionEffect(PotionEffectType.getByName(customSword.getConfStr("CustomSword." + CustomSwordID + ".onHitPlayer.effect")), customSword.getConfig()
                             //          .getInt("CustomSword." + CustomSwordID + ".onHitPlayer.time"), customSword.getConfig().getInt("CustomSword." + CustomSwordID + ".onHitPlayer.modifier")));
                           }
                       }
                    }
                }else{
                    Bukkit.broadcastMessage(attacker.getDisplayName() + "     " + attacker.getItemInHand().getItemMeta().getDisplayName() + "---------" + customSword.customSwordItemsList.get(i).getItemMeta().getDisplayName());
                }
            }
        }

    }
}
