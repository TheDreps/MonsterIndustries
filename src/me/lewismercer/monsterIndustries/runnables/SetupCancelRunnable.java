package me.lewismercer.monsterIndustries.runnables;

import org.bukkit.entity.Player;

import me.lewismercer.monsterIndustries.commands.SetupCommands;

public class SetupCancelRunnable implements Runnable{
	
	private Player p;
	
	public SetupCancelRunnable(Player p) {
		this.p = p;
	}

	@Override
	public void run() {
		SetupCommands.cancel(p);
	}

}
