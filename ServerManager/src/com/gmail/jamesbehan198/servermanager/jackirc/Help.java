package com.gmail.jamesbehan198.servermanager.jackirc;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.gmail.jamesbehan198.servermanager.ServerManager;

/*
 Licensed to the Apache Software Foundation (ASF) under one
 or more contributor license agreements.  See the NOTICE file
 distributed with this work for additional information
 regarding copyright ownership.  The ASF licenses this file
 to you under the Apache License, Version 2.0 (the
 "License"); you may not use this file except in compliance
 with the License.  You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing,
 software distributed under the License is distributed on an
 "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 KIND, either express or implied.  See the License for the
 specific language governing permissions and limitations
 under the License.
 */

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
