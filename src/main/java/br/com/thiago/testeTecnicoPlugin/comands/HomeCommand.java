package br.com.thiago.testeTecnicoPlugin.comands;

import br.com.thiago.testeTecnicoPlugin.Main;
import br.com.thiago.testeTecnicoPlugin.dao.HomeDAO;
import br.com.thiago.testeTecnicoPlugin.model.PlayerHome;
import br.com.thiago.testeTecnicoPlugin.util.CooldownManager;
import br.com.thiago.testeTecnicoPlugin.util.ParticleUtil;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


import java.util.UUID;

public class HomeCommand implements CommandExecutor {
    private final HomeDAO homeDAO;
    private final CooldownManager cooldownManager = new CooldownManager();



    public HomeCommand(HomeDAO homeDAO) {
        this.homeDAO = homeDAO;
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
            player.sendMessage("Você precisa esperar antes de usar o teleporte novamente!");
            long remainingTime = cooldownManager.getRemainingTime(playerId);
            player.sendMessage("Tempo restante: " + (remainingTime / 1000) + " segundos.");
            return true;
        }

        PlayerHome home = homeDAO.getHome(playerId);
        if (home == null) {
            player.sendMessage("Você não tem nenhuma home definida.");
            return true;
        }

        Location homeLocation = home.getLocation();
        player.teleport(homeLocation);
        ParticleUtil.showParticlesInLocation(player.getLocation(), Main.getIsSummonParticleTeleport());
        cooldownManager.setCooldown(playerId);
        player.sendMessage("Teleporte realizado com sucesso!");

        return true;
    }
}
