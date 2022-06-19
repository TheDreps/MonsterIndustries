package me.lewismercer.monsterIndustries.lang;

import org.bukkit.ChatColor;

import me.lewismercer.monsterIndustries.utils.CurrentGames;

public class EnglishLang {
	
	public static String prefix = ChatColor.GOLD + "[MI] "; //Will be able to change this in config this later on
	public static String itemPrefix = ChatColor.GOLD + "";
	public static String unknown = prefix + "Unknown command, use /mi help for commands";
	public static String MI = prefix + "Monster Industries plugin by lewismercer and tested by Melislis" + "\n" + "Type /mi help for commands";
	public static String wrongSyntax = prefix + "Wrong syntax!";
	public static String notANumber = prefix + "This is not a number!";
	public static String gameNotActive = prefix + "This game is not currently active!";
	public static String whatGame = prefix + "What game ID do you want?";
	public static String playersOnly = prefix + "This command is for players only!";
	
	//admin
	public static String createdGame(int id) {return prefix +"Created game: " + id;}
	public static String killedGame(int id) {return prefix + "Killed game: " + id + "!";}
	
	//Player
	public static String joiningGame(int id) {return prefix + "Joining game " + id + "!";}
	public static String gamesList(String gamesListBuilder) {return prefix + "Active games(" + CurrentGames.map.size() + "): " + gamesListBuilder;}
	public static String noGames = prefix + "There are no games currently active!";
	
	
	/*
	 * SETUP  \/
	 */
	
	//create world 
	public static String setupCreateWorldInput = ChatColor.GOLD + "\n\n\n\n\n\n"
			+ "Make sure the world you want to use has been copied in to the root folder with the other worlds" + ChatColor.BOLD + " BEFORE " + org.bukkit.ChatColor.RESET + ChatColor.GOLD  + "continuing!"
			+ "\n\nAnd make sure there is no spaces or illeagal charactors in the folder name."
			+ "\n\nPlease enter the world name exactly as it appears in the folder:";
	public static String setupInvalidInput = prefix + "Invalid input, please try again or type cancel to stop.";
	public static String setupWorldNotFound = prefix + "World not found! Please try again or type cancel to stop.";
	public static String setupCreatingWorld = prefix + "Creating world!";
	public static String setupCantTeleport = prefix + "Can't teleport out of the world while setting up! Use /mi cancel to quit the setup and disregard changes.";
	public static String setupCreateTwoWorlds = prefix + "You can't create two worlds at the same time! Use /mi cancel to stop the one you are currently doing.";
	
	//cancel
	public static String setupCancel = prefix + "Cancelling setup, changes have not been made!";
	
	//setup items
	public static String setupMenuItem = ChatColor.RED + "" + ChatColor.BOLD + "Setup Menu!";
	public static String setupMenuItemMeta = "Right click to open the setup menu!";
	
	public static String setupCreateWorldItem = itemPrefix + "Create World";
	public static String setupCreateWorldMeta = "/mi createworld";
	
	public static String setupCancelItem = itemPrefix + "Cancel Setup";
	public static String setupCancelMeta = "/mi cancel";
	
	//setup GUI
	public static String setupMenuTitle = "Monster Industries setup!";
}
