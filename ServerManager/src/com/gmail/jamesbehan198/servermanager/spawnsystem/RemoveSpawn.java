package com.gmail.jamesbehan198.servermanager.spawnsystem;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.gmail.jamesbehan198.servermanager.ServerManager;

public class RemoveSpawn implements CommandExecutor
{
	
	ServerManager main;
	
	public RemoveSpawn(ServerManager main)
	{
		this.main = main;
	}
	
	public void remSpawn()
	{
		main.getConfig().set("spawnSystem.x", null);
		main.getConfig().set("spawnSystem.y", null);
		main.getConfig().set("spawnSystem.z", null);
		
		main.getConfig().set("spawnSystem.pitch", null);
		main.getConfig().set("spawnSystem.yaw", null);
		
		main.getConfig().set("spawnSystem.world", null);
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(main.spawnSystem)
		{
			if(sender.hasPermission("sm.spawnsys.remspawn"))
			{
				if(cmd.getName().equalsIgnoreCase("remspawn"))
				{
					sender.sendMessage(main.colors("&cRemoving spawnpoint . . ."));
					remSpawn();
					main.saveConfig();
					main.reloadConfig();
					sender.sendMessage(main.colors("&cSpawn removed!"));
					return true;
				}
			}
		}
		
		return true;
	}
	
}
