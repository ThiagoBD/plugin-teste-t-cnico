package br.com.thiago.testeTecnicoPlugin.dao;

import br.com.thiago.testeTecnicoPlugin.model.PlayerHome;

import java.util.List;
import java.util.UUID;

public class HomeDAOImpl implements HomeDAO {
    @Override
    public void saveHome(PlayerHome home) {

    }

    @Override
    public PlayerHome getHome(UUID playerId) {
        return null;
    }

    @Override
    public List<PlayerHome> getHomes(UUID playerId) {
        return List.of();
    }

    @Override
    public int countHomes(UUID playerId) {
        return 0;
    }
}
