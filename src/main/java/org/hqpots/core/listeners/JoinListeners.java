package org.hqpots.core.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.hqpots.core.utils.StringUtil;

public class JoinListeners implements Listener
{

	@EventHandler
	public void onJoinRank(PlayerLoginEvent e)
	{
		Player p = e.getPlayer();
		if ((p.hasPermission("command.allow.donator")))
		{
			if (Bukkit.getOnlinePlayers().size() >= Bukkit.getMaxPlayers())
			{
				e.allow();
			}
		}
		else if ((!p.hasPermission("command.alertssee")) && (Bukkit.getOnlinePlayers().size() >= Bukkit.getMaxPlayers()))
		{
			e.disallow(PlayerLoginEvent.Result.KICK_FULL, StringUtil.colorize("&cThe server is currently full, buy a reserved slot @ &lStore.hqpots.org"));
			for (Player staff : Bukkit.getOnlinePlayers())
			{
				if (staff.hasPermission("command.alertssee"))
				{
					staff.sendMessage(StringUtil.colorize("&cYou should probably increase the slots as there are people trying to log in, but the server is full"));
				}
			}
		}
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e)
	{
		Player player = e.getPlayer();
		player.sendMessage(StringUtil.colorize("&7&m-------------------------------------------"));
		player.sendMessage(StringUtil.colorize("&6* &a&lForums: &areddit.com/r/HQPots"));
		player.sendMessage(StringUtil.colorize("&6* &a&lTS3: &ats.hqpots.org"));
		player.sendMessage(StringUtil.colorize("&6* &a&lStore: &astore.hqpots.org"));
		player.sendMessage(StringUtil.colorize("&7&m-------------------------------------------"));
	}
}