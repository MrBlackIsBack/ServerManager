package com.gmail.jamesbehan198.servermanager.jackirc;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.gmail.jamesbehan198.servermanager.ServerManager;

public class Kill implements Listener
{
	ServerManager main;
	JackMethods methods;
	
	public Kill(ServerManager main, JackMethods methods)
	{
		this.main = main;
		this.methods = methods;
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e)
	{
		Player p = e.getPlayer();
		
		for (Player ps : Bukkit.getServer().getOnlinePlayers())
		{
			if (e.getMessage().equalsIgnoreCase("Jack kill " + ps.getName()))
			{
				e.setCancelled(true);
				p.sendMessage(main.colors("<Jack> &bkilling " + ps.getName()));
				ps.setHealth(0);
				ps.sendMessage(main.colors("<Jack> &bYou have been killed."));
			}
		}
	}
	
}
