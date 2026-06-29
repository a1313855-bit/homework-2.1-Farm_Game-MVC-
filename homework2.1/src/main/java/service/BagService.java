package service;

import java.util.List;

import entity.Bag;

public interface BagService {
	//查詢背包內物品
	List<Bag> find_all_bag_table(String player_name);
}
