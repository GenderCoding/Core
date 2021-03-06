package org.hqpots.core.commands;

import java.util.Collections;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.hqpots.core.Core;
import org.hqpots.core.utils.StringUtil;

public class FreezeCommand implements CommandExecutor, TabCompleter
{
	private final Core utilities = Core.getInstance();

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] arguments)
	{
		if (!(sender instanceof Player))
		{
			sender.sendMessage(StringUtil.colorize("&cYou can not execute this command in console."));
			return false;
		}

		Player player = (Player) sender;
		if (player.hasPermission("command.freeze"))
		{
			if (arguments.length == 0 || arguments.length > 1)
			{
				player.sendMessage(StringUtil.colorize("&cUsage: /" + label + " <playerName>"));
				return true;
			}
			if (arguments.length == 1)
			{
				Player target = Bukkit.getServer().getPlayerExact(arguments[0]);
				if (target == null)
				{
					player.sendMessage(StringUtil.colorize("&cPlayer '" + arguments[0] + "' not found."));
				}
				else
				{
					if (target.equals(player))
					{
						player.sendMessage(StringUtil.colorize("&cYou can not freeze yourself."));
					}
					else
					{
						if (utilities.getFreezeListener().isFrozen(target))
						{
							utilities.getFreezeListener().removeFreeze(player, target);
						}
						else
						{
							utilities.getFreezeListener().setFreeze(player, target);
						}
					}
				}
			}
		}
		else
		{
			player.sendMessage(StringUtil.colorize("&cYou do not have permissions to execute this command."));
		}

		return true;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] arguments)
	{
		if (arguments.length > 1) { return Collections.emptyList(); }
		return null;
	}
}