package br.com.thiago.testeTecnicoPlugin.dao;

import br.com.thiago.testeTecnicoPlugin.util.DataBaseConnection;

public class HomeDAOFactory {
    public static HomeDAO createHomeDAO() {
        return new HomeDAOImpl(DataBaseConnection.getInstance().getConnection());
    }
}
