package br.com.thiago.testeTecnicoPlugin.util;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

public class ParticleUtil {
    public static void showTeleportParticles(Player player) {
        Location location = player.getLocation();
        player.getWorld().spawnParticle(Particle.PORTAL, location, 100, 1, 1, 1, 0.1);
    }
}
