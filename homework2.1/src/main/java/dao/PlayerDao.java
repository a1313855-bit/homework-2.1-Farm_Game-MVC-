package dao;

import entity.Player;

public interface PlayerDao {
	//create
	//新增玩家
	void insert(Player player);
	
	
	//read
	//查詢ID
	Player select_id(int id);
	//查詢名稱
	Player select_name(String name);
	//查詢帳號
	Player select_username(String username);
	//查詢帳號密碼
	Player select_username_and_password(String username,String password);
	//查詢信箱
	Player select_mail(String mail);
	
	
	//update
	//修改密碼
	void update_password(Player player);
	
	//修改金額
	void update_data(Player player);
	
	
	//delete
	
	
	
}
