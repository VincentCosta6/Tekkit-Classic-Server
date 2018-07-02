package Listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import Main.start;
import PlayerData.PlayerD;

public class PlayerJoin implements Listener{
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e)
	{
		PlayerD p = PlayerD.readPlayer(e.getPlayer().getDisplayName());
		if(p == null) {
			start.log(ChatColor.YELLOW + "Welcome " + e.getPlayer().getDisplayName() + " to the server!");
			p = new PlayerD(e.getPlayer().getDisplayName());
			p.save();
		}
		else {
			start.log(ChatColor.YELLOW + "Welcome back " + e.getPlayer().getDisplayName());
			
			
		}
		
		PlayerD.players.add(p);
		for(int i=0; i<p.getLandPlots().size(); i++)
			PlayerD.allLand.add(p.getLandPlot(i));
			
	}
}
