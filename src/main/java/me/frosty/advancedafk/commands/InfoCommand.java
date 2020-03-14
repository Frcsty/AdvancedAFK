package me.frosty.advancedafk.commands;

import com.codeitforyou.lib.api.command.Command;
import com.codeitforyou.lib.api.general.StringUtil;
import me.frosty.advancedafk.AdvancedAFKPlugin;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class InfoCommand
{
	@Command(aliases = {"info"}, usage = "info")
	public static void execute(final Player player, final AdvancedAFKPlugin plugin, final String[] args)
	{
		String authors = String.valueOf(plugin.getDescription().getAuthors());
		String version = plugin.getDescription().getVersion();
		String serverVersion = plugin.getServer().getBukkitVersion();

		authors = authors.replace("[", "");
		authors = authors.replace("]", "");

		List<String> messages = new ArrayList<>();

		messages.add("&5Advanced&fAFK &8» &f{version}".replace("{version}", version));
		messages.add("");
		messages.add("&fStatus");
		messages.add(" &8» &7Version&8: &f" + version);
		messages.add(" &8» &7Authors&8: &f" + authors);
		messages.add(" &8» &7Server Version&8: &f" + serverVersion);
		messages.add("");

		for (String message : messages)
		{
			player.sendMessage(StringUtil.translate(message));
		}

	}

}
