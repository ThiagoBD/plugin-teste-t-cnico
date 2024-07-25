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
        if (strings.length != 3) {
            sender.sendMessage("Uso: /setAttributesWindCharge <damage> <speed> <particle(true or false)");
            return true;
        }
        if(!strings[2].equalsIgnoreCase("true") && !strings[2].equalsIgnoreCase("false")){
            sender.sendMessage("o comando /setAttributesWindCharge tem que receber um valor (true ou false) no seu 3 campo.");
            return true;
        }
        try {
            double damage = Double.parseDouble(strings[0]);
            double velocity = Double.parseDouble(strings[1]);
            boolean isSummonParticle = Boolean.parseBoolean(strings[2]);
            Main.setIsSummonParticleWindCharge(isSummonParticle);
            Main.setWindChargeDamage(damage);
            Main.setWindChargeVelocity(velocity);
            sender.sendMessage("WindCharge attributes set successfully!");
        }catch(NumberFormatException e){
            sender.sendMessage("Invalid number format.");
        }

        return true;
    }
}
