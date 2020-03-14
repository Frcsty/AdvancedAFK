package me.frosty.advancedafk.commands;

import com.codeitforyou.lib.api.command.Command;
import com.codeitforyou.lib.api.general.StringUtil;
import me.frosty.advancedafk.AdvancedAFKPlugin;
import org.bukkit.entity.Player;

public class CurrentSessionCommand
{

	@Command(aliases = {"session"}, usage = "session")
	public static void execute(final Player player, final AdvancedAFKPlugin plugin, final String[] args)
	{
		player.sendMessage(StringUtil.translate("&8[&dAFK&8] &7Current Session Duration&8: &f" + plugin.getStorageUtility().sessionTime + "s"));

	}

}
