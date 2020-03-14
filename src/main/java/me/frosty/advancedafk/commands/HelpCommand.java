package me.frosty.advancedafk.commands;

import com.codeitforyou.lib.api.command.Command;
import com.codeitforyou.lib.api.general.StringUtil;
import me.frosty.advancedafk.AdvancedAFKPlugin;
import org.bukkit.entity.Player;

public class HelpCommand
{

	@Command(aliases = {"help"}, usage = "help")
	public static void execute(final Player player, final AdvancedAFKPlugin plugin, final String[] args)
	{
		if (player.hasPermission("advancedafk.help") || player.hasPermission("advancedafk.*"))
		{
			for (String message : plugin.getStorageUtility().helpMessage)
			{
				player.sendMessage(StringUtil.translate(message));
			}

		}
	}

}
