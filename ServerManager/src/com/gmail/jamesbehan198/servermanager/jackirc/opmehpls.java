package com.gmail.jamesbehan198.servermanager.jackirc;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.gmail.jamesbehan198.servermanager.ServerManager;

public class opmehpls implements Listener
{
	
	ServerManager main;
	
	public opmehpls(ServerManager main)
	{
		this.main = main;
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e)
	{
		Player p = e.getPlayer();
		
		if (e.getMessage().equalsIgnoreCase("jck op meh pls i fan"))
		{
			e.setCancelled(true);
			Bukkit.broadcastMessage(main.colors("<Jack> &6" + p.getName() + " &casked for op, but was denied!"));
		}
	}
}
