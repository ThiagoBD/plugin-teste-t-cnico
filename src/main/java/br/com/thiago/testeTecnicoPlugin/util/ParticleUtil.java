package br.com.thiago.testeTecnicoPlugin.util;

import br.com.thiago.testeTecnicoPlugin.Main;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

public class ParticleUtil {
    public static void showParticlesInLocation(Location particle, Boolean isSummonParticle) {
        if(isSummonParticle){
            particle.getWorld().spawnParticle(Particle.DRAGON_BREATH, particle, 100, 1, 1, 1, 0.1);
        }

    }



}
