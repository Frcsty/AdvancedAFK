package me.frosty.advancedafk.storage;

import me.frosty.advancedafk.AdvancedAFKPlugin;

public class PlayerData
{

	private AdvancedAFKPlugin plugin;
	private StorageUtil       utility;

	public PlayerData(final AdvancedAFKPlugin plugin)
	{
		this.plugin = plugin;
		this.utility = plugin.getStorageUtility();
	}

	public void reloadConfigurationData()
	{
		// Kick Settings
		utility.useCustomAction = plugin.getConfig().getBoolean("customAction.Enabled");
		utility.customAction = plugin.getConfig().getString("customAction.Action");
		utility.customPermission = plugin.getConfig().getString("customAction.Permission");

		// Analysis Settings
		utility.rangeX = plugin.getConfig().getInt("analysisSettings.CheckRadius.x");
		utility.rangeY = plugin.getConfig().getInt("analysisSettings.CheckRadius.y");
		utility.rangeZ = plugin.getConfig().getInt("analysisSettings.CheckRadius.z");
		utility.maximumPoints = plugin.getConfig().getInt("analysisSettings.Points");
		utility.kickTime = plugin.getConfig().getInt("analysisSettings.Time");
		utility.checkTimeInterval = plugin.getConfig().getInt("analysisSettings.LocationTime");
		utility.allowedOnlinePlayers = plugin.getConfig().getInt("analysisSettings.InactivityKickAmount");
		utility.warnTimeAmounts = plugin.getConfig().getIntegerList("analysisSettings.WarnTimer");
		utility.pauseTime = plugin.getConfig().getInt("analysisSettings.PauseTimer");

		// Messages
		utility.noPermissionMessage = plugin.getConfig().getString("messages.NoPermissionMessage");
		utility.unknownCommandMessage = plugin.getConfig().getString("messages.UnknownCommandMessage");
		utility.commandUsageMessage = plugin.getConfig().getString("messages.CommandUsageMessage");
		utility.playerKickMessage = plugin.getConfig().getString("messages.KickMessage");
		utility.playerKickBroadcastMessage = plugin.getConfig().getString("messages.BroadcastKickMessage");
		utility.vanishMessage = plugin.getConfig().getString("messages.VanishMessage");
		utility.unvanishMessage = plugin.getConfig().getString("messages.UnVanishMessage");
		utility.listPlayersMessage = plugin.getConfig().getString("messages.AfkPlayerList");
		utility.reloadStartMessage = plugin.getConfig().getString("messages.ReloadStartMessage");
		utility.reloadCompleteMessage = plugin.getConfig().getString("messages.ReloadCompleteMessage");
		utility.cleanupStartMessage = plugin.getConfig().getString("messages.CleanUpStartMessage");
		utility.cleanupRemovedMessage = plugin.getConfig().getString("messages.CleanUpRemovedMessage");
		utility.cleanupEndMessage = plugin.getConfig().getString("messages.CleanUpCompleteMessage");
		utility.noPlayerOnline = plugin.getConfig().getString("messages.InvalidCheckedPlayerMessage");
		utility.debugMessage = plugin.getConfig().getStringList("messages.DebugMessage");
		utility.helpMessage = plugin.getConfig().getStringList("messages.HelpMessage");

		// Title Message
		utility.warningFirstMessage = plugin.getConfig().getString("title.TitleMessage");
		utility.warningSecondMessage = plugin.getConfig().getString("title.SubtitleMessage");
		utility.titleFadeIn = plugin.getConfig().getInt("title.FadeIn");
		utility.titleFadeOut = plugin.getConfig().getInt("title.FadeOut");
		utility.titleStay = plugin.getConfig().getInt("title.Stay");


	}

}
