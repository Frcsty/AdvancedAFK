package me.frosty.advancedafk.commands;

import com.codeitforyou.lib.api.command.Command;
import com.codeitforyou.lib.api.general.StringUtil;
import me.frosty.advancedafk.AdvancedAFKPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class DebugCommand
{

	@Command(aliases = {"debug"}, usage = "debug <player>", requiredArgs = 1)
	public static void execute(final Player player, final AdvancedAFKPlugin plugin, final String[] args)
	{
		if (player.hasPermission("advancedafk.debug") || player.hasPermission("advancedafk.*"))
		{
			Player targetPlayer = Bukkit.getPlayerExact(args[0]);

			if (targetPlayer != null)
			{
				for (String message : plugin.getStorageUtility().debugMessage)
				{
					message = message.replace("<player>", targetPlayer.getName());
					message = message.replace("<status>", getPlayerStatus(plugin, targetPlayer));
					message = message.replace("<time>", String.valueOf(plugin.getStorageUtility().playerSessionDuration.get(targetPlayer)));
					message = message.replace("<onlinePlayers>", String.valueOf(Bukkit.getOnlinePlayers().size()));
					message = message.replace("<maxPlayers>", String.valueOf(plugin.getStorageUtility().allowedOnlinePlayers));

					player.sendMessage(StringUtil.translate(message));
				}
			}
			else
			{
				player.sendMessage(StringUtil.translate(plugin.getStorageUtility().noPlayerOnline.replace("<player>", args[0])));
			}
		}
	}

	private static String getPlayerStatus(final AdvancedAFKPlugin plugin, final Player targetPlayer)
	{
		String status = "Playing";
		if (plugin.getStorageUtility().kickTime <= plugin.getStorageUtility().playerSessionDuration.get(targetPlayer))
		{
			status = "Afk";
		}
		return status;
	}

}
