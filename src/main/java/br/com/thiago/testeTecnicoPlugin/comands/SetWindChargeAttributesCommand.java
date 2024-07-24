package br.com.thiago.testeTecnicoPlugin.comands;

import br.com.thiago.testeTecnicoPlugin.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SetWindChargeAttributesCommand implements CommandExecutor {
    private final Main plugin;

    public SetWindChargeAttributesCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] strings) {
        if (!sender.isOp()) {
            sender.sendMessage("You do not have permission to use this command.");
            return true;
        }
        if (strings.length != 2) {
            sender.sendMessage("Uso: /setAttributesWindCharge <damage> <speed>");
            return true;
        }
        try {
            double damage = Double.parseDouble(strings[0]);
            double velocity = Double.parseDouble(strings[1]);
            Main.setWindChargeDamage(damage);
            Main.setWindChargeVelocity(velocity);
            sender.sendMessage("WindCharge attributes set successfully!");
        }catch(NumberFormatException e){
            sender.sendMessage("Invalid number format.");
        }

        return true;
    }
}
