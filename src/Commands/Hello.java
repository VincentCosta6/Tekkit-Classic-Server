package Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import Main.start;

public class Hello implements CommandExecutor{

	public boolean onCommand(CommandSender arg0, Command arg1, String arg2,
			String[] arg3) {
		start.log("YOU DID IT");
		return false;
	}

}
