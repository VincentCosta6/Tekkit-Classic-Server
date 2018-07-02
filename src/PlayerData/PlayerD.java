package PlayerData;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class PlayerD implements Serializable{
	public static String URL = "/Data/Players/";
	public static ArrayList<PlayerD> players = new ArrayList<PlayerD>();
	public static ArrayList<LandPlot> allLand = new ArrayList<LandPlot>();

	private String playerName;
	private long accountBalance = 0;
	
	private permissionEnum permission;
	public enum permissionEnum {
		player, moderator, admin, owner
	};
	
	private transient Player playerObj;
	
	private ArrayList<Warp> warps = new ArrayList<Warp>();
	private ArrayList<LandPlot> landPlots = new ArrayList<LandPlot>();
	
	public PlayerD(String playerName)
	{
		this.playerName = playerName;
		this.permission = permissionEnum.player;
	}
	
	public Player getPlayerObj()
	{
		return this.playerObj;
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
	
	
	public ArrayList<Warp> getWarps()
	{
		return this.warps;
	}
	
	public Warp getWarp(int index)
	{
		return this.warps.get(index);
	}
	public Warp getWarp(String name)
	{
		for(Warp w: this.warps)
			if(w.getName().contentEquals(name))
				return w;
		return null;
	}
	
	public void setWarp(int index, Warp w)
	{
		this.warps.set(index, w);
	}
	
	
	public ArrayList<LandPlot> getLandPlots()
	{
		return this.landPlots;
	}
	public LandPlot getLandPlot(int index)
	{
		return this.landPlots.get(index);
	}
	
	
	
	
	public boolean save()
	{
		return savePlayer(this);
	}
	
	
	public static boolean savePlayer(PlayerD p)
	{
		String to = "";
		File file = null;
		try {
			to = Paths.get(".").toAbsolutePath().normalize().toString();
			file = new File(to + URL + p.getPlayerName() + ".txt");
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(p);
			oos.close();
		}
		catch(FileNotFoundException e)
		{
			File file2 = new File(to + URL + p.getPlayerName() + ".txt");
			try {
				file2.createNewFile();
				savePlayer(p);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
			String to = Paths.get(".").toAbsolutePath().normalize().toString();
			Path path = Paths.get(to + URL + name + ".txt");
			path.normalize();
			FileInputStream fin = new FileInputStream(path.toString());
			ObjectInputStream ois = new ObjectInputStream(fin);
			PlayerD player = (PlayerD) ois.readObject();
			ois.close();
			return player;
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	public static String myPath()
	{
		try {
			return (PlayerD.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public String toString()
	{
		StringBuilder s = new StringBuilder();
		s.append("Player: ");
		s.append(this.playerName);
		s.append(" Money: ");
		s.append(this.accountBalance);
		s.append(" Warps: ");
		s.append(this.warps.size());
		
		return s.toString();
	}
}
class Warp implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -464190363430117210L;
	private String name;
	private float x, y, z;
	
	public Warp(String name, float x, float y, float z)
	{
		this.name = name;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Location getLoc()
	{
		return new Location(Bukkit.getWorlds().get(0), (int) this.x, (int) this.y, (int) this.z);
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public float getX()
	{
		return this.x;
	}
	public float getY()
	{
		return this.y;
	}
	public float getZ()
	{
		return this.z;
	}
	
	
	public Warp setX(float x)
	{
		this.x = x;
		return this;
	}
	public Warp setY(float y)
	{
		this.y = y;
		return this;
	}
	public Warp setZ(float z)
	{
		this.z = z;
		return this;
	}
	
}

class LandPlot implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6482706151428933614L;
	
	
	
	private String owner;
	private int x1,y1;
	private int x2, y2;
	
	public LandPlot(String owner, int x1, int y1, int x2, int y2)
	{
		this.owner = owner;
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	public String getOwner()
	{
		return this.owner;
	}
	
	public float getX1()
	{
		return this.x1;
	}
	public float getY1()
	{
		return this.y1;
	}
	public float getX2()
	{
		return this.x2;
	}
	public float getY2()
	{
		return this.y2;
	}
	
	
}
