package me.frosty.advancedafk.commands;

import com.codeitforyou.lib.api.command.Command;
import com.codeitforyou.lib.api.general.StringUtil;
import me.frosty.advancedafk.AdvancedAFKPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class CleanupCommand
{

	@Command(aliases = {"cleanup"}, usage = "cleanup")
	public static void execute(final Player player, final AdvancedAFKPlugin plugin, final String[] args)
	{

		if (player.hasPermission("advancedafk.cleanup") || player.hasPermission("advancedafk.*"))
		{
			player.sendMessage(StringUtil.translate(plugin.getStorageUtility().cleanupStartMessage));
			int i = 0;
			for (Player target : Bukkit.getOnlinePlayers())
			{

				int playerSession = plugin.getStorageUtility().playerSessionDuration.get(target);
				if (playerSession >= plugin.getStorageUtility().kickTime)
				{
					// If the user has the permission they will be skipped from the purge
					if (!target.hasPermission("advancedafk.ignore") || !target.hasPermission("advancedafk.*"))
					{
						player.sendMessage(StringUtil.translate(plugin.getStorageUtility().cleanupRemovedMessage.replace("<player>", target.getName())));
						Location location = target.getLocation();

						if (location.getWorld() != null)
						{
							// Play effects and sounds to the player
							location.getWorld().playSound(location, Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
							location.getWorld().playEffect(location, Effect.ENDER_SIGNAL, 300);
						}

						// Kick the player and send him the kick message
						target.kickPlayer(StringUtil.translate(plugin.getStorageUtility().playerKickMessage));

						// Add 1 to the total kicked players count
						i++;
					}
				}
			}

			// Return the player kick amount
			player.sendMessage(StringUtil.translate(plugin.getStorageUtility().cleanupEndMessage.replace("<number>", String.valueOf(i))));
		}
	}

}
