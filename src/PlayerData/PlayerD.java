package PlayerData;

import java.io.Serializable;
import java.util.ArrayList;

import org.bukkit.entity.Player;

public class PlayerD implements Serializable{
	
	public ArrayList<PlayerD> players = new ArrayList<PlayerD>();

	private String playerName;
	private long accountBalance;
	
	private permissionEnum permission;
	public enum permissionEnum {
		player, moderator, admin, owner
	};
	
	private transient Player playerObj;
	
	
	
	public PlayerD(String playerName)
	{
		this.playerName = playerName;
		this.permission = permissionEnum.player;
	}
	
	public String getPlayerName()
	{
		return this.playerName;
	}
	public long getAccountBalance()
	{
		return this.accountBalance;
	}
	public permissionEnum getPermission()
	{
		return this.permission;
	}
	
	public PlayerD setBalance(long newBalance)
	{
		this.accountBalance = newBalance;
		return this;
	}
	public PlayerD setPermission(permissionEnum newPermission)
	{
		this.permission = newPermission;
		return this;
	}
}
