package service.impl;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import dao.BagDao;
import dao.PlayerDao;
import dao.ShopDao;
import dao.impl.BagDaoImpl;
import dao.impl.PlayerDaoImpl;
import dao.impl.ShopDaoImpl;
import entity.Bag;
import entity.Player;
import entity.Shop;
import service.ShopService;

public class ShopServiceImpl implements ShopService {

	public static void main(String[] args) {
		//new ShopServiceImpl().buy_seed("Mr.A", 2);
	}

	PlayerDao pdi=new PlayerDaoImpl();
	BagDao bdi=new BagDaoImpl();
	ShopDao sdi = new ShopDaoImpl();

	// =======================顯示商店=======================//

	@Override
	public List<Shop> find_all_shop_table() {
		return sdi.select_all();
	}
	
	// =======================顯示商店=======================//
	
	@Override
	public Shop find_shop_by_name(String seed_name) {
	    return sdi.select_name(seed_name);
	}

	// =======================購買物品=======================//

	@Override
	public String buy_seed(String player_name, String shop_item_name) {
		Player player = pdi.select_name(player_name);
		Shop shop = sdi.select_name(shop_item_name);

		if (player == null || shop == null) {
			return "資料錯誤";
		}

		if (player.getMoney() < shop.getBuy_price()) {
			return "購買失敗:金額不足";
		}

		player.setMoney(player.getMoney() - shop.getBuy_price());
		pdi.update_data(player);

		Bag bag = bdi.select_item(player_name, shop.getSeed_name());

		if (bag == null) {
			Bag newbag = new Bag();
			newbag.setPlayer_name(player.getName());
			newbag.setItem_name(shop.getSeed_name());
			newbag.setQuantity(1);
			bdi.insert(newbag);
		} else {
			bag.setQuantity(bag.getQuantity() + 1);
			bdi.update_item_quantity(bag);
		}

		return "購買成功";
	}
	
	// =======================出售物品=======================//

	@Override
	public String sell_crop(String player_name, String crop_name) {

	    Player player = pdi.select_name(player_name);

	    if (player == null) {
	        return "玩家資料錯誤";
	    }

	    int count = bdi.select_item_count(player_name, crop_name);

	    if (count <= 0) {
	        return "出售失敗：沒有這個農作物";
	    }

	    String seedName = crop_name + "種子";
	    Shop shop = sdi.select_name(seedName);

	    if (shop == null) {
	        return "出售失敗：找不到售價";
	    }

	    bdi.decrease_item(player_name, crop_name, 1);

	    player.setMoney(player.getMoney() + shop.getSell_price());
	    pdi.update_data(player);

	    return "出售成功：" + crop_name + "，獲得 " + shop.getSell_price() + " 元";
	}

}
