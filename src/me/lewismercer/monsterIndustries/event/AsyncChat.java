package me.lewismercer.monsterIndustries.event;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.lewismercer.monsterIndustries.MonsterIndustries;
import me.lewismercer.monsterIndustries.lang.EnglishLang;
import me.lewismercer.monsterIndustries.runnables.CreateWorldRunnable;
import me.lewismercer.monsterIndustries.runnables.SetupCancelRunnable;

public class AsyncChat implements Listener{
	
	public static ArrayList<UUID> watchList = new ArrayList<>();
	
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) throws IOException {

		 Player p = e.getPlayer();

		 if (watchList.indexOf(p.getUniqueId()) != -1) {
			 
			 String formattedArg = e.getMessage().trim();

			 if(!(formattedArg.indexOf(' ') >= 0)){
				if (formattedArg.equalsIgnoreCase("Cancel")) {
					Bukkit.getScheduler().runTask(MonsterIndustries.getInstance(), new SetupCancelRunnable(p));
					e.setCancelled(true);
				} else {
					Bukkit.getScheduler().runTask(MonsterIndustries.getInstance(), new CreateWorldRunnable(p, e.getMessage().trim()));
					watchList.remove(p.getUniqueId());
					e.setCancelled(true);
				}
			} else {
				p.sendMessage(EnglishLang.setupInvalidInput);
				e.setCancelled(true);
			}
		}

	}
}
