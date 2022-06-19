package me.lewismercer.monsterIndustries.runnables;

import java.io.IOException;

import org.bukkit.entity.Player;

import me.lewismercer.monsterIndustries.utils.GameUtils;

public class CreateWorldRunnable implements Runnable{

	private Player p;
	private String worldName;
	
	public CreateWorldRunnable(Player p, String worldName) {
		this.p = p;
		this.worldName = worldName;
	}
	
	@Override
	public void run() {
		try {
			GameUtils.createWorld(p, worldName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}