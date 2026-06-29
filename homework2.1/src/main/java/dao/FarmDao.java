package dao;

import java.util.List;

import entity.Farm;

public interface FarmDao {
	//create
	//每位玩家擁有的田
	void insert(String player_name);
	
	//read
	//查詢某個玩家的土地
	List<Farm> select_player(String player_name);	
	//查詢某個玩家的某個土地
	Farm select_one(String player_name, int farm_number);	
	//查詢種子的收成時間
	int select_grow_time(String seed_name);
	
	//update
	//更新田區種植狀態
	void plant_seed(String player_name, int farm_number, String seed_name); 	 	
	//更新田區收成狀態
	void clearField(String player_name, int farm_number);
	//修改田區狀態
	void unlock_farm(String player_name, int farm_number);
	
	//delete
	
	
}
