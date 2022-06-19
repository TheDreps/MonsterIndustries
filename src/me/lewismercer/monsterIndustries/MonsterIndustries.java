package me.lewismercer.monsterIndustries;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.lewismercer.monsterIndustries.commands.MICmd;
import me.lewismercer.monsterIndustries.commands.SetupCommands;
import me.lewismercer.monsterIndustries.event.AsyncChat;
import me.lewismercer.monsterIndustries.event.InventoryClick;
import me.lewismercer.monsterIndustries.event.PlayerDrop;
import me.lewismercer.monsterIndustries.event.PlayerInteract;
import me.lewismercer.monsterIndustries.event.PlayerQuit;
import me.lewismercer.monsterIndustries.event.PlayerRespawn;
import me.lewismercer.monsterIndustries.event.PlayerTeleport;
import me.lewismercer.monsterIndustries.lang.EnglishLang;
import me.lewismercer.monsterIndustries.utils.GameUtils;

public class MonsterIndustries extends JavaPlugin{
	
	
	private static MonsterIndustries instance;


	public static MonsterIndustries getInstance() {
		return instance;
	}

	public void onEnable() {
		
		Bukkit.getLogger().info("Enabled the uwu plugin!");
		
		instance = this;
		
		registerListeners();
		registerCommands();
		loadConfiguration();
	}
	
	public void onDisable() {
		//kill all current games
		
		if(GameUtils.inSetup.size() >=1) {
			for(int i = 0; i<GameUtils.inSetup.size(); i++) {
				UUID uuid = GameUtils.inSetup.get(i);
				SetupCommands.cancel(Bukkit.getPlayer(uuid));
			}
			
			getServer().getConsoleSender().sendMessage(EnglishLang.setupCancel);
			
		}
	}
	
	private void registerCommands() {
		getCommand("mi").setExecutor(new MICmd());
	}
	
	private void loadConfiguration() {
		
	}
	
	private void registerListeners() {
		getServer().getPluginManager().registerEvents(new InventoryClick(), this);
		getServer().getPluginManager().registerEvents(new AsyncChat(), this);
		getServer().getPluginManager().registerEvents(new PlayerInteract(), this);
		getServer().getPluginManager().registerEvents(new PlayerDrop(), this);
		getServer().getPluginManager().registerEvents(new PlayerQuit(), this);
		getServer().getPluginManager().registerEvents(new PlayerTeleport(), this);
		getServer().getPluginManager().registerEvents(new PlayerRespawn(), this);
	}

}
