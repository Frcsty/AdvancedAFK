package me.frosty.advancedafk.storage;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class StorageUtil
{

	public HashMap<Player, Location>           playerLocationMap;
	public HashMap<Player, Integer>            playerInactivityDuration;
	public HashMap<Player, Integer>            playerSessionDuration;
	public HashMap<Player, Boolean>            playerIsVanish;
	public HashMap<UUID, PermissionAttachment> permissionData = new HashMap<>();
	int inactivityDuration;
	public String noPlayerOnline;
	public String playerKickMessage;
	public String playerKickBroadcastMessage;
	public String noPermissionMessage;
	public String warningFirstMessage;
	public String warningSecondMessage;
	public String vanishMessage;
	public String unvanishMessage;
	public String reloadStartMessage;
	public String reloadCompleteMessage;
	public String cleanupStartMessage;
	public String cleanupRemovedMessage;
	public String cleanupEndMessage;
	public String listPlayersMessage;
	public String unknownCommandMessage;
	public String commandUsageMessage;
	public String customPermission;
	public int    kickTime;
	public int    allowedOnlinePlayers;
	int pauseTime;
	int rangeX;
	int rangeY;
	int rangeZ;
	int maximumPoints;
	public int           checkTimeInterval;
	public List<Integer> warnTimeAmounts;
	public List<String>  debugMessage;
	public List<String>  helpMessage;
	public int           sessionTime;
	public int           locationTime;
	public int           titleFadeIn;
	public int           titleFadeOut;
	public int           titleStay;
	public String        customAction;
	public Boolean       useCustomAction;

	public StorageUtil()
	{
		this.playerLocationMap = new HashMap<>();
		this.playerInactivityDuration = new HashMap<>();
		this.playerSessionDuration = new HashMap<>();
		this.playerIsVanish = new HashMap<>();
	}

}
