package me.lewismercer.monsterIndustries.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class InventoryManagement {
	
	public static Map<UUID, ItemStack[]> items = new HashMap<UUID, ItemStack[]>();
	public static Map<UUID, ItemStack[]> armor = new HashMap<UUID, ItemStack[]>();
	public static Map<UUID, GameMode> gamemode = new HashMap<UUID, GameMode>();
	public static Map<UUID, Location> location = new HashMap<UUID, Location>();
	public static Map<UUID, Float> xp = new HashMap<UUID, Float>();
	public static Map<UUID, Integer> level = new HashMap<UUID, Integer>();
	
	public static void storeAndClearItems(Player p, PlayerInventory inv) {
		UUID uuid = p.getUniqueId();

		items.put(uuid, inv.getContents());
		armor.put(uuid, inv.getArmorContents());
		gamemode.put(uuid, p.getGameMode());
		location.put(uuid, p.getLocation());
		xp.put(uuid, p.getExp());
		level.put(uuid, p.getLevel());
		
		p.getInventory().clear();
		p.setExp(0);
		p.setLevel(0);
		
		p.getInventory().setHelmet(null);
	    p.getInventory().setChestplate(null);
	    p.getInventory().setLeggings(null);
	    p.getInventory().setBoots(null);
	}
	
	public static void recoverPlayer(Player p) {
		UUID uuid = p.getUniqueId();
		
		p.setGameMode(gamemode.get(uuid));
		p.teleport(location.get(uuid));
		p.setLevel(level.get(uuid));
		p.setExp(xp.get(uuid));
		ItemStack[] contents = items.get(uuid);
	    ItemStack[] armorContents = armor.get(uuid);
	    
	    if(contents != null){
	        p.getInventory().setContents(contents);
	    }
	    else{
	        p.getInventory().clear();
	    }

	    if(armorContents != null){
	        p.getInventory().setArmorContents(armorContents);
	    }
	    else{
	        p.getInventory().setHelmet(null);
	        p.getInventory().setChestplate(null);
	        p.getInventory().setLeggings(null);
	        p.getInventory().setBoots(null);
	    }
	    
	    items.remove(uuid);
	    armor.remove(uuid);
	    location.remove(uuid);
	    xp.remove(uuid);
	}

}
