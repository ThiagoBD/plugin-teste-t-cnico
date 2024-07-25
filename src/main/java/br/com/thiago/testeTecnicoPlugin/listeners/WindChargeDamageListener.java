package br.com.thiago.testeTecnicoPlugin.listeners;

import br.com.thiago.testeTecnicoPlugin.Main;
import br.com.thiago.testeTecnicoPlugin.util.ParticleUtil;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.WindCharge;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;


public class WindChargeDamageListener implements Listener {

    @EventHandler
    public void onWindChargeDamage(EntityDamageByEntityEvent event){
        if (event.getDamager().getType() == EntityType.WIND_CHARGE){
            WindCharge windCharge = (WindCharge) event.getDamager();
            Main.getWindChargeDamage().ifPresent(damage -> event.setDamage(damage));
            ParticleUtil.showParticlesInLocation(windCharge.getLocation(),Main.getIsSummonParticleWindCharge());

        }
    }


}
