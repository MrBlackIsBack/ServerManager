package com.gmail.jamesbehan198.servermanager.jackirc;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.gmail.jamesbehan198.servermanager.ServerManager;

public class Kick implements Listener
{
	ServerManager main;
	
	public Kick(ServerManager main)
	{
		this.main = main;
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e)
	{
		if (main.enableJack)
		{
			if (e.getPlayer().hasPermission("sm.jack.kick"))
			{
				for (Player ps : Bukkit.getOnlinePlayers())
				{
					if (e.getMessage().contains("Jack, could you kick " + ps.getName() + " please") 
							|| e.getMessage().contains("Jack could you kick " + ps.getName()) 
							|| e.getMessage().contains("Jack kick " + ps.getName()))
					{
						e.setCancelled(true);
						e.getPlayer().sendMessage(main.colors("<Jack> &cKicking " + ps.getName()));
						ps.kickPlayer("<Jack> You have been kicked!");
					}
				}
			}
		}
	}
	
}