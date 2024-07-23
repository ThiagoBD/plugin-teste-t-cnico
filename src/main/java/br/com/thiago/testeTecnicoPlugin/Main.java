package br.com.thiago.testeTecnicoPlugin;

import br.com.thiago.testeTecnicoPlugin.comands.HomeCommand;
import br.com.thiago.testeTecnicoPlugin.comands.SetHomeCommand;
import br.com.thiago.testeTecnicoPlugin.dao.HomeDAO;
import br.com.thiago.testeTecnicoPlugin.dao.HomeDAOFactory;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    private HomeDAO homeDAO;

    @Override
    public void onEnable() {
        int maxHomes = 1;
        homeDAO = HomeDAOFactory.createHomeDAO();

        this.getCommand("sethome").setExecutor(new SetHomeCommand(homeDAO, maxHomes));
        this.getCommand("home").setExecutor(new HomeCommand(homeDAO));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
