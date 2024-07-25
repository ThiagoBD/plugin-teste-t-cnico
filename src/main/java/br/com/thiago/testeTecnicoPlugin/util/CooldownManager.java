package br.com.thiago.testeTecnicoPlugin.util;

import br.com.thiago.testeTecnicoPlugin.Main;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CooldownManager {
    private final Map<UUID, Long> cooldowns = new HashMap<>();


    public void setCooldown(UUID playerId) {
        cooldowns.put(playerId, System.currentTimeMillis() + Main.getCooldownTime());
    }

    public boolean hasCooldown(UUID playerId) {
        return cooldowns.containsKey(playerId) && cooldowns.get(playerId) > System.currentTimeMillis();
    }

    public long getRemainingTime(UUID playerId) {
        if (!cooldowns.containsKey(playerId)) return 0;
        long remainingTime = cooldowns.get(playerId) - System.currentTimeMillis();
        return Math.max(remainingTime, 0);
    }
}
