package dao;

import java.util.List;

import entity.Shop;

public interface ShopDao {
	//create
	void insert(Shop shop);
	
	//read
	//查詢全部
	List<Shop> select_all();
	//查詢ID
	Shop select_id(int id);
	//查詢名稱
	Shop select_name(String seed_name);
	
	//update
	
	
	//delete
	
	
}
