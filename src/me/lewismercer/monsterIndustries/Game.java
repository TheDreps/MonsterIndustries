package me.lewismercer.monsterIndustries;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.World;
import me.lewismercer.monsterIndustries.utils.CurrentGames;
import me.lewismercer.monsterIndustries.utils.GameUtils;

public class Game {

	public int gameID;
	public World world;

	public Game() {
		gameID = getID();
		world = GameUtils.createWorld("MIGAME" + gameID);

	}

	/*
	 * Finds an available ID for the game
	 */
	private int getID() {
		int newID = -1; // just as a default value, this shouldn't be used.
//		if (CurrentGames.map.isEmpty()) { // Checks if the games map is empty to avoid passing in the default -1 value
//			newID = 1;
//		}
		for (int i = 1; i < CurrentGames.map.size() + 2; i++) { // finds the next avalible ID
			if (!(CurrentGames.map.containsKey(i))) {
				newID = i;
				break;
			}
		}
		if (newID == -1) { // checks to see if the default value is still there
			Bukkit.getLogger().log(Level.SEVERE, "Unable to find an avalible game ID!");
		}
		return newID;
	}
}
