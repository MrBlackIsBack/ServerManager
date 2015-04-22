package com.gmail.jamesbehan198.servermanager.jackirc;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.gmail.jamesbehan198.servermanager.ServerManager;

public class Help implements Listener
{
	ServerManager main;
	JackMethods methods;
	
	public Help(ServerManager main, JackMethods methods)
	{
		this.main = main;
		this.methods = methods;
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e)
	{
		Player p = e.getPlayer();
		if (p.hasPermission("sm.jack.showhelp"))
		{
			if (e.getMessage().equalsIgnoreCase("Jack show help"))
			{
				p.sendMessage(main.colors("<Jack> &bShowing help.."));
				e.setCancelled(true);
				methods.showHelp(p);
			}
		}
	}
	
}
