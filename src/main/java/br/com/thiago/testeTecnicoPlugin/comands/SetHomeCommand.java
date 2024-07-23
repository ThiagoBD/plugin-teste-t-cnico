package br.com.thiago.testeTecnicoPlugin.comands;

import br.com.thiago.testeTecnicoPlugin.dao.HomeDAO;
import br.com.thiago.testeTecnicoPlugin.model.PlayerHome;
import br.com.thiago.testeTecnicoPlugin.util.CooldownManager;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class SetHomeCommand implements CommandExecutor {
    private final HomeDAO homeDAO;
    private final CooldownManager cooldownManager;
    private final int maxHomes;


    public SetHomeCommand(HomeDAO homeDAO,CooldownManager cooldownManager, int maxHomes) {
        this.cooldownManager = cooldownManager;
        this.homeDAO = homeDAO;
        this.maxHomes = maxHomes;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Este comando só pode ser executado por jogadores.");
            return true;
        }
        Player player = (Player) sender;
        UUID playerId = player.getUniqueId();

        if (cooldownManager.hasCooldown(playerId)) {
            player.sendMessage("Você precisa esperar antes de definir outra home!");
            long remainingTime = cooldownManager.getRemainingTime(playerId);
            player.sendMessage("Tempo restante: " + (remainingTime / 1000) + " segundos.");
            return true;
        }

        int currentHomes = homeDAO.countHomes(playerId);
        if (currentHomes >= maxHomes) {
            player.sendMessage("Você já atingiu o número máximo de homes!");
            return true;
        }
        Location location = player.getLocation();
        PlayerHome home = new PlayerHome(playerId, strings[0], location);
        homeDAO.saveHome(home);
        cooldownManager.setCooldown(playerId);
        player.sendMessage("Home definida com sucesso!");

        return true;

    }
}
