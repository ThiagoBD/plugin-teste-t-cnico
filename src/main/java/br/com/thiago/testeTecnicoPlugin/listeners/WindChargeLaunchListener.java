package br.com.thiago.testeTecnicoPlugin.listeners;

import br.com.thiago.testeTecnicoPlugin.Main;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;

public class WindChargeLaunchListener implements Listener {

    @EventHandler
    public void onWindChargeLaunch(ProjectileLaunchEvent event){
        if (event.getEntity() instanceof WindCharge){
            WindCharge windCharge = (WindCharge) event.getEntity();
            if(windCharge.getShooter() instanceof Player){
                Main.getWindChargeVelocity().ifPresent(velocity -> windCharge.setVelocity(windCharge.getVelocity().multiply(velocity)));
            }
        }
    }
}

