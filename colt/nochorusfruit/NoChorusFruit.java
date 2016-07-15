package colt.nochorusfruit;

import org.bukkit.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.common.base.Strings;

public class NoChorusFruit extends JavaPlugin implements Listener {
	private	String blockedMessage;

	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		saveDefaultConfig();
		blockedMessage = ChatColor.translateAlternateColorCodes('&',
				getConfig().getString("blocked-message"));
	}

	@EventHandler
	public void playerTeleportEvent(PlayerTeleportEvent event) {
		if (event.getCause() == PlayerTeleportEvent.TeleportCause.CHORUS_FRUIT) {
			if (!event.getPlayer().hasPermission("ncf.bypass")) {
				event.setCancelled(true);
				if(!Strings.isNullOrEmpty(blockedMessage)) 
					event.getPlayer().sendMessage(blockedMessage);
			}
		}
	}
}
