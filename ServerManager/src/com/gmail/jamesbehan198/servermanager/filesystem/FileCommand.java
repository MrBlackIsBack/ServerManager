/**
 All rights reserved (C) Mr_Black_Is_Back
 */
package com.gmail.jamesbehan198.servermanager.filesystem;

import java.io.File;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.jamesbehan198.servermanager.ServerManager;

public class FileCommand implements CommandExecutor
{

	ServerManager main;
	FileSystem sys;

	public FileCommand(ServerManager main)
	{
		this.main = main;
		sys = new FileSystem(main);
	}

	private String temp = "";

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (!(sender instanceof Player))
			return true;

		Player p = (Player) sender;

		if (sender.hasPermission("sm.filewriter.write"))
		{
			if (cmd.getName().equalsIgnoreCase("fw"))
			{
				if (args.length == 0)
				{
					p.sendMessage(main.colors("&cPlease type something!"));
					return true;
				}

				if (args.length > 0)
				{
					if (main.enableFileWriter)
					{
						if (args[0].equalsIgnoreCase("deleteall"))
						{
							for (File file : main.getDataFolder().listFiles())
							{
								if (file.getName().startsWith("File"))
								{
									file.delete();
								}

							}
							p.sendMessage(main.colors("&cDeleting files..."));

							return true;
						}

						for (String msg : args)
						{
							temp += (msg + " ");
						}

						p.sendMessage(main.colors("&aCreating file! &2(File1.txt)"));

						File[] files = main.getDataFolder().listFiles();

						int fileNum = 1;

						if (files.length > 10)
						{
							System.out.println("DEBUG 1");
							return true;
						}

						if (new File(main.getDataFolder(), "File" + fileNum + ".txt").exists())
						{
							fileNum++;
							sys.createFile("File" + fileNum, ".txt");
						} else
						{
							sys.createFile("File1", ".txt");
						}

						p.sendMessage(main.colors("&aThe file has been writen to!s"));

						sys.writeToFile(new File(main.getDataFolder(), "File" + fileNum + ".txt"), temp);

						return true;
					}
				}
			}
		}
		return true;
	}
}
