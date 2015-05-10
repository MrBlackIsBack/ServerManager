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
package com.gmail.jamesbehan198.servermanager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.jamesbehan198.servermanager.chat.Spam;
import com.gmail.jamesbehan198.servermanager.fun.player.WalkSpeed;
import com.gmail.jamesbehan198.servermanager.jackirc.Help;
import com.gmail.jamesbehan198.servermanager.jackirc.JackMethods;
import com.gmail.jamesbehan198.servermanager.jackirc.Kick;
import com.gmail.jamesbehan198.servermanager.jackirc.Kill;
import com.gmail.jamesbehan198.servermanager.jackirc.opmehpls;
import com.gmail.jamesbehan198.servermanager.join.AltAccounts;
import com.gmail.jamesbehan198.servermanager.join.JoinMessage;
import com.gmail.jamesbehan198.servermanager.quit.QuitMessages;
import com.gmail.jamesbehan198.servermanager.spawnsystem.RemoveSpawn;
import com.gmail.jamesbehan198.servermanager.spawnsystem.SetSpawn;
import com.gmail.jamesbehan198.servermanager.spawnsystem.Spawn;

public class ServerManager extends JavaPlugin {

	private AdminMode admin;

	// Lists;
	public List<String> spamming;
	public List<String> password;
	public List<String> broadcastMessages;

	// Booleans;
	public boolean chatTimer;
	public boolean onAdvertise;
	public boolean checkForAlts;
	public boolean offlineMode;
	public boolean showTitleOnJoin;
	public boolean spawnSystem;
	public boolean lagTimer;
	public boolean enableJack;
	public boolean doBroadcasts;

	// Ints:
	public long timer;
	public long broadcastTimer;

	// Strings;
	public String newJoinMsg;
	public String regJoinMsg;
	public String title;
	public String subTitle;
	public String titleColor;
	public String subTitleColor;
	public String quitMsg;

	@Override
	public void onEnable() {
		// Lists:
		spamming = new ArrayList<String>();
		password = new ArrayList<String>();
		broadcastMessages = getConfig().getStringList("broadcast.messages");

		// Strings related to the config:
		newJoinMsg = getConfig().getString("joinMSG.newPlayer");
		regJoinMsg = getConfig().getString("joinMSG.regPlayer");

		title = getConfig().getString("titleMSG.title");
		subTitle = getConfig().getString("titleMSG.subTitle");
		titleColor = getConfig().getString("titleMSG.titleColor");
		subTitleColor = getConfig().getString("titleMSG.subTitleColor");

		quitMsg = getConfig().getString("quitMSG.msg");

		// Ints related to the config:
		timer = getConfig().getLong("removeLag.delay");
		broadcastTimer = getConfig().getLong("broadcast.timer");

		// Booleans related to the config:
		chatTimer = getConfig().getBoolean("alphaFeatures.chatTimer");
		onAdvertise = getConfig().getBoolean("alphaFeatures.onAdvertise");
		checkForAlts = getConfig().getBoolean("checkForAlts");
		offlineMode = getConfig().getBoolean("inOfflineMode");
		showTitleOnJoin = getConfig().getBoolean("titleMSG.showTitle");
		spawnSystem = getConfig().getBoolean("spawnSystem.enabled");
		lagTimer = getConfig().getBoolean("removeLag.doTimer");
		doBroadcasts = getConfig().getBoolean("broadcast.enable");
		// enableJack = getConfig().getBoolean("alphaFeatures.enableJack");
		enableJack = false; // Temp;

		// Registering and saving:
		registry();
		saveDefaultConfig();
		admin = new AdminMode(this);

		admin.vanish.clear();
		admin.flying.clear();

		broadcast();
	}

	@Override
	public void onDisable() {
		// Config;
		saveDefaultConfig();

		admin.vanish.clear();
		admin.flying.clear();
	}

	/*
	 * Easy color support.
	 */
	public String colors(String msg) {
		return ChatColor.translateAlternateColorCodes('&', msg);
	}

	// Registry for commands and events.
	public void registry() {
		// Spawn System:
		getCommand("setspawn").setExecutor(new SetSpawn(this));
		getCommand("spawn").setExecutor(new Spawn(this));
		getCommand("remspawn").setExecutor(new RemoveSpawn(this));
		
		// Other:
		getCommand("admin").setExecutor(new AdminMode(this));
		getCommand("walkspeed").setExecutor(new WalkSpeed(this));

		// Jack
		this.getServer().getPluginManager().registerEvents(new Kick(this), this);
		this.getServer().getPluginManager().registerEvents(new Help(this, new JackMethods(this)), this);
		this.getServer().getPluginManager().registerEvents(new Kill(this, new JackMethods(this)), this);
		this.getServer().getPluginManager().registerEvents(new opmehpls(this), this);

		// Chat Related:
		this.getServer().getPluginManager().registerEvents(new Spam(this), this);

		// Join / Quit related:
		this.getServer().getPluginManager().registerEvents(new JoinMessage(this), this);
		this.getServer().getPluginManager().registerEvents(new QuitMessages(this), this);
		this.getServer().getPluginManager().registerEvents(new AltAccounts(this), this);
		this.getServer().getPluginManager().registerEvents(new AdminMode(this), this);
	}

	public void broadcast() {
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {

			@Override
			public void run() {
				if (doBroadcasts) {
					String trans = ChatColor.translateAlternateColorCodes('&', broadcastMessages.get(new Random().nextInt(broadcastMessages.size())));
					Bukkit.broadcastMessage(trans);
				}
			}
		}, 0, broadcastTimer * 20);
	}
}
