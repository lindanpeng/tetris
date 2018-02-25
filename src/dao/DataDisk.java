package dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import dto.Player;

public class DataDisk implements Data {

	private  final String filePath;
	
	public DataDisk (HashMap<String,String> param){
		this.filePath=param.get("path");
	}
	@Override
	public List<Player> loadData()  {
		// TODO Auto-generated method stub
		ObjectInputStream ois =null;
		List<Player> players=null;
		try {
			ois=new ObjectInputStream(new FileInputStream(filePath));
			players=(List<Player>)ois.readObject();
		} catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		finally{
			try {
                ois.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return players;
	}

	@Override
	public void saveData(Player player) {
		List<Player> players=this.loadData();
		Collections.sort(players);
		while(players.size()>5)
		{
			players.remove(5);
		}
		players.add(player);
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(filePath));
			oos.writeObject(players);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
				try {
					oos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
		
	}
    
}
