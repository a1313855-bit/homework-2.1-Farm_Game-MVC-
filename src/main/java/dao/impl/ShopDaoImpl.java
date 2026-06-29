package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ShopDao;
import entity.Shop;
import util.DbConnection;

public class ShopDaoImpl implements ShopDao{

	public static void main(String[] args) {
		//new ShopDaoImpl().insert(new Shop("洋蔥種子",20,40,45));

		//System.out.println(new ShopDaoImpl().select_all());
		
		//System.out.println(new ShopDaoImpl().select_id(4));
	}

	Connection conn=DbConnection.getDb();
	
	// =======================新增商店物品=======================//
	
	@Override
	public void insert(Shop shop) {
		String sql="insert into shop(seed_name,buy_price,sell_price,grow_time) "
				+ "values(?,?,?,?)";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, shop.getSeed_name());
			ps.setInt(2, shop.getBuy_price());
			ps.setInt(3, shop.getSell_price());
			ps.setInt(4, shop.getGrow_time());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	// =======================查詢商店物品(全部)=======================//

	@Override
	public List<Shop> select_all() {
		String sql="select * from shop";
		List<Shop> l=new ArrayList<>();
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				Shop shop=new Shop();
				shop.setId(rs.getInt("id"));
				shop.setSeed_name(rs.getString("seed_name"));
				shop.setBuy_price(rs.getInt("buy_price"));
				shop.setSell_price(rs.getInt("sell_price"));
				shop.setGrow_time(rs.getInt("grow_time"));
				l.add(shop);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	}

	// =======================查詢商店物品(ID)=======================//
	
	@Override
	public Shop select_id(int id) {
		String sql="select * from shop where id=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				Shop shop=new Shop();
				shop.setId(rs.getInt("id"));
				shop.setSeed_name(rs.getString("seed_name"));
				shop.setBuy_price(rs.getInt("buy_price"));
				shop.setSell_price(rs.getInt("sell_price"));
				shop.setGrow_time(rs.getInt("grow_time"));
				return shop;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	
	// =======================查詢商店物品(名稱)=======================//
	
	@Override
	public Shop select_name(String seed_name) {
		String sql="select * from shop where seed_name=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, seed_name);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				Shop shop=new Shop();
				shop.setId(rs.getInt("id"));
				shop.setSeed_name(rs.getString("seed_name"));
				shop.setBuy_price(rs.getInt("buy_price"));
				shop.setSell_price(rs.getInt("sell_price"));
				shop.setGrow_time(rs.getInt("grow_time"));
				return shop;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
