package me.lewismercer.monsterIndustries.utils;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.lewismercer.monsterIndustries.lang.EnglishLang;

public class RandomBits {
	
	
	/*
	 * Tests if its a player that sent the command
	 */
	public static boolean playerTest(CommandSender sender) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(EnglishLang.playersOnly);
			return false;
		}else {
			return true;
		}
	}
	
	/*
	 * Number test
	 */
	public static boolean numberTest(String str) {
		try 
        { 
            Integer.parseInt(str); 
            return true;
        }  
        catch (NumberFormatException e)  {
        
            return false;
        } 
	}
}
