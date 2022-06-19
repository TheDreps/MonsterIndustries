package me.lewismercer.monsterIndustries.event;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.lewismercer.monsterIndustries.commands.SetupCommands;
import me.lewismercer.monsterIndustries.lang.EnglishLang;

public class InventoryClick implements Listener{
	
	public static Inventory setupMenu = Bukkit.createInventory(null, 9, EnglishLang.setupMenuTitle);
	
	public static ArrayList<UUID> watchList = new ArrayList<>();

	static {
		createDisplay(Material.LLAMA_SPAWN_EGG, setupMenu, 0, EnglishLang.setupCreateWorldItem, EnglishLang.setupCreateWorldMeta);
		createDisplay(Material.BARRIER, setupMenu, 8, EnglishLang.setupCancelItem, EnglishLang.setupCancelMeta);
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if(!(e.getCurrentItem() == null || e.getCurrentItem().getType().equals(Material.AIR))) { //Only IF player clicks on an item
			ItemStack clicked = e.getCurrentItem();
			String clickedName = clicked.getItemMeta().getDisplayName();

			if (e.getView().getTitle().equalsIgnoreCase(EnglishLang.setupMenuTitle)) { // If its the setup menu thats open																				// clicked
				e.setCancelled(true);

				if (clickedName.equalsIgnoreCase(EnglishLang.setupCreateWorldItem)) { //createWorld
					SetupCommands.createWorld(p);
					
				}else if(clickedName.equalsIgnoreCase(EnglishLang.setupCancelItem)) { //cancel
					SetupCommands.cancel(p);
				}
				
				
				p.closeInventory();
			}
			
			
		}else if(watchList.contains(p.getUniqueId())) { //If its not setup menu, but the player is on watchList.
			e.setCancelled(true);
		}
		
		
		
		
		

	}
	
	
	public static void createDisplay(Material material, Inventory inv, int Slot, String name, String lore) {
		ItemStack item = new ItemStack(material);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		ArrayList<String> Lore = new ArrayList<String>();
		Lore.add(lore);
		meta.setLore(Lore);
		item.setItemMeta(meta);
		inv.setItem(Slot, item); 
		}
	
	
}
