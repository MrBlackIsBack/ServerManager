package com.gmail.jamesbehan198.servermanager.spawnsystem;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
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

public class SetSpawn implements CommandExecutor {

	private int x, y, z;
	private float yaw, pitch;
	private String world;

	ServerManager main;

	public SetSpawn(ServerManager main) {
		this.main = main;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (main.spawnSystem) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(main.colors("&cYou can only set the spawn in game!"));
				return true;
			}

			Player p = (Player) sender;

			if (p.hasPermission("sm.spawnsys.setspawn")) {
				if (cmd.getName().equalsIgnoreCase("setspawn")) {
					p.sendMessage(main.colors("&6Setting spawn . . ."));

					Location ploc = p.getLocation();

					world = ploc.getWorld().getName();

					x = ploc.getBlockX();
					y = ploc.getBlockY();
					z = ploc.getBlockZ();

					yaw = ploc.getYaw();
					pitch = ploc.getPitch();

					main.getConfig().set("spawnSystem.world", world);
					main.getConfig().set("spawnSystem.x", x);
					main.getConfig().set("spawnSystem.y", y);
					main.getConfig().set("spawnSystem.z", z);

					main.getConfig().set("spawnSystem.yaw", yaw);
					main.getConfig().set("spawnSystem.pitch", pitch);

					main.saveConfig();
					main.reloadConfig();

					p.sendMessage(main.colors("&aSpawn has been set! &7(x:&b" + x + " &7y:&b " + y
							+ " &7y:&b " + z));
					return true;
				}
			}

		}
		return true;
	}

}
