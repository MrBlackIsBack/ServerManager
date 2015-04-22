package com.gmail.jamesbehan198.servermanager;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.jamesbehan198.servermanager.chat.Spam;
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

public class ServerManager extends JavaPlugin
{
	// Lists;
	public List<String> spamming;
	public List<String> password;
	
	// Booleans;
	public boolean chatTimer;
	public boolean onAdvertise;
	public boolean checkForAlts;
	public boolean offlineMode;
	public boolean showTitleOnJoin;
	public boolean spawnSystem;
	public boolean lagTimer;
	public boolean enableJack;
	
	// Ints:
	public long timer;
	
	// Strings;
	public String newJoinMsg;
	public String regJoinMsg;
	public String title;
	public String subTitle;
	public String titleColor;
	public String subTitleColor;
	public String quitMsg;
	
	
	@Override
	public void onEnable()
	{
		// Lists:
		spamming = new ArrayList<String>();
		password = new ArrayList<String>();
		
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
		
		// Booleans related to the config:
		chatTimer = getConfig().getBoolean("alphaFeatures.chatTimer");
		onAdvertise = getConfig().getBoolean("alphaFeatures.onAdvertise");
		checkForAlts = getConfig().getBoolean("checkForAlts");
		offlineMode = getConfig().getBoolean("inOfflineMode");
		showTitleOnJoin = getConfig().getBoolean("titleMSG.showTitle");
		spawnSystem = getConfig().getBoolean("spawnSystem.enabled");
		lagTimer = getConfig().getBoolean("removeLag.doTimer");
		enableJack = getConfig().getBoolean("alphaFeatures.enableJack");
		
		// Registering and saving:
		registry();
		saveDefaultConfig();
	}
	
	@Override
	public void onDisable()
	{
		// Config;
		saveDefaultConfig();
	}
	
	/*
	 * Easy color support.
	 */
	public String colors(String msg)
	{
		return ChatColor.translateAlternateColorCodes('&', msg);
	}
	
	// Registry for commands and events.
	public void registry()
	{	
		// Spawn System:
		getCommand("setspawn").setExecutor(new SetSpawn(this));
		getCommand("spawn").setExecutor(new Spawn(this));
		getCommand("remspawn").setExecutor(new RemoveSpawn(this));
		
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
	}
}
