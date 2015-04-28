package com.gmail.jamesbehan198.servermanager.join;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

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

public class AltAccounts implements Listener {

	ServerManager main;

	public AltAccounts(ServerManager main) {
		this.main = main;
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();

		if (p.hasPermission("sm.alts.bypass"))
			return;

		if (main.checkForAlts) {

			for (Player all : main.getServer().getOnlinePlayers()) {
				if (all.getName().equals(p.getName()))
					return;

				if (p.getAddress().getHostName().toString()
						.equalsIgnoreCase(all.getAddress().getHostName().toString())) {
					if (all.isOp()) {
						Player ops = (Player) all;
						ops.sendMessage(main.colors("&cPlayer &6" + p.getName() + " &cand &6"
								+ all.getName() + " &chave the same IP Address."));
						main.getServer()
								.getConsoleSender()
								.sendMessage(
										main.colors("&cPlayer &6" + p.getName() + " &cand &6"
												+ all.getName() + " &chave the same IP Address."));
					}
				}
			}
		}
	}

}
