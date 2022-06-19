package me.lewismercer.monsterIndustries.event;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

import me.lewismercer.monsterIndustries.lang.EnglishLang;

public class PlayerTeleport implements Listener{
	
	public static ArrayList<UUID> watchList = new ArrayList<>();
	
	@EventHandler
	public void onPlayerTeleport (PlayerTeleportEvent e) {
		Player p = e.getPlayer();
		if(watchList.contains(p.getUniqueId())) {
			if(!(e.getFrom().getWorld().equals(e.getTo().getWorld()))) {
				e.setCancelled(true);
				p.sendMessage(EnglishLang.setupCantTeleport);
			}
		}
	}

}
