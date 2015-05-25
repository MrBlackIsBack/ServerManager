/**
 All rights reserved (C) Mr_Black_Is_Back
 */
package com.gmail.jamesbehan198.servermanager.filesystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;

import com.gmail.jamesbehan198.servermanager.ServerManager;

public class FileSystem
{

	ServerManager main;

	public FileSystem(ServerManager main)
	{
		this.main = main;
	}

	private File file;
	
	private List<File> allFiles = new ArrayList<File>();

	public void writeToFile(File file, String toWrite)
	{
		try
		{
			PrintWriter pw = new PrintWriter(file);
			
			pw.write(toWrite);
			pw.close();
		} catch (FileNotFoundException e)
		{
			Bukkit.getConsoleSender().sendMessage(main.colors("&cCould not find file " + file.getName()));
		}
	}
	
	public void createFile(String name, String type)
	{
		if (!new File(main.getDataFolder(), name + "." + type).exists())
		{
			file = new File(main.getDataFolder(), name + "." + type);
			allFiles.add(file);
		}
	}
	
	public File getFile(String name, String type)
	{
		return file.getAbsoluteFile();
	}
	
	public List<File> getAllFiles()
	{
		return allFiles;
	}
}
