package Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

import Main.start;

public class PlayerC implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] arg3) {
		start.log(arg1.getName());
		start.log(arg2);
		for(String s: arg3)
			start.log(s);
		if(sender instanceof PlayerC)
		{
			
		}
		else if(sender instanceof ConsoleCommandSender)
		{
			
		}
		else
		{
			
		}
		return false;
	}

}
