package service;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import entity.Shop;

public interface ShopService {
	//查詢商店物品
	List<Shop> find_all_shop_table();
	
	//查詢商店物品
	Shop find_shop_by_name(String seed_name);
	
	//購買物品
	String buy_seed(String player_name,String shop_item_name);
	
	//出售物品
	String sell_crop(String player_name, String crop_name);
	
	
	
}
