package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import entity.Bag;
import entity.Player;
import entity.Shop;

public class Tool {

	public static void main(String[] args) {
		// 寫入測試
		//Player player=new Player("Mr.A","aaa","1234","aaa@gmail.com");
		//Tool.save_file("player.txt", player);

		// 讀出測試
		//System.out.println(Tool.read_file("player.txt"));
		
		//嘗試讀出資料
		/*
		Player player=(Player) Tool.read_file("player.txt");
		System.out.println("id:"+player.getId()+
				"\nname:"+player.getName()+
				"\nusername:"+player.getUsername()+
				"\npassword:"+player.getPassword()+
				"\nmail:"+player.getMail()+
				"\nmoney:"+player.getMoney());
		*/
		//Shop shop=(Shop) Tool.read_file("shop.txt");
		//System.out.println();
		
		//Bag bag=(Bag) Tool.read_file("bag.txt");
		//System.out.println();
		
	}

	// ===================寫入===================//

	public static void save_file(String file_name, Object object) {
		try {
			FileOutputStream fos = new FileOutputStream(file_name);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(object);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// ===================讀出===================//

	public static Object read_file(String file_name) {
		Object object = new Object();
		try {
			FileInputStream fis = new FileInputStream(file_name);
			ObjectInputStream ois = new ObjectInputStream(fis);
			object = ois.readObject();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return object;
	}

}
