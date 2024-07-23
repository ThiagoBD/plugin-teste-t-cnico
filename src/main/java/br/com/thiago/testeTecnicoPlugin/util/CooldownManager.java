package br.com.thiago.testeTecnicoPlugin.util;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CooldownManager {
    private final Map<UUID, Long> cooldowns = new HashMap<>();
    private final long cooldownTime; // Tempo de cooldown em milissegundos

    public CooldownManager(long cooldownTime) {
        this.cooldownTime = cooldownTime;
    }

    public void setCooldown(UUID playerId) {
        cooldowns.put(playerId, System.currentTimeMillis() + cooldownTime);
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
