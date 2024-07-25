package br.com.thiago.testeTecnicoPlugin;

import br.com.thiago.testeTecnicoPlugin.comands.ConfigurationHomeAttributesCommand;
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
    private static Optional<Double>windChargeDamage = Optional.empty();
    private static Optional<Double> windChargeVelocity = Optional.empty();
    private static Boolean isSummonParticleTeleport = false;
    private static Boolean isSummonParticleWindCharge = false;
    private static long cooldownTime = 60000;

    @Override
    public void onEnable() {
        int maxHomes = 1;
        homeDAO = HomeDAOFactory.createHomeDAO();

        this.getCommand("setAttributesWindCharge").setExecutor(new SetWindChargeAttributesCommand());
        getServer().getPluginManager().registerEvents(new WindChargeLaunchListener(), this);
        getServer().getPluginManager().registerEvents(new WindChargeDamageListener(), this);
        this.getCommand("setHomeConfig").setExecutor(new ConfigurationHomeAttributesCommand());
        this.getCommand("sethome").setExecutor(new SetHomeCommand(homeDAO,new CooldownManager(), maxHomes));
        this.getCommand("home").setExecutor(new HomeCommand(homeDAO));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
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

    public static long getCooldownTime() {
        return cooldownTime;
    }

    public static void setCooldownTime(long cooldownTime) {
        Main.cooldownTime = cooldownTime;
    }
}
