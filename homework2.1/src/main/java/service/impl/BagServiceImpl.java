package service.impl;

import java.util.List;

import dao.BagDao;
import dao.impl.BagDaoImpl;
import entity.Bag;
import service.BagService;

public class BagServiceImpl implements BagService {
	public static void main(String[] args) {
		
	}

    BagDao bdi = new BagDaoImpl();

    @Override
    public List<Bag> find_all_bag_table(String player_name) {
        return bdi.select_all_name(player_name);
    }
}
