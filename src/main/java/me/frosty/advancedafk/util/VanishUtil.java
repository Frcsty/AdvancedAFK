package me.frosty.advancedafk.util;

import com.codeitforyou.lib.api.general.StringUtil;
import me.frosty.advancedafk.AdvancedAFKPlugin;
import me.frosty.advancedafk.storage.StorageUtil;
import org.bukkit.entity.Player;

public class VanishUtil
{

	private AdvancedAFKPlugin plugin;
	private StorageUtil       utility;

	public VanishUtil(final AdvancedAFKPlugin plugin)
	{
		this.plugin = plugin;
		this.utility = plugin.getStorageUtility();
	}

	// Enable vanish for specified user
	void enableVanish(Player player)
	{
		if (player != null)
		{
			for (Player otherPlayers : plugin.getServer().getOnlinePlayers())
			{
				otherPlayers.hidePlayer(plugin, player);
			}
			utility.playerIsVanish.put(player, true);
			player.sendMessage(StringUtil.translate(utility.vanishMessage));
		}
	}

	// Disable vanish for specified user
	void disableVanish(Player player)
	{
		if (player != null)
		{
			for (Player otherPlayers : plugin.getServer().getOnlinePlayers())
			{
				otherPlayers.showPlayer(plugin, player);
			}
			utility.playerIsVanish.put(player, false);
			player.sendMessage(StringUtil.translate(utility.unvanishMessage));
		}
	}

}
