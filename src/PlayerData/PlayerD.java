package PlayerData;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import org.bukkit.entity.Player;

public class PlayerD implements Serializable{
	public static String URL = "../Data/Players/";
	public static ArrayList<PlayerD> players = new ArrayList<PlayerD>();

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
	
	public boolean save()
	{
		return savePlayer(this);
	}
	
	
	
	
	public static boolean savePlayer(PlayerD p)
	{
		try {
			FileOutputStream fos = new FileOutputStream(URL + p.playerName + ".txt");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(p);
			oos.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public static PlayerD readPlayer(String name)
	{
		try { 
			FileInputStream fin = new FileInputStream(URL + name + ".txt");
			ObjectInputStream ois = new ObjectInputStream(fin);
			PlayerD player = (PlayerD) ois.readObject();
			ois.close();
			return player;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
