package me.lewismercer.monsterIndustries.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import me.lewismercer.monsterIndustries.utils.GameUtils;

public class PlayerInteract implements Listener{
	
	@EventHandler
	public void onPlayerInteractEvent(PlayerInteractEvent e) {
		if (e.getPlayer().getInventory().getItemInMainHand().equals(GameUtils.setupMenuItem)) {
			if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)
					|| e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
				Player p = e.getPlayer();
				p.openInventory(InventoryClick.setupMenu);
				e.setCancelled(true);
			}

		}
	}

}
