package service;

import entity.Farm;

public interface FarmService {	
	//種植
	String plant_seed(String player_name, int farm_number, String seed_name);

	//收成
    String harvest(String player_name, int farm_number);
    
    //計算剩餘時間
    int get_remaining_time(String player_name, int farm_number);
    
    //解鎖田區
    String unlock_farm(String player_name, int farm_number);
    
    //查詢單塊田區
    Farm find_one_farm(String player_name, int farm_number);

}
