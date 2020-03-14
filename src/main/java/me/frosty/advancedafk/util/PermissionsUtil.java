package me.frosty.advancedafk.util;

import me.frosty.advancedafk.AdvancedAFKPlugin;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

public class PermissionsUtil
{
	private AdvancedAFKPlugin plugin;

	public PermissionsUtil(final AdvancedAFKPlugin plugin)
	{
		this.plugin = plugin;
	}

	void setPermission(Player player, String permission)
	{
		PermissionAttachment permissionAttachment = player.addAttachment(plugin);
		plugin.getStorageUtility().permissionData.put(player.getUniqueId(), permissionAttachment);
		PermissionAttachment permissionAttachment1 = plugin.getStorageUtility().permissionData.get(player.getUniqueId());
		permissionAttachment1.setPermission(permission, true);
	}

	void unsetPermission(Player player, String permission)
	{
		plugin.getStorageUtility().permissionData.get(player.getUniqueId()).unsetPermission(permission);
	}

}
