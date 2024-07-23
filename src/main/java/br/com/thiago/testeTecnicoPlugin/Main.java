package br.com.thiago.testeTecnicoPlugin;

import br.com.thiago.testeTecnicoPlugin.comands.HomeCommand;
import br.com.thiago.testeTecnicoPlugin.comands.SetHomeCommand;
import br.com.thiago.testeTecnicoPlugin.dao.HomeDAO;
import br.com.thiago.testeTecnicoPlugin.dao.HomeDAOFactory;
import br.com.thiago.testeTecnicoPlugin.util.CooldownManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    private HomeDAO homeDAO;
    private CooldownManager cooldownManager;

    @Override
    public void onEnable() {
        int maxHomes = 1;
        long cooldownTime = 60000;

        homeDAO = HomeDAOFactory.createHomeDAO();
        cooldownManager = new CooldownManager(cooldownTime);

        this.getCommand("sethome").setExecutor(new SetHomeCommand(homeDAO,cooldownManager, maxHomes));
        this.getCommand("home").setExecutor(new HomeCommand(homeDAO,cooldownManager));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
