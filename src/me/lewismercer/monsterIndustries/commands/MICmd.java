package me.lewismercer.monsterIndustries.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.lewismercer.monsterIndustries.lang.EnglishLang;
import me.lewismercer.monsterIndustries.utils.RandomBits;

public class MICmd implements CommandExecutor{
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		//mi
		if(args == null || args.length == 0){
			sender.sendMessage(EnglishLang.MI);
			return true;
		}
		
		switch(args[0].toLowerCase()) {
		
		//mi [Command]
		//player commands
		case "joingame":
			return PlayerCommands.JoinGame(sender, args);
		case "listgames":
			return PlayerCommands.listGames(sender, args);
			
		//admin commands
		case "creategame":
			return AdminCommands.creategame(sender, args);
		case "killgame":
			return AdminCommands.killgame(sender, args);
			
		//setup commands
		case "setup":
			return SetupCommands.setup(sender, args);
		case "createworld":
			if(!(RandomBits.playerTest(sender))){sender.sendMessage(EnglishLang.playersOnly);return true;} //Checks if its a player
			return SetupCommands.createWorld((Player) sender);
		case "cancel":
			if(!(RandomBits.playerTest(sender))){sender.sendMessage(EnglishLang.playersOnly);return true;} //Checks if its a player
			return SetupCommands.cancel((Player) sender);
			
			
		default:
			sender.sendMessage(EnglishLang.unknown);
			return false;
		
		}
	}

}
