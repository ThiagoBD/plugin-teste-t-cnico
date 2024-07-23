package br.com.thiago.testeTecnicoPlugin.dao;

import br.com.thiago.testeTecnicoPlugin.model.PlayerHome;

import java.util.List;
import java.util.UUID;

public interface HomeDAO {
    void saveHome(PlayerHome home);
    PlayerHome getHome(UUID playerId);
    List<PlayerHome> getHomes(UUID playerId);
    int countHomes(UUID playerId);
}
