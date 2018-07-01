package Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import Main.start;

public class BlockBreak implements Listener{
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e)
	{
		start.log("Block has been broken");
	}
}
