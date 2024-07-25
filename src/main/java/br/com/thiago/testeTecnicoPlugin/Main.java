package br.com.thiago.testeTecnicoPlugin;

import br.com.thiago.testeTecnicoPlugin.comands.HomeCommand;
import br.com.thiago.testeTecnicoPlugin.comands.SetHomeCommand;
import br.com.thiago.testeTecnicoPlugin.comands.SetWindChargeAttributesCommand;
import br.com.thiago.testeTecnicoPlugin.dao.HomeDAO;
import br.com.thiago.testeTecnicoPlugin.dao.HomeDAOFactory;
import br.com.thiago.testeTecnicoPlugin.listeners.WindChargeDamageListener;
import br.com.thiago.testeTecnicoPlugin.listeners.WindChargeLaunchListener;

import br.com.thiago.testeTecnicoPlugin.util.CooldownManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Optional;

public final class Main extends JavaPlugin {
    private HomeDAO homeDAO;
    private CooldownManager cooldownManager;
    private static Optional<Double>windChargeDamage = Optional.empty();
    private static Optional<Double> windChargeVelocity = Optional.empty();
    private static Boolean isSummonParticleTeleport = false;
    private static Boolean isSummonParticleWindCharge = false;

    @Override
    public void onEnable() {
        int maxHomes = 1;
        long cooldownTime = 60000;

        homeDAO = HomeDAOFactory.createHomeDAO();
        cooldownManager = new CooldownManager(cooldownTime);

        this.getCommand("setAttributesWindCharge").setExecutor(new SetWindChargeAttributesCommand(this));
        getServer().getPluginManager().registerEvents(new WindChargeLaunchListener(), this);
        getServer().getPluginManager().registerEvents(new WindChargeDamageListener(), this);



        this.getCommand("sethome").setExecutor(new SetHomeCommand(homeDAO,cooldownManager, maxHomes));
        this.getCommand("home").setExecutor(new HomeCommand(homeDAO,cooldownManager));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public HomeDAO getHomeDAO() {
        return homeDAO;
    }

    public void setHomeDAO(HomeDAO homeDAO) {
        this.homeDAO = homeDAO;
    }

    public CooldownManager getCooldownManager() {
        return cooldownManager;
    }

    public void setCooldownManager(CooldownManager cooldownManager) {
        this.cooldownManager = cooldownManager;
    }

    public static Optional<Double> getWindChargeDamage() {
        return windChargeDamage;
    }

    public static void setWindChargeDamage(double damage) {
        windChargeDamage = Optional.of(damage);
    }

    public static Optional<Double> getWindChargeVelocity() {
        return windChargeVelocity;
    }

    public static void setWindChargeVelocity(double velocity) {
        windChargeVelocity = Optional.of(velocity);
    }

    public static Boolean getIsSummonParticleTeleport() {
        return isSummonParticleTeleport;
    }

    public static void setIsSummonParticleTeleport(Boolean isSummonParticleTeleport) {
        Main.isSummonParticleTeleport = isSummonParticleTeleport;
    }

    public static Boolean getIsSummonParticleWindCharge() {
        return isSummonParticleWindCharge;
    }

    public static void setIsSummonParticleWindCharge(Boolean isSummonParticleWindCharge) {
        Main.isSummonParticleWindCharge = isSummonParticleWindCharge;
    }
}
