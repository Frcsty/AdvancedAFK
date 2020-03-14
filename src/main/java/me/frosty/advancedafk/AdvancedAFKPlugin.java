package me.frosty.advancedafk;

import me.frosty.advancedafk.listeners.PlayerListener;
import me.frosty.advancedafk.manager.CommandsManager;
import me.frosty.advancedafk.storage.PlayerData;
import me.frosty.advancedafk.storage.StorageUtil;
import me.frosty.advancedafk.util.PermissionsUtil;
import me.frosty.advancedafk.util.TaskUtil;
import me.frosty.advancedafk.util.VanishUtil;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class AdvancedAFKPlugin extends JavaPlugin
{

	private final CommandsManager commandsManager = new CommandsManager();
	private final StorageUtil     utility         = new StorageUtil();
	private final PlayerData      playerData      = new PlayerData(this);
	private final VanishUtil      vanishUtil      = new VanishUtil(this);
	private final TaskUtil        taskUtil        = new TaskUtil(this);
	private final Logger          logger          = getLogger();
	private final PlayerListener  listener        = new PlayerListener(this);
	private final PermissionsUtil permissionsUtil = new PermissionsUtil(this);

	@Override
	public void onEnable()
	{
		// Save default config
		saveDefaultConfig();

		// Initialize thread and checks
		taskUtil.executeStartingTask();

		// Reloaded PlayerData settings
		playerData.reloadConfigurationData();

		// Register PlayerListener (PlayerJoinEvent, PlayerQuitEvent)
		getServer().getPluginManager().registerEvents(listener, this);

		// Register Command and sub classes (CleanupCommand, DebugCommand, InfoCommand, ListPlayerCommand, ReloadCommand
		commandsManager.registerListCommand(this);

		// Console startup message
		logger.info("");
		logger.info("Initializing AdvancedAFK..");
		logger.info("");
		logger.info("Status:");
		logger.info(" » Registering PlayerListener");
		logger.info(" » Starting Check Timer");
		logger.info(" » Creating ThreadAnalysis Threads");
		logger.info(" » Registering Commands");
		logger.info(" » Command Alias: 'advancedafk'");
		logger.info(" » Authors: Frosty, SebbaIndustries");
		logger.info("");
		logger.info("AdvancedAFK Initialized.");
	}

	@Override
	public void onDisable()
	{
		saveConfig();
	}

	public StorageUtil getStorageUtility()
	{
		return utility;
	}

	public VanishUtil getVanishUtil()
	{
		return vanishUtil;
	}

	public PlayerData getPlayerData()
	{
		return playerData;
	}

	public PermissionsUtil getPermissionsUtil()
	{
		return permissionsUtil;
	}

}
