package me.lewismercer.monsterIndustries.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.lewismercer.monsterIndustries.event.AsyncChat;
import me.lewismercer.monsterIndustries.event.PlayerDrop;
import me.lewismercer.monsterIndustries.event.PlayerQuit;
import me.lewismercer.monsterIndustries.event.PlayerRespawn;
import me.lewismercer.monsterIndustries.event.PlayerTeleport;
import me.lewismercer.monsterIndustries.lang.EnglishLang;

public class GameUtils {
	
	
	/*
	 * Create world 
	 */
	
	public static ArrayList<UUID> inSetup = new ArrayList<>();
	public static HashMap<UUID, World> setupWorlds = new HashMap<UUID, World>();
	public static ItemStack setupMenuItem;
	
	static { //creating the setup menu item
		ItemStack redstone = new ItemStack(Material.REDSTONE);
		ArrayList<String> Lore = new ArrayList<String>();
		Lore.add(EnglishLang.setupMenuItemMeta);
		ItemMeta redstoneMeta = redstone.getItemMeta();
		redstone.addUnsafeEnchantment(Enchantment.LOYALTY, 3);
		redstoneMeta.setDisplayName(EnglishLang.setupMenuItem);
		redstoneMeta.setLore(Lore);
		redstone.setItemMeta(redstoneMeta);
		setupMenuItem = redstone;
	}
	
	
	
	public static boolean createWorld(Player p, String worldName) throws IOException {
		UUID uuid = p.getUniqueId();
		
		if(inSetup.contains(uuid)) {p.sendMessage(EnglishLang.setupCreateTwoWorlds);return false;} //Checks if already setting up a world, if so, won't let you continue
		
		String worldSetupName = worldName + "_misetup";
		File exampleWorldFolder = new File(Bukkit.getWorldContainer() + "/" + worldName + "/");
		File worldInSetupFolder = new File(Bukkit.getWorldContainer() + "/" + worldName + "_misetup/");
		if (exampleWorldFolder.exists()) {

			p.sendMessage(EnglishLang.setupCreatingWorld);
			GameUtils.copyDirectory(exampleWorldFolder, worldInSetupFolder);
			World worldInSetup = GameUtils.createWorld(worldSetupName);
			setupWorlds.put(uuid, worldInSetup);
			inSetup.add(p.getUniqueId());
			
			InventoryManagement.storeAndClearItems(p, p.getInventory());
			p.teleport(worldInSetup.getSpawnLocation());
			p.setGameMode(GameMode.CREATIVE);
			p.getInventory().setItem(0, setupMenuItem);
			PlayerDrop.watchList.add(uuid);
			PlayerTeleport.watchList.add(uuid);
			PlayerQuit.watchList.add(uuid);
			PlayerRespawn.watchList.add(uuid);
			
			
			return true;
		} else {
			p.sendMessage(EnglishLang.setupWorldNotFound);
			AsyncChat.watchList.add(p.getUniqueId());
			return false;
		}
	}
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////

	public static boolean deleteWorld(World world) {
		if (!(Bukkit.getWorlds().contains(world))) {
			return false;
		}
		Bukkit.unloadWorld(world, false);
		deleteDirectory(world.getWorldFolder());
		return true;
	}

	static boolean deleteDirectory(File url) {
		File[] allContents = url.listFiles();
		if (allContents != null) {
			for (File file : allContents) {
				deleteDirectory(file);
			}
		}
		return url.delete();
	}
	
	public static World createWorld(String name) {
		WorldCreator wc = new WorldCreator(name);
		wc.generator(new WorldGen());
		World world = wc.createWorld();
		return world;
	}
	
	public static void copyDirectory(File sourceDir, File targetDir) throws IOException {
		if (sourceDir.isDirectory()) {
			copyDirectoryRecursively(sourceDir, targetDir);
		} else {
			Files.copy(sourceDir.toPath(), targetDir.toPath());
		}
	}

	private static void copyDirectoryRecursively(File source, File target) throws IOException {
		if (!target.exists()) {
			target.mkdir();
		}

		for (String child : source.list()) {
			copyDirectory(new File(source, child), new File(target, child));
		}
	}
}
