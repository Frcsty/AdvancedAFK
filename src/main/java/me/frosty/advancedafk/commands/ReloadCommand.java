package me.frosty.advancedafk.commands;

import com.codeitforyou.lib.api.command.Command;
import com.codeitforyou.lib.api.general.StringUtil;
import me.frosty.advancedafk.AdvancedAFKPlugin;
import me.frosty.advancedafk.storage.PlayerData;
import me.frosty.advancedafk.storage.StorageUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReloadCommand
{

	@Command(aliases = {"reload"}, usage = "reload")
	public static void execute(final CommandSender sender, final AdvancedAFKPlugin plugin, final String[] args)
	{
		PlayerData playerData = plugin.getPlayerData();
		StorageUtil utility = plugin.getStorageUtility();

		if (!(sender instanceof Player))
		{

			sender.sendMessage(StringUtil.translate(utility.reloadStartMessage));
			plugin.reloadConfig();
			playerData.reloadConfigurationData();
			sender.sendMessage(StringUtil.translate(utility.reloadCompleteMessage));
			return;
		}

		Player player = (Player) sender;

		if (player.hasPermission("advancedafk.reload") || sender.hasPermission("advancedafk.*"))
		{
			player.sendMessage(StringUtil.translate(utility.reloadStartMessage));
			plugin.reloadConfig();
			playerData.reloadConfigurationData();
			player.sendMessage(StringUtil.translate(utility.reloadCompleteMessage));
		}
	}

}
