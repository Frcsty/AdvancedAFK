package me.frosty.advancedafk.storage;

import me.frosty.advancedafk.AdvancedAFKPlugin;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class ThreadAnalysis extends Thread
{

	private StorageUtil utility;
	private Thread      thread = null;
	private String      threadName;
	public  Player      player;

	public ThreadAnalysis(final AdvancedAFKPlugin plugin, final String name)
	{
		this.utility = plugin.getStorageUtility();
		this.threadName = name;
	}

	// Execute Threads Task
	public void run()
	{
		// Analyzing Players Location
		Location playerLocation = player.getLocation();
		Location targetLocation = utility.playerLocationMap.get(player);

		int amountX = Math.abs(Math.abs(playerLocation.getBlockX())) - Math.abs(targetLocation.getBlockX());
		int amountY = Math.abs(Math.abs(playerLocation.getBlockY())) - Math.abs(targetLocation.getBlockY());
		int amountZ = Math.abs(Math.abs(playerLocation.getBlockZ())) - Math.abs(targetLocation.getBlockZ());
		float pitchAmount = Math.abs(Math.abs(playerLocation.getPitch())) - Math.abs(targetLocation.getPitch());
		float yawAmount = Math.abs(Math.abs(playerLocation.getYaw())) - Math.abs(targetLocation.getYaw());

		// Appropriate Points Check
		int playerPoints = 0;

		if (amountX <= utility.rangeX)
		{
			playerPoints++;
		}
		if (amountY <= utility.rangeY)
		{
			playerPoints++;
		}
		if (amountZ <= utility.rangeZ)
		{
			playerPoints++;
		}
		if (pitchAmount == 0)
		{
			playerPoints++;
		}
		if (yawAmount == 0)
		{
			playerPoints++;
		}

		// Check Results
		if (playerPoints >= utility.maximumPoints)
		{
			// User has failed the check
			utility.playerSessionDuration.put(player, utility.playerSessionDuration.get(player) + 1);
		}
		else
		{
			// User has passed the check
			utility.playerInactivityDuration.put(player, (utility.inactivityDuration + utility.pauseTime));
			utility.playerSessionDuration.put(player, 0);
		}
	}

	// Start Analysis Thread
	public void startAnalysis()
	{
		// Create a new thread
		if (thread == null)
		{
			thread = new Thread(this, threadName);
			thread.start();
		}
	}

}
