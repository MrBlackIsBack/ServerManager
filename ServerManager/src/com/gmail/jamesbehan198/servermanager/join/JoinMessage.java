package com.gmail.jamesbehan198.servermanager.join;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import be.maximvdw.mctitle.TitleClass;

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

public class JoinMessage implements Listener
{
	
	ServerManager main;
	
	public JoinMessage(ServerManager main)
	{
		this.main = main;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e)
	{
		Player p = e.getPlayer();
		
		if (main.showTitleOnJoin)
		{
			TitleClass title = new TitleClass(main.title.replace("^player^", p.getName()), main.subTitle.replace("^player^", p.getName()));
			
			System.out.println("Debug 1;");
			title.setTitleColor(ChatColor.valueOf(main.titleColor));
			title.setSubtitleColor(ChatColor.valueOf(main.subTitleColor));
			System.out.println("Debug 2;");
			title.setTimingsToTicks();
			
			System.out.println("Debug 3;");
			title.send(p);
		}
		
		if (!p.hasPlayedBefore())
		{
			e.setJoinMessage(main.colors(main.newJoinMsg).replace("^player^", p.getName()));
		} else
		{
			e.setJoinMessage(main.colors(main.regJoinMsg).replace("^player^", p.getName()));
		}
	}
	
}
