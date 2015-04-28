package com.gmail.jamesbehan198.servermanager;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

 /**
   Copyright @Mr_Black_Is_Back to Present
   All rights reserved.
 */

public class AdminMode implements CommandExecutor, Listener {
	
	ServerManager main;
	
	public AdminMode(ServerManager main) {
		this.main = main;
	}
	
	public ArrayList<Player> vanish = new ArrayList<Player>();
	public ArrayList<String> flying = new ArrayList<String>();
	
	@EventHandler
	public void	onPlayerLeave(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		
		vanish.remove(p.getName());
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(main.colors("&cYou must be a player to preform this command."));
			return true;
		}
		
		Player p = (Player) sender;
		
		if(p.hasPermission("sm.adminmode")) {
			if(cmd.getName().equalsIgnoreCase("admin")) {
				if(args.length == 0) {
					p.sendMessage(main.colors("&cAdmin mode > Help"));
					p.sendMessage(main.colors("&a/admin vanish")); // DONE
					p.sendMessage(main.colors("&a/admin invsee [player]")); // DONE
					p.sendMessage(main.colors("&a/admin sethealth [player] [health]")); // DONE
					p.sendMessage(main.colors("&a/admin fly")); // DONE
					return true;
				}
				
				if(args.length == 1) {
					if(args[0].equalsIgnoreCase("vanish")) {
						if(!vanish.contains(p)) {
							p.sendMessage(main.colors("&2You are now vanished!"));
							p.sendMessage(main.colors("&7Note: If you log out, you will be un vanished!"));
							
							for(Player pl : Bukkit.getServer().getOnlinePlayers()) {
								pl.hidePlayer(p);
							}
							
							vanish.add(p);
							return true;
						}
						
						p.sendMessage(main.colors("&cYou are no longer vanished!"));
						vanish.remove(p);
						return true;
					} else if(args[0].equalsIgnoreCase("fly")) {
						if(!flying.contains(p.getName())) {
							p.sendMessage(main.colors("&2You are now able to fly!"));
							p.setAllowFlight(true);
							p.setFlying(true);
							flying.add(p.getName());
							return true;
						}
						
						p.sendMessage(main.colors("&cYou can no longer fly!"));
						p.setAllowFlight(false);
						p.setFlying(false);
						flying.remove(p.getName());
						return true;
					}
				}
				
				if(args.length == 2) {
					if(args[0].equalsIgnoreCase("invsee")) {
						Player t = Bukkit.getServer().getPlayer(args[1]);
						if(t != null) {
							p.sendMessage(main.colors("&2Opening&6 " + t.getName() + "&2's inventory..."));
							p.openInventory(t.getInventory());
							
							return true;
						} else 
							p.sendMessage(main.colors("&cPlayer is not online or invalid!"));
					}
				}
				
				if(args.length == 3) {
					if(args[0].equalsIgnoreCase("sethealth")) {
						Player t = Bukkit.getServer().getPlayer(args[1]);
						double health = Integer.parseInt(args[2]);
						
						if(t != null) {
							if(args[2] != null) {
								if(health > 20) {
									p.sendMessage(main.colors("&cHealth cannot be set higher than 20!"));
									return true;
								}
								t.setHealth(health);
								return true;
							} else 
								p.sendMessage(main.colors("&cPlease define a number (20 or under)!"));
						} else 
							p.sendMessage(main.colors("&cPlayer is not online or invalid!"));
						return true;
					}
				}
			}
		}
		return true;
	}

}
