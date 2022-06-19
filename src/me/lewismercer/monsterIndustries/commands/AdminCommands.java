package me.lewismercer.monsterIndustries.commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.lewismercer.monsterIndustries.Game;
import me.lewismercer.monsterIndustries.lang.EnglishLang;
import me.lewismercer.monsterIndustries.utils.CurrentGames;
import me.lewismercer.monsterIndustries.utils.GameUtils;
import me.lewismercer.monsterIndustries.utils.RandomBits;

public class AdminCommands {
	
	public static boolean creategame(CommandSender sender, String[] args) {
		
		
		if(args.length > 1) {
			sender.sendMessage(EnglishLang.wrongSyntax);
			return false;
		}
		Game game = new Game();
		CurrentGames.map.put(game.gameID, game);
		sender.sendMessage(EnglishLang.createdGame(game.gameID));
		
		
		
		
		return true;
	}
	
	public static boolean killgame(CommandSender sender, String[] args) {
		
		int id;
		Game game;

		//checking syntax
		if (args.length <= 1 || args.length >= 3) {
			sender.sendMessage(EnglishLang.wrongSyntax);
			return false;
			
			
			//if correct syntax (/mi killgame [id])
		} else {
			if(!(RandomBits.numberTest(args[1]))){sender.sendMessage(EnglishLang.notANumber);return false;}else { id = Integer.parseInt(args[1]);} // checks & parses int
	        if(!(CurrentGames.map.containsKey(id))) {sender.sendMessage(EnglishLang.gameNotActive);return false;} //checks if the game is active
	        game = CurrentGames.map.get(id);
	        Location lc = Bukkit.getServer().getWorlds().get(0).getSpawnLocation();
	        List<Player> players = game.world.getPlayers();
	        for (Player i : players) {
	        	i.teleport(lc);
	        }
		}

		//
		GameUtils.deleteWorld(CurrentGames.map.get(id).world);
		CurrentGames.map.remove(id);

		sender.sendMessage(EnglishLang.killedGame(id));
		return true;
	}
}

