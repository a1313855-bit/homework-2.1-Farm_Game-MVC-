package service;

import entity.Player;

public interface PlayerService {
	//註冊
	void create_player(Player player);
	
	//檢測名稱是否相同
	boolean check_name(String name);
	
	//檢測帳號是否相同
	boolean check_username(String username);
	
	//檢測信箱是否相同
	boolean check_mail(String mail);
	
	//登入
	Player login(String username,String password);
	
	//忘記密碼
	void chenge_password(String mail,String password);
	
}
