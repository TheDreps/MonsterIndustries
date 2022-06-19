package me.lewismercer.monsterIndustries.commands;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.lewismercer.monsterIndustries.Game;
import me.lewismercer.monsterIndustries.lang.EnglishLang;
import me.lewismercer.monsterIndustries.utils.CurrentGames;
import me.lewismercer.monsterIndustries.utils.RandomBits;

public class PlayerCommands {

	
	/*
	 * joingame
	 */
	
	public static boolean JoinGame(CommandSender sender, String[] args) {
		
		if(!(RandomBits.playerTest(sender))){sender.sendMessage(EnglishLang.playersOnly);return true;} //Checks if its a player
		Player p = (Player) sender;
		int gameRequested;

		if(args.length == 1) {
			p.sendMessage(EnglishLang.whatGame);
					
			
		}else if(args.length >= 2) {
			
	        if(!(RandomBits.numberTest(args[1]))){p.sendMessage(EnglishLang.notANumber);return false;}else { gameRequested = Integer.parseInt(args[1]);} // checks & parses int
	        
	        Game game = CurrentGames.map.get(gameRequested);
	        
	        
			p.sendMessage(EnglishLang.joiningGame(game.gameID));
			Location lc=new Location(game.world, 0, 70, 0);
			p.teleport(lc);
			
		}else {
			p.sendMessage(EnglishLang.wrongSyntax);
		}
		
		
		return true;
	}
	
	
	public static boolean listGames(CommandSender sender, String[] args) {
		String gamesListBuilder = "";
		for(int i : CurrentGames.map.keySet()) { 
			gamesListBuilder = gamesListBuilder + CurrentGames.map.get(i).gameID + ", ";
		}
		if(gamesListBuilder.length() >= 1) {
			gamesListBuilder = gamesListBuilder.substring( 0, gamesListBuilder.length() - 2);
			sender.sendMessage(EnglishLang.gamesList(gamesListBuilder));
		}else {
			sender.sendMessage(EnglishLang.noGames);
		}
		
		
		return true;
	}

}
