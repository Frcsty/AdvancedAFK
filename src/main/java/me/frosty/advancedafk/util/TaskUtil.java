package me.frosty.advancedafk.util;

import com.codeitforyou.lib.api.general.StringUtil;
import me.frosty.advancedafk.AdvancedAFKPlugin;
import me.frosty.advancedafk.storage.StorageUtil;
import me.frosty.advancedafk.storage.ThreadAnalysis;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class TaskUtil
{

	private AdvancedAFKPlugin plugin;
	private StorageUtil       utility;
	private VanishUtil        vanishUtil;
	private PermissionsUtil   permissionsUtil;

	public TaskUtil(final AdvancedAFKPlugin plugin)
	{
		this.plugin = plugin;
		this.utility = plugin.getStorageUtility();
		this.vanishUtil = plugin.getVanishUtil();
		this.permissionsUtil = plugin.getPermissionsUtil();
	}

	// Start check task
	public void executeStartingTask()
	{
		new BukkitRunnable()
		{
			public void run()
			{
				utility.sessionTime++;
				utility.locationTime++;

				for (Player player : Bukkit.getOnlinePlayers())
				{
					if (utility.locationTime == utility.checkTimeInterval)
					{
						utility.playerLocationMap.put(player, player.getLocation());
					}
					if (utility.locationTime >= (utility.checkTimeInterval + 1))
					{
						utility.checkTimeInterval = 0;
					}
					if (utility.sessionTime >= utility.playerInactivityDuration.get(player))
					{
						int playerSession = utility.playerSessionDuration.get(player);

						// Data Analysis Thread
						ThreadAnalysis ThreadAnalysis1 = new ThreadAnalysis(plugin, "AdvancedThread-1");
						if (ThreadAnalysis1.isAlive())
						{
							// Initialize a new thread if one already exists
							ThreadAnalysis ThreadAnalysis2 = new ThreadAnalysis(plugin, "AdvancedThread-2");
							ThreadAnalysis2.player = player;
							ThreadAnalysis2.startAnalysis();
						}
						else
						{
							// Initialize First Thread
							ThreadAnalysis1.player = player;
							ThreadAnalysis1.startAnalysis();
						}

						// Kick and Warn user
						if (Bukkit.getOnlinePlayers().size() >= utility.allowedOnlinePlayers)
						{

							if (!player.hasPermission("advancedafk.ignore") || !player.hasPermission("advancedafk.*"))
							{
								// Warn the user if they are about the get kicked
								for (Integer warnTimes : utility.warnTimeAmounts)
								{
									if (warnTimes == playerSession)
									{
										player.sendTitle(StringUtil.translate(utility.warningFirstMessage), StringUtil.translate(utility.warningSecondMessage.replace("<time>", String.valueOf(utility.kickTime - warnTimes))), utility.titleFadeIn, utility.titleStay, utility.titleFadeOut);
									}
								}
								
								// Kick user if they have surpassed the allowed afk time
								if (playerSession >= utility.kickTime)
								{

									// Broadcast the kick message to those who have the permission
									Bukkit.broadcast(StringUtil.translate(utility.playerKickBroadcastMessage.replace("<player>", player.getName())), "advancedafk.broadcast");
									Location location = player.getLocation();
									Sound sound = Sound.ENTITY_ENDERMAN_TELEPORT;
									Effect effect = Effect.ENDER_SIGNAL;

									// Play sound and effect on the players kick location
									if (location.getWorld() != null)
									{
										location.getWorld().playSound(location, sound, 1, 1);
										location.getWorld().playEffect(location, effect, 300);
									}

									// Send the kicked user a message and kick them depending on the settings
									if (utility.useCustomAction)
									{
										if (utility.customPermission != null)
										{
											permissionsUtil.setPermission(player, utility.customPermission);
											Bukkit.dispatchCommand(player, utility.customAction.replace("<player>", player.getName()));
											permissionsUtil.unsetPermission(player, utility.customPermission);
										}
										else
										{
											System.out.println("Custom Permission is null");
										}
									}
									else
									{
										player.kickPlayer(StringUtil.translate(utility.playerKickMessage));
									}

								}

							}
						}

						// Vanish user if they have the permission upon certain time
						if (player.hasPermission("advancedafk.ignore") || player.hasPermission("advancedafk.*"))
						{
							if (playerSession >= utility.kickTime)
							{
								// If a user is not in vanish, set them in vanish
								if (!utility.playerIsVanish.get(player))
								{
									vanishUtil.enableVanish(player);
								}
							}
							if (playerSession < utility.kickTime)
							{

								// If a user in vanish, set the out of vanish
								if (utility.playerIsVanish.get(player))
								{
									vanishUtil.disableVanish(player);
								}
							}
						}
					}
				}

			}
		}.runTaskTimerAsynchronously(plugin, 0L, 20L);
	}

}
