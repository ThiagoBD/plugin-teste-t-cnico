package br.com.thiago.testeTecnicoPlugin.comands;

import br.com.thiago.testeTecnicoPlugin.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ConfigurationHomeAttributesCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.isOp()) {
            sender.sendMessage("You do not have permission to use this command.");
            return true;
        }
        if (args.length != 2) {
            sender.sendMessage("Uso: /setHomeConfig <CoolDown> <particle>(true or false)");
            return true;
        }
        if(!args[1].equalsIgnoreCase("true") && !args[1].equalsIgnoreCase("false")){
            sender.sendMessage("o comando /setHomeConfig tem que receber um valor (true ou false) no seu 2 campo.");
            return true;
        }
        try{
            long CooldownHome = Long.parseLong(args[0]);
            boolean isSummonParticleTeleport = Boolean.parseBoolean(args[1]);
            Main.setCooldownTime(CooldownHome);
            Main.setIsSummonParticleTeleport(isSummonParticleTeleport);
        }catch(NumberFormatException e){
            sender.sendMessage("Invalid number format.");
        }
        return true;
    }
}
