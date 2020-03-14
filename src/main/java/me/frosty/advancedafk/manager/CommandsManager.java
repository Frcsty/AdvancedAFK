package me.frosty.advancedafk.manager;

import com.codeitforyou.lib.api.command.CommandManager;
import me.frosty.advancedafk.AdvancedAFKPlugin;
import me.frosty.advancedafk.commands.CleanupCommand;
import me.frosty.advancedafk.commands.CurrentSessionCommand;
import me.frosty.advancedafk.commands.DebugCommand;
import me.frosty.advancedafk.commands.HelpCommand;
import me.frosty.advancedafk.commands.InfoCommand;
import me.frosty.advancedafk.commands.ListPlayersCommand;
import me.frosty.advancedafk.commands.ReloadCommand;

import java.util.Arrays;

public class CommandsManager
{

	// Registering of CommandManager which is providing through CIFYLib
	public void registerListCommand(final AdvancedAFKPlugin plugin)
	{
		CommandManager afkCommandManager = new CommandManager(Arrays.asList(
				ListPlayersCommand.class, ReloadCommand.class, DebugCommand.class, CleanupCommand.class
				, HelpCommand.class, CurrentSessionCommand.class
		), "advancedafk", plugin
		);

		// Registering of the commands attributes
		registerCommandAttributes(plugin, afkCommandManager);
	}

	private void registerCommandAttributes(final AdvancedAFKPlugin plugin, final CommandManager commandManager)
	{
		// Main plugin command which is executed upon command with no arguments
		commandManager.setMainCommand(InfoCommand.class);

		// Setting basic messages which are handled through the lib
		commandManager.getLocale().setNoPermission(plugin.getStorageUtility().noPermissionMessage);
		commandManager.getLocale().setUnknownCommand(plugin.getStorageUtility().unknownCommandMessage);
		commandManager.getLocale().setUsage(plugin.getStorageUtility().commandUsageMessage);
		commandManager.getLocale().setPlayerOnly("§4This command can not be executed through console!");

		// Registering the command and the arguments
		commandManager.register();
	}

}
