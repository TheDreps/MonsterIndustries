package me.lewismercer.monsterIndustries.commands;

import java.util.UUID;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.lewismercer.monsterIndustries.event.AsyncChat;
import me.lewismercer.monsterIndustries.event.InventoryClick;
import me.lewismercer.monsterIndustries.event.PlayerDrop;
import me.lewismercer.monsterIndustries.event.PlayerRespawn;
import me.lewismercer.monsterIndustries.event.PlayerTeleport;
import me.lewismercer.monsterIndustries.lang.EnglishLang;
import me.lewismercer.monsterIndustries.utils.GameUtils;
import me.lewismercer.monsterIndustries.utils.InventoryManagement;
import me.lewismercer.monsterIndustries.utils.RandomBits;
import net.md_5.bungee.api.ChatColor;

public class SetupCommands {
	
	
	
	
	
	//mi setup
	public static boolean setup(CommandSender sender, String[] args) {
		if(!(RandomBits.playerTest(sender))){sender.sendMessage(EnglishLang.playersOnly);return true;} //Checks if its a player
		Player p = (Player) sender;
		p.openInventory(InventoryClick.setupMenu);
		
		
		return true;
	}
	
	//mi createworld
	public static boolean createWorld(Player p) {
		if (GameUtils.inSetup.contains(p.getUniqueId())) {p.sendMessage(EnglishLang.setupCreateTwoWorlds);return false;} // Checks if already setting up a world, if so, won't let you continue
		if(!(AsyncChat.watchList.contains(p.getUniqueId()))){AsyncChat.watchList.add(p.getUniqueId());}
		String[] s = EnglishLang.setupCreateWorldInput.split("\n");
		for (String m : s) {
			p.sendMessage(ChatColor.GOLD + m);

		}
		return false;
		
	}
	
	//mi cancel
	public static boolean cancel(Player p) {
		UUID uuid = p.getUniqueId();
		
		p.sendMessage(EnglishLang.setupCancel);
		if(PlayerDrop.watchList.contains(uuid)) {PlayerDrop.watchList.remove(p.getUniqueId());} //Removes from playerDrop
		if(AsyncChat.watchList.contains(uuid)) {AsyncChat.watchList.remove(p.getUniqueId());} //Removes from AsyncChat
		if(PlayerTeleport.watchList.contains(uuid)) {PlayerTeleport.watchList.remove(p.getUniqueId());} //Removes from PlayerTeleport
		if(InventoryManagement.items.containsKey(uuid)){InventoryManagement.recoverPlayer(p);}; //Restores players inventory
		if (GameUtils.setupWorlds.containsKey(uuid)) {GameUtils.deleteWorld(GameUtils.setupWorlds.get(uuid));GameUtils.setupWorlds.remove(uuid);} //unloads & deletes the setup world.
		if(GameUtils.inSetup.contains(uuid)) {GameUtils.inSetup.remove(uuid);} //Removes the player from the inSetup map
		if(PlayerRespawn.watchList.contains(uuid)) {PlayerRespawn.watchList.remove(p.getUniqueId());} //Removes from PlayerRespawn
		
		
		return false;
	}

}
