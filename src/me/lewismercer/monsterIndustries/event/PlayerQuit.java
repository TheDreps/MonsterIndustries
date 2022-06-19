package me.lewismercer.monsterIndustries.event;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.lewismercer.monsterIndustries.commands.SetupCommands;

public class PlayerQuit implements Listener{
	
	public static ArrayList<UUID> watchList = new ArrayList<>();
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		if(watchList.contains(e.getPlayer().getUniqueId())) {
			SetupCommands.cancel(e.getPlayer());
		}
	}

}
