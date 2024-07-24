package br.com.thiago.testeTecnicoPlugin.listeners;

import br.com.thiago.testeTecnicoPlugin.Main;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.WindCharge;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityKnockbackByEntityEvent;

public class WindChargeDamageListener implements Listener {

    @EventHandler
    public void onWindChargeDamage(EntityDamageByEntityEvent event){
        if (event.getDamager().getType() == EntityType.WIND_CHARGE){
            Location damageLocation = event.getEntity().getLocation();
            WindCharge windCharge = (WindCharge) event.getDamager();
            Main.getWindChargeDamage().ifPresent(damage -> event.setDamage(damage));


        }
    }


}
