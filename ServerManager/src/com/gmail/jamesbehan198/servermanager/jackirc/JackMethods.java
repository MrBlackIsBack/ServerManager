package com.gmail.jamesbehan198.servermanager.jackirc;

import org.bukkit.entity.Player;

import com.gmail.jamesbehan198.servermanager.ServerManager;

public class JackMethods
{
	ServerManager main;
	
	public JackMethods(ServerManager main)
	{
		this.main = main;
	}
	
	public void showHelp(Player p)
	{
		p.sendMessage(main.colors("&4               [BETA]           "));
		p.sendMessage(main.colors("&c-----------Jack-----------"));
		p.sendMessage(main.colors("&aJack could you kick <name> please")); // Done
		p.sendMessage(main.colors("&aJack show help")); // Done
		p.sendMessage(main.colors("&aJack kill <name>")); // Done
		p.sendMessage(main.colors("&ajck op meh pls i fan")); // Done
		p.sendMessage(main.colors("&c-----------Jack-----------"));
	}
	
}
