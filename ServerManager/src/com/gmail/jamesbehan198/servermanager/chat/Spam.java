package com.gmail.jamesbehan198.servermanager.chat;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;

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

public class Spam implements Listener {

	ServerManager main;

	public Spam(ServerManager main) {
		this.main = main;
	}

	@EventHandler
	public void onCaps(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		String msg = e.getMessage();

		if (p.hasPermission("sm.spam.caps.bypass"))
			return;

		if (msg.matches(".*[A-Z].*")) {
			// As confusing as this looks... it's efficient.. trust me! :)
			e.setMessage(main.colors(msg.substring(0, 1).toUpperCase()
					+ msg.substring(1).toLowerCase() + "."));
		}
	}

	/*
	 * TODO Make more efficient!
	 */

	@EventHandler
	public void onAdvertise(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		String msg = e.getMessage();

		if (main.onAdvertise) {
			if (p.hasPermission("sm.spam.ad.bypass"))
				return;

			if (msg.indexOf('.', msg.indexOf('.') + 4) != -1) {
				p.sendMessage(main.colors("&cPlease don't use to many dots.. Thanks :)"));
				e.setCancelled(true);
			}

		}
	}

	/*
	 * TODO Make more efficient!
	 */
	@EventHandler
	public void chatTimer(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		String msg = e.getMessage();

		if (p.hasPermission("sm.spam.chattimer.bypass"))
			return;

		if (main.chatTimer) {
			if (main.spamming.contains(msg)) {
				for (int i = 0; i < main.spamming.size(); i++) {
					String tempName = main.spamming.get(i);

					if (msg.equalsIgnoreCase(tempName)) {
						e.setCancelled(true);
						p.sendMessage(main.colors("&cPlease do not repeat yourself"));
						main.spamming.remove(msg);
					}

					new BukkitRunnable() {

						@Override
						public void run() {
							main.spamming.remove(tempName);
						}
					}.runTaskLater(main, 40);

				}
			}

			main.spamming.add(msg);
		}
	}
}
