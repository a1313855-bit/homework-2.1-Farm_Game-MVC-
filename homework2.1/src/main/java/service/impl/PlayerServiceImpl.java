package service.impl;

import dao.FarmDao;
import dao.PlayerDao;
import dao.impl.FarmDaoImpl;
import dao.impl.PlayerDaoImpl;
import entity.Player;
import service.PlayerService;

public class PlayerServiceImpl implements PlayerService{

	public static void main(String[] args) {
		// =======================測試=======================//
		
		//註冊
		new PlayerServiceImpl().create_player(new Player("Mr.C","ccc","1234","ccc@gmail.com"));
		
		//檢測名稱是否相同
		//System.out.println(new PlayerServiceImpl().check_name("Mr.B"));
		
		//檢測帳號是否相同
		//System.out.println(new PlayerServiceImpl().check_username("aaa"));
		
		//檢測帳號是否相同
		//System.out.println(new PlayerServiceImpl().check_mail("ccc@gmail.com"));
		
		//登入
		//System.out.println(new PlayerServiceImpl().login("aaa", "1234"));
		
		//忘記密碼
		//new PlayerServiceImpl().chenge_password("aaa@gmail.com", "1234");
	}

	PlayerDao pdi=new PlayerDaoImpl();
	FarmDao fdi=new FarmDaoImpl();
	
	// =======================註冊=======================//
	@Override
	public void create_player(Player player) {
		pdi.insert(player);
		fdi.insert(player.getName());
	}

	
	// =======================檢測名稱是否相同=======================//
	@Override
	public boolean check_name(String name) {
		boolean x=false;
		Player player=pdi.select_name(name);
		if(player!=null) {
			x=true;
		}
		return x;
	}

	// =======================檢測帳號是否相同=======================//
	@Override
	public boolean check_username(String username) {
		boolean x=false;
		Player player=pdi.select_username(username);
		if(player!=null) {
			x=true;
		}
		return x;
	}
	
	// =======================檢測信箱是否相同=======================//
	
	@Override
	public boolean check_mail(String mail) {
		boolean x=false;
		Player player=pdi.select_mail(mail);
		if(player!=null) {
			x=true;
		}
		return x;
	}

	// =======================登入=======================//
	@Override
	public Player login(String username, String password) {
		return pdi.select_username_and_password(username, password);
	}

	// =======================忘記密碼=======================//
	@Override
	public void chenge_password(String mail, String password) {
		Player player=pdi.select_mail(mail);
		if(player!=null) {
			player.setPassword(password);
			pdi.update_password(player);
		}		
	}	
}
