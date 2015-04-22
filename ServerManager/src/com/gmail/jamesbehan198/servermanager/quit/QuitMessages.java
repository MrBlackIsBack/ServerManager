package com.gmail.jamesbehan198.servermanager.quit;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.gmail.jamesbehan198.servermanager.ServerManager;

public class QuitMessages implements Listener
{
	
	ServerManager main;
	
	public QuitMessages(ServerManager main)
	{
		this.main = main;
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e)
	{
		Player p = e.getPlayer();
		
		e.setQuitMessage(main.colors(main.quitMsg
				.replace("^player^", p.getName())));
	}
	
}
