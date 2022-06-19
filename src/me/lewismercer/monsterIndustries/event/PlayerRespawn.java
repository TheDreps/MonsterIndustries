package me.lewismercer.monsterIndustries.event;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import me.lewismercer.monsterIndustries.utils.GameUtils;

public class PlayerRespawn implements Listener{
	
	public static ArrayList<UUID> watchList = new ArrayList<>();
	
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerRespawn(PlayerRespawnEvent e) {
		Player p = e.getPlayer();
		if (watchList.contains(p.getUniqueId())) {
			p.getInventory().clear();
			p.getInventory().setItem(0, GameUtils.setupMenuItem);

			World world = GameUtils.setupWorlds.get(p.getUniqueId());
			e.setRespawnLocation(world.getSpawnLocation());
		}
		
	}

}
