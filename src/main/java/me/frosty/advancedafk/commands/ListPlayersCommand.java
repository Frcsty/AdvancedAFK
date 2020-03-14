package me.frosty.advancedafk.commands;

import com.codeitforyou.lib.api.command.Command;
import com.codeitforyou.lib.api.general.StringUtil;
import me.frosty.advancedafk.AdvancedAFKPlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ListPlayersCommand
{

	@Command(aliases = {"list"}, usage = "list")
	public static void execute(final CommandSender sender, final AdvancedAFKPlugin plugin, final String[] args)
	{
		if (sender.hasPermission("advancedafk.list") || sender.hasPermission("advancedafk.*"))
		{
			StringBuilder text = new StringBuilder();
			text.append(plugin.getStorageUtility().listPlayersMessage);
			int i = 0;
			for (Player player : Bukkit.getOnlinePlayers())
			{
				int playerSession = plugin.getStorageUtility().playerSessionDuration.get(player);
				if (playerSession >= plugin.getStorageUtility().kickTime)
				{
					text.append(text).append(player.getName()).append("&8,&r ");
					i++;
				}
			}
			String afkPlayers = String.valueOf(text).replace("<players>", String.valueOf(i));
			sender.sendMessage(StringUtil.translate(afkPlayers));
		}
		else
		{
			sender.sendMessage(StringUtil.translate(plugin.getStorageUtility().noPermissionMessage));
		}
	}

}
