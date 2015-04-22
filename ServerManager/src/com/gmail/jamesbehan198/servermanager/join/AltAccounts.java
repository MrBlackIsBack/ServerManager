package com.gmail.jamesbehan198.servermanager.join;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.gmail.jamesbehan198.servermanager.ServerManager;

public class AltAccounts implements Listener
{
	
	ServerManager main;
	
	public AltAccounts(ServerManager main)
	{
		this.main = main;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e)
	{
		Player p = e.getPlayer();
		
		if (p.hasPermission("sm.alts.bypass"))
			return;
		
		if (main.checkForAlts)
		{
			
			for (Player all : main.getServer().getOnlinePlayers())
			{
				if (all.getName().equals(p.getName()))
					return;
				
				if (p.getAddress().getHostName().toString().equalsIgnoreCase(all.getAddress().getHostName().toString()))
				{
					if (all.isOp())
					{
						Player ops = (Player) all;
						ops.sendMessage(main.colors("&cPlayer &6" + p.getName() + " &cand &6" + all.getName() + " &chave the same IP Address."));
						main.getServer().getConsoleSender().sendMessage(main.colors("&cPlayer &6" + p.getName() + " &cand &6" + all.getName() + " &chave the same IP Address."));
					}
				}
			}
		}
	}
	
}
