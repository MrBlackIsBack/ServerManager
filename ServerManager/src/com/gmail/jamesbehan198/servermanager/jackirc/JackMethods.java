package com.gmail.jamesbehan198.servermanager.jackirc;

import org.bukkit.entity.Player;

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
