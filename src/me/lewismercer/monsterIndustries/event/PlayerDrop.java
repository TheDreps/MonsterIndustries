package me.lewismercer.monsterIndustries.event;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class PlayerDrop implements Listener{
	
	public static ArrayList<UUID> watchList = new ArrayList<>();

	@EventHandler
	public void onPlayerDrop (PlayerDropItemEvent e) {
		if(watchList.contains(e.getPlayer().getUniqueId())) {
			e.setCancelled(true);
		}
	}
	
}
