package com.gmail.jamesbehan198.servermanager.spawnsystem;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.jamesbehan198.servermanager.ServerManager;

public class Spawn implements CommandExecutor
{
	
	ServerManager main;
	
	public Spawn(ServerManager main)
	{
		this.main = main;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(main.spawnSystem)
		{
			if(!(sender instanceof Player))
			{
				sender.sendMessage(main.colors("&cYou can only set the spawn in game!"));
				return true;
			}
			
			Player p = (Player) sender;	
			
			if(p.hasPermission("sm.spawnsys.spawn"))
			{
				if(cmd.getName().equalsIgnoreCase("spawn"))
				{
					p.sendMessage(main.colors("&7Teleporting . . ."));
					
					int x = main.getConfig().getInt("spawnSystem.x");
					int y = main.getConfig().getInt("spawnSystem.y");
					int z = main.getConfig().getInt("spawnSystem.z");
					int yaw = main.getConfig().getInt("spawnSystem.yaw");
					int pitch = main.getConfig().getInt("spawnSystem.pitch");
					
					String w = main.getConfig().getString("spawnSystem.world");
					
					if(x != 0 && y != 0 && z != 0 && yaw != 0 && pitch != 0)
					{
						p.teleport(new Location(Bukkit.getWorld(w), x, y, z, yaw, pitch));
						p.sendMessage(main.colors("&aTeleported to &bSpawn"));
						return true;
					}
					else
					{
						p.sendMessage(main.colors("&cSpawn not set!"));
						return true;
					}
				}
			}
		}
		
		return true;
	}
	
}
