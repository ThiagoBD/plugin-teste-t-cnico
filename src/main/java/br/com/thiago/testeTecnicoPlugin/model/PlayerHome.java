package br.com.thiago.testeTecnicoPlugin.model;

import org.bukkit.Location;

import java.util.UUID;

public class PlayerHome {
    private final UUID playerId;
    private final String homeName;
    private final Location location;

    public PlayerHome(UUID playerId, String homeName, Location location) {
        this.playerId = playerId;
        this.homeName = homeName;
        this.location = location;
    }

    public UUID getPlayerId() {
        return playerId;
    }

    public String getHomeName() {
        return homeName;
    }

    public Location getLocation() {
        return location;
    }
}
