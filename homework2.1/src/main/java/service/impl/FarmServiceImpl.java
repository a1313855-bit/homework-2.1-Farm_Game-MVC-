package service.impl;

import dao.BagDao;
import dao.FarmDao;
import dao.PlayerDao;
import dao.impl.BagDaoImpl;
import dao.impl.FarmDaoImpl;
import dao.impl.PlayerDaoImpl;
import entity.Farm;
import entity.Player;
import service.FarmService;

public class FarmServiceImpl implements FarmService {

	FarmDao fdi = new FarmDaoImpl();
	BagDao bdi = new BagDaoImpl();
	PlayerDao pdi = new PlayerDaoImpl();

	// ====================種植====================//

	@Override
	public String plant_seed(String player_name, int farm_number, String seed_name) {

		if (player_name == null || player_name.trim().isEmpty()) {
			return "種植失敗：玩家資料錯誤";
		}

		if (seed_name == null || seed_name.trim().isEmpty()) {
			return "種植失敗：請選擇種子";
		}

		seed_name = seed_name.trim();

		Farm farm = fdi.select_one(player_name, farm_number);

		// 舊玩家可能沒有 farm 資料，這裡自動補 4 塊田
		if (farm == null) {
			fdi.insert(player_name);
			farm = fdi.select_one(player_name, farm_number);
		}

		if (farm == null) {
			return "種植失敗：找不到農地";
		}

		if (farm.getIs_unlock() == 0) {
			return "種植失敗：這塊田尚未解鎖";
		}

		if (farm.getSeed_name() != null && !farm.getSeed_name().trim().isEmpty()) {
			return "種植失敗：這塊田已經種植";
		}

		int growTime = fdi.select_grow_time(seed_name);
		if (growTime <= 0) {
			return "種植失敗：找不到種子成熟時間";
		}

		int count = bdi.select_item_count(player_name, seed_name);

		if (count <= 0) {
			return "種植失敗：沒有種子";
		}

		bdi.decrease_item(player_name, seed_name, 1);
		fdi.plant_seed(player_name, farm_number, seed_name);

		return "種植成功：" + seed_name;
	}

	// ====================收成====================//

	@Override
	public String harvest(String player_name, int farm_number) {

		Farm farm = fdi.select_one(player_name, farm_number);

		if (farm == null || farm.getSeed_name() == null) {
			return "沒有作物";
		}

		String seed = farm.getSeed_name();
		String crop = seed.replace("種子", "");

		bdi.add_item(player_name, crop, 1);

		fdi.clearField(player_name, farm_number);

		return "收成成功：" + crop;
	}

	// ====================計算剩餘時間====================//

	@Override
	public int get_remaining_time(String player_name, int farm_number) {

		Farm farm = fdi.select_one(player_name, farm_number);

		if (farm == null) {
			return -1;
		}

		if (farm.getSeed_name() == null || farm.getSeed_name().trim().isEmpty()) {
			return -1;
		}

		if (farm.getPlant_time() == null) {
			return -1;
		}

		int growTime = fdi.select_grow_time(farm.getSeed_name());

		if (growTime <= 0) {
			return -1;
		}

		long now = System.currentTimeMillis();
		long plant = farm.getPlant_time().getTime();
		long elapsed = (now - plant) / 1000;
		long remain = growTime - elapsed;

		if (remain <= 0) {
			return 0;
		}

		return (int) remain;
	}

	// ====================解鎖田區====================//

	@Override
	public String unlock_farm(String player_name, int farm_number) {

		int price = 0;

		if (farm_number == 2) {
			price = 200;
		} else if (farm_number == 3) {
			price = 500;
		} else if (farm_number == 4) {
			price = 1000;
		} else {
			return "這塊田不用解鎖";
		}

		Player player = pdi.select_name(player_name);

		if (player == null) {
			return "玩家資料錯誤";
		}

		if (player.getMoney() < price) {
			return "金錢不足，無法解鎖";
		}

		player.setMoney(player.getMoney() - price);
		pdi.update_data(player);

		fdi.unlock_farm(player_name, farm_number);

		return "解鎖成功";
	}

	// ====================查詢單塊田區====================//

	@Override
	public Farm find_one_farm(String player_name, int farm_number) {
		return fdi.select_one(player_name, farm_number);
	}
}
