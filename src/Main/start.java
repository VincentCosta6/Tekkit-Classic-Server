package Main;

import java.awt.List;
import java.beans.EventHandler;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public class start extends JavaPlugin{
	
	private static ConsoleCommandSender console;
	
	public void onEnable() {
		console = getServer().getConsoleSender();
		log(ChatColor.GREEN + "Vincent's Tekkit Classic Plugin has initialized");
		
		String[] commands = {"Hello", "PlayerC"};
		boolean result = registerCommands(commands);
		if(!result) {
			log(ChatColor.RED + "Server commands failed to register!");
			Bukkit.shutdown();
		}
		
		String[] events = {"BlockBreak"};
		result = registerCommands(events);
		if(!result) {
			log(ChatColor.RED + "Server events failed to register!");
			Bukkit.shutdown();
		}
		
	}
	
	public void onDisable() {
		log(ChatColor.GREEN + "Vincent's Tekkit Classic Plugin has been disabled");
	}
	
	public boolean registerCommands(String[] commands)
	{
		try {
			JavaPlugin plugin = (JavaPlugin) getServer().getPluginManager().getPlugin("VTekkitClassic");
			Method getFileMethod = JavaPlugin.class.getDeclaredMethod("getFile");
			getFileMethod.setAccessible(true);
			File jar = (File) getFileMethod.invoke(plugin);
			Set<Class<?>> classes = getClasses(jar, "Commands");
			for(String s: commands)
			{
				for(Class<?> c: classes)
				{
					try {
						if(s.contentEquals(c.getSimpleName()))
						{
							getCommand(s.substring(0, s.length()-1)).setExecutor((CommandExecutor) c.newInstance());
						}
					}
					catch(Exception e){
						Bukkit.shutdown();
						return false;
					}
				}
			}
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	public boolean registerEvents(String[] events)
	{
		PluginManager pm = getServer().getPluginManager();
		try {
			JavaPlugin plugin = (JavaPlugin) getServer().getPluginManager().getPlugin("VTekkitClassic");
			Method getFileMethod = JavaPlugin.class.getDeclaredMethod("getFile");
			getFileMethod.setAccessible(true);
			File jar = (File) getFileMethod.invoke(plugin);
			Set<Class<?>> classes = getClasses(jar, "Listeners");
			for(String s: events)
			{
				for(Class<?> c: classes)
				{
					try {
						if(s.contentEquals(c.getSimpleName()))
							pm.registerEvents((Listener)c.newInstance(), this);
					}
					catch(Exception e){
						Bukkit.shutdown();
						return false;
					}
				}
			}
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Logs a message to the console with color support
	 * @param toLog the message to send to the console
	 */
	public static void log(boolean toLog) //Logs parameter to console
	{
		try {
			console.sendMessage(toLog ? "true" : "false");
		}
		catch (NullPointerException e) {
			System.out.println("Console must be initialized first");
		}
		catch (Exception e) {
			System.out.println("No idea what happened here");
		}
	}
	public static void log(int toLog) //Logs parameter to console
	{
		try {
			console.sendMessage(toLog + "");
		}
		catch (NullPointerException e) {
			System.out.println("Console must be initialized first");
		}
		catch (Exception e) {
			System.out.println("No idea what happened here");
		}
	}
	public static void log(Object toLog) //Logs parameter to console
	{
		try {
			console.sendMessage(toLog.toString());
		}
		catch (NullPointerException e) {
			System.out.println("Console must be initialized first");
		}
		catch (Exception e) {
			System.out.println("No idea what happened here");
		}
	}
	public static void log(String toLog) //Logs parameter to console
	{
		try {
			console.sendMessage(toLog);
		}
		catch (NullPointerException e) {
			System.out.println("Console must be initialized first");
		}
		catch (Exception e) {
			System.out.println("No idea what happened here");
		}
	}
	
	public static Set<Class<?>> getClasses(File jarFile, String packageName) {
        Set<Class<?>> classes = new HashSet<Class<?>>();
        try {
            JarFile file = new JarFile(jarFile); 
            for (Enumeration<JarEntry> entry = file.entries(); entry.hasMoreElements();) { 
               JarEntry jarEntry = entry.nextElement();
               String name = jarEntry.getName().replace("/", ".");
               if(name.startsWith(packageName) && name.endsWith(".class"))
               classes.add(Class.forName(name.substring(0, name.length() - 6)));
            }
            file.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return classes;
    }
}
