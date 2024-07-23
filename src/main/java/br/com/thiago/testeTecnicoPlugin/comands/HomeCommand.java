package br.com.thiago.testeTecnicoPlugin.comands;

import br.com.thiago.testeTecnicoPlugin.dao.HomeDAO;
import br.com.thiago.testeTecnicoPlugin.model.PlayerHome;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class HomeCommand implements CommandExecutor {
    private final HomeDAO homeDAO;

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

        PlayerHome home = homeDAO.getHome(playerId);
        if (home == null) {
            player.sendMessage("Você não tem nenhuma home definida.");
            return true;
        }

        Location homeLocation = home.getLocation();
        player.teleport(homeLocation);
        player.sendMessage("Teleporte realizado com sucesso!");

        return true;
    }
}
