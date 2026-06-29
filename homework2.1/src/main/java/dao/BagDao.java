package dao;

import java.util.List;

import entity.Bag;

public interface BagDao {
	//create
	void insert(Bag bag);
	
	//read
	//查詢誰的物品(單個)
	Bag select_item(String player_name,String item_name);	
	//查詢全部物品
	List<Bag> select_all_name(String player_name);
	//查詢誰的物品的數量
	int select_item_count(String playerName, String itemName);
	
	//update
	//更新物品數量
	void update_item_quantity(Bag bag);	
	//增加物品數量
	void add_item(String playerName, String itemName, int quantity);
	//減少物品數量
	void decrease_item(String playerName, String itemName, int quantity);
	
	//delete
	
	
}
