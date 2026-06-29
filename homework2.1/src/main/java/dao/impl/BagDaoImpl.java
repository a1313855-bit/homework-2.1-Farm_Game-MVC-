package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.BagDao;
import entity.Bag;
import util.DbConnection;

public class BagDaoImpl implements BagDao {

    Connection conn = DbConnection.getDb();

    // ====================新增====================//

    @Override
    public void insert(Bag bag) {
        String sql = "insert into bag(player_name,item_name,quantity) values(?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, bag.getPlayer_name());
            ps.setString(2, bag.getItem_name());
            ps.setInt(3, bag.getQuantity());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ====================查詢誰的物品(單個)====================//

    @Override
    public Bag select_item(String player_name, String item_name) {
        String sql = "select * from bag where player_name=? and item_name=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, player_name);
            ps.setString(2, item_name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Bag bag = new Bag();
                bag.setId(rs.getInt("id"));
                bag.setPlayer_name(rs.getString("player_name"));
                bag.setItem_name(rs.getString("item_name"));
                bag.setQuantity(rs.getInt("quantity"));
                return bag;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // ====================查詢全部物品====================//

    @Override
    public List<Bag> select_all_name(String player_name) {
        List<Bag> list = new ArrayList<>();
        String sql = "select * from bag where player_name=? order by item_name";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, player_name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Bag bag = new Bag();
                bag.setId(rs.getInt("id"));
                bag.setPlayer_name(rs.getString("player_name"));
                bag.setItem_name(rs.getString("item_name"));
                bag.setQuantity(rs.getInt("quantity"));
                list.add(bag);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // ====================查詢誰的物品的數量====================//

    @Override
    public int select_item_count(String player_name, String item_name) {
        String sql = "select quantity from bag where player_name=? and item_name=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, player_name);
            ps.setString(2, item_name);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("quantity");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // ====================更新物品數量====================//

    @Override
    public void update_item_quantity(Bag bag) {
        String sql = "update bag set quantity=? where player_name=? and item_name=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, bag.getQuantity());
            ps.setString(2, bag.getPlayer_name());
            ps.setString(3, bag.getItem_name());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ====================增加道具數量====================//

    @Override
    public void add_item(String player_name, String item_name, int quantity) {

        String check_sql = "select * from bag where player_name=? and item_name=?";
        
        try {
            PreparedStatement ps = conn.prepareStatement(check_sql);
            ps.setString(1, player_name);
            ps.setString(2, item_name);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int current = rs.getInt("quantity");

                String updateSql = "update bag set quantity=? where player_name=? and item_name=?";
                PreparedStatement ups = conn.prepareStatement(updateSql);
                ups.setInt(1, current + quantity);
                ups.setString(2, player_name);
                ups.setString(3, item_name);
                ups.executeUpdate();

            } else {
                String insertSql = "insert into bag(player_name, item_name, quantity) values(?,?,?)";
                PreparedStatement ips = conn.prepareStatement(insertSql);
                ips.setString(1, player_name);
                ips.setString(2, item_name);
                ips.setInt(3, quantity);
                ips.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ====================減少道具數量====================//

    @Override
    public void decrease_item(String player_name, String item_name, int quantity) {
        String sql = "update bag set quantity = quantity - ? where player_name=? and item_name=? and quantity >= ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, quantity);
            ps.setString(2, player_name);
            ps.setString(3, item_name);
            ps.setInt(4, quantity);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
