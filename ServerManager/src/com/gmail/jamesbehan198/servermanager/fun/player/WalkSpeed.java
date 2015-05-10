/**
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
package com.gmail.jamesbehan198.servermanager.fun.player;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.jamesbehan198.servermanager.ServerManager;

public class WalkSpeed implements CommandExecutor {

	ServerManager main;

	public WalkSpeed(ServerManager main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(main.colors("&cOnly players can set there walk speed!"));
			return true;
		}

		Player p = (Player) sender;

		if (p.hasPermission("sm.fun.player.walkspeed")) {
			if (cmd.getName().equalsIgnoreCase("walkspeed")) {
				if (args.length == 0) {
					p.sendMessage(main.colors("&cPlease define a speed. (1 or lower!)"));
					p.sendMessage(main.colors("&6Example usage: /walkspeed 0.5, /walkspeed normal"));
					return true;
				}

				if (args.length == 1) {
					if (args[0].equalsIgnoreCase("normal") && args[0] != null) {
						p.sendMessage(main.colors("&2Resetting walkspeed.."));
						p.setWalkSpeed(0.2f);
						return true;
					}
					
					if (args[0] != null) {
						try {
							float speed = Float.parseFloat(args[0]);

							if (speed <= 1) {
								p.setWalkSpeed(speed);
								p.sendMessage(main.colors("&2Speed set to: &6" + speed));
							} else {
								p.sendMessage(main.colors("&cSpeed cannot be greater than 1!"));
								p.sendMessage(main.colors("&6Example usage: /walkspeed 0.5, /walkspeed normal"));
								return true;
							}

							return true;

						} catch (NumberFormatException e) {
							p.sendMessage(main.colors("&cYou must set a valid speed!"));
							return true;
						}
					}
				}
			}
		}

		return true;
	}

}
