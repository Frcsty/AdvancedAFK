package me.frosty.advancedafk.listeners;

import me.frosty.advancedafk.AdvancedAFKPlugin;
import me.frosty.advancedafk.storage.StorageUtil;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener
{

	private StorageUtil       utility;

	public PlayerListener(final AdvancedAFKPlugin plugin)
	{
		this.utility = plugin.getStorageUtility();
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		// Get the player
		Player player = event.getPlayer();

		// Get the player's location
		Location location = player.getLocation();

		// Store the player and their location in a HashMap
		utility.playerLocationMap.put(player, location);

		// Create a new session entry, and inactivity duration
		utility.playerSessionDuration.put(player, 0);
		utility.playerInactivityDuration.put(player, 0);

		// Set the player's vanish status
		utility.playerIsVanish.put(player, false);
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event)
	{
		// Get the player
		Player player = event.getPlayer();

		// Remove the player and their location from the HashMap
		utility.playerLocationMap.remove(player);

		// Remove the session entry, and inactivity duration
		utility.playerSessionDuration.remove(player);
		utility.playerInactivityDuration.remove(player);

		// Remove the player's vanish status
		utility.playerIsVanish.remove(player);
	}
}
